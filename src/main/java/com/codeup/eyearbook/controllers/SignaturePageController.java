package com.codeup.eyearbook.controllers;

import com.codeup.eyearbook.models.Signatures;
import com.codeup.eyearbook.models.User;
import com.codeup.eyearbook.repositories.SignatureRepository;
import com.codeup.eyearbook.repositories.StudentRepository;
import com.codeup.eyearbook.repositories.UserRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SignaturePageController {
    private UserRepository userDao;
    private PasswordEncoder passwordEncoder;
    private SignatureRepository signatureDao;
    private StudentRepository studentsDao;

    public SignaturePageController(UserRepository userDao, PasswordEncoder passwordEncoder, SignatureRepository signatureDao, StudentRepository studentsDao) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.signatureDao = signatureDao;
        this.studentsDao = studentsDao;
    }


@GetMapping("signature-page")
public String redirectThisPage (){
        return "redirect:/home";
}

//    THIS CURRENTLY PREVENTS A PARENT FROM SEEING THE SIGNATURE PAGE, BUT REDIRECT DOES NOT WORK.  -----
    @GetMapping("/signature-page/{id}")
    public String signatureForm(@PathVariable("id") long profileId,Model model) {

 Authentication token = SecurityContextHolder.getContext().getAuthentication();
        boolean AnonCheck = token instanceof AnonymousAuthenticationToken;
        if (AnonCheck) return "users/login";
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        loggedInUser.getId();
//        BOBBIE: "DO WE NEED TO CREATE A NEW USER HERE?"
        User user = new User();
       user = userDao.getOne(profileId);
//      ===============================================


        model.addAttribute("signatures", new Signatures());
        //Armando: inserted this attribute to be able to find and display comments
        model.addAttribute("comment", signatureDao.findAll());
        //Armando: inserted this attribute to be able to find and display images
        String yearbookLink = "View yearbook";
        User loggedIn = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userDao.getOne(loggedIn.getId());
        String username = user.getUsername();
        model.addAttribute("user", user);
        model.addAttribute("username", username);


//IF IS PARENT REDIRECT TO HOME PAGE.....
        boolean isParent = loggedInUser.getIsParent();
        return !isParent  ? "users/signature-page/{id}" : "/home";
    }


    /* Armando : I set the path variable as a parameter for the setProfile_User (which is the profile that you are signing
    but the parameter type for setProfile_user needs to be a User
    If I leave postMapping to ("/signature-page") without the path variable itll post but I have to set profile_user manual
     */

    @PostMapping("/signature-page/{id}")
//    @RequestParam("yearbook_comment") String yearbook_comment,  @PathVariable("id") long id,
    public String saveSignatureIndividual(@RequestParam("yearbookComment") String yearbookComment,  @PathVariable("id") long id,@ModelAttribute Signatures signatures) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

//        System.out.println(id);
        User user = userDao.getOne(id);
        Signatures signature = new Signatures();
        signature.setProfile_user(user);
        signature.setSigner(loggedInUser);
        signature.setYearbook_comment(yearbookComment);
        signatureDao.save(signatures);

        // uncomment below to use with  @PostMapping("/signature-page")
//        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        signatures.setSigner(loggedInUser);
//        System.out.println(loggedInUser.getUsername());
//        // new line to test if comments appear?
//        signatureDao.save(signatures);
//        System.out.println(signatures.getYearbook_comment());
        return "redirect:/signature-page";
    }

    @GetMapping("/filestack/{id}")
    public String imageForm(@PathVariable("id")long id, Model model) {
        User user = userDao.getOne(id);

//        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(user.getUsername());
        model.addAttribute("user", user);
        return "users/file-stack";
    }

    //    Armando: I had to make this mapping to save the image, might be able to use one already made
    @PostMapping("/saveUser")
    public String saveUserImage(@ModelAttribute("user") User user){

        userDao.save(user);
        return "redirect:/parent-profile";
    }



}


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
        return "redirect:/";
}

//    THIS CURRENTLY PREVENTS A PARENT FROM SEEING THE SIGNATURE PAGE, BUT REDIRECT DOES NOT WORK.  -----
    @GetMapping("/signature-page/{id}")
    public String signatureForm(@PathVariable("id") long profileId,Model model) {

        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        loggedInUser = userDao.getOne(loggedInUser.getId());

        User pageOwner = userDao.getOne(profileId);
        model.addAttribute("pageOwner", pageOwner);

        model.addAttribute("signatures", new Signatures());
        String username = pageOwner.getUsername();
        model.addAttribute("username", username);

//psuedo code:  if user id=parent id, allow the parent to see this signature page
//IF IS PARENT REDIRECT TO HOME PAGE.....
        boolean isParent = loggedInUser.getIsParent();
        long loggedUserId = loggedInUser.getId();
        long pageOwnersParentId = pageOwner.getParent_id();
        return (!isParent || loggedUserId == pageOwnersParentId ) ? "users/signature-page" : "home";
    }


    /* Armando : I set the path variable as a parameter for the setProfile_User (which is the profile that you are signing
    but the parameter type for setProfile_user needs to be a User
    If I leave postMapping to ("/signature-page") without the path variable it'll post but I have to set profile_user manual
     */

    @PostMapping("/signature-page/{id}")
    public String saveSignatureIndividual(@PathVariable("id") long id,@ModelAttribute Signatures signatures) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User profileUser = userDao.getOne(id);

        signatures.setProfile_user(profileUser);
        signatures.setSigner(loggedInUser);
        signatureDao.save(signatures);


        return "redirect:/signature-page/{id}";
    }
//
//    @GetMapping("/bannerImg/{id}")
//    public String imageForm(@PathVariable("id")long id, Model model) {
//        User pageOwner = userDao.getOne(id);
//
//        model.addAttribute("pageOwner", pageOwner);
//        return "users/banner";
//    }

    @PostMapping("/bannerImg")
    public String saveUserImage(@ModelAttribute User pageOwner){
        System.out.println(pageOwner.getId());
//        System.out.println(user.getId());
        User currentUser = userDao.getOne(pageOwner.getId());
        currentUser.setSign_page_banner_image(pageOwner.getSign_page_banner_image());

//        if(pageOwner.getEmail().isEmpty()){
//            pageOwner.setEmail(null);
//        }
        userDao.save(currentUser);
//        userDao.save(pageOwner);
        return "redirect:/signature-page";
    }




}


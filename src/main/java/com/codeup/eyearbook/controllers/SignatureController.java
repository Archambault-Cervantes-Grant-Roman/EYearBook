package com.codeup.eyearbook.controllers;

import com.codeup.eyearbook.models.Signatures;
import com.codeup.eyearbook.models.User;
import com.codeup.eyearbook.repositories.SignatureRepository;
import com.codeup.eyearbook.repositories.StudentRepository;
import com.codeup.eyearbook.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SignatureController {
    private UserRepository users;
    private PasswordEncoder passwordEncoder;
    private SignatureRepository signatureDao;
    private StudentRepository studentsDao;

    public SignatureController(UserRepository users, PasswordEncoder passwordEncoder, SignatureRepository signatureDao, StudentRepository studentsDao) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
        this.signatureDao = signatureDao;
        this.studentsDao = studentsDao;
    }

    //TODO:this page needs to be dynamic between basic child and premium child
    @GetMapping("/signature-page/{id}")
    public String signaturePage(@PathVariable("id") long profileId, Model model){
        // Armando: I have to have the following 3 lines
        // to go to a users personal page and show their
        // personal banner image
        User user = users.getOne(profileId);
        model.addAttribute("signatures", new Signatures());

//        model.addAttribute("comment", comment.findAll());



        model.addAttribute("user", user);
        return "users/signature-page";
    }


    /* Armando : I set the path variable as a parameter for the setProfile_User (which is the profile that you are signing
    but the parameter type for setProfile_user needs to be a User
    If I leave postMapping to ("/signature-page") without the path variable itll post but I have to set profile_user manual
     */
//
    @PostMapping("/signature-page/{id}")
    public String saveSignatureIndividual(@PathVariable("id") long id, @ModelAttribute Signatures signatures) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User profileUser = users.getOne(id);

        signatures.setProfile_user(profileUser);
        signatures.setSigner(loggedInUser);
        signatureDao.save(signatures);

        return "redirect:/signature-page/{id}";
    }
//
//    @PostMapping("/signature-page")
//    public String saveSignatureIndividual(@ModelAttribute Signatures signatures) {
//        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        signatures.setSigner(loggedInUser);
//        // new line to test if comments appear?
//        signatureDao.save(signatures);
//        System.out.println(signatures.getYearbook_comment());
//        return "redirect:/parent-profile";
//    }

    // =====LEROY'S VERSION===== //
//    @PostMapping("/signature-page/{id}")
////    @RequestParam("yearbook_comment") String yearbook_comment,  @PathVariable("id") long id,
//    public String saveSignature(@RequestParam("yearbook_comment") String yearbook_comment,  @PathVariable("id") long id,@ModelAttribute Signatures signatures) {
//        Signatures signature = new Signatures();
//        signature.setYearbook_comment(yearbook_comment);
//        signature.save(signatures);
//        return "redirect:/parent-profile";
//    }


}


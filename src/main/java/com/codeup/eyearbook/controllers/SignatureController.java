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
    private UserRepository userDao;
    private PasswordEncoder passwordEncoder;
    private SignatureRepository signatureDao;
    private StudentRepository studentsDao;

    public SignatureController(UserRepository userDao, PasswordEncoder passwordEncoder, SignatureRepository signatureDao, StudentRepository studentsDao) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.signatureDao = signatureDao;
        this.studentsDao = studentsDao;
    }


    //TODO:this page needs to be dynamic between basic child and premium child
    @GetMapping("/signature-page/{id}")
    public String signaturePage(@PathVariable("id") long profileId, Model model){

        User user = userDao.getOne(profileId);
        model.addAttribute("signatures", new Signatures());
        model.addAttribute("user", user);
        boolean isParent = user.getIsParent();
        return !isParent  ? "users/signature-page" : "home";
//        return "users/signature-page";
    }

    @PostMapping("/signature-page/{id}")
    public String saveSignatureIndividual(@PathVariable("id") long id, @ModelAttribute Signatures signatures) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User profileUser = userDao.getOne(id);

        signatures.setProfile_user(profileUser);
        signatures.setSigner(loggedInUser);
        signatureDao.save(signatures);

        return "redirect:/signature-page/{id}";
    }
                    // ===================== TESTING PURPOSES =====================//

    @GetMapping("signature-page")
    public String redirectThisPage (){
        return "redirect:/home";
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


package com.codeup.eyearbook.controllers;

import com.codeup.eyearbook.models.Signatures;
import com.codeup.eyearbook.models.User;
import com.codeup.eyearbook.repositories.SignatureRepository;
import com.codeup.eyearbook.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@Controller
public class UserController {
    private UserRepository users;
    private PasswordEncoder passwordEncoder;
    private SignatureRepository comment;


    public UserController(UserRepository users, PasswordEncoder passwordEncoder, SignatureRepository comment) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
        this.comment = comment;
    }

    @GetMapping("/sign-up")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "users/register";
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user) {
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        users.save(user);
        return "redirect:/login";
    }


    @GetMapping("/signature-page/{id}")
    public String signaturePage(@PathVariable long id,  Model model){
        User user = users.getOne(id);
        model.addAttribute("user", user);
        return "users/signature-page";
    }

    @GetMapping("/parent-profile/{id}")
    public String parentProfile(@PathVariable long id,  Model model){
        User user = users.getOne(id);
        model.addAttribute("user", user);
        return "users/parent-profile";
    }

    @GetMapping("/signature-page")
    public String signatureForm(Model model) {
        model.addAttribute("signatures", new Signatures());
        return "users/signature-page";
    }

    @PostMapping("/signature-page")
    public String saveSignature(@ModelAttribute Signatures signatures) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        signatures.setSigner(loggedInUser);
        System.out.println(loggedInUser.getUsername());
        comment.save(signatures);
        System.out.println(signatures.getYearbook_comment());
        return "redirect:/signature-page";
    }

    @PostMapping("/SIGN-UP")
    public RedirectView addNew(User user, RedirectAttributes redir) {
        users.save(user);
        RedirectView  redirectView= new RedirectView("/login",true);
        redir.addFlashAttribute("message",
                "You successfully registered! You can now login");
        return redirectView;
    }


}


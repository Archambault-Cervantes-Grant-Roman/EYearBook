package com.codeup.eyearbook.controllers;


import com.codeup.eyearbook.models.Signatures;
import com.codeup.eyearbook.models.Student;
import com.codeup.eyearbook.models.User;
import com.codeup.eyearbook.repositories.SignatureRepository;
import com.codeup.eyearbook.repositories.StudentRepository;
import com.codeup.eyearbook.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class UserController {
    private UserRepository users;
    private PasswordEncoder passwordEncoder;
    private SignatureRepository comment;
    private StudentRepository studentsDao;


    public UserController(UserRepository users, PasswordEncoder passwordEncoder, SignatureRepository comment, StudentRepository studentsDao) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
        this.comment = comment;
        this.studentsDao = studentsDao;
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

    //TODO:this page needs to be dynamic between basic child and premium child
    @GetMapping("/signature-page/{id}")
    public String signaturePage(@PathVariable("id") long id, Model model){
        // Armando: I have to have the following 3 lines
        // to go to a users personal page and show their
        // personal banner image
        User user = users.getOne(id);
        model.addAttribute("signatures", new Signatures());
        model.addAttribute("user", user);
        return "users/signature-page";
    }


    @GetMapping("/parent-profile")
    public String parentProfile(Model model){
        User loggedIn = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = users.getOne(loggedIn.getId());
        model.addAttribute("user", user);
        return "users/parent-profile";
    }

    @GetMapping("edit-profile")
    public String editProfile(Model model){
        User loggedIn = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = users.getOne(loggedIn.getId());
        model.addAttribute("user", user);
        return "users/edit-profile";
    }

//      Armando: I dont think you no longer need this to edit profile
//      I made one called "@PostMapping("editUser")" that handles the post
//    @PostMapping("edit-profile")
//    public String update(@RequestParam (name="username") String username, @RequestParam (name="email") String email, @RequestParam (name="newPassword") String newPassword, @ModelAttribute User user, Model model) {
//        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        model.addAttribute("username", username);
//        model.addAttribute("email", email);
//        model.addAttribute("newPassword", newPassword);
//        long id = loggedInUser.getId();
//        users.getOne(id);
//        loggedInUser.setUsername(username);
//        loggedInUser.setEmail(email);
//        String hash = passwordEncoder.encode(loggedInUser.getPassword());
//        loggedInUser.setPassword(hash);
//        users.save(loggedInUser);
//        System.out.println(model.addAttribute("username", username));
//        return "redirect:/parent-profile";
//    }


    @GetMapping("/signature-page")
    public String signatureForm(Model model) {
        model.addAttribute("signatures", new Signatures());
        //Armando: inserted this attribute to be able to find and display comments
        model.addAttribute("comment", comment.findAll());
        //Armando: inserted this attribute to be able to find and display images
        return "users/signature-page";
    }

    @PostMapping("/signature-page")
    public String saveSignature(@ModelAttribute Signatures signatures) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        signatures.setSigner(loggedInUser);
        System.out.println(loggedInUser.getUsername());

        System.out.println(signatures.getYearbook_comment());
        return "redirect:/signature-page";
    }

    @GetMapping("/filestack/{id}")
    public String imageForm(@PathVariable("id")long id, Model model) {
        User user = users.getOne(id);

//        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(user.getUsername());
        model.addAttribute("user", user);
        return "users/file-stack";
    }


//    Armando: I had to make this mapping to save the image, might be able to use one already made
    @PostMapping("saveUser")
    public String saveUserImage(@ModelAttribute("user") User user){

        users.save(user);
        return "redirect:/parent-profile";
    }

    //    Armando: I had to make this mapping to edit the user info, might be able to use one already made

    @PostMapping("editUser")
    public String updateUserInfo(@ModelAttribute("user") User user){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        users.save(user);
        return "redirect:/parent-profile";
    }

//    @PostMapping("/filestack/{id}")
//    public String saveImage(@PathVariable("id") long id, @Valid User user,
//                            BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            user.setId(id);
//            return "users/file-stack";
//        }
//
//        users.save(user);
//        model.addAttribute("users", users.findAll());
//        return "redirect:/parent-profile";
//    }

//CHILD REGISTRATION PART ONE - STUDENT ID********************

@GetMapping("/register-child")
public String childRegister() {
        return "users/register-child";
}

//APPLYS THE CHILDS INFO ONTO THE CARD FOR PART 2 OF CHILD REGISTRATION**************
    @PostMapping("/register-child")
    public String locateByStudentId(@RequestParam long id, Model model) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        loggedInUser.getId();
        Student s = studentsDao.getByStudent_id(id);
        model.addAttribute("studentId", s.getStudent_id());
        model.addAttribute("firstName", s.getFirst_name());
        model.addAttribute("lastName", s.getLast_name());
//        this creates a new user from the student record
        model.addAttribute("user", new User() );
        return "users/child-register2";
    }


    @PostMapping("/child-register2")
    public String saveChildUser(@ModelAttribute User user) {
        String hash = passwordEncoder.encode(user.getPassword());
//        get the parents id
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
   long parentId =  loggedInUser.getId();
        user.setParent_id(parentId);
        user.setPassword(hash);
        users.save(user);
        return "redirect:/parent-profile";
    }



}


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


//*****************PARENT PROFILE PAGE******************************
    @GetMapping("/parent-profile")
    public String parentProfile(Model model){
        User loggedIn = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = users.getOne(loggedIn.getId());
        model.addAttribute("user", user);
        return "users/parent-profile";
    }


    @PostMapping("/purchase-code")
    public String enteredPurchaseCode(@ModelAttribute User user, @RequestParam(name = "code") String code) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long parentId = loggedInUser.getId();
        user = users.getOne(parentId);

        code = "DX978J3";

        if (code.equals("DX978J3")) {
            user.setOwns_yearbook(true);
//            can user once we implement roles
//            Role role = userroleDao.getOne(1);
//            user.getRoles().add(role);
        }

        users.save(user);
        return "redirect:/parent-profile";
    }

    @GetMapping("edit-profile")
    public String editProfile(Model model){
        User loggedIn = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = users.getOne(loggedIn.getId());
        model.addAttribute("user", user);
        return "users/edit-profile";
    }

    @PostMapping("editUser")
    public String updateUserInfo(@ModelAttribute("user") User user){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        users.save(user);
        return "redirect:/parent-profile";
    }



    //*****************---END----PARENT PROFILE PAGE******************************
    @GetMapping("/signature-page")
    public String signatureForm(Model model) {

        model.addAttribute("signatures", new Signatures());
        //Armando: inserted this attribute to be able to find and display comments
        model.addAttribute("comment", comment.findAll());
        //Armando: inserted this attribute to be able to find and display images
        // Armando : not too sure if this belongs in the
        // generic signature-page area
        String yearbookLink = "View yearbook";
        User loggedIn = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = users.getOne(loggedIn.getId());
        model.addAttribute("user", user);

        if(user.isOwns_yearbook()){
            model.addAttribute("yearbookLink", yearbookLink);
        }
        return "users/signature-page";
    }

    @PostMapping("/signature-page")
    public String saveSignature(@ModelAttribute Signatures signatures) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        signatures.setSigner(loggedInUser);
        System.out.println(loggedInUser.getUsername());
        // new line to test if comments appear?
        comment.save(signatures);
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
    @PostMapping("/saveUser")
    public String saveUserImage(@ModelAttribute("user") User user){

        users.save(user);
        return "redirect:/parent-profile";
    }

//****************CHILD REGISTRATION PART ONE - STUDENT ID********************

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


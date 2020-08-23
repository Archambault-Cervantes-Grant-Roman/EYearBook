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
import org.springframework.web.bind.annotation.*;

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

//TODO:does this grab the current logged in user?
    //TODO:this page needs to be dynamic between basic child and premium child
    @GetMapping("/signature-page/{id}")
    public String signaturePage(@PathVariable long id,  Model model){
        User loggedIn = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = users.getOne(id);
        model.addAttribute("user", user);
        model.addAttribute("signatures", new Signatures());
        model.addAttribute("image", new User());
        return "users/signature-page";
    }


    //TODO: does this page grab the current logged in user?
    //TODO:  needs to be dynamic between a basic parent and a premium parent
    @GetMapping("/parent-profile")
    public String parentProfile(Model model){
        User loggedIn = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = users.getOne(loggedIn.getId());
        model.addAttribute("user", user);
        return "users/parent-profile";
    }

////    //TODO: does this page grab the current logged in user?
////    //TODO:  needs to be dynamic between a basic parent and a premium parent
////    @GetMapping("/parent-profile/{id}")
////    public String parentProfile(@PathVariable long id,  Model model){
////        User user = users.getOne(id);
////        model.addAttribute("user", user);
////        return "users/parent-profile";
////    }

    @GetMapping("edit-profile")
    public String editProfile(){
        User loggedIn = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = users.getOne(loggedIn.getId());
        return "users/edit-profile";
    }

//    @GetMapping("edit-profile")
//    public String editProfile(Model model){
//        User loggedIn = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User user = users.getOne(loggedIn.getId());
//        model.addAttribute("user", user);
//        return "users/edit-profile";
//    }


    @PostMapping("edit-profile")
    public String update(@RequestParam (name="username") String username, @RequestParam (name="email") String email, @RequestParam (name="newPassword") String newPassword, Model model) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("username", username);
        model.addAttribute("email", email);
        model.addAttribute("newPassword", newPassword);
        long id = loggedInUser.getId();
        users.getOne(id);
        loggedInUser.setUsername(username);
        loggedInUser.setEmail(email);
        String hash = passwordEncoder.encode(loggedInUser.getPassword());
        loggedInUser.setPassword(hash);
        users.save(loggedInUser);
        System.out.println(username);
        return "redirect:/parent-profile";
    }


    @GetMapping("/signature-page")
    public String signatureForm(Model model) {
        model.addAttribute("signatures", new Signatures());
        //Armando: inserted this attribute to be able to find and display comments
        model.addAttribute("comment", comment.findAll());
        //Armando: inserted this attribute to be able to find and display images
        model.addAttribute("image", new User());
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


//child registration
    //TODO:  this page needs to grab the parent id and display it on the page
//TODO: should just return the search form
@GetMapping("/register-child")
public String childRegister() {
//    User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    loggedInUser.getId();
//    System.out.println(loggedInUser.getParent_id())

        return "users/register-child";
}

    @PostMapping("/register-child")
    public String locateByStudentId(@RequestParam long id, Model model) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    loggedInUser.getId();
        System.out.println(id);
        Student s = studentsDao.getByStudent_id(id);
        model.addAttribute("studentId", s.getStudent_id());
        System.out.println(model.addAttribute("studentId", s.getStudent_id()));
        model.addAttribute("firstName", s.getFirst_name());
        model.addAttribute("lastName", s.getLast_name());
        return "users/child-register2";
    }


}


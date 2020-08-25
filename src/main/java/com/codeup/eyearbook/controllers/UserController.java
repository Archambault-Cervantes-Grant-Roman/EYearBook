package com.codeup.eyearbook.controllers;


import com.codeup.eyearbook.models.Signatures;
import com.codeup.eyearbook.models.Student;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;

@Controller
public class UserController {
    private UserRepository userDao;
    private PasswordEncoder passwordEncoder;
    private StudentRepository studentsDao;


    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder, StudentRepository studentsDao) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
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
        user.setIsParent(true);
        userDao.save(user);

        return "redirect:/login";
    }


    //*****************PARENT PROFILE PAGE******************************
    @GetMapping("/parent-profile")
    public String parentProfile(Model model) {

        Authentication token = SecurityContextHolder.getContext().getAuthentication();
        boolean AnonCheck = token instanceof AnonymousAuthenticationToken;
        if (AnonCheck) return "users/login";
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userDao.getOne(loggedInUser.getId());
        model.addAttribute("user", user);

        boolean isParent = loggedInUser.getIsParent();
        return isParent ? "users/parent-profile" : "/home";
//        return "users/parent-profile";
    }


    @PostMapping("/purchase-code")
    public String enteredPurchaseCode(@ModelAttribute User user, @RequestParam(name = "code") String code) {

        Authentication token = SecurityContextHolder.getContext().getAuthentication();
        boolean AnonCheck = token instanceof AnonymousAuthenticationToken;
        if (AnonCheck) return "users/login";
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        long parentId = loggedInUser.getId();
        user = userDao.getOne(parentId);

        code = "DX978J3";

        if (code.equals("DX978J3")) {
            user.setOwns_yearbook(true);

        }

        userDao.save(user);
        boolean isParent = loggedInUser.getIsParent();
        return isParent ? "users/parent-profile" : "/home";
//        return "redirect:/parent-profile";
    }

    @GetMapping("edit-profile")
    public String editProfile(Model model) {

        Authentication token = SecurityContextHolder.getContext().getAuthentication();
        boolean AnonCheck = token instanceof AnonymousAuthenticationToken;
        if (AnonCheck) return "users/login";
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userDao.getOne(loggedInUser.getId());
        model.addAttribute("user", user);

        boolean isParent = loggedInUser.getIsParent();
        return isParent ? "users/edit-profile" : "/home";
//        return "users/edit-profile";
    }



        //this is to change username, email, and password
        @PostMapping("editUser")
        public String updateUserInfo (@ModelAttribute("user") User user){
            User loggedIn = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            long id = loggedIn.getId();
            User existing = userDao.getOne(id);
            existing.setUsername(user.getUsername());
            existing.setEmail(user.getEmail());
            String hash = passwordEncoder.encode(user.getPassword());
            existing.setPassword(hash);
            userDao.save(existing);

            return "redirect:/parent-profile";
        }


        //*****************---END----PARENT PROFILE PAGE******************************

//****************CHILD REGISTRATION PART ONE - STUDENT ID********************

            @GetMapping("/register-child")
            public String childRegister () {
                Authentication token = SecurityContextHolder.getContext().getAuthentication();
                boolean AnonCheck = token instanceof AnonymousAuthenticationToken;
                if (AnonCheck) return "users/login";
                User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

                boolean isParent = loggedInUser.getIsParent();
                return isParent ? "users/register-child" : "/home";
//        return "users/register-child";
            }

//APPLYS THE CHILDS INFO ONTO THE CARD FOR PART 2 OF CHILD REGISTRATION**************
            @PostMapping("/register-child")
            public String locateByStudentId ( @RequestParam long id, Model model){
//        Authentication token = SecurityContextHolder.getContext().getAuthentication();
//        boolean AnonCheck = token instanceof AnonymousAuthenticationToken;
//        if (AnonCheck) return "users/login";
                User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

                loggedInUser.getId();
                Student s = studentsDao.getByStudent_id(id);
                model.addAttribute("studentId", s.getStudent_id());
                model.addAttribute("firstName", s.getFirst_name());
                model.addAttribute("lastName", s.getLast_name());
//        this creates a new user from the student record
                model.addAttribute("user", new User());

//        boolean isParent = loggedInUser.isIsParent();
//        return !isParent  ? "users/child-register2" : "/home";
                return "users/child-register2";
            }


            @PostMapping("/child-register2")
            public String saveChildUser (@ModelAttribute User user){
                String hash = passwordEncoder.encode(user.getPassword());
//        get the parents id
                User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                long parentId = loggedInUser.getId();
                user.setParent_id(parentId);
                user.setPassword(hash);
                userDao.save(user);
                return "redirect:/parent-profile";
            }


        }

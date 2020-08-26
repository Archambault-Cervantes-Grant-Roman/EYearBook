package com.codeup.eyearbook.controllers;

import com.codeup.eyearbook.models.User;
import com.codeup.eyearbook.models.Yearbook;
import com.codeup.eyearbook.repositories.UserRepository;
import com.codeup.eyearbook.repositories.YearbookRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class YearbookController {
    private UserRepository userDao;
    private YearbookRepository yearbook;

    public YearbookController(YearbookRepository yearbook, UserRepository userDao) {
        this.userDao = userDao;
        this.yearbook = yearbook;
    }

    @RequestMapping("/yearbook")
    public String home(){

        Authentication token = SecurityContextHolder.getContext().getAuthentication();
        boolean AnonCheck = token instanceof AnonymousAuthenticationToken;
        if (AnonCheck) return "users/login";

        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long currentUserId = loggedInUser.getId();
        loggedInUser = userDao.getOne(currentUserId);


//        User user = new User();
        boolean yearBookCheck = loggedInUser.isOwns_yearbook();
        // This means the user is not logged in
        return yearBookCheck  ? "users/yearbook" : "home";


    }

//    post mapping for child view yearbook link
//User parent =  userDao.getOne(loggedInUser.getParent_id());
//        boolean parentOwnsYearbook = parent.isOwns_yearbook();
//        System.out.println(loggedInUser.getUsername());
//        System.out.println(parent.getUsername());
//        System.out.println(parentOwnsYearbook);
//         if(parentOwnsYearbook){
//             loggedInUser.setOwns_yearbook(true);
//         }


}


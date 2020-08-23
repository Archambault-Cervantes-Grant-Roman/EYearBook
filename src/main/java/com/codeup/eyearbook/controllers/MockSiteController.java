package com.codeup.eyearbook.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MockSiteController {

    @GetMapping("/mock-site")
    public String mockSite(){
        return "mock-site";
    }

@PostMapping("/mock-site")
    public String purchaseCode(Model model){
        String line1 = "Thank you for your purchase.";
        String line2 = "Your Proof of Purchase Code: DX978J3";
//        String codeMsg = line1 + "<br>" + line2;
        String registerLink = " register this code at E-YearBook.net ";
        model.addAttribute("thankYouMsg", line1);
    model.addAttribute("purchaseCode", line2);
        model.addAttribute("registerLink", registerLink);
        return "mock-site";
}

}

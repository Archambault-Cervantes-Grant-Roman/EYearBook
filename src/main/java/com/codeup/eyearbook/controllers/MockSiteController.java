package com.codeup.eyearbook.controllers;

import org.springframework.stereotype.Controller;
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
@ResponseBody

    public String purchaseCode(){
        return "Your Proof of Purchase Code: DX978J3 ";
}

}

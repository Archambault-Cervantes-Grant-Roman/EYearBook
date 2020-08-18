package com.codeup.eyearbook.controllers;

import com.codeup.eyearbook.models.Signatures;
import com.codeup.eyearbook.models.User;
import com.codeup.eyearbook.repositories.SignatureRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignatureController {
    private SignatureRepository signatures;

    public SignatureController(SignatureRepository signatures) {
        this.signatures = signatures;
    }

    @GetMapping("/signature-page")
    public String signatureForm(Model model) {
        model.addAttribute("sign", new Signatures());
        return "users/signature-page";
    }

    @PostMapping("/signature-page")
    public String saveSignature(@ModelAttribute Signatures signatures) {
//        signatures.save(signatures);
        return "redirect:/login";
    }

}

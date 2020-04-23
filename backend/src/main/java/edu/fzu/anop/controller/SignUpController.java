package edu.fzu.anop.controller;

import edu.fzu.anop.pojo.ValidEmail;
import edu.fzu.anop.resource.UserSignUpResource;
import edu.fzu.anop.resource.ValidEmailResource;
import edu.fzu.anop.service.SignUpService;
import edu.fzu.anop.util.BindingResultUtil;
import edu.fzu.anop.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.validation.Valid;

@RestController
@RequestMapping("v1")
public class SignUpController {

    @Autowired
    SignUpService signUpService;

    @PostMapping("/valid_email")
    public Object validEmail(
            @RequestBody @Valid ValidEmailResource resource,
            BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return JsonResult.unprocessableEntity("error in validating", BindingResultUtil.getErrorList(bindingResult));
        }
        if(signUpService.isSignedUp(resource.getEmail())) {
            return JsonResult.badRequest("Email has been signed up", null);
        }
        try {
            signUpService.sendValidEmail(resource.getEmail());
        } catch (MessagingException e) {
            return JsonResult.internalServerError("Failed to send email", null);
        }
        return JsonResult.noContent().build();
    }

    @PostMapping("/signup")
    public Object signUp(
            @RequestBody @Valid UserSignUpResource resource,
            BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return JsonResult.unprocessableEntity("error in validating", BindingResultUtil.getErrorList(bindingResult));
        }
        ValidEmail validEmail = signUpService.getValidEmail(resource.getEmail());
        if(validEmail == null) {
            return JsonResult.badRequest("Email is not verified", null);
        }
        if( !resource.getCode().equals(validEmail.getCode()) ) {
            return JsonResult.badRequest("Verification code mismatch", null);
        }
        signUpService.signUp(resource);
        return JsonResult.noContent().build();
    }

}

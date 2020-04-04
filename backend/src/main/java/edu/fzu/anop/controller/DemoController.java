package edu.fzu.anop.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class DemoController {

    @GetMapping("/resource")
    public Message home() {
        return new Message("Hello World");
    }

    @RequestMapping(path = "/user", method = {RequestMethod.GET, RequestMethod.POST})
    public Object user() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getPrincipal();
    }

}

class Message {
    private String id = UUID.randomUUID().toString();
    private String content;

    public Message(String content) {
        this.content = content;
    }

    public Message() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

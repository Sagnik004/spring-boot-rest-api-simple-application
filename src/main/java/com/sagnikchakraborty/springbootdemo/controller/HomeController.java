package com.sagnikchakraborty.springbootdemo.controller;

import com.sagnikchakraborty.springbootdemo.model.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "Hello dear world!!";
    }

    @GetMapping("/user")
    public User getUser() {
        User user = new User();
        user.setId("1");
        user.setName("Tinku");
        user.setEmailId("tinku@gmail.com");

        return user;
    }

    @GetMapping("/{id}")
    public String pathVariableExample(@PathVariable String id) {
        return "The path variable is: " + id;
    }

    @GetMapping("/{id}/{id2}")
    public String pathVariableExample2(@PathVariable String id,
                                       @PathVariable("id2") String name) {
        return String.format("The path variables are %s, %s", id, name);
    }

    @GetMapping("/requestParam")
    public String requestParamExample(@RequestParam String name,
                                      @RequestParam(name = "email", required = false, defaultValue = "") String emailId) {
        return String.format("The request params are: %s, %s", name, emailId);
    }
}

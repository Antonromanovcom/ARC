package com.javamaster.controller;

import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@RequestMapping("/restapi/users")
public class MRC {

    @RequestMapping(value = "/whoiam", method = RequestMethod.GET)
    String whoIAm(Principal principal) {

        System.out.println("Мы зашли в ГЕТ");
        String reply = "hello " + principal.getName();
        System.out.println("you are -  " + reply);
        return principal.getName();
    }


}

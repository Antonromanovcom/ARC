package com.javamaster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.slf4j.*;
import java.security.Principal;
import java.util.Date;


@Controller
@RequestMapping("/")
public class HomeController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;



    @RequestMapping(method = RequestMethod.GET)
    public String home() {
        System.out.println("МЫ ВОШЛИ....");
        return "home";
    }



   /* @MessageMapping("/chat")
    @SendTo("/topic/message")
    public OutputMessage sendMessage() {
        //public OutputMessage sendMessage(Message message) {
        logger.info("Message sent");
        return new OutputMessage(new Message("Хуй"), new Date());
    }*/
    @MessageMapping("/chat")
    public void  sendMessage(Principal principal) {

        String reply = "hello " + principal.getName();
        System.out.println("sending " + reply);
        logger.info("Message sent");
        simpMessagingTemplate.convertAndSendToUser(principal.getName(), "/reply", reply);

    }




}
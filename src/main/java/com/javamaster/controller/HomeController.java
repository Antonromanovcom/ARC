package com.javamaster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.slf4j.*;

import java.security.Principal;


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


    @MessageMapping("/chat")
    //public void  sendMessage(Principal principal) throws Exception {
    public void sendMessage(Message<Object> message, @Payload ChatMessage chatMessage) throws Exception {
        Principal principal = message.getHeaders().get(SimpMessageHeaderAccessor.USER_HEADER, Principal.class);
        String authedSender = principal.getName();
        chatMessage.setSender(authedSender);

        System.out.println("sender =  " + authedSender);
        String recipient = chatMessage.getRecipient();

        if (authedSender.equals("bob")) {
            recipient = "jim";
        } else {
            recipient = "bob";
        }

        logger.info("Message for - " + recipient);

        if (!authedSender.equals(recipient)) {
            simpMessagingTemplate.convertAndSendToUser(authedSender, "/reply", chatMessage);
        }

        simpMessagingTemplate.convertAndSendToUser(recipient, "/reply", chatMessage);

        logger.info("Message sent");
    }


}
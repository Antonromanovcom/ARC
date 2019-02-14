package com.javamaster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class HomeController {

/*
    private SimpMessagingTemplate template;
*/

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

/*
    @Autowired
    public HomeController(SimpMessagingTemplate template) {
        this.template = template;
    }

*/


    @RequestMapping(method = RequestMethod.GET)
    public String home() {
        System.out.println("МЫ ВОШЛИ....");
        return "home";
    }

    @MessageMapping("/greeting")
        public String handle (String greeting){
            return "["  + ": " + greeting;
        }


/*
    @MessageMapping("/greeting")
    public void greeting(Principal principal) {
        String reply = "hello " + principal.getName();
        System.out.println("sending " + reply);
        //simpMessagingTemplate.convertAndSend("/user/{username}/reply", reply);
        //template.convertAndSendToUser(principal.getName(), "/reply", reply);
        simpMessagingTemplate.convertAndSendToUser(principal.getName(), "/reply", reply);
        //simpMessagingTemplate.convertAndSendToUser("jim", "/reply", reply);
    }

    */

        //mvn -Djetty.port=8080 jetty:run

  /*  @MessageMapping("/greeting")
    public void greeting(Principal principal) throws Exception {

        //Principal principal = message.getHeaders().get(SimpMessageHeaderAccessor.USER_HEADER, Principal.class);

        String reply = "hello " + principal.getName();
        System.out.println("sending " + reply);

      //  if (!authedSender.equals(recipient)) {
         //  template.convertAndSendToUser(authedSender, "/queue/messages", chatMessage);
      //  }

        simpMessagingTemplate.convertAndSendToUser(principal.getName(), "/reply", reply);


    }
*/



}
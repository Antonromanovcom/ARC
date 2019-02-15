package com.javamaster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.slf4j.*;

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

   /* @MessageMapping("/greeting")
        public String handle (String greeting){
            return "["  + ": " + greeting;
        }*/


    /*@MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public void greeting() throws Exception {
        System.out.println("Мы тут");
        //Thread.sleep(1000); // simulated delay
       // String reply = "hello " + principal.getName();
      //  System.out.println("sending " + reply);
       // return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }*/

    @MessageMapping("/chat")
    @SendTo("/topic/message")
    public OutputMessage sendMessage() {
        //public OutputMessage sendMessage(Message message) {
        logger.info("Message sent");
        return new OutputMessage(new Message("Хуй"), new Date());
    }



    /*@MessageMapping("/greeting")
    public void greeting(Principal principal) {
        String reply = "hello " + principal.getName();
        System.out.println("sending " + reply);
        simpMessagingTemplate.convertAndSend("/user/{username}/reply", reply);
        //template.convertAndSendToUser(principal.getName(), "/reply", reply);
       // simpMessagingTemplate.convertAndSendToUser(principal.getName(), "/reply", reply);
        //simpMessagingTemplate.convertAndSendToUser("jim", "/reply", reply);
    }*/


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
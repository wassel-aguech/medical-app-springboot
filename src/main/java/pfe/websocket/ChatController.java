//package pfe.websocket;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.stereotype.Controller;
//import pfe.entities.Message;
//
//@Controller
//public class ChatController {
//
//
//    @Autowired
//    private SimpMessagingTemplate messagingTemplate;
//
//    @MessageMapping("/chat.send.private")
//    public void sendPrivate(@Payload Message message) {
//        // Exemple : envoyer à /topic/messages.1.2
//        String topic = "/topic/messages." + message.getSenderId() + "." + message.getReceiverId();
//        messagingTemplate.convertAndSend(topic, message);
//    }
//}

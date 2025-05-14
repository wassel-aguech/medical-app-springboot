package pfe.websocket;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import pfe.entities.NotificationType;

@Controller
public class ChatController {

    @Autowired
    private  NotificationService notificationService;


    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/messages")
    public ChatMessage sendMessage(ChatMessage message) {

        // 2. Notification via SSE
        String notifMessage = "💬 Nouveau message de " + message.getSender();
        notificationService.sendNotification(
                message.getReciverId(),
                notifMessage,
                NotificationType.MESSAGE
        );

        return message;


    }

}

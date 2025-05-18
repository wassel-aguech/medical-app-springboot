package pfe.websocket;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import pfe.entities.NotificationType;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private  final NotificationService notificationService;


//    @MessageMapping("/chat.sendMessage")
//    @SendTo("/topic/messages")
//    public ChatMessage sendMessage(ChatMessage message) {
//
//        System.out.println("✅ Message reçu : " + message.getContent());
//
//        // 2. Notification via SSE
//        String notifMessage = "💬 Nouveau message de " + message.getSender();
//        notificationService.sendNotification(
//                message.getReceiverId(),
//                notifMessage,
//                NotificationType.MESSAGE
//        );
//
//
//        System.out.println("📤 Notification envoyée au destinataire ID : " + message.getReceiver());
//
//        return message;
//
//
//    }


    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/messages")
    public ChatMessage sendMessage(ChatMessage message) {
        System.out.println("✅ Message reçu : " + message.getContent());
        System.out.println("🚩 ReceiverId reçu : " + message.getReceiverId());

        String notifMessage = "💬 Nouveau message de " + message.getSender();

        notificationService.sendNotification(
                message.getReceiverId(),
                notifMessage,
                NotificationType.MESSAGE
        );

        System.out.println("📤 Notification envoyée au destinataire ID : " + message.getReceiverId());
        return message;
    }
}

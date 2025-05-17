

package pfe.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import pfe.entities.Notification;
import pfe.entities.NotificationType;
import pfe.repository.NotificationRepository;

import java.util.*;

@Service
public class NotificationService {


    private final Map<Long, List<SseEmitter>> subscribers = new HashMap<>();

    @Autowired
    private NotificationRepository notificationRepository;

    public void subscribe(Long medecinId, SseEmitter emitter) {
        subscribers.computeIfAbsent(medecinId, k -> new ArrayList<>()).add(emitter);
    }


    public void sendNotification(Long destinataireId, String message, NotificationType type) {
        Notification notification = new Notification();
        notification.setDestinataireId(destinataireId);
        notification.setMessage(message);
        notification.setDateEnvoi(new Date());
        notification.setLue(false);
        notification.setType(type);

        notificationRepository.save(notification);

        List<SseEmitter> emitters = subscribers.get(destinataireId);
        if (emitters != null) {
            for (SseEmitter emitter : emitters) {
                try {
                    // Envoie l'objet Notification complet (pas juste le message)
                    emitter.send(notification);

                    System.out.println("✅ Notification envoyée à l'utilisateur " + destinataireId + " : " + message);

                } catch (Exception e) {
                    emitter.completeWithError(e);
                }


            }
        }

     else {
        System.out.println("⚠️ Aucun SseEmitter trouvé pour l'utilisateur " + destinataireId);
    }




    }


    public void markAsRead(Long notificationId) {
        Optional<Notification> notifOpt = notificationRepository.findById(notificationId);
        if (notifOpt.isPresent()) {
            Notification notif = notifOpt.get();
            notif.setLue(true);
            notificationRepository.save(notif);
        }
    }

    public List<Notification> getAllNotificationsByDestinataire(Long destinataireId) {
        return notificationRepository.findAllByDestinataireId(destinataireId);
    }

}
//package pfe.websocket;
//
//import org.springframework.stereotype.Service;
//import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
//
//import java.util.*;
//
//@Service
//public class NotificationService {
//
//    private final Map<Long, List<SseEmitter>> subscribers = new HashMap<>();
//
//    // S'abonner à un médecin
//    public void subscribe(Long medecinId, SseEmitter emitter) {
//        subscribers.computeIfAbsent(medecinId, k -> new ArrayList<>()).add(emitter);
//    }
//
//    // Envoyer une notification à un médecin
//    public void sendNotification(Long medecinId, String message) {
//        List<SseEmitter> emitters = subscribers.get(medecinId);
//        if (emitters != null) {
//            for (SseEmitter emitter : emitters) {
//                try {
//                    emitter.send(message);
//                } catch (Exception e) {
//                    emitter.completeWithError(e);
//                }
//            }
//        }
//    }
//}
//


package pfe.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import pfe.entities.Notification;
import pfe.repository.NotificationRepository;

import java.util.*;

@Service
public class NotificationService {

    private final Map<Long, List<SseEmitter>> subscribers = new HashMap<>();

    @Autowired
    private NotificationRepository notificationRepository;

    // S'abonner à un médecin
    public void subscribe(Long medecinId, SseEmitter emitter) {
        subscribers.computeIfAbsent(medecinId, k -> new ArrayList<>()).add(emitter);
    }

    // Envoyer une notification à un médecin + l'enregistrer
    public void sendNotification(Long medecinId, String message) {
        // Enregistrement en base
        Notification notification = new Notification();
        notification.setDestinataireId(medecinId);
        notification.setMessage(message);
        notification.setDateEnvoi(new Date());
        notification.setLue(false);
        notificationRepository.save(notification);

        // Envoi via SSE
        List<SseEmitter> emitters = subscribers.get(medecinId);
        if (emitters != null) {
            for (SseEmitter emitter : emitters) {
                try {
                    emitter.send(message);
                } catch (Exception e) {
                    emitter.completeWithError(e);
                }
            }
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

    public List<Notification> getAllNotificationsByMedecin(Long medecinId) {
        return notificationRepository.findAllByMedecinId(medecinId);
    }
}


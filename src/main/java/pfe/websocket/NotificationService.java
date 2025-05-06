package pfe.websocket;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.*;

@Service
public class NotificationService {

    private final Map<Long, List<SseEmitter>> subscribers = new HashMap<>();

    // S'abonner à un médecin
    public void subscribe(Long medecinId, SseEmitter emitter) {
        subscribers.computeIfAbsent(medecinId, k -> new ArrayList<>()).add(emitter);
    }

    // Envoyer une notification à un médecin
    public void sendNotification(Long medecinId, String message) {
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
}


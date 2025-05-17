package pfe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import pfe.entities.Notification;
import pfe.websocket.NotificationService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotficationController {

    private final NotificationService notificationService;

    @GetMapping(value = "/notifications/{destinataireId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribeToNotifications(@PathVariable Long destinataireId) {
        SseEmitter emitter = new SseEmitter(0L);

        notificationService.subscribe(destinataireId, emitter);

        emitter.onCompletion(() -> System.out.println("✅ SSE complété pour médecin " + destinataireId));

        emitter.onTimeout(() -> {
            System.out.println("⚠️ Timeout SSE pour médecin " + destinataireId);
            emitter.complete();
        });

        emitter.onError((e) -> {
            System.out.println("💥 Erreur SSE pour médecin " + destinataireId + " : " + e.getMessage());
            emitter.completeWithError(e);
        });

        return emitter;
    }


    @PutMapping("/{id}/read")
    public ResponseEntity<Void> markAsRead(@PathVariable Long id) {
        notificationService.markAsRead(id);
        return ResponseEntity.ok().build();
    }



    @GetMapping("/all/{medecinId}")
    public ResponseEntity<List<Notification>> getAllByMedecin(@PathVariable Long medecinId) {
        List<Notification> list = notificationService.getAllNotificationsByDestinataire(medecinId);
        return ResponseEntity.ok(list);
    }
}

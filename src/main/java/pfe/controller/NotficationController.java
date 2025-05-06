package pfe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import pfe.websocket.NotificationService;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotficationController {

    private final NotificationService notificationService;

    @GetMapping(value = "/notifications/{medecinId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribeToNotifications(@PathVariable Long medecinId) {
        // ⏱️ Timeout désactivé (0L signifie infini)
        SseEmitter emitter = new SseEmitter(0L);

        // 🔔 S'abonner
        notificationService.subscribe(medecinId, emitter);

        // 👂 Log en cas de fermeture normale
        emitter.onCompletion(() -> System.out.println("✅ SSE complété pour médecin " + medecinId));

        // ❌ Log en cas de timeout (inutile ici vu qu'on a mis 0L, mais utile si tu remets un timeout plus tard)
        emitter.onTimeout(() -> {
            System.out.println("⚠️ Timeout SSE pour médecin " + medecinId);
            emitter.complete();
        });

        // 🛑 Log en cas d'erreur
        emitter.onError((e) -> {
            System.out.println("💥 Erreur SSE pour médecin " + medecinId + " : " + e.getMessage());
            emitter.completeWithError(e);
        });

        return emitter;
    }
}

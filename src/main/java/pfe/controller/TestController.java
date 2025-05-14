//package pfe.controller;
//
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import pfe.websocket.NotificationService;
//
//@RestController
//@RequestMapping("/api/v1/test")
//public class TestController {
//
//
//
//    private final NotificationService notificationService;
//
//    public TestController(NotificationService notificationService) {
//        this.notificationService = notificationService;
//    }
//
//    @GetMapping("/envoyer/{medecinId}")
//    public String envoyerNotification(@PathVariable Long medecinId) {
//        notificationService.sendNotification(medecinId, "Nouvelle demande de rendez-vous !");
//        return "Notification envoyée";
//    }
//
//}

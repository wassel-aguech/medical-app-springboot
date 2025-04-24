//package pfe.websocket;
//
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.simp.config.MessageBrokerRegistry;
//import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
//import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
//import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
//
//@Configuration
//@EnableWebSocketMessageBroker
//public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
//
//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        // Endpoint pour les connexions WebSocket
//        registry.addEndpoint("/chat-websocket").setAllowedOriginPatterns("*").withSockJS();
//    }
//
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry config) {
//        // Pour envoyer des messages aux clients abonnés
//        config.enableSimpleBroker("/topic");
//
//        // Pour recevoir les messages du frontend
//        config.setApplicationDestinationPrefixes("/app");
//    }
//}

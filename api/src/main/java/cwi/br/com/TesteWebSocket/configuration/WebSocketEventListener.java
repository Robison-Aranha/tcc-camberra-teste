package cwi.br.com.TesteWebSocket.configuration;

import cwi.br.com.TesteWebSocket.controller.model.ConnectDisconnect;
import cwi.br.com.TesteWebSocket.controller.model.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectEvent event){
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String username = (String) headerAccessor.getSessionAttributes().get("username");
        String key = (String) headerAccessor.getSessionAttributes().get("key");

        ConnectDisconnect connection = new ConnectDisconnect();

        connection.setSerderName(username);
        connection.setStatus(Status.JOIN);
        connection.setKey(key);

        simpMessagingTemplate.convertAndSend("/room/" + key + "/perfils", connection);
    }

    //method called when user close page in browser
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String username = (String) headerAccessor.getSessionAttributes().get("username");
        String key = (String) headerAccessor.getSessionAttributes().get("key");

        ConnectDisconnect connection = new ConnectDisconnect();

        connection.setSerderName(username);
        connection.setStatus(Status.JOIN);
        connection.setKey(key);

        simpMessagingTemplate.convertAndSend("/room/" + key + "/perfils", connection);
        simpMessagingTemplate.convertAndSend("/room/" + key, connection);
    }
}


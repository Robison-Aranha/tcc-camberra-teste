package cwi.br.com.TesteWebSocket.controller;

import cwi.br.com.TesteWebSocket.configuration.WebSocketEventListener;
import cwi.br.com.TesteWebSocket.controller.model.*;
import cwi.br.com.TesteWebSocket.controller.response.ResponseHash;
import cwi.br.com.TesteWebSocket.services.GeradorDeIndentificarDeSala;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;


@RestController
public class ChatController {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private GeradorDeIndentificarDeSala geradorDeIndentificarDeSala;

    @MessageMapping("/room")
    public Message receberMesagemChatroom(@Payload Message message){

        simpMessagingTemplate.convertAndSend("/room/" + message.getKey(), message); // /room/codigo-sala
        return message;
    }

    @MessageMapping("/room/scoreboard")
    public Verification receberVerificationScoreBoard(@Payload Verification verification){
        simpMessagingTemplate.convertAndSend("/room/" + verification.getKey() + "/scoreboard", verification);
        return verification;
    }

    @MessageMapping("/room/validation")
    public Validation receberValidations(@Payload Validation validation){
        simpMessagingTemplate.convertAndSend("/room/" + validation.getKey() + "/validation", validation);
        return validation;
    }

    @MessageMapping("/room/content")
    public Content receberContentSala(@Payload Content content){
        simpMessagingTemplate.convertAndSend("/room/" + content.getKey() + "/content", content);
        return content;
    }

    @MessageMapping("/room/verification")
    public Verification receberVerificationSala(@Payload Verification verification){
        simpMessagingTemplate.convertAndSend("/room/" + verification.getKey() + "/verification", verification);
        return verification;
    }


    @MessageMapping("/room/start")
    public Verification receberStartRoom(@Payload Verification start) {
        simpMessagingTemplate.convertAndSend("/room/" + start.getKey() + "/stop", start);
        return start;
    }

    @MessageMapping("/room/perfil")
    public Perfil receberPerfilsSala(@Payload Perfil perfil, SimpMessageHeaderAccessor headerAccessor){
        headerAccessor.getSessionAttributes().put("username", perfil.getSenderName());
        headerAccessor.getSessionAttributes().put("admin", perfil.getAdmin());
        headerAccessor.getSessionAttributes().put("key", perfil.getKey());

        simpMessagingTemplate.convertAndSend("/room/" + perfil.getAdmin() + "/perfil", perfil);
        return perfil;

    }

    @MessageMapping("/room/perfils")
    public ListaPerfils receberPerfilsSala(@Payload ListaPerfils listaPerfil){

        simpMessagingTemplate.convertAndSend("/room/" + listaPerfil.getKey() + "/perfils", listaPerfil);
        return listaPerfil;
    }


    @GetMapping("/hash")
    public ResponseHash gerarHash(){
        return geradorDeIndentificarDeSala.gerar();
    }
}
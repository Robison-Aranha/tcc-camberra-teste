package cwi.br.com.TesteWebSocket.controller;

import cwi.br.com.TesteWebSocket.controller.model.Content;
import cwi.br.com.TesteWebSocket.controller.model.Message;
import cwi.br.com.TesteWebSocket.controller.response.ResponseHash;
import cwi.br.com.TesteWebSocket.services.GeradorDeIndentificarDeSala;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private GeradorDeIndentificarDeSala geradorDeIndentificarDeSala;

    @MessageMapping("/room")
    public Message receberMesagemChatroom(@Payload Message message){
        simpMessagingTemplate.convertAndSend("/room/" + message.getKey(), message); // /room/codigo-sala
        return message;
    }

    @MessageMapping("/room/content")
    public Content receberContentChatroom(@Payload Content content){
        simpMessagingTemplate.convertAndSend("/room/" + content.getKey() + "/content", content);
        return content;
    }

    @GetMapping("/hash")
    public ResponseHash gerarHash(){
        return geradorDeIndentificarDeSala.gerar();
    }
}
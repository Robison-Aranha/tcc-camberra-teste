package cwi.br.com.TesteWebSocket.controller.model;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Perfil {

    private String senderName;
    private String photo;
    private String admin;
    private String key;
    private int points;
}

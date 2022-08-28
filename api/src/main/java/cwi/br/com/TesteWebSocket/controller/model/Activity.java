package cwi.br.com.TesteWebSocket.controller.model;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Activity {

    private String usuario;
    private Status status;

}

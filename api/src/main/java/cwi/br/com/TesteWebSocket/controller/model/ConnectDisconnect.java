package cwi.br.com.TesteWebSocket.controller.model;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ConnectDisconnect {
    private String serderName;
    private String key;
    private Status status;
}


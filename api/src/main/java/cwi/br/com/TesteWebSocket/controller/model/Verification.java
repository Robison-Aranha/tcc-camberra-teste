package cwi.br.com.TesteWebSocket.controller.model;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Verification {

    private String key;
    private boolean verification;
}

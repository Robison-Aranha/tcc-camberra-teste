package cwi.br.com.TesteWebSocket.controller.model;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Validation {

    private String senderName;
    private String key;
    private String inputs;
    private String uncheckeds;
}

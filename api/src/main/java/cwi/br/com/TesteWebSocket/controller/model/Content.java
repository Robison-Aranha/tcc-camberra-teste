package cwi.br.com.TesteWebSocket.controller.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Content {
    private String senderName;
    private String key;
    private Content content;
}

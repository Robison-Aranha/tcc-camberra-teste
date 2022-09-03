package cwi.br.com.TesteWebSocket.controller.model;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TimerCount {
    private String key;
    private Integer count;
}

package cwi.br.com.TesteWebSocket.controller.model;


import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ListContents {

    private String key;
    private List<Content> contents;

}

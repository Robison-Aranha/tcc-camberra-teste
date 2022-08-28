package cwi.br.com.TesteWebSocket.controller.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ListaPerfils {

    private String key;
    private List<Perfil> perfils;
}

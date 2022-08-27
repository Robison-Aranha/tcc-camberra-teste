package cwi.br.com.TesteWebSocket.services;

import cwi.br.com.TesteWebSocket.controller.response.ResponseHash;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class GeradorDeIndentificarDeSala {

    private final static int NUMERO_DE_CASAS = 5;

    public ResponseHash gerar() {

        Random random = new Random();

        List<Integer> hash = new ArrayList<>();

        for (int i = 0; i < NUMERO_DE_CASAS; i++){
            hash.add(random.nextInt(10));
        }

        StringBuilder hashConvertido = new StringBuilder();
        for (int i : hash) hashConvertido.append(i);


        ResponseHash response = new ResponseHash();
        response.setHash(hashConvertido.toString());

        return response;
    }
}

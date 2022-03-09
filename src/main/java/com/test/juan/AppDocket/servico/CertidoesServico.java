package com.test.juan.AppDocket.servico;

import com.test.juan.AppDocket.models.Certidao;
import com.test.juan.AppDocket.repositorys.CeritdaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CertidoesServico {

    @Autowired(required = false)
    private CeritdaoRepository repository;


    @Scheduled(fixedRate = 1000)
    public void atualizarDados() {

        try {
            RestTemplate rest = new RestTemplate();
            ResponseEntity<Certidao[]> response =
                    rest.getForEntity(
                            "https://docketdesafiobackend.herokuapp.com/api/v1/certidoes",
                            Certidao[].class);
            Certidao[] certidaoList = response.getBody();


            List<Certidao> certidaoListSalvas = repository.findAll();

            for (Certidao certidao : certidaoList) {
                if (!certidaoListSalvas.contains(certidao)) {
                    repository.save(certidao);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
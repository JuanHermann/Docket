package com.test.juan.AppDocket.services;

import com.test.juan.AppDocket.models.Certidao;
import com.test.juan.AppDocket.repositorys.CeritdaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CertidaoService {


    private final CeritdaoRepository repository;

    public List<Certidao> listAll() {
        return repository.findAll();
    }

    @Scheduled(cron = "0 0/5 * 1/1 * ?")
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
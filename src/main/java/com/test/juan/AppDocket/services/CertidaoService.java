package com.test.juan.AppDocket.services;

import com.test.juan.AppDocket.models.Certidao;
import com.test.juan.AppDocket.repositorys.CeritdaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CertidaoService {


    private final CeritdaoRepository repository;

    public List<Certidao> listAll() {
        return repository.findAll();
    }


}
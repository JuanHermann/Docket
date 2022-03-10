package com.test.juan.AppDocket.services;

import com.test.juan.AppDocket.models.Cartorio;
import com.test.juan.AppDocket.repositorys.CartorioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CartorioService {


    private final CartorioRepository repository;

    public List<Cartorio> listAll() {
        return repository.findAll();
    }

    public ResponseEntity findById(Long id) {
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(cartorioNaoEncontrado(id));

    }

    public ResponseEntity create(Cartorio cartorio) {
        cartorio.setId(null);
        return ResponseEntity.created(URI.create("")).body(repository.save(cartorio));
    }


    public ResponseEntity update(Long id, Cartorio cartorio) {
        if (verificarCartorio(id)) {
            cartorio.setId(id);
            repository.save(cartorio);

            return ResponseEntity.ok().body("Cartório alterado com sucesso.");
        }
        return cartorioNaoEncontrado(id);

    }

    public ResponseEntity delete(Long id) {
        if (verificarCartorio(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok().body("Cartório excluido com sucesso.");
        }

        return cartorioNaoEncontrado(id);
    }


    public boolean verificarCartorio(Long id) {
        return repository.findById(id).isPresent();
    }

    public ResponseEntity cartorioNaoEncontrado(Long id) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Cartório com o ID %s não encontrado!", id));
    }
}
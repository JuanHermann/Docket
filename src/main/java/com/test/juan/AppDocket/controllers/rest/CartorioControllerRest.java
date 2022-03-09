package com.test.juan.AppDocket.controllers.rest;

import com.test.juan.AppDocket.models.Cartorio;
import com.test.juan.AppDocket.services.CartorioService;
import com.test.juan.AppDocket.services.CertidaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cartorio")
public class CartorioControllerRest {

    @Autowired(required = false)
    private CartorioService service;

    @Autowired(required = false)
    private CertidaoService certidaoService;

    @GetMapping
    public List<Cartorio> findAll() {
        return service.listAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Cartorio cartorio) {
        if (certidoesExistem(cartorio)) {
            return service.create(cartorio);
        }
        return certidoesNotFoundMessage();
    }


    @PutMapping(path = {"/{id}"})
    public ResponseEntity update(@PathVariable Long id, @RequestBody Cartorio cartorio) {
        if (certidoesExistem(cartorio)) {
            return service.update(id, cartorio);
        }
        return certidoesNotFoundMessage();

    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    private boolean certidoesExistem(Cartorio cartorio) {

        return certidaoService.listAll().containsAll(cartorio.getCertidao());
    }

    private ResponseEntity<String> certidoesNotFoundMessage() {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Certidões não cadastradas no sistema!");
    }
}

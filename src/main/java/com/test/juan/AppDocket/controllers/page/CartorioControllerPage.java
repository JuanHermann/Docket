package com.test.juan.AppDocket.controllers.page;

import com.test.juan.AppDocket.models.Cartorio;
import com.test.juan.AppDocket.repositorys.CartorioRepository;
import com.test.juan.AppDocket.repositorys.CeritdaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CartorioControllerPage {

    @Autowired(required = false)
    private CartorioRepository repository;

    @Autowired(required = false)
    private CeritdaoRepository ceritdaoRepository;

    @GetMapping("/")
    public String home(ModelMap model) {

        model.addAttribute("cartorios", repository.findAll());

        return "home";
    }

    @GetMapping("/insert-update")
    public String showForm(ModelMap model) {
        model.addAttribute("cartorio", new Cartorio());
        model.addAttribute("certidoes", ceritdaoRepository.findAll());

        return "insert-update";
    }

    @PostMapping("/insert-update")
    public String cadastro(@ModelAttribute Cartorio cartorio, ModelMap model) {
        repository.save(cartorio);
        return "redirect:/";
    }


    @GetMapping("/insert-update/{id}")
    public String update(@PathVariable("id") long id, ModelMap model) {
        Cartorio cartorio = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("id cartorio invalido:" + id));

        model.addAttribute("cartorio", cartorio);
        model.addAttribute("certidoes", ceritdaoRepository.findAll());
        return "insert-update";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        Cartorio cartorio = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("id cartorio invalido:" + id));
        repository.delete(cartorio);
        return "redirect:/";
    }
}

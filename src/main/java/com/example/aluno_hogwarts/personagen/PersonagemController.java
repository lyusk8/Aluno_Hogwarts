package com.example.aluno_hogwarts.personagen;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController
@RequestMapping("/personagens")
public class PersonagemController {

    private PersonagemService personagemService;

    @GetMapping("/{nome}")
    @ResponseStatus(HttpStatus.OK)
    public Single<PersonagemResponse> buscarPorNome(@PathVariable String nome){
        return personagemService.buscarPorNome(nome);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Observable<PersonagemResponse> buscarTodos(){
        return personagemService.findAll();
    }

    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public Single<PersonagemResponse> criarPerson(@RequestBody PersonagemRequest person){
       return personagemService.salvarPersonagem(person);
    }
}

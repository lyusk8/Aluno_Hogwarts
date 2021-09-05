package com.example.aluno_hogwarts.personagen;

import com.example.aluno_hogwarts.casa.Casa;
import lombok.Data;

@Data
public class PersonagemResponse {

    private String id;
    private String nome;
    private Casa casa;

    public PersonagemResponse (Personagem personagem, Casa casa){
        this.id = personagem.getId();
        this.nome = personagem.getNome();
        this.casa = casa;
    }

}

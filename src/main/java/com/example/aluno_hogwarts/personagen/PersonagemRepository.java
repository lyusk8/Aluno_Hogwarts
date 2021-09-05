package com.example.aluno_hogwarts.personagen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonagemRepository extends JpaRepository<Personagem, String> {

    Personagem findByNome(String nome);
}

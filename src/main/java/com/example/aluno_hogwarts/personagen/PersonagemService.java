package com.example.aluno_hogwarts.personagen;

import com.example.aluno_hogwarts.casa.Casa;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Objects;

@AllArgsConstructor
@Service
public class PersonagemService {

    private final PersonagemRepository personagemRepository;
    private WebClient webClient;

    public Casa obterCasa(String id){
        Mono<Casa> mono = this.webClient
                .method(HttpMethod.GET)
                .uri("house/{id}", id)
                .retrieve()
                .bodyToMono(Casa.class);
        //Casa casa = mono.block();
        return mono.block();
    }

    public String obterId() {
        String id = "sortinghat";
        Mono<String> mono = this.webClient
                .method(HttpMethod.GET)
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(String.class);
        String casa = mono.block();

        return Objects.requireNonNull(casa).substring(23, 59);

    }

    public Single<PersonagemResponse> salvarPersonagem(PersonagemRequest personagemRequest){
        return Single.create(single -> {
            Personagem personagem = new Personagem();
            personagem.setNome(personagemRequest.getNome());
            personagem.setIdHouse(obterId());
            personagemRepository.save(personagem);
            single.onSuccess(new PersonagemResponse(personagem, obterCasa(personagem.getIdHouse())));
        });
    }

    public Single<PersonagemResponse> buscarPorNome(String nome){
        return Single.create(single -> {
            Personagem personagem = personagemRepository.findByNome(nome);
            single.onSuccess(new PersonagemResponse(personagem, obterCasa(personagem.getIdHouse())));
        });
    }

    public Observable<PersonagemResponse> findAll(){
        return Observable.fromIterable(personagemRepository.findAll())
                .map(this::convert);
    }

    public PersonagemResponse convert(Personagem personagem){
        Casa casa = obterCasa(personagem.getIdHouse());
        return new PersonagemResponse(personagem, casa);
    }

}

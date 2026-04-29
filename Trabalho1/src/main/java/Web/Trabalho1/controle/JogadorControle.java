package Web.Trabalho1.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import Web.Trabalho1.repositorio.JogadorRepositorio;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.List;
import Web.Trabalho1.modelo.Jogador;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
public class JogadorControle {
    @Autowired
    private JogadorRepositorio rep;
    
    @GetMapping("/Jogadores")
    public ResponseEntity<List<Jogador>> getAllJogadores(@RequestParam(required = false) String nome){
        try {
            List<Jogador> lj = new ArrayList<Jogador>();
            
            if(nome == null)//retorna todos os jogadores
                rep.findAll().forEach(lj::add);
            else if(nome != null)//retorna os jogadores que contém o nome
                rep.findByNomeContainingIgnoreCase(nome).forEach(lj::add);
            if(lj.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else{
                return new ResponseEntity<>(lj,HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/Jogadores/email:{email}")
    public ResponseEntity<List<Jogador>> getAllJogadoresByEmail(@PathVariable("email") String email){
        try {
            List<Jogador> lj = new ArrayList<Jogador>();
                rep.findByEmailContainingIgnoreCase(email).forEach(lj::add);
            if(lj.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else{
                return new ResponseEntity<>(lj,HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/Jogadores/{cod_jogador}")
    public ResponseEntity<Jogador> getJogador(@PathVariable("cod_jogador") int cod_jogador) {
        try {
            Jogador jogador = rep.findByCodJogador(cod_jogador);
            if (jogador == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(jogador, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

@DeleteMapping("/Jogadores/{cod_jogador}")
    public ResponseEntity<HttpStatus> deleteJogador(@PathVariable("cod_jogador") int cod_jogador) {
        try {
            if(rep.findByCodJogador(cod_jogador) == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            rep.deleteById(cod_jogador);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/Jogadores")
    public ResponseEntity<Jogador> createJogador(@RequestBody Jogador jogador) {
        try {
            Jogador _jogador = rep.save(jogador);
            return new ResponseEntity<>(_jogador, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/Jogadores/{cod_jogador}")
    public ResponseEntity<Jogador> updateJogador(@PathVariable("cod_jogador") int cod_jogador, @RequestBody Jogador jogador) {
        try {
            Jogador _jogador = rep.findByCodJogador(cod_jogador);
            if (_jogador != null) {
                _jogador.setNome(jogador.getNome());
                _jogador.setDataNascimento(jogador.getDataNascimento());
                _jogador.setEmail(jogador.getEmail());
                return new ResponseEntity<>(rep.save(_jogador), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
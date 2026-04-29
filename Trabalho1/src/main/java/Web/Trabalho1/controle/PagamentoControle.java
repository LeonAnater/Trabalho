package Web.Trabalho1.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import Web.Trabalho1.repositorio.PagamentoRepositorio;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.List;
import Web.Trabalho1.modelo.Pagamento;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
public class PagamentoControle {
    @Autowired
    private PagamentoRepositorio rep;

    @GetMapping("/Pagamentos")
    public ResponseEntity<List<Pagamento>> getAllPagamentosByJogador(@RequestParam(required = false) String nome){
        try {
            List<Pagamento> lp = new ArrayList<Pagamento>();
            
            if(nome == null)//retorna todos os pagamentos
                rep.findAll().forEach(lp::add);
            else if(nome != null)//retorna os pagamentos que contém o nome do jogador
                rep.findByJogadorContainingIgnoreCase(nome).forEach(lp::add);
            if(lp.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else{
                return new ResponseEntity<>(lp,HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/Pagamentos/ano/{ano}")
    public ResponseEntity<List<Pagamento>> getAllPagamentosByAno(@PathVariable short ano) {
        try {
            List<Pagamento> lp = rep.findByAno(ano);
            if (lp.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(lp, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/Pagamentos/{mes}/{ano}")
    public ResponseEntity<List<Pagamento>> getAllPagamentosByMesEAno(@PathVariable byte mes, @PathVariable short ano) {
        try {
            List<Pagamento> lp = rep.findByMesAndAno(mes, ano);
            if (lp.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(lp, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/Pagamentos/mes/{mes}")
    public ResponseEntity<List<Pagamento>> getAllPagamentosByMes(@PathVariable byte mes) {
        try {
            List<Pagamento> lp = rep.findByMes(mes);
            if (lp.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(lp, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/Pagamentos/{cod_pagamento}")
    public ResponseEntity<Pagamento> getPagamento(@PathVariable("cod_pagamento") int cod_pagamento) {
        try {
            List<Pagamento> lp = rep.findByCodPagamento(cod_pagamento);
            if (lp.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(lp.get(0), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/Pagamentos/{cod_pagamento}")
    public ResponseEntity<HttpStatus> deletePagamento(@PathVariable("cod_pagamento") int cod_pagamento) {
        try {
            rep.deleteById(cod_pagamento);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/Pagamentos")
    public ResponseEntity<Pagamento> createPagamento(@RequestBody Pagamento pagamento) {
        try {
            Pagamento _pagamento = rep.save(new Pagamento(pagamento.getAno(), pagamento.getMes(), pagamento.getValor(), pagamento.getJogador()));
            return new ResponseEntity<>(_pagamento, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/Pagamentos/{cod_pagamento}")
    public ResponseEntity<Pagamento> updatePagamento(@PathVariable("cod_pagamento") int cod_pagamento, @RequestBody Pagamento pagamento) {
        try {
            List<Pagamento> lp = rep.findByCodPagamento(cod_pagamento);
            if (lp.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                Pagamento _pagamento = lp.get(0);
                _pagamento.setAno(pagamento.getAno());
                _pagamento.setMes(pagamento.getMes());
                _pagamento.setValor(pagamento.getValor());
                _pagamento.setJogador(pagamento.getJogador());
                return new ResponseEntity<>(rep.save(_pagamento), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }
}

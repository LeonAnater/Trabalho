package Web.Trabalho1.repositorio;
import org.springframework.data.jpa.repository.JpaRepository;
import Web.Trabalho1.modelo.Pagamento;
import java.util.List;

public interface PagamentoRepositorio extends JpaRepository<Pagamento, Integer> {
List<Pagamento> findByJogadorContainingIgnoreCase(String nome);
List<Pagamento> findByAno(short ano);
List<Pagamento> findByMes(byte mes);
List<Pagamento> findByMesAndAno(byte mes, short ano);

List<Pagamento> findByCodPagamento(Integer cod_pagamento);

} 

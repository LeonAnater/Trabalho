package Web.Trabalho1.repositorio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import Web.Trabalho1.modelo.Jogador;

public interface JogadorRepositorio extends JpaRepository<Jogador, Integer> {
List<Jogador> findByNomeContainingIgnoreCase(String nome);
List<Jogador> findByEmailContainingIgnoreCase(String email);
Jogador findByCodJogador(int cod_jogador);
} 
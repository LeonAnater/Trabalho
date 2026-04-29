package Web.Trabalho1.modelo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate; 
@Entity
@Table(name="jogador")
public class Jogador {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codJogador;

    @Column(nullable=false,length=100)
    private String nome;

    @Column(nullable=false,length=100)
    private LocalDate dataNascimento;

    @Column(nullable=false,length=100)
    private String email;

    
    @OneToMany(mappedBy = "jogador")
    @JsonIgnore
    private List<Pagamento> pagamentos;

    public Jogador(){}

    public Jogador(String nome, LocalDate dataNascimento, String email, List<Pagamento> pagamentos){
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.pagamentos = pagamentos;
    }
    public Jogador(String nome, LocalDate dataNascimento, String email){
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.email = email;
    }
    public Jogador(String nome, String email,int dia, int mes, int ano){
        this.nome = nome;
        this.email = email;
        this.dataNascimento = LocalDate.of(ano, mes, dia);
    }
    public int getCodJogador() {
        return codJogador;
    }
    public String getNome() {
        return nome;
    }
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    public String getEmail() {
        return email;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

}

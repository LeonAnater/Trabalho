package Web.Trabalho1.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import java.math.BigDecimal;

@Entity
@Table(name="pagamento")
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codPagamento;

    @Column(nullable=false,length=100)
    private short ano;

    @Column(nullable=false,length=100)
    private byte mes;

    @Column(nullable=false,length=100)
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name="cod_jogador", nullable=true)
    private Jogador jogador;

    public Pagamento(){}
    public Pagamento(short ano, byte mes, BigDecimal valor, Jogador jogador){
        this.ano = ano;
        this.mes = mes;
        this.valor = valor;
        this.jogador = jogador;
    }
    public int getCodPagamento() {
        return codPagamento;
    }
    public short getAno() {
        return ano;
    }
    public byte getMes() {
        return mes;
    }
    public BigDecimal getValor() {
        return valor;
    }
    public Jogador getJogador() {
        return jogador;
    }
    public void setAno(short ano) {
        this.ano = ano;
    }
    public void setMes(byte mes) {
        this.mes = mes;
    }
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }
}

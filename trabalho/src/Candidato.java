import java.time.*;

public class Candidato {
    String nomeCompleto;
    String nomeUrna;
    LocalDate dataNascimento;
    int votosNominais; // inicializa em 0 e vai aumentando
    int eleito; // 2 ou 3 se eleito (candidaturas invalidas sao barradas antes daqui)
    Partido partido;
    int codigoCandidato;
    int genero; // 2 masculino e 4 feminino
    int federacao; // -1 se for partido isolado

    public Candidato(String nomeCompleto,
    String nomeUrna,
    LocalDate dataNascimento,
    int eleito,
    Partido partido,
    int codigoCandidato,
    int genero,
            int federacao) {
        this.nomeCompleto = nomeCompleto;
        this.nomeUrna = nomeUrna;
        this.dataNascimento = dataNascimento;
        this.votosNominais = 0;
        this.eleito = eleito;
        this.partido = partido;
        this.codigoCandidato = codigoCandidato;
        this.genero = genero;
        this.federacao = federacao;
    }
    
    // tambem eh o unico responsavel por atualizar os votos nominais de seu partido
    public void aumentaVotosNominais(int quantidadeVotosNominais) {
        votosNominais += quantidadeVotosNominais;

        // atualiza votos do partido
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getNomeUrna() {
        return nomeUrna;
    }

    public int getIdade(LocalDate dataEleicao) {
        int idade;
        
        Period periodo = Period.between(dataNascimento, dataEleicao);
        idade = periodo.getYears();

        return idade;
    }

    public int getVotosNominais() {
        return votosNominais;
    }

    // retorna 1 se estiver eleito, 0 se nao
    public int estaEleito() {
        
        if (eleito > 0) {
            return 1;
        }
        else {
            return 0;
        }
    }

    public Partido getPartido() {
        return partido;
    }

    public int getCodigoCandidato() {
        return codigoCandidato;
    }

    public int getGenero() {
        return genero;
    }

    // retorna 1 se fizer parte de federacao, 0 se nao
    public int participaFederacao() {
        if (federacao > 0) {
            return 1;
        }
        else {
            return 0;
        }
    }
}
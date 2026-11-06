import java.time.LocalDate;
import java.util.*;

public class Eleicao {
    private HashMap<Integer, Candidato> candidatos = new HashMap<>();
    private HashMap<Integer, Partido> partidos = new HashMap<>();
    private int codigoMunicipio;
    private LocalDate dataEleicao;

    public Eleicao(int codigoMunicipio, LocalDate dataEleicao) {
        this.codigoMunicipio = codigoMunicipio;
        this.dataEleicao = dataEleicao;
    }

    public Partido retornaPartido(int codigoPartido, String nomePartido) {
        if (partidos.containsKey(codigoPartido)) {
            return partidos.get(codigoPartido);
        }

        Partido partido = new Partido(nomePartido, codigoPartido);
        partidos.put(codigoPartido, partido);
        return partido;
    }

    public void cadastraCandidato(String nomeUrna,
            LocalDate dataNascimento, int eleito, Partido partido,
            int codigoCandidato, int genero, int federacao) {

        if (candidatos.containsKey(codigoCandidato)) {
            System.err.println("Erro: Existe um candidato com mesmo código já cadastrado nessa eleição.");
            return;
        }

        Candidato candidato = new Candidato(nomeUrna,
                dataNascimento, eleito, partido, codigoCandidato, genero, federacao);

        partido.adicionaCandidato(candidato);
        candidatos.put(codigoCandidato, candidato);
    }

    public void adicionaVotos(int numeroVotavel, String nomePartido, int quantidadeVotos) {
        if (numeroVotavel >= 95 && numeroVotavel <= 98) {
            return;
        }

        if (numeroVotavel < 100) {
            Partido partido = retornaPartido(numeroVotavel, nomePartido);
            partido.aumentaVotosLegenda(quantidadeVotos);
        } else {
            Candidato candidato = candidatos.get(numeroVotavel);

            if (candidato != null) {
                candidato.aumentaVotosNominais(quantidadeVotos);
            }
        }
    }

    public int getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public LocalDate getDataEleicao() {
        return dataEleicao;
    }

    public ArrayList<Candidato> ordenaCandidatosGeral() {
        ArrayList<Candidato> candidatosOrdenados = new ArrayList<>(candidatos.values());
        Compare comparador = new Compare();

        candidatosOrdenados.sort(comparador);

        return candidatosOrdenados;
    }

    public ArrayList<Candidato> getCandidatos() {
        return new ArrayList<>(candidatos.values());
    }

     public ArrayList<Partido> getPartidos() {
        return new ArrayList<>(partidos.values());
    }

    public int getTotalCandidatosEleitos() {
        int qtdEleitos = 0;
        Collection<Partido> colecaoPartidos = partidos.values();

        for (Partido partido : colecaoPartidos) {
            qtdEleitos += partido.getTotalCandidatosEleitos();
        }

        return qtdEleitos;
    }
}

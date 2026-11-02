import java.util.HashMap;
import java.util.ArrayList;
import java.util.*;

public class Partido {
    private HashMap<Integer, Candidato> candidatos = new HashMap<>();
    private String nomePartido;
    private int codigoPartido;
    private int votosLegenda; // inicializado com 0
    private int votosNominais; // inicializado com 0 e acrescido somente pelo candidato

    public Partido(String nomePartido,
            int codigoPartido) {
        this.nomePartido = nomePartido;
        this.codigoPartido = codigoPartido;
        votosLegenda = 0;
        votosNominais = 0;
    }

    public void adicionaCandidato(Candidato candidato) {
        int codigoCandidato = candidato.getCodigoCandidato();

        if (candidatos.containsKey(codigoCandidato)) {
            System.out.printf("\nErro: Existe um candidato com mesmo código já cadastrado nesse partido.\n");
        } else {
            candidatos.put(codigoCandidato, candidato);
        }
    }

    public void aumentaVotosNominais(int quantidadeVotosNominais) {
        votosNominais += quantidadeVotosNominais;
    }

    public void aumentaVotosLegenda(int quantidadeVotosLegenda) {
        votosLegenda += quantidadeVotosLegenda;
    }

    public ArrayList<Candidato> ordenaCandidatos() {
        ArrayList<Candidato> candidatosOrdenados = new ArrayList<>(candidatos.values());
        Compare comparador = new Compare();

        candidatosOrdenados.sort(comparador);

        return candidatosOrdenados;
    }

    public Candidato getUltimoCandidato() {
        ArrayList<Candidato> candidatosOrdenados = ordenaCandidatos();
        int indice = candidatosOrdenados.size() - 1;
        int quantidadeVotos = 0;

        while (indice >= 0) {
            Candidato candidato = candidatosOrdenados.get(indice);
            quantidadeVotos = candidato.getVotosNominais();
            if (quantidadeVotos > 0) {
                return candidato;
            }
            indice -= 1;
        }

        System.out.printf("\nNão existe um candidato válido nesse partido.\n");
        return null;

    }

    public Candidato getPrimeiroCandidato() {
        ArrayList<Candidato> candidatosOrdenados = ordenaCandidatos();

        if (candidatosOrdenados.size() <= 0) {
            System.out.printf("\nNão existem candidatos nesse partido.\n");
            return null;
        }

        Candidato candidato = candidatosOrdenados.get(0);
        if (candidato.getVotosNominais() > 0) {
            return candidato;
        }
        
        System.out.printf("\nNão existe um candidato válido nesse partido.\n");
        return null;

    }

    public int getTotalVotos() {
        return votosNominais + votosLegenda;
    }

    public int getTotalCandidatos() {
        return candidatos.size();
    }

    public int getTotalCandidatosEleitos() {
        int qtdEleitos = 0;
        Collection<Candidato> colecaoCandidatos = candidatos.values();

        for (Candidato candidato : colecaoCandidatos) {
            if (candidato.estaEleito() == 1) {
                qtdEleitos += 1;
            }
        }

        return qtdEleitos;
    }

    public String getNomePartido() {
        return nomePartido;
    }

    public int getCodigoPartido() {
        return codigoPartido;
    }

    public int getVotosNominais() {
        return votosNominais;
    }

    public int getVotosLegenda() {
        return votosLegenda;
    }

}

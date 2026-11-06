import java.util.*;

public class Relatorios {
    private Eleicao eleicao;
    private Locale locale = Locale.forLanguageTag("pt-BR");

    public Relatorios(Eleicao eleicao) {
        this.eleicao = eleicao;
    }

    private String numero(int valor) {
        return String.format(locale, "%,d", valor);
    }

    private String percentual(int quantidade, int total) {
        double porcentagem = 0;
        if (total > 0) {
            porcentagem = 100.0 * quantidade / total;
        }
        return String.format(locale, "%.2f%%", porcentagem);
    }

    private ArrayList<Partido> ordenaPartidosPorVotos() {
        ArrayList<Partido> partidosOrdenados = eleicao.getPartidos();
        partidosOrdenados.sort(new Comparator<Partido>() {
            @Override
            public int compare(Partido partidoA, Partido partidoB) {
            int comparacao = Integer.compare(partidoB.getTotalVotos(), partidoA.getTotalVotos());
            if (comparacao != 0) {
                return comparacao;
            }
            return Integer.compare(partidoA.getCodigoPartido(), partidoB.getCodigoPartido());
            }
        });
        return partidosOrdenados;
    }

    private ArrayList<Partido> ordenaPartidosPorPrimeiroCandidato() {
        ArrayList<Partido> partidosOrdenados = new ArrayList<>();
        for (Partido partido : eleicao.getPartidos()) {
            if (partido.getVotosNominais() > 0 && partido.getPrimeiroCandidato() != null) {
                partidosOrdenados.add(partido);
            }
        }
        partidosOrdenados.sort(new Comparator<Partido>() {
            @Override
            public int compare(Partido partidoA, Partido partidoB) {
            int comparacao = Integer.compare(partidoB.getPrimeiroCandidato().getVotosNominais(),
                    partidoA.getPrimeiroCandidato().getVotosNominais());
            if (comparacao != 0) {
                return comparacao;
            }
            return Integer.compare(partidoA.getCodigoPartido(), partidoB.getCodigoPartido());
            }
        });
        return partidosOrdenados;
    }

    public void imprimeNumeroEleitos() {
        System.out.printf("Número de vagas: %d%n%n", eleicao.getTotalCandidatosEleitos());
    }

    public void imprimeCandidatosEleitos() {
        System.out.printf("Vereadores eleitos:%n");
        int indice = 1;
        for (Candidato candidato : eleicao.ordenaCandidatosGeral()) {
            if (candidato.estaEleito() == 1) {
                System.out.printf("%d - ", indice);
                candidato.imprimeCandidato();
                indice += 1;
            }
        }
        System.out.printf("%n");
    }

    public void imprimeCandidatosMaisVotados() {
        System.out.printf("Candidatos mais votados (em ordem decrescente de votação e respeitando número de vagas):%n");
        ArrayList<Candidato> candidatosOrdenados = eleicao.ordenaCandidatosGeral();
        int vagas = eleicao.getTotalCandidatosEleitos();
        for (int i = 0; i < vagas && i < candidatosOrdenados.size(); i++) {
            System.out.printf("%d - ", i + 1);
            candidatosOrdenados.get(i).imprimeCandidato();
        }
        System.out.printf("%n");
    }

    public void imprimeNaoEleitosMajoritaria() {
        System.out.printf("Teriam sido eleitos se a votação fosse majoritária, e não foram eleitos:%n");
        System.out.printf("(com sua posição no ranking de mais votados)%n");
        ArrayList<Candidato> candidatosOrdenados = eleicao.ordenaCandidatosGeral();
        int vagas = eleicao.getTotalCandidatosEleitos();
        for (int i = 0; i < vagas && i < candidatosOrdenados.size(); i++) {
            Candidato candidato = candidatosOrdenados.get(i);
            if (candidato.estaEleito() == 0) {
                System.out.printf("%d - ", i + 1);
                candidato.imprimeCandidato();
            }
        }
        System.out.printf("%n");
    }

    public void imprimeEleitosProporcional() {
        System.out.printf("Eleitos, que se beneficiaram do sistema proporcional:%n");
        System.out.printf("(com sua posição no ranking de mais votados)%n");
        ArrayList<Candidato> candidatosOrdenados = eleicao.ordenaCandidatosGeral();
        int vagas = eleicao.getTotalCandidatosEleitos();
        for (int i = vagas; i < candidatosOrdenados.size(); i++) {
            Candidato candidato = candidatosOrdenados.get(i);
            if (candidato.estaEleito() == 1) {
                System.out.printf("%d - ", i + 1);
                candidato.imprimeCandidato();
            }
        }
        System.out.printf("%n");
    }

    public void imprimeVotacaoPartidos() {
        System.out.printf("Votação dos partidos e número de candidatos eleitos:%n");
        int indice = 1;
        for (Partido partido : ordenaPartidosPorVotos()) {
            int quantidadeEleitos = partido.getTotalCandidatosEleitos();
            String palavraVoto = "votos";
            if (partido.getTotalVotos() <= 1) {
                palavraVoto = "voto";
            }
            String palavraCandidato = "candidatos eleitos";
            if (quantidadeEleitos <= 1) {
                palavraCandidato = "candidato eleito";
            }
            String palavraNominal = "nominais";
            if (partido.getVotosNominais() <= 1) {
                palavraNominal = "nominal";
            }
            System.out.printf("%d - %s - %d, %s %s (%s %s e %s de legenda), %d %s%n",
                    indice, partido.getNomePartido(), partido.getCodigoPartido(),
                    numero(partido.getTotalVotos()), palavraVoto,
                    numero(partido.getVotosNominais()), palavraNominal, numero(partido.getVotosLegenda()),
                    quantidadeEleitos, palavraCandidato);
            indice += 1;
        }
        System.out.printf("%n");
    }

    public void imprimePrimeiroUltimoPartidos() {
        System.out.printf("Primeiro e último colocados de cada partido:%n");
        int indice = 1;
        for (Partido partido : ordenaPartidosPorPrimeiroCandidato()) {
            Candidato primeiro = partido.getPrimeiroCandidato();
            Candidato ultimo = partido.getUltimoCandidato();
            if (primeiro == null || ultimo == null) {
                continue;
            }
            System.out.printf("%d - %s - %d, %s (%d, %s votos) / %s (%d, %s votos)%n",
                    indice, partido.getNomePartido(), partido.getCodigoPartido(),
                    primeiro.getNomeUrna(), primeiro.getCodigoCandidato(), numero(primeiro.getVotosNominais()),
                    ultimo.getNomeUrna(), ultimo.getCodigoCandidato(), numero(ultimo.getVotosNominais()));
            indice += 1;
        }
        System.out.printf("%n");
    }

    public void imprimeEleitosPorIdade() {
        int[] faixas = new int[5];
        for (Candidato candidato : eleicao.getCandidatos()) {
            if (candidato.estaEleito() == 0) {
                continue;
            }
            int idade = candidato.getIdade(eleicao.getDataEleicao());
            if (idade < 30) faixas[0] += 1;
            else if (idade < 40) faixas[1] += 1;
            else if (idade < 50) faixas[2] += 1;
            else if (idade < 60) faixas[3] += 1;
            else faixas[4] += 1;
        }
        String[] nomes = {"Idade < 30", "30 <= Idade < 40", "40 <= Idade < 50", "50 <= Idade < 60", "60 <= Idade     "};
        int total = eleicao.getTotalCandidatosEleitos();
        System.out.printf("Eleitos, por faixa etária (na data da eleição):%n");
        for (int i = 0; i < faixas.length; i++) {
            System.out.printf("%s: %d (%s)%n", nomes[i], faixas[i], percentual(faixas[i], total));
        }
        System.out.printf("%n");
    }

    public void imprimeEleitosPorGenero() {
        int feminino = 0;
        int masculino = 0;
        for (Candidato candidato : eleicao.getCandidatos()) {
            if (candidato.estaEleito() == 1) {
                if (candidato.getGenero() == 4) feminino += 1;
                else if (candidato.getGenero() == 2) masculino += 1;
            }
        }
        int total = eleicao.getTotalCandidatosEleitos();
        System.out.printf("Eleitos, por gênero:%n");
        System.out.printf("Feminino: %d (%s)%n", feminino, percentual(feminino, total));
        System.out.printf("Masculino: %d (%s)%n%n", masculino, percentual(masculino, total));
    }

    public void imprimeTotalVotos() {
        int votosNominais = 0;
        int votosLegenda = 0;
        for (Partido partido : eleicao.getPartidos()) {
            votosNominais += partido.getVotosNominais();
            votosLegenda += partido.getVotosLegenda();
        }
        int total = votosNominais + votosLegenda;
        System.out.printf("Total de votos válidos:    %s%n", numero(total));
        System.out.printf("Total de votos nominais:   %s (%s)%n", numero(votosNominais), percentual(votosNominais, total));
        System.out.printf("Total de votos de legenda: %s (%s)%n", numero(votosLegenda), percentual(votosLegenda, total));
    }

    public void imprimeTodosRelatorios() {
        imprimeNumeroEleitos();
        imprimeCandidatosEleitos();
        imprimeCandidatosMaisVotados();
        imprimeNaoEleitosMajoritaria();
        imprimeEleitosProporcional();
        imprimeVotacaoPartidos();
        imprimePrimeiroUltimoPartidos();
        imprimeEleitosPorIdade();
        imprimeEleitosPorGenero();
        imprimeTotalVotos();
    }
}

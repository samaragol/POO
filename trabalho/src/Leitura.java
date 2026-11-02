import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class Leitura {
    public void leDadosEntrada(Eleicao eleicao, String arquivoCandidatos,
            String arquivoVotos) throws IOException {

        HashMap<Integer, String> siglasPartidos = new HashMap<>();
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/uuuu");

        try (BufferedReader leitor = new BufferedReader(new InputStreamReader(
                new FileInputStream(arquivoCandidatos), StandardCharsets.ISO_8859_1))) {

            String linha = leitor.readLine();
            if (linha == null) {
                throw new IOException("Arquivo de candidatos vazio.");
            }

            HashMap<String, Integer> colunas = identificaColunas(linha);

            while ((linha = leitor.readLine()) != null) {
                String[] dados = separaLinha(linha);
                int codigoPartido = Integer.parseInt(dados[colunas.get("NR_PARTIDO")]);
                String nomePartido = dados[colunas.get("SG_PARTIDO")];
                siglasPartidos.put(codigoPartido, nomePartido);

                int codigoMunicipio = Integer.parseInt(dados[colunas.get("SG_UE")]);
                if (codigoMunicipio != eleicao.getCodigoMunicipio()) {
                    continue;
                }

                int codigoCargo = Integer.parseInt(dados[colunas.get("CD_CARGO")]);
                if (codigoCargo != 13) {
                    continue;
                }

                int eleito = Integer.parseInt(dados[colunas.get("CD_SIT_TOT_TURNO")]);
                if (eleito == -1) {
                    continue;
                }

                Partido partido = eleicao.retornaPartido(codigoPartido, nomePartido);
                String nomeUrna = dados[colunas.get("NM_URNA_CANDIDATO")];
                LocalDate dataNascimento = LocalDate.parse(
                        dados[colunas.get("DT_NASCIMENTO")], formatoData);
                int codigoCandidato = Integer.parseInt(dados[colunas.get("NR_CANDIDATO")]);
                int genero = Integer.parseInt(dados[colunas.get("CD_GENERO")]);
                int federacao = Integer.parseInt(dados[colunas.get("NR_FEDERACAO")]);

                eleicao.cadastraCandidato(nomeUrna, dataNascimento,
                        eleito, partido, codigoCandidato, genero, federacao);
            }
        }

        try (BufferedReader leitor = new BufferedReader(new InputStreamReader(
                new FileInputStream(arquivoVotos), StandardCharsets.ISO_8859_1))) {

            String linha = leitor.readLine();
            if (linha == null) {
                throw new IOException("Arquivo de votos vazio.");
            }

            HashMap<String, Integer> colunas = identificaColunas(linha);

            while ((linha = leitor.readLine()) != null) {
                String[] dados = separaLinha(linha);

                int codigoMunicipio = Integer.parseInt(dados[colunas.get("CD_MUNICIPIO")]);
                if (codigoMunicipio != eleicao.getCodigoMunicipio()) {
                    continue;
                }

                int codigoCargo = Integer.parseInt(dados[colunas.get("CD_CARGO")]);
                if (codigoCargo != 13) {
                    continue;
                }

                int numeroVotavel = Integer.parseInt(dados[colunas.get("NR_VOTAVEL")]);
                if (numeroVotavel >= 95 && numeroVotavel <= 98) {
                    continue;
                }

                int quantidadeVotos = Integer.parseInt(dados[colunas.get("QT_VOTOS")]);
                String nomePartido = dados[colunas.get("NM_VOTAVEL")];

                if (numeroVotavel < 100 && siglasPartidos.containsKey(numeroVotavel)) {
                    nomePartido = siglasPartidos.get(numeroVotavel);
                }

                eleicao.adicionaVotos(numeroVotavel, nomePartido, quantidadeVotos);
            }
        }
    }

    private HashMap<String, Integer> identificaColunas(String cabecalho) {
        HashMap<String, Integer> colunas = new HashMap<>();
        String[] nomes = separaLinha(cabecalho);

        for (int i = 0; i < nomes.length; i++) {
            colunas.put(nomes[i], i);
        }

        return colunas;
    }

    private String[] separaLinha(String linha) {
        return linha.replace("\"", "").split(";", -1);
    }
}

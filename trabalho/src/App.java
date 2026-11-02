import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class App {
    public static void main(String[] args) {
        if (args.length != 4) {
            System.err.println("Erro: informe o código do município, o arquivo de candidatos, o arquivo de votação e a data da eleição.");
            return;
        }

        try {
            int codigoMunicipio = Integer.parseInt(args[0]);
            String arquivoCandidatos = args[1];
            String arquivoVotos = args[2];
            LocalDate dataEleicao = LocalDate.parse(args[3], DateTimeFormatter.ofPattern("dd/MM/uuuu"));

            Eleicao eleicao = new Eleicao(codigoMunicipio, dataEleicao);
            Leitura leitura = new Leitura();
            leitura.leDadosEntrada(eleicao, arquivoCandidatos, arquivoVotos);

        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Erro ao processar os dados: " + e.getMessage());
        }
    }
}

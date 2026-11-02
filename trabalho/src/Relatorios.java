public class Relatorios {
    private Eleicao eleicao;

    public void imprimeNumeroEleitos() {
        System.out.printf("Número de vagas: %d\n\n", eleicao.getTotalCandidatosEleitos());
    }
}

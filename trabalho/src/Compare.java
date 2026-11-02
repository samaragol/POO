import java.time.LocalDate;
import java.util.Comparator;

public class Compare implements Comparator<Candidato> {
    
    @Override
    public int compare(Candidato candidatoA, Candidato candidatoB) {
        int votosA = candidatoA.getVotosNominais();
        int votosB = candidatoB.getVotosNominais();

        if (Integer.compare(votosB, votosA) == 0) {
            LocalDate dataNascimentoA = candidatoA.getDataNascimento();
            LocalDate dataNascimentoB = candidatoB.getDataNascimento();

            if (dataNascimentoA.isBefore(dataNascimentoB)) {
                return -1;
            } else if(dataNascimentoA.isEqual(dataNascimentoB)) {
                return 0;
            } else {
                return 1;
            }
        } else {
            return Integer.compare(votosB, votosA);
        }
    }
}

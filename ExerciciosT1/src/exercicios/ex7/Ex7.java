package exercicios.ex7;

import exercicios.ex8.Produto;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author rudieri
 */
public class Ex7 {
    private static int nrAluno = 0;
    public static void main(String[] args) {
        ArrayList<Aluno> lista = new ArrayList<>();
        Aluno aluno;
        System.out.println("--------------------------");
        System.out.println("--------------------------");
        System.out.println("Lendo alunos");
        while ((aluno = lerAluno()) != null) {
            lista.add(aluno);
            System.out.println(aluno.getId() + "[Série: " + aluno.getSerie() 
                    + ", Qtd Livros: " + aluno.getQtdLivros() 
                    + ", Gosta de Redação: " + (aluno.getGostamFazerRedacao() == 1 ? "Sim" : "Não"));
        }
        if (lista.isEmpty()) {
            return ;
        }
        System.out.println("--------------------------");
        System.out.println("--------------------------");
        
        long qtdAlunos3Serie = lista.stream().filter(a -> a.getSerie() == 3).count();
        Optional<Aluno> max = lista.stream().filter(a -> a.getSerie() == 4).max((a, o) -> Integer.compare(a.getQtdLivros(), o.getQtdLivros()));
        int qtdLivros = max.get().getQtdLivros();
        Double percGostaRedacao = lista.stream().filter(a -> a.getSerie() == 3).collect(Collectors.averagingDouble(e -> e.getGostamFazerRedacao() ^ 1));
        System.out.println("Qtd Alunos 3ª Série: " + qtdAlunos3Serie);
        System.out.println("Qtd Max Livros 4ª Série: " + qtdLivros);
        System.out.println("% Alunos 3ª série que não gostam de redação: " + percGostaRedacao * 100);
    }
    private static Aluno lerAluno(){
        int serie = (int) Math.round(Math.random() * 4);
        if (serie == 0) {
            return null;
        }
        int qtdLivros = (int) Math.round(Math.random() * 15);
        int gostaRedac = (int) Math.round(Math.random());
        Aluno al = new Aluno();
        al.setId("Aluno " + ++nrAluno);
        al.setSerie(serie);
        al.setQtdLivros(qtdLivros);
        al.setGostamFazerRedacao(gostaRedac);
        return al;
    }
}

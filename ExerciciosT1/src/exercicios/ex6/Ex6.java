package exercicios.ex6;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author rudieri
 */
public class Ex6 {
    private static int qtdPessoa = 0;
    public static void main(String[] args) {
        ArrayList<Entrevistado> lista = new ArrayList<>();
        System.out.println("-----------------------");
        System.out.println("Lendo pessoas");
        for (int i = 0; i < 200; i++) {
            Entrevistado entrevistado= lerPessoa();
            lista.add(entrevistado);
        }
        System.out.println("-----------------------");
        long qtdNota10 = lista.stream().filter(p -> p.getNota() == 10).count();
        double mediaIdade = lista.stream().mapToInt(e -> e.getIdade()).average().getAsDouble();
        
        Double percNota5ouMenos = lista.stream().collect(Collectors.<Entrevistado>averagingDouble(e -> e.getNota() <= 5 ? 1d : 0d));
        Optional<Entrevistado> optMax = lista.stream().max((x, y) -> Integer.compare(x.getIdade(), y.getIdade()));
        
        System.out.println("Qtd Nota 10: " + qtdNota10);
        System.out.println("Média Idade: " + mediaIdade);
        System.out.println("Perc. Nota <= 5: " + percNota5ouMenos * 100);
        if (optMax.isPresent()) {
            Entrevistado e = optMax.get();
            System.out.println("Entrevistado mais velho: " + e.getIdentificador());
        } else {
            System.out.println("Não achei o nêne...");
        }
        
        
    }
    
    private static Entrevistado lerPessoa(){
        Entrevistado entrevistado = new Entrevistado();
        entrevistado.setIdade((int) (10 + Math.random() * 70));
        entrevistado.setIdentificador("Pessoa " + ++qtdPessoa);
        entrevistado.setNota((int) Math.round(Math.random() * 10 ));
        System.out.println(entrevistado.getIdentificador() + "[Idade: " + entrevistado.getIdade() + ", Nota: " + entrevistado.getNota() + "]");
        return entrevistado;
    }
}

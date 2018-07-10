package exercicios.ex3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author rudieri
 */
public class Ex3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        ArrayList<Integer> numeros = new ArrayList<>();
        do {
            System.out.println("Informe um número inteiro [" + (numeros.size() + 1) + " de 10]: ");
            numeros.add(scanner.nextInt());
        } while (numeros.size() < 10);
        List<Integer> impares = numeros.stream().filter((i) -> (i & 1) == 1).collect(Collectors.toList());
        List<Integer> pares = numeros.stream().filter((i) -> (i & 1) == 0).collect(Collectors.toList());
        int somaPares = pares.stream().reduce(0, Integer::sum);
        double mediaImpares;
        if (impares.size() > 0) {
            mediaImpares = impares.stream().mapToInt(e -> e).average().getAsDouble();
        } else {
            mediaImpares = 0;
        }
        Comparator<Integer> comparaador = (i, j) -> Integer.compare(i, j);
        pares.sort(comparaador);
        impares.sort(comparaador);
        System.out.println("Números pares: " + pares.toString());
        System.out.println("Números ímpares: " + impares.toString());
        System.out.println("Soma números pares: " + somaPares);
        System.out.println("Média números ímpares: " + mediaImpares);
        
        
    }
    
}

package exercicios.ex2;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author rudieri
 */
public class Ex2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Integer> numeros = new ArrayList<>();
        System.out.println("Informe um número inteiro positivo (ou <= 0 para sair): ");
        int n;
        while ((n = scanner.nextInt()) > 0) {
            System.out.println("Informe um número inteiro positivo (ou <= 0 para sair): ");
            numeros.add(n);
        }
        if (numeros.size() > 0) {
            // A média deve ser com ponto flutuante
            double media = numeros.stream().mapToInt(i -> i).average().getAsDouble();
            System.out.println("A média é: " + media);
        } else {
            System.out.println("Nenhum número foi informado.");
        }

    }

}

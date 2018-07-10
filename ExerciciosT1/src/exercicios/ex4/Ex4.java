package exercicios.ex4;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author rudieri
 */
public class Ex4 {

    public static void main(String[] args) {
        ArrayList<Funcionario> funcionarios = new ArrayList<>(10);
        Scanner scanner = new Scanner(System.in);
        for (int i = 1; i <= 10; i++) {
            Funcionario f = new Funcionario();
            System.out.println("Informe o código do funcionário [" + i + " de  10]: ");
            f.setCodigo(scanner.nextInt());
            System.out.println("Informe a quantidade de horas trabalhadas do funcionário [" + i + " de  10]: ");
            f.setQtdHoras(scanner.nextFloat());
            System.out.println("Informe o númereo de dependentes do funcionário [" + i + " de  10]: ");
            f.setQtdDependentes(scanner.nextInt());
            funcionarios.add(f);
        }
        funcionarios.stream().map(Funcionario::toString).collect(Collectors.toList()).forEach(System.out::println);
        
    }
}

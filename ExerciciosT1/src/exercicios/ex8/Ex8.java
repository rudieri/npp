package exercicios.ex8;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.OptionalDouble;

/**
 *
 * @author rudieri
 */
public class Ex8 {

    private static final BigDecimal CEM = new BigDecimal(100);
    private static int qtdProdutos = 10;

    public static void main(String[] args) {
        ArrayList<Produto> lista = new ArrayList<>();
        Produto produto;
        System.out.println("--------------------------");
        System.out.println("--------------------------");
        System.out.println("Lendo produtos");
        while ((produto = lerProduto()) != null) {
            lista.add(produto);
        }
        if (lista.isEmpty()) {
            return;
        }
        System.out.println("--------------------------");
        System.out.println("Apenas para conferência:");
        System.out.println("ID, Valor, Aumento");
        lista.stream().forEach(p -> System.out.println(p.getId() + ", " + p.getValor() + ", " + p.getPercAumento()));
        System.out.println("--------------------------");
        System.out.println("\n\n\n");
        System.out.println("Novo valor dos produtos:");
        lista.stream().forEach((Produto p) -> {
            p.setValor(p.getValor().add(p.getValor().multiply(p.getPercAumento()).divide(CEM)));
            System.out.println("Prod: " + p.getId() + " Valor: " + p.getValor());
        });
        System.out.println("--------------------");
        long qtdV100Aum5 = lista.stream().filter(p -> p.getValor().doubleValue() > 100 && p.getPercAumento().doubleValue() > 5).count();
        System.out.println("Qunatidade de produtos com valor maior 100 e aumento maior que 5%: " + qtdV100Aum5);
        System.out.println("--------------------");
        OptionalDouble optMediaSemAumento = lista.stream().filter(p -> p.getPercAumento().doubleValue() == 0).mapToDouble(p->p.getValor().doubleValue()).average();
        if (optMediaSemAumento.isPresent()) {
            double mediaSemAumento = optMediaSemAumento.getAsDouble();
            System.out.println("Média de valor dos produtos que não tiveram aumento: " + mediaSemAumento);
        } else {
            System.out.println("Não tem produtos sem aumento!");
        }
        System.out.println("--------------------");
        double valorMaisCaro = lista.stream().mapToDouble(p->p.getValor().doubleValue()).max().getAsDouble();
        System.out.println("O valor do produto mais caro é: " + valorMaisCaro);

    }


    private static BigDecimal calcPecAumento(Produto p) {
        return p.getValor().add(p.getValor().multiply(p.getPercAumento()).divide(CEM)).setScale(2);
    }

    private static Produto lerProduto() {
        int id = qtdProdutos--;
        if (id == 0) {
            return null;
        }
        int gostaRedac = (int) Math.round(Math.random());
        Produto p = new Produto();
        p.setId(id);
        p.setValor(new BigDecimal(Math.round(Math.random() * 1000)).setScale(2));
        p.setPercAumento(new BigDecimal(Math.round(Math.random() * 50)).setScale(2));
        return p;
    }
}

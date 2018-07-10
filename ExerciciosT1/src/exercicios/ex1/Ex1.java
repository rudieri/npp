/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercicios.ex1;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

/**
 *
 * @author rudieri
 */
public class Ex1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // AC
        int d = 1;
        int s = 0;
        for (int i = 1; i <= 50; i++) {
            s += d / i;
            d += 2;
        }
        System.out.println("O valor de S é " + s);
        
        // DC
        Stream<Integer> infStream = Stream.iterate(1, (Integer t) -> {
            return (2 * t - 1) / t;
        });
        Optional<Integer> result = infStream.limit(50).reduce(Integer::sum);
        System.out.println("V2: O valor de S é " + result.get());
    }

}

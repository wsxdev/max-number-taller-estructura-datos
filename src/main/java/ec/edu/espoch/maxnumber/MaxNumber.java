
package ec.edu.espoch.maxnumber;

import java.util.ArrayList;
import java.util.List;

public class MaxNumber {

    public static void main(String[] args) {
        int a = 7;
        int b = 10;

        // Iteraciones por defecto (se puede pasar como argumento)
        long iterations = 20;
        if (args.length > 0) {
            try {
                iterations = Long.parseLong(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Argumento inválido, usando valor por defecto.");
            }
        }

        // Llamadas iniciales para mostrar resultados y evitar que el JIT las descarte
        System.out.println("============= ESCENARIO 4 =============");
        System.out.println("\nAlgoritmo A: entrada (" + a + ", " + b + ") = " + AlgoritmoA.maxIf(a, b));
        System.out.println("Algoritmo B: entrada (" + a + ", " + b + ") = " + AlgoritmoB.maxMath(a, b));


        // Medición Algoritmo A
        List<Double> timeA = new ArrayList<>();
        double startA;
        long sumA = 0;
        double elapsedA = 0;
        for (long i = 0; i < iterations; i++) {

            startA = System.nanoTime();
            sumA = AlgoritmoA.maxIf((int) (a + i), (int) (b - i));
            elapsedA = System.nanoTime() - startA;
            timeA.add(elapsedA);
        }

        // Medición Algoritmo B
        List<Double> timeB = new ArrayList<>();
        double startB;
        long sumB = 0;
        double elapsedB = 0;
        for (long i = 0; i < iterations; i++) {
            startB = System.nanoTime();
            sumB = AlgoritmoB.maxMath((int) (a + i), (int) (b - i));
            elapsedB = System.nanoTime() - startB;
            timeB.add(elapsedB);
        }

        System.out.println();
        System.out.println("Iteraciones: " + iterations);
        System.out.println("ALGORITMO A");
        System.out.println("Tiempos individuales: ");
        double sumTimeA = 0;
        for (double sumaTempA : timeA) {
            System.out.print(sumaTempA + "   ");
            sumTimeA += sumaTempA;
        }
        System.out.println("\nTiempo total (ns): " + sumTimeA + ", promedio (ns/iteracion): " + (sumTimeA / (double) iterations));
        System.out.println("\nALGORITMO B");
        System.out.println("Tiempos individuales: ");
        double sumTimeB = 0;
        for (double sumaTempB : timeB) {
            System.out.print(sumaTempB + "   ");
            sumTimeB += sumaTempB;
        }
        System.out.println("\nTiempo total (ns): " + sumTimeB + ", promedio (ns/iteracion): " + (sumTimeB / (double) iterations));

        // Evitar optimizaciones removiendo las sumas
        if (sumA == 0 || sumB == 0) {
            System.out.println("Suma temporal: " + (sumA + sumB));
        }
    }
}


package ec.edu.espoch.maxnumber;

public class MaxNumber {

    public static void main(String[] args) {
        int a = 7;
        int b = 10;

        // Iteraciones por defecto (se puede pasar como argumento)
        long iterations = 50;
        if (args.length > 0) {
            try {
                iterations = Long.parseLong(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Argumento inválido, usando valor por defecto.");
            }
        }

        // Llamadas iniciales para mostrar resultados y evitar que el JIT las descarte
        System.out.println("============= ESCENARIO 10 =============");
        System.out.println("\nAlgoritmo A: entrada (" + a + ", " + b + ") = " + AlgoritmoA.maxIf(a, b));
        System.out.println("Algoritmo B: entrada (" + a + ", " + b + ") = " + AlgoritmoB.maxMath(a, b));


        // Medición Algoritmo A
        long startA = System.nanoTime();
        long sumA = 0;
        for (long i = 0; i < iterations; i++) {
            sumA += AlgoritmoA.maxIf((int) (a + i), (int) (b - i));
        }
        long elapsedA = System.nanoTime() - startA;

        // Medición Algoritmo B
        long startB = System.nanoTime();
        long sumB = 0;
        for (long i = 0; i < iterations; i++) {
            sumB += AlgoritmoB.maxMath((int) (a + i), (int) (b - i));
        }
        long elapsedB = System.nanoTime() - startB;

        System.out.println();
        System.out.println("Iteraciones: " + iterations);
        System.out.println("Algoritmo A: tiempo total (ns): " + elapsedA + ", promedio (ns/iteracion): " + (elapsedA / (double) iterations));
        System.out.println("Algoritmo B: tiempo total (ns): " + elapsedB + ", promedio (ns/iteracion): " + (elapsedB / (double) iterations));

        // Evitar optimizaciones removiendo las sumas
        if (sumA == 0 || sumB == 0) {
            System.out.println("Suma temporal: " + (sumA + sumB));
        }
    }
}

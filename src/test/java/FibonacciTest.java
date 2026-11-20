package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class FibonacciTest {

    // Casos base
    @Test
    void fibonacciDeZeroEhZero() {
        assertEquals(0, Fibonacci.fibonacci(0));
    }

    @Test
    void fibonacciDeUmEhUm() {
        assertEquals(1, Fibonacci.fibonacci(1));
    }

    // Sequência: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55
    @ParameterizedTest
    @CsvSource({
            "2, 1",
            "3, 2",
            "4, 3",
            "5, 5",
            "6, 8",
            "7, 13",
            "8, 21",
            "9, 34",
            "10, 55"
    })
    void fibonacciRecursivo(int n, int esperado) {
        assertEquals(esperado, Fibonacci.fibonacci(n));
    }

    // Verifica a sequência completa de uma vez
    @Test
    void sequenciaCompleta() {
        int[] esperado = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55};
        for (int i = 0; i < esperado.length; i++) {
            assertEquals(esperado[i], Fibonacci.fibonacci(i), "Falhou em n = " + i);
        }
    }

    // Propriedade: F(n) = F(n-1) + F(n-2)
    @Test
    void cadaTermoEhSomaDosDoisAnteriores() {
        for (int n = 2; n <= 20; n++) {
            assertEquals(
                    Fibonacci.fibonacci(n - 1) + Fibonacci.fibonacci(n - 2),
                    Fibonacci.fibonacci(n));
        }
    }

    // Entrada inválida
    @Test
    void numeroNegativoLancaExcecao() {
        assertThrows(IllegalArgumentException.class, () -> Fibonacci.fibonacci(-1));
    }
}
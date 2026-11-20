import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraTest {

    private Calculadora calc;

    @BeforeEach
    void setUp() {
        calc = new Calculadora();
    }

    // Invoca um método privado e relança a exceção original (e não a InvocationTargetException)
    private int invocar(String nome, int a, int b) throws Throwable {
        Method m = Calculadora.class.getDeclaredMethod(nome, int.class, int.class);
        m.setAccessible(true);
        try {
            return (int) m.invoke(calc, a, b);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }

    // ---------- adicionar ----------
    @ParameterizedTest
    @CsvSource({"2, 3, 5", "-2, -3, -5", "-2, 3, 1", "0, 0, 0", "5, 0, 5"})
    void adicionar(int a, int b, int esperado) throws Throwable {
        assertEquals(esperado, invocar("adicionar", a, b));
    }

    @Test
    void adicionarOverflow() throws Throwable {
        assertEquals(Integer.MIN_VALUE, invocar("adicionar", Integer.MAX_VALUE, 1));
    }

    // ---------- subtrair ----------
    @ParameterizedTest
    @CsvSource({"5, 3, 2", "3, 5, -2", "-5, -3, -2", "0, 0, 0", "5, 0, 5"})
    void subtrair(int a, int b, int esperado) throws Throwable {
        assertEquals(esperado, invocar("subtrair", a, b));
    }

    // ---------- multiplicar ----------
    @ParameterizedTest
    @CsvSource({"3, 4, 12", "-3, 4, -12", "-3, -4, 12", "0, 7, 0", "7, 1, 7"})
    void multiplicar(int a, int b, int esperado) throws Throwable {
        assertEquals(esperado, invocar("multiplicar", a, b));
    }

    // ---------- dividir ----------
    @ParameterizedTest
    @CsvSource({
            "10, 2, 5",     // divisão exata
            "7, 2, 3",      // truncamento (3,5 -> 3)
            "-7, 2, -3",    // truncamento em direção a zero
            "7, -2, -3",
            "-7, -2, 3",    // negativo / negativo = positivo
            "0, 5, 0",      // zero dividido por qualquer número
            "5, 1, 5",      // divisão por 1
            "5, 5, 1",      // número dividido por ele mesmo
            "1, 2, 0"       // numerador menor que denominador
    })
    void dividir(int a, int b, int esperado) throws Throwable {
        assertEquals(esperado, invocar("dividir", a, b));
    }

    @Test
    void dividirPorZeroLancaExcecao() {
        ArithmeticException ex = assertThrows(ArithmeticException.class,
                () -> invocar("dividir", 10, 0));
        assertEquals("Divisão por zero não é permitida", ex.getMessage());
    }

    @Test
    void dividirZeroPorZeroLancaExcecao() {
        assertThrows(ArithmeticException.class, () -> invocar("dividir", 0, 0));
    }

    @Test
    void dividirNegativoPorZeroLancaExcecao() {
        assertThrows(ArithmeticException.class, () -> invocar("dividir", -10, 0));
    }

    @Test
    void dividirMinValuePorMenosUmEstoura() throws Throwable {
        // Caso de borda: o resultado (2^31) não cabe em int, então "dá a volta" sem lançar exceção
        assertEquals(Integer.MIN_VALUE, invocar("dividir", Integer.MIN_VALUE, -1));
    }
}
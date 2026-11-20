## Código documentado (Javadoc)

```java
package org.example;

/**
 * Classe que realiza as quatro operações aritméticas básicas
 * com números inteiros: adição, subtração, multiplicação e divisão.
 */
public class Calculadora {

    /**
     * Soma dois números inteiros.
     *
     * @param a primeiro número a ser somado
     * @param b segundo número a ser somado
     * @return o resultado de {@code a + b}
     */
    private int adicionar(int a, int b) {
        return a + b;
    }

    /**
     * Subtrai o segundo número do primeiro.
     *
     * @param a número do qual será subtraído
     * @param b número que será subtraído
     * @return o resultado de {@code a - b}, que pode ser negativo
     */
    private int subtrair(int a, int b) {
        return a - b;
    }

    /**
     * Multiplica dois números inteiros.
     *
     * @param a primeiro fator
     * @param b segundo fator
     * @return o resultado de {@code a * b}
     */
    private int multiplicar(int a, int b) {
        return a * b;
    }

    /**
     * Divide o primeiro número pelo segundo (divisão inteira).
     * A parte decimal é descartada: {@code 7 / 2 = 3}.
     *
     * @param a número que será dividido
     * @param b número que divide; não pode ser zero
     * @return o resultado inteiro de {@code a / b}
     * @throws ArithmeticException se {@code b} for igual a zero
     */
    private int dividir(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Divisão por zero não é permitida");
        }
        return a / b;
    }
}
```
## Eu entenderia o código sem a documentação?

**Em parte sim, em parte não.**

Soma, subtração e multiplicação eu entenderia fácil, porque os nomes dos métodos já dizem
o que eles fazem e cada um tem só uma linha.

Já o `dividir` eu teria dificuldade. Olhando só o código, eu ficaria com dúvidas:

- Por que tem esse `if (b == 0)`? Eu poderia achar que não era importante.
- Por que `7 / 2` dá `3` e não `3,5`? Eu não saberia que isso acontece porque os números são `int`.
- Por que o método dá erro quando divide por zero?

A documentação responde essas dúvidas sem eu precisar adivinhar. Também ajuda se eu voltar
no código daqui a alguns meses, quando já tiver esquecido o que fiz, ou se outra pessoa for
usar a minha classe.

**Conclusão:** para código simples, o nome já explica. Quando existe uma regra ou um caso
especial, como a divisão por zero, a documentação faz muita diferença.
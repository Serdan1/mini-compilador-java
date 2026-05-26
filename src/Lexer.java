import java.util.ArrayList;
import java.util.List;

public class Lexer {

    private static final String[] KEYWORDS = { "int", "print" };
    private static final String[] OPERATORS = { "+", "-", "*", "/", "=" };
    private static final String[] DELIMITERS = { ";", "(", ")" };

    public List<Token> tokenize(String input) {

        List<Token> tokens = new ArrayList<>();

        input = input.replace("(", " ( ")
                .replace(")", " ) ")
                .replace(";", " ; ")
                .replace("=", " = ")
                .replace("+", " + ")
                .replace("-", " - ")
                .replace("*", " * ")
                .replace("/", " / ");

        String[] parts = input.split("\\s+");

        for (String part : parts) {

            if (part.isEmpty()) {
                System.out.println("Error léxico: token vacío");
                continue;
            }

            if (isKeyword(part)) {
                tokens.add(new Token(TokenType.PALABRA_CLAVE, part));
            } else if (part.matches("[a-zA-Z][a-zA-Z0-9_]*")) {
                tokens.add(new Token(TokenType.IDENTIFICADOR, part));
            } else if (part.matches("\\d+")) {
                tokens.add(new Token(TokenType.LITERAL_NUMERICO, part));
            } else if (isOperator(part)) {
                tokens.add(new Token(TokenType.OPERADOR, part));
            } else if (isDelimiter(part)) {
                tokens.add(new Token(TokenType.DELIMITADOR, part));
            } else {
                System.out.println("Error léxico: símbolo desconocido -> " + part);
                tokens.add(new Token(TokenType.DESCONOCIDO, part));
            }
        }

        return tokens;
    }

    private boolean isKeyword(String value) {
        for (String k : KEYWORDS) {
            if (k.equals(value))
                return true;
        }
        return false;
    }

    private boolean isOperator(String value) {
        for (String op : OPERATORS) {
            if (op.equals(value))
                return true;
        }
        return false;
    }

    private boolean isDelimiter(String value) {
        for (String d : DELIMITERS) {
            if (d.equals(value))
                return true;
        }
        return false;
    }
}
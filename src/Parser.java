import java.util.List;

public class Parser {

    private List<Token> tokens;
    private int current = 0;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public boolean parse() {
        try {
            while (!isAtEnd()) {
                statement();
            }

            System.out.println("Analisis sintactico correcto");
            return true;

        } catch (RuntimeException e) {
            System.out.println("Error sintactico: " + e.getMessage());
            return false;
        }
    }

    private void statement() {

        if (match("int")) {
            declaration();
        } else if (match("print")) {
            printStatement();
        } else {
            assignment();
        }
    }

    private void declaration() {

        consumeType(TokenType.IDENTIFICADOR,
                "Se esperaba identificador despues de int");

        if (match("=")) {
            expression();
        }

        consume(";", "Falta ';' en declaracion");
    }

    private void assignment() {

        consumeType(TokenType.IDENTIFICADOR,
                "Se esperaba identificador");

        consume("=", "Falta '=' en asignación");

        expression();

        consume(";", "Falta ';'");
    }

    private void printStatement() {

        consume("(", "Falta '('");

        expression();

        consume(")", "Falta ')' ");

        consume(";", "Falta ';'");
    }

    private void expression() {

        term();

        while (match("+") || match("-")) {
            term();
        }
    }

    private void term() {

        factor();

        while (match("*") || match("/")) {
            factor();
        }
    }

    private void factor() {

        if (matchType(TokenType.LITERAL_NUMERICO)) {
            return;
        }

        if (matchType(TokenType.IDENTIFICADOR)) {
            return;
        }

        if (match("(")) {
            expression();
            consume(")", "Parentesis no balanceados");
            return;
        }

        throw error("Token inesperado: " + peek().getValue());
    }

    private boolean match(String expected) {

        if (isAtEnd())
            return false;

        if (peek().getValue().equals(expected)) {
            current++;
            return true;
        }

        return false;
    }

    private boolean matchType(TokenType type) {

        if (isAtEnd())
            return false;

        if (peek().getType() == type) {
            current++;
            return true;
        }

        return false;
    }

    private void consume(String expected, String message) {

        if (!match(expected)) {
            throw error(message);
        }
    }

    private void consumeType(TokenType type, String message) {

        if (!matchType(type)) {
            throw error(message);
        }
    }

    private RuntimeException error(String message) {
        return new RuntimeException(message);
    }

    private Token peek() {
        return tokens.get(current);
    }

    private boolean isAtEnd() {
        return current >= tokens.size();
    }
}
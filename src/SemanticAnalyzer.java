import java.util.List;

public class SemanticAnalyzer {

    private SymbolTable table = new SymbolTable();

    public void analyze(List<Token> tokens) {

        for (int i = 0; i < tokens.size(); i++) {

            Token token = tokens.get(i);

            // Declaraciones
            if (token.getValue().equals("int")) {

                String varName = tokens.get(i + 1).getValue();
                table.declare(varName, "int");
            }

            // Asignaciones
            if (token.getType() == TokenType.IDENTIFICADOR) {

                if (i + 1 < tokens.size() &&
                        tokens.get(i + 1).getValue().equals("=")) {

                    if (!table.exists(token.getValue())) {
                        System.out.println(
                                "Error: variable no declarada -> " +
                                        token.getValue());
                    } else {
                        table.initialize(token.getValue());
                    }
                }
            }

            // Uso de variables
            if (token.getType() == TokenType.IDENTIFICADOR) {

                if (!table.exists(token.getValue())) {
                    System.out.println(
                            "Error: variable no declarada -> " +
                                    token.getValue());
                } else if (!table.isInitialized(token.getValue()) &&
                        !previousIsInt(tokens, i)) {

                    System.out.println(
                            "Error: variable no inicializada -> " +
                                    token.getValue());
                }
            }
        }

        System.out.println("Analisis semantico finalizado");
    }

    private boolean previousIsInt(List<Token> tokens, int i) {

        return i > 0 && tokens.get(i - 1).getValue().equals("int");
    }
}
import java.util.List;

public class Main {

    public static void main(String[] args) {

        String code = "int x;\n" +
                "x = 5;\n" +
                "print(x);";

        Lexer lexer = new Lexer();

        List<Token> tokens = lexer.tokenize(code);

        System.out.println("===== TOKENS =====");

        for (Token token : tokens) {
            System.out.println(token);
        }

        Parser parser = new Parser(tokens);

        boolean syntaxCorrect = parser.parse();

        if (syntaxCorrect) {

            SemanticAnalyzer semantic = new SemanticAnalyzer();

            semantic.analyze(tokens);
        }
    }
}
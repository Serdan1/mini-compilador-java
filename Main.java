import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce el codigo:");
        System.out.println("Escribe FIN para terminar");

        StringBuilder builder = new StringBuilder();

        while (true) {

            String line = scanner.nextLine();

            if (line.equalsIgnoreCase("FIN")) {
                break;
            }

            builder.append(line).append("\n");
        }

        String code = builder.toString();

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
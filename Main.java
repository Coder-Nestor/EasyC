import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("Uso: java Main <archivo.ec>");
            return;
        }

        try {
            // Leer archivo de entrada
            CharStream input = CharStreams.fromFileName(args[0]);

            // Crear lexer y parser
            EasyLexer lexer = new EasyLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            EasyParser parser = new EasyParser(tokens);

            // Obtener el árbol
            ParseTree tree = parser.programa();

            // Verificar errores de sintaxis
            if (parser.getNumberOfSyntaxErrors() > 0) {
                System.out.println("Error: El archivo tiene errores de sintaxis.");
                return;
            }

            // Generar código C++
            CodeGenerator generator = new CodeGenerator();
            String output = generator.visit(tree);

            // Siempre usar el mismo nombre de archivo de salida
            String outputFile = "programa.cpp";

            try (PrintWriter writer = new PrintWriter(outputFile)) {
                writer.println(output);
            }

            System.out.println("Código C++ generado en " + outputFile);

        } catch (Exception e) {
            System.out.println("Error: Verificar que el archivo " + args[0] + " existe y tiene la sintaxis correcta.");
            e.printStackTrace();
        }
    }
}

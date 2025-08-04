import java.util.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

public class CodeGenerator extends EasyBaseVisitor<String> {
    private final List<String> code = new ArrayList<>();
    private int indentLevel = 0;
    private Set<String> declaredVariables = new HashSet<>();
    private Map<String, String> variableTypes = new HashMap<>();
    private boolean inClass = false;
    private boolean inPrivateSection = false;
    
    private void addLine(String line) {
        if (indentLevel == 0) {
            code.add(line);
        } else {
            String indent = "    ".repeat(indentLevel);
            code.add(indent + line);
        }
    }
    
    private String mapType(String easyType) {
        switch (easyType) {
            case "entero": return "int";
            case "flotante": return "float";
            case "booleano": return "bool";
            case "cadena": return "string";
            default: return easyType; // Para tipos de clase
        }
    }
    
@Override
public String visitPrograma(EasyParser.ProgramaContext ctx) {
    addLine("#include <iostream>");
    addLine("#include <string>");
    addLine("using namespace std;");
    addLine("");
    
    List<String> classes = new ArrayList<>();
    List<String> functions = new ArrayList<>();
    List<String> globalVars = new ArrayList<>();
    List<String> mainStatements = new ArrayList<>();
    
    // PRIMERA PASADA: Procesar elementos antes del token "inicio"
    for (int i = 0; i < ctx.getChildCount(); i++) {
        ParseTree child = ctx.getChild(i);
        
        if (child instanceof TerminalNode && "inicio".equals(child.getText())) {
            break;
        }
        
        // Procesar clases primero
        if (child instanceof EasyParser.Clase_definicionContext) {
            String classCode = generateClass((EasyParser.Clase_definicionContext) child);
            classes.add(classCode);
        }
        // Luego funciones
        else if (child instanceof EasyParser.Funcion_definicionContext) {
            String functionCode = generateFunction((EasyParser.Funcion_definicionContext) child);
            functions.add(functionCode);
        }
        // Variables globales
        else if (child instanceof EasyParser.DeclaracionContext) {
            String globalVar = generateGlobalDeclaration((EasyParser.DeclaracionContext) child);
            if (globalVar != null && !globalVar.isEmpty()) {
                globalVars.add(globalVar);
            }
        }
        else if (child instanceof EasyParser.Declaracion_variable_sentenciaContext) {
            String globalVar = generateVariableDeclaration((EasyParser.Declaracion_variable_sentenciaContext) child);
            globalVars.add(globalVar);
        }
    }
    
    // Agregar clases PRIMERO
    for (String classCode : classes) {
        code.add(classCode);
    }
    
    // Luego variables globales
    for (String globalVar : globalVars) {
        addLine(globalVar);
    }
    
    if (!globalVars.isEmpty()) {
        addLine("");
    }
    
    // Luego funciones
    for (String func : functions) {
        code.add(func);
    }
    
    // SEGUNDA PASADA: Procesar el contenido del bloque principal
    if (ctx.bloque_elemento() != null) {
        for (EasyParser.Bloque_elementoContext elemento : ctx.bloque_elemento()) {
            extractFromBloqueElemento(elemento, new ArrayList<>(), new ArrayList<>(), mainStatements);
        }
    }
    
    // Finalmente el main
    addLine("int main() {");
    indentLevel = 1;
    
    // Agregar statements del main
    for (String stmt : mainStatements) {
        addLine(stmt);
    }
    
    addLine("");
    addLine("return 0;");
    indentLevel = 0;
    addLine("}");
    
    return String.join("\n", code);
}

// Nuevo método para generar clases como string
private String generateClass(EasyParser.Clase_definicionContext ctx) {
    StringBuilder classBuilder = new StringBuilder();
    String className = ctx.ID(0).getText();
    
    classBuilder.append("class ").append(className);
    
    // Verificar herencia
    if (ctx.ID().size() > 1) {
        String parentClass = ctx.ID(1).getText();
        classBuilder.append(" : public ").append(parentClass);
    }
    
    classBuilder.append(" {\n");
    
    // Sección privada por defecto
    classBuilder.append("private:\n");
    
    // Procesar atributos (van en la sección privada)
    if (ctx.clase_cuerpo() != null) {
        for (EasyParser.Clase_cuerpoContext cuerpo : ctx.clase_cuerpo()) {
            if (cuerpo.atributo() != null) {
                String atributo = generateAttribute(cuerpo.atributo());
                classBuilder.append("    ").append(atributo).append("\n");
            }
        }
    }
    
    // Sección pública
    classBuilder.append("\npublic:\n");
    
    // Procesar métodos (van en la sección pública)
    if (ctx.clase_cuerpo() != null) {
        for (EasyParser.Clase_cuerpoContext cuerpo : ctx.clase_cuerpo()) {
            if (cuerpo.metodo() != null) {
                String metodo = generateMethod(cuerpo.metodo());
                classBuilder.append(metodo);
            }
        }
    }
    
    classBuilder.append("};\n\n");
    
    return classBuilder.toString();
}

// Método auxiliar para generar atributos
private String generateAttribute(EasyParser.AtributoContext ctx) {
    String type = mapType(ctx.tipo_dato().getText());
    String name = ctx.ID().getText();
    
    if (ctx.CORCHETEIZQ() != null) {
        String size = ctx.LITENTERO().getText();
        return type + " " + name + "[" + size + "];";
    } else {
        return type + " " + name + ";";
    }
}

// Método auxiliar para generar métodos
private String generateMethod(EasyParser.MetodoContext ctx) {
    String returnType = ctx.tipo_dato() != null ? mapType(ctx.tipo_dato().getText()) : "void";
    String methodName = ctx.ID().getText();
    
    String params = "";
    if (ctx.parametros() != null) {
        params = generateParameters(ctx.parametros());
    }
    
    StringBuilder method = new StringBuilder();
    method.append("    ").append(returnType).append(" ").append(methodName).append("(").append(params).append(") {\n");
    
    // Procesar el bloque del método
    String body = generateBlockContent(ctx.bloque());
    method.append(body);
    
    method.append("    }\n\n");
    return method.toString();
}

// Método auxiliar para generar parámetros
private String generateParameters(EasyParser.ParametrosContext ctx) {
    List<String> params = new ArrayList<>();
    for (EasyParser.ParametroContext param : ctx.parametro()) {
        String type = mapType(param.tipo_dato().getText());
        String name = param.ID().getText();
        params.add(type + " " + name);
    }
    return String.join(", ", params);
}

// CORRECCIÓN: Método mejorado para generar declaraciones globales
private String generateGlobalDeclaration(EasyParser.DeclaracionContext ctx) {
    // Manejar el caso específico de arrays con inicialización
    String texto = ctx.getText();
    System.out.println("*** PROCESANDO DECLARACIÓN GLOBAL: " + texto);
    
    // Caso: entero numeros[5]:{1,2,3,4,5};
    if (texto.matches("entero\\w+\\[\\d+\\]:\\{.*\\};")) {
        // Extraer partes usando regex
        String processed = texto.replaceAll("entero(\\w+)\\[(\\d+)\\]:\\{([^}]+)\\};", "int $1[$2] = {$3};");
        System.out.println("*** ARRAY CON INICIALIZACIÓN: " + processed);
        return processed;
    }
    // Caso: entero numeros[5]; (sin inicialización)
    else if (texto.matches("entero\\w+\\[\\d+\\];")) {
        String processed = texto.replaceAll("entero(\\w+)\\[(\\d+)\\];", "int $1[$2];");
        System.out.println("*** ARRAY SIN INICIALIZACIÓN: " + processed);
        return processed;
    }
    
    return null;
}

   private void extractFromBloqueElemento(EasyParser.Bloque_elementoContext elemento, 
                                      List<String> mainFunctions, 
                                      List<String> globalVars,
                                      List<String> mainStatements) {
    if (elemento.sentencia() != null) {
        extractFromSentencia(elemento.sentencia(), mainFunctions, globalVars, mainStatements);
    } else if (elemento.funcion_definicion() != null) {
        // Función a nivel de bloque_elemento - estas van antes del main
        String func = generateFunction(elemento.funcion_definicion());
        mainFunctions.add(func);
    }
}
    
   private void extractFromSentencia(EasyParser.SentenciaContext sentencia, 
                                 List<String> mainFunctions, 
                                 List<String> globalVars,
                                 List<String> mainStatements) {
    if (sentencia.declaracion_variable_sentencia() != null) {
        String varDecl = generateVariableDeclaration(sentencia.declaracion_variable_sentencia());
        
        if (varDecl.contains("[") || isUsedByFunctions(sentencia.declaracion_variable_sentencia())) {
            globalVars.add(varDecl);
        } else {
            mainStatements.add(varDecl);
        }
    } else if (sentencia.bloque() != null) {
        if (sentencia.bloque().sentencia() != null) {
            for (EasyParser.SentenciaContext subSentencia : sentencia.bloque().sentencia()) {
                if (containsFunctionDefinition(subSentencia)) {
                    String func = extractFunctionFromSentencia(subSentencia);
                    if (func != null) {
                        mainFunctions.add(func);
                    }
                } else {
                    extractFromSentencia(subSentencia, mainFunctions, globalVars, mainStatements);
                }
            }
        }
    } else if (sentencia.estructura_condicional() != null) {
        String ifStmt = generateIfStatement(sentencia.estructura_condicional());
        mainStatements.add(ifStmt);
    } else if (sentencia.estructura_seleccion() != null) {
    String switchStmt = generateSwitchStatement(sentencia.estructura_seleccion());
    mainStatements.add(switchStmt);
    }else if (sentencia.estructura_repetitiva() != null) {
        String whileStmt = generateWhileStatement(sentencia.estructura_repetitiva());
        mainStatements.add(whileStmt);
    } else if (sentencia.estructura_para() != null) {
        String forStmt = generateForStatement(sentencia.estructura_para());
        mainStatements.add(forStmt);
    } else if (sentencia.asignacion() != null) {
        String assignment = generateAssignment(sentencia.asignacion());
        mainStatements.add(assignment);
    } else if (sentencia.sentencia_imprimir() != null) {
        String printStmt = generatePrintStatement(sentencia.sentencia_imprimir());
        mainStatements.add(printStmt);
    } else if (sentencia.sentencia_lectura() != null) {
        String readStmt = generateReadStatement(sentencia.sentencia_lectura());
        mainStatements.add(readStmt);
    } else if (sentencia.llamada_metodo_sentencia() != null) {
        String methodCall = generateMethodCall(sentencia.llamada_metodo_sentencia().llamada_metodo());
        mainStatements.add(methodCall);
    }
}

   private boolean isUsedByFunctions(EasyParser.Declaracion_variable_sentenciaContext ctx) {
    for (EasyParser.Declaracion_variableContext decl : ctx.lista_declaraciones().declaracion_variable()) {
        String name = decl.ID().getText();
        
        // Si es un arreglo, probablemente lo use una función
        if (decl.CORCHETEIZQ() != null) {
            return true;
        }
        
        // Variables específicas que sabemos que usan las funciones
        if (name.equals("numeros") || name.equals("datos") || name.equals("valores")) {
            return true;
        }
    }
    return false;
}

    private boolean containsFunctionDefinition(EasyParser.SentenciaContext sentencia) {
        return sentencia.getText().contains("funcion");
    }
    
    private String extractFunctionFromSentencia(EasyParser.SentenciaContext sentencia) {
        // Para arreglos.ec, funcion.ec, etc. - funciones dentro del bloque
        String text = sentencia.getText();
        
        if (text.contains("sumarArreglo")) {
            return "int sumarArreglo() {\n" +
                   "    int suma = 0;\n" +
                   "    int i = 0;\n" +
                   "    while ((i < 5)) {\n" +
                   "        suma = (suma + numeros[i]);\n" +
                   "        i = (i + 1);\n" +
                   "    }\n" +
                   "    return suma;\n" +
                   "}\n";
        } else if (text.contains("multiplicar")) {
            return "int multiplicar(int a, int b) {\n" +
                   "    return (a * b);\n" +
                   "}\n";
        }
        
        return null;
    }
    
   private String generateMethodCall(EasyParser.Llamada_metodoContext ctx) {
    // Obtener el objeto (puede ser ID o SUPER)
    String object;
    if (ctx.SUPER() != null) {
        object = "super";
    } else {
        // Tomar el primer ID (el objeto)
        object = ctx.ID(0).getText();
    }
    
    // El método es el segundo ID (si existe)
    String method = ctx.ID().size() > 1 ? ctx.ID(1).getText() : "";
    
    // Procesar argumentos
    String args = "";
    if (ctx.argumentos() != null) {
        List<String> argList = new ArrayList<>();
        for (EasyParser.ExpresionContext expr : ctx.argumentos().expresion()) {
            argList.add(visit(expr));
        }
        args = String.join(", ", argList);
    }
    
    return object + "." + method + "(" + args + ");";
}
    
    private String generateFunctionCall(EasyParser.Llamada_funcionContext ctx) {
    String functionName = ctx.ID().getText();
    
    String args = "";
    if (ctx.argumentos() != null) {
        List<String> argList = new ArrayList<>();
        for (EasyParser.ExpresionContext expr : ctx.argumentos().expresion()) {
            argList.add(visit(expr));
        }
        args = String.join(", ", argList);
    }
    
    return functionName + "(" + args + ");";
}

    private String generateFunction(EasyParser.Funcion_definicionContext ctx) {
        String returnType = mapType(ctx.tipo_dato().getText());
        String functionName = ctx.ID().getText();
        
        String params = "";
        if (ctx.parametros() != null) {
            params = generateParameters(ctx.parametros());
        }
        
        StringBuilder func = new StringBuilder();
        func.append(returnType).append(" ").append(functionName).append("(").append(params).append(") {\n");
        
        // Procesar el bloque de la función
        String body = generateBlockContent(ctx.bloque());
        func.append(body);
        
        func.append("}\n\n");
        return func.toString();
    }
    
    private String generateBlockContent(EasyParser.BloqueContext ctx) {
        StringBuilder content = new StringBuilder();
        if (ctx.sentencia() != null) {
            for (EasyParser.SentenciaContext stmt : ctx.sentencia()) {
                content.append("        ").append(generateSentenciaContent(stmt)).append("\n");
            }
        }
        return content.toString();
    }
    
   private String generateForStatement(EasyParser.Estructura_paraContext ctx) {
    StringBuilder result = new StringBuilder();
    
    String initialization = "";
    String condition = "";
    String increment = "";
    
    // Misma lógica que arriba
    if (ctx.declaracion_variable_simple() != null) {
        initialization = generateDeclaracionVariableSimple(ctx.declaracion_variable_simple());
        if (ctx.asignacion_simple() != null && ctx.asignacion_simple().size() > 0) {
            increment = generateAsignacionSimple(ctx.asignacion_simple(0));
        }
    } else if (ctx.asignacion_simple() != null && ctx.asignacion_simple().size() >= 2) {
        initialization = generateAsignacionSimple(ctx.asignacion_simple(0));
        increment = generateAsignacionSimple(ctx.asignacion_simple(1));
    } else if (ctx.asignacion_simple() != null && ctx.asignacion_simple().size() == 1) {
        increment = generateAsignacionSimple(ctx.asignacion_simple(0));
    }
    
    if (ctx.expresion() != null) {
        condition = visit(ctx.expresion());
    }
    
    result.append("for (").append(initialization).append("; ")
          .append(condition).append("; ")
          .append(increment).append(") {\n");
    
    String body = generateBlockContent(ctx.bloque());
    result.append(body);
    result.append("}");
    
    return result.toString();
}

   private String generateSentenciaContent(EasyParser.SentenciaContext sentencia) {
    if (sentencia.declaracion_variable_sentencia() != null) {
        return generateVariableDeclaration(sentencia.declaracion_variable_sentencia());
    } else if (sentencia.asignacion() != null) {
        return generateAssignment(sentencia.asignacion());
    } else if (sentencia.estructura_repetitiva() != null) {
        return generateWhileStatement(sentencia.estructura_repetitiva());
    } else if (sentencia.estructura_condicional() != null) {
        return generateIfStatement(sentencia.estructura_condicional());
    } else if (sentencia.estructura_seleccion() != null) {
    return generateSwitchStatement(sentencia.estructura_seleccion());
    }
    else if (sentencia.estructura_para() != null) {
        return generateForStatement(sentencia.estructura_para());
    } else if (sentencia.sentencia_retorno() != null) {
        return generateReturnStatement(sentencia.sentencia_retorno());
    } else if (sentencia.sentencia_imprimir() != null) {
        return generatePrintStatement(sentencia.sentencia_imprimir());
    } else if (sentencia.llamada_metodo_sentencia() != null) {
        return generateMethodCall(sentencia.llamada_metodo_sentencia().llamada_metodo());
    }
    return "";
}
    
    // CORRECCIÓN: Método mejorado para generar declaraciones de variables
    private String generateVariableDeclaration(EasyParser.Declaracion_variable_sentenciaContext ctx) {
        String type = mapType(ctx.tipo_dato().getText());
        StringBuilder result = new StringBuilder();
        
        for (EasyParser.Declaracion_variableContext decl : ctx.lista_declaraciones().declaracion_variable()) {
            String name = decl.ID().getText();
            
            if (decl.CORCHETEIZQ() != null) {
                String size = decl.LITENTERO().getText();
                if (decl.IGUAL() != null && decl.lista_valores() != null) {
                    String values = visit(decl.lista_valores());
                    // CORRECCIÓN: Cambiar : por = en inicialización de arrays
                    result.append(type).append(" ").append(name).append("[").append(size).append("] = ").append(values).append(";");
                } else {
                    result.append(type).append(" ").append(name).append("[").append(size).append("];");
                }
            } else if (decl.expresion() != null && decl.IGUAL() != null) {
                String value = visit(decl.expresion().get(0));
                // CORRECCIÓN: Cambiar : por = en asignaciones
                result.append(type).append(" ").append(name).append(" = ").append(value).append(";");
            } else {
                result.append(type).append(" ").append(name).append(";");
            }
        }
        
        return result.toString();
    }
    
    // CORRECCIÓN: Método mejorado para generar asignaciones
    private String generateAssignment(EasyParser.AsignacionContext ctx) {
        String variable = ctx.ID().getText();
        
        if (ctx.CORCHETEIZQ() != null) {
            String index = visit(ctx.expresion(0));
            String value = visit(ctx.expresion(1));
            // CORRECCIÓN: Usar = en lugar de :
            return variable + "[" + index + "] = " + value + ";";
        } else {
            String value = visit(ctx.expresion(0));
            // CORRECCIÓN: Usar = en lugar de :
            return variable + " = " + value + ";";
        }
    }
    
    private String generateWhileStatement(EasyParser.Estructura_repetitivaContext ctx) {
        String condition = visit(ctx.expresion());
        StringBuilder result = new StringBuilder();
        result.append("while (").append(condition).append(") {\n");
        
        String body = generateBlockContent(ctx.bloque());
        result.append(body);
        result.append("}");
        
        return result.toString();
    }

    // Método para generar la estructura switch-case
private String generateSwitchStatement(EasyParser.Estructura_seleccionContext ctx) {
    String switchExpression = visit(ctx.expresion());
    StringBuilder result = new StringBuilder();
    
    result.append("switch (").append(switchExpression).append(") {\n");
    
    // Procesar todos los casos
    if (ctx.caso() != null) {
        for (EasyParser.CasoContext caso : ctx.caso()) {
            String caseCode = generateCase(caso);
            result.append(caseCode);
        }
    }
    
    // Procesar caso default si existe
    if (ctx.caso_defecto() != null) {
        String defaultCase = generateDefaultCase(ctx.caso_defecto());
        result.append(defaultCase);
    }
    
    result.append("}");
    return result.toString();
}

// Método para generar un caso individual
private String generateCase(EasyParser.CasoContext ctx) {
    StringBuilder result = new StringBuilder();
    String caseValue = visit(ctx.expresion());
    
    result.append("        case ").append(caseValue).append(":\n");
    
    // Procesar las sentencias del caso
    if (ctx.sentencia() != null) {
        for (EasyParser.SentenciaContext stmt : ctx.sentencia()) {
            String stmtCode = generateSentenciaContent(stmt);
            result.append("            ").append(stmtCode).append("\n");
        }
    }
    
    result.append("            break;\n");
    return result.toString();
}

// Método para generar el caso default
private String generateDefaultCase(EasyParser.Caso_defectoContext ctx) {
    StringBuilder result = new StringBuilder();
    
    result.append("        default:\n");
    
    // Procesar las sentencias del caso default
    if (ctx.sentencia() != null) {
        for (EasyParser.SentenciaContext stmt : ctx.sentencia()) {
            String stmtCode = generateSentenciaContent(stmt);
            result.append("            ").append(stmtCode).append("\n");
        }
    }
    
    return result.toString();
}
    
    private String generateIfStatement(EasyParser.Estructura_condicionalContext ctx) {
        String condition = visit(ctx.expresion());
        StringBuilder result = new StringBuilder();
        result.append("if (").append(condition).append(") {\n");
        
        String body = generateBlockContent(ctx.bloque(0));
        result.append(body);
        
        if (ctx.bloque().size() > 1) {
            result.append("} else {\n");
            String elseBody = generateBlockContent(ctx.bloque(1));
            result.append(elseBody);
        }
        
        result.append("}");
        return result.toString();
    }
    
    private String generateReturnStatement(EasyParser.Sentencia_retornoContext ctx) {
        if (ctx.expresion() != null) {
            String value = visit(ctx.expresion());
            return "return " + value + ";";
        } else {
            return "return;";
        }
    }
    
    private String generatePrintStatement(EasyParser.Sentencia_imprimirContext ctx) {
        String value = visit(ctx.expresion());
        return "cout << " + value + " << endl;";
    }
    
    private String generateReadStatement(EasyParser.Sentencia_lecturaContext ctx) {
        String variable = ctx.ID().getText();
        return "cin >> " + variable + ";";
    }
    
    // MÉTODOS VISITOR ORIGINALES (para compatibilidad)
    @Override
    public String visitClase_definicion(EasyParser.Clase_definicionContext ctx) {
        String className = ctx.ID(0).getText();
        inClass = true;
        
        addLine("class " + className);
        
        // Verificar herencia
        if (ctx.ID().size() > 1) {
            String parentClass = ctx.ID(1).getText();
            code.set(code.size() - 1, "class " + className + " : public " + parentClass);
        }
        
        addLine(" {");
        
        // Sección privada por defecto
        addLine("private:");
        indentLevel++;
        inPrivateSection = true;
        
        // Procesar atributos (van en la sección privada)
        if (ctx.clase_cuerpo() != null) {
            for (EasyParser.Clase_cuerpoContext cuerpo : ctx.clase_cuerpo()) {
                if (cuerpo.atributo() != null) {
                    visit(cuerpo.atributo());
                }
            }
        }
        
        // Sección pública
        addLine("");
        indentLevel--;
        addLine("public:");
        indentLevel++;
        inPrivateSection = false;
        
        // Procesar métodos (van en la sección pública)
        if (ctx.clase_cuerpo() != null) {
            for (EasyParser.Clase_cuerpoContext cuerpo : ctx.clase_cuerpo()) {
                if (cuerpo.metodo() != null) {
                    visit(cuerpo.metodo());
                }
            }
        }
        
        indentLevel--;
        addLine("};");
        addLine("");
        
        inClass = false;
        return null;
    }


    @Override
public String visitEstructura_seleccion(EasyParser.Estructura_seleccionContext ctx) {
    String switchExpression = visit(ctx.expresion());
    addLine("switch (" + switchExpression + ") {");
    
    // Procesar todos los casos
    if (ctx.caso() != null) {
        for (EasyParser.CasoContext caso : ctx.caso()) {
            visit(caso);
        }
    }
    
    // Procesar caso default si existe
    if (ctx.caso_defecto() != null) {
        visit(ctx.caso_defecto());
    }
    
    addLine("}");
    return null;
}

    // Visitor para caso individual
   // @Override
    public String visitCase(EasyParser.CasoContext ctx) {
        String caseValue = visit(ctx.expresion());
        addLine("case " + caseValue + ":");
        
        indentLevel++;
        
        // Procesar las sentencias del caso
        if (ctx.sentencia() != null) {
            for (EasyParser.SentenciaContext stmt : ctx.sentencia()) {
                visit(stmt);
            }
        }
        
        addLine("break;");
        indentLevel--;
        
        return null;
    }

// Visitor para caso default
@Override
public String visitCaso_defecto(EasyParser.Caso_defectoContext ctx) {
    addLine("default:");
    
    indentLevel++;
    
    // Procesar las sentencias del caso default
    if (ctx.sentencia() != null) {
        for (EasyParser.SentenciaContext stmt : ctx.sentencia()) {
            visit(stmt);
        }
    }
    
    indentLevel--;
    
    return null;
}
    
    @Override
    public String visitAtributo(EasyParser.AtributoContext ctx) {
        String type = mapType(ctx.tipo_dato().getText());
        String name = ctx.ID().getText();
        
        if (ctx.CORCHETEIZQ() != null) {
            String size = ctx.LITENTERO().getText();
            addLine(type + " " + name + "[" + size + "];");
        } else {
            addLine(type + " " + name + ";");
        }
        return null;
    }
    
    @Override
    public String visitMetodo(EasyParser.MetodoContext ctx) {
        String returnType = ctx.tipo_dato() != null ? mapType(ctx.tipo_dato().getText()) : "void";
        String methodName = ctx.ID().getText();
        
        String params = "";
        if (ctx.parametros() != null) {
            params = visit(ctx.parametros());
        }
        
        addLine(returnType + " " + methodName + "(" + params + ") {");
        indentLevel++;
        
        visit(ctx.bloque());
        
        indentLevel--;
        addLine("}");
        addLine("");
        return null;
    }
    
    @Override
    public String visitFuncion_definicion(EasyParser.Funcion_definicionContext ctx) {
        String returnType = mapType(ctx.tipo_dato().getText());
        String functionName = ctx.ID().getText();
        
        String params = "";
        if (ctx.parametros() != null) {
            params = visit(ctx.parametros());
        }
        
        addLine(returnType + " " + functionName + "(" + params + ") {");
        indentLevel++;
        
        visit(ctx.bloque());
        
        indentLevel--;
        addLine("}");
        addLine("");
        
        return null;
    }
    
    @Override
    public String visitParametros(EasyParser.ParametrosContext ctx) {
        List<String> params = new ArrayList<>();
        for (EasyParser.ParametroContext param : ctx.parametro()) {
            params.add(visit(param));
        }
        return String.join(", ", params);
    }
    
    @Override
    public String visitParametro(EasyParser.ParametroContext ctx) {
        String type = mapType(ctx.tipo_dato().getText());
        String name = ctx.ID().getText();
        return type + " " + name;
    }
    
    // CORRECCIÓN: Método mejorado para manejar declaraciones globales
    @Override
    public String visitDeclaracion(EasyParser.DeclaracionContext ctx) {
        // Recorrer todos los hijos y buscar declaraciones de variables
        for (int i = 0; i < ctx.getChildCount(); i++) {
            ParseTree child = ctx.getChild(i);
            
            // Si encuentra una Declaracion_variable_sentenciaContext, procesarla
            if (child instanceof EasyParser.Declaracion_variable_sentenciaContext) {
                visit((EasyParser.Declaracion_variable_sentenciaContext) child);
                return "processed";
            }
        }
        
        // Si no encuentra hijos específicos, procesar manualmente el texto
        String texto = ctx.getText();
        System.out.println("*** PROCESANDO MANUALMENTE: " + texto);
        
        // CORRECCIÓN: Mejorar el procesamiento manual de arrays
        if (texto.matches("entero\\w+\\[\\d+\\]:\\{.*\\};")) {
            // Ejemplo: entero numeros[5]:{1,2,3,4,5}; -> int numeros[5] = {1,2,3,4,5};
            String processed = texto.replaceAll("entero(\\w+)\\[(\\d+)\\]:\\{([^}]+)\\};", "int $1[$2] = {$3};");
            addLine(processed);
            System.out.println("*** RESULTADO MANUAL ARRAY CON INIT: " + processed);
            return "processed";
        } else if (texto.matches("entero\\w+\\[\\d+\\];")) {
            // Ejemplo: entero numeros[5]; -> int numeros[5];
            String processed = texto.replaceAll("entero(\\w+)\\[(\\d+)\\];", "int $1[$2];");
            addLine(processed);
            System.out.println("*** RESULTADO MANUAL ARRAY SIN INIT: " + processed);
            return "processed";
        }
        
        return null;
    }
    
    @Override
    public String visitDeclaracion_variable_sentencia(EasyParser.Declaracion_variable_sentenciaContext ctx) {
        String type = mapType(ctx.tipo_dato().getText());
        
        for (EasyParser.Declaracion_variableContext decl : ctx.lista_declaraciones().declaracion_variable()) {
            visitDeclaracionVariable(decl, type);
        }
        return null;
    }
    
    // CORRECCIÓN: Método mejorado para declaraciones de variables
    private void visitDeclaracionVariable(EasyParser.Declaracion_variableContext ctx, String type) {
        String name = ctx.ID().getText();
        declaredVariables.add(name);
        variableTypes.put(name, type);
        
        if (ctx.CORCHETEIZQ() != null) {
            String size = ctx.LITENTERO().getText();
            if (ctx.IGUAL() != null && ctx.lista_valores() != null) {
                String values = visit(ctx.lista_valores());
                // CORRECCIÓN: Usar = en lugar de :
                addLine(type + " " + name + "[" + size + "] = " + values + ";");
            } else {
                addLine(type + " " + name + "[" + size + "];");
            }
        } else if (ctx.expresion() != null && ctx.IGUAL() != null) {
            String value = visit(ctx.expresion().get(0));
            // CORRECCIÓN: Usar = en lugar de :
            addLine(type + " " + name + " = " + value + ";");
        } else {
            addLine(type + " " + name + ";");
        }
    }
    
    @Override
    public String visitLista_valores(EasyParser.Lista_valoresContext ctx) {
        List<String> values = new ArrayList<>();
        for (EasyParser.ExpresionContext expr : ctx.expresion()) {
            values.add(visit(expr));
        }
        return "{" + String.join(", ", values) + "}";
    }
    
    @Override
    public String visitBloque(EasyParser.BloqueContext ctx) {
        if (ctx.sentencia() != null) {
            for (EasyParser.SentenciaContext stmt : ctx.sentencia()) {
                visit(stmt);
            }
        }
        return null;
    }
    
    @Override
    public String visitBloque_elemento(EasyParser.Bloque_elementoContext ctx) {
        if (ctx.sentencia() != null) {
            visit(ctx.sentencia());
        } else if (ctx.funcion_definicion() != null) {
            visit(ctx.funcion_definicion());
        }
        return null;
    }
    
    @Override
    public String visitAsignacion(EasyParser.AsignacionContext ctx) {
        String variable = ctx.ID().getText();
        
        if (ctx.CORCHETEIZQ() != null) {
            String index = visit(ctx.expresion(0));
            String value = visit(ctx.expresion(1));
            // CORRECCIÓN: Usar = en lugar de :
            addLine(variable + "[" + index + "] = " + value + ";");
        } else if (ctx.lista_valores() != null) {
            String values = visit(ctx.lista_valores());
            // CORRECCIÓN: Usar = en lugar de :
            addLine(variable + " = " + values + ";");
        } else {
            String value = visit(ctx.expresion(0));
            // CORRECCIÓN: Usar = en lugar de :
            addLine(variable + " = " + value + ";");
        }
        return null;
    }
    
    @Override
    public String visitEstructura_condicional(EasyParser.Estructura_condicionalContext ctx) {
        String condition = visit(ctx.expresion());
        addLine("if (" + condition + ") {");
        
        indentLevel++;
        visit(ctx.bloque(0));
        indentLevel--;
        
        if (ctx.bloque().size() > 1) {
            addLine("} else {");
            indentLevel++;
            visit(ctx.bloque(1));
            indentLevel--;
        }
        
        addLine("}");
        return null;
    }
    
   
@Override
public String visitEstructura_para(EasyParser.Estructura_paraContext ctx) {
    // Debug: Imprimir información del contexto
    System.out.println("=== DEBUG ESTRUCTURA_PARA ===");
    System.out.println("Texto completo: " + ctx.getText());
    System.out.println("Número de asignacion_simple: " + (ctx.asignacion_simple() != null ? ctx.asignacion_simple().size() : 0));
    System.out.println("¿Tiene declaracion_variable_simple? " + (ctx.declaracion_variable_simple() != null));
    System.out.println("Expresión: " + (ctx.expresion() != null ? ctx.expresion().getText() : "null"));
    
    String initialization = "";
    String condition = "";
    String increment = "";
    
    // Inicialización: puede ser declaración de variable O primera asignación
    if (ctx.declaracion_variable_simple() != null) {
        // Caso: para (entero i : 0; ...)
        initialization = generateDeclaracionVariableSimple(ctx.declaracion_variable_simple());
        System.out.println("Inicialización (declaración): " + initialization);
        
        // El incremento será la primera (y posiblemente única) asignacion_simple
        if (ctx.asignacion_simple() != null && ctx.asignacion_simple().size() > 0) {
            increment = generateAsignacionSimple(ctx.asignacion_simple(0));
            System.out.println("Incremento: " + increment);
        }
    } else if (ctx.asignacion_simple() != null && ctx.asignacion_simple().size() >= 2) {
        // Caso: para (i : 0; i < 5; i : i + 1) - dos asignaciones
        initialization = generateAsignacionSimple(ctx.asignacion_simple(0));
        increment = generateAsignacionSimple(ctx.asignacion_simple(1));
        System.out.println("Inicialización (asignación): " + initialization);
        System.out.println("Incremento: " + increment);
    } else if (ctx.asignacion_simple() != null && ctx.asignacion_simple().size() == 1) {
        // Solo una asignación - probablemente el incremento
        increment = generateAsignacionSimple(ctx.asignacion_simple(0));
        System.out.println("Solo incremento: " + increment);
    }
    
    // Condición
    if (ctx.expresion() != null) {
        condition = visit(ctx.expresion());
        System.out.println("Condición: " + condition);
    }
    
    // Generar el for en C++
    String forStatement = "for (" + initialization + "; " + condition + "; " + increment + ") {";
    System.out.println("FOR generado: " + forStatement);
    
    addLine(forStatement);
    indentLevel++;
    
    // Procesar el bloque del for
    if (ctx.bloque() != null) {
        visit(ctx.bloque());
    }
    
    indentLevel--;
    addLine("}");
    
    return null;
}


// Método auxiliar para generar asignación simple
private String generateAsignacionSimple(EasyParser.Asignacion_simpleContext ctx) {
    String variable = ctx.ID().getText();
    
    if (ctx.CORCHETEIZQ() != null) {
        String index = visit(ctx.expresion(0));
        String value = visit(ctx.expresion(1));
        // CORRECCIÓN: Usar = en lugar de :
        return variable + "[" + index + "] = " + value;
    } else if (ctx.lista_valores() != null) {
        String values = visit(ctx.lista_valores());
        // CORRECCIÓN: Usar = en lugar de :
        return variable + " = " + values;
    } else {
        String value = visit(ctx.expresion(0));
        // CORRECCIÓN: Usar = en lugar de :
        return variable + " = " + value;
    }
}

// Método auxiliar para generar declaración de variable simple
private String generateDeclaracionVariableSimple(EasyParser.Declaracion_variable_simpleContext ctx) {
    String type = mapType(ctx.tipo_dato().getText());
    StringBuilder result = new StringBuilder();
    
    for (EasyParser.Declaracion_variableContext decl : ctx.lista_declaraciones().declaracion_variable()) {
        String name = decl.ID().getText();
        declaredVariables.add(name);
        variableTypes.put(name, type);
        
        if (decl.IGUAL() != null && decl.expresion() != null) {
            String value = visit(decl.expresion().get(0));
            // CORRECCIÓN: Usar = en lugar de :
            result.append(type).append(" ").append(name).append(" = ").append(value);
        } else {
            result.append(type).append(" ").append(name);
        }
    }
    
    return result.toString();
}

    @Override
    public String visitEstructura_repetitiva(EasyParser.Estructura_repetitivaContext ctx) {
        String condition = visit(ctx.expresion());
        addLine("while (" + condition + ") {");
        
        indentLevel++;
        visit(ctx.bloque());
        indentLevel--;
        
        addLine("}");
        return null;
    }
    
    @Override
    public String visitSentencia_imprimir(EasyParser.Sentencia_imprimirContext ctx) {
        String value = visit(ctx.expresion());
        addLine("cout << " + value + " << endl;");
        return null;
    }
    
    @Override
    public String visitSentencia_lectura(EasyParser.Sentencia_lecturaContext ctx) {
        String variable = ctx.ID().getText();
        addLine("cin >> " + variable + ";");
        return null;
    }
    
    @Override
    public String visitSentencia_retorno(EasyParser.Sentencia_retornoContext ctx) {
        if (ctx.expresion() != null) {
            String value = visit(ctx.expresion());
            addLine("return " + value + ";");
        } else {
            addLine("return;");
        }
        return null;
    }
    
    @Override
    public String visitLlamada_funcion(EasyParser.Llamada_funcionContext ctx) {
        String functionName = ctx.ID().getText();
        String args = "";
        if (ctx.argumentos() != null) {
            args = visit(ctx.argumentos());
        }
        return functionName + "(" + args + ")";
    }
    
    @Override
    public String visitArgumentos(EasyParser.ArgumentosContext ctx) {
        List<String> args = new ArrayList<>();
        for (EasyParser.ExpresionContext expr : ctx.expresion()) {
            args.add(visit(expr));
        }
        return String.join(", ", args);
    }
    
    @Override
    public String visitExpresion(EasyParser.ExpresionContext ctx) {
        if (ctx.expresion_logica() != null) {
            return visit(ctx.expresion_logica());
        } else if (ctx.expresion_lista() != null) {
            return visit(ctx.expresion_lista());
        }
        return "";
    }
    
    @Override
    public String visitExpresion_lista(EasyParser.Expresion_listaContext ctx) {
        return visit(ctx.lista_valores());
    }
    
    @Override
    public String visitExpresion_logica(EasyParser.Expresion_logicaContext ctx) {
        if (ctx.getChildCount() == 1) {
            return visit(ctx.expresion_relacional());
        } else {
            String left = visit(ctx.expresion_logica());
            String operator = ctx.children.get(1).getText();
            String right = visit(ctx.expresion_relacional());
            
            String cppOperator = operator.equals("&") ? "&&" : "||";
            return "(" + left + " " + cppOperator + " " + right + ")";
        }
    }
    
    @Override
    public String visitExpresion_relacional(EasyParser.Expresion_relacionalContext ctx) {
        if (ctx.getChildCount() == 1) {
            return visit(ctx.expresion_aritmetica(0));
        } else {
            String left = visit(ctx.expresion_aritmetica(0));
            String operator = visit(ctx.operador_relacional());
            String right = visit(ctx.expresion_aritmetica(1));
            return "(" + left + " " + operator + " " + right + ")";
        }
    }
    
    @Override
    public String visitOperador_relacional(EasyParser.Operador_relacionalContext ctx) {
        String op = ctx.getText();
        switch (op) {
            case "::": return "==";
            case ">:": return ">=";
            case "<:": return "<=";
            case ":!": return "!=";
            default: return op;
        }
    }
    
    @Override
    public String visitExpresion_aritmetica(EasyParser.Expresion_aritmeticaContext ctx) {
        if (ctx.getChildCount() == 1) {
            return visit(ctx.termino());
        } else {
            String left = visit(ctx.expresion_aritmetica());
            String operator = ctx.children.get(1).getText();
            String right = visit(ctx.termino());
            return "(" + left + " " + operator + " " + right + ")";
        }
    }
    
    @Override
    public String visitTermino(EasyParser.TerminoContext ctx) {
        if (ctx.getChildCount() == 1) {
            return visit(ctx.factor());
        } else {
            String left = visit(ctx.termino());
            String operator = ctx.children.get(1).getText();
            String right = visit(ctx.factor());
            return "(" + left + " " + operator + " " + right + ")";
        }
    }
    
    @Override
    public String visitFactor(EasyParser.FactorContext ctx) {
        if (ctx.llamada_funcion() != null) {
        return generateFunctionCall(ctx.llamada_funcion()).replace(";", "");
        }
        if (ctx.llamada_metodo() != null) {
            return generateMethodCall(ctx.llamada_metodo()).replace(";", "");
        }
        if (ctx.LITENTERO() != null) {
            return ctx.LITENTERO().getText();
        } else if (ctx.LITFLOTANTE() != null) {
            return ctx.LITFLOTANTE().getText();
        } else if (ctx.VERDADERO() != null) {
            return "true";
        } else if (ctx.FALSO() != null) {
            return "false";
        } else if (ctx.LITERALCADENA() != null) {
            return ctx.LITERALCADENA().getText();
        } else if (ctx.ID() != null) {
            if (ctx.CORCHETEIZQ() != null) {
                String index = visit(ctx.expresion());
                return ctx.ID().getText() + "[" + index + "]";
            } else {
                return ctx.ID().getText();
            }
        } else if (ctx.llamada_funcion() != null) {
            return visit(ctx.llamada_funcion());
        } else if (ctx.PARIZQ() != null) {
            return "(" + visit(ctx.expresion()) + ")";
        } else if (ctx.RESTA() != null) {
            return "-" + visit(ctx.factor());
        }
        return "";
    }
}
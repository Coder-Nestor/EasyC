// Generated from Easy.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link EasyParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface EasyVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link EasyParser#bloque_elemento}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBloque_elemento(EasyParser.Bloque_elementoContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyParser#programa}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrograma(EasyParser.ProgramaContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyParser#declaracion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracion(EasyParser.DeclaracionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyParser#lista_declaraciones}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLista_declaraciones(EasyParser.Lista_declaracionesContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyParser#funcion_definicion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncion_definicion(EasyParser.Funcion_definicionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyParser#clase_definicion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClase_definicion(EasyParser.Clase_definicionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyParser#clase_cuerpo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClase_cuerpo(EasyParser.Clase_cuerpoContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyParser#atributo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtributo(EasyParser.AtributoContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyParser#metodo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMetodo(EasyParser.MetodoContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyParser#parametros}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParametros(EasyParser.ParametrosContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyParser#parametro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParametro(EasyParser.ParametroContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyParser#tipo_dato}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo_dato(EasyParser.Tipo_datoContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyParser#sentencia}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSentencia(EasyParser.SentenciaContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyParser#declaracion_variable_sentencia}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracion_variable_sentencia(EasyParser.Declaracion_variable_sentenciaContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyParser#declaracion_variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracion_variable(EasyParser.Declaracion_variableContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyParser#declaracion_variable_simple}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracion_variable_simple(EasyParser.Declaracion_variable_simpleContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyParser#lista_valores}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLista_valores(EasyParser.Lista_valoresContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyParser#asignacion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAsignacion(EasyParser.AsignacionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyParser#asignacion_simple}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAsignacion_simple(EasyParser.Asignacion_simpleContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyParser#estructura_condicional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEstructura_condicional(EasyParser.Estructura_condicionalContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyParser#estructura_seleccion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEstructura_seleccion(EasyParser.Estructura_seleccionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyParser#caso}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaso(EasyParser.CasoContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyParser#caso_defecto}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaso_defecto(EasyParser.Caso_defectoContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyParser#estructura_repetitiva}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEstructura_repetitiva(EasyParser.Estructura_repetitivaContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyParser#estructura_para}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEstructura_para(EasyParser.Estructura_paraContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyParser#bloque}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBloque(EasyParser.BloqueContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyParser#sentencia_imprimir}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSentencia_imprimir(EasyParser.Sentencia_imprimirContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyParser#sentencia_lectura}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSentencia_lectura(EasyParser.Sentencia_lecturaContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyParser#sentencia_retorno}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSentencia_retorno(EasyParser.Sentencia_retornoContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyParser#llamada_metodo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLlamada_metodo(EasyParser.Llamada_metodoContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyParser#llamada_metodo_sentencia}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLlamada_metodo_sentencia(EasyParser.Llamada_metodo_sentenciaContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyParser#argumentos}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgumentos(EasyParser.ArgumentosContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyParser#expresion_primaria}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresion_primaria(EasyParser.Expresion_primariaContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresion(EasyParser.ExpresionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyParser#expresion_lista}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresion_lista(EasyParser.Expresion_listaContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyParser#expresion_logica}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresion_logica(EasyParser.Expresion_logicaContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyParser#expresion_relacional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresion_relacional(EasyParser.Expresion_relacionalContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyParser#operador_relacional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperador_relacional(EasyParser.Operador_relacionalContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyParser#expresion_aritmetica}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresion_aritmetica(EasyParser.Expresion_aritmeticaContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyParser#termino}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTermino(EasyParser.TerminoContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactor(EasyParser.FactorContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyParser#llamada_funcion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLlamada_funcion(EasyParser.Llamada_funcionContext ctx);
}
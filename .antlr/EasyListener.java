// Generated from c:/Users/Yuvini/Downloads/Easy6.0/E/Easy.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link EasyParser}.
 */
public interface EasyListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link EasyParser#bloque_elemento}.
	 * @param ctx the parse tree
	 */
	void enterBloque_elemento(EasyParser.Bloque_elementoContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyParser#bloque_elemento}.
	 * @param ctx the parse tree
	 */
	void exitBloque_elemento(EasyParser.Bloque_elementoContext ctx);
	/**
	 * Enter a parse tree produced by {@link EasyParser#programa}.
	 * @param ctx the parse tree
	 */
	void enterPrograma(EasyParser.ProgramaContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyParser#programa}.
	 * @param ctx the parse tree
	 */
	void exitPrograma(EasyParser.ProgramaContext ctx);
	/**
	 * Enter a parse tree produced by {@link EasyParser#declaracion}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracion(EasyParser.DeclaracionContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyParser#declaracion}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracion(EasyParser.DeclaracionContext ctx);
	/**
	 * Enter a parse tree produced by {@link EasyParser#lista_declaraciones}.
	 * @param ctx the parse tree
	 */
	void enterLista_declaraciones(EasyParser.Lista_declaracionesContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyParser#lista_declaraciones}.
	 * @param ctx the parse tree
	 */
	void exitLista_declaraciones(EasyParser.Lista_declaracionesContext ctx);
	/**
	 * Enter a parse tree produced by {@link EasyParser#funcion_definicion}.
	 * @param ctx the parse tree
	 */
	void enterFuncion_definicion(EasyParser.Funcion_definicionContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyParser#funcion_definicion}.
	 * @param ctx the parse tree
	 */
	void exitFuncion_definicion(EasyParser.Funcion_definicionContext ctx);
	/**
	 * Enter a parse tree produced by {@link EasyParser#clase_definicion}.
	 * @param ctx the parse tree
	 */
	void enterClase_definicion(EasyParser.Clase_definicionContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyParser#clase_definicion}.
	 * @param ctx the parse tree
	 */
	void exitClase_definicion(EasyParser.Clase_definicionContext ctx);
	/**
	 * Enter a parse tree produced by {@link EasyParser#clase_cuerpo}.
	 * @param ctx the parse tree
	 */
	void enterClase_cuerpo(EasyParser.Clase_cuerpoContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyParser#clase_cuerpo}.
	 * @param ctx the parse tree
	 */
	void exitClase_cuerpo(EasyParser.Clase_cuerpoContext ctx);
	/**
	 * Enter a parse tree produced by {@link EasyParser#atributo}.
	 * @param ctx the parse tree
	 */
	void enterAtributo(EasyParser.AtributoContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyParser#atributo}.
	 * @param ctx the parse tree
	 */
	void exitAtributo(EasyParser.AtributoContext ctx);
	/**
	 * Enter a parse tree produced by {@link EasyParser#metodo}.
	 * @param ctx the parse tree
	 */
	void enterMetodo(EasyParser.MetodoContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyParser#metodo}.
	 * @param ctx the parse tree
	 */
	void exitMetodo(EasyParser.MetodoContext ctx);
	/**
	 * Enter a parse tree produced by {@link EasyParser#parametros}.
	 * @param ctx the parse tree
	 */
	void enterParametros(EasyParser.ParametrosContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyParser#parametros}.
	 * @param ctx the parse tree
	 */
	void exitParametros(EasyParser.ParametrosContext ctx);
	/**
	 * Enter a parse tree produced by {@link EasyParser#parametro}.
	 * @param ctx the parse tree
	 */
	void enterParametro(EasyParser.ParametroContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyParser#parametro}.
	 * @param ctx the parse tree
	 */
	void exitParametro(EasyParser.ParametroContext ctx);
	/**
	 * Enter a parse tree produced by {@link EasyParser#tipo_dato}.
	 * @param ctx the parse tree
	 */
	void enterTipo_dato(EasyParser.Tipo_datoContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyParser#tipo_dato}.
	 * @param ctx the parse tree
	 */
	void exitTipo_dato(EasyParser.Tipo_datoContext ctx);
	/**
	 * Enter a parse tree produced by {@link EasyParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void enterSentencia(EasyParser.SentenciaContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void exitSentencia(EasyParser.SentenciaContext ctx);
	/**
	 * Enter a parse tree produced by {@link EasyParser#declaracion_variable_sentencia}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracion_variable_sentencia(EasyParser.Declaracion_variable_sentenciaContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyParser#declaracion_variable_sentencia}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracion_variable_sentencia(EasyParser.Declaracion_variable_sentenciaContext ctx);
	/**
	 * Enter a parse tree produced by {@link EasyParser#declaracion_variable}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracion_variable(EasyParser.Declaracion_variableContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyParser#declaracion_variable}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracion_variable(EasyParser.Declaracion_variableContext ctx);
	/**
	 * Enter a parse tree produced by {@link EasyParser#declaracion_variable_simple}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracion_variable_simple(EasyParser.Declaracion_variable_simpleContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyParser#declaracion_variable_simple}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracion_variable_simple(EasyParser.Declaracion_variable_simpleContext ctx);
	/**
	 * Enter a parse tree produced by {@link EasyParser#lista_valores}.
	 * @param ctx the parse tree
	 */
	void enterLista_valores(EasyParser.Lista_valoresContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyParser#lista_valores}.
	 * @param ctx the parse tree
	 */
	void exitLista_valores(EasyParser.Lista_valoresContext ctx);
	/**
	 * Enter a parse tree produced by {@link EasyParser#asignacion}.
	 * @param ctx the parse tree
	 */
	void enterAsignacion(EasyParser.AsignacionContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyParser#asignacion}.
	 * @param ctx the parse tree
	 */
	void exitAsignacion(EasyParser.AsignacionContext ctx);
	/**
	 * Enter a parse tree produced by {@link EasyParser#asignacion_simple}.
	 * @param ctx the parse tree
	 */
	void enterAsignacion_simple(EasyParser.Asignacion_simpleContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyParser#asignacion_simple}.
	 * @param ctx the parse tree
	 */
	void exitAsignacion_simple(EasyParser.Asignacion_simpleContext ctx);
	/**
	 * Enter a parse tree produced by {@link EasyParser#estructura_condicional}.
	 * @param ctx the parse tree
	 */
	void enterEstructura_condicional(EasyParser.Estructura_condicionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyParser#estructura_condicional}.
	 * @param ctx the parse tree
	 */
	void exitEstructura_condicional(EasyParser.Estructura_condicionalContext ctx);
	/**
	 * Enter a parse tree produced by {@link EasyParser#estructura_seleccion}.
	 * @param ctx the parse tree
	 */
	void enterEstructura_seleccion(EasyParser.Estructura_seleccionContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyParser#estructura_seleccion}.
	 * @param ctx the parse tree
	 */
	void exitEstructura_seleccion(EasyParser.Estructura_seleccionContext ctx);
	/**
	 * Enter a parse tree produced by {@link EasyParser#caso}.
	 * @param ctx the parse tree
	 */
	void enterCaso(EasyParser.CasoContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyParser#caso}.
	 * @param ctx the parse tree
	 */
	void exitCaso(EasyParser.CasoContext ctx);
	/**
	 * Enter a parse tree produced by {@link EasyParser#caso_defecto}.
	 * @param ctx the parse tree
	 */
	void enterCaso_defecto(EasyParser.Caso_defectoContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyParser#caso_defecto}.
	 * @param ctx the parse tree
	 */
	void exitCaso_defecto(EasyParser.Caso_defectoContext ctx);
	/**
	 * Enter a parse tree produced by {@link EasyParser#estructura_repetitiva}.
	 * @param ctx the parse tree
	 */
	void enterEstructura_repetitiva(EasyParser.Estructura_repetitivaContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyParser#estructura_repetitiva}.
	 * @param ctx the parse tree
	 */
	void exitEstructura_repetitiva(EasyParser.Estructura_repetitivaContext ctx);
	/**
	 * Enter a parse tree produced by {@link EasyParser#estructura_para}.
	 * @param ctx the parse tree
	 */
	void enterEstructura_para(EasyParser.Estructura_paraContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyParser#estructura_para}.
	 * @param ctx the parse tree
	 */
	void exitEstructura_para(EasyParser.Estructura_paraContext ctx);
	/**
	 * Enter a parse tree produced by {@link EasyParser#bloque}.
	 * @param ctx the parse tree
	 */
	void enterBloque(EasyParser.BloqueContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyParser#bloque}.
	 * @param ctx the parse tree
	 */
	void exitBloque(EasyParser.BloqueContext ctx);
	/**
	 * Enter a parse tree produced by {@link EasyParser#sentencia_imprimir}.
	 * @param ctx the parse tree
	 */
	void enterSentencia_imprimir(EasyParser.Sentencia_imprimirContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyParser#sentencia_imprimir}.
	 * @param ctx the parse tree
	 */
	void exitSentencia_imprimir(EasyParser.Sentencia_imprimirContext ctx);
	/**
	 * Enter a parse tree produced by {@link EasyParser#sentencia_lectura}.
	 * @param ctx the parse tree
	 */
	void enterSentencia_lectura(EasyParser.Sentencia_lecturaContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyParser#sentencia_lectura}.
	 * @param ctx the parse tree
	 */
	void exitSentencia_lectura(EasyParser.Sentencia_lecturaContext ctx);
	/**
	 * Enter a parse tree produced by {@link EasyParser#sentencia_retorno}.
	 * @param ctx the parse tree
	 */
	void enterSentencia_retorno(EasyParser.Sentencia_retornoContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyParser#sentencia_retorno}.
	 * @param ctx the parse tree
	 */
	void exitSentencia_retorno(EasyParser.Sentencia_retornoContext ctx);
	/**
	 * Enter a parse tree produced by {@link EasyParser#llamada_metodo}.
	 * @param ctx the parse tree
	 */
	void enterLlamada_metodo(EasyParser.Llamada_metodoContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyParser#llamada_metodo}.
	 * @param ctx the parse tree
	 */
	void exitLlamada_metodo(EasyParser.Llamada_metodoContext ctx);
	/**
	 * Enter a parse tree produced by {@link EasyParser#llamada_metodo_sentencia}.
	 * @param ctx the parse tree
	 */
	void enterLlamada_metodo_sentencia(EasyParser.Llamada_metodo_sentenciaContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyParser#llamada_metodo_sentencia}.
	 * @param ctx the parse tree
	 */
	void exitLlamada_metodo_sentencia(EasyParser.Llamada_metodo_sentenciaContext ctx);
	/**
	 * Enter a parse tree produced by {@link EasyParser#argumentos}.
	 * @param ctx the parse tree
	 */
	void enterArgumentos(EasyParser.ArgumentosContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyParser#argumentos}.
	 * @param ctx the parse tree
	 */
	void exitArgumentos(EasyParser.ArgumentosContext ctx);
	/**
	 * Enter a parse tree produced by {@link EasyParser#expresion_primaria}.
	 * @param ctx the parse tree
	 */
	void enterExpresion_primaria(EasyParser.Expresion_primariaContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyParser#expresion_primaria}.
	 * @param ctx the parse tree
	 */
	void exitExpresion_primaria(EasyParser.Expresion_primariaContext ctx);
	/**
	 * Enter a parse tree produced by {@link EasyParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExpresion(EasyParser.ExpresionContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExpresion(EasyParser.ExpresionContext ctx);
	/**
	 * Enter a parse tree produced by {@link EasyParser#expresion_lista}.
	 * @param ctx the parse tree
	 */
	void enterExpresion_lista(EasyParser.Expresion_listaContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyParser#expresion_lista}.
	 * @param ctx the parse tree
	 */
	void exitExpresion_lista(EasyParser.Expresion_listaContext ctx);
	/**
	 * Enter a parse tree produced by {@link EasyParser#expresion_logica}.
	 * @param ctx the parse tree
	 */
	void enterExpresion_logica(EasyParser.Expresion_logicaContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyParser#expresion_logica}.
	 * @param ctx the parse tree
	 */
	void exitExpresion_logica(EasyParser.Expresion_logicaContext ctx);
	/**
	 * Enter a parse tree produced by {@link EasyParser#expresion_relacional}.
	 * @param ctx the parse tree
	 */
	void enterExpresion_relacional(EasyParser.Expresion_relacionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyParser#expresion_relacional}.
	 * @param ctx the parse tree
	 */
	void exitExpresion_relacional(EasyParser.Expresion_relacionalContext ctx);
	/**
	 * Enter a parse tree produced by {@link EasyParser#operador_relacional}.
	 * @param ctx the parse tree
	 */
	void enterOperador_relacional(EasyParser.Operador_relacionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyParser#operador_relacional}.
	 * @param ctx the parse tree
	 */
	void exitOperador_relacional(EasyParser.Operador_relacionalContext ctx);
	/**
	 * Enter a parse tree produced by {@link EasyParser#expresion_aritmetica}.
	 * @param ctx the parse tree
	 */
	void enterExpresion_aritmetica(EasyParser.Expresion_aritmeticaContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyParser#expresion_aritmetica}.
	 * @param ctx the parse tree
	 */
	void exitExpresion_aritmetica(EasyParser.Expresion_aritmeticaContext ctx);
	/**
	 * Enter a parse tree produced by {@link EasyParser#termino}.
	 * @param ctx the parse tree
	 */
	void enterTermino(EasyParser.TerminoContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyParser#termino}.
	 * @param ctx the parse tree
	 */
	void exitTermino(EasyParser.TerminoContext ctx);
	/**
	 * Enter a parse tree produced by {@link EasyParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(EasyParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(EasyParser.FactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link EasyParser#llamada_funcion}.
	 * @param ctx the parse tree
	 */
	void enterLlamada_funcion(EasyParser.Llamada_funcionContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyParser#llamada_funcion}.
	 * @param ctx the parse tree
	 */
	void exitLlamada_funcion(EasyParser.Llamada_funcionContext ctx);
}
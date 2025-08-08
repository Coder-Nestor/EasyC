grammar Easy;

bloque_elemento
    : sentencia
    | funcion_definicion
    ;

programa
    : (declaracion | funcion_definicion | clase_definicion | declaracion_variable_sentencia)* 
      INICIO LLAVEIZQ bloque_elemento* LLAVEDER FIN
    ;

declaracion
    : tipo_dato lista_declaraciones PUNTOYCOMA
    ;

lista_declaraciones
    : declaracion_variable (COMA declaracion_variable)*
    ;

funcion_definicion
    : FUNCION tipo_dato ID PARIZQ parametros? PARDER bloque
    ;

clase_definicion
    : CLASE ID (IGUAL ID)? LLAVEIZQ clase_cuerpo* LLAVEDER
    ;

clase_cuerpo
    : atributo
    | metodo
    ;

atributo
    : tipo_dato ID PUNTOYCOMA
    | tipo_dato ID CORCHETEIZQ LITENTERO CORCHETEDER PUNTOYCOMA
    ;

metodo
    : METODO tipo_dato? ID PARIZQ parametros? PARDER bloque
    ;

parametros
    : parametro (COMA parametro)*
    ;

parametro
    : tipo_dato ID
    ;

tipo_dato
    : ENTERO | FLOTANTE | BOOLEANO | CADENA | ID
    ;

sentencia
    : asignacion
    | estructura_condicional
    | estructura_seleccion
    | estructura_repetitiva
    | estructura_para
    | sentencia_imprimir
    | sentencia_lectura
    | sentencia_retorno
    | bloque
    | llamada_metodo_sentencia
    | declaracion_variable_sentencia
    ;

declaracion_variable_sentencia
    : tipo_dato lista_declaraciones PUNTOYCOMA
    ;


declaracion_variable
    : ID
    | ID IGUAL expresion
    | ID CORCHETEIZQ LITENTERO CORCHETEDER
    | ID CORCHETEIZQ LITENTERO CORCHETEDER IGUAL LLAVEIZQ lista_valores LLAVEDER
    | ID ('[' expresion ']')? (':' expresion)?
    ;

declaracion_variable_simple
    : tipo_dato lista_declaraciones
    ;

lista_valores
    : expresion (COMA expresion)*
    ;

asignacion
    : ID IGUAL expresion PUNTOYCOMA
    | ID PUNTO ID IGUAL expresion PUNTOYCOMA
    | ID CORCHETEIZQ expresion CORCHETEDER IGUAL expresion PUNTOYCOMA
    | ID IGUAL LLAVEIZQ lista_valores LLAVEDER PUNTOYCOMA
    ;

asignacion_simple
    : ID IGUAL expresion
    | ID PUNTO ID IGUAL expresion                  
    | ID CORCHETEIZQ expresion CORCHETEDER IGUAL expresion
    | ID IGUAL LLAVEIZQ lista_valores LLAVEDER
    ;

estructura_condicional
    : SI PARIZQ expresion PARDER bloque (SINO bloque)?
    ;

estructura_seleccion
    : SELECCIONAR PARIZQ expresion PARDER LLAVEIZQ caso+ caso_defecto? LLAVEDER
    ;

caso
    : CASO expresion IGUAL_CASO sentencia* ROMPER PUNTOYCOMA
    ;

caso_defecto
    : DEFECTO IGUAL_CASO sentencia*
    ;

estructura_repetitiva
    : MIENTRAS PARIZQ expresion PARDER bloque
    ;

estructura_para
    : PARA PARIZQ (asignacion_simple | declaracion_variable_simple) PUNTOYCOMA expresion PUNTOYCOMA asignacion_simple PARDER bloque
    ;

bloque
    : LLAVEIZQ sentencia* LLAVEDER
    ;

sentencia_imprimir
    : IMPRIMIR PARIZQ expresion PARDER PUNTOYCOMA
    ;

sentencia_lectura
    : LEER PARIZQ ID PARDER PUNTOYCOMA
    ;

sentencia_retorno
    : RETORNAR expresion? PUNTOYCOMA
    ;

llamada_metodo
    : (ID | SUPER) PUNTO ID PARIZQ argumentos? PARDER
    ;

llamada_metodo_sentencia
    : llamada_metodo PUNTOYCOMA
    ;

argumentos
    : expresion (COMA expresion)*
    ;

expresion_primaria
    : LITENTERO
    | LITFLOTANTE
    | VERDADERO
    | FALSO
    | LITERALCADENA
    | ID
    | ID CORCHETEIZQ expresion CORCHETEDER
    | llamada_funcion
    | llamada_metodo  
    | PARIZQ expresion PARDER
    ;
    
expresion
    : expresion_logica
    | expresion_lista
    ;

expresion_lista
    : LLAVEIZQ lista_valores LLAVEDER
    ;

expresion_logica
    : expresion_relacional
    | expresion_logica Y expresion_relacional
    | expresion_logica O expresion_relacional
    ;

expresion_relacional
    : expresion_aritmetica
    | expresion_aritmetica operador_relacional expresion_aritmetica
    ;

operador_relacional
    : MAYORQUE
    | MENORQUE
    | MAYORIGUAL
    | MENORIGUAL
    | IGUALIGUAL
    | DIFERENTEDE
    ;

expresion_aritmetica
    : termino
    | expresion_aritmetica SUMA termino
    | expresion_aritmetica RESTA termino
    ;

termino
    : factor
    | termino MULTIPLICACION factor
    | termino DIVISION factor
    ;

factor
    : LITENTERO
    | LITFLOTANTE
    | VERDADERO
    | FALSO
    | LITERALCADENA
    | ID
    | ID CORCHETEIZQ expresion CORCHETEDER
    | llamada_funcion
    | llamada_metodo  
    | PARIZQ expresion PARDER
    | RESTA factor
    ;

llamada_funcion
    : ID PARIZQ argumentos? PARDER
    ;

// === LÉXICO ===

INICIO: 'inicio';
FIN: 'fin';
SI: 'si';
SINO: 'sino';
MIENTRAS: 'mientras';
PARA: 'para';
RETORNAR: 'retornar';
LEER: 'leer';
IMPRIMIR: 'imprimir';
SELECCIONAR: 'seleccionar';
CASO: 'caso';
DEFECTO: 'defecto';
ROMPER: 'romper';
CLASE: 'clase';
SUPER: 'super';
METODO: 'metodo';
FUNCION: 'funcion';

ENTERO: 'entero';
FLOTANTE: 'flotante';
BOOLEANO: 'booleano';
CADENA: 'cadena';

VERDADERO: 'verdadero';
FALSO: 'falso';

ID: [a-zA-Z_][a-zA-Z0-9_]*;

LITENTERO: '-'?[0-9]+;
LITFLOTANTE: '-'?[0-9]+('.'[0-9]+)([fF])?;
LITERALCADENA: '"' .*? '"';

IGUALIGUAL: '::';
MAYORIGUAL: '>:';
MENORIGUAL: '<:';
DIFERENTEDE: ':!';
IGUAL: ':';
IGUAL_CASO: '=';

Y: '&';
O: '??';

MAYORQUE: '>';
MENORQUE: '<';

SUMA: '+';
RESTA: '-';
MULTIPLICACION: '*';
DIVISION: '/';

LLAVEIZQ: '{';
LLAVEDER: '}';
PARIZQ: '(';
PARDER: ')';
CORCHETEIZQ: '[';
CORCHETEDER: ']';
PUNTOYCOMA: ';';
COMA: ',';
PUNTO: '.';

COMENTARIO: '/#' .*? '#/' -> skip;
ESPACIOS: [ \t\r\n]+ -> skip;

ERROR: .;
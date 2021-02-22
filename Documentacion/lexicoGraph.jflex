/*codigo de usuario*/
package com.example.practica1.source;
import java_cup.runtime.*;
import static com.example.practica1.source.sym.*;
%%
/*configuracion*/
%class LexicoGraph
%public
%unicode
%line
%column
%cup
dec=[1-9][0-9]*[.]{number}| [0][.]{number}
number=[0-9]+
lineTerminator = \r|\n|\r\n
whiteSpace     = {lineTerminator} | [ \t\f]

%%
/*reglas lexicas*/
/*palabras reservadas*/
<YYINITIAL> "graficar"	{return  new Symbol(GRAFICAR,yyline+1,yycolumn+1);}
<YYINITIAL> "azul"	{return   new Symbol(COLOR,yyline+1,yycolumn+1,yytext());}
<YYINITIAL> "rojo"	{return   new Symbol(COLOR,yyline+1,yycolumn+1,yytext());}
<YYINITIAL> "verde"	{return   new Symbol(COLOR,yyline+1,yycolumn+1,yytext());}
<YYINITIAL> "amarillo"	{return  new Symbol(COLOR,yyline+1,yycolumn+1,yytext());}
<YYINITIAL> "naranja"	{return   new Symbol(COLOR,yyline+1,yycolumn+1,yytext());}
<YYINITIAL> "morado"	{return  new Symbol(COLOR,yyline+1,yycolumn+1,yytext());}
<YYINITIAL> "cafe"	{return   new Symbol(COLOR,yyline+1,yycolumn+1,yytext());}
<YYINITIAL> "negro"	{return   new Symbol(COLOR,yyline+1,yycolumn+1,yytext());}
<YYINITIAL> "linea"	{return   new Symbol(LINEA,yyline+1,yycolumn+1);}
<YYINITIAL> "curva"	{return   new Symbol(CURVA,yyline+1,yycolumn+1);}
<YYINITIAL> "animar"	{return   new Symbol(ANIMAR,yyline+1,yycolumn+1);}
<YYINITIAL> "objeto"	{return   new Symbol(OBJETO,yyline+1,yycolumn+1);}
<YYINITIAL> "anterior"	{return   new Symbol(ANTERIOR,yyline+1,yycolumn+1);}
<YYINITIAL> "circulo"	{return   new Symbol(CIRCULO,yyline+1,yycolumn+1);}
<YYINITIAL> "rectangulo"	{return   new Symbol(RECTANGULO,yyline+1,yycolumn+1);}
<YYINITIAL> "poligono"	{return  new Symbol(POLIGONO,yyline+1,yycolumn+1);}
<YYINITIAL> "cuadrado"	{return  new Symbol(CUADRADO,yyline+1,yycolumn+1);}


/*simbolos de operaciones*/
<YYINITIAL> "+"	{return   new Symbol(SUMA,yyline+1,yycolumn+1, new Token(yyline+1,yycolumn+1));}
<YYINITIAL> "-"	{return   new Symbol(RESTA,yyline+1,yycolumn+1,new Token(yyline+1,yycolumn+1));}
<YYINITIAL> "*"	{return  new Symbol(MULT,yyline+1,yycolumn+1,new Token(yyline+1,yycolumn+1));}
<YYINITIAL> "/"	{return   new Symbol(DIV,yyline+1,yycolumn+1,new Token(yyline+1,yycolumn+1));}
<YYINITIAL> "("	{return   new Symbol(PA1,yyline+1,yycolumn+1,new Token(yyline+1,yycolumn+1));}
<YYINITIAL> ")"	{return  new Symbol(PA2,yyline+1,yycolumn+1,new Token(yyline+1,yycolumn+1));}

/*simbolo*/
<YYINITIAL> ","	{return   new Symbol(COMA,yyline+1,yycolumn+1);}

/*numero*/
<YYINITIAL> {number}|{dec} 	{return   new Symbol(NUMBER,yyline+1,yycolumn+1,yytext());}

/*espacios en blanco*/
<YYINITIAL>{whiteSpace}		{/*nada*/}

[^]                   { return   new Symbol(ERROR,yyline+1,yycolumn+1,yytext());}
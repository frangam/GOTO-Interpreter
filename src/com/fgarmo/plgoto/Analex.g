/*
 * Copyright (C) 2017 Francisco Manuel Garcia Moreno
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 
header{
	package com.fgarmo.plgoto;
}

class Analex extends Lexer;

options{
	importVocab = Anasint;
	k=2;
	charVocabulary='\u0000'..'\uFFFE'; // allow any char but \uFFFF (16 bit -1)
	testLiterals=false;
}

{
	public void uponEOF() throws TokenStreamException, CharStreamException{
        GOTOCompiler.lexicalAnalysisDone = true;
        System.out.println("lexical analysis done");
    }	
}



WHITE : (' '|'\t'|NL) { $setType(Token.SKIP); };

protected NL : (
	("\r\n") => "\r\n" // MS-DOS
	| '\r' // MACINTOSH
	| '\n' // UNIX
) { newline(); }
;

protected COMMENT_ONE_LINE: "//" (~('\n'|'\r'))* { $setType(Token.SKIP); };

protected COMMENT_MULTI_LINES : "/*" ( 
	('*' NL) => '*' NL
	| ('*' ~('/'|'\n'|'\r')) => '*' ~('/'|'\n'|'\r')  //('*' ~('/'|NL)) => '*' ~('/'|NL) this is not possible in ANTLR 
	| NL
	| ~( '\n' | '\r' | '*' ) //~( NL | '*' ) this is not possible in ANTLR
)* "*/" { $setType(Token.SKIP); }
;

protected DIGIT: ('0'..'9');
protected LETTER: ('A'..'Z');

ID:LETTER(DIGIT)*;
//STRING: '"'LETTER(LETTER|DIGIT|'_'|' ')*'"';
NUMBER: (DIGIT)+;
COMMA:',';

ASSIG: "<-";
LSB:'['; //left square bracket
RSB:']'; //right square bracket
//LP:'('; //left parenthesis
//RP:')'; //right parenthesis
//SC:';'; //semicolon
//DOT:'.';
PLUS:'+';
MINUS:'-';

//MUL:'*';
//DIV:'/';
EQUAL: '=';
DISTINCT: "!=";

IF: "IF";
GOTO: "GOTO";
DEFMACRO: "#MACRO";
ENDMACRO: "#ENDMACRO";	
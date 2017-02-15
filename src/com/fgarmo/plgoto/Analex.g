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
	charVocabulary='\u0000'..'\uFFFE'; //allow unicode characters for the future
	testLiterals=false;
}



protected NL: "\n" {newline();};
BTF: (' '|'\t'|NL) {$setType(Token.SKIP);};

protected DIGIT: ('0'..'9');
protected LETTER: ('A'..'Z');//('a'..'z'|'A'..'Z');


ID options {testLiterals=true;}: LETTER(DIGIT)*; //letra (seguida de digito)
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
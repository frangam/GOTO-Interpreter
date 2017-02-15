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
 
//-----------------------------------------------
// Grammar for PL GOTO
//-----------------------------------------------

header{
	package com.fgarmo.plgoto;
}

class Anasint extends Parser; 

options{
   	buildAST=true;   
}

tokens{ 
	PROGRAM;
	MACRO;
	INSTRUCTIONS;
	LEFT_LABEL;
}

// The Program
program: order
	{#program = #(#[PROGRAM,"PROGRAM"], ##);}
;

order: (DEFMACRO)=> macro_def order
	| instructions
	;

instructions: (instruction)*
	{#instructions = #(#[INSTRUCTIONS,"INSTRUCTIONS"], ##);}
;

macro_def: DEFMACRO! instruction (instruction)* ENDMACRO!
	{#macro_def = #(#[MACRO,"MACRO"], ##);}
;

instruction : (ID ASSIG)=> assigment
	| (LSB ID)=> labelled_instruction
	| conditional
	;

basic_instruction: (ID ASSIG)=> assigment
	| conditional
	;
	
assigment: ID ASSIG^ expr
;

conditional: IF^ expr_bool GOTO^ ID
;

labelled_instruction: LSB! ID^ RSB! basic_instruction
	{#labelled_instruction = #(#[LEFT_LABEL,"LEFT LABEL"], ##);}
;

// Expressions
expr: (atom (PLUS | MINUS))=> atom (PLUS^ | MINUS^) expr
	| atom
	;
	
expr_bool : (expr EQUAL)=> expr EQUAL^ expr_bool
	| (expr DISTINCT)=> expr DISTINCT^ expr_bool
	| (expr GREATER)=> ID GREATER^ expr_bool
	| (expr GREATEREQ)=> expr GREATEREQ^ expr_bool
	| (expr LOWER)=> expr LOWER^ expr_bool
	| (expr LOWEREQ)=> expr LOWEREQ^ expr_bool
	| expr
	;

atom: ID
	| NUMBER
	;
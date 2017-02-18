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
   	k=2;  
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

instruction2: assigment
	| conditional
	| labelled_instruction
;

instruction : (ID ASSIG)=> assigment
	| (LSB ID)=> labelled_instruction
	| conditional
	;

basic_instruction: (ID ASSIG)=> assigment
	| conditional
	;
	
assigment: ID ASSIG^ expr_arithm
;

conditional: IF^ expr GOTO^ ID
;

labelled_instruction: LSB! ID^ RSB! basic_instruction
	{#labelled_instruction = #(#[LEFT_LABEL,"LEFT LABEL"], ##);}
;

// Expressions
expr : expr_arithm ((LOWER^ | GREATER^ | LOWEREQ^ | GREATEREQ^ | EQUAL^ | DISTINCT^) expr_arithm)?
;

expr_arithm: atom ((PLUS^ | MINUS^) atom)*
;

//expr_arithm: expr_mult ((PLUS^ | MINUS^) expr_mult)*
//;

//expr_mult: atom ((MUL^ | DIV^) atom)*
//;

atom: NUMBER
	| ID
	;
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
	import java.util.*;
	import java.io.ByteArrayOutputStream;
	import java.io.PrintStream;
	import antlr.*;
}



class Anasint2 extends TreeParser; 

options{
       importVocab = Anasint;
}

{
	//Map<String,AST> assigments = new HashMap<String,AST>(); //for saving all of the assigment
	Map<String,Integer> vars = new HashMap<String,Integer>(); //for saving all of the assigment
	Map<String,AST> leftLabelledInst = new HashMap<String,AST>(); //for letf labelled instructions
	ASTFactory factory = new ASTFactory();
	
	/*void registerAssigment(String var, AST expression){
		assigments.put(var, factory.dupTree(expression));
	}*/
	
	//Print the program result to standard stream
	void printProgramResult(int result){
		ByteArrayOutputStream consoleStorage = new ByteArrayOutputStream();
	    PrintStream newConsole = System.out;
	    System.setOut(new PrintStream(consoleStorage));
	    System.out.println(result);
	    newConsole.println(consoleStorage.toString());
	}
	
	void registerVar(String var, int value){
		System.out.println("Saving " + var +" = "+value); 
		vars.put(var, value);
	}
	
	void registerLeftLabelledInst(String label, AST expression){
		if(!leftLabelledInst.containsKey(label)){
			leftLabelledInst.put(label, expression);
		}
		else{
			//System.out.println("Compilation Error: duplicated label "+label);
			throw new RuntimeException("Compilation Error: duplicated label "+label);	
		}	
	}
	
	int evalVar(AST exp){
		int res = 0;
		AST aux = exp;
		
		//iterate over subexpressions
		while(aux != null){
			switch(aux.getType()){
				//-------------------------------------------
				//Iterate process
				//-------------------------------------------	
				case PLUS:
					res = evalVar(aux.getFirstChild()) + evalVar(aux.getFirstChild().getNextSibling());
					break; 
				case MINUS:
					res = evalVar(aux.getFirstChild()) - evalVar(aux.getFirstChild().getNextSibling());
				break;
			
				//---------------
				//Base Case
				//---------------
				case NUMBER:
					return new Integer(aux.getText()).intValue();
				
				case ID:
					String var = aux.getText();
					if(vars.containsKey(var)){
						res = vars.get(var);
					}
					else{
						res = 0;
					}
					return res;
			}				
				
			aux = aux.getNextSibling();
		}		
		
		return res;	
	}
}



// The Program
program: #(PROGRAM order)
;

order: macro_def order
	| instructions
	;
	
instructions: #(INSTRUCTIONS (instruction[false, false])*)
;

macro_def: #(MACRO instruction[true, false] (instruction[false, true])*)
;

instruction [boolean isMacroDefination, boolean isMacroContent] : a:assigment 
	| b:labelled_instruction 
	| c:conditional 
	;

basic_instruction: assigment
	| conditional
	;
	
assigment {int r;}: #(ASSIG a:ID r=expr_arithm)
	{
		registerVar(a.getText(), r);
		
		if(a.getText().equals("Y")){
			GOTOCompiler.result=r; 
			System.out.println("Setting Y="+r);
		}
		
		System.out.println("Assignment: "+a.toStringTree()+" = "+r);
	}
;

conditional {boolean r;}: #(IF r=a:expr (#(GOTO b:ID)))
	{
		System.out.println("Conditional: "+a.toStringTree()+" = "+r +"GOTO "+b.getText());
	}
;

labelled_instruction: #(LEFT_LABEL (#(a:ID b:basic_instruction)))
	{registerLeftLabelledInst(a.getText(), b);}
;

// Expressions
expr returns [boolean res=false] {int r1, r2;}: #(EQUAL r1=expr_arithm r2=expr_arithm) {res = r1 == r2;} 
	| #(DISTINCT r1=expr_arithm r2=expr_arithm) {res = r1 != r2;} 
	| #(GREATER r1=expr_arithm r2=expr_arithm) {res = r1 > r2;} 
	| #(GREATEREQ r1=expr_arithm r2=expr_arithm) {res = r1 >= r2;} 
	| #(LOWER r1=expr_arithm r2=expr_arithm) {res = r1 < r2;} 
	| #(LOWEREQ r1=expr_arithm r2=expr_arithm) {res = r1 <= r2;} 
	;
	
expr_arithm returns [int res=0] {int r1, r2;}: a: NUMBER {res = new Integer(a.getText());}
	| b:ID {res = evalVar(b);}
	| #(PLUS r1=expr_arithm r2=expr_arithm) {res = r1+r2;}
	| #(MINUS r1=expr_arithm r2=expr_arithm) {res = r1-r2;}
//	| #(MUL r1=expr_arithm r2=expr_arithm) {res = r1*r2;}
//	| #(DIV r1=expr_arithm r2=expr_arithm) {res = r1/r2;}
	;
	


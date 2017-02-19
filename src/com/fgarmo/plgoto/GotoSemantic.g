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
// Semantic Checker of GOTO
//-----------------------------------------------

header{
	package com.fgarmo.plgoto;
	import java.util.*;
	import antlr.*;
}

class GotoSemantic extends TreeParser; 

options{
   	 importVocab = Anasint;  
}


{
	public Map<String, AST[]> labels = new HashMap<String, AST[]>();
	//ASTFactory factory = new ASTFactory();
	
	void registerLeftLabelledInst(String label, AST[] expression){
		if(!labels.containsKey(label)){
			labels.put(label, expression);
		}
		else{
			//System.out.println("Compilation Error: duplicated label "+label);
			throw new RuntimeException("Compilation Error: duplicated label "+label);	
		}	
	}
}

// The Program
check_semantic: #(PROGRAM order)
;

order: (macro_def)*
 	| instructions_block
	;

macro_def: #(MACRO ID_MACRO instructions_block)
;

instructions_block: labelled_instruction
	| basic_instruction (instructions_block)?
;

labelled_instruction: #(BLOCK i:ID_LABEL a:stats (b:labelled_instruction)?)
	{registerLeftLabelledInst(i.getText(), new AST[]{a, b});}
;

stats: (basic_instruction)+
;
  
basic_instruction: #(ASSIG ID_VAR expr_arithm)
	| #(GOTO IF expr ID_LABEL)
	;


// Expressions
expr: 
	#(DISTINCT v1:ID_VAR ZERO)  
	;
	
expr_arithm: 
	#(PLUS v1:ID_VAR ONE) 
	| #(MINUS v2:ID_VAR ONE)
	;
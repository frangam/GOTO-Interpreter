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
	import com.fgarmo.plgoto.node.*;
	import java.util.*;
	import java.io.ByteArrayOutputStream;
	import java.io.PrintStream;
	import antlr.*;
}



class GotoWalker extends TreeParser; 

options{
       importVocab = Anasint;
}

{
	public Map<String, AST[]> labels = new HashMap<String, AST[]>();
	
	Map<String,Integer> vars = new HashMap<String,Integer>(); //for saving all of the assigment
	//ASTFactory factory = new ASTFactory();
	
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
		//System.out.println("Saving " + var +" = "+value); 
		vars.put(var, value);
	}
	
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
walk returns [Node n=null]: #(PROGRAM n=order)
;

order returns [Node n=null] {Node n1;}: (n=macro_def)*
	| n=instructions_block 
	;
	
macro_def returns [Node n=null] {Node n1=null;}: #(MACRO ID_MACRO n=instructions_block)
;


instructions_block returns [Node n=null] {Node n1;}
	: n=labelled_instruction {System.out.println("reading labelling instruction");}
	| n=basic_instruction (n1=instructions_block)?
;

labelled_instruction returns [Node n=null] {Node n1, n2=null;}: 
	#(BLOCK i:ID_LABEL n1=a:stats (n2=b:labelled_instruction)?)
	{
		System.out.println("["+i.getText()+"]"+ a.toStringTree());
		//registerLeftLabelledInst(i.getText(), new AST[]{a, b});
		n = new BlockNode(n1, n2);
	}
;

stats returns [Node n=null] {Node s; List<Node> nodes = new ArrayList<Node>();}
  :  (s=basic_instruction {nodes.add(s);})+ {n = new StatsNode(nodes);}
;

basic_instruction returns [Node n=null] {int r1; boolean r2; Node n1;}
	: #(as:ASSIG a:ID_VAR {registerVar(a.getText(),0);} r1=expr_arithm)
	{
		registerVar(a.getText(), r1);
		
		if(a.getText().equals("Y")){
			GOTOCompiler.result=r1; 
			//System.out.println("Setting Y="+r);
		}
		
		n1 = new AssignmentNode(a.getText(), r1, as);
		//System.out.println(n1.toString());
		n = n1;
	}
	| #(GOTO IF r2=b:expr  c:ID_LABEL) {n=new GotoNode(c.getText(), labels);}
	;
	
// Expressions
expr returns [boolean res=false] {int r1, r2;}: 
	#(DISTINCT v1:ID_VAR ZERO) {res = vars.get(v1.getText()) != 0;} 
	;
	
expr_arithm returns [int res=0]: 
	#(PLUS v1:ID_VAR ONE) {res = vars.get(v1.getText())+1;}
	| #(MINUS v2:ID_VAR ONE) {res = vars.get(v2.getText())-1;}
	;
	


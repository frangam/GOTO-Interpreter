// $ANTLR : "Anasint2.g" -> "Anasint2.java"$

	package com.fgarmo.plgoto;
	import java.util.*;
	import java.io.ByteArrayOutputStream;
	import java.io.PrintStream;
	import antlr.*;

import antlr.TreeParser;
import antlr.Token;
import antlr.collections.AST;
import antlr.RecognitionException;
import antlr.ANTLRException;
import antlr.NoViableAltException;
import antlr.MismatchedTokenException;
import antlr.SemanticException;
import antlr.collections.impl.BitSet;
import antlr.ASTPair;
import antlr.collections.impl.ASTArray;


public class Anasint2 extends antlr.TreeParser       implements Anasint2TokenTypes
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
public Anasint2() {
	tokenNames = _tokenNames;
}

	public final void program(AST _t) throws RecognitionException {
		
		AST program_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			AST __t2 = _t;
			AST tmp1_AST_in = (AST)_t;
			match(_t,PROGRAM);
			_t = _t.getFirstChild();
			order(_t);
			_t = _retTree;
			_t = __t2;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void order(AST _t) throws RecognitionException {
		
		AST order_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case MACRO:
			{
				macro_def(_t);
				_t = _retTree;
				order(_t);
				_t = _retTree;
				break;
			}
			case INSTRUCTIONS:
			{
				instructions(_t);
				_t = _retTree;
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void macro_def(AST _t) throws RecognitionException {
		
		AST macro_def_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			AST __t9 = _t;
			AST tmp2_AST_in = (AST)_t;
			match(_t,MACRO);
			_t = _t.getFirstChild();
			instruction(_t,true, false);
			_t = _retTree;
			{
			_loop11:
			do {
				if (_t==null) _t=ASTNULL;
				if ((_t.getType()==LEFT_LABEL||_t.getType()==ASSIG||_t.getType()==IF)) {
					instruction(_t,false, true);
					_t = _retTree;
				}
				else {
					break _loop11;
				}
				
			} while (true);
			}
			_t = __t9;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void instructions(AST _t) throws RecognitionException {
		
		AST instructions_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			AST __t5 = _t;
			AST tmp3_AST_in = (AST)_t;
			match(_t,INSTRUCTIONS);
			_t = _t.getFirstChild();
			{
			_loop7:
			do {
				if (_t==null) _t=ASTNULL;
				if ((_t.getType()==LEFT_LABEL||_t.getType()==ASSIG||_t.getType()==IF)) {
					instruction(_t,false, false);
					_t = _retTree;
				}
				else {
					break _loop7;
				}
				
			} while (true);
			}
			_t = __t5;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void instruction(AST _t,
		boolean isMacroDefination, boolean isMacroContent
	) throws RecognitionException {
		
		AST instruction_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		AST a = null;
		AST b = null;
		AST c = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case ASSIG:
			{
				a = _t==ASTNULL ? null : (AST)_t;
				assigment(_t);
				_t = _retTree;
				break;
			}
			case LEFT_LABEL:
			{
				b = _t==ASTNULL ? null : (AST)_t;
				labelled_instruction(_t);
				_t = _retTree;
				break;
			}
			case IF:
			{
				c = _t==ASTNULL ? null : (AST)_t;
				conditional(_t);
				_t = _retTree;
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void assigment(AST _t) throws RecognitionException {
		
		AST assigment_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		AST a = null;
		int r;
		
		try {      // for error handling
			AST __t15 = _t;
			AST tmp4_AST_in = (AST)_t;
			match(_t,ASSIG);
			_t = _t.getFirstChild();
			a = (AST)_t;
			match(_t,ID);
			_t = _t.getNextSibling();
			r=expr_arithm(_t);
			_t = _retTree;
			_t = __t15;
			_t = _t.getNextSibling();
			
					registerVar(a.getText(), r);
					
					if(a.getText().equals("Y")){
						GOTOCompiler.result=r; 
						System.out.println("Setting Y="+r);
					}
					
					System.out.println("Assignment: "+a.toStringTree()+" = "+r);
				
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void labelled_instruction(AST _t) throws RecognitionException {
		
		AST labelled_instruction_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		AST a = null;
		AST b = null;
		
		try {      // for error handling
			AST __t21 = _t;
			AST tmp5_AST_in = (AST)_t;
			match(_t,LEFT_LABEL);
			_t = _t.getFirstChild();
			{
			AST __t23 = _t;
			a = _t==ASTNULL ? null :(AST)_t;
			match(_t,ID);
			_t = _t.getFirstChild();
			b = _t==ASTNULL ? null : (AST)_t;
			basic_instruction(_t);
			_t = _retTree;
			_t = __t23;
			_t = _t.getNextSibling();
			}
			_t = __t21;
			_t = _t.getNextSibling();
			registerLeftLabelledInst(a.getText(), b);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void conditional(AST _t) throws RecognitionException {
		
		AST conditional_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		AST a = null;
		AST b = null;
		boolean r;
		
		try {      // for error handling
			AST __t17 = _t;
			AST tmp6_AST_in = (AST)_t;
			match(_t,IF);
			_t = _t.getFirstChild();
			a = _t==ASTNULL ? null : (AST)_t;
			r=expr(_t);
			_t = _retTree;
			{
			AST __t19 = _t;
			AST tmp7_AST_in = (AST)_t;
			match(_t,GOTO);
			_t = _t.getFirstChild();
			b = (AST)_t;
			match(_t,ID);
			_t = _t.getNextSibling();
			_t = __t19;
			_t = _t.getNextSibling();
			}
			_t = __t17;
			_t = _t.getNextSibling();
			
					System.out.println("Conditional: "+a.toStringTree()+" = "+r +"GOTO "+b.getText());
				
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void basic_instruction(AST _t) throws RecognitionException {
		
		AST basic_instruction_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case ASSIG:
			{
				assigment(_t);
				_t = _retTree;
				break;
			}
			case IF:
			{
				conditional(_t);
				_t = _retTree;
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final int  expr_arithm(AST _t) throws RecognitionException {
		int res=0;
		
		AST expr_arithm_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		AST a = null;
		AST b = null;
		int r1, r2;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case NUMBER:
			{
				a = (AST)_t;
				match(_t,NUMBER);
				_t = _t.getNextSibling();
				res = new Integer(a.getText());
				break;
			}
			case ID:
			{
				b = (AST)_t;
				match(_t,ID);
				_t = _t.getNextSibling();
				res = evalVar(b);
				break;
			}
			case PLUS:
			{
				AST __t32 = _t;
				AST tmp8_AST_in = (AST)_t;
				match(_t,PLUS);
				_t = _t.getFirstChild();
				r1=expr_arithm(_t);
				_t = _retTree;
				r2=expr_arithm(_t);
				_t = _retTree;
				_t = __t32;
				_t = _t.getNextSibling();
				res = r1+r2;
				break;
			}
			case MINUS:
			{
				AST __t33 = _t;
				AST tmp9_AST_in = (AST)_t;
				match(_t,MINUS);
				_t = _t.getFirstChild();
				r1=expr_arithm(_t);
				_t = _retTree;
				r2=expr_arithm(_t);
				_t = _retTree;
				_t = __t33;
				_t = _t.getNextSibling();
				res = r1-r2;
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
		return res;
	}
	
	public final boolean  expr(AST _t) throws RecognitionException {
		boolean res=false;
		
		AST expr_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		int r1, r2;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case EQUAL:
			{
				AST __t25 = _t;
				AST tmp10_AST_in = (AST)_t;
				match(_t,EQUAL);
				_t = _t.getFirstChild();
				r1=expr_arithm(_t);
				_t = _retTree;
				r2=expr_arithm(_t);
				_t = _retTree;
				_t = __t25;
				_t = _t.getNextSibling();
				res = r1 == r2;
				break;
			}
			case DISTINCT:
			{
				AST __t26 = _t;
				AST tmp11_AST_in = (AST)_t;
				match(_t,DISTINCT);
				_t = _t.getFirstChild();
				r1=expr_arithm(_t);
				_t = _retTree;
				r2=expr_arithm(_t);
				_t = _retTree;
				_t = __t26;
				_t = _t.getNextSibling();
				res = r1 != r2;
				break;
			}
			case GREATER:
			{
				AST __t27 = _t;
				AST tmp12_AST_in = (AST)_t;
				match(_t,GREATER);
				_t = _t.getFirstChild();
				r1=expr_arithm(_t);
				_t = _retTree;
				r2=expr_arithm(_t);
				_t = _retTree;
				_t = __t27;
				_t = _t.getNextSibling();
				res = r1 > r2;
				break;
			}
			case GREATEREQ:
			{
				AST __t28 = _t;
				AST tmp13_AST_in = (AST)_t;
				match(_t,GREATEREQ);
				_t = _t.getFirstChild();
				r1=expr_arithm(_t);
				_t = _retTree;
				r2=expr_arithm(_t);
				_t = _retTree;
				_t = __t28;
				_t = _t.getNextSibling();
				res = r1 >= r2;
				break;
			}
			case LOWER:
			{
				AST __t29 = _t;
				AST tmp14_AST_in = (AST)_t;
				match(_t,LOWER);
				_t = _t.getFirstChild();
				r1=expr_arithm(_t);
				_t = _retTree;
				r2=expr_arithm(_t);
				_t = _retTree;
				_t = __t29;
				_t = _t.getNextSibling();
				res = r1 < r2;
				break;
			}
			case LOWEREQ:
			{
				AST __t30 = _t;
				AST tmp15_AST_in = (AST)_t;
				match(_t,LOWEREQ);
				_t = _t.getFirstChild();
				r1=expr_arithm(_t);
				_t = _retTree;
				r2=expr_arithm(_t);
				_t = _retTree;
				_t = __t30;
				_t = _t.getNextSibling();
				res = r1 <= r2;
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
		return res;
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"PROGRAM",
		"MACRO",
		"INSTRUCTIONS",
		"LEFT_LABEL",
		"DEFMACRO",
		"ENDMACRO",
		"ID",
		"ASSIG",
		"LSB",
		"IF",
		"GOTO",
		"RSB",
		"LOWER",
		"GREATER",
		"LOWEREQ",
		"GREATEREQ",
		"EQUAL",
		"DISTINCT",
		"PLUS",
		"MINUS",
		"NUMBER"
	};
	
	}
	

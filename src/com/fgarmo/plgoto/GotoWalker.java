// $ANTLR : "GotoWalker.g" -> "GotoWalker.java"$

	package com.fgarmo.plgoto;
	import com.fgarmo.plgoto.node.*;
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


public class GotoWalker extends antlr.TreeParser       implements GotoWalkerTokenTypes
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
	
	
public GotoWalker() {
	tokenNames = _tokenNames;
}

	public final Node  walk(AST _t) throws RecognitionException {
		Node n=null;
		
		AST walk_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			AST __t2 = _t;
			AST tmp1_AST_in = (AST)_t;
			match(_t,PROGRAM);
			_t = _t.getFirstChild();
			n=order(_t);
			_t = _retTree;
			_t = __t2;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
		return n;
	}
	
	public final Node  order(AST _t) throws RecognitionException {
		Node n=null;
		
		AST order_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		Node n1;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case 3:
			case MACRO:
			{
				{
				_loop5:
				do {
					if (_t==null) _t=ASTNULL;
					if ((_t.getType()==MACRO)) {
						n=macro_def(_t);
						_t = _retTree;
					}
					else {
						break _loop5;
					}
					
				} while (true);
				}
				break;
			}
			case BLOCK:
			case ASSIG:
			case GOTO:
			{
				n=instructions_block(_t);
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
		return n;
	}
	
	public final Node  macro_def(AST _t) throws RecognitionException {
		Node n=null;
		
		AST macro_def_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		Node n1=null;
		
		try {      // for error handling
			AST __t7 = _t;
			AST tmp2_AST_in = (AST)_t;
			match(_t,MACRO);
			_t = _t.getFirstChild();
			AST tmp3_AST_in = (AST)_t;
			match(_t,ID_MACRO);
			_t = _t.getNextSibling();
			n=instructions_block(_t);
			_t = _retTree;
			_t = __t7;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
		return n;
	}
	
	public final Node  instructions_block(AST _t) throws RecognitionException {
		Node n=null;
		
		AST instructions_block_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		Node n1;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case BLOCK:
			{
				n=labelled_instruction(_t);
				_t = _retTree;
				System.out.println("reading labelling instruction");
				break;
			}
			case ASSIG:
			case GOTO:
			{
				n=basic_instruction(_t);
				_t = _retTree;
				{
				if (_t==null) _t=ASTNULL;
				switch ( _t.getType()) {
				case BLOCK:
				case ASSIG:
				case GOTO:
				{
					n1=instructions_block(_t);
					_t = _retTree;
					break;
				}
				case 3:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(_t);
				}
				}
				}
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
		return n;
	}
	
	public final Node  labelled_instruction(AST _t) throws RecognitionException {
		Node n=null;
		
		AST labelled_instruction_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		AST i = null;
		AST a = null;
		AST b = null;
		Node n1, n2=null;
		
		try {      // for error handling
			AST __t11 = _t;
			AST tmp4_AST_in = (AST)_t;
			match(_t,BLOCK);
			_t = _t.getFirstChild();
			i = (AST)_t;
			match(_t,ID_LABEL);
			_t = _t.getNextSibling();
			a = _t==ASTNULL ? null : (AST)_t;
			n1=stats(_t);
			_t = _retTree;
			{
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case BLOCK:
			{
				b = _t==ASTNULL ? null : (AST)_t;
				n2=labelled_instruction(_t);
				_t = _retTree;
				break;
			}
			case 3:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
			}
			_t = __t11;
			_t = _t.getNextSibling();
			
					System.out.println("["+i.getText()+"]"+ a.toStringTree());
					//registerLeftLabelledInst(i.getText(), new AST[]{a, b});
					n = new BlockNode(n1, n2);
				
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
		return n;
	}
	
	public final Node  basic_instruction(AST _t) throws RecognitionException {
		Node n=null;
		
		AST basic_instruction_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		AST as = null;
		AST a = null;
		AST b = null;
		AST c = null;
		int r1; boolean r2; Node n1;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case ASSIG:
			{
				AST __t17 = _t;
				as = _t==ASTNULL ? null :(AST)_t;
				match(_t,ASSIG);
				_t = _t.getFirstChild();
				a = (AST)_t;
				match(_t,ID_VAR);
				_t = _t.getNextSibling();
				registerVar(a.getText(),0);
				r1=expr_arithm(_t);
				_t = _retTree;
				_t = __t17;
				_t = _t.getNextSibling();
				
						registerVar(a.getText(), r1);
						
						if(a.getText().equals("Y")){
							GOTOCompiler.result=r1; 
							//System.out.println("Setting Y="+r);
						}
						
						n1 = new AssignmentNode(a.getText(), r1, as);
						//System.out.println(n1.toString());
						n = n1;
					
				break;
			}
			case GOTO:
			{
				AST __t18 = _t;
				AST tmp5_AST_in = (AST)_t;
				match(_t,GOTO);
				_t = _t.getFirstChild();
				AST tmp6_AST_in = (AST)_t;
				match(_t,IF);
				_t = _t.getNextSibling();
				b = _t==ASTNULL ? null : (AST)_t;
				r2=expr(_t);
				_t = _retTree;
				c = (AST)_t;
				match(_t,ID_LABEL);
				_t = _t.getNextSibling();
				_t = __t18;
				_t = _t.getNextSibling();
				n=new GotoNode(c.getText(), labels);
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
		return n;
	}
	
	public final Node  stats(AST _t) throws RecognitionException {
		Node n=null;
		
		AST stats_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		Node s; List<Node> nodes = new ArrayList<Node>();
		
		try {      // for error handling
			{
			int _cnt15=0;
			_loop15:
			do {
				if (_t==null) _t=ASTNULL;
				if ((_t.getType()==ASSIG||_t.getType()==GOTO)) {
					s=basic_instruction(_t);
					_t = _retTree;
					nodes.add(s);
				}
				else {
					if ( _cnt15>=1 ) { break _loop15; } else {throw new NoViableAltException(_t);}
				}
				
				_cnt15++;
			} while (true);
			}
			n = new StatsNode(nodes);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
		return n;
	}
	
	public final int  expr_arithm(AST _t) throws RecognitionException {
		int res=0;
		
		AST expr_arithm_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		AST v1 = null;
		AST v2 = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case PLUS:
			{
				AST __t22 = _t;
				AST tmp7_AST_in = (AST)_t;
				match(_t,PLUS);
				_t = _t.getFirstChild();
				v1 = (AST)_t;
				match(_t,ID_VAR);
				_t = _t.getNextSibling();
				AST tmp8_AST_in = (AST)_t;
				match(_t,ONE);
				_t = _t.getNextSibling();
				_t = __t22;
				_t = _t.getNextSibling();
				res = vars.get(v1.getText())+1;
				break;
			}
			case MINUS:
			{
				AST __t23 = _t;
				AST tmp9_AST_in = (AST)_t;
				match(_t,MINUS);
				_t = _t.getFirstChild();
				v2 = (AST)_t;
				match(_t,ID_VAR);
				_t = _t.getNextSibling();
				AST tmp10_AST_in = (AST)_t;
				match(_t,ONE);
				_t = _t.getNextSibling();
				_t = __t23;
				_t = _t.getNextSibling();
				res = vars.get(v2.getText())-1;
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
		AST v1 = null;
		int r1, r2;
		
		try {      // for error handling
			AST __t20 = _t;
			AST tmp11_AST_in = (AST)_t;
			match(_t,DISTINCT);
			_t = _t.getFirstChild();
			v1 = (AST)_t;
			match(_t,ID_VAR);
			_t = _t.getNextSibling();
			AST tmp12_AST_in = (AST)_t;
			match(_t,ZERO);
			_t = _t.getNextSibling();
			_t = __t20;
			_t = _t.getNextSibling();
			res = vars.get(v1.getText()) != 0;
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
		"BLOCK",
		"DEFMACRO",
		"ID_MACRO",
		"ENDMACRO",
		"LSB",
		"ID_LABEL",
		"RSB",
		"ID_VAR",
		"ASSIG",
		"IF",
		"GOTO",
		"PLUS",
		"MINUS",
		"ONE",
		"DISTINCT",
		"ZERO"
	};
	
	}
	

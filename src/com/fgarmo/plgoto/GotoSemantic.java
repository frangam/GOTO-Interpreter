// $ANTLR : "GotoSemantic.g" -> "GotoSemantic.java"$

	package com.fgarmo.plgoto;
	import java.util.*;
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


public class GotoSemantic extends antlr.TreeParser       implements GotoSemanticTokenTypes
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
public GotoSemantic() {
	tokenNames = _tokenNames;
}

	public final void check_semantic(AST _t) throws RecognitionException {
		
		AST check_semantic_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
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
			case 3:
			case MACRO:
			{
				{
				_loop5:
				do {
					if (_t==null) _t=ASTNULL;
					if ((_t.getType()==MACRO)) {
						macro_def(_t);
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
				instructions_block(_t);
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
			AST __t7 = _t;
			AST tmp2_AST_in = (AST)_t;
			match(_t,MACRO);
			_t = _t.getFirstChild();
			AST tmp3_AST_in = (AST)_t;
			match(_t,ID_MACRO);
			_t = _t.getNextSibling();
			instructions_block(_t);
			_t = _retTree;
			_t = __t7;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void instructions_block(AST _t) throws RecognitionException {
		
		AST instructions_block_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case BLOCK:
			{
				labelled_instruction(_t);
				_t = _retTree;
				break;
			}
			case ASSIG:
			case GOTO:
			{
				basic_instruction(_t);
				_t = _retTree;
				{
				if (_t==null) _t=ASTNULL;
				switch ( _t.getType()) {
				case BLOCK:
				case ASSIG:
				case GOTO:
				{
					instructions_block(_t);
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
	}
	
	public final void labelled_instruction(AST _t) throws RecognitionException {
		
		AST labelled_instruction_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		AST i = null;
		AST a = null;
		AST b = null;
		
		try {      // for error handling
			AST __t11 = _t;
			AST tmp4_AST_in = (AST)_t;
			match(_t,BLOCK);
			_t = _t.getFirstChild();
			i = (AST)_t;
			match(_t,ID_LABEL);
			_t = _t.getNextSibling();
			a = _t==ASTNULL ? null : (AST)_t;
			stats(_t);
			_t = _retTree;
			{
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case BLOCK:
			{
				b = _t==ASTNULL ? null : (AST)_t;
				labelled_instruction(_t);
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
			registerLeftLabelledInst(i.getText(), new AST[]{a, b});
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
				AST __t17 = _t;
				AST tmp5_AST_in = (AST)_t;
				match(_t,ASSIG);
				_t = _t.getFirstChild();
				AST tmp6_AST_in = (AST)_t;
				match(_t,ID_VAR);
				_t = _t.getNextSibling();
				expr_arithm(_t);
				_t = _retTree;
				_t = __t17;
				_t = _t.getNextSibling();
				break;
			}
			case GOTO:
			{
				AST __t18 = _t;
				AST tmp7_AST_in = (AST)_t;
				match(_t,GOTO);
				_t = _t.getFirstChild();
				AST tmp8_AST_in = (AST)_t;
				match(_t,IF);
				_t = _t.getNextSibling();
				expr(_t);
				_t = _retTree;
				AST tmp9_AST_in = (AST)_t;
				match(_t,ID_LABEL);
				_t = _t.getNextSibling();
				_t = __t18;
				_t = _t.getNextSibling();
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
	
	public final void stats(AST _t) throws RecognitionException {
		
		AST stats_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			{
			int _cnt15=0;
			_loop15:
			do {
				if (_t==null) _t=ASTNULL;
				if ((_t.getType()==ASSIG||_t.getType()==GOTO)) {
					basic_instruction(_t);
					_t = _retTree;
				}
				else {
					if ( _cnt15>=1 ) { break _loop15; } else {throw new NoViableAltException(_t);}
				}
				
				_cnt15++;
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void expr_arithm(AST _t) throws RecognitionException {
		
		AST expr_arithm_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		AST v1 = null;
		AST v2 = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case PLUS:
			{
				AST __t22 = _t;
				AST tmp10_AST_in = (AST)_t;
				match(_t,PLUS);
				_t = _t.getFirstChild();
				v1 = (AST)_t;
				match(_t,ID_VAR);
				_t = _t.getNextSibling();
				AST tmp11_AST_in = (AST)_t;
				match(_t,ONE);
				_t = _t.getNextSibling();
				_t = __t22;
				_t = _t.getNextSibling();
				break;
			}
			case MINUS:
			{
				AST __t23 = _t;
				AST tmp12_AST_in = (AST)_t;
				match(_t,MINUS);
				_t = _t.getFirstChild();
				v2 = (AST)_t;
				match(_t,ID_VAR);
				_t = _t.getNextSibling();
				AST tmp13_AST_in = (AST)_t;
				match(_t,ONE);
				_t = _t.getNextSibling();
				_t = __t23;
				_t = _t.getNextSibling();
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
	
	public final void expr(AST _t) throws RecognitionException {
		
		AST expr_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		AST v1 = null;
		
		try {      // for error handling
			AST __t20 = _t;
			AST tmp14_AST_in = (AST)_t;
			match(_t,DISTINCT);
			_t = _t.getFirstChild();
			v1 = (AST)_t;
			match(_t,ID_VAR);
			_t = _t.getNextSibling();
			AST tmp15_AST_in = (AST)_t;
			match(_t,ZERO);
			_t = _t.getNextSibling();
			_t = __t20;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
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
	

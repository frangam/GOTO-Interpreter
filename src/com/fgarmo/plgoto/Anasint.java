// $ANTLR : "Anasint.g" -> "Anasint.java"$

	package com.fgarmo.plgoto;
	import java.util.*;
	import antlr.*;

import antlr.TokenBuffer;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.ANTLRException;
import antlr.LLkParser;
import antlr.Token;
import antlr.TokenStream;
import antlr.RecognitionException;
import antlr.NoViableAltException;
import antlr.MismatchedTokenException;
import antlr.SemanticException;
import antlr.ParserSharedInputState;
import antlr.collections.impl.BitSet;
import antlr.collections.AST;
import java.util.Hashtable;
import antlr.ASTFactory;
import antlr.ASTPair;
import antlr.collections.impl.ASTArray;

public class Anasint extends antlr.LLkParser       implements AnasintTokenTypes
 {

	public Map<String, AST[]> labels = new HashMap<String, AST[]>();
	ASTFactory factory = new ASTFactory();
	
	void registerLeftLabelledInst(String label, AST[] expression){
		if(!labels.containsKey(label)){
			labels.put(label, expression);
		}
		else{
			//System.out.println("Compilation Error: duplicated label "+label);
			throw new RuntimeException("Compilation Error: duplicated label "+label);	
		}	
	}

protected Anasint(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public Anasint(TokenBuffer tokenBuf) {
  this(tokenBuf,2);
}

protected Anasint(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public Anasint(TokenStream lexer) {
  this(lexer,2);
}

public Anasint(ParserSharedInputState state) {
  super(state,2);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

	public final void program() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST program_AST = null;
		
		try {      // for error handling
			order();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				program_AST = (AST)currentAST.root;
				program_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(PROGRAM,"PROGRAM")).add(program_AST));
				currentAST.root = program_AST;
				currentAST.child = program_AST!=null &&program_AST.getFirstChild()!=null ?
					program_AST.getFirstChild() : program_AST;
				currentAST.advanceChildToEnd();
			}
			program_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
		returnAST = program_AST;
	}
	
	public final void order() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST order_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case EOF:
			case DEFMACRO:
			{
				{
				_loop6:
				do {
					if ((LA(1)==DEFMACRO)) {
						macro_def();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop6;
					}
					
				} while (true);
				}
				order_AST = (AST)currentAST.root;
				break;
			}
			case LSB:
			case ID_VAR:
			case IF:
			{
				instructions_block();
				astFactory.addASTChild(currentAST, returnAST);
				order_AST = (AST)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
		returnAST = order_AST;
	}
	
	public final void macro_def() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST macro_def_AST = null;
		
		try {      // for error handling
			match(DEFMACRO);
			AST tmp2_AST = null;
			tmp2_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp2_AST);
			match(ID_MACRO);
			instructions_block();
			astFactory.addASTChild(currentAST, returnAST);
			match(ENDMACRO);
			if ( inputState.guessing==0 ) {
				macro_def_AST = (AST)currentAST.root;
				macro_def_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(MACRO,"MACRO")).add(macro_def_AST));
				currentAST.root = macro_def_AST;
				currentAST.child = macro_def_AST!=null &&macro_def_AST.getFirstChild()!=null ?
					macro_def_AST.getFirstChild() : macro_def_AST;
				currentAST.advanceChildToEnd();
			}
			macro_def_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_1);
			} else {
			  throw ex;
			}
		}
		returnAST = macro_def_AST;
	}
	
	public final void instructions_block() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST instructions_block_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LSB:
			{
				labelled_instruction();
				astFactory.addASTChild(currentAST, returnAST);
				instructions_block_AST = (AST)currentAST.root;
				break;
			}
			case ID_VAR:
			case IF:
			{
				basic_instruction();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case LSB:
				case ID_VAR:
				case IF:
				{
					instructions_block();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case EOF:
				case ENDMACRO:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				instructions_block_AST = (AST)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_2);
			} else {
			  throw ex;
			}
		}
		returnAST = instructions_block_AST;
	}
	
	public final void labelled_instruction() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST labelled_instruction_AST = null;
		Token  i = null;
		AST i_AST = null;
		AST a_AST = null;
		AST b_AST = null;
		
		try {      // for error handling
			match(LSB);
			i = LT(1);
			i_AST = astFactory.create(i);
			astFactory.addASTChild(currentAST, i_AST);
			match(ID_LABEL);
			match(RSB);
			stats();
			a_AST = (AST)returnAST;
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case LSB:
			{
				labelled_instruction();
				b_AST = (AST)returnAST;
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case EOF:
			case ENDMACRO:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			if ( inputState.guessing==0 ) {
				labelled_instruction_AST = (AST)currentAST.root;
				labelled_instruction_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(BLOCK,"BLOCK")).add(labelled_instruction_AST));
				currentAST.root = labelled_instruction_AST;
				currentAST.child = labelled_instruction_AST!=null &&labelled_instruction_AST.getFirstChild()!=null ?
					labelled_instruction_AST.getFirstChild() : labelled_instruction_AST;
				currentAST.advanceChildToEnd();
			}
			labelled_instruction_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_2);
			} else {
			  throw ex;
			}
		}
		returnAST = labelled_instruction_AST;
	}
	
	public final void basic_instruction() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST basic_instruction_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case ID_VAR:
			{
				AST tmp6_AST = null;
				tmp6_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp6_AST);
				match(ID_VAR);
				AST tmp7_AST = null;
				tmp7_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp7_AST);
				match(ASSIG);
				expr();
				astFactory.addASTChild(currentAST, returnAST);
				basic_instruction_AST = (AST)currentAST.root;
				break;
			}
			case IF:
			{
				AST tmp8_AST = null;
				tmp8_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp8_AST);
				match(IF);
				expr();
				astFactory.addASTChild(currentAST, returnAST);
				AST tmp9_AST = null;
				tmp9_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp9_AST);
				match(GOTO);
				AST tmp10_AST = null;
				tmp10_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp10_AST);
				match(ID_LABEL);
				basic_instruction_AST = (AST)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_3);
			} else {
			  throw ex;
			}
		}
		returnAST = basic_instruction_AST;
	}
	
	public final void stats() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST stats_AST = null;
		
		try {      // for error handling
			{
			int _cnt16=0;
			_loop16:
			do {
				if ((LA(1)==ID_VAR||LA(1)==IF)) {
					basic_instruction();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					if ( _cnt16>=1 ) { break _loop16; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt16++;
			} while (true);
			}
			stats_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		returnAST = stats_AST;
	}
	
	public final void expr() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST expr_AST = null;
		
		try {      // for error handling
			boolean synPredMatched23 = false;
			if (((LA(1)==ID_VAR) && (LA(2)==PLUS||LA(2)==MINUS))) {
				int _m23 = mark();
				synPredMatched23 = true;
				inputState.guessing++;
				try {
					{
					match(ID_VAR);
					{
					switch ( LA(1)) {
					case PLUS:
					{
						match(PLUS);
						break;
					}
					case MINUS:
					{
						match(MINUS);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					}
				}
				catch (RecognitionException pe) {
					synPredMatched23 = false;
				}
				rewind(_m23);
inputState.guessing--;
			}
			if ( synPredMatched23 ) {
				AST tmp11_AST = null;
				tmp11_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp11_AST);
				match(ID_VAR);
				{
				switch ( LA(1)) {
				case PLUS:
				{
					AST tmp12_AST = null;
					tmp12_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp12_AST);
					match(PLUS);
					break;
				}
				case MINUS:
				{
					AST tmp13_AST = null;
					tmp13_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp13_AST);
					match(MINUS);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				AST tmp14_AST = null;
				tmp14_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp14_AST);
				match(ONE);
				expr_AST = (AST)currentAST.root;
			}
			else if ((LA(1)==ID_VAR) && (LA(2)==DISTINCT)) {
				AST tmp15_AST = null;
				tmp15_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp15_AST);
				match(ID_VAR);
				AST tmp16_AST = null;
				tmp16_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp16_AST);
				match(DISTINCT);
				AST tmp17_AST = null;
				tmp17_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp17_AST);
				match(ZERO);
				expr_AST = (AST)currentAST.root;
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_5);
			} else {
			  throw ex;
			}
		}
		returnAST = expr_AST;
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
	
	protected void buildTokenTypeASTClassMap() {
		tokenTypeToASTClassMap=null;
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 2L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 130L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 514L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 42498L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { 1538L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = { 108034L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	
	}

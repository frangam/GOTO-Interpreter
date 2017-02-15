// $ANTLR : "Anasint.g" -> "Anasint.java"$

	package com.fgarmo.plgoto;

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

protected Anasint(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public Anasint(TokenBuffer tokenBuf) {
  this(tokenBuf,1);
}

protected Anasint(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public Anasint(TokenStream lexer) {
  this(lexer,1);
}

public Anasint(ParserSharedInputState state) {
  super(state,1);
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
			case DEFMACRO:
			{
				macro_def();
				astFactory.addASTChild(currentAST, returnAST);
				order();
				astFactory.addASTChild(currentAST, returnAST);
				order_AST = (AST)currentAST.root;
				break;
			}
			case EOF:
			case ID:
			case LSB:
			case IF:
			{
				instructions();
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
			instruction();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop10:
			do {
				if ((LA(1)==ID||LA(1)==LSB||LA(1)==IF)) {
					instruction();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop10;
				}
				
			} while (true);
			}
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
	
	public final void instructions() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST instructions_AST = null;
		
		try {      // for error handling
			{
			_loop7:
			do {
				if ((LA(1)==ID||LA(1)==LSB||LA(1)==IF)) {
					instruction();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop7;
				}
				
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				instructions_AST = (AST)currentAST.root;
				instructions_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(INSTRUCTIONS,"INSTRUCTIONS")).add(instructions_AST));
				currentAST.root = instructions_AST;
				currentAST.child = instructions_AST!=null &&instructions_AST.getFirstChild()!=null ?
					instructions_AST.getFirstChild() : instructions_AST;
				currentAST.advanceChildToEnd();
			}
			instructions_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
		returnAST = instructions_AST;
	}
	
	public final void instruction() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST instruction_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case ID:
			{
				assigment();
				astFactory.addASTChild(currentAST, returnAST);
				instruction_AST = (AST)currentAST.root;
				break;
			}
			case LSB:
			{
				labelled_instruction();
				astFactory.addASTChild(currentAST, returnAST);
				instruction_AST = (AST)currentAST.root;
				break;
			}
			case IF:
			{
				conditional();
				astFactory.addASTChild(currentAST, returnAST);
				instruction_AST = (AST)currentAST.root;
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
		returnAST = instruction_AST;
	}
	
	public final void assigment() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST assigment_AST = null;
		
		try {      // for error handling
			AST tmp3_AST = null;
			tmp3_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp3_AST);
			match(ID);
			AST tmp4_AST = null;
			tmp4_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp4_AST);
			match(ASSIG);
			expr();
			astFactory.addASTChild(currentAST, returnAST);
			assigment_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_2);
			} else {
			  throw ex;
			}
		}
		returnAST = assigment_AST;
	}
	
	public final void labelled_instruction() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST labelled_instruction_AST = null;
		
		try {      // for error handling
			match(LSB);
			AST tmp6_AST = null;
			tmp6_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp6_AST);
			match(ID);
			match(RSB);
			basic_instruction();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				labelled_instruction_AST = (AST)currentAST.root;
				labelled_instruction_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(LEFT_LABEL,"LEFT LABEL")).add(labelled_instruction_AST));
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
	
	public final void conditional() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST conditional_AST = null;
		
		try {      // for error handling
			AST tmp8_AST = null;
			tmp8_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp8_AST);
			match(IF);
			expr_bool();
			astFactory.addASTChild(currentAST, returnAST);
			AST tmp9_AST = null;
			tmp9_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp9_AST);
			match(GOTO);
			AST tmp10_AST = null;
			tmp10_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp10_AST);
			match(ID);
			conditional_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_2);
			} else {
			  throw ex;
			}
		}
		returnAST = conditional_AST;
	}
	
	public final void basic_instruction() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST basic_instruction_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case ID:
			{
				assigment();
				astFactory.addASTChild(currentAST, returnAST);
				basic_instruction_AST = (AST)currentAST.root;
				break;
			}
			case IF:
			{
				conditional();
				astFactory.addASTChild(currentAST, returnAST);
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
				recover(ex,_tokenSet_2);
			} else {
			  throw ex;
			}
		}
		returnAST = basic_instruction_AST;
	}
	
	public final void expr() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST expr_AST = null;
		
		try {      // for error handling
			boolean synPredMatched25 = false;
			if (((LA(1)==ID||LA(1)==NUMBER))) {
				int _m25 = mark();
				synPredMatched25 = true;
				inputState.guessing++;
				try {
					{
					atom();
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
					synPredMatched25 = false;
				}
				rewind(_m25);
inputState.guessing--;
			}
			if ( synPredMatched25 ) {
				atom();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case PLUS:
				{
					AST tmp11_AST = null;
					tmp11_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp11_AST);
					match(PLUS);
					break;
				}
				case MINUS:
				{
					AST tmp12_AST = null;
					tmp12_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp12_AST);
					match(MINUS);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				expr();
				astFactory.addASTChild(currentAST, returnAST);
				expr_AST = (AST)currentAST.root;
			}
			else if ((LA(1)==ID||LA(1)==NUMBER)) {
				atom();
				astFactory.addASTChild(currentAST, returnAST);
				expr_AST = (AST)currentAST.root;
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
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
		returnAST = expr_AST;
	}
	
	public final void expr_bool() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST expr_bool_AST = null;
		
		try {      // for error handling
			boolean synPredMatched29 = false;
			if (((LA(1)==ID||LA(1)==NUMBER))) {
				int _m29 = mark();
				synPredMatched29 = true;
				inputState.guessing++;
				try {
					{
					expr();
					match(EQUAL);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched29 = false;
				}
				rewind(_m29);
inputState.guessing--;
			}
			if ( synPredMatched29 ) {
				expr();
				astFactory.addASTChild(currentAST, returnAST);
				AST tmp13_AST = null;
				tmp13_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp13_AST);
				match(EQUAL);
				expr_bool();
				astFactory.addASTChild(currentAST, returnAST);
				expr_bool_AST = (AST)currentAST.root;
			}
			else {
				boolean synPredMatched31 = false;
				if (((LA(1)==ID||LA(1)==NUMBER))) {
					int _m31 = mark();
					synPredMatched31 = true;
					inputState.guessing++;
					try {
						{
						expr();
						match(DISTINCT);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched31 = false;
					}
					rewind(_m31);
inputState.guessing--;
				}
				if ( synPredMatched31 ) {
					expr();
					astFactory.addASTChild(currentAST, returnAST);
					AST tmp14_AST = null;
					tmp14_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp14_AST);
					match(DISTINCT);
					expr_bool();
					astFactory.addASTChild(currentAST, returnAST);
					expr_bool_AST = (AST)currentAST.root;
				}
				else {
					boolean synPredMatched33 = false;
					if (((LA(1)==ID))) {
						int _m33 = mark();
						synPredMatched33 = true;
						inputState.guessing++;
						try {
							{
							expr();
							match(GREATER);
							}
						}
						catch (RecognitionException pe) {
							synPredMatched33 = false;
						}
						rewind(_m33);
inputState.guessing--;
					}
					if ( synPredMatched33 ) {
						AST tmp15_AST = null;
						tmp15_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp15_AST);
						match(ID);
						AST tmp16_AST = null;
						tmp16_AST = astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp16_AST);
						match(GREATER);
						expr_bool();
						astFactory.addASTChild(currentAST, returnAST);
						expr_bool_AST = (AST)currentAST.root;
					}
					else {
						boolean synPredMatched35 = false;
						if (((LA(1)==ID||LA(1)==NUMBER))) {
							int _m35 = mark();
							synPredMatched35 = true;
							inputState.guessing++;
							try {
								{
								expr();
								match(GREATEREQ);
								}
							}
							catch (RecognitionException pe) {
								synPredMatched35 = false;
							}
							rewind(_m35);
inputState.guessing--;
						}
						if ( synPredMatched35 ) {
							expr();
							astFactory.addASTChild(currentAST, returnAST);
							AST tmp17_AST = null;
							tmp17_AST = astFactory.create(LT(1));
							astFactory.makeASTRoot(currentAST, tmp17_AST);
							match(GREATEREQ);
							expr_bool();
							astFactory.addASTChild(currentAST, returnAST);
							expr_bool_AST = (AST)currentAST.root;
						}
						else {
							boolean synPredMatched37 = false;
							if (((LA(1)==ID||LA(1)==NUMBER))) {
								int _m37 = mark();
								synPredMatched37 = true;
								inputState.guessing++;
								try {
									{
									expr();
									match(LOWER);
									}
								}
								catch (RecognitionException pe) {
									synPredMatched37 = false;
								}
								rewind(_m37);
inputState.guessing--;
							}
							if ( synPredMatched37 ) {
								expr();
								astFactory.addASTChild(currentAST, returnAST);
								AST tmp18_AST = null;
								tmp18_AST = astFactory.create(LT(1));
								astFactory.makeASTRoot(currentAST, tmp18_AST);
								match(LOWER);
								expr_bool();
								astFactory.addASTChild(currentAST, returnAST);
								expr_bool_AST = (AST)currentAST.root;
							}
							else {
								boolean synPredMatched39 = false;
								if (((LA(1)==ID||LA(1)==NUMBER))) {
									int _m39 = mark();
									synPredMatched39 = true;
									inputState.guessing++;
									try {
										{
										expr();
										match(LOWEREQ);
										}
									}
									catch (RecognitionException pe) {
										synPredMatched39 = false;
									}
									rewind(_m39);
inputState.guessing--;
								}
								if ( synPredMatched39 ) {
									expr();
									astFactory.addASTChild(currentAST, returnAST);
									AST tmp19_AST = null;
									tmp19_AST = astFactory.create(LT(1));
									astFactory.makeASTRoot(currentAST, tmp19_AST);
									match(LOWEREQ);
									expr_bool();
									astFactory.addASTChild(currentAST, returnAST);
									expr_bool_AST = (AST)currentAST.root;
								}
								else if ((LA(1)==ID||LA(1)==NUMBER)) {
									expr();
									astFactory.addASTChild(currentAST, returnAST);
									expr_bool_AST = (AST)currentAST.root;
								}
								else {
									throw new NoViableAltException(LT(1), getFilename());
								}
								}}}}}
							}
							catch (RecognitionException ex) {
								if (inputState.guessing==0) {
									reportError(ex);
									recover(ex,_tokenSet_4);
								} else {
								  throw ex;
								}
							}
							returnAST = expr_bool_AST;
						}
						
	public final void atom() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST atom_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case ID:
			{
				AST tmp20_AST = null;
				tmp20_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp20_AST);
				match(ID);
				atom_AST = (AST)currentAST.root;
				break;
			}
			case NUMBER:
			{
				AST tmp21_AST = null;
				tmp21_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp21_AST);
				match(NUMBER);
				atom_AST = (AST)currentAST.root;
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
				recover(ex,_tokenSet_5);
			} else {
			  throw ex;
			}
		}
		returnAST = atom_AST;
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
		"PLUS",
		"MINUS",
		"EQUAL",
		"DISTINCT",
		"GREATER",
		"GREATEREQ",
		"LOWER",
		"LOWEREQ",
		"NUMBER"
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
		long[] data = { 13570L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 13826L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 15496706L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { 16384L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = { 15693314L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	
	}

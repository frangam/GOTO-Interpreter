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
			{
			_loop3:
			do {
				if ((LA(1)==ID||LA(1)==LSB||LA(1)==IF)) {
					instruction();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop3;
				}
				
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				program_AST = (AST)currentAST.root;
				program_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(INSTRUCTIONS,"INSTRUCTIONS")).add(program_AST));
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
				recover(ex,_tokenSet_1);
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
			AST tmp1_AST = null;
			tmp1_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1_AST);
			match(ID);
			AST tmp2_AST = null;
			tmp2_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp2_AST);
			match(ASSIG);
			expr();
			astFactory.addASTChild(currentAST, returnAST);
			assigment_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_1);
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
			AST tmp4_AST = null;
			tmp4_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp4_AST);
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
				recover(ex,_tokenSet_1);
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
			AST tmp6_AST = null;
			tmp6_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp6_AST);
			match(IF);
			expr_bool();
			astFactory.addASTChild(currentAST, returnAST);
			AST tmp7_AST = null;
			tmp7_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp7_AST);
			match(GOTO);
			AST tmp8_AST = null;
			tmp8_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp8_AST);
			match(ID);
			conditional_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_1);
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
				recover(ex,_tokenSet_1);
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
			boolean synPredMatched18 = false;
			if (((LA(1)==ID||LA(1)==NUMBER))) {
				int _m18 = mark();
				synPredMatched18 = true;
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
					synPredMatched18 = false;
				}
				rewind(_m18);
inputState.guessing--;
			}
			if ( synPredMatched18 ) {
				atom();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case PLUS:
				{
					AST tmp9_AST = null;
					tmp9_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp9_AST);
					match(PLUS);
					break;
				}
				case MINUS:
				{
					AST tmp10_AST = null;
					tmp10_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp10_AST);
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
				recover(ex,_tokenSet_2);
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
			boolean synPredMatched22 = false;
			if (((LA(1)==ID||LA(1)==NUMBER))) {
				int _m22 = mark();
				synPredMatched22 = true;
				inputState.guessing++;
				try {
					{
					expr();
					match(EQUAL);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched22 = false;
				}
				rewind(_m22);
inputState.guessing--;
			}
			if ( synPredMatched22 ) {
				expr();
				astFactory.addASTChild(currentAST, returnAST);
				AST tmp11_AST = null;
				tmp11_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp11_AST);
				match(EQUAL);
				expr_bool();
				astFactory.addASTChild(currentAST, returnAST);
				expr_bool_AST = (AST)currentAST.root;
			}
			else {
				boolean synPredMatched24 = false;
				if (((LA(1)==ID||LA(1)==NUMBER))) {
					int _m24 = mark();
					synPredMatched24 = true;
					inputState.guessing++;
					try {
						{
						expr();
						match(DISTINCT);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched24 = false;
					}
					rewind(_m24);
inputState.guessing--;
				}
				if ( synPredMatched24 ) {
					expr();
					astFactory.addASTChild(currentAST, returnAST);
					AST tmp12_AST = null;
					tmp12_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp12_AST);
					match(DISTINCT);
					expr_bool();
					astFactory.addASTChild(currentAST, returnAST);
					expr_bool_AST = (AST)currentAST.root;
				}
				else {
					boolean synPredMatched26 = false;
					if (((LA(1)==ID))) {
						int _m26 = mark();
						synPredMatched26 = true;
						inputState.guessing++;
						try {
							{
							expr();
							match(GREATER);
							}
						}
						catch (RecognitionException pe) {
							synPredMatched26 = false;
						}
						rewind(_m26);
inputState.guessing--;
					}
					if ( synPredMatched26 ) {
						AST tmp13_AST = null;
						tmp13_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp13_AST);
						match(ID);
						AST tmp14_AST = null;
						tmp14_AST = astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp14_AST);
						match(GREATER);
						expr_bool();
						astFactory.addASTChild(currentAST, returnAST);
						expr_bool_AST = (AST)currentAST.root;
					}
					else {
						boolean synPredMatched28 = false;
						if (((LA(1)==ID||LA(1)==NUMBER))) {
							int _m28 = mark();
							synPredMatched28 = true;
							inputState.guessing++;
							try {
								{
								expr();
								match(GREATEREQ);
								}
							}
							catch (RecognitionException pe) {
								synPredMatched28 = false;
							}
							rewind(_m28);
inputState.guessing--;
						}
						if ( synPredMatched28 ) {
							expr();
							astFactory.addASTChild(currentAST, returnAST);
							AST tmp15_AST = null;
							tmp15_AST = astFactory.create(LT(1));
							astFactory.makeASTRoot(currentAST, tmp15_AST);
							match(GREATEREQ);
							expr_bool();
							astFactory.addASTChild(currentAST, returnAST);
							expr_bool_AST = (AST)currentAST.root;
						}
						else {
							boolean synPredMatched30 = false;
							if (((LA(1)==ID||LA(1)==NUMBER))) {
								int _m30 = mark();
								synPredMatched30 = true;
								inputState.guessing++;
								try {
									{
									expr();
									match(LOWER);
									}
								}
								catch (RecognitionException pe) {
									synPredMatched30 = false;
								}
								rewind(_m30);
inputState.guessing--;
							}
							if ( synPredMatched30 ) {
								expr();
								astFactory.addASTChild(currentAST, returnAST);
								AST tmp16_AST = null;
								tmp16_AST = astFactory.create(LT(1));
								astFactory.makeASTRoot(currentAST, tmp16_AST);
								match(LOWER);
								expr_bool();
								astFactory.addASTChild(currentAST, returnAST);
								expr_bool_AST = (AST)currentAST.root;
							}
							else {
								boolean synPredMatched32 = false;
								if (((LA(1)==ID||LA(1)==NUMBER))) {
									int _m32 = mark();
									synPredMatched32 = true;
									inputState.guessing++;
									try {
										{
										expr();
										match(LOWEREQ);
										}
									}
									catch (RecognitionException pe) {
										synPredMatched32 = false;
									}
									rewind(_m32);
inputState.guessing--;
								}
								if ( synPredMatched32 ) {
									expr();
									astFactory.addASTChild(currentAST, returnAST);
									AST tmp17_AST = null;
									tmp17_AST = astFactory.create(LT(1));
									astFactory.makeASTRoot(currentAST, tmp17_AST);
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
									recover(ex,_tokenSet_3);
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
				AST tmp18_AST = null;
				tmp18_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp18_AST);
				match(ID);
				atom_AST = (AST)currentAST.root;
				break;
			}
			case NUMBER:
			{
				AST tmp19_AST = null;
				tmp19_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp19_AST);
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
				recover(ex,_tokenSet_4);
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
		"INSTRUCTIONS",
		"LEFT_LABEL",
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
		long[] data = { 834L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 968514L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 1024L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { 980802L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	
	}

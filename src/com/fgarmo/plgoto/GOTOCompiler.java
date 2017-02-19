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

package com.fgarmo.plgoto;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fgarmo.plgoto.node.Node;

import antlr.RecognitionException;
import antlr.TokenStreamException;
import antlr.collections.AST;
import antlr.debug.misc.ASTFrame;

public class GOTOCompiler {
	public static final int LAST_SPECIAL_PARAMETERS = 2;
	public static int result = 0;
	public static boolean lexicalAnalysisDone = false;
	
	
	/**
	 * Parametters (String):
	 * 1st params: all of the several .goto files
	 *  
	 * 2nd LAST param: input vars values (comma separated values): "1,45,2,1890"
	 * LAST param: "true" or "false" for showing or not the AST tree frame window 
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			//first, load several source files, like macros and, finally, the program
			List<InputStream> allFiles = new ArrayList<InputStream>();
			
			//parse input var values to GOTO increment instructions
//			if(args.length>2){
//				String inVarValues = parseGOTOInputVarsValuesToGOTOIncInstructions(args[args.length-LAST_SPECIAL_PARAMETERS]);
//				InputStream stream = new ByteArrayInputStream(inVarValues.getBytes(StandardCharsets.UTF_8));
//				
//				allFiles.add(stream);		
//			}
			
			for(int i=0; i<args.length-LAST_SPECIAL_PARAMETERS; i++){
				FileInputStream fs = new FileInputStream(args[i]);
				allFiles.add(fs);				
			}
			
			//put all in one single input stream
			InputStream is = new SequenceInputStream(Collections.enumeration(allFiles));
			System.out.println("Loaded GOTO files");
			
			
			
			
			//Lexer and parser
			Analex analex = new Analex(is);
			
//			while ( !lexicalAnalysisDone ) {
//	            Token t = analex.nextToken();
//	            System.out.println("Token: "+t);
//	        }
			
			//parser
	        Anasint anasint = new Anasint(analex);
	        anasint.program();
	        AST parserTree = anasint.getAST();
	        
	        //show window with AST
	        boolean showAST = Boolean.valueOf(args[args.length-1]);
	        if(showAST){
		        ASTFrame af = new ASTFrame(args[0], parserTree);
		        af.setVisible(true);
	        }
	        
	        //semantic
	        GotoSemantic semanticChecker = new GotoSemantic();
	        semanticChecker.check_semantic(parserTree);
	        
	        
	        System.out.println("Running program");
	        //compiler
	        GotoWalker walker = new GotoWalker();
	        walker.labels = semanticChecker.labels;
	        Node root = walker.walk(parserTree);
	        root.eval();
	       	
	       	
	       	args[0] = String.valueOf(result);
	       	System.out.println("Program finished. Y = "+result);
		}
		catch(FileNotFoundException e) {
			System.out.println("Error in file");
		}
		catch(RecognitionException e){
		   System.out.println("Error in parser analysis");
		} 
		catch(TokenStreamException e) {
			System.out.println("Error in lexical analysis");
		}
	}
	
	
	/**
	 * Parse all the input variable's values to GOTO increment instructions
	 * @param csv
	 * @return
	 */
	private static String parseGOTOInputVarsValuesToGOTOIncInstructions(String csv){
		String res = "";
		String[] values = csv.split("\\,");
		
		for(int i=0; i<values.length; i++){
			int value = Math.abs(new Integer(values[i]).intValue());
			String index = values.length > 1 ? String.valueOf(i+1) : "";
			String var = "X"+index;
			
			
			for(int j=0; j<value; j++){
				String newLine = System.getProperty("line.separator") ;
				res += var+ "<-" + var +"+"+ 1 + newLine;
			}
		}
		
		return res;
	}
	

}

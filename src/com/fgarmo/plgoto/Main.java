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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import antlr.RecognitionException;
import antlr.TokenStreamException;
import antlr.collections.AST;
import antlr.debug.misc.ASTFrame;

public class Main {

	public static void main(String[] args) {
		try{
			//first, load several source files, like macros and, finally, the program
			List<InputStream> allFiles = new ArrayList<InputStream>();
			for(String a: args){
				allFiles.add(new FileInputStream(a));				
			}
			InputStream is = new SequenceInputStream(Collections.enumeration(allFiles));
			
			//Lexer and parser
			Analex analex = new Analex(is);
	        Anasint anasint = new Anasint(analex);
	        anasint.program();
	        AST a = anasint.getAST();
	        
	        //show window with AST
	        ASTFrame af = new ASTFrame(args[0],a);
	        af.setVisible(true);
	        
	        //compiler
	        Anasint2 anasint2 = new Anasint2();
	       	anasint2.program(a);
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
}

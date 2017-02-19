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

/**
 * Source: http://stackoverflow.com/questions/6780195/goto-statement-in-antlr
 */

package com.fgarmo.plgoto.node;

import java.util.Map;

import com.fgarmo.plgoto.GotoWalker;

import antlr.collections.AST;

public class GotoNode implements Node {
	//-----------------------------------------
    // Fields
    //-----------------------------------------
	private String label;
	private Map<String, AST[]> labels;

	//-----------------------------------------
    // Constructors
    //-----------------------------------------
	public GotoNode(String lbl, Map<String, AST[]> lbls) {
		label = lbl;
		labels = lbls;
	}

	//-----------------------------------------
    // Methods from interfaces
    //-----------------------------------------
	@Override
	public Object eval() {
		AST[] toExecute = labels.get(label);
		try {
			Thread.sleep(1000L);
			GotoWalker walker = new GotoWalker();
			walker.labels = this.labels;
			Node root = walker.basic_instruction(toExecute[0]);
			Object o = root.eval();
			if(o != VOID) {
				return o;
			}
			if(toExecute[1] != null){
				walker = new GotoWalker();
				walker.labels = this.labels;
				System.out.println("Next: "+toExecute[1].toStringTree());
				root = walker.labelled_instruction(toExecute[1]);
				o = root.eval();
				if(o != VOID) {
					return o;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return BREAK;
	}

}

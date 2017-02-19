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

package com.fgarmo.plgoto.node;

import antlr.collections.AST;

public class AssignmentNode implements Node {
	//-----------------------------------------
	// Fields
	//-----------------------------------------
	private String var;
	private int value;
	private AST ast;
	
	//-----------------------------------------
	// Constructors
	//-----------------------------------------
	public AssignmentNode(String var, int value, AST ast) {
		this.var = var;
		this.value = value;
		this.ast = ast;
	}

	//-----------------------------------------
	// Overridden Methods
	//-----------------------------------------
	@Override
	public Object eval() {
		System.out.println(var+"="+value +"["+ast.toStringTree()+"]");
		return VOID;
	}

	@Override
	public String toString() {
		return var+"="+value;
	}
	
	
}

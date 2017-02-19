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

public class BlockNode implements Node {
	//-----------------------------------------
    // Fields
    //-----------------------------------------
	private Node stats;
	private Node child;

	//-----------------------------------------
    // Constructors
    //-----------------------------------------
	public BlockNode(Node ns, Node ch) {
		stats = ns;
		child = ch;
	}

	//-----------------------------------------
    // Methods from interfaces
    //-----------------------------------------
	@Override
	public Object eval() {
		Object o = stats.eval();
		if(o != VOID) {
			return o;
		}
		if(child != null) {
			o = child.eval();
			if(o != VOID) {
				return o;
			}
		}
		return VOID;
	}
}

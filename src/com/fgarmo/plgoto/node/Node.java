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

public interface Node {
	public static final Node VOID = new Node(){public Object eval(){throw new RuntimeException("VOID.eval()");}};
	public static final Node BREAK = new Node(){public Object eval(){throw new RuntimeException("BREAK.eval()");}};
	Object eval();
}

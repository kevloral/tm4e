/**
 *  Copyright (c) 2015-2017 Angelo ZERR.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Initial code from https://github.com/Microsoft/vscode-textmate/
 * Initial copyright Copyright (C) Microsoft Corporation. All rights reserved.
 * Initial license: MIT
 *
 * Contributors:
 *  - Microsoft Corporation: Initial code, written in TypeScript, licensed under MIT license
 *  - Angelo Zerr <angelo.zerr@gmail.com> - translation and adaptation to Java
 */
package org.eclipse.tm4e.core.internal.grammar;

import org.eclipse.tm4e.core.grammar.IToken;
import org.eclipse.tm4e.core.grammar.ITokenizeLineResult;

/**
 * Result of the line tokenization implementation.
 */
final class TokenizeLineResult implements ITokenizeLineResult {

	private final IToken[] tokens;
	private final StateStack ruleStack;

	TokenizeLineResult(final IToken[] tokens, final StateStack ruleStack) {
		this.tokens = tokens;
		this.ruleStack = ruleStack;
	}

	@Override
	public IToken[] getTokens() {
		return tokens;
	}

	@Override
	public StateStack getRuleStack() {
		return ruleStack;
	}
}

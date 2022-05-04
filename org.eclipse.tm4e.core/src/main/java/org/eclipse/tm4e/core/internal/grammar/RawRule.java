/**
 * Copyright (c) 2015-2019 Angelo ZERR.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * Angelo Zerr <angelo.zerr@gmail.com> - initial API and implementation
 */
package org.eclipse.tm4e.core.internal.grammar;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.tm4e.core.internal.parser.PropertySettable;
import org.eclipse.tm4e.core.internal.types.IRawCaptures;
import org.eclipse.tm4e.core.internal.types.IRawRepository;
import org.eclipse.tm4e.core.internal.types.IRawRule;
import org.eclipse.tm4e.core.internal.utils.DeepCloneable;

public class RawRule extends HashMap<String, @Nullable Object>
		implements IRawRule, DeepCloneable, PropertySettable<Object> {

	private static final String APPLY_END_PATTERN_LAST = "applyEndPatternLast";
	private static final String BEGIN = "begin";
	public static final String BEGIN_CAPTURES = "beginCaptures";
	public static final String CAPTURES = "captures";
	private static final String CONTENT_NAME = "contentName";
	private static final String END = "end";
	public static final String END_CAPTURES = "endCaptures";
	private static final String ID = "id";
	private static final String INCLUDE = "include";
	private static final String MATCH = "match";
	private static final String NAME = "name";
	private static final String PATTERNS = "patterns";
	public static final String REPOSITORY = "repository";
	private static final String WHILE = "while";
	public static final String WHILE_CAPTURES = "whileCaptures";

	private static final long serialVersionUID = 1L;

	@Override
	public RawRule deepClone() {
		final var clone = new RawRule();
		for (final var entry : entrySet()) {
			clone.put(entry.getKey(), DeepCloneable.deepClone(entry.getValue()));
		}
		return clone;
	}

	@Nullable
	@Override
	public Integer getId() {
		return (Integer) get(ID);
	}

	@Override
	public void setId(final Integer id) {
		super.put(ID, id);
	}

	@Nullable
	@Override
	public String getName() {
		return (String) get(NAME);
	}

	public void setName(final String name) {
		super.put(NAME, name);
	}

	@Nullable
	@Override
	public String getContentName() {
		return (String) get(CONTENT_NAME);
	}

	@Nullable
	@Override
	public String getMatch() {
		return (String) get(MATCH);
	}

	@Nullable
	@Override
	public IRawCaptures getCaptures() {
		updateCaptures(CAPTURES);
		return (IRawCaptures) get(CAPTURES);
	}

	private void updateCaptures(final String name) {
		final Object captures = get(name);
		if (captures instanceof List) {
			final RawRule rawCaptures = new RawRule();
			int i = 0;
			for (final Object capture : (List<?>) captures) {
				i++;
				rawCaptures.put(Integer.toString(i), capture);
			}
			super.put(name, rawCaptures);
		}
	}

	@Nullable
	@Override
	public String getBegin() {
		return (String) get(BEGIN);
	}

	@Nullable
	@Override
	public String getWhile() {
		return (String) get(WHILE);
	}

	@Nullable
	@Override
	public String getInclude() {
		return (String) get(INCLUDE);
	}

	public void setInclude(@Nullable final String include) {
		super.put(INCLUDE, include);
	}

	@Nullable
	@Override
	public IRawCaptures getBeginCaptures() {
		updateCaptures(BEGIN_CAPTURES);
		return (IRawCaptures) get(BEGIN_CAPTURES);
	}

	@Nullable
	@Override
	public String getEnd() {
		return (String) get(END);
	}

	@Nullable
	@Override
	public IRawCaptures getEndCaptures() {
		updateCaptures(END_CAPTURES);
		return (IRawCaptures) get(END_CAPTURES);
	}

	@Nullable
	@Override
	public IRawCaptures getWhileCaptures() {
		updateCaptures(WHILE_CAPTURES);
		return (IRawCaptures) get(WHILE_CAPTURES);
	}

	@Nullable
	@Override
	@SuppressWarnings("unchecked")
	public Collection<IRawRule> getPatterns() {
		return (Collection<IRawRule>) get(PATTERNS);
	}

	public void setPatterns(final @Nullable Collection<IRawRule> patterns) {
		super.put(PATTERNS, patterns);
	}

	@Nullable
	@Override
	public IRawRepository getRepository() {
		return (IRawRepository) get(REPOSITORY);
	}

	@Override
	public boolean isApplyEndPatternLast() {
		final Object applyEndPatternLast = get(APPLY_END_PATTERN_LAST);
		if (applyEndPatternLast == null) {
			return false;
		}
		if (applyEndPatternLast instanceof Boolean) {
			return (Boolean) applyEndPatternLast;
		}
		if (applyEndPatternLast instanceof Integer) {
			return ((Integer) applyEndPatternLast) == 1;
		}
		return false;
	}

	@Override
	public void setProperty(final String name, final Object value) {
		put(name, value);
	}
}
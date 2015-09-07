/*
 * Copyright 2014 Black Pepper Software
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.co.blackpepper.support.date.test;

import java.util.Date;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.joda.time.DateTime;

import static com.google.common.base.Preconditions.checkNotNull;

public final class LenientDateMatcher extends TypeSafeMatcher<Date> {
	
	private static final int LENIENT_MINUTES = 1;
	
	private final Date expected;

	private LenientDateMatcher(Date expected) {
		this.expected = checkNotNull(expected, "expected");
	}

	public static LenientDateMatcher withinOneMinute(Date expected) {
		return new LenientDateMatcher(expected);
	}
	
	@Override
	public void describeTo(Description description) {
		description.appendValue(expected);
	}
	
	@Override
	protected boolean matchesSafely(Date actual) {
		Date earliestExpected = new DateTime(expected.getTime())
			.minusMinutes(LENIENT_MINUTES)
			.toDate();
		
		return actual.compareTo(earliestExpected) >= 0
			&& actual.compareTo(expected) <= 0;
	}

	public Date getExpected() {
		return expected;
	}
}

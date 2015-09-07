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

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import static uk.co.blackpepper.support.date.Dates.newDate;
import static uk.co.blackpepper.support.date.test.LenientDateMatcher.withinOneMinute;

public class LenientDateMatcherTest {

	@Test
	public void withinOneMinuteReturnsMatcherWithProperties() {
		Date expectedDate = new Date();
		
		LenientDateMatcher actual = withinOneMinute(expectedDate);
		
		assertThat(actual.getExpected(), is(expectedDate));
	}
	
	@Test
	public void matchesSafelyWithDateInRangeReturnsTrue() {
		Date expected = newDate(2000, 1, 1, 0, 0, 30);
		Date actual = newDate(2000, 1, 1, 0, 0, 0);
		
		assertThat(withinOneMinute(expected).matchesSafely(actual), is(true));
	}

	@Test
	public void matchesSafelyWithDateAtRangeStartReturnsTrue() {
		Date expected = newDate(2000, 1, 1, 0, 1, 0);
		Date actual = newDate(2000, 1, 1, 0, 0, 0);
		
		assertThat(withinOneMinute(expected).matchesSafely(actual), is(true));
	}
	
	@Test
	public void matchesSafelyWithDateAtRangeEndReturnsTrue() {
		Date expected = newDate(2000, 1, 1, 0, 0, 0);
		Date actual = newDate(2000, 1, 1, 0, 0, 0);
		
		assertThat(withinOneMinute(expected).matchesSafely(actual), is(true));
	}

	@Test
	public void matchesSafelyWithDateBeforeRangeStartReturnsFalse() {
		Date expected = newDate(2000, 1, 1, 0, 2, 0);
		Date actual = newDate(2000, 1, 1, 0, 0, 0);
		
		assertThat(withinOneMinute(expected).matchesSafely(actual), is(false));
	}

	@Test
	public void matchesSafelyWithDateAfterRangeEndReturnsFalse() {
		Date expected = newDate(2000, 1, 1, 0, 0, 0);
		Date actual = newDate(2000, 1, 1, 0, 0, 1);
		
		assertThat(withinOneMinute(expected).matchesSafely(actual), is(false));
	}
}

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

package telran.time;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

public class DateTimeOperations {
	public static LocalDate nextFriday13() {
		LocalDate nextOrSameFriday = LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
		while (nextOrSameFriday.getDayOfMonth() != 13)
			nextOrSameFriday = nextOrSameFriday.plusWeeks(1);
		return nextOrSameFriday;
	}

	public static int workingDays(LocalDate from, LocalDate to, DayOfWeek[] daysOff) {
		int weekdays = 0;
		if (from.isEqual(to)) {
			return weekdays;
		}
		int workingdays = (int)ChronoUnit.DAYS.between(from, to);
		if (daysOff.length == 0) {
			return workingdays;
		}

		while (from.isBefore(to)) {
			for (int i = 0; i < daysOff.length; i++) {
				if (from.getDayOfWeek().equals(daysOff[i])) {
					weekdays++;
					break;
				}
			}
			from = from.plusDays(1);
		}
		
		return workingdays- weekdays;
	}

	static public ZonedDateTime getDateTimeZoneFromMilli(long timeInMilli, String zoneId) {
		return ZonedDateTime.ofInstant(Instant.ofEpochMilli(timeInMilli), ZoneId.of(zoneId));
	}
}

package outofbounds.badges.conditions

import java.util.Calendar;
import java.util.List;

import org.hibernate.util.CalendarComparator;

import outofbounds.User;

class ConnectedCondition implements BadgeCondition {
	private static final int SECOND = 1000
	private static final int MINUTE = 60 * SECOND
	private static final int HOUR = 60 * MINUTE
	private static final int DAY = 24 * HOUR
	private static final int YEAR = 365 * DAY

	int neededValue
	int calendarFormat

	public ConnectedCondition() {
		this.neededValue = 1
		calendarFormat = DAY
	}

	public ConnectedCondition(int neededValue, String format) {
		this.neededValue = neededValue
		switch(format) {
			case "YEAR": calendarFormat = YEAR; break
			case "DAY": calendarFormat = DAY; break
			case "HOUR": calendarFormat = HOUR; break
			case "MINUTE": calendarFormat = MINUTE; break
			case "SECOND": calendarFormat = SECOND; break
			default: calendarFormat = DAY
		}
	}

	/**
	 * Ex : neededValue = 3 and format = HOUR
	 * check will return true if user has signed up for at least 3 hours
	 */
	@Override
	public boolean check(User user) {
		return user.dateSignUp >= (new Date().getTime() + calendarFormat * neededValue);
	}

	@Override
	public List<String> getParameters() {
		ArrayList<String> listParameters = new ArrayList<String>()
		listParameters.add(neededValue)
		listParameters.add(calendarFormat)
		return listParameters
	}

	@Override
	public void setParameters(List<String> parameters) {
		if (parameters != null) {
			neededValue = Integer.parseInt(parameters[0])
			calendarFormat = Integer.parseInt(parameters[1])
		}
	}

}

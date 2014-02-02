package outOfBounds

import java.util.Calendar;
import java.util.List;

import org.hibernate.util.CalendarComparator;

import outofbounds.User;

class ConnectedCondition implements BadgeCondition {
	
	enum CalendarFormat {
		YEAR,
		HOUR
	}

	int neededValue
	int calendarFormat
	
	public ConnectedCondition() {
		this.neededValue = 1
		calendarFormat = Calendar.YEAR
	}
	
	public ConnectedCondition(int neededValue, CalendarFormat format) {
		this.neededValue = neededValue
		switch(format) {
			case CalendarFormat.YEAR: calendarFormat = Calendar.YEAR; break
			case CalendarFormat.HOUR: calendarFormat = Calendar.HOUR; break
			default: calendarFormat = Calendar.YEAR
		}
	}

	/**
	 * Ex : neededValue = 3 and format = HOUR
	 * check will return true if user has signed up for at least 3 hours
	 */
	@Override
	public boolean check(User user) {
		Calendar c = Calendar.getInstance();
		Calendar c1 = Calendar.getInstance();
		c.setTime(new Date());
		c1.setTime(user.dateSignUp);
		//throw new RuntimeException("check " + (c.get(calendarFormat) - c1.get(calendarFormat)).toString())
		return c.get(calendarFormat) - c1.get(calendarFormat) >= neededValue;
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

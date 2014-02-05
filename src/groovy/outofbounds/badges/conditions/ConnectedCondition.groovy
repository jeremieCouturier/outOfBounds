package outofbounds

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
		Calendar currentCalendar = Calendar.getInstance()
		currentCalendar.setTime(new Date())
		currentCalendar.add(calendarFormat, neededValue)
		Calendar userCalendar = Calendar.getInstance()
		userCalendar.setTime(user.dateSignUp)
		//throw new RuntimeException("check " + user.username + " " + calendarFormat + " " + (currentCalendar.compareTo(userCalendar) >= 0).toString())
		return userCalendar.compareTo(currentCalendar) >= 0;
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

package outofbounds.badges.conditions

import outofbounds.User

public interface BadgeCondition {
	boolean check(User user)
	List<String> getParameters()
	List<String> getParametersNames()
	void setParameters(List<String> parameters)
}

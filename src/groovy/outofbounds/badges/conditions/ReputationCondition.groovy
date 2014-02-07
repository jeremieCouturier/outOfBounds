package outofbounds.badges.conditions

import outofbounds.User;

class ReputationCondition implements BadgeCondition {

	int neededValue
	
	public ReputationCondition() {
		this.neededValue = 1
	}
	
	public ReputationCondition(int neededValue) {
		this.neededValue = neededValue
	}

	/**
	 * Ex : neededValue = 3
	 * check will return true if user has at least 3 in reputation
	 */
	@Override
	public boolean check(User user) {
		if (user == null) return false
		//throw new RuntimeException("check reputation " + user.username + " " + neededValue)
		return user.reputation >= neededValue
	}

	@Override
	public List<String> getParameters() {
		ArrayList<String> listParameters = new ArrayList<String>()
		listParameters.add(neededValue)
		return listParameters
	}

	@Override
	public List<String> getParametersNames() {
		return ["neededValue:int"]
	}

	@Override
	public void setParameters(List<String> parameters) {
		if (parameters != null && parameters.size() > 0) {
			neededValue = Integer.parseInt(parameters[0])
		}
	}

}

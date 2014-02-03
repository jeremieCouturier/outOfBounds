package outofbounds

import outofbounds.User;

class ReputationCondition implements BadgeCondition {

	int neededValue
	
	public ConnectedCondition() {
		this.neededValue = 1
	}
	
	public ConnectedCondition(int neededValue) {
		this.neededValue = neededValue
	}

	/**
	 * Ex : neededValue = 3
	 * check will return true if user has at least 3 in reputation
	 */
	@Override
	public boolean check(User user) {
		return false;
	}

	@Override
	public List<String> getParameters() {
		ArrayList<String> listParameters = new ArrayList<String>()
		listParameters.add(neededValue)
		return listParameters
	}

	@Override
	public void setParameters(List<String> parameters) {
		if (parameters != null && parameters.size() > 0) {
			neededValue = Integer.parseInt(parameters[0])
		}
	}

}

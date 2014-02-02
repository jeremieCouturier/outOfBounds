package outOfBounds

import outofbounds.User;

class QuestionNumberCondition implements BadgeCondition {

	int neededValue
	
	public QuestionNumberCondition() {
		this.neededValue = 1
	}
	
	public QuestionNumberCondition(int neededValue) {
		this.neededValue = neededValue
	}

	/**
	 * Ex : neededValue = 3
	 * check will return true if user has at least 3 in reputation
	 */
	@Override
	public boolean check(User user) {
		return user.questions.size() >= neededValue;
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

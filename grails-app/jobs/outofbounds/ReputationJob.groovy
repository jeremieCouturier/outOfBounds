package outofbounds

import outofbounds.User;

class ReputationJob {

	def UserService userService
	
  static triggers = {
    simple repeatInterval: 10000l // execute job once in 10 seconds
  }

  /**
   * For each user, add badges that verify the badge's conditions
   */
  def execute() {
    for (User user : userService.getAllUsers()) {
      userService.updateReputation(user)
    }
  }
}

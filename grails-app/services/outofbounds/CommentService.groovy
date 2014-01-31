package outofbounds

import grails.transaction.Transactional

@Transactional
class CommentService {

    def serviceMethod() {

    }
	
	def delete(Comment comment)
	{
		def father_post = comment.post

		father_post.removeFromComments(comment)
		comment.delete()
		father_post.save(FailOnError: true)
	}
	
	/*def editAnswer(int answer_id, String text)
	{
		def answer = Answer.findById(answer_id)
	
		answer.text = text
		answer.save(failOnError: true)
	
		return answer
	}*/
	
	/*def create(int post_id, String text, User user)
	{
		def post = Post.findById(post_id)
		
		Comment comment = new Comment(
			post:post,
			text:text,
			user:user
		).save(failOnError: true)

		return comment
	}*/
	
	/*def delete(int answer_id)
	{
		def answer = Answer.findById(answer_id)
		def question = answer.question

		question.removeFromAnswers(answer)
		answer.delete()
		question.save(FailOnError: true)

		return question
	}*/
}

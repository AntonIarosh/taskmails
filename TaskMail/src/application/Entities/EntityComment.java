package application.Entities;

public class EntityComment {

	private int idComment;
	private String comment;
	
	public EntityComment() {
		this.idComment = 0;
		this.comment = null;
	}
	
	public EntityComment(int _idComment, String _comment) {
		this.idComment = _idComment;
		this.comment = _comment;
	}
	
	public int getIdComment() {
		return idComment;
	}
	public void setIdComment(int idComment) {
		this.idComment = idComment;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
}

package application.Entities;

public class EntityLink {
	private int idLink;
	private String link;
	
	public EntityLink() {
		this.setIdLink(0);
		this.setLink(null);
	}
	
	public EntityLink(int _idLink, String _link) {
		this.setIdLink(_idLink);
		this.setLink(_link);
	}

	public int getIdLink() {
		return idLink;
	}

	public void setIdLink(int idLink) {
		this.idLink = idLink;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
}

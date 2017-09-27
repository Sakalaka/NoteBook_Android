package bean;

public class Note {
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	String notename,username,title,note_content;
	public Note(){
		
	}
	public Note(String notename,String username,String title,String note_content) {
		this.notename=notename;
		this.username=username;
		this.title=title;
		this.note_content=note_content;
	}
	/**
	 * @return the notename
	 */
	public String getNotename() {
		return notename;
	}

	/**
	 * @param notename the notename to set
	 */
	public void setNotename(String notename) {
		this.notename = notename;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the note_content
	 */
	public String getNote_content() {
		return note_content;
	}

	/**
	 * @param note_content the note_content to set
	 */
	public void setNote_content(String note_content) {
		this.note_content = note_content;
	}


}

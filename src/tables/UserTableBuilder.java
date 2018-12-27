package tables;

/**
 * This class is needed to build tables when searching. eg. searching by Id. Id gets stored here and used elswhere.
 */
public class UserTableBuilder
{

	private String id;
	private String username;
		
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}

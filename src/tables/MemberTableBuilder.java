package tables;

/**
 * This class is needed to build tables when searching. eg. searching by Id. Id gets stored here and used elswhere.
 */
public class MemberTableBuilder 
{
	
	private String id;
	private String memberName;
	private String address;
	private String fine;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFine() {
		return fine;
	}
	public void setFine(String fine) {
		this.fine = fine;
	}
}

package tables;

/**
 * This class is needed to build tables when searching. eg. searching by Id. Id gets stored here and used elswhere.
 */
public class TransactionTableBuilder {
	
	private int transId;
	private int bookId;
	private int memberId;
	
	public int getTransId() {
		return transId;
	}
	public void setTransId(int transId) {
		this.transId = transId;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
}

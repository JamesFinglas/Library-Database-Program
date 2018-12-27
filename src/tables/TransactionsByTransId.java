package tables;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import cards.sixTransactionsPanel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import com.mysql.jdbc.Statement;

import database.DatabaseConnection;

/**
 * This is a table builder for searching books by Author
 * @author Cathal Murphy & James Finglas
 */
public class TransactionsByTransId extends JPanel
{
	
	private boolean isResultSetEmpty = false;
	private int id = sixTransactionsPanel.builderInstance.getTransId();
	JTable table;

	/**
	 * This method creates the table structure
	 */
	public TransactionsByTransId()
	{
		
		this.setLayout(new GridLayout());
		Font font1 = new Font("Courier", Font.BOLD,17);
		
		JPanel tableContainer = new JPanel(new GridLayout());
			String[] columnNames = {"Transaction Id", "Book Id", "User Id", "Member Id", "Fees","Borrow Date", "Due Date", "Return Date"};
			
			int gaurd = 0;
			
			try
			{	
				
				Object[][] data = transactionsData();
				gaurd = 1;
				table = new JTable(data, columnNames);
				table.setBackground(new JButton().getBackground());
				JTableHeader header = table.getTableHeader();
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				table.setFont(font1);
				tableContainer.add(table);
			
			} catch (ClassNotFoundException e) 
			{
				
				e.printStackTrace();
			
			} catch (SQLException e) 
			{
				
				e.printStackTrace();
			
			}
			
			if(gaurd == 0)
			{
				
				Object[][] data = {{"Transaction Id", "Book Id", "User Id", "Member Id", "Fees","Borrow Date", "Due Date", "Return Date"},{"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR","ERROR","ERROR"}};
				table = new JTable(data, columnNames);
				table.setBackground(new JButton().getBackground());
				JTableHeader header = table.getTableHeader();
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				table.setFont(font1);
				tableContainer.add(table);
			
			}
			
			if(isResultSetEmpty)
			{
				
				TransactionsTable instance = new TransactionsTable();
				this.add(instance);
			
			}
			else
			{
				
				tableContainer.setBackground(new Color(76,156,0));   
		   		JScrollPane scrollPane = new JScrollPane(tableContainer,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		   		this.setPreferredSize(new Dimension(600,200));
		   		resizeColumnWidth(table);
		   		this.setBorder(BorderFactory.createEmptyBorder());
		   		this.add(scrollPane);
			
			}
	}
		
	/**
	 * This method retrieves the data to be put into the table.
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public  Object[][] transactionsData() throws ClassNotFoundException, SQLException
	{
		
		Statement stmt = DatabaseConnection.startConnection();
		String strSelect = "select * from transactions WHERE transaction_Id = " + id;
		ResultSet rset = stmt.executeQuery(strSelect);

		int transactionCount = 0;
		int rowCount = 0;
		
		while(rset.next())
		{
			
			transactionCount++;
		
		}
		
		Object[][] transactions = new Object[transactionCount+1][8];
		
		while(rset.previous())
		{
			
			// Reset the result Set
		
		}
		
		transactions[rowCount][0] = " Transaction Id ";
		transactions[rowCount][1] = " Book Id ";
		transactions[rowCount][2] = " User Id ";
		transactions[rowCount][3] = " Member Id ";
		transactions[rowCount][4] = "  Fees  ";
		transactions[rowCount][5] = " Borrow Date ";
		transactions[rowCount][6] = "  Due Date  ";
		transactions[rowCount][7] = " Return Date ";
		
		rowCount++;
		
		while(rset.next())
		{
			
			int transactionId = rset.getInt("transaction_Id");
			int bookId = rset.getInt("book_Id");
			int userId = rset.getInt("user_Id");
			int memberId = rset.getInt("member_Id");
			int fees = rset.getInt("fees");
			
			String borrowDate = rset.getString("borrow_Date");
			String dueDate = rset.getString("due_Date");
			String returnDate = rset.getString("return_Date");
			
			transactions[rowCount][0] = transactionId;
			transactions[rowCount][1] = bookId;
			transactions[rowCount][2] = userId;
			transactions[rowCount][3] = memberId;
			transactions[rowCount][4] = "€" + fees;
			transactions[rowCount][5] = borrowDate;
			transactions[rowCount][6] = dueDate;
			transactions[rowCount][7] = returnDate;
			rowCount++;
		
		}
		
		stmt.close();
		
		if(transactionCount == 0)
		{
			
			JOptionPane.showMessageDialog(null, "No records found","Result",JOptionPane.INFORMATION_MESSAGE);	
			isResultSetEmpty = true;
		
		}
		
		return transactions;
	
	}
	
	/**
	 * This method allows the table columns to stretch to fit its contents
	 * @param table
	 */
	public void resizeColumnWidth(JTable table) 
	{
	    final TableColumnModel columnModel = table.getColumnModel();
	    for (int column = 0; column < table.getColumnCount(); column++) 
	    {
	        int width = 15; // Min width
	        for (int row = 0; row < table.getRowCount(); row++) 
	        {
	            TableCellRenderer renderer = table.getCellRenderer(row, column);
	            Component comp = table.prepareRenderer(renderer, row, column);
	            width = Math.max(comp.getPreferredSize().width +1 , width);
	            columnModel.getColumn(column).setPreferredWidth(width);
	            
	        }
		}
	}
}
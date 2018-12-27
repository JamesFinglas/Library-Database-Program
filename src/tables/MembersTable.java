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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import com.mysql.jdbc.Statement;

import database.DatabaseConnection;

/**
 * This is a table builder for all members
 * @author Cathal Murphy & James Finglas
 */
public class MembersTable extends JPanel
{
	JTable table;

	/**
	 * This method is the table constructor
	 */
	public MembersTable()
	{
		
		this.setLayout(new GridLayout());
		Font font1 = new Font("Courier", Font.BOLD,17);

		JPanel tableContainer = new JPanel(new GridLayout());
			String[] columnNames = {"ID","MemberName","Address","Fine","Card Id"};
			
			int gaurd = 0;
			
			try
			{	
				
				Object[][] data = memberData();
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
				
				Object[][] data = {{"ID","MemberName","Address","Fine","Card Id"},{"ERROR","ERROR","ERROR","ERROR","ERROR"}};
				table = new JTable(data, columnNames);
				table.setBackground(new JButton().getBackground());
				JTableHeader header = table.getTableHeader();
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				table.setFont(font1);
				tableContainer.add(table);
			
			}

   		tableContainer.setBackground(new Color(76,156,0));
   		JScrollPane scrollPane = new JScrollPane(tableContainer,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
   		this.setPreferredSize(new Dimension(600,200));
   		resizeColumnWidth(table);
   		this.setBorder(BorderFactory.createEmptyBorder());
   		this.add(scrollPane);
	}
			
	/**
	 * This method retrieves the data to be put into the table.
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Object[][] memberData() throws ClassNotFoundException, SQLException
	{
		
		Statement stmt = DatabaseConnection.startConnection();
		String strSelect = "select * from Members";
		ResultSet rset = stmt.executeQuery(strSelect);

		int userCount = 0;
		int rowCount = 0;
		
		while(rset.next())
		{
			
			userCount++;
		
		}
		
		Object[][] users = new Object[userCount+1][5];
		
		while(rset.previous())
		{
			
			// Reset the result Set
		
		}
		
		users[rowCount][0] = "ID";
		users[rowCount][1] = "MemberName";
		users[rowCount][2] = "Address";
		users[rowCount][3] = "Fine";
		users[rowCount][4] = "Card Id";
		rowCount++;
		
		while(rset.next())
		{
			
			int book_Id = rset.getInt("member_Id");
			String memberName = rset.getString("member_Name");
			String memberAddress = rset.getString("member_Address");
			String fine = "€ " +  rset.getString("member_Fine");
			String card_Id = rset.getString("card_Id");
			users[rowCount][0] = book_Id;
			users[rowCount][1] = memberName;
			users[rowCount][2] = memberAddress;
			users[rowCount][3] = fine;
			users[rowCount][4] = card_Id;
			rowCount++;
		
		}
		
		stmt.close();
		return users;
	
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
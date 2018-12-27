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


import database.DatabaseConnection;

/**
 * This is a table builder for all books
 * @author Cathal Murphy & James Finglas
 */
public class BookTable extends JPanel
{
	
	JTable table;
	
	/**
	 * This method is the table constructor
	 */
	public BookTable()
	{
		
		this.setLayout(new GridLayout());
		Font font1 = new Font("Courier", Font.BOLD,17);

		JPanel tableContainer = new JPanel(new GridLayout());
			String[] columnNames = {"ID", "Title", "Author", "Publisher", "OnLoan"};
			
			int gaurd = 0;
			
			try
			{
				
				Object[][] data = booksData();
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
				
				Object[][] data = {{"ID", "Title", "Author", "Publisher","OnLoan"},{"ERROR","ERROR","ERROR","ERROR","ERROR"}};
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
	public static Object[][] booksData() throws ClassNotFoundException, SQLException
	{
		
		Statement stmt = DatabaseConnection.startConnection();
		String strSelect = "select * from Books";
		ResultSet rset = stmt.executeQuery(strSelect);

		int userCount = 0;
		int rowCount = 0;
		
		while(rset.next())
		{
			
			userCount++;
		
		}
		
		Object[][] books = new Object[userCount+1][5];
		
		while(rset.previous())
		{
			
			// Reset the result Set
		
		}
		
		books[rowCount][0] = "ID";
		books[rowCount][1] = "Title";
		books[rowCount][2] = "Author";
		books[rowCount][3] = "Publisher";
		books[rowCount][4] = "OnLoan";
		rowCount++;
		
		while(rset.next())
		{
			
			int book_Id = rset.getInt("book_Id");
			String title = rset.getString("title");
			String author = rset.getString("author");
			String publisher = rset.getString("publisher");
			String onLoan = rset.getString("onLoan");
			books[rowCount][0] = book_Id;
			books[rowCount][1] = title;
			books[rowCount][2] = author;
			books[rowCount][3] = publisher;
			books[rowCount][4] = onLoan;
			rowCount++;
		
		}
		
		stmt.close();
		return books;
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

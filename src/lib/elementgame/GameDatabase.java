package lib.elementgame;

import lib.element.ElementTable;
import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

@SuppressLint("NewApi")
public class GameDatabase extends SQLiteOpenHelper
{	
// < Fields
	private ElementTable[] tables;
	
	private SQLiteDatabase myDb;
	private SQLiteStatement sqlInsert;
	private Cursor cursor;

// > End Fields

// < Construct
	public GameDatabase(Context context, ElementTable[] tables) 
	{
		super(context, "AGD_DB", null, 2);
		this.tables = tables;
		
		init();
	}
	
	public GameDatabase(Context context) 
	{
		super(context, "AGD_DB", null, 2);
	}

// > End Construct

// < Other Methods
	
	private void init() 
	{
		for (ElementTable table : tables) 
		{
			if(!isTableExist(table._tableName))
			{
				createTable(table);
				insertData(table);
			}
		}
	}
	
	private void openConnection() throws SQLException
	{
		myDb = this.getWritableDatabase();
	}
	
	private void closeConnection() 
	{
		myDb.close();
	}
	
	public boolean isTableExist(String tableName) 
	{
		openConnection();
		try
		{
			myDb.rawQuery("SELECT * FROM " + tableName, null);
		}
		catch(SQLException ex)
		{
			System.out.println("Table "+ tableName +" is not exists");
			closeConnection();
			return false;
		}
		
		System.out.println("Table "+ tableName +" is exists");
		closeConnection();
		return true;
	}

	public boolean execSQL(String query)
	{
		openConnection();
		System.out.println(query);
		try 
		{
			myDb.execSQL(query);
			closeConnection();
			return true;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		closeConnection();
		return false;
	}
	
	public boolean execInsert(String query) 
	{
		openConnection();
		System.out.println(query);
		try 
		{
			sqlInsert = myDb.compileStatement(query);
			sqlInsert.executeInsert();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			closeConnection();
			System.out.println("Gagal query");
			return false;
		}
		
		closeConnection();
		return true;
	}
	
	public boolean reset() 
	{
		boolean status = false;
		
		for (ElementTable table : tables) 
		{
			status = reset(table);
		}
		
		return status;
	}
	
	public boolean reset(ElementTable table) 
	{
		boolean status1 = false, status2 = false, status3 = false;

		status1 = dropTable(table);
		status2 = createTable(table);
		status3 = insertData(table);
		
		return status1 && status2 && status3;
	}
	
	public void print(int tableIndex) 
	{
		print(tables[tableIndex]);
	}
	
	public void print(ElementTable table)
	{
		print(table._tableName);
	}
	
	public void print(String tableName)
	{
		String[][] data = getData(tableName);
		
		System.out.println("Data " + tableName);
		System.out.println("=================================");
		for(int i = 0; i < data.length; i++)
		{
			for (int j = 0; j < data[i].length; j++) 
			{
				System.out.println("[" + i + "][" + j + "] : " + data[i][j]);
			}
		}
	}

// > End Other Methods
	
//
//CIUS D2

// < Create
	
	public boolean createTable(int tableIndex) 
	{
		return createTable(tables[tableIndex]);
	}
	
	public boolean createTable(ElementTable table) 
	{
		String name = table._tableName;
		String[] colName = table._colNames;
		
		return createTable(name, colName);
	}
	
	public boolean createTable(String tabName, String[] colName)
	{
		String query = "CREATE TABLE IF NOT EXISTS " + tabName + " (";
		
		for (int i = 0; i < colName.length; i++) 
		{
			query += colName[i] + " VARCHAR(255)";
			
			if(i < colName.length - 1)
				query += ",";
		}
		
		query += ")";
		
		return execSQL(query);
	}
	
 
// > End Create

// < Insert
	public boolean insertData(ElementTable table) 
	{
		String[] values = table._initValues;
		
		return insertData(table, values);
	}
	
	public boolean insertData(ElementTable table, String[] values) 
	{
		String name = table._tableName;
		String[] colNames = table._colNames;
		
		return insertData(name, colNames, values);
	}
	
	public boolean insertData(int tabIndex, String[] values) 
	{
		return insertData(tables[tabIndex]._tableName, tables[tabIndex]._colNames, values);
	}
	
	public boolean insertData(String tabName, String[] colNames, String[] values) 
	{
		for (int i = 0; i < values.length; i++) 
		{
			values[i] = values[i].replace("'", "\\'");
		}
		
		String query = "INSERT INTO " + tabName + " (";
		
		for (int i = 0; i < colNames.length; i++) 
		{
			query += colNames[i];
			
			if(i < colNames.length - 1)
				query += ",";
		}
		
		query += ") VALUES (";
		

		for (int i = 0; i < values.length; i++) 
		{			
			query += "'" + values[i] + "'";
			
			if(i < values.length - 1)
				query += ",";
		}
		
		query += ")";
		
		return execInsert(query);
	}
	
// > End Insert
	
// < Update
	
	public boolean updateData(int tableIndex, int[] colIndexToSet, String[] values, String whereClause) 
	{
		return updateData(tables[tableIndex], colIndexToSet, values, whereClause);
	}
	
	public boolean updateData(ElementTable table, int[] colIndexToSet, String[] values, String whereClause) 
	{
		String[] cols = new String[colIndexToSet.length];
		
		for (int i = 0; i < cols.length; i++) 
		{
			cols[i] = table._colNames[colIndexToSet[i]];
		}
		
		return updateData(table._tableName, cols, values, whereClause);
	}
	
	public boolean updateData(String tableName, String[] colNameToSet, String[] values, String whereClause) 
	{
		
		for (int i = 0; i < values.length; i++) 
		{
			values[i] = values[i].replace("'", "\\'");
		}
		
		String query = "UPDATE " + tableName + " SET ";
		
		for (int i = 0; i < colNameToSet.length; i++) 
		{
			query += colNameToSet[i] + " = '" + values[i] + "'";
			
			if(i < colNameToSet.length - 1)
				query += ", ";
		}
		
		query += " " + whereClause;
		
		return execInsert(query);
	}
	
// > End Update

// < Select
	
	public String[] getDataColumn(int tableIndex, int columnIndex) 
	{
		return getDataColumn(tables[tableIndex], columnIndex);
	}
	
	public String[] getDataColumn(ElementTable table, int columnIndex) 
	{
		return getDataColumn(table._tableName, columnIndex);
	}
	
	public String[] getDataColumn(String tableName, int columnIndex) 
	{
		String data[][] = getData(tableName);
		
		String[] col 	= new String[data.length];
		
		for (int i = 0; i < data.length; i++) 
		{
			for (int j = 0; j < data[i].length; j++) 
			{
				col[i] = data[i][columnIndex];
			}
		}
		
		return col;
	}
	
	public String[] getDataRow(int tableIndex, int rowIndex) 
	{
		return getDataRow(tables[tableIndex], rowIndex);
	}
	
	public String[] getDataRow(ElementTable table, int rowIndex) 
	{
		return getDataRow(table._tableName, rowIndex);
	}
	
	public String[] getDataRow(String tableName, int rowIndex) 
	{
		String[][] data = getData(tableName);
		
		String[] row 	= new String[data[rowIndex].length];
		
		for (int i = 0; i < data.length; i++) 
		{
			for (int j = 0; j < data[i].length; j++) 
			{
				row[j] = data[rowIndex][j];
			}
		}
		
		return row;
	}
	
	public String getData(int tableIndex, int row, int col)
	{
		return getData(tables[tableIndex])[row][col];
	}
	
	public String getData(ElementTable table, int row, int col)
	{
		return getData(table._tableName)[row][col];
	}
	
	public String getData(String tableName, int row, int col)
	{
		return getData(tableName)[row][col];
	}
	
	public String[][] getData(int tableIndex)
	{
		return getData(tables[tableIndex]);
	}
	
	public String[][] getData(ElementTable table)
	{
		String name = table._tableName;
		
		String[] col = table._colNames;
		
		String query = "SELECT ";
		
		for (int i = 0; i < col.length; i++) 
		{
			query += col[i];
			
			if(i<col.length - 1)
				query += ", ";
		}
		
		query += " FROM " + name;
		
		return getData(query, null);
	}
	
	public String[][] getData(String tableName)
	{
		String query = "SELECT * FROM " + tableName;
		
		return getData(query, null);
	}
	
	public String[][] getData(String query, String[] selectionArgs) 
	{
		openConnection();
		
		System.out.println(query);
		
		String[][] data = null;
		
		try 
		{
			cursor = myDb.rawQuery(query, selectionArgs);
			
			int col = cursor.getColumnCount();
			int row = cursor.getCount();
			
			data = new String[row][col];
			
			for (int i = 0; cursor.moveToNext(); i++) 
			{
				for (int j = 0; j < col; j++) 
				{
					data[i][j] = cursor.getString(j);
				}
			}

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		cursor.close();
		closeConnection();
		
		return data;
	}
// > End Select

// < Delete
	
	public boolean deleteData(int tableIndex, String whereClause) 
	{
		return deleteData(tables[tableIndex], whereClause);
	}
	
	public boolean deleteData(ElementTable table, String whereClause) 
	{
		return deleteData(table._tableName, whereClause);
	}
	
	public boolean deleteData(String tableName, String whereClause) 
	{
		String query = "DELETE FROM " + tableName + " " + whereClause;
		
		return execSQL(query);
	}
	
// > End Delete	
	
// < Drop
	public boolean dropTable(int tableIndex)
	{
		return dropTable(tables[tableIndex]._tableName);
	}
	
	public boolean dropTable(ElementTable table) 
	{
		return dropTable(table._tableName);
	}
	
	public boolean dropTable(String tableName)
	{
		String query = "DROP TABLE IF EXISTS " + tableName;
		
		return execSQL(query);
	}

// > End Drop

	
	@Override
	public void onCreate(SQLiteDatabase db) {}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){}
}

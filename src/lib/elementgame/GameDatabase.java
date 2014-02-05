package lib.elementgame;

import java.util.ArrayList;
import java.util.Arrays;

import lib.element.ElementTable;
import lib.engine.GameEngine;
import android.annotation.SuppressLint;
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
	
	public GameDatabase(GameEngine engine, ElementTable[] tables) 
	{
		super(engine, "AGD_DB", null, 2);
		this.tables = tables;
		
		init();
	}
	
	private void init() 
	{
		for (ElementTable table : tables) 
		{
			if(!isTableExist(table._tableName))
				createTable(table);
		}
	}
	
	protected void openConnection() throws SQLException
	{
		myDb = this.getWritableDatabase();
	}
	
	protected void closeConnection() 
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

//
//CIUS D2	

// < Create
	
	public boolean createTable(ElementTable table) 
	{
		String name = table._tableName;
		String[] colName = table._colName;
		String[] initValues = table._initColValue;
		
		return createTable(name, colName, initValues);
	}
	
	public boolean createTable(String tabName, String[] colName, String[] initValues)
	{
		String query = "CREATE TABLE IF NOT EXISTS " + tabName + " (";
		
		for (int i = 0; i < colName.length; i++) 
		{
			query += colName[i] + " VARCHAR(255)";
			
			if(!initValues[i].isEmpty() || initValues[i] != null || !initValues.equals(""))
				query += " DEFAULT '" + initValues[i] + "'";
			
			if(i < colName.length - 1)
				query += ",";
		}
		
		query += ")";
		
		return execute(query);
	}
	
 
// > End Create

// < Insert
	public boolean insertData(ElementTable table) 
	{
		String[] values = table._initColValue;
		
		return insertData(table, values);
	}
	
	public boolean insertData(ElementTable table, String[] values) 
	{
		String name = table._tableName;
		String[] colNames = table._colName;
		
		return insertData(name, colNames, values);
	}
	
	public boolean insertData(int tabIndex, String[] values) 
	{
		return insertData(tables[tabIndex]._tableName, tables[tabIndex]._colName, values);
	}
	
	public boolean insertData(String tabName, String[] colNames, String[] values) 
	{
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
		
		return insertData(query);
	}
	
	public boolean insertData(String query) 
	{
		openConnection();
		try 
		{
			sqlInsert = myDb.compileStatement(query);
			sqlInsert.executeInsert();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			closeConnection();
			return false;
		}
		
		closeConnection();
		return true;
	}

// > End Insert
	
// < Update
	public boolean updateData(int tableIndex, int colIndexToSet, String value) 
	{
		return updateData(tableIndex, colIndexToSet, value, "", "");
	}
	
	public boolean updateData(int tableIndex, int colIndexToSet, String value, int colIndexToControl, String equals) 
	{
		return updateData(tableIndex, colIndexToSet, value, tables[tableIndex]._colName[colIndexToControl], equals);
	}
	
	public boolean updateData(int tableIndex, int colIndexToSet, String value, String where, String equals) 
	{
		return updateData(tableIndex, colIndexToSet, value, "WHERE " + where + " = '" + equals + "'");
	}
	
	public boolean updateData(int tableIndex, int colIndexToSet, String value, String condition) 
	{
		String name = tables[tableIndex]._tableName;
		String col  = tables[tableIndex]._colName[colIndexToSet];
		
		String query = "UPDATE " + name + " SET " + col + " = '" + value + "' ";
		
		if(!condition.isEmpty())
		{
			query += condition;
		}
		
		return updateData(query);
	}
	
	public boolean updateData(String query) 
	{
		return insertData(query);
	}

// > End Update

// < Select
	public String[][] getAllData(int tableIndex) 
	{
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		
		for (String col : tables[tableIndex]._colName) 
		{
			list.add(new ArrayList<String>(Arrays.asList(getData(tableIndex, col))));
		}
		
		return (String[][]) list.toArray();
	}
	
	public String[] getData(int tableIndex, int colIndex)
	{
		return getData(tableIndex, tables[tableIndex]._colName[colIndex]);
	}
	
	public String[] getData(int tableIndex, String colName) 
	{
		String query = "SELECT " + colName + "FROM " + tables[tableIndex]._tableName;
		
		return getData(query);
	}
	
	public String[] getData(String query) 
	{
		return getData(query, null);
	}
	
	public String[] getData(String query, String[] selectionArgs) 
	{
		openConnection();
		
		ArrayList<String> list = new ArrayList<String>();
		
		try 
		{
			cursor = myDb.rawQuery(query, selectionArgs);
			
			while (cursor.moveToNext())
			{
				list.add(cursor.getString(cursor.getPosition()));
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		cursor.close();
		closeConnection();
		return (String[]) list.toArray();
	}
// > End Select
	
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
		
		return execute(query);
	}

// > End Drop

// < Delete
	public boolean deleteData(int tableIndex, String where, String equals)
	{
		return deleteData(tableIndex, "WHERE " + where + " = '" + equals + "'");
	}
	
	public boolean deleteData(int tableIndex, int colIdxToControl, String equals)
	{
		return deleteData(tableIndex, "WHERE " + tables[tableIndex]._colName[colIdxToControl] + " = '" + equals + "'");
	}
	
	public boolean deleteData(int tableIndex, String condition)
	{
		return deleteData(tables[tableIndex]._tableName, condition);
	}
	
	public boolean deleteData(String tableName, String condition) 
	{
		String query = "DELETE FROM " + tableName;
		
		if(!condition.isEmpty())
		{
			query += " " + condition;
		}
		
		return execute(query);
	}

// > End Delete	
	
	public boolean execute(String query)
	{
		openConnection();
		
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
	
	public boolean resetDatabase() 
	{
		boolean status1 = false, status2 = false;
		
		for (ElementTable table : tables) 
		{
			status1 = dropTable(table);
			status2 = createTable(table);
		}
		
		return status1 && status2;
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){}
}

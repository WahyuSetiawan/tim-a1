package lib.element;

public class ElementTable 
{
	public final String _tableName;
	public final String[] _colName;
	public final String[] _initColValue;
	
	public ElementTable(String tableName, String [] colName, String [] initColValue) 
	{
		this._tableName = tableName;
		this._colName = colName;
		this._initColValue = initColValue;
	}
}

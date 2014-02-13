package lib.element;

public class ElementTable 
{
	public final String _tableName;
	public final String[] _colNames;
	public final String[] _initValues;
	
	public ElementTable(String tableName, String [] colNames, String [] initValues) 
	{
		this._tableName = tableName;
		this._colNames = colNames;
		this._initValues = initValues;
	}
}

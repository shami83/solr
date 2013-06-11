package solr;

public enum DvdType {
	SAD("sad",0),
	COMEDY("comedy",1),
	ACTION("action",2),
	ROMANTIC("romantic",3),
	SUSPENCE("suspence",4),
	HAUNTED("haunted",5),
	ALL("all",6);
	
	
	public String typeName=null;
	public Integer typeID =null;
	DvdType(String typeName,Integer typeID )
	{
		this.typeName =typeName;
		this.typeID = typeID;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Integer getTypeID() {
		return typeID;
	}
	public void setTypeID(Integer typeID) {
		this.typeID = typeID;
	}
	

}

package com.cenmw.dialect;

import java.sql.Types;

import org.hibernate.dialect.SQLServerDialect;

public class UnicodeSQLServerDialect extends SQLServerDialect {
	public UnicodeSQLServerDialect() {
		super();
		registerColumnType(Types.VARCHAR, "nvarchar($l)");
		registerColumnType(Types.CLOB, "ntext");
	}

	public String getTypeName(int code, int length, int precision, int scale) {
		String result = null;
		if(code==Types.VARCHAR&&length==8000){
			result="ntext";
		}else{
			result = super.getTypeName(code, length, precision, scale);
		}
		return result;
	}
}

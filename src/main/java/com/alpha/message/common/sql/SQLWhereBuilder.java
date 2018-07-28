package com.alpha.message.common.sql;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import com.alpha.message.common.utils.SQLUtils;

public class SQLWhereBuilder {
	private final List<String> whereItems;

	private SQLWhereBuilder() {
		this.whereItems = new ArrayList<String>();
	}

	public static SQLWhereBuilder instance() {
		return new SQLWhereBuilder();
	}

	public SQLWhereBuilder and(String cloumn, SQLOperators operators, Collection<?> values) {
		this.appendCondition(this.whereItems.size() == 0 ? null : SQLConjunction.AND.getValue(), cloumn, operators, values);
		return this;
	}

	public SQLWhereBuilder or(String cloumn, SQLOperators operators, Collection<?> values) {
		this.appendCondition(this.whereItems.size() == 0 ? null : SQLConjunction.OR.getValue(), cloumn, operators, values);
		return this;
	}

	private void appendCondition(String conjunction, String cloumn, SQLOperators operators, Collection<?> values) {
		StringBuilder builder = new StringBuilder();

		if (!CollectionUtils.isEmpty(values) && StringUtils.isNotBlank(cloumn) && operators != null) {
			if (StringUtils.isNotBlank(conjunction)) {
				builder.append(conjunction);
			}
			builder.append(cloumn).append(operators.getValue());
			builder.append("(").append(SQLUtils.inJoin(values)).append(")");
			whereItems.add(builder.toString());
		}

	}

	public String toString() {
		if (CollectionUtils.isEmpty(whereItems)) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		sb.append(SQLConjunction.WHERE.getValue());
		for (String item : whereItems) {
			sb.append(item);
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		SQLWhereBuilder  sqlBuilder =	SQLWhereBuilder.instance() ;
	    List<String>  array =	new ArrayList<String>() ;
	    array.add("dddd") ;
		sqlBuilder.and(" name ", SQLOperators.In, array ) ;
		System.out.println(sqlBuilder.toString());
	}
}

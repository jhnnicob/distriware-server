package com.wkt.distriware.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.wkt.distriware.dao.ITransactionDao;

public abstract class BaseDao implements ITransactionDao {

	@Autowired
	protected JdbcTemplate jdbcTemplate;

	public String getReferenceNumber(String table, String tableField, int referenceId) {
		String res = null;

		SimpleJdbcCall jcbccall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("REFERENCE_SELECT");

		Map<String, Object> out = jcbccall.execute(new MapSqlParameterSource("id", referenceId));

		if (out.size() <= 0) {
		} else {
			List<Map<String, Object>> results = (List<Map<String, Object>>) out.get("#result-set-1");
			if (results.size() <= 0) {
			} else {
				Map<String, Object> one = results.get(0);
				res = (String) one.get("REFERENCE");
			}
		}

		//loop here until new record is created
		if (recordExist(table, tableField, res)) {
			res = getReferenceNumber(table,tableField,referenceId);
		}
		
		return res;
	}

	public boolean recordExist(String table, String field, String value) {
		boolean res = false;

		String query = "SELECT count(" + field + ") as counter FROM " + table + " WHERE " + field + " = '" + value + "'";
		List<Integer> list = jdbcTemplate.query(query, (rs, rowNum) -> new Integer(rs.getInt("counter")));
		
		if (list.size() <= 0) {
		}else {
			res = list.get(0).intValue() > 0;
		}
		
		return res;
	}

}

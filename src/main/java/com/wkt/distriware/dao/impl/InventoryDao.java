package com.wkt.distriware.dao.impl;

import static com.wkt.distriware.util.StringUtil.trim;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.wkt.distriware.dao.IInventoryDao;
import com.wkt.distriware.exception.DaoException;
import com.wkt.distriware.model.Inventory;
import com.wkt.distriware.model.InventoryItem;
import com.wkt.distriware.model.Product;
import com.wkt.distriware.model.User;

@Repository
public class InventoryDao extends BaseDao implements IInventoryDao {

	@Override
	public List<Inventory> list(int inventoryScheduleId, User user) {
		// TODO include user in the query

		String query = "Select INVTID, INVOICE, INVDATE,SPCODE, STYPE FROM "
				+ "INVENTORY WHERE INVTID = "+ inventoryScheduleId +" GROUP BY INVTID, INVOICE, INVDATE,SPCODE, STYPE";

		return jdbcTemplate.query(query,
				(rs, rowNum) -> new Inventory(rs.getInt("invtid"), trim(rs.getString("invoice")), rs.getDate("invdate"),
						trim(rs.getString("spcode")), trim(rs.getString("stype"))));
	}

	@Override
	public Inventory find(int inventoryScheduleId, String referenceNumber) throws DaoException {
		Inventory res = null;
		String query = "Select INVTID, INVOICE, INVDATE,SPCODE, STYPE FROM " + "INVENTORY WHERE INVOICE = '"
				+ referenceNumber + "' AND INVTID = '"+ inventoryScheduleId +"' GROUP BY INVTID, INVOICE, INVDATE,SPCODE, STYPE";

		List<Inventory> list = jdbcTemplate.query(query,
				(rs, rowNum) -> new Inventory(rs.getInt("invtid"), trim(rs.getString("invoice")), rs.getDate("invdate"),
						trim(rs.getString("spcode")), trim(rs.getString("stype"))));

		if (list != null && list.size() > 0) {
			res = list.get(0);
		}

		return res;
	}

	@Override
	public Inventory findBlankInventory(User user, int inventoryId) {
		Inventory res = null;
		String query = "Select INVTID, INVOICE, INVDATE,SPCODE, STYPE FROM INVENTORY WHERE INVTID = "+ inventoryId +" AND NUM = " + 0
				+ " AND PCODE IS NULL GROUP BY INVTID, INVOICE, INVDATE,SPCODE, STYPE";

		List<Inventory> list = jdbcTemplate.query(query,
				(rs, rowNum) -> new Inventory(rs.getInt("invtid"), trim(rs.getString("invoice")), rs.getDate("invdate"),
						trim(rs.getString("spcode")), trim(rs.getString("stype"))));

		if (list != null && list.size() > 0) {
			res = list.get(0);
		}

		return res;
	}

	@Override
	public Integer getOpenInventorySchedule() {
		Integer res = null;
		String query = "Select INVTID FROM INVTSCHED WHERE STATUS = 'ACTIVE' ";

		List<Integer> list = jdbcTemplate.query(query, (rs, rowNum) -> new Integer(rs.getInt("INVTID")));

		if (list.size() <= 0) {
		} else {
			res = list.get(0);
		}

		return res;
	}

	@Override
	public String createBlankInventory(int inventoryScheduleId, String referenceNumber, User user) throws DaoException {
		String query = "INSERT INTO INVENTORY (INVTID, INVOICE, NUM, INVDATE, COUNTDATE, CUSERID, CUSERNAME) VALUES"
				+ "(" + inventoryScheduleId + ", '" + referenceNumber + "', " + 0 + ", GETDATE(), GETDATE(), '"
				+ user.getUserId().trim() + "', '" + user.getUsername().trim() + "')";

		int affected = jdbcTemplate.update(query);
		if (affected > 0) {
			return referenceNumber;
		} else {
			return null;
		}
	}
	
	@Override
	public boolean update(int inventoryScheduleId, String reference, String spcode, String stockType) throws DaoException {
		String query = "UPDATE INVENTORY SET"
				+ " SPCODE = '"+ spcode +"', STYPE = '"+ stockType +"' "
						+ "WHERE INVOICE = '"+ reference +"' AND INVTID = '"+ inventoryScheduleId +"'";
		
		int affected = jdbcTemplate.update(query);
		return affected > 0;
	}
	
	@Override
	public boolean addInventoryItem(Inventory inventory, InventoryItem item, Product product) throws DaoException {
		SimpleJdbcCall jcbccall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("INVENTORY_ADD");
				
		Map<String, Object> map = new HashMap<>();
		map.put("INVTID", inventory.getInventoryId());
		map.put("INVOICE", inventory.getReference());
		map.put("INVDATE", inventory.getInventoryDate());
		
		Map<String, Object> out = jcbccall.execute(new MapSqlParameterSource(map));
		//TODO: Check query return value to determine the result
		
		return true;
	}
}

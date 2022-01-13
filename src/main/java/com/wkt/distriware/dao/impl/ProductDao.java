package com.wkt.distriware.dao.impl;

import static com.wkt.distriware.util.StringUtil.trim;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wkt.distriware.dao.IProductDao;
import com.wkt.distriware.exception.DaoException;
import com.wkt.distriware.model.Product;
import com.wkt.distriware.util.ListUtil;

@Repository
public class ProductDao extends BaseDao implements IProductDao{

	@Override
	public Product findWithBarcode(String barcode) throws DaoException {
		
		Product res = null;
		
		String query = "SELECT PCODE, PNAME, M_FL, M_LS, BARCODE, BARCODE_PCS FROM PRODUCT "
				+ "WHERE BARCODE = '"+ barcode +"' OR BARCODE_PCS = '"+ barcode +"'";
		
		List<Product> list = jdbcTemplate.query(query,  (rs, rowNum) ->
        new Product(
                trim(rs.getString("PCODE")),
                trim(rs.getString("PNAME")),
                trim(rs.getString("M_FL")),
                trim(rs.getString("M_LS")),
                trim(rs.getString("BARCODE")),
                trim(rs.getString("BARCODE_PCS"))
        )); 
		
		if (ListUtil.isNullOrEmpty(list)) {
		}else {
			res = list.get(0);
		}
		
		return res;
	}
	
}

package com.wkt.distriware.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wkt.distriware.service.api.ProductService;
import com.wkt.distriware.service.api.SupplierService;
import com.wkt.distriware.util.ExceptionUtil;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/supplier")
public class SupplierRestController {

	@Autowired
	private SupplierService supplierService;
	
	@GetMapping("/{keyword}")
	public RestResult searchSupplier(@PathVariable("keyword") String keyword) {
		RestResult res = RestResult.negativeInstance();
		try {
			res.setData(supplierService.search(keyword));
			res.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			res.setMessage(e.getMessage());
			res.setStackTrace(ExceptionUtil.toString(e));
		}
		return res;
	}
}

package com.wkt.distriware.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wkt.distriware.service.api.ProductService;
import com.wkt.distriware.util.ExceptionUtil;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/product")
public class ProductRestController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/{code}")
	public RestResult find(@PathVariable("code") String code) {
		RestResult res = RestResult.negativeInstance();
		try {
			res.setData(productService.findWithBarcode(code));
			res.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			res.setMessage(e.getMessage());
			res.setStackTrace(ExceptionUtil.toString(e));
		}
		return res;
	}
	
}

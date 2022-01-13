package com.wkt.distriware.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wkt.distriware.model.Inventory;
import com.wkt.distriware.service.api.InventoryService;
import com.wkt.distriware.util.ExceptionUtil;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/inventory")
public class InventoryRestController extends BaseRestController {

	@Autowired
	private InventoryService inventoryService;

	@GetMapping("")
	public RestResult list() {
		RestResult res = RestResult.negativeInstance();
		try {
			res.setData(inventoryService.listInventoryByUser());
			res.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			res.setMessage(e.getMessage());
			res.setStackTrace(ExceptionUtil.toString(e));
		}
		return res;
	}

	@GetMapping("/new")
	public RestResult create() {
		RestResult res = RestResult.negativeInstance();
		try {
			res.setData(inventoryService.createNewInventory());
			res.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			res.setMessage(e.getMessage());
			res.setStackTrace(ExceptionUtil.toString(e));
		}
		return res;
	}

	@PostMapping("/update")
	public RestResult updateHeader(@RequestBody Inventory inventory) {
		RestResult res = RestResult.negativeInstance();
		try {
			res.setData(inventoryService.updateInventoryHeader(inventory.getReference(), inventory.getSupplierCode(),
					inventory.getStockType()));
			res.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			res.setMessage(e.getMessage());
			res.setStackTrace(ExceptionUtil.toString(e));
		}
		return res;
	}
	
	@GetMapping("/{reference}")
	public RestResult getInventory(@PathVariable("reference") String reference) {
		RestResult res = RestResult.negativeInstance();
		try {
			res.setData(inventoryService.getInventoryHeader(reference));
			res.setSuccess(true);
		}catch(Exception e) {
			e.printStackTrace();
			res.setMessage(e.getMessage());
			res.setStackTrace(ExceptionUtil.toString(e));
		}
		return res;
	}
}

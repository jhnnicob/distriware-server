package com.wkt.distriware.rest;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseRestController {

	@Autowired
	protected ControllerUtil controllerUtil;
	
}

package kosta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import dto.EmpDto;
import service.EmpService;

@RestController
public class AjaxRestController {

	@Autowired
	private EmpService empservice;
	
	@RequestMapping(value="restcon.ajax")
	public List<EmpDto> ajaxResponseBody(){
	
		List<EmpDto> list = empservice.getEmpList();
		return list;  
	}
	
			
}

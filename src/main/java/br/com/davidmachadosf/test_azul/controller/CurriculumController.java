package br.com.davidmachadosf.test_azul.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.davidmachadosf.test_azul.model.cv.Curriculum;
import br.com.davidmachadosf.test_azul.service.utils.CurriculumUtil;

@Controller
public class CurriculumController {

	@RequestMapping(value ="/curriculum")
	public String curriculum(Model model) {
		
		Curriculum cvDavid = new Curriculum();
		CurriculumUtil.populateDavidMachadoSantosFilhoData(cvDavid);
		
		model.addAttribute("titulo", cvDavid.getTitulo());
		model.addAttribute("toString", cvDavid.toString());
		return "curriculum";
	}
}
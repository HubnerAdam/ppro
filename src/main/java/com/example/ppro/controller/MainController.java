package com.example.ppro.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ppro.model.Zamestnanec;
import com.example.ppro.service.ZamestnanecService;

@Controller
public class MainController {

	@Autowired
	private ZamestnanecService zamestnanecService;
	
	@GetMapping(value = "/")
	public String init(HttpServletRequest req){
		req.setAttribute("zamestnanci", zamestnanecService.findAllZamestnanci());
		req.setAttribute("mode", "ZAMESTNANEC_VIEW");
		return "index";
	}
	
	//Nastaveni modu pro editaci zamestnance
	@GetMapping(value = "/updateZamestnanec")
	public String init(@RequestParam long id, HttpServletRequest req){
		req.setAttribute("zamestnanec", zamestnanecService.findOne(id));
		req.setAttribute("mode", "ZAMESTNANEC_EDIT");
		return "index";
	}
	
	//ulozeni zmeny, noveho vlozeni zamestnance
	@GetMapping (value = "/save")
	public String save(@ModelAttribute Zamestnanec zamestnanec, HttpServletRequest req) {
		zamestnanecService.save(zamestnanec);
		req.setAttribute("zamestnanci", zamestnanecService.findAllZamestnanci());
		req.setAttribute("mode", "ZAMESTNANEC_VIEW");
		return "index";
	}
	
	//nastaveni modu pro noveho zamestnance
	@GetMapping (value = "/newZamestnanec")
	public String newZamestnanec(HttpServletRequest req) {
		req.setAttribute("mode", "ZAMESTNANEC_NEW");
		return "index";
	}
	
	//nastaveni modu pro smazani zamestnance
	@GetMapping (value = "/deleteZamestnanec")
	public void deleteZamestnanec(@RequestParam long id, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		zamestnanecService.delete(id);
		resp.sendRedirect("/");
	} 
} 

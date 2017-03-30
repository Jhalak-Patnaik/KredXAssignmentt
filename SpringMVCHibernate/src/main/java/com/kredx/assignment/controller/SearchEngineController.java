package com.kredx.assignment.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kredx.assignment.service.SearchEngineService;
import com.kredx.assignnment.model.Person;
import com.kredx.assignnment.model.ReviewDoc;

@Controller
public class SearchEngineController {
	
	
//	@Autowired(required=true)
//	@Qualifier(value="personService")
//	public void setPersonService(PersonService ps){
//		this.personService = ps;
//	}
	
	@Autowired
	public SearchEngineService searchEngineService;
	
	
//	@RequestMapping(value="/",method=RequestMethod.GET)
//	public String homeScreen(Model model) {
//		return "homeScreen";
//	}
//	
//	@RequestMapping(value = "/persons", method = RequestMethod.GET)
//	public String listPersons(Model model) {
//		model.addAttribute("person", new Person(1,"Jhalak","India"));
//		model.addAttribute("listPersons", this.searchEngineService.listPersons());
//		return "person";
//	}
//	
//	//For add and update person both
//	@RequestMapping(value= "/person/add", method = RequestMethod.POST)
//	public String addPerson(@ModelAttribute("person") Person p){
//		
//		if(p.getId() == 0){
//			//new person, add it
//			this.searchEngineService.addPerson(p);
//		}else{
//			//existing person, call update
//			this.searchEngineService.updatePerson(p);
//		}
//		
//		return "redirect:/persons";
//		
//	}
//	
//	@RequestMapping("/remove/{id}")
//    public String removePerson(@PathVariable("id") int id){
//		
//        this.searchEngineService.removePerson(id);
//        return "redirect:/persons";
//    }
// 
//    @RequestMapping("/edit/{id}")
//    public String editPerson(@PathVariable("id") int id, Model model){
//        model.addAttribute("person", this.searchEngineService.getPersonById(id));
//        model.addAttribute("listPersons", this.searchEngineService.listPersons());
//        return "person";
//    }
	
	
	@RequestMapping(value = "/submitQuery",method=RequestMethod.POST)
	@ResponseBody
	public List<ReviewDoc> getMatchingReviews(@RequestBody List<String> searchStrings) {
		List<String> ls = new ArrayList<String>();
//		ls.add(searchString);
		return searchEngineService.getMatchingReviews(searchStrings);
//		System.out.println(searchEngineService.getMatchingReviews());
	}
	
	
}

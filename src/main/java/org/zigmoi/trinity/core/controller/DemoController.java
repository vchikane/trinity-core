package org.zigmoi.trinity.core.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the Demo JSP home page, offers connection testing, GET and POST demos.
 */
@Controller
public class DemoController {
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		return "home";
	}
	
	@RequestMapping(value = "/testconnection", method = RequestMethod.GET)
	public  @ResponseBody String testConnection() {		
		return "TRINITY UP & RUNNING!";
	}
	
	@RequestMapping(value = "/postrequestdemo", method = RequestMethod.POST)
	public  @ResponseBody String postRequestDemo(@RequestBody TestData inp) {		
		// To process post data you need create a java class to convert that json object to java object.
		// Code processing the input parameters
		// request data should be like this when passed in javascript var data ={inp1:"ashim",inp2:"usmani"};
		return "POST data recieved, Mirrored Data follows:"+ inp.getInp1()+ inp.getInp2();
	}
	
	@RequestMapping(value = "/getrequestdemo", method = RequestMethod.GET)
	public  @ResponseBody String getRequestDemo(@RequestParam(value="inp1") String input1,@RequestParam(value="inp2") String input2) {		
		//@RequestParam is for values passed in query string. 
		return "GET data recieved, Mirrored Data follows:"+ input1+input2;
	}
}

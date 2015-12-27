package com.honorhs.app.basecamp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.honorhs.app.basecamp.service.GBService;
import com.honorhs.app.basecamp.model.GB;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
 
@Controller
public class GBController {
     
    @Autowired
    GBService GBService;
     
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model) {
         
        model.addAttribute("GB", GBService.getGBList());       
         
        return "/GB/guest_book";
    }
    
    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String add(@ModelAttribute("GB") GB gb,
            RedirectAttributes redirectAttributes) {
    	
    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	Date date = new Date();
    	gb.setdate_time(dateFormat.format(date));
    	
        this.GBService.add(gb);      
        redirectAttributes.addFlashAttribute("message", "추가되었습니다.");       
        return "redirect:/";
    }
     

    
    
}
package com.codegym.testmodule.controller;

import com.codegym.testmodule.model.City;
import com.codegym.testmodule.model.Country;
import com.codegym.testmodule.service.CityService;
import com.codegym.testmodule.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class CityInfoController {

    @Autowired
    private CityService cityService;

    @Autowired
    private CountryService countryService;

    @GetMapping("/view")
    private ModelAndView cityView(@RequestParam("id") Long id){
        Optional<City> cityOptional = cityService.findById(id);
        ModelAndView model = new ModelAndView("viewcity","city",cityOptional.get());
        return model;
    }

    @GetMapping("/edit")
    private ModelAndView cityEditForm(@RequestParam("id") Long id){
        List<Country> countryList = (List<Country>) countryService.findAll();
        Optional<City> cityOptional = cityService.findById(id);
        ModelAndView model = new ModelAndView("edit","city",cityOptional.get());
        model.addObject("countryList",countryList);
        return model;
    }

    @PostMapping("/edit")
    public ModelAndView createCity(@ModelAttribute City city){
        cityService.save(city);
        ModelAndView modelAndView = new ModelAndView("city","message","Tạo Thành Phố Thành Công");
        return modelAndView;
    }
}

package com.codegym.controller;

import com.codegym.model.City;
import com.codegym.repository.ICountryRepository;
import com.codegym.service.ICityService;
import com.codegym.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
@RequestMapping("/api/cities")
public class CityController {
    @Autowired
    private ICityService cityService;
    @Autowired
    private ICountryService countryService;
    @GetMapping("/list")
    private ModelAndView showList() {
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("city", cityService.findAll());
        modelAndView.addObject("counties",countryService.findAll());
        return modelAndView;
    }

    @GetMapping()
    ResponseEntity<Iterable<City>> findAll() {
        return new ResponseEntity<>(cityService.findAll(), HttpStatus.OK);
    }

    @PostMapping()
    ResponseEntity<City> createCity(@RequestBody City city) {
        return new ResponseEntity<>(cityService.save(city), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    ResponseEntity<City> findCityById(@PathVariable Long id){
        Optional<City> cityOptional = cityService.findById(id);
        if(!cityOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cityOptional.get(),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    ResponseEntity<City> updateCity(@PathVariable Long id,@RequestBody City city){
        Optional<City> cityOptional = cityService.findById(id);
        if (!cityOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        city.setId(cityOptional.get().getId());
        return new ResponseEntity<>(cityService.save(city),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    ResponseEntity<City> deleteCity(@PathVariable Long id){
        Optional<City> cityOptional =cityService.findById(id);
        if(!cityOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cityService.remote(id);
        return new ResponseEntity<>(cityOptional.get(),HttpStatus.OK);
    }

}

package com.codegym.controller;

import com.codegym.model.Country;
import com.codegym.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/countries")
public class CountryController {
    @Autowired
    private ICountryService iCountryService;
    @GetMapping()
    ResponseEntity<Iterable<Country>> findAll(){
        return new ResponseEntity<>(iCountryService.findAll(), HttpStatus.OK);
    }

}

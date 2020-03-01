package com.rest.webservices.restfulwebservices.filtering;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
	@GetMapping("filtering")
	public MappingJacksonValue retriveBean() {
		SomeBean someBean = new SomeBean("value1","value2","value3");
		
	 SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("val1","val2");
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		MappingJacksonValue jacksonValue = new MappingJacksonValue(someBean);
		jacksonValue.setFilters(filterProvider);
		return jacksonValue;
	}
	
	@GetMapping("filtering-all")
	public MappingJacksonValue retriveBeans() {
		List<SomeBean> list =  Arrays.asList(new SomeBean("asd", "asda", "cds"),new SomeBean("bgb", "bcvfx", "rtr"));
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("val2","val3");
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		MappingJacksonValue jacksonValue = new MappingJacksonValue(list);
		jacksonValue.setFilters(filterProvider);
		return jacksonValue;
	
	}
}

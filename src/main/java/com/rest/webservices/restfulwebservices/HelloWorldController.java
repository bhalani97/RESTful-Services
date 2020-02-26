package com.rest.webservices.restfulwebservices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.rest.webservices.restfulwebservices.helloworld.HelloBean;

@ RestController
public class HelloWorldController {

	@GetMapping(path = "/hello-world")
	public String getMsg() {
		return "Hello World";
	}
	
	@GetMapping(path = "/hello-world-bean")
	public HelloBean getHelloWorldBean() {
		return new HelloBean("Divyesh");
	}
	
	
	@GetMapping(path = "/hello-world/path-variable/{name}")
	public HelloBean getHelloWorldBean(@PathVariable String name) {
		return new HelloBean("Hello 	" + name);
	}
	
}

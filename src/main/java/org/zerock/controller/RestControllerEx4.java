package org.zerock.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.Rest1;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/rest4")
@Log4j
public class RestControllerEx4 {

	@RequestMapping(value = "/ex1", produces = MediaType.TEXT_PLAIN_VALUE)
	// "text/plain" == MediaType.TEXT_PLAIN_VALUE 
	public String method1() {
		log.info("method1");
		
		return "hello world";
	}
	
	@RequestMapping(value = "/ex2", produces = MediaType.APPLICATION_JSON_VALUE)
	//header =application/json
	public String method2() {
		log.info("method2");
		
		return "{}";
	}
	
	@RequestMapping(value = "/ex3", produces = {MediaType.APPLICATION_JSON_VALUE,
			                                    MediaType.APPLICATION_XML_VALUE})
	
	// localhost:8080/rest4/ex3.xml----> application/xml;
	//localhost:8080/rest4/ex3.json---->application/json;
	public Rest1 method3() {
		
		Rest1 r = new Rest1();
		r.setName("selah");
		r.setAge(22);
		r.setVote(true);
		r.setObj(null);
		
		return r;
	}
	
	@RequestMapping(value = "/ex4", produces = {MediaType.TEXT_PLAIN_VALUE })
	// produces == 요청 header의 accept와 연관
	public String method4() {
		log.info("method4");
		
		return "HELLO THIS IS ME";
	}
	
	@RequestMapping(value = "/ex5", produces ="text/plain; charset=UTF-8")
	
	public String method5() {
		log.info("method5");
		
		return "스프링이지요";
	}
}

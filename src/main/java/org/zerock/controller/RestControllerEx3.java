package org.zerock.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.Rest1;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/rest3")
@Log4j
public class RestControllerEx3 {

	@RequestMapping("/ex1") //get or host 방식으로 모두 가능
	public String method1(String name) { //dispatcher servlet이 param을 읽어서 전달
		log.info("name:" +name);
		//get : localhost:8080/rest3/ex1?name=selah
		
		
		return "spring";
	}
	
	@RequestMapping("/ex2/{val}")// 경로 자체에 데이터 추가 (path variable)
	public String method2(@PathVariable("val")String value) {
		
		log.info("method2");
		log.info(value);
		
		return "method2";
		
		//localhost:8080/rest3/ex2/abc
	}
	
	@RequestMapping("/ex3/{val}")
	public String method3(@PathVariable String val) {
		// PathVariable과 String 값이 같으면 생략가능
		log.info("method3");
		
		return val;
		
		//localhost:8080/rest3/ex3/hello
	}
	
	@RequestMapping("/ex4/{val}/other/{age}")
	//{} 안에 같은 이름을 찾아 리턴
	public String method4(@PathVariable String val, @PathVariable int age) {
		return val +age;
		
		//localhost:8080/rest3/ex4/xyz/other/99
	}
	
	@RequestMapping("/ex5")
	public String method5(@RequestBody String b) {
		// 몸통에 붙일려면 post방식으로 ->몸통자체:raw
		
		log.info(b);
		
		return "method5";
	}
	
	@RequestMapping("/ex6")
	public String method6(@RequestBody Rest1 body) {
		// rest1(json type) ==>body + toString 
	//public String method6(@RequestBody String body)
		log.info(body);
		
		return "method6";
	}
	
	//consumes 의 값은 mime type
	@RequestMapping(path = "/ex7", consumes ="text/plain")
	//value(생략가능) ==path, value="ex7"
	//consume == http media type
	//consumes type을 기재해 놓으면 content type이 같은 타입일때만 실행
	public String method7(@RequestBody String body) {
		log.info(body);
		
		return "method7";
	}
	
	@RequestMapping(path = "/ex8", consumes =MediaType.APPLICATION_JSON_VALUE)
	//consumes =MediaType.APPLICATION_JSON_VALUE =="application/json"
	public String method8(@RequestBody String body) {
		log.info(body);
		
		return "method8";
	}
	
	// consumes는 request header(Content-Type)과 연관있음
	@RequestMapping(path = "/ex9", 
			consumes = {MediaType.APPLICATION_JSON_VALUE,
					MediaType.TEXT_PLAIN_VALUE })
	// consumes는 String type
	public String method9(@RequestBody String body) {
		log.info(body);
		
		return "method9";
	}
	
	
	
}

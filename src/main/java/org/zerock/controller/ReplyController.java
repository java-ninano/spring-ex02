package org.zerock.controller;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;
import org.zerock.service.ReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/replies")
@Log4j
@AllArgsConstructor
public class ReplyController {

	private ReplyService service;

	   // new Register 
	@PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE,
			// content-type : application/json 체크,
			// body(본문타입) : raw-json
			produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> create(@RequestBody ReplyVO vo) {
		// 368page Ticket == @RequestBody
		// @RequestBody : json data -->ReplyVO 타입으로 변환하도록 지정

		log.info("vo: " + vo);

		// vo: ReplyVO(rno=null, bno=200, reply=new reply, replyer=user00,
		// replyDate=null, updateDate=null)

		int insertCount = service.register(vo);
		// create --> 내부적으로 ReplyServiceImpl을 호출해서 register()호출

		log.info("count: " + insertCount);

		if (insertCount == 1) {
			return new ResponseEntity<>("success98982214", HttpStatus.OK);
			// HttpStatus.OK=200, if (insertCount == 1) 이면 body=success 출력
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			// INTERNAL= 500
		}
	}

	// 395page,list
	@GetMapping(value = "/pages/{bno}/{page}",
			// ACCEPT HEADER == PRODUCES
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<ReplyVO>> getList(@PathVariable("page") int page, @PathVariable("bno") Long bno) {

		Criteria cri = new Criteria(page, 10);

		List<ReplyVO> list = service.getList(cri, bno);

		log.info(list);

		return new ResponseEntity<List<ReplyVO>>(list, HttpStatus.OK);
		// localhost:8080/replies/pages/203/1
	}

	// 397page
	@GetMapping(value = "/{rno}",
			// get 방식
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ReplyVO> get(@PathVariable("rno") Long rno) {
		ReplyVO vo = service.get(rno);
		
		log.info(vo);

		return new ResponseEntity<ReplyVO>(vo, HttpStatus.OK);

		// localhost:8080/replies/2
		// json : 날짜 type 없음 -->숫자 타입으로 리턴
		// "replyDate": 1611511652000,
		// "updateDate": 1611514140000

	}

	@DeleteMapping(value = "/{rno}", produces = MediaType.TEXT_PLAIN_VALUE)
	// method = RequestMethod.DELETE == @DeleteMapping
	public ResponseEntity<String> remove(@PathVariable("rno") Long rno) {

		int cnt = service.remove(rno);
		// @PathVariable -->rno값 가져옴

		log.info(cnt);

		if (cnt == 1) {
			return new ResponseEntity<>("success", HttpStatus.OK);
			// success 라는 body
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		// localhost:8080/replies/2

	}

	@RequestMapping(value = "/{rno}", method = { RequestMethod.PUT,
			RequestMethod.PATCH }, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> modify(@RequestBody ReplyVO vo, @PathVariable Long rno) {

		vo.setRno(rno);

		int cnt = service.modify(vo);

		log.info(cnt);

		if (cnt == 1) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
			/*
			 * 398page SELECT * from tbl_board order by bno desc; select * from tbl_reply;
			 * select * from tbl_reply where rno =23;
			 */
		}
	}
}

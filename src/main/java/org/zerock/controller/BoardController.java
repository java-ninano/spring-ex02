package org.zerock.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@AllArgsConstructor
@RequestMapping("/board/*")
@Log4j
public class BoardController {

	private BoardService service;
	/*
	 * @Autowired public BoardController(BoardService service) { super();
	 * this.service = service; } ==> @AllArgsConstructor을 사용시 이 문장을 작성할 필요가 없다.
	 */

	/*
	 * // 211 page // @RequestMapping(value = "/list", method= RequestMethod.GET)
	 * 
	 * @GetMapping("/list") // handler method의 return type이 void인경우 // 요청경로가
	 * view(jsp)의 이름이 됨 // 이 메소드는 (/board/list) -> board/list.jsp
	 * -->(servlet-context.xml) // WEB-INF/VIEWS/board/list -->/board/list.jsp 로 전달됨
	 * 
	 * public void list(Model model) {
	 * log.info("**********************list*********************************");
	 * List<BoardVO> list = service.getList(); model.addAttribute("list", list); //
	 * model로 옮겨주는 작업
	 * 
	 * }
	 */
	@GetMapping("/list") //목록으로 돌아올때 404오류 뜰때
	public void list(@ModelAttribute("cri")Criteria cri, Model model) { //pageNum, amount -->cri
	List<BoardVO> list = service.getList(cri);
		
		int total = service.getTotal(cri);
		
		PageDTO dto = new PageDTO(cri, total);
		
		model.addAttribute("list", list);
		model.addAttribute("pageMaker", dto);
	}

	@GetMapping("/register")
	public void register(@ModelAttribute("cri")Criteria cri) {

	}

	// @RequestMapping(value = "register" method=RequestMethod.POST)
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		// RedirectAttributes rttr : 한번사용하고 사라짐
		/*
		 * BoardVO board = new BoardVO(); board.setTitle(request.getParameter("title"));
		 * board.setContent(request.getParameter("content"));
		 * board.setWriter(request.getParameter("writer"));
		 */
		service.register(board);

		rttr.addFlashAttribute("result", board.getBno());
		rttr.addFlashAttribute("message", board.getBno() + "번 글이 등록되었습니다.");

		// flash --> session attr + 객체로
		// return "board/list";
		return "redirect:/board/list";
	}

	// 표: /board/read, 코드: /board/get
	@GetMapping({ "/get", "/modify" })
	public void get(@RequestParam("bno") Long bno, 
			@ModelAttribute("cri")Criteria cri,  Model model) { // 생략가능 : @RequestParam("bno")

		// String boardNum = request.getParameter("num");
		// int num = Integer.parseInt(boardNum);
		// BoardVO vo = service.get((long) num);
		// request.setAttribute("board" , vo);
		// request.getRequestDispatcher(".jsp").forward();

		log.info("get method - bno: " + bno);
		log.info(cri);
		BoardVO vo = service.get(bno);
		model.addAttribute("board", vo);
		// model.addAttribute("cri", cri); 
		// ===>@으로 처리 가능, 타입의 이름으로 넘어감(Criteria)==>get.jsp
		// ===>/@ModelAttribute("cri")로 적어주면 됨, 명시하지 않아도 타입이름으로 자동 넘어감 
		
	}

	@PostMapping("/modify") //페이지 수정 후 원래페이지로 , 수정완료 후 넘어가는 애들
	public String modify(BoardVO board,Criteria cri,RedirectAttributes rttr) {
		/*
		 * 이전코드 
		 * BoardVO board = new BoardVO();
		 * board.setBno(request.getParameter("bno"));
		 * board.setTitle(request.getParameter("title"));
		 * board.setContent(request.getPatameter("content"));
		 */

		if (service.modify(board)) {

			rttr.addFlashAttribute("result", "success");
			rttr.addFlashAttribute("message", board.getBno() + "번 글이 수정되었습니다.");
			// 있으면 modal창 띄우기
		}
		log.info(cri);
		rttr.addAttribute("pageNum", cri.getPageNum()); //rttr + addattr
		rttr.addAttribute("amount", cri.getAmount());
		// 2021.01.22 글 수정 완료후 번호유지
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());

		return "redirect:/board/list";
	}

	@PostMapping("/modify2")
	public String modify2(BoardVO board, RedirectAttributes rttr) {
		/**
		 * 스프링 없이 BoardVO board = new BoardVO();
		 * board.setBno(request.getParameter("bno"));
		 * board.setTitle(request.getParameter("title"));
		 * board.setContent(request.getParameter("content"));
		 */

		if (service.modify(board)) {
			rttr.addFlashAttribute("result", "success");
			rttr.addAttribute("bno", board.getBno());
			rttr.addAttribute("a", "a");
			rttr.addFlashAttribute("b", "b");
		}

		return "redirect:/board/get";
	}

	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno,
			Criteria cri, RedirectAttributes rttr) {

		if (service.remove(bno)) {
			rttr.addFlashAttribute("result", "success");
			rttr.addFlashAttribute("message", bno + "번 글이 삭제되었습니다.");
		}
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type",cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());

		return "redirect:/board/list";
	}
	/*
	 * @GetMapping("/modify") public void modify(Long bno) { BoardVO vo
	 * =service.get(bno); model.addAttribute("board", vo); }
	 */
}

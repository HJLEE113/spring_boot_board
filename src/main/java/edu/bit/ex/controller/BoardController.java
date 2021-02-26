package edu.bit.ex.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import edu.bit.ex.page.Criteria;
import edu.bit.ex.service.BoardServiceImpl;
import edu.bit.ex.vo.BoardVO;
import edu.bit.ex.vo.PageVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j // log4j보다 속도가 더 빠름
@AllArgsConstructor
@Controller
public class BoardController {

	private BoardServiceImpl boardService;

	@GetMapping("/board/list")
	public ModelAndView list(ModelAndView mav, Criteria cri) {
		log.debug("list");
		log.info("cri");

		mav.setViewName("rest_list");
		mav.addObject("list", boardService.getList(cri));

		int total = boardService.getTotalCount(cri);
		mav.addObject("pageMaker", new PageVO(cri, total));

		return mav;
	}

	// 삭제
	@DeleteMapping("/board/{bId}")
	public ResponseEntity<String> delete(BoardVO boardVO, Model model) {
		ResponseEntity<String> entity = null;
		boardService.remove(boardVO.getbId());
		return entity;
	}

	// 글화면
	@GetMapping("/board/{bId}")
	public ModelAndView content_view(ModelAndView mav, BoardVO boardVO) {
		// 조회수 증가
		boardService.hitupdate(boardVO);
		mav.setViewName("rest_content_view");
		mav.addObject("content_view", boardService.getBoard(boardVO.getbId()));
		return mav;
	}

	// 글작성뷰
	@GetMapping("/board/write")
	public ModelAndView write_view(ModelAndView mav) {
		mav.setViewName("rest_write_view");
		return mav;
	}

	// 글작성-insert
	@PostMapping("/board/write")
	public String write(BoardVO boardVO, ModelAndView mav) {
		boardService.insertBoard(boardVO);
		mav.setViewName("rest_list");
		mav.addObject("list", boardService.getList());
		return "redirect:/board/list";
	}

	// 글수정
	@PutMapping("/board/{bId}")
	public ResponseEntity<String> update(BoardVO boardVO) {
		ResponseEntity<String> entity = null;
		boardService.modifyBoard(boardVO);
		return entity;
	}

	// 댓글작성뷰
	@GetMapping("/board/reply/{bId}")
	public ModelAndView reply_view(ModelAndView mav, BoardVO boardVO) {
		mav.setViewName("rest_reply_view");
		mav.addObject("reply_view", boardService.getBoard(boardVO.getbId()));
		return mav;
	}

	// 댓글작성후
	@PostMapping("/board/reply/{bId}")
	public String replyWrite(BoardVO boardVO, Model model) {
		boardService.replyBoard(boardVO);
		return "redirect:/board/list";
	}
}
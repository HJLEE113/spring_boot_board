package edu.bit.ex;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.bit.ex.vo.BoardVO;
import lombok.AllArgsConstructor;

@AllArgsConstructor
//@Controller // 스트링부트에서는 무조건 Rest가 기본.
public class HomeController {

	@RequestMapping("/")
	//@ResponseBody
	public String home(Model model) {
		BoardVO board = new BoardVO();
		board.setbContent("컨텐츠");
		board.setbTitle("타이틀");
		board.setbName("홍길동");

		model.addAttribute("board", board);

		return "thymeleaf/index";
		// 타임리프로 작성된 문서 불러오기
	}
}
//	@GetMapping("/board")
//	public String board() {
//		return "rest_list";
//	}


//	@GetMapping("/board")
//	public ModelAndView list(ModelAndView mav) {
//		mav.setViewName("rest_list");
//		mav.addObject("list", boardService.getList());
//
//		return mav;
//	}

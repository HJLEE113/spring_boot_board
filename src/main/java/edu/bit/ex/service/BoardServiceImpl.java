package edu.bit.ex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.bit.ex.mapper.BoardMapper;
import edu.bit.ex.page.Criteria;
import edu.bit.ex.vo.BoardVO;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Service
public class BoardServiceImpl {

	@Autowired
	private BoardMapper mapper;

	// 리스트
	public List<BoardVO> getList() {

		return mapper.getList();
	}

	// 페이징
	public List<BoardVO> getList(Criteria cri) {

		return mapper.getPaging(cri);
	}

	// 페이징
	public int getTotalCount(Criteria cri) {

		return mapper.getTotalCnt(cri);
	}

	// content_view
	public BoardVO getBoard(int getbId) {

		return mapper.getContent(getbId);
	}

	// 조회수
	public void hitupdate(BoardVO boardVO) {
		mapper.getHitup(boardVO);

	}

	// 글작성
	public int insertBoard(BoardVO boardVO) {
		return mapper.getInsert(boardVO);
	}

	// 글삭제
	public void remove(int getbId) {
		mapper.getRemove(getbId);
	}

	// 글수정
	public void modifyBoard(BoardVO boardVO) {
		mapper.getUpdate(boardVO);
	}

	public BoardVO getReply(BoardVO boardVO) {
		return mapper.reply_view(boardVO);
	}

	public int replyBoard(BoardVO boardVO) {
		return mapper.reply(boardVO);
	}

}

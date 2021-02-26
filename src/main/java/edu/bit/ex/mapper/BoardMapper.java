package edu.bit.ex.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.bit.ex.page.Criteria;
import edu.bit.ex.vo.BoardVO;

@Mapper
public interface BoardMapper {

	public List<BoardVO> getList();

	public List<BoardVO> getPaging(Criteria cri);

	public int getTotalCnt(Criteria cri);

	public BoardVO getContent(int getbId);

	public void getHitup(BoardVO boardVO);

	public int getInsert(BoardVO boardVO);

	public void getRemove(int getbId);

	public int getUpdate(BoardVO boardVO);

	public void getReplyUpdate(BoardVO boardVO);

	public BoardVO reply_view(BoardVO boardVO);

	public int reply(BoardVO boardVO);

	public void replyShape(BoardVO boardVO);

}

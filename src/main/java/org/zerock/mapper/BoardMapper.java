package org.zerock.mapper;

import java.sql.PreparedStatement;
import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

public interface BoardMapper {
	
	public int getTotalCount(Criteria cri);
	//SELECT count (*)FROM tbl_board
	
	// @Select("select * from tbl_board where bno > 0") -->BoardMapper에 작성
	public List<BoardVO> getList();
	
	public List<BoardVO> getListWithPaging(Criteria cri);
	
	public void insert(BoardVO board);
	/*
	{
		String sql ="INSERT INTO tbl_board"
				+ "(bno,title, content, writer,regdate,updatedate) "
				+ "VALUE (seq_board.nextval,?,?,?,SYSDATE)";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		pstmt.setString(1, board.getTitle());
		pstmt.setString(1, board.getContent());
		pstmt.setString(1, board.getwriter());
		
		pstmt.updateQuery();
		
		close();
		
	}*/
	
	public void insertSelectKey(BoardVO board);
	
		//1. seq_board의 nextval을 먼저 조회(select)
		//2. 조회된 nextval을 insert에서 사용
	
	//2021.01.13
	public BoardVO read(Long bno); //stmt -->table query -->xml
	
	public int delete(Long bno); //deleted row of size
	
	public int update(BoardVO board);
	
}

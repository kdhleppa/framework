package edu.kh.project.member.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.project.board.model.dto.Comment;
import edu.kh.project.common.utility.Util;
import edu.kh.project.member.model.dao.CommentDAO;

@Service
public class CommentServiceImple implements CommentService {
	
	@Autowired
	private CommentDAO dao;
	
	/** 댓글 목록 조회
	 *
	 */
	@Override
	public List<Comment> select(int boardNo) {
		
		return dao.select(boardNo);
	}
	
	
	/** 겟방식 삽입
	 *
	 */
	@Override
	public int insert(Map<String, Object> map) {
		return dao.insert(map);
	}

	@Override
	public int delete(int commentNo) {
		return dao.delete(commentNo);
	}

	@Override
	public int update(Map<String, Object> map) {
		return dao.update(map);
	}
	
	/** 포스트방식 삽입
	 *
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int insert(Comment comment) {
		// XSS 방지 처리
		comment.setCommentContent(Util.XSSHandling(comment.getCommentContent()));
		return dao.insert(comment);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public int update(Comment comment) {
		comment.setCommentContent(Util.XSSHandling(comment.getCommentContent()));
		return dao.update(comment);
	}
	
	

	



	

}

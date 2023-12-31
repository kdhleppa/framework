package edu.kh.project.member.model.service;

import java.util.List;
import java.util.Map;

import edu.kh.project.board.model.dto.Comment;

public interface CommentService {

	List<Comment> select(int boardNo);


	int insert(Map<String, Object> map);


	int delete(int commentNo);


	int update(Map<String, Object> map);


	int insert(Comment comment);


	int update(Comment comment);

	

	

}

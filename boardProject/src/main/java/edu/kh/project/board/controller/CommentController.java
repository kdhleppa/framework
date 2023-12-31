package edu.kh.project.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.kh.project.board.model.dto.Comment;
import edu.kh.project.member.model.service.CommentService;
import oracle.jdbc.proxy.annotation.Post;

// @Controller + @ResponseBody

@RestController // 요청/응답 처리(단, 모든 요청 응답은 비동기)
				// -> REST API 를 구축하기 위한 Controller
@SessionAttributes({"loginMember"})
public class CommentController {
	
	@Autowired
	private CommentService service;
	
	//댓글 목록 조회					// json 통신 시 한글깨짐 방지 코드 : , produces="application/json; charset=UTF-8"
	@GetMapping(value="/comment")
	public List<Comment> select(int boardNo) {
		
		
		return service.select(boardNo);
		// 동기시 return : forward / redirect
		// 비동기 시 return : 값 자체
	}
	
	// 댓글 삽입 (post)
	@PostMapping("/comment")
	public int insert(@RequestBody Comment comment) {
		// 요청 데이터(JSON)
		// HttpMessageConverter가 해석 -> Java 객체(comment)에 대입
		return service.insert(comment);
	}
	
	
	
	/*
	// 댓글 삽입 (get)
	@GetMapping("/comment/insert")
	public int insert(
			int boardNo,
			String commentContent,
			int parentNo,
			@SessionAttribute("loginMember") Member loginMember){
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardNo", boardNo);
		map.put("commentContent", commentContent);
		map.put("memberNo", loginMember.getMemberNo());
		map.put("parentNo", parentNo);
		int result = service.insert(map);	
		System.out.println("result : " + result);
		return result;
	}
	*/
	// 댓글 삭제
	@GetMapping("/comment/delete")
	public int delete(int commentNo) {
		
		int result = service.delete(commentNo);
		return result; 
	}
	
	/*
	// 댓글 수정
	@GetMapping("/comment/update")
	public int update(int commentNo, String commentContent) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("commentNo", commentNo);
		map.put("commentContent", commentContent);
		
		int result = service.update(map);
		System.out.println("수정 : "+ result);
		return result;
	}
	*/
	
	@PostMapping("/comment/update")
	public int update(@RequestBody Comment comment) {
		return service.update(comment);
	}
	
}

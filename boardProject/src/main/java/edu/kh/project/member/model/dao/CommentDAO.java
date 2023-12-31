package edu.kh.project.member.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.project.board.model.dto.Comment;

@Repository
public class CommentDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	/** 댓글 목록 조회
	 * @param boardNo 
	 * @return
	 */
	public List<Comment> select(int boardNo) {

		return sqlSession.selectList("boardMapper.selectCommentList",boardNo);
	}

	

	public int insert(Map<String, Object> map) {
		return sqlSession.insert("boardMapper.commentInsert", map);
	}



	public int delete(int commentNo) {
		return sqlSession.update("boardMapper.commentDelete", commentNo);
	}



	public int update(Map<String, Object> map) {
		return sqlSession.update("boardMapper.commentUpdate", map);
	}



	public int insert(Comment comment) {
		return sqlSession.insert("boardMapper.insert", comment);
	}



	public int update(Comment comment) {
		return sqlSession.update("boardMapper.commentUpdate", comment);
		}

}

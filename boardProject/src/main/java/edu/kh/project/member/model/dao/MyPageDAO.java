package edu.kh.project.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.project.member.model.dto.Member;

@Repository
public class MyPageDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	
	public int update(Member updateMember) {
		return sqlSession.update("mypageMapper.update", updateMember);
	}
	
	public int updateProfileImage(Member loginMember) {
		
		return sqlSession.update("mypageMapper.updateProfileImage", loginMember);
	}

}

package edu.kh.project.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.project.member.model.dao.MemberDAO;
import edu.kh.project.member.model.dto.Member;

@Service // 비즈니스 로직(데이터 가공, DAO 호출, 트렌젝션 제어)처리하는 클래스 명사
// + Bean 으로 등록하는 어노테이션
public class MemberServiceImpl implements MemberService {
	// @Autowired : 작성된 필드의
	// Bean 으로 등록된 객체 중 타입에 일치하는 Bean을
	// 해당 필드에 자동 주입(Injection) 하는 어노테이션.
	// == DI(Dependecy Injection, 의존성 주입)
	// -> 객체를 직접 만들지않고, Spring이 만들걸 주입함(Spring에 의존)
	
	@Autowired
	private MemberDAO dao;

	@Override
	public Member login(Member inputMember) {
		
		// dao 메서드 호출
		Member loginMember = dao.login(inputMember);
		
		return loginMember;
	}
	
	
}
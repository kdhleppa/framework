package edu.kh.project.member.model.service;

import edu.kh.project.member.model.dto.Member;

public interface AjaxService {

	
	
	/** 닉네임으로 전화번호 검색
	 * @param nickname
	 * @return
	 */
	String selectMemberTel(String nickname);

	
	
	/** 이메일로 회원정보 조회
	 * @param email
	 * @return
	 */
	Member selectMember(String email);



	


	/** 이메일 중복 검사
	 * @param email
	 * @return
	 */
	int checkEmail(String email);



	/** 닉네임 중복 검사
	 * @param nickname
	 * @return
	 */
	int checkNickname(String nickname);
	
}

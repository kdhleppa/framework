package edu.kh.project.member.model.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import edu.kh.project.common.utility.Util;
import edu.kh.project.member.model.dao.MyPageDAO;
import edu.kh.project.member.model.dto.Member;

@Service
public class MyPageServiceImpl implements MyPageService{

@Autowired
private MyPageDAO dao;

@Transactional
@Override
public int update(Member updateMember) {
	
	return dao.update(updateMember);
}

@Override
public int updateProfile(MultipartFile profileImage, String webPath, String filePath, Member loginMember) throws Exception {
		// 프로필 이미지 변경 실패 대비
		String temp = loginMember.getProfileImage(); // 기존에 가지고 있던 이미지 저장
			
		String rename = null; // 변경 이름 저장 변수
			
		if(profileImage.getSize() >0) {
			// 1) 파일 이름 변경
			rename = Util.fileRename(profileImage.getOriginalFilename());
			
			// 2) 바뀐 이름 loginMember에 세팅
			loginMember.setProfileImage(webPath + rename);
		} else { // 업로드된 이미지가 없는 경우 (x버튼)
			loginMember.setProfileImage(null);
		}
		
		// 프로필 이미지 수정 DAO 메서드 호출 
	int result = dao.updateProfileImage(loginMember);
	
	if(result > 0) { // DB에 이미지 경로 업데이트 성공했다면
		
		// 업로드된 새 이미지가 있을 경우
		if(rename != null) {
			// 메모리에 임시 저장되어있는 파일을 서버에 진짜로 저장하는 것
			profileImage.transferTo(new File(filePath + rename));
		}
		
		
	} else { // DB 에 이미지 경로 업데이트 실패 
		// 이전 이미지로 프로필 다시 셋팅
		loginMember.setProfileImage(temp);
		
	}
	return result;
}


}

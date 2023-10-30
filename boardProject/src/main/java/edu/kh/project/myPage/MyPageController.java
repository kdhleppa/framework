package edu.kh.project.myPage;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.project.member.model.dto.Member;
import edu.kh.project.member.model.service.MemberService;
import edu.kh.project.member.model.service.MyPageService;

@SessionAttributes({"loginMember"})
// 1) Model에 세팅된 값의 key와 {}안에 작성된 값이 일치하면 session scope로 이동
// 2) Session으로 올려둔 값을 해당 클래스에서 얻어와 사용 가능하게함
// -> @SessionAttribute(key)로 사용
@RequestMapping("/myPage")
@Controller
public class MyPageController {
	
	@Autowired
	private MyPageService service;
	
	@GetMapping("/info")
	public String info() {
	
		return "myPage/myPage-info";
	}
	
	
	@GetMapping("/profile")
	public String profile() {
		return "myPage/myPage-profile";
	}
	
	
	@GetMapping("/changePw")
	public String changePw() {
		return "myPage/myPage-changePw";
	}
	
	@GetMapping("/secession")
	public String secession() {
		return "myPage/myPage-secession";
	}
	
	
	// 회원정보 수정
	@PostMapping("/info")
	public String updateInfo(@SessionAttribute("loginMember") Member loginMember,
							Member updateMember,
							String[] memberAddress,
							RedirectAttributes ra
							) {
		
		/*
		 * @SessionAttribute("loginMember") Member loginMember
		 * : Session에서 얻어온 "loginMember"에 해당하는 객채를
		 * 	매개변수 Member loginMember에 저장
		 * 
		 *  Member updateMember
		 *  : 수정할 닉네임, 전화번호 담긴 커맨드 객체
		 * 
		 * String[] memberAddress
		 * : name="memberAddress"인 input 3개의 값(주소)
		 * 
		 * RedirectAttributes ra : 리다이렉트 시 값 전달용 객체
		 * */
		
		if (updateMember.getMemberAddress().equals(",,")) {
			updateMember.setMemberAddress(null);
		} else {
			String addr = String.join("^^^",  memberAddress);
			updateMember.setMemberAddress(addr);
		}
		
		updateMember.setMemberNo(loginMember.getMemberNo());
		int result = service.update(updateMember);
		
		if (result > 0) {
			loginMember.setMemberNickname(updateMember.getMemberNickname());
			loginMember.setMemberTel(updateMember.getMemberTel());
			loginMember.setMemberAddress(updateMember.getMemberAddress());
			ra.addFlashAttribute("message", "성공");
		} else {
			ra.addFlashAttribute("message", "실패");
		}
		
		return "redirect:info"; // 상대경로(/myPage/info)
	}
	
	/* MultipartFile : input type="file"로 제출된 파일을 저장한 객체
	 * 
	 * [제공하는 메서드]
	 * - transferTo() : 파일을 지정된 경로에 저장 (메모리 -> HDD/SSD)
	 * - getOriginalFileName() : 파일 원본명
	 * - getSize() : 파일 크기
	 */
	
	
	//프로필 이미지 수정
	@PostMapping("/profile")
	public String updateProfile(
			@RequestParam("profileImage") MultipartFile profileImage,
			HttpSession session, @SessionAttribute("loginMember") Member loginMember
			, RedirectAttributes ra) throws Exception {
		
		// 웹 접근 경로
		String webPath = "/resources/images/member/";
		
		// 실제로 이미지 파일이 저장되어야하는 서버컴퓨터 경로
		String filePath = session.getServletContext().getRealPath(webPath);
		
		
		// 프로필 이미지 수정 서비스 호출
		int result = service.updateProfile(profileImage, webPath, filePath, loginMember);
		
		String message = null;
		if(result > 0) message = "프로필 이미지가 변경되었습니다";
		else message = "프로필 변경 실패";
		
		ra.addFlashAttribute("message", message);
		
		return "redirect:profile";
	}
}

package edu.kh.project.common.aop;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import edu.kh.project.member.model.dto.Member;

@Component
@Aspect // 공통 관심사가 작성된 클래스임을 지정
		// Pointcut(타겟지정) + Advice(수행할 코드)
public class BeforeAspect {
	
	private Logger logger = LoggerFactory.getLogger(BeforeAspect.class);
		// execution([접근제한자] 리턴타입 클래스명 메소드명 ([파라미터])
	
	@Order(1) // 순서, 하나의 타겟에 대한 여러 advice 수행할코드
	@Before("CommonPointCut.serviceImplPointCut()")
	public void beforeLog(JoinPoint jp) {
		// 매개변수 JoinPoint : AOP의 부가 기능이 적용된 대상의
		// 			객체, 메서드, 파라미터 정보를 얻을 수 있게 해주는 객체
		
		// 대상 객체의 간단한 클래스명(패키지명 제외)
		String className = jp.getTarget().getClass().getSimpleName();
		
		// 메서드 선언부(== 메서드 시그니처)에서 메스드명만 얻어옴
		String methodName = jp.getSignature().getName();
		
		String str = "-----------------------------------------\n";
		
		str += "[start] : " + className + " - " + methodName + "()\n";
		// [start] : MemberServiceImpl - login()
		
		str += "[Parameter] : " + Arrays.toString(jp.getArgs())+ "\n";
		
		try {
			// 접속자 IP 얻어오기
			HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
			Member loginMember = (Member)req.getSession().getAttribute("loginMember");
			
			str += "[ip]" + getRemoteAddr(req);
			if(loginMember != null) {
				str += "(email:" + loginMember.getMemberEmail() + ")";
			}
		}catch (Exception e) {
			str += "[스프링 스케쥴러]";
		}
		
		logger.info(str);
	}
	
	public static String getRemoteAddr(HttpServletRequest request) {

        String ip = null;

        ip = request.getHeader("X-Forwarded-For");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("Proxy-Client-IP"); 
        } 

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("WL-Proxy-Client-IP"); 
        } 

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("HTTP_CLIENT_IP"); 
        } 

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("HTTP_X_FORWARDED_FOR"); 
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("X-Real-IP"); 
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("X-RealIP"); 
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("REMOTE_ADDR");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getRemoteAddr(); 
        }

		return ip;
	}







}
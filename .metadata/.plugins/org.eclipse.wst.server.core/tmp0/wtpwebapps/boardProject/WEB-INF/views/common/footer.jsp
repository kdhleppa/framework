<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
    <footer>
        <p>Copyright &copy; KH Information Educational Institute E-Class </p>

        <section>
            <a href="#">프로젝트 소개</a>
            <span>|</span>
            <a href="#">이용약관</a>
            <span>|</span>
            <a href="#">개인정보 처리 방침</a>
            <span>|</span>
            <a href="#">고객센터</a>
            <span>|</span>

        </section>
    </footer>
    <%-- request에 message 가 존재할 경우 --%>
    <c:if test="${not empty message}">
    	<script>
    		// EL/JSTL 구문이 먼저 해석
    		// 문자열의 경우 따옴표가 없는 상태이니 옆에 붙여줘야함.
    	
    		alert('${message}') // ${message}
    	
    	
    	</script>
    	<%--
    		session에 message를 추가하면
    		브라우저 종료 또는 만료 전까지 계속 메시지가 출력된다
    		
    		-> 1회 출력 후 session에서 message 삭제	
    	 
    	 <c:remove var="message" scope="session"/>
    	 --%>
    </c:if>
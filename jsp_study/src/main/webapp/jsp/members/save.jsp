<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="woojin.jsp_study.domain.member.Member" %>
<%@ page import="woojin.jsp_study.domain.Repository.MemberRepository" %>

<%
	//request, response 사용 가능
	
	MemberRepository memberRepository = MemberRepository.getInstance();
	
	System.out.println("MemberRepository.service");
	String username = request.getParameter("username");
	int age = Integer.parseInt(request.getParameter("age"));
		
	Member member = new Member(username, age);
	memberRepository.save(member);
%>

<html>
<head>
 	<meta charset="UTF-8">
</head>
	<body>성공
<ul>
 	<li>id=<%=member.getId()%></li>
 	<li>username=<%=member.getUsername()%></li>
 	<li>age=<%=member.getAge()%></li>
</ul>
	<a href="/home.html">메인</a>
</body>
</html>
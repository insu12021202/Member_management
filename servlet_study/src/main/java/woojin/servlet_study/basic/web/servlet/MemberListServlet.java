package woojin.servlet_study.basic.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import woojin.servlet_study.domain.Repository.MemberRepository;
import woojin.servlet_study.domain.member.Member;

@WebServlet(name = "memberListServlet", urlPatterns = "/servlet_study/members")
public class MemberListServlet extends HttpServlet{

	MemberRepository memberRepository = MemberRepository.getInstance();
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Member> members = memberRepository.findAll();
		
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		
		PrintWriter writer = response.getWriter();
		 writer.write("<html>");
		 writer.write("<head>");
		 writer.write(" 	<meta charset=\"UTF-8\">");
		 writer.write(" 	<title>Title</title>");
		 writer.write("</head>");
		 writer.write("<body>");
		 writer.write("<a href=\"/home.html\">메인</a>");
		 writer.write("<table>");
		 writer.write(" 	<thead>");
		 writer.write(" 	<th>id</th>");
		 writer.write(" 	<th>username</th>");
		 writer.write(" 	<th>age</th>");
		 writer.write(" 	</thead>");
		 writer.write(" 	<tbody>");
		 
		 for (Member member : members) {
			 writer.write(" <tr>");
			 writer.write(" 	<td>" + member.getId() + "</td>");
			 writer.write(" 	<td>" + member.getUsername() + "</td>");
			 writer.write(" 	<td>" + member.getAge() + "</td>");
			 writer.write(" </tr>");
		 }
		 
		 writer.write("</tbody>");
		 writer.write("</table>"); writer.write("</body>");
		 writer.write("</html>");
	}
}
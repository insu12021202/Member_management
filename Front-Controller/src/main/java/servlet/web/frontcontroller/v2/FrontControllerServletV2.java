package servlet.web.frontcontroller.v2;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.web.frontcontroller.MyView;
import servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;

@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet{

	private Map<String, ControllerV2> controllerMap = new HashMap<>();
	
	public FrontControllerServletV2() {
		controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
		controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
		controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		ControllerV2 controller = controllerMap.get(requestURI);
		
		if(controller == null) {
			//response.setStatus(response.SC_FOUND);
			//String redirection = "https://www.naver.com";
			//response.setHeader("Location", redirection);
			
			response.setStatus(response.SC_NOT_FOUND);
			return;
		}
		
		// 다형성 -> View 공통 로직
		MyView view = controller.process(request, response);
		view.render(request, response);
	}
}

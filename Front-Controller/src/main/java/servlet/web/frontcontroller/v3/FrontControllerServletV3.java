package servlet.web.frontcontroller.v3;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.web.frontcontroller.ModelView;
import servlet.web.frontcontroller.MyView;
import servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet{

	private Map<String, ControllerV3> controllerMap = new HashMap<>();
	
	public FrontControllerServletV3() {
		controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
		controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
		controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		ControllerV3 controller = controllerMap.get(requestURI);
		
		if(controller == null) {
			//response.setStatus(response.SC_FOUND);
			//String redirection = "https://www.naver.com";
			//response.setHeader("Location", redirection);
			
			response.setStatus(response.SC_NOT_FOUND);
			return;
		}
		
		// paraMap
		
		// **Controller**
		Map<String, String> paraMap = createParaMap(request);
		ModelView mv = controller.process(paraMap);
		
		// **View""
		String viewName = mv.getViewName(); // View의 논리 이름만 얻음	
		MyView view = viewResolver(viewName);
		
		view.render(mv.getModel(), request, response);
	}
	
	private Map<String, String> createParaMap(HttpServletRequest request){
		Map<String, String> paraMap = new HashMap<>();
		request.getParameterNames().asIterator()
			.forEachRemaining(paraName -> paraMap.put(paraName, request.getParameter(paraName)));
		
		return paraMap;
	}
	
	private MyView viewResolver(String viewName) {
		return new MyView("/WEB-INF/views/" + viewName + ".jsp");
	}
}

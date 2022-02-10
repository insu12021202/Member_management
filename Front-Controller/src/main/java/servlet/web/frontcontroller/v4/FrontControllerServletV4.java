package servlet.web.frontcontroller.v4;

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
import servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet{

	private Map<String, ControllerV4> controllerMap = new HashMap<>();
	
	public FrontControllerServletV4() {
		controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
		controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
		controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		ControllerV4 controller = controllerMap.get(requestURI);
		
		if(controller == null) {
			//response.setStatus(response.SC_FOUND);
			//String redirection = "https://www.naver.com";
			//response.setHeader("Location", redirection);
			
			response.setStatus(response.SC_NOT_FOUND);
			return;
		}
				
		Map<String, String> paraMap = createParaMap(request);
		Map<String,Object> model = new HashMap<>();
		
		String viewName = controller.process(paraMap, model);
		MyView view = viewResolver(viewName);
		
		view.render(model, request, response);
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

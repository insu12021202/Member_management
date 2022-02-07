package servlet.web.frontcontroller.v5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet{
	
	private final Map<String, Object> handlerMappingMap = new HashMap<>();
	private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();
	
	public FrontControllerServletV5(){
		initHandlerMappingMap();
		initHandlerAdapters();
	}

	private void initHandlerMappingMap() {
		handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
		handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
		handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
	}
	
	private void initHandlerAdapters() {
		handlerAdapters.add(new ControllerV3HandlerAdapter());
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 핸들러 조회
		Object handler = getHandler(request);
		
		if(handler == null) {
			//response.setStatus(response.SC_FOUND);
			//String redirection = "https://www.naver.com";
			//response.setHeader("Location", redirection);
			
			response.setStatus(response.SC_NOT_FOUND);
			return;
		}
		
		//2. 핸들러를 처리할 수 있는 핸들러 어댑터 조회
		MyHandlerAdapter adapter = getHandlerAdapter(handler);
		
		//3. 어댑터를 이용해 ModelView 얻기
		ModelView mv = adapter.handle(request, response, handler);
		
		//4. viewName을 이용해 viewPath 얻기
		String viewName = mv.getViewName(); // View의 논리 이름만 얻음	
		MyView view = viewResolver(viewName);
		
		view.render(mv.getModel(), request, response);
	}

	private MyHandlerAdapter getHandlerAdapter(Object handler) {
		for(MyHandlerAdapter adapter : handlerAdapters) {
			if(adapter.supports(handler)) {
				return adapter;
			}
		}
		
		throw new IllegalArgumentException("handler adpater를 찾을 수 없습니다. handler= "+handler);
	}

	private Object getHandler(HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		return handlerMappingMap.get(requestURI);
	}
	
	private MyView viewResolver(String viewName) {
		return new MyView("/WEB-INF/views/" + viewName + ".jsp");
	}
	
}
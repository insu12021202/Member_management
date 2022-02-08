package servlet.web.frontcontroller.v5.adapter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.web.frontcontroller.ModelView;
import servlet.web.frontcontroller.v4.ControllerV4;
import servlet.web.frontcontroller.v5.MyHandlerAdapter;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter{

	@Override
	public boolean supports(Object handler) {
		return (handler instanceof ControllerV4);
	}

	@Override
	public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
		ControllerV4 controller = (ControllerV4) handler;
		
		Map<String, String> paraMap = createParaMap(request);
		Map<String, Object> model = new HashMap<>();
		String viewName = controller.process(paraMap, model);
		
		ModelView mv = new ModelView(viewName);
		mv.setModel(model);
		
		return mv;
	}
	
	private Map<String, String> createParaMap(HttpServletRequest request){
		Map<String, String> paraMap = new HashMap<>();
		
		request.getParameterNames().asIterator()
			.forEachRemaining( paraName -> paraMap.put(paraName, request.getParameter(paraName)));
		
		return paraMap;
	}
}

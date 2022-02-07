package servlet.web.frontcontroller.v4.controller;

import java.util.Map;

import servlet.web.frontcontroller.v4.ControllerV4;

public class MemberFormControllerV4 implements ControllerV4{

	@Override
	public String process(Map<String, String> paraMap, Map<String, Object> model) {
		return "new-form";
	}

}

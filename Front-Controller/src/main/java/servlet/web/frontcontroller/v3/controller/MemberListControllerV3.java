package servlet.web.frontcontroller.v3.controller;

import java.util.List;
import java.util.Map;

import servlet.domain.Repository.MemberRepository;
import servlet.domain.member.Member;
import servlet.web.frontcontroller.ModelView;
import servlet.web.frontcontroller.v3.ControllerV3;

public class MemberListControllerV3 implements ControllerV3{

	MemberRepository memberRepository = MemberRepository.getInstance();
	
	@Override
	public ModelView process(Map<String, String> paraMap) {
		List<Member> members = memberRepository.findAll();
		
		ModelView mv = new ModelView("members");
		mv.getModel().put("members", members);
		
		return mv;
	}

	
}

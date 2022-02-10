package servlet.web.frontcontroller.v3.controller;

import java.util.Map;

import servlet.domain.Repository.MemberRepository;
import servlet.domain.member.Member;
import servlet.web.frontcontroller.ModelView;
import servlet.web.frontcontroller.v3.ControllerV3;

public class MemberSaveControllerV3 implements ControllerV3 {

	MemberRepository memberRepository = MemberRepository.getInstance();
	
	@Override
	public ModelView process(Map<String, String> paraMap) {
		String username = paraMap.get("username");
		int age = Integer.parseInt(paraMap.get("age"));
		
		Member member = new Member(username, age);
		memberRepository.save(member);
		
		ModelView mv = new ModelView("save-result");
		mv.getModel().put("member", member);
		return mv;
	}

}

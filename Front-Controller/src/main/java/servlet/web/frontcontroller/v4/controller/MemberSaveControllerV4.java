package servlet.web.frontcontroller.v4.controller;

import java.util.Map;

import servlet.domain.Repository.MemberRepository;
import servlet.domain.member.Member;
import servlet.web.frontcontroller.v4.ControllerV4;

public class MemberSaveControllerV4 implements ControllerV4 {

	MemberRepository memberRepository = MemberRepository.getInstance();
	
	@Override
	public String process(Map<String, String> paraMap, Map<String, Object> model) {
		String username = paraMap.get("username");
		int age = Integer.parseInt(paraMap.get("age"));
		
		Member member = new Member(username, age);
		memberRepository.save(member);
		
		model.put("member", member);
		return "save-result";
	}

}

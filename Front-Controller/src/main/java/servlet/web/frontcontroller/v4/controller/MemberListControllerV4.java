package servlet.web.frontcontroller.v4.controller;

import java.util.List;
import java.util.Map;

import servlet.domain.Repository.MemberRepository;
import servlet.domain.member.Member;
import servlet.web.frontcontroller.v4.ControllerV4;

public class MemberListControllerV4 implements ControllerV4{

	MemberRepository memberRepository = MemberRepository.getInstance();
	
	@Override
	public String process(Map<String, String> paraMap, Map<String, Object> model) {
		List<Member> members = memberRepository.findAll();
		
		model.put("members", members);
		
		return "members";
	}

	
}

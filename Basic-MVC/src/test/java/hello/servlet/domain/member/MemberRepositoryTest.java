package hello.servlet.domain.member;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import servlet.domain.Repository.MemberRepository;
import servlet.domain.member.Member;

public class MemberRepositoryTest {
	
	MemberRepository memberRepository = MemberRepository.getInstance();
	
	@AfterEach // 각 Test가 실행된 이후 적용
	void afterEach() {
		memberRepository.clearStore();
	}
	
	@Test
	void save() {
		//given ~가 주어졌을 때
		Member member = new Member("woojin",24);
		
		//when ~를 실행했을 때
		Member saveMember = memberRepository.save(member);
		
		//then 결과가 ~해야함
		Member findMember = memberRepository.findById(saveMember.getId());
		Assertions.assertThat(findMember).isEqualTo(saveMember);
	}
	
	@Test
	void findAll() {
		//given
		Member member1 = new Member("member1", 21);
		Member member2 = new Member("member2", 22);
		
		memberRepository.save(member1);
		memberRepository.save(member2);
		
		//when
		List<Member> result = memberRepository.findAll();
		
		//then
		Assertions.assertThat(result.size()).isEqualTo(2);
		Assertions.assertThat(result).contains(member1, member2);
	}
}

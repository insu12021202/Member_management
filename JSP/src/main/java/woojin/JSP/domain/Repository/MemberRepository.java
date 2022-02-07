package woojin.JSP.domain.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import woojin.JSP.domain.Member.Member;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */

public class MemberRepository {
	
	private Map<Long, Member> store = new HashMap<>();
	private long sequence = 0L;
	
	// 싱글톤 객체로 생성
	private static final MemberRepository instance = new MemberRepository();
	
	// 생성자를 막아놓음
	private MemberRepository() {
	}
	
	public static MemberRepository getInstance() {
		return instance;
	}
	
	public Member save(Member member) {
		member.setId(++sequence);
		store.put(member.getId(),member);
		return member;
	}
	
	public Member findById(Long id) {
		return store.get(id);
	}
	
	public List<Member> findAll(){
		// store에 있는 값을 보존하기 위해 새로운 객체 반환
		return new ArrayList<>(store.values());
	}
	
	public void clearStore() {
		store.clear();
	}
}
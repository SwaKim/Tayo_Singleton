package kr.or.ddit.member;

import java.util.Map;

import kr.or.ddit.vo.MemberVO;

public interface MemberDao {

	boolean createMember(MemberVO joinMemberVO);

	boolean deleteMember(int id);

	boolean idCheck(String menberid);

	MemberVO findThisMember(Map<String, String> login);

	MemberVO chargeMoney(int id, int money);

	boolean showMemberList();

	

	
}

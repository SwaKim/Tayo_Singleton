package kr.or.ddit.member;

import java.util.Map;

public interface MemberDao {

	boolean createMember(Map<String, String> member);

	boolean deleteMember(int id);

	boolean idCheck(String menberid);

	int loginCheck(Map<String, String> login);

	int chargeMoney(int id, int money);

	boolean showMemberList();

	

	
}

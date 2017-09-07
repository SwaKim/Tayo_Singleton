package kr.or.ddit.member;

import java.util.Map;

import kr.or.ddit.database.Database;
import kr.or.ddit.vo.MemberVO;

public class MemberDaoImpl implements MemberDao {

	private static MemberDaoImpl memberDao = null;
	private Database db = null;

	private MemberDaoImpl() {
		db = Database.getInstance();
	}

	public static MemberDao getInstance() {
		if(memberDao == null){
			memberDao = new MemberDaoImpl();
		}
		return memberDao;
	}

	@Override
	public boolean createMember(MemberVO joinMemberVO) {
		
		return db.createMember(joinMemberVO);
	}

	@Override
	public boolean deleteMember(int id) {
		
		return db.deleteMember(id);
	}

	@Override
	public boolean idCheck(String menberid) {
		
		return db.joinIdCheck(menberid);
	}
	
	//로그인
	@Override
	public MemberVO findThisMember(Map<String, String> login) {
		return db.readThisMember(login);
	}
	
	@Override
	public MemberVO chargeMoney(int id, int money) {
		
		return db.chargeMoney(id, money);
	}
	
	@Override
	public boolean showMemberList() {
		if (db.getListSize("member") == 0) {
			return false;
		} else {
			for (int i = 0; i < db.getListSize("member"); i++) {
				System.out.println(db.getMemberList(i));
			}
		}
		return true;
	}




}
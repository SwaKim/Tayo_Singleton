package kr.or.ddit.member;

import java.util.Map;

import kr.or.ddit.database.Database;

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
	public boolean createMember(Map<String, String> member) {
		
		return db.createMember(member);
	}

	@Override
	public boolean deleteMember(int id) {
		
		return db.deleteMember(id);
	}

	@Override
	public boolean idCheck(String menberid) {
		
		return db.idCheck(menberid);
	}
	
	@Override
	public int loginCheck(Map<String, String> login) {
		try {
			if (db.readIdPwFromDB(login).get("userId").equals(login.get("userId"))) {	// 회원

				if (!db.readIdPwFromDB(login).get("userPw").equals(login.get("userPw"))) {
					return -1;// 비밀번호틀림

				} else {
					if (db.readIdPwFromDB(login).get("isAdmin").equals("true")) {
						return -2;// 관리자 로그인

					} else {
						return Integer.parseInt(db.readIdPwFromDB(login).get("index"));	// 로그인 완료, 해당 회원 인덱스값 반환
					}
				}
			}
		} catch (NullPointerException e) {
			return -3;
		}
		

		return -3;																		// 아이디 없음.
	}
	
	@Override
	public int chargeMoney(int id, int money) {
		
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
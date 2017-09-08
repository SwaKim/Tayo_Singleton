package kr.or.ddit.member;

import java.util.Map;

import kr.or.ddit.vo.MemberVO;

/**
 * @Class Name : MemberServiceImpl.java
 * @Description 
 * @Modification Information
 * @author 현우석, 이중우, 김수환
 * @since 2017.08.28.
 * @version 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력(Modification Information) >>
 *		수정일			수정자		수정내용      
 *   -------        	-------		-------------------
 *	2017.08.2			현우석		최초생성
 *	2017.09.04			현우석		싱글톤적용
 * Copyright (c) 2017 by DDIT  All right reserved
 *      </pre>
 */
public class MemberServiceImpl implements MemberService{
	
	private static MemberService memberService = null;
	private MemberDao memberDao = null;
	
	private MemberServiceImpl(){
		memberDao = MemberDaoImpl.getInstance();
	}
	
	public static MemberService getInstance() {
		if(memberService == null){
			memberService = new MemberServiceImpl();
		}
		
		return memberService;
	}
	
	// 회원추가
	@Override
	public boolean joinMember(MemberVO joinMemberVO) {

		return memberDao.createMember(joinMemberVO);
	}

	// 회원삭제
	@Override
	public boolean deleteMember(int id) {

		return memberDao.deleteMember(id);
	}

	// 회원가입시 아이디 중복체크
	@Override
	public boolean checkJoinId(String menberid) {

		return memberDao.idCheck(menberid);
	}

	// 아이디체크
	@Override
	public int loginCheck(Map<String, String> loginVO) {
		MemberVO memberFromDB = memberDao.findThisMember(loginVO);
		try {
			if (memberFromDB.getMbUserId().equals(loginVO.get("userId"))) {	// 회원

				if (!memberFromDB.getMbUserPw().equals(loginVO.get("userPw"))) {
					return -1;// 비밀번호틀림

				} else {
					if (memberFromDB.isAdmin()==true) {
						return -2;// 관리자 로그인

					} else {
						return memberFromDB.getIndex();	// 로그인 완료, 해당 회원 인덱스값 반환
					}
				}
			}
		} catch (NullPointerException e) {
			return -3;
		}
		

		return -3;																		// 아이디 없음.
	}
	
	// 충전
	@Override
	public MemberVO chargeMoney(int id, int money) {
		
		return memberDao.chargeMoney(id, money);
	}
	
	// 회원 맴버리스트
	@Override
	public boolean showMemberList() {
		
		return memberDao.showMemberList();
	}
}

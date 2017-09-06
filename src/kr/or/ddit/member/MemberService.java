package kr.or.ddit.member;

import java.util.Map;

/**
 * @Class Name : MemberService.java
 * @Description 
 * @Modification Information
 * @author 현우석, 이중우, 김수환
 * @since 2017.08.28.
 * @version 1.0
 * @see <pre>
 * << 개정이력(Modification Information) >>
 * 		수정일			수정자		수정내용      
 *   -------        	-------		-------------------
 *	2017.08.29			이중우		최초생성
 *	2017.09.04			현우석		싱글톤적용
 * Copyright (c) 2017 by DDIT  All right reserved
 * </pre>
 */
public interface MemberService {

	/**
	 * 회원 가입 회원 가입에 필요한 id pwd 이름 생성해준다.
	 * 
	 * @param Map<s,s> member
	 * @return boolean
	 */
	public boolean joinMember(Map<String, String> member);

	/**
	 * 회원 삭제 List에서 id와 일치하는 것을 찾아 remove해준다.
	 * 
	 * @param id
	 * @return boolean
	 */
	public boolean deleteMember(int id);

	/**
	 * 회원가입
	 * 아이디 중복체크
	 * @param 입력된   멤버id
	 * @return true(적합) / false(부적합)
	 */
	public boolean checkJoinId(String menberid);

	/**
	 * 로그인체크 id와 pwd와 관리자인지 체크해준다.
	 * @param 입력한 login의 정보
	 * @return int 0 : 일반회원로그인 -1 : 비밀번호틀림 -2 : 관리자로그인 -3 : 아이디 없음
	 */
	public int loginCheck(Map<String, String> login);

	/**
	 * 충전 
	 * 로그인된 유저 아이디에 돈을 충전해준다.
	 * @param 유저 id 금액 money
	 * @return int money금액
	 */
	public int chargeMoney(int id, int money);

	/**
	 * 관리자:회원리스트
	 * 
	 * @return boolean
	 */
	public boolean showMemberList();

}

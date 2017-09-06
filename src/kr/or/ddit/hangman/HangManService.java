package kr.or.ddit.hangman;

import java.util.Map;

/**
 * @Class Name : MemberService.java
 * @Description 
 * @Modification Information
 * @author 김수환
 * @since 2017.09.07.
 * @version 1.0
 * @see <pre>
 * << 개정이력(Modification Information) >>
 * 		수정일			수정자		수정내용      
 *   -------        	-------		-------------------
 *	2017.09.27			김수환		최초생성
 *	2017.09.08			김수환		싱글톤적용
 * Copyright (c) 2017 by DDIT  All right reserved
 * </pre>
 */
public interface HangManService {
	
	/**
	 * 단어를 준비합니다. word.txt
	 * @author	김수환
	 * @exception 내부에서 File, IO 익셉션 처리
	 * @return	String 답안
	 */
	public String setWord();
	
	/**
	 * 단어의 스펠링 중 임의의 글자를 빈칸으로 만들어줍니다.
	 * @author	김수환
	 * @param	int 레벨
	 * @return	String 문제
	 */
	public Map<String, Object> setBlank(int n);
}

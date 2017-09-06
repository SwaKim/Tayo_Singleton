package kr.or.ddit.ticket;

import java.util.Map;

/**
 * @Class Name : TicketService.java
 * @Description 
 * @Modification Information
 * @author 현우석, 이중우, 김수환
 * @since 2017.08.28.
 * @version 1.0
 * @see <pre>
 * << 개정이력(Modification Information) >>
 *		수정일			수정자		수정내용      
 *   -------        	-------		-------------------
 *	2017.08.29			이중우		최초생성
 *	2017.09.04			김수환		싱글톤용
 * Copyright (c) 2017 by DDIT  All right reserved
 * </pre>
 */
public interface TicketService {

	/**
	 * 티켓 생성 및 결제
	 * 
	 * @param Map<s,s> ticket          
	 * @return int value 구입후잔액
	 *            -1   해당 노선이 존재하지 않습니다
	 *            -2   좌석이 이미 판매되었습니다.
	 *            -3   잔액이 부족합니다.
	 */
	public int payBusTicket(Map<String, String> ticket);

	/**
	 * 환불메서드 회원이 가지고있는 티켓이면 환불 아니면 실패
	 * 
	 * @param 로그인된  회원인덱스, 티켓인덱스
	 * @return int -1 티켓이 없다. -2 구매자가 아니다.
	 */
	public int refundTicket(int loginid, int tinput);

	/**
	 * 구매목록 조회 로그인된 아이디의 구매리스트를 반환
	 * 
	 * @param 로그인된  회원의 인덱스값
	 * @return boolean
	 */
	public boolean showTicketList(int loginid);

	/**
	 * 총 판매 금액
	 * 
	 * @param
	 * @return
	 */
	public boolean showTotalTicketList();

	/**
	 * 총 판매 금액
	 * 
	 * @param
	 * @return
	 */
	public int calcTotal();
	
}

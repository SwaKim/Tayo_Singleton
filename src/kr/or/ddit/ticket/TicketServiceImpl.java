package kr.or.ddit.ticket;

import java.util.Map;

/**
 * @Class Name : TicketServiceImpl.java
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
 *	2017.08.29			현우석		최초생성
 *	2017.09.04			김수환		싱글톤적용
 * Copyright (c) 2017 by DDIT  All right reserved
 *      </pre>
 */
public class TicketServiceImpl implements TicketService{

	private static TicketService ticketService = null;
	private TicketDao ticketDao = null;
	//private MemberDao memberDao = null;
	
	private TicketServiceImpl() {
		ticketDao = TicketDaoImpl.getInstance();
		//memberDao = MemberDaoImpl.getInstance();
	}
	
	public static TicketService getInstance(){
		if(ticketService == null){
			ticketService = new TicketServiceImpl(); 
		}
		return ticketService;
	}
	
	// 티켓구입 = 티켓 생성
	@Override
	public int payBusTicket(Map<String, String> ticket) {

		int result = ticketDao.payBusTicket(ticket); 
		if(result == -1){
			return -1;
		}else if(result == -2){
			return -2;
		}else if(result == -3){
			return -3;
		}

		return result;// db.createTicket(ticket);
	}



	// 환불 -1  티켓이 없다. -2 구매자가 아니다.
	@Override
	public int refundTicket(int loginid, int tinput) {
		int result = ticketDao.refundTicket(loginid, tinput);
		if (result == -1) {
			return -1;
		} else if (result == -2) {
			return -2;
		}
		return result;
	}
	
	// 회원메뉴티켓리스트
	@Override
	public boolean showTicketList(int loginid) {
		
		return ticketDao.showTicketList(loginid);
	}
	
	// 관리자용티켓리스트
	@Override
	public boolean showTotalTicketList() {
		
		return ticketDao.showTotalTicketList();
	}
	
	// 총금액
	@Override
	public int calcTotal() {
		
		return ticketDao.calcTotal();
	}
}

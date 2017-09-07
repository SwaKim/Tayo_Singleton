package kr.or.ddit.ticket;

import java.util.Map;

import kr.or.ddit.database.Database;
import kr.or.ddit.vo.TicketVO;

public class TicketDaoImpl implements TicketDao{

	private static TicketDaoImpl ticketDao = null;
	private Database db = null;
	
	private TicketDaoImpl(){
		db = Database.getInstance();
	}
	
	
	
	public static TicketDao getInstance() {
		if( ticketDao == null ){
			ticketDao = new TicketDaoImpl();
		}
		return ticketDao;
	}
	
	@Override
	public int payBusTicket(TicketVO paidVo) {
		
		return db.createTicket(paidVo);// db.createTicket(ticket);
	}
	
	// 환불 -1  티켓이 없다. -2 구매자가 아니다.
	@Override
	public int refundTicket(int loginid, int tinput) {
		
		return db.deleteTicket(loginid, tinput);
	}
	
	// 회원메뉴티켓리스트
	@Override
	public boolean showTicketList(int loginid) {
		if(db.getTicketListSize(loginid) == 0){								//구매한티켓이 한개도 없을때
			return false;
		}
		for (int i = 0; i < db.getTotalTicketList().size(); i++) {			//구매한 티켓이 있을때.
			if (db.getTicketListString(loginid).get(i) != null) {
				System.out.println(db.getTicketListString(loginid).get(i));
			}

		}

		return true;
	}
	// 관리자용티켓리스트
	@Override
	public boolean showTotalTicketList() {
		for (int i = 0; i < db.getTotalTicketList().size(); i++) {
			System.out.println(db.getTotalTicketList().get(i));
		}
		return true;
	}
	
	// 총금액
	@Override
	public int calcTotal() {
		int money = db.allPayMoney();
		return money;
	}
}

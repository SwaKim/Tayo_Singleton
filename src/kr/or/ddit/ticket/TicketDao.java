package kr.or.ddit.ticket;

import kr.or.ddit.vo.TicketVO;

public interface TicketDao {

	int payBusTicket(TicketVO paidVo);

	int refundTicket(int loginid, int tinput);

	boolean showTicketList(int loginid);

	boolean showTotalTicketList();

	int calcTotal();


}

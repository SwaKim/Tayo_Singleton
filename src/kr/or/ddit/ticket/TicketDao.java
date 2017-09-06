package kr.or.ddit.ticket;

import java.util.Map;

public interface TicketDao {

	int payBusTicket(Map<String, String> ticket);

	int refundTicket(int loginid, int tinput);

	boolean showTicketList(int loginid);

	boolean showTotalTicketList();

	int calcTotal();


}

package kr.or.ddit.bus;

import java.util.Map;

public interface BusDao{

	boolean createBus(Map<String, String> busAdd);

	boolean deleteBus(int id);

	boolean showBusList();

}

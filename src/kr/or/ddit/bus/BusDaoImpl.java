package kr.or.ddit.bus;

import java.util.Map;

import kr.or.ddit.database.Database;

public class BusDaoImpl implements BusDao{
	
	private static BusDaoImpl busDao = null;
	private Database db = null;
	
	private BusDaoImpl() {
		db = Database.getInstance();
	}
	
	public static BusDao getInstance(){
		if(busDao == null){
			busDao = new BusDaoImpl();
		}
		return busDao;
	}
	
	// 버스추가 
	@Override
	public boolean createBus(Map<String, String> busAdd) {

		return db.createBus(busAdd);// ;
	}

	// 버스삭제  
	@Override
	public boolean deleteBus(int id) {

		return db.deleteBus(id);
	}

	// 버스변경  
	@Override
	public boolean changeBus(int id) {

		return db.changeBus(id);
	}

	// 버스리스트
	public boolean showBusList() {
		// busVosize
		if (db.getListSize("bus") == 0) {
			return false;
		} else {
			for (int i = 0; i < db.getListSize("bus"); i++) {
				System.out.println(db.getBusList(i));
			}
			return true;
		}
	}
}

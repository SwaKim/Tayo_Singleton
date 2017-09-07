package kr.or.ddit.bus;

import java.util.Map;

/**
 * @Class Name : BusServiceImpl.java
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
 *	2017.09.04			이중우		싱글톤적용
 * Copyright (c) 2017 by DDIT  All right reserved
 *      </pre>
 */
public class BusServiceImpl implements BusService{

	private static BusServiceImpl busService = null;
	private BusDao busDao = null;
	
	private BusServiceImpl(){
		busDao = BusDaoImpl.getInstance();
	}
	
	public static BusService getInstance(){
		if(busService == null){
			busService = new BusServiceImpl();
		}
		return busService;
	}
	
	// 버스추가 
	@Override
	public boolean addBus(Map<String, String> busAdd) {

		return busDao.createBus(busAdd);
	}
	
	// 버스삭제  
	@Override
	public boolean removeBus(int id) {

		return busDao.deleteBus(id);
	}
	
	// 버스변경
	@Override
	public boolean changeBus(int id) {

		return busDao.deleteBus(id);
	}
	
	// 버스리스트
	public boolean showBusList() {
		// busVosize
		
		return busDao.showBusList();
		
	}
}

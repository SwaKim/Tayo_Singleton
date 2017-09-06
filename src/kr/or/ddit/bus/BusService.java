package kr.or.ddit.bus;

import java.util.Map;

/**
 * @Class Name : BusService.java
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
 *	2017.09.04			이중우		싱글톤적용
 * Copyright (c) 2017 by DDIT  All right reserved
 * </pre>
 */
public interface BusService {

	/**
	 * 노선 추가 
	 * 버스의 노선을 추가해준다.
	 * @param busadd
	 * @return boolean
	 */
	public boolean addBus(Map<String, String> busAdd);

	/**
	 * 노선삭제 
	 * 버스의 id값을 받아와 삭제한다.
	 * @param id
	 * @return boolean
	 */
	public boolean removeBus(int id);

	/**
	 * 관리자:버스리스트
	 * 관리자페이지에서 버스 리스트를 보여준다. 
	 * @return boolean
	 */
	public boolean showBusList();

}

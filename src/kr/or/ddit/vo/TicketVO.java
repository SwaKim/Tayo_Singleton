package kr.or.ddit.vo;


public class TicketVO{
	//어느 VO클래스건 index의 id
	private int index;         //기본키

	private String tkBuyTime;      //구매시간

	private boolean isTkUsed;   //사용여부

	//티켓은 외래키로 각 정보를 빌려서 받아온다.
	//참조
	private int memIndex;				//외래키 소유자
	private int busIndex;				//외래키 버스 노선, 요금, 출발시간, 종류
	private int seatIndex;			//외래키 좌석


	public int getSeatIndex() {
		return seatIndex;
	}
	public void setSeatIndex(int seatIndex) {
		this.seatIndex = seatIndex;
	}
	public int getId() {
		return index;
	}
	public void setId(int mbIndex) {
		this.index = mbIndex;
	}
	public String getTkBuyTime() {
		return tkBuyTime;
	}
	public void setTkBuyTime(String tkBuyTime) {
		this.tkBuyTime = tkBuyTime;
	}
	public boolean isTkUsed() {
		return isTkUsed;
	}
	public void setTkUsed(boolean isTkUsed) {
		this.isTkUsed = isTkUsed;
	}
	public int getMemIndex() {
		return memIndex;
	}
	public void setMemIndex(int memIndex) {
		this.memIndex = memIndex;
	}
	public int getBusIndex() {
		return busIndex;
	}
	public void setBusIndex(int busIndex) {
		this.busIndex = busIndex;
	}
	@Override
	public String toString() {
		return "TicketVO [id=" + index + ", tkBuyTime=" + tkBuyTime + ", isTkUsed=" + isTkUsed + ", memId=" + memIndex
				+ ", busId=" + busIndex + "]";
	}
}
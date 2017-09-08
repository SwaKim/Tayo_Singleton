package kr.or.ddit.vo;


public class BusVO {
	//어느 VO클래스건 index의 id
	private int index;				//기본키

	private String bsRoute;			//출발지-도착지

	private String bsPrice;			//요금

	private String bsDepartureTime;	//출발시간

	private int bsTotalSeat;		//좌석정보

	private String bsKind;			//일반?우등?

	private String isExfired;		//만료여부


	public String getIsExfired() {
		return isExfired;
	}

	public void setIsExfired(String isExfired) {
		this.isExfired = isExfired;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getBsRoute() {
		return bsRoute;
	}

	public void setBsRoute(String bsRoute) {
		this.bsRoute = bsRoute;
	}

	public String getBsPrice() {
		return bsPrice;
	}

	public void setBsPrice(String bsPrice) {
		this.bsPrice = bsPrice;
	}

	public String getBsDepartureTime() {
		return bsDepartureTime;
	}

	public void setBsDepartureTime(String bsDepartureTime) {
		this.bsDepartureTime = bsDepartureTime;
	}


	public int getBsTotalSeat() {
		return bsTotalSeat;
	}

	public void setBsTotalSeat(int size) {
		this.bsTotalSeat = size; 
	}

	public String getBsKind() {
		return bsKind;
	}

	public void setBsKind(String bsKind) {
		this.bsKind = bsKind;
	}

	@Override
	public String toString() {
		return "BusVO [id=" + index + ", bsRoute=" + bsRoute + ", bsPrice=" + bsPrice + ", bsDepartureTime="
				+ bsDepartureTime + ", bsSeat=" + bsTotalSeat + ", bsKind=" + bsKind + "]";
	}


}
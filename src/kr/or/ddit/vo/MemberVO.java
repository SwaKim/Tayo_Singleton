package kr.or.ddit.vo;

public class MemberVO {
	//어느 VO클래스건 index의 id
	private int index;			//기본키

	private boolean isAdmin;		//분류 = 회원 /관리자

	private String mbUserId;		//아이디

	private String mbUserPw;		//패스워드

	private String mbUserName;	//이름

	private int mbUserMoney;		//충전된 금액

	private String mbJoinDate;	//가입일자


	public String getMbJoinDate() {
		return mbJoinDate;
	}

	public void setMbJoinDate(String mbJoinDate) {
		this.mbJoinDate = mbJoinDate;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int mbIndex) {
		this.index = mbIndex;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getMbUserId() {
		return mbUserId;
	}

	public void setMbUserId(String mbUserId) {
		this.mbUserId = mbUserId;
	}

	public String getMbUserPw() {
		return mbUserPw;
	}

	public void setMbUserPw(String mbUserPw) {
		this.mbUserPw = mbUserPw;
	}

	public String getMbUserName() {
		return mbUserName;
	}

	public void setMbUserName(String mbUserName) {
		this.mbUserName = mbUserName;
	}

	public int getMbUserMoney() {
		return mbUserMoney;
	}

	public void setMbUserMoney(int mbUserMoney) {
		this.mbUserMoney += mbUserMoney;
	}


	@Override
	public String toString() {
		return "MemberVO [id=" + index + ", isAdmin=" + isAdmin + ", mbUserId=" + mbUserId + ", mbUserPw=" + mbUserPw
				+ ", mbUserName=" + mbUserName + ", mbUserMoney=" + mbUserMoney + "]";
	}
}
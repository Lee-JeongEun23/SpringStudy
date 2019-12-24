package kr.or.bit.dto;

public class ChattingRoom {
	private String roomname;
	private String master;
	private int roomuser;
	private String roompassword;
	
	
	public ChattingRoom(String roomname, String master, int roomuser, String roompassword) {
		super();
		this.roomname = roomname;
		this.master = master;
		this.roomuser = roomuser;
		this.roompassword = roompassword;
	}
	

	public String getRoomname() {
		return roomname;
	}


	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}


	public String getMaster() {
		return master;
	}


	public void setMaster(String master) {
		this.master = master;
	}


	public int getRoomuser() {
		return roomuser;
	}


	public void setRoomuser(int roomuser) {
		this.roomuser = roomuser;
	}


	public String getRoompassword() {
		return roompassword;
	}


	public void setRoompassword(String roompassword) {
		this.roompassword = roompassword;
	}


	@Override
	public String toString() {
		return "ChattingRoom [roomname=" + roomname + ", master=" + master + ", roomuser=" + roomuser
				+ ", roompassword=" + roompassword + "]";
	}
		
	
}

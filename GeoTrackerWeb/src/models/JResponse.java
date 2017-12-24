package models;

public class JResponse {

	private boolean isOk;
	private Object extra;
	
	public JResponse(boolean isOk, Object extra) {
		this.isOk = isOk;
		this.extra = extra;
	}
	
	public boolean isOk() {
		return isOk;
	}
	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}
	public Object getExtra() {
		return extra;
	}
	public void setExtra(Object extra) {
		this.extra = extra;
	}
}

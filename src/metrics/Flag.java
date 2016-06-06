package metrics;

public class Flag {

	private volatile boolean flag;

	public Flag(boolean initFlag) {
		this.flag = initFlag;
	}
	
	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
}

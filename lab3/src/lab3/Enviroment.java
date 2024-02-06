package lab3;

import java.util.concurrent.ThreadLocalRandom;

public class Enviroment {
	private int MIN_IN_TIME = 200;
	private int MAX_IN_TIME = 1500;

	private int MIN_OUT_TIME = 300;
	private int MAX_OUT_TIME = 2000;
	
	private int MUSEUM_WORK_TIME = 5000;
	
	private int MUSEUM_CHECK_TIME = 2000;
	
	private int numOfCurVisitors = 0;
	
	public Enviroment(int minInTime, int maxInTime, int minOutTime, int maxOutTime) {
		MIN_IN_TIME = minInTime;
		MAX_IN_TIME = maxInTime;

		MIN_OUT_TIME = minOutTime;
		MAX_OUT_TIME = maxOutTime;
	}
	
	public Enviroment() {
		
	}

	public void newVisitorEntered() {
		numOfCurVisitors++;
	}
	
	public void setCheckTime(int checkTime) {
		MUSEUM_CHECK_TIME = checkTime;
	}
	
	public void setWorkTime(int workTime) {
		MUSEUM_WORK_TIME = workTime;
	}
	
	public final int getTimeIn() {
		return ThreadLocalRandom.current().nextInt(MIN_IN_TIME, MAX_IN_TIME);
	}
	
	public final int getTimeOut() {
		return ThreadLocalRandom.current().nextInt(MIN_OUT_TIME, MAX_OUT_TIME);
	}
	
	public final int getWorkTime() {
		return MUSEUM_WORK_TIME;
	}
	
	public final int getCheckTime() {
		return MUSEUM_CHECK_TIME;
	}

	public void visitorOuted() {
		numOfCurVisitors--;
	}
	
	public final int getNumOfCurVisitors() {
		return numOfCurVisitors;
	};
}

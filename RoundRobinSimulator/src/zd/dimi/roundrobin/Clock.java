package zd.dimi.roundrobin;

// Class that represents the clock of the system

public class Clock {
	
	// Current number of clock ticks
    protected static int ticks; 
	
	// Constructor
	public Clock(){	
		this.ticks = 0;	
	};
	
	// Increases the variable of clock ticks by 1
	public void timeRun(){	
		this.ticks = this.ticks + 1;
		
	}
	
	// Returns time based on the value of ticks variable
	public int showTime(){  	
		return ticks;	
	}

}

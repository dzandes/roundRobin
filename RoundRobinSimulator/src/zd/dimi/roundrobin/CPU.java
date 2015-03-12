package zd.dimi.roundrobin;
// Class that represents the CPU of the system

public class CPU {
	
	// Running process in CPU
    private Process runningProcess;
 
    // Time to next context switch
    private int timeToNextContextSwitch;
 
    // Time when the last process starts
    private int lastProcessStartTime;
    
    // Current tick of the CPU's clock
    private int currentTick;
 
    // Necessary Clock
    private Clock cl;
     
    // Necessary Scheduler
    private RRScheduler rrs;
     
     
    // Constructor 
    public CPU(Clock c, RRScheduler r){
         
        this.timeToNextContextSwitch=0;
        this.lastProcessStartTime = 0;
        this.cl = c;
        this.rrs = r;              
    }
     
    // When a process is added, only variable running process is changed
    // All the other job is done in "execute" method
    public void addProcess(Process process){
         
        this.runningProcess = process;
        this.lastProcessStartTime = this.cl.showTime();
        this.timeToNextContextSwitch = this.cl.showTime() + this.rrs.getQuantum();
        this.runningProcess.setProcessState(2);     
    }
 
    // Returns the running process and then sets the variable equal to null
    public Process removeProcessFromCpu(){
         
    	if(this.runningProcess.getCpuRemainingTime() == 0){    
            this.runningProcess.setProcessState(3); 
        }else{       
            this.runningProcess.setProcessState(1);     
        }
        Process tempPr = this.runningProcess;
        this.runningProcess = null;
        return tempPr;
    }
     
    public void execute(){
         
        // Every time execute is called, one unit of time has passed
        this.runningProcess.changeCpuRemainingTime(this.runningProcess.getCpuRemainingTime() - 1);
        
        // Printer for check
        System.out.println("Current clock tick: " + cl.showTime() + " / " + "Running process ID: " + this.runningProcess.getProcessId() );               
    }
    
    public boolean existRunningProcess(){  // Method that checks if there is a running process in the CPU
        
        boolean flag = true;  
        if (this.runningProcess==null){
             
            flag = false;   
        }
        return flag;
    }
    
    // Checks if it is time to stop because of zero remaining time
    public boolean timeToStopRPByZeroRemainingTime(){  
         
        boolean flag = false;
        
        if (this.runningProcess != null){
        	
            if (this.runningProcess.getCpuRemainingTime()==0){
                flag = true;      
            }     
        }  
        return flag;
    }
    
    // Checks if it is time to stop due to quantum's expiration
    public boolean timeToStopRPByQuantum(){  
        
        boolean flag = false;
        
        if (this.runningProcess != null){	
            if (this.timeToNextContextSwitch == cl.showTime()){
                flag = true;   
            }    
        }
        return flag;
    }
    
    // Increases the variable timeToNextContextSwitch by value equal to quantum
    public void increaseTimeToNextContextSwitch(){  
        
        this.timeToNextContextSwitch += this.rrs.getQuantum();   
    }
    
    // Returns the current tick
    public int CpuCurrentTick(){  
    	this.currentTick = cl.showTime();
    	return this.currentTick;
    }
    
}

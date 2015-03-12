package zd.dimi.roundrobin;

// Class that represents a process of the system

public class Process {
	
		private int processId;
		private int arrivalTime;
		private int terminationTime;
		
		// Time that a process needs to stay in CPU in order to be terminated
		private int cpuTotalTime;
		
		// Time that remains for a process to be terminated
		private int cpuRemainingTime;
		
		// Current state of the process, 0 - Created/New, 1 - Ready/Waiting, 2 - Running or 3 - Terminated
		private int currentState;
		
		public Process(){};
		
		public Process(int pid, int arrivalTime, int cpuBurstTime){
			
			this.processId = pid;
			this.arrivalTime = arrivalTime;
			this.cpuTotalTime = cpuBurstTime;
			this.cpuRemainingTime = cpuBurstTime;
			this.currentState = 0;	
		}
		
		// Sets the new state of a process
		public void setProcessState(int newState){  	
			this.currentState = newState;		
		}
		
		// Changes the cpuRemainingTime of the process
		public void changeCpuRemainingTime(int newCpuRemainingTime){  	
			this.cpuRemainingTime = newCpuRemainingTime;	
		}
		
		// Sets the termination time of a process
		public void setTerminationTime(int time){  	
			this.terminationTime = time;
		}
		
		// Calculates and returns the response time of a process
		public int getResponseTime(){  	
			return this.terminationTime - this.arrivalTime;	
		}
		
		public int getProcessId(){
	    	return processId;
	    }
		
		public int getArrivalTime(){
			return arrivalTime;
		}
		
		public int getCpuTotalTime(){
			return cpuTotalTime;
		}
		
		public int getCpuRemainingTime(){
			return cpuRemainingTime;
		}
		
		public int getCurrentState(){
			return currentState;
		}
		

}

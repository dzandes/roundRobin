package zd.dimi.roundrobin;

// Class that works as a scheduler, based on RR, for the processes and transfers them from and to the CPU

public class RRScheduler {
	
    private int quantum; 
	
	public RRScheduler(){};
	
	public RRScheduler(int quantum){
		this.quantum = quantum;	
	}
	
	public int getQuantum(){
		return quantum;	
	}
	
	// Method that checks if it is called when a process is exported from the CPU. It checks whether it is necessary for the process
	// to be inserted once more in the "Ready/Waiting" list or if it is terminated.
	public void addProcessToReadyList(Process process, ReadyProcessesList readyList, TerminatedProcessList termList, int time){ 
		
		if (process.getCurrentState() == 1){
            
			readyList.addProcess(process);
            System.out.println("Process " + process.getProcessId() + " was inserted in the Waiting/Ready state list");
            
        }else if(process.getCurrentState() == 3){
        	
        	termList.addProcess(process);
        	System.out.println("Process " + process.getProcessId() + " was terminated"); 
   
        	process.setTerminationTime(time);
        	System.out.println("Process " + process.getProcessId() + " response time is: " + process.getResponseTime() + " ticks");
        	
        }
		
	} 
	
	// This method is the "heart" of the RR scheduler
	public void RR(CPU cpu, ReadyProcessesList readyList, TerminatedProcessList termList){ 
		
		  
        if (!cpu.existRunningProcess()){
             
            if (!readyList.isEmpty()){
                 
                cpu.addProcess(readyList.getProcessToRunInCpu());
                readyList.removeReadyProcess();    
            }    
        }else{
             
            if (cpu.timeToStopRPByZeroRemainingTime()){
                
            	this.addProcessToReadyList(cpu.removeProcessFromCpu(), readyList, termList, cpu.CpuCurrentTick());
                
                if (!readyList.isEmpty()){
                     
                    cpu.addProcess(readyList.getProcessToRunInCpu());
                    readyList.removeReadyProcess();     
                }
                 
            }else if(cpu.timeToStopRPByQuantum()){
                 
                if (!readyList.isEmpty()){
                     
                    this.addProcessToReadyList(cpu.removeProcessFromCpu(),readyList, termList, cpu.CpuCurrentTick());
                    cpu.addProcess(readyList.getProcessToRunInCpu());
                    readyList.removeReadyProcess();
                     
                }else{
                     
                    cpu.increaseTimeToNextContextSwitch();          
                }             
            }  
        }
	}; // Swap of processes between CPU and Ready Processes List based on the value of the quantum
	
}

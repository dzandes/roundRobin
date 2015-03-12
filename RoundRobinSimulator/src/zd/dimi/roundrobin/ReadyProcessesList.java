package zd.dimi.roundrobin;

// Class that uses a list where "Ready/Waiting" processes are stored. We use an ArrayList to store them because it works as a queue
// and that philosophy is necessary for our algorithm which is Round Robin(RR).

import java.util.ArrayList;
import java.util.List;

public class ReadyProcessesList {
	
    private List<Process> readyProcessList = new ArrayList<Process>();
	
	public ReadyProcessesList(){};  
	
	// Adds a process to the list
	public void addProcess(Process process){  
		readyProcessList.add(process);
	}
	
	// Removes a process from the list
    public void removeReadyProcess(){  
    	readyProcessList.remove(0);
    }
		
    // Returns the process that its time has come to run in CPU	
	public Process getProcessToRunInCpu(){  
		return readyProcessList.get(0);	
	}
	
	// Prints to the screen the contents of the list
	public void printList(){  
		
		if(readyProcessList.size() == 0){
			
			System.out.println("List of ready processes is empty");
			
		}else{
			
			for(Process pro : readyProcessList){
				
				System.out.println("Process ID : " + pro.getProcessId() + " - " + "Arrival Time : " + pro.getArrivalTime() + " - " + "CPU Total Time : " + pro.getCpuTotalTime() + " - " + "CPU Remaining Time : " + pro.getCpuRemainingTime() + " - " + "Current State : " + pro.getCurrentState());
			}	
		}
	}
	
	// Checks if the list is empty
	public boolean isEmpty(){  
         
	    boolean empty = false;
	         
	    if (readyProcessList.isEmpty()){        
	         empty = true;         
	    }     
	    return empty;         
	}
	
	// Returns the size of the list
	public int getNumberOfProcesses (){     
        return this.readyProcessList.size();      
    }

}

package zd.dimi.roundrobin;

// Class that uses a list where "Terminated" processes are stored

import java.util.ArrayList;
import java.util.List;

public class TerminatedProcessList {
	
private List<Process> terminatedProcessList = new ArrayList<Process>();
	
	private float sum = 0; // This variable is used to calculate the average response time of the processes
	
	// Default constructor
	public TerminatedProcessList(){};
	
	public void addProcess(Process process){  // Adds a process to list
		terminatedProcessList.add(process);
	}
	
	public float averageResponse(){  // Calculates and returns the average response time of processes
		
		for(Process pro : terminatedProcessList){  // It "takes" the response time of each terminated process and then divides it                                       // with the size of the list			
			sum = sum + pro.getResponseTime();
		}
		
		float average = sum / (float) terminatedProcessList.size(); 
		return average;
	}

}

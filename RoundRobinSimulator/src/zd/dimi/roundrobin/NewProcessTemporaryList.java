package zd.dimi.roundrobin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// Class that uses a list where "New/Created" processes are stored. We use an ArrayList to store them

public class NewProcessTemporaryList {
	
private List<Process> newProcessList = new ArrayList<Process>();  // The necessary ArrayList
	
	// Default Constructor
	public NewProcessTemporaryList(){};
	
	// Adds a process in the list
	public void addNewProcess(Process process){  	
		newProcessList.add(process); 	
	}
	
	// Removes a process from the list based on its id
	public void removeNewProcess(int id){ 
		
		for(Process pro : newProcessList){
			
			if(pro.getProcessId() == id){	
				newProcessList.remove(pro);
				break;	
			}
		}
	}  
	
	// Returns the first object of the list 
	public Process getFirst(){ 	
			return newProcessList.get(0); 		
	}
	
	// Prints the contents of the list to the screen
	public void printList(){ 
		
		if(newProcessList.size() == 0){
			System.out.println("List of new processes is empty");	
		}else{
			
			for(Process pro : newProcessList){
				
				System.out.println("Process ID : " + pro.getProcessId() + " - " + "Arrival Time : " + pro.getArrivalTime() + " - " + "CPU Total Time : " + pro.getCpuTotalTime() + " - " + "CPU Remaining Time : " + pro.getCpuRemainingTime() + " - " + "Current State : " + pro.getCurrentState());	
			}	
		}
	} 
	
	// Checks if the list is empty
	public boolean isEmpty(){  
        
         boolean empty = false;
         
         if (newProcessList.isEmpty()){
             
            empty = true;    
         }
         return empty;  
    } 
	
	// Sorts the processes of newProcessList by arrival time
	public void sortNewProcessList(){  
	     
	            Collections.sort(this.newProcessList, new Comparator<Process>(){
	                
	            	public int compare(Process p1, Process p2) {
	                     
	                    return p1.getArrivalTime()-p2.getArrivalTime();      
	                }
	            });       
	}
	
	// Returns the size of the newProcessList
	public int getNumberOfProcesses(){       
	    return this.newProcessList.size();
	}


}

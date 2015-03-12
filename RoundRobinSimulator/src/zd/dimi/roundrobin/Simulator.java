package zd.dimi.roundrobin;

// The class where the simulation takes place

import java.io.File;

public class Simulator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int quantum = 1;
         
        String fileName = "input.txt";
        File inputFile = new File(fileName);
        Boolean fileExists = false;
         
        // Check if input file exists
        if(inputFile.exists()){
            fileExists = true;     
        }
        
        // Create an output file where the statistics of the simulation will be saved
        String fileNameOut = "output.txt";
    
        // If file does not exist the constructor of processGenerator creates a new input file
        // Create new processes and write them into the input file
        // Create all the necessary objects for the simulation
        
        ProcessGenerator processGen = new ProcessGenerator(fileName, fileExists);
        
        NewProcessTemporaryList newPTL = new NewProcessTemporaryList();
        newPTL = processGen.parseProcessFile(); 
        ReadyProcessesList readyPL = new ReadyProcessesList();
        TerminatedProcessList termList = new TerminatedProcessList(); 
        RRScheduler rRS = new RRScheduler(quantum);
        Clock cl = new Clock();
        CPU cpu = new CPU(cl, rRS);
    
        Statistics stat = new Statistics(fileNameOut);
        
        stat.setTotalNumberOfProcesses(newPTL.getNumberOfProcesses());
        
        newPTL.sortNewProcessList();
        newPTL.printList();
         
        boolean flag = true; // when flag becomes false, the entire process stops
         
        while (flag){
             
            int time = cl.showTime();
             
            // Check if it is time for a new process to become "Ready/Waiting"
            // It may be more than one processes that is time to become "Ready/Waiting"
            while ((!newPTL.isEmpty()) && newPTL.getFirst().getArrivalTime() == time){
                 
                Process process = newPTL.getFirst();
                int id = process.getProcessId();
                process.setProcessState(1);
                newPTL.removeNewProcess(id);
                rRS.addProcessToReadyList(process, readyPL, termList, cpu.CpuCurrentTick());           
            }
             
            rRS.RR(cpu, readyPL, termList);
            
            if (cpu.existRunningProcess()){
                 
                cpu.execute();    
            }
            
            // Update the values of maximum list length for the "Ready/Waiting" list and for the clock's ticks
            stat.UpdateMaximumListLength(readyPL);
            cl.timeRun();
             
            // if "New/Created" and "Ready/Waiting" lists are empty and there is no process in CPU, then simulation has finished
            if(newPTL.isEmpty() && readyPL.isEmpty() && (!cpu.existRunningProcess())){
                 
                flag = false;    
            }
        }
         
        // Pass the necessary statistics to the output file
        stat.WriteStatistics2File(termList); 
         
        // Check message
        System.out.println("SIMULATION FINISHED...CHECK OUT THE output.txt FOR FULL STATISTICS...");
	}

}

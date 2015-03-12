package zd.dimi.roundrobin;

// The class where processes are randomly created in case there is no input file

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class ProcessGenerator {
	
	// The file where new processes are stored
    private File inputFile;
     
    // Number of new processes to be created if input file does not exist
    private int newProcesses = 1000;
     
    // Upper bound of generated processes arrivalTime
    int upArTime = 200;
     
    // Upper bound of generated processes cpuBurstTime
    int upBurTime = 10;
 
    // CONSTRUCTOR
    // if file does not exist, constructor generates new processes and writes them to the input file
    public ProcessGenerator(String filename, boolean readFile) {
         
         if(readFile){  // if file exists
             
            this.inputFile= new File(filename);
             
        }else{ // if there is no file to read, create a new one
            try {       
            	File f = new File(filename);
                BufferedWriter output = new BufferedWriter(new FileWriter(f));
                output.write("");
                output.close();
                this.inputFile= new File(filename);
                
            } catch(IOException e){ 	
                e.printStackTrace();    
            }
             
            // Generate new Processes
            for(int i = 0; i < this.newProcesses; i++){
                 
                // variable i is used as process ID to new processes because it's a unique number for every process
                this.StoreProcessToFile(this.createProcess (i));      
            }
        }    
    }
    
    // Creates a new process in random arrivalTime and cpuBurstTime
    public Process createProcess (int pid){
         
        Random generator = new Random();
        int arrivalTime = generator.nextInt(upArTime);
        int cpuBurstTime = generator.nextInt(upBurTime)+1; // +1 for no zero
         
        Process pr = new Process(pid,arrivalTime,cpuBurstTime);
        return pr;  
    }
     
    // This method writes pr process to the input file
    public void StoreProcessToFile(Process pr) {
         
        try {
        	
            BufferedWriter output = new BufferedWriter(new FileWriter(inputFile, true));
            output.write(pr.getProcessId() + "," + pr.getArrivalTime() + "," + pr.getCpuTotalTime() + "\n");
            output.close();
            
        } catch(IOException e){
        	e.printStackTrace();
        }   
    }
     
    // Returns a list of the Processes that are in the input file
    public NewProcessTemporaryList parseProcessFile() {
         
    	NewProcessTemporaryList newProcessList = new NewProcessTemporaryList();
         
        try {
        	
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            String line;
         
            while ((line = br.readLine()) != null) {
                
                // Parse the line and split the string in commas
                String delims = "[,]";
                String[] tokens = line.split(delims);
                
                Process pr = new Process(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]),Integer.parseInt(tokens[2]));
                newProcessList.addNewProcess(pr);        
            }   
            br.close();
            
        } catch(IOException e){	
            e.printStackTrace(); 
        }   
        return newProcessList;
    }

}

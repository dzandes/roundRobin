package zd.dimi.roundrobin;
// Class that calculates the necessary statistics and stores them to the output file

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Statistics {
	
    private float averageWaitingTime;
    private int totalWaitingTime;
    private float responseTime;
    private int maximumLengthOfReadyProcessesList;
    public int totalNumberOfProcesses;
    private File outputFile;
    
    public Statistics(){}
      
    public Statistics(String filename){
          
        this.outputFile = new File(filename);
          
        if (!this.outputFile.exists()){ // if outputFile does not exist, create a new one
              
            // initialize variables
            this.averageWaitingTime = 0;
            this.maximumLengthOfReadyProcessesList = 0;
            this.responseTime = 0;
            this.totalNumberOfProcesses = 0;
            this.totalWaitingTime = 0;
              
            // for the outputFile
            BufferedWriter output;
            try {
            	
                output = new BufferedWriter(new FileWriter(this.outputFile));
                output.write("");
                output.close();
          
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }    
    }
  
    // Sets the value of totalNumberOfProcesses variable
    public void setTotalNumberOfProcesses(int totalNumberOfProcesses){  
        this.totalNumberOfProcesses = totalNumberOfProcesses;   
    }
  
    // Updates the value of maximumLengthOfReadyProcessesList variable
    public void UpdateMaximumListLength(ReadyProcessesList readyPL){  
          
        int tempLength = readyPL.getNumberOfProcesses();
        if (tempLength > this.maximumLengthOfReadyProcessesList){
              
            this.maximumLengthOfReadyProcessesList = tempLength;    
        }
          
        // Updates the total waiting time by the length of ReadyProcessList
        // the whole though about this is described in variable totalWaitingTime
        this.totalWaitingTime += tempLength;   
    }
    
    // Calculates the average waiting time   
    public float CalculateAverageWaitingTime(){ 
        return (float) this.totalWaitingTime / (float) this.totalNumberOfProcesses;   
    }
  
    // Adds a new line to the output file with the statistics of every simulation running
    public void WriteStatistics2File(TerminatedProcessList list){
          
        try {
                      
             BufferedWriter output = new BufferedWriter(new FileWriter(outputFile/*, true*/));
             output.write("Total Processes: " + this.totalNumberOfProcesses + "\n");
             output.write("Maximum Length of Ready Processes List: " + this.maximumLengthOfReadyProcessesList + "\n");
             output.write("Total Waiting Time: " + this.totalWaitingTime + " ticks\n");
             output.write("Average Waiting Time: " + this.CalculateAverageWaitingTime() + " ticks\n");
             output.write("Average Response Time: " + list.averageResponse() + " ticks\n");
             output.close();
                      
        } catch(IOException e){
             
            e.printStackTrace();              
        }
    } 

}

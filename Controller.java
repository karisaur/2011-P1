import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Controller {

   public static void main (String[] args){
      int incorrectRecords = 0, totalRecords = 0, correctRecords = 0;
	  try{
         String input = ""; 
    	 int arrivalTime = 0, priority = 0, finished = 0, time = 0;
    	 int maxSize = 0, listSize = 0, oldMax = 0;
    	 String maxOccur = "";
    	 boolean printRecord = true;
    	 boolean finishedSim = false;
    	 String data = "";
    	 PriorityQueue queue = new PriorityQueue();
    	 FileReader readFile = new FileReader("data1.txt");
    	 BufferedReader reader = new BufferedReader(readFile);
    	 StringTokenizer token;
    	 
		while (!finishedSim){
    		 
    	    try{
    		   if(printRecord){
    		      if ((input = reader.readLine()) == null) break;
    			  printRecord = false;
    			  token = new StringTokenizer(input);
    			  arrivalTime = Integer.parseInt(token.nextToken());
    			  priority = Integer.parseInt(token.nextToken());
    			  finished = Integer.parseInt(token.nextToken());
    			  data = token.nextToken("\n").trim(); 
    			  //Changes to newline to preserve the internal spaces of the field
    				 
    			  System.out.println("");
    			  
    		   }
    			  
    		   if((data.length() == 0) || (time > arrivalTime)){
    			  System.err.println("Incorrect record; missing field" + input);
    			  printRecord = true;
    		   }   else {
    				  
    		     // System.out.println(time + " " + arrivalTime);
    		      
    			  if (time == arrivalTime){
    			     queue.enqueue(priority, finished, data);
    			     printRecord = true;
    			     correctRecords++;
    			  }
    		   }
    		  time++;
 		      System.out.println(time + " " + arrivalTime);
 		      queue.update();
 		     
    	    }catch (NumberFormatException e){
		      incorrectRecords++;
              System.err.println("Not a parsable integer.");
            }
         }//End while(finished).
    	 while(!queue.isEmpty()){
    		 time++;
		     System.out.println(time + " " + arrivalTime);
		     queue.update();
    	 }
         totalRecords = correctRecords + incorrectRecords;
         System.out.println("There were " + totalRecords + 
            " records in total; " + correctRecords 
            + " correct records, " + incorrectRecords 
            + " incorrect records.");
         System.out.println("Total simulation time is " + time);
         } catch (FileNotFoundException e) {
	        incorrectRecords++;
	        System.err.println("Could not find file.");
	     } catch (IOException e) {
		    incorrectRecords++; 
	        System.err.println("Could not read file");
	     }
      }
   }

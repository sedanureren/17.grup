import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Dispatcher {
	private Queue realTimeQueue;
	private Queue userQueue;
	private int quantum;

	public Dispatcher() {
		realTimeQueue = new Queue();
		userQueue = new Queue();
		quantum = 1;
	}
	public List<Process> readProcessesFromFile(String fileName) {
	    List<Process> processList = new ArrayList<Process>();
	    try {
	      BufferedReader reader = new BufferedReader(new FileReader(fileName));
	      String line = reader.readLine();
	      while (line != null) {
	        String[] parts = line.split(",");
	        int priority = Integer.parseInt(parts[0].trim());
	        int runTime = Integer.parseInt(parts[1].trim());
	        boolean isRealTime = Boolean.parseBoolean(parts[2].trim());
	        processList.add(new Process(priority, runTime, isRealTime));
	        line = reader.readLine();
	      }
	      reader.close();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	    return processList;
	  }
	
	
	public void runProcesses() {
    Process currentProcess = realTimeQueue.dequeue();
    LowestQueue lowestQueue = new LowestQueue();
    while (currentProcess != null) {
      currentProcess.setRunTime(currentProcess.getRunTime() - 1);
      if (currentProcess.getRunTime() <= 0) {
        currentProcess = realTimeQueue.dequeue();
      } else {
        realTimeQueue.enqueue(currentProcess);
        currentProcess = realTimeQueue.dequeue();
      }
    }

    userQueue.prioritySort();
    currentProcess = userQueue.dequeue();
    while (currentProcess != null) {
      currentProcess.setRunTime(currentProcess.getRunTime() - quantum);
      if (currentProcess.getRunTime() <= 0) {
        currentProcess = userQueue.dequeue();
      } else {
    	  if (currentProcess.getPriority() > 1) {
    		  currentProcess.setPriority(currentProcess.getPriority() - 1);
    		  userQueue.enqueue(currentProcess);
    		  currentProcess = userQueue.dequeue();
    		} else if (currentProcess.getPriority() == 1) {
    		  currentProcess.setPriority(currentProcess.getPriority() - 1);
    		  lowestQueue.enqueue(currentProcess);
    		  currentProcess = lowestQueue.dequeue();
    		}
      }
    }}
	public void trackTime() {
    		  int realTimeQueueTime = 0;
    		  int userQueueTime = 0;

    		  for (Process p : realTimeQueue.getProcessList()) {
    		    realTimeQueueTime += p.getRunTime();
    		  }
    		  for (Process p : userQueue.getProcessList()) {
    		    userQueueTime += p.getRunTime();
    		  }

    		  System.out.println("Gerçek Zamanlý Kuyruk Çalýþma Süresi: " + realTimeQueueTime + " saniye");
    		  System.out.println("Kullanýcý Kuyruk Çalýþma Süresi: " + userQueueTime + " saniye");
    		}}
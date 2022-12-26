import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Queue {
	private List<Process> processList;

public Queue() {
    processList = new ArrayList<Process>();
  }

  public void enqueue(Process process) {
    processList.add(process);
  }

  public Process dequeue() {
    if (processList.size() == 0) {
      return null;
    }
    return processList.remove(0);
  }
  public List<Process> getProcessList() {
	    return processList;
	  }


  public void prioritySort() {
    Collections.sort(processList, new Comparator<Process>() {
      @Override
      public int compare(Process p1, Process p2) {
        if (p1.getPriority() < p2.getPriority()) {
          return -1;
        }
        if (p1.getPriority() > p2.getPriority()) {
          return 1;
        }
        return 0;
      }
    });
  }
}

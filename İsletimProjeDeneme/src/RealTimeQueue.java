import java.util.ArrayList;
import java.util.List;

public class RealTimeQueue {
  private List<Process> processList;

  public RealTimeQueue() {
    processList = new ArrayList<Process>();
  }

  public void enqueue(Process p) {
    processList.add(p);
  }

  public Process dequeue() {
    if (processList.isEmpty()) {
      return null;
    }
    Process p = processList.get(0);
    processList.remove(0);
    return p;
  }

  public List<Process> getProcessList() {
    return processList;
  }
}
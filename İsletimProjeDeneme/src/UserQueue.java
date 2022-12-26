
import java.util.ArrayList;
import java.util.List;

public class UserQueue {
  private List<Process> processList;

  public UserQueue() {
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

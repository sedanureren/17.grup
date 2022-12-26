public class Process {
  private int priority;
  private int runTime;
  private boolean isRealTime;

  public Process(int priority, int runTime, boolean isRealTime) {
    this.priority = priority;
    this.runTime = runTime;
    this.isRealTime = isRealTime;
  }

  public int getPriority() {
    return priority;
  }

  public void setPriority(int priority) {
    this.priority = priority;
  }

  public int getRunTime() {
    return runTime;
  }

  public void setRunTime(int runTime) {
    this.runTime = runTime;
  }

  public boolean isRealTime() {
    return isRealTime;
  }

  public void setRealTime(boolean isRealTime) {
    this.isRealTime = isRealTime;
  }
}

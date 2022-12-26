import java.util.List;

public class Main {

	public static void main(String[] args) {
		Queue realTimeQueue = new Queue();
		Queue userQueue = new Queue();
	

	Dispatcher dispatcher = new Dispatcher();
	String fileName = "C:\\Users\\Moster\\Desktop\\giris.txt";
	List<Process> processList = dispatcher.readProcessesFromFile(fileName);for(
	Process process:processList)
	{
		if (process.isRealTime()) {
			realTimeQueue.enqueue(process);
		} else {
			userQueue.enqueue(process);
		}
	}dispatcher.runProcesses();

}}

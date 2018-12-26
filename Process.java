public class Process
{
	public String processID;
	public int processArrivalTime;
	public int processServiceTime;
	public int completionTime;
	public int burstTime;
	public Process(String processID, int processArrivalTime , int burstTime)
	{
		this.processArrivalTime = processArrivalTime;
		this.processID = processID;
		this.burstTime = burstTime;
		processServiceTime = 0;
	}
}


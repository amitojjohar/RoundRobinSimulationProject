import java.util.*;
public class RoundRobin 
{
	int timeQuantum;
	int counter;
	int contextSwitchTime = 0;
	ArrayList<Process> readyqueue = new ArrayList<>();
	ArrayList<Process>rrprocesses;
	CPU cpu1 = new CPU();
	ArrayList<Process>finishedProcesses = new ArrayList<>();
	double waitingTime = 0;
	double turnAroundTime = 0;
	double totalBurstTime = 0;
	public RoundRobin(int timeQuantum, ArrayList<Process>rrprocesses)
	{
		this.timeQuantum = timeQuantum;
		this.rrprocesses = rrprocesses;
	}
	public void algorithm()
	{
		counter = 0;		
		while(!readyqueue.isEmpty() || !rrprocesses.isEmpty() || cpu1.inCPU != null)
		{
			//System.out.println(counter);
			for(int i = 0; i < rrprocesses.size(); i++)
			{
				//System.out.println(rrprocesses.get(i).processArrivalTime);
				if(counter == rrprocesses.get(i).processArrivalTime)
					readyqueue.add(rrprocesses.remove(i));
			}
			//System.out.println("  " + rrprocesses.size());
			//System.out.println("  " + readyqueue.size());
			
			// Make A CPU
			//Check if CPU is null
			if(cpu1.inCPU == null){
				cpu1.inCPU = readyqueue.remove(0);
			}
			
			cpu1.timeSpent++;
			cpu1.inCPU.processServiceTime++;
			// Process 0 = readyqueue.remove(0)				
			// Use this to CPU
			//what if the process finishes
			//empty out CPU
			if(cpu1.inCPU.burstTime == cpu1.inCPU.processServiceTime){
				finishedProcesses.add(cpu1.inCPU);
				cpu1.inCPU.completionTime = counter;
				cpu1.inCPU= null;
				contextSwitchTime++;
				cpu1.timeSpent = 0;
			}
			//else if time quantum equals time spent
			//if(timeQuantum == timeSpent)
			//kick out the process, wait again at the back of the readyqueue
			
			else if(cpu1.timeSpent == timeQuantum){
				readyqueue.add(cpu1.inCPU);
				cpu1.inCPU = null;
				contextSwitchTime++;
				cpu1.timeSpent = 0;
			}
			counter++;
		}
		
		for(int i =0; i<finishedProcesses.size(); i++)
		{
			turnAroundTime += finishedProcesses.get(i).completionTime - finishedProcesses.get(i).processArrivalTime;
			waitingTime += (finishedProcesses.get(i).completionTime - finishedProcesses.get(i).processArrivalTime) - finishedProcesses.get(i).burstTime;
			totalBurstTime += finishedProcesses.get(i).burstTime;
		}
		System.out.println("Average Waiting Time: " + waitingTime/finishedProcesses.size());
		System.out.println("Average Turnaround Time: " + turnAroundTime/finishedProcesses.size());
		System.out.println("CPU Utilization: " + ((totalBurstTime - (contextSwitchTime*0.01))/counter));
		System.out.println("CPU Throughput: " + ((double)finishedProcesses.size()/counter));
	}
		
}	



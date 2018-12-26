import java.io.*;
import java.util.*;

public class MainClass
{
	public static void main(String [] args)
	{
		int timeQuantum;
		ArrayList<Process> processes = new ArrayList<>();
		System.out.println("Enter Time Quantum: ");
		Scanner user = new Scanner(System.in);
		timeQuantum = user.nextInt();
		try{
			FileReader reader = new FileReader("processes.csv");
			Scanner read = new Scanner(reader);
			read.nextLine();
			while (read.hasNextLine())
			{
				String line = read.nextLine();
				String[] numbers = line.split(",");
				processes.add(new Process(numbers[0], Integer.parseInt(numbers[1]), Integer.parseInt(numbers[2])));
			}
			RoundRobin round = new RoundRobin(timeQuantum, processes);
			round.algorithm();
			read.close();
			user.close();
		} catch (IOException E)
		{
			System.out.print("An exception was thrown!");
		}
	}
}

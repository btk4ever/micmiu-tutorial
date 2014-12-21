package com.micmiu.tutorial.java.algorithm.scheduling;

import java.util.Scanner;

/**
 * Created
 * User: <a href="http://micmiu.com">micmiu</a>
 * Date: 8/18/2014
 * Time: 11:29
 */
public class RoundRobin {

	public static void main(String[] args) {

		Scanner kb = new Scanner(System.in);
		System.out.print("No. of Processes: ");
		int n = kb.nextInt();
		System.out.print("TQ: ");
		int tq = kb.nextInt();

		int []p = new int[n];
		int at[] = new int[n];
		int bt[] = new int[n];
		int bt2[] = new int[n];
		int wt[] = new int[n];
		int tat[] = new int[n];


		int count = 1;
		for(int i=0; i<n; i++){
			p[i] = count;
			at[i] = 0 + (int)(Math.random()*10);
			bt[i] = 4 + (int)(Math.random()*20);
			bt2[i] = bt[i];

			count++;
		}
		int tbt=0;
		for (int i = 0; i < n; i++)
			tbt = tbt + bt[i];

		int time[] = new int[tbt];
		int k = 0;
		int q2 = 0;
		int asd;
		System.out.println("\nGantt Chart");
		bt[0] = bt[0] - 1;
		//********************************************************
		//********************************************************
		// THE PROBLEM IS HERE, I DON'T KNOW IF I AM DOING THE RIGHT COMPUTATIONS HERE
		// GANTT CHART HERE (TEXT)
		int a = 0;
		while(tbt>0){
			System.out.print("|    p[" + p[tbt] + "]\t");
			tbt = tbt - 4;

		}
		//********************************************************
		//********************************************************
		//********************************************************

		double wtime = 0;
		double tatime = 0;

		for (int i = 0; i < n; i++)
		{
			wt[i] = wt[i] - at[i];
			tat[i] = tat[i] - at[i];
			wtime = wt[i];
			tatime = tat[i];
		}

	}
}

package demo;

import java.io.*;

public class RankList {

	//public static void main(String[] args) {
	//	new RankList().addPlayer("LiXue",100);
	//	System.out.println(new RankList().getList()[0]);
	//}

	public RankList() {
	}

	public void addPlayer(String name, int score) {
		try {
			BufferedReader input=new BufferedReader(new FileReader("RankList.dat"));
			int n=Integer.parseInt(input.readLine());
			boolean r=false;
			String[] nameList=new String[n];
			int[] scoreList=new int[n];
			for (int i=0; i<n; i++) {
				nameList[i]=input.readLine();
				scoreList[i]=Integer.parseInt(input.readLine());
				if (nameList[i].equals(name)) {
					scoreList[i]+=score;
					r=true;
				}
			}
			if (!r) {
				n+=1;
			}
			boolean hasChanged=true;
			while (hasChanged) {
				hasChanged=false;
				for (int i=0; i<nameList.length-1; i++) {
					if (scoreList[i]<scoreList[i+1]) {
						int scoreTmp=scoreList[i];
						scoreList[i]=scoreList[i+1];
						scoreList[i+1]=scoreTmp;
						String nameTmp=nameList[i];
						nameList[i]=nameList[i+1];
						nameList[i+1]=nameTmp;
						hasChanged=true;
					}
				}
			}
			input.close();
			BufferedWriter output=new BufferedWriter(new FileWriter("RankList.dat"));
			output.write(n+"", 0, new String(n+"").length());
			output.newLine();
			for (int i=0; i<n; i++) {
				if (!r && i==n-1) {
					output.write(name, 0, name.length());
					output.newLine();
					output.write(score+"", 0, new String(score+"").length());
					output.newLine();
					break;
				}
				output.write(nameList[i], 0, nameList[i].length());
				output.newLine();
				output.write(scoreList[i]+"", 0, new String(scoreList[i]+"").length());
				output.newLine();
			}
			output.close();
		}
		catch (Exception ex1) {
			try {
				BufferedWriter output=new BufferedWriter(new FileWriter("RankList.dat"));
				output.write('1');
				output.newLine();
				output.write(name, 0, name.length());
				output.newLine();
				output.write(score+"", 0, new String(score+"").length());
				output.newLine();
				output.close();
			}
			catch (Exception ex2) {
			}
		}
	}

	public String[] getList() {
		try {
			BufferedReader input=new BufferedReader(new FileReader("RankList.dat"));
			int n=Integer.parseInt(input.readLine());
			int count=0;
			String[] list=new String[(n-1)/10+1];
			for (int i=0; i<n; i++) {
				if (list[count]==null) {
					list[count]="";
				}
				list[count]+=input.readLine()+" : "+Integer.parseInt(input.readLine());
				if ((i+1)%10==0) {
					count++;
				} else {
					list[count]+="\n";
				}
			}
			input.close();
			return list;
		}
		catch (Exception ex) {
			return new String[] {"û�м�¼"};
		}
	}
}

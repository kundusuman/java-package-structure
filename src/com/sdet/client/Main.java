package com.sdet.client;
import java.lang.*;

import com.sdet.service.*;
import java.util.*;
import com.sdet.controller.*;
import java.sql.SQLException;
import com.sdet.entities.*;

public class Main {
	public static void main(String[] args) throws SQLException 
	{
		//System.out.println(args[0]);
		Scanner sc =new Scanner(System.in);
		DemoController dc=new DemoController();
		while(true)
		{
			int ch;
		 
			System.out.println("enter 1 to add player\nenter 2 to display player\nenter 3 to exit");
			String inp=sc.nextLine();
			try 
			{
				ch=Integer.parseInt(inp);				
			}
			catch (NumberFormatException e)
			{
				System.out.println("enter your choice carefully");
				System.out.println("---------------------------------------------------\n\n");
				continue;
			}
			
			switch(ch)
			{
				case 1:	System.out.println("ADDING PLAYERS !");
						System.out.println("---------------------------------------------------------------------");
						Player player=new Player();
						String rres=dc.install(player);
						System.out.println("the status/id of player added id := " + rres);
						break;
				case 2: System.out.println("DISPLAYING PLAYERS !");
						System.out.println("---------------------------------------------------------------------");
						System.out.println("enter team name");
						String team=sc.nextLine();
						dc.show(team);
						break;
				case 3:
						System.exit(0);
				default:
						System.out.println("wrong input");
						sc.close();
						break;
			
			}
			
			System.out.println();
			System.out.print("-------------------------------------------------------------------------------");
			System.out.println();
			
		}
		
	}

}

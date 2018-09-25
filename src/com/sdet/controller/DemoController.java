package com.sdet.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import com.sdet.exceptions.*;
import com.sdet.entities.Player;
import com.sdet.service.SDETServiceImpl;

public class DemoController {
	public static final Scanner sc=new Scanner(System.in);
	public static SDETServiceImpl service = new SDETServiceImpl();
	public void show(String team) throws SQLException {
		try {
					if (service.checkT(team) == false)
						throw new InvalidTeamNameException();
					ArrayList<String> List1 = service.getPlayer(team);
					for (int i = 0; i < List1.size(); i++) {
						System.out.println(List1.get(i));
					}
			} catch (InvalidTeamNameException e) {
						System.out.println(e);
		}
	}
	private boolean bBestcheck(String s) {
		try {
			String[] arr = s.split("/");
			if (arr.length != 2)
				return false;
			int f = Integer.parseInt(arr[0]);
			if (f > 10)
				return false;
			int f1 = Integer.parseInt(arr[1]);

			return true;

		} catch (Exception e) {
			return false;
		}
	}

	public String install(Player player) throws SQLException {
		String qwe = "not added";
		try 
		{
			if(player.getplayername()==null)
					addName(player);
			if(player.getcategory()==null)
				addCategory(player);
			if(player.gethighestscore()==-1)
				addHighScore(player,0);
			if(player.getbestfigure()==null)
					addBestFigure(player);
			if(player.getTeam()==null)
					addTeam(player);
			if(service.check(player))
			{
				throw new DuplicateEntryException();
				
			}
			
		 qwe=service.saveOrUpdate(player);
			return qwe;	
	
		} 
		catch (DuplicateEntryException e) {
				System.out.println("\n" + e);
				qwe=install(new Player());
		}
		catch (NotABatsmanException e) {
			System.out.println("\n" + e);
			qwe=install(new Player());
		} 
		catch (NotABowlerException e) {
			System.out.println("\n" + e);
			qwe=install(new Player());
		} 
		catch (InvalidCategoryException e) {
			System.out.println("\n" + e);
			qwe=install(player);
		} 
		catch (InvalidTeamNameException e) {
			System.out.println("\n" + e);
			qwe=install(player);
		}
		finally
		{
			return qwe;
		}
		/*
		 * catch(Exception e) { System.out.println(); System.out.println(e); }
		 */
	}
	private void addName(Player player) throws SQLException, InvalidTeamNameException
	{
		String name="";
		boolean temp=true;
		while(temp)
				{
				System.out.println("enter name:-- ");
				name=sc.nextLine();
				if(name.equals(null) || name.equals(""))
				{
					System.out.println("name cannot be empty or null");
					continue;
					
				}
				else
					temp=false;
				}
		player.setPlayer_name(name);
		
	}
	private void addBestFigure(Player player) throws NotABowlerException
	{
		String str="";
		boolean temp=true;
		while(temp)
		{
			System.out.println("enter best score");
			str=sc.nextLine();
			
			if(!bBestcheck(str))
			{
				System.out.println("best figure should be linke 9/77");
				continue;
			}
			if(player.getcategory().equalsIgnoreCase("bowler") && str.equals("0/0"))
			{
				throw new NotABowlerException();
			}
			else
				temp=false;
		}
		player.setBestfigure(str);
		
	}
	private void addHighScore(Player player,int high) throws NotABatsmanException 
	{
		//int high=0;
		boolean temp=true;
		String str="";
		while(temp)
		{
				System.out.println("enter high score");
				
					str=sc.nextLine();
				
				try 
				{
					high=Integer.parseInt(str);
				}
				
				catch(Exception e)
				{
					System.out.println("score should be a value");
					continue;
				}
				if(high<0)
				{
					System.out.println("score cannot be negative");
					
				}
				else
					temp=false;
		}
		System.out.println(player.getcategory() + "    " + high);
		if(player.getcategory().equalsIgnoreCase("batsman") && high < 50 || player.getcategory().equalsIgnoreCase("batsman") && high > 200)
			throw new NotABatsmanException();
		player.setHighestscore(high);
		
		
		
	}
	private void addCategory(Player player) throws InvalidCategoryException, SQLException, InvalidTeamNameException 
	{
		String cat="";
		boolean temp=true;
		while(temp)
		{
			
			System.out.println("enter category:-- ");
			cat=sc.nextLine();	
				if(cat.equals(null) || cat.equals(""))
				{
					System.out.println("category cannot be empty or null");
					continue;
				}
				if(!cat.equalsIgnoreCase("batsman") && !cat.equalsIgnoreCase("bowler") && !cat.equalsIgnoreCase("allrounder"))
				{
					throw new InvalidCategoryException();
				}
				else
						temp=false;
		}
		player.setCategory(cat);
		
		
	}
	private void addTeam(Player player) throws SQLException, InvalidTeamNameException 
	{
		String team="";
		boolean temp=true;
		while(temp)
		{
		System.out.println("enter team:---");
		team=sc.nextLine();
		if(team.equals(null) || team.equals(""))
		{
			System.out.println("team cannot be null or empty");
			continue;
		}
		if(!service.checkT(team))
			throw new InvalidTeamNameException();
		else
			temp=false;
		}
		player.setTeam(team);
			
	}

}

package com.sdet.dao;

import com.sdet.entities.Player;
import com.sdet.util.*;
import java.sql.*;
import java.util.ArrayList;
/**
 * 
 * This class implements the methods(rules) defined in SDETDao to interact with
 * database
 *
 */
public class SDETDaoImpl implements SDETDao {

	//@Override
//	public void transferMoney() {
		// TODO Auto-generated method stub

	//}
	String player_nameV;
	String categoryV;
	Integer highestscoreV;
	String bestfigureV;
	String team;
	Statement statementV=null;
	
	public ArrayList<String> getPlayer(String team) throws SQLException 
	{
		Connection conn=DBUtil.getConnection() ;
		statementV=conn.createStatement();
		String str="select team_id from team where team_name='" + team + "'" ;
		ResultSet rs=statementV.executeQuery(str);
		rs.next();
		int team_id=rs.getInt(1);
		str="";
		rs=null;
		str="select * from player inner join team_player on player.player_no=team_player.player_no where team_player.team_id=" + team_id;
		rs=statementV.executeQuery(str);
		String val="";
		ArrayList<String> value=new ArrayList<String>();
		while(rs.next())
		{
			 val="";
			 val=rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5);
			 value.add(val);
			 
		}
		conn.close();
		statementV=null;
		return value;
			
	}
	
	public String saveOrUpdate(Player player) throws SQLException
	{
		
//opening connection
		Connection conn=DBUtil.getConnection() ;
//assigning values
		player_nameV=player.getplayername();
		categoryV=player.getcategory();
		highestscoreV=player.gethighestscore();
		bestfigureV=player.getbestfigure();
		team=player.getTeam();
//creating statement
		statementV=conn.createStatement();
		String str="INSERT INTO Player(player_name,category,highestscore,bestfigure)VALUES ( '" + player_nameV +"','" + categoryV + "','" + highestscoreV + "','" +  
bestfigureV + "')";
		statementV.executeUpdate(str);
		
		String str1=	"insert into team_player values((SELECT LAST_INSERT_ID()),(select team_id from team where team_name = " + "'" + team + "'))";
		statementV.executeUpdate(str1);	
		String Tstr="select player_no from player where player_name='" + player.getplayername() + "' and category= '"
				+ player.getcategory() + "'and bestfigure='" + player.getbestfigure() + "'and highestscore="
				+ player.gethighestscore();
		ResultSet rrrs=statementV.executeQuery(Tstr);
		rrrs.next();
		String ret=rrrs.getString(1);
		conn.close();
		statementV=null;
		
		return ret;
	
	}
	public boolean check(Player player) throws SQLException
	{
		Connection conn=DBUtil.getConnection() ;
		statementV=conn.createStatement();
		String str = "select * from player where player_name='" + player.getplayername() + "' and category= '"
				+ player.getcategory() + "'and bestfigure='" + player.getbestfigure() + "'and highestscore="
				+ player.gethighestscore();
		ResultSet rs=statementV.executeQuery(str);
		if (!rs.next() ) 
		{
			conn.close();
			statementV=null;
		    return false;
		}
		conn.close();
		statementV=null;
		return true;
	}
	public boolean checkT(String team) throws SQLException
	{
		Connection conn=DBUtil.getConnection() ;
		statementV=conn.createStatement();
		String str="select * from team where team_name='" + team + "'";
		ResultSet rs=statementV.executeQuery(str);
		
		if (!rs.next() ) 
		{
			conn.close();
			statementV=null;
			return false;
		}
		conn.close();
		statementV=null;   
		return true;
	}

}


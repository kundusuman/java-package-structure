package com.sdet.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.sdet.util.*;

import com.sdet.entities.*;

/**
 * 
 * This interface defines the rules to be implemented by the DAO layer to
 * interact with database
 *
 */
public interface SDETDao {

	/*
	 * 
	 */
	//void transferMoney();

		
		public String saveOrUpdate(Player player) throws SQLException;

		public ArrayList<String> getPlayer(String team) throws SQLException;
		
		public boolean check(Player player)throws SQLException;
		public boolean checkT(String team)throws SQLException;
	

}

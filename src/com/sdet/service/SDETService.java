package com.sdet.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.sdet.entities.Player;

/**
 * 
 * This interface defines the rules to be implemented as business layer
 */
public interface SDETService {

	//void transferMoney();
	public String saveOrUpdate(Player player)throws SQLException;
	public ArrayList<String> getPlayer(String team) throws SQLException ;
	public boolean check(Player player)throws SQLException;
	public boolean checkT(String team) throws SQLException;

}

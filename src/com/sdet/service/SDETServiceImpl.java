package com.sdet.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.sdet.dao.*;
import com.sdet.entities.Player;

/**
 * This class implements the business rules defined for business layer in
 * SDETService interface
 *
 */
public class SDETServiceImpl implements SDETService {

	@Override
	public String saveOrUpdate(Player player) throws SQLException
	{
		SDETDao dao = new SDETDaoImpl();
		return dao.saveOrUpdate(player);
	}

	public ArrayList<String> getPlayer(String team) throws SQLException 
	{
		SDETDao dao = new SDETDaoImpl();
		return dao.getPlayer(team);
		
	}
	public boolean check(Player player) throws SQLException
	{
		SDETDao dao = new SDETDaoImpl();
			return dao.check(player);
	}
	public boolean checkT(String team) throws SQLException
	{
		SDETDao dao = new SDETDaoImpl();
		return dao.checkT(team);
	}
}

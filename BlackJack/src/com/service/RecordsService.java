package com.service;

import java.util.List;

import com.dao.RecordsDao;
import com.entity.Records;

public class RecordsService {
	private RecordsDao recordsDao=new RecordsDao();
	public List<Records> selectByCondition(String name){
		return recordsDao.selectByCondition(name);
	}
	public int addRecords(Records records) {
		return recordsDao.addRecords(records);
	}/*
		 * public void updateUsers(Users users) { usersDao.updateUsers(users); }
		 */
}

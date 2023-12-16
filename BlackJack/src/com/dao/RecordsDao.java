package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.entity.Records;
import com.entity.Users;
import com.utils.DbUtils;
import com.utils.StringUtils;

public class RecordsDao {
	DbUtils dbUtil = new DbUtils();
	

	public int addRecords(Records records) {
		Connection conn = dbUtil.getConn();
		int flag = -1;
		try {
			String sql = "insert into records values(null,?,?,?,?,?,sysdate())";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, records.getPlayName());
			pstmt.setInt(2, records.getNumbers());
			pstmt.setInt(3, records.getMoney());
			pstmt.setString(4, records.getState());
			pstmt.setString(5, records.getAgainUser());
			flag = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConn(conn);
		}
		return flag;

	}
	public List<Records> selectByCondition(String name){
		Connection conn = dbUtil.getConn();
		try{
			StringBuffer sb=new StringBuffer("select * from records ");

			if(StringUtils.isNotEmpty(name))
			{
				sb.append(" where  playName like '%"+name+"%'  ");
			}
			
			PreparedStatement pstmt=conn.prepareStatement(sb.toString());
		
			ResultSet rs=pstmt.executeQuery();
			List<Records> records=new ArrayList<>();
			while(rs.next()){
				Records record=new Records();
				record.setId(rs.getInt(1));
				record.setPlayName(rs.getString(2));
				record.setNumbers(rs.getInt(3));
				record.setMoney(rs.getInt(4));
				record.setState(rs.getString(5));
				record.setAgainUser(rs.getString(6));
				record.setAddTime(rs.getDate(7));
				records.add(record);
			}
			return records;
			}
			catch(Exception e){
				e.printStackTrace();	
			}finally{
				dbUtil.closeConn(conn);
			}
//		System.out.println(records);
		return null;

	}

}

package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import com.entity.Users;
import com.utils.DbUtils;

public class UsersDao {
	DbUtils dbUtil = new DbUtils();
	

	public int addUsers(Users users) {
		int flag = -1;
		Connection conn = dbUtil.getConn();
		try {
			String sql = "insert into users values(null,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, users.getName());
			pstmt.setString(2, users.getIco());
			pstmt.setInt(3, users.getMoney());
			flag = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConn(conn);
		}
		return flag;

	}
	public int updateUsers(Users users) {
		int flag = -1;
		Connection conn = dbUtil.getConn();
		try {
			String sql = "update users set money=?,icon=?,name=? where userid=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, users.getMoney());
			pstmt.setString(2, users.getIco());
			pstmt.setInt(4, users.getUserId());
			pstmt.setString(3, users.getName());
			flag = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConn(conn);
		}
		return flag;

	}
//	public List<Users> getDatas(AddStudent addstudent) {
//		Vector datas = new Vector();
//		try {
//			StringBuffer sb = new StringBuffer("select * from tb_student ");
//
//			if (StringUtil.isNotEmpty(addstudent.getStuNo())) {
//				sb.append("and stuNo like '%" + addstudent.getStuNo() + "%'");
//			}
//			if (StringUtil.isNotEmpty(addstudent.getStuName())) {
//				sb.append("and stuName like '%" + addstudent.getStuName() + "%'");
//			}
//			if (StringUtil.isNotEmpty(addstudent.getStuSex())) {
//				sb.append("and stuSex like '%" + addstudent.getStuSex() + "%'");
//			}
//			if (StringUtil.isNotEmpty(addstudent.getStuCardId())) {
//				sb.append("and stuCardId like '%" + addstudent.getStuCardId() + "%'");
//			}
//			if (StringUtil.isNotEmpty(addstudent.getStuPhoneNo())) {
//				sb.append("and stuPhoneNo like '%" + addstudent.getStuPhoneNo() + "%'");
//			}
//			System.out.println(sb.toString().replaceFirst("and", "where"));
//			PreparedStatement pstmt = conn.prepareStatement(sb.toString().replaceFirst("and", "where"));
//
//			ResultSet rs = pstmt.executeQuery();
//			Vector v = new Vector();
//			while (rs.next()) {
//				v.clear();
//				v.add(rs.getInt("stuId"));
//				v.add(rs.getString("stuNo"));
//				v.add(rs.getString("stuName"));
//				v.add(rs.getString("stuSex"));
//				v.add(rs.getString("stuCardId"));
//				v.add(rs.getString("stuPhoneNo"));
//				datas.add(v.clone());
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			dbUtil.closeConn(conn);
//		}
//		System.out.println(datas);
//		return datas;
//	}
//
	
	public Users selectByName(String name) {
		String sql="select * from users where name='"+name+"';";
		Connection conn = dbUtil.getConn();
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				Users user=new Users();
				user.setUserId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setIco(rs.getString(3));
				user.setMoney(rs.getInt(4));
				return user;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
//	public List getAddStudentList() {
//		List list = new ArrayList();
//		String sql = "select * from tb_student";
//		try {
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//			ResultSet rs = pstmt.executeQuery();
//			while (rs.next())
//				;
//			{
//				AddStudent addstudent = new AddStudent();
//				addstudent.setStuId(rs.getInt("stuId"));
//				addstudent.setStuNo(rs.getString("stuNo"));
//				addstudent.setStuName(rs.getString("stuName"));
//				addstudent.setStuSex(rs.getString("stuSex"));
//				addstudent.setStuCardId(rs.getString("stuCardId"));
//				addstudent.setStuPhoneNo(rs.getString("stuPhoneNo"));
//				list.add(addstudent);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//
//		}
//		return list;
//	}
}

package com.frame;

import java.awt.*;

import javax.swing.*;

import com.dao.UsersDao;
import com.entity.Users;
import com.utils.DbUtils;
import com.utils.StringUtils;

import game.GameMain;

import java.sql.*;
import java.awt.event.*;

public class LoginFrame extends JFrame {

	private JLabel lbUsername = new JLabel("用户名:");
	private JTextField txtUsername = new JTextField();

	private JButton btnLogin = new JButton("登录");
	private JButton btnRegist = new JButton("注册");
	
	private JLabel lbTip = new JLabel("21点游戏");

	private DbUtils dbUtil = new DbUtils();
	private UsersDao userDao = new UsersDao();
	private  static Users mainUser = null;

	LoginFrame() {

		setSize(400, 340);

		setLocation(200, 50);

		String path = "登录.jpeg";

		ImageIcon background = new ImageIcon(path);

		JLabel label = new JLabel(background);

		label.setBounds(0, 0, this.getWidth(), this.getHeight());

		JPanel imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);

		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));

		this.setSize(400, 340);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container cont = this.getContentPane();
		cont.setLayout(null);

		lbUsername.setFont(new Font("华文新魏", Font.PLAIN, 14));

		lbTip.setFont(new Font("黑体", Font.PLAIN, 24));
		lbTip.setBounds(142, 0, 200, 50);
		cont.add(lbTip);

		lbUsername.setBounds(85, 115, 50, 25);
		txtUsername.setBounds(155, 115, 150, 25);
		cont.add(lbUsername);
		cont.add(txtUsername);

		btnLogin.setBounds(80, 210, 70, 35);
		btnRegist.setBounds(260, 210, 70, 35);

		btnLogin.addActionListener(new LoginAction());
		btnRegist.addActionListener(new RegistAction());

		cont.add(btnLogin);
		cont.add(btnRegist);
		this.setVisible(true);

	}

	private class LoginAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			String username = txtUsername.getText().trim();

			if (StringUtils.isEmpty(username)) {
				JOptionPane.showMessageDialog(null, "用户名不能为空！");
				return;

			}

			Users user = new Users();
			Connection conn = dbUtil.getConn();
			mainUser = userDao.selectByName(username);
			dbUtil.closeConn(conn);
			if (mainUser != null) {
				GameMain mainFrame = new GameMain();
				mainFrame.gameMain();
			} else {
				JOptionPane.showMessageDialog(null, "用户名错误！");
				txtUsername.setText("");
				txtUsername.requestFocus();
			}

		}

	}

	private class RegistAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			new RegisterFrame();

		}

	}

	public static void main(String[] args) {
		new LoginFrame();

	}

	public static Users getMainUser() {
		return mainUser;
	}

	public static void setMainUser(Users mainUser) {
		LoginFrame.mainUser = mainUser;
	}

}

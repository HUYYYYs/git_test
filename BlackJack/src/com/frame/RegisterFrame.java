package com.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.entity.Users;
import com.service.UsersService;
public class RegisterFrame extends JFrame implements ActionListener{
	private JLabel nameLabel,moneyLabel,icoLabel;
	private JTextField nameTF,moneyTF,icoTF;
	
	private JButton regitBtn;
	public RegisterFrame() {
		this.setTitle("                        注册");
		this.setLayout(null);
		nameLabel=new JLabel("用户姓名");
		moneyLabel=new JLabel("充值金额");
		icoLabel=new JLabel("用户图片");
		
		moneyTF=new JTextField(15);
		icoTF=new JTextField(15);
		nameTF=new JTextField(15);
		regitBtn=new JButton("注册");
		this.add(moneyLabel);
		this.add(icoLabel);
		this.add(nameLabel);
		this.add(moneyTF);
		this.add(icoTF);
		this.add(nameTF);
		icoLabel.setBounds(55,125,150,35);
		icoTF.setBounds(185,125,150,35);
		moneyLabel.setBounds(55,195,150,35);
		moneyTF.setBounds(185,195,150,35);
		nameLabel.setBounds(55,50,150,35);
		nameTF.setBounds(185,50,150,35);
		regitBtn.setBounds(130,320,150,35);
		
		this.add(regitBtn);
		regitBtn.addActionListener(this);
		this.setVisible(true);
		this.setBounds(300,300,400,470);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String name=nameTF.getText().trim();
		String money=moneyTF.getText().trim();
		String ico=icoTF.getText().trim();
		if(null==name ||"".equals(name)) {
			JOptionPane.showMessageDialog(null,"用户姓名不可为空!");
			return;
		}else if(null==money ||"".equals(money)) {
			JOptionPane.showMessageDialog(null,"充值金额不可为空!");
			return;
		}else if(null==ico ||"".equals(ico)) {
			JOptionPane.showMessageDialog(null,"用户头像不可为空!");
			return;
		}
		
		try {
//			FileUtil.addDataTotxt("data/out.txt" ,name+"    "+age+"  "+sex+"   "+phone+"   "+password);
			UsersService service=new UsersService();
			Users user=new Users();
			user.setName(name);
			user.setIco(ico);
			user.setMoney(Integer.parseInt(money));
			service.addUsers(user);
			JOptionPane.showMessageDialog(null,"添加用户信息成功!");
			this.dispose();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null,"添加用户信息失败!");
			e1.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		new RegisterFrame();
	}
}

package com.frame;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.entity.Records;
import com.service.RecordsService;

/**
 * 赵炎
 *
 */
public class RecordsListFrame extends JFrame implements ActionListener {
	Container c;
	JPanel panel1, panel2, panel3;
	JLabel  nameLabel;
	JTextField  nameTF;
	JButton SearchBtn, ExitBtn;
	JTable table = null;
	DefaultTableModel defaultModel = null;
	RecordsService service=new RecordsService();
	public List<Records> selectAllRecords(String name){
		return service.selectByCondition(name);
	}
	public RecordsListFrame() {
		super("玩家输赢记录查询");
		this.setBounds(200,200,800,760);
		c = getContentPane();
		
		c.setLayout(new BorderLayout());
		nameLabel = new JLabel("姓名", JLabel.CENTER);
		nameTF = new JTextField(15);
		SearchBtn = new JButton("查询");
		SearchBtn.addActionListener(this);
		ExitBtn = new JButton("退出");
		ExitBtn.addActionListener(this);
		panel1 = new JPanel();
		panel3 = new JPanel();
		panel1.add(nameLabel);
		panel1.add(nameTF);
		panel3.add(SearchBtn);
		panel3.add(ExitBtn);
		String[] name = { "编号", "玩家姓名", "下注金额", "输赢金额","输赢状态","对局玩家","添加时间"};
		String[][] data = new String[0][0];
		defaultModel = new DefaultTableModel(data, name);
		table = new JTable(defaultModel);
		table.setPreferredScrollableViewportSize(new Dimension(600, 600));
		JScrollPane s = new JScrollPane(table);
		panel2 = new JPanel();
		panel2.add(s);
		c.add(panel1, BorderLayout.NORTH);
		c.add(panel3, BorderLayout.CENTER);
		c.add(panel2, BorderLayout.SOUTH);
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == SearchBtn) {
			// 首先要删除table中的数据先：
			int rowCount = defaultModel.getRowCount() - 1;// 取得table中的数据行；
			int j = rowCount;
			for (int i = 0; i <= rowCount; i++) {
				defaultModel.removeRow(j);// 删除rowCount行的数据；
				defaultModel.setRowCount(j);// 重新设置行数；
				j = j - 1;
			}
			
				List<Records> list=selectAllRecords(nameTF.getText().trim());
				for(int i=0;i<list.size();i++) {
					Records record=list.get(i);
					Vector data = new Vector();
					data.addElement(record.getId());
					data.addElement(record.getPlayName());
					data.addElement(record.getNumbers());
					data.addElement(record.getMoney());
					data.addElement(record.getState());
					data.addElement(record.getAgainUser());
					data.addElement(record.getAddTime());
					defaultModel.addRow(data);
				}
			
			table.revalidate();
		}else if (e.getSource() == ExitBtn) {
			this.dispose();
		}
	}
}


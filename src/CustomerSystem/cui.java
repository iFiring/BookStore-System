package CustomerSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;

public class cui {

	private JFrame frame;
	private int cusbacknum = 0;
	private int cusbuynum = 0;
	private int cusborrownum = 0;
	private String cusphonenum;
	private JTextField textField, textField_1, textField_2, textField_3, textField_4, textField_5, textField_6,
			textField_7, textField_8;
	private JTextField textField_9, textField_10, textField_11, textField_12, textField_13, textField_14, textField_15,
			textField_16;

	/**
	 * 启动前台程序.
	 * 
	 * @throws SQLException
	 */
	public cui(String y, Statement sta) throws SQLException {
		cusinf(y, sta);
		cuimian(y, sta);
	}

	private void cusinf(String y, Statement sta) throws SQLException {
		ResultSet result1 = sta.executeQuery("select b_id from borrow where b_type='借阅' and r_id='" + y + "'");
		while (result1.next()) {
			cusborrownum++;
		}
		;
		ResultSet result2 = sta.executeQuery("select b_id from borrow where b_type='购买' and r_id='" + y + "'");
		while (result2.next()) {
			cusbuynum++;
		}
		;
		ResultSet result3 = sta.executeQuery("select b_id from borrow where b_type='已还' and r_id='" + y + "'");
		while (result3.next()) {
			cusbacknum++;
		}
		;
		ResultSet result4 = sta.executeQuery("select r_phone from reader where r_id='" + y + "'");
		while (result4.next()) {
			cusphonenum = result4.getString(1);
		}
		;
	}

	public void cusborsearch(String y, Statement sta) throws SQLException {
		String booksearch = textField.getText();
		ResultSet rs = sta.executeQuery(
				"select b_id,b_name,b_author,b_publish,b_num,b_price from books where b_name = '" + booksearch + "'");
		if (rs.next()) {
			textField_1.setText(rs.getString(1));
			textField_2.setText(rs.getString(2));
			textField_3.setText(rs.getString(3));
			textField_4.setText(rs.getString(4));
			textField_5.setText(rs.getString(5));
			textField_6.setText(rs.getString(6));
		} else {
			JOptionPane.showMessageDialog(null, "您输入的图书名称不存在，请重新输入", "输入错误", JOptionPane.YES_NO_OPTION);
		}
	}

	public void cusborbor(String y, Statement sta) throws SQLException {
		Date dt = new Date(0);
		SimpleDateFormat mt = new SimpleDateFormat("yyyy-MM-dd");
		sta.executeUpdate("insert into borrow values('" + textField_7.getText() + "','" + y + "','" + mt.format(dt)
				+ "','null','借阅','1')");
		JOptionPane.showMessageDialog(null, "图书借阅成功！");
	}

	public void cusborback(String y, Statement sta) throws SQLException {
		Date dt = new Date(0);
		SimpleDateFormat mt = new SimpleDateFormat("yyyy-MM-dd");
		sta.executeUpdate("insert into borrow values('" + textField_8.getText() + "','" + y + "','null','"
				+ mt.format(dt) + "','已还','1')");
		JOptionPane.showMessageDialog(null, "图书归还成功！");
	}

	public void cusbuysearch(String y, Statement sta) throws SQLException {
		String booksearch = textField_9.getText();
		ResultSet rs = sta.executeQuery(
				"select b_id,b_name,b_author,b_publish,b_num,b_price from books where b_name = '" + booksearch + "'");
		if (rs.next()) {
			textField_10.setText(rs.getString(1));
			textField_11.setText(rs.getString(2));
			textField_12.setText(rs.getString(3));
			textField_13.setText(rs.getString(4));
			textField_14.setText(rs.getString(5));
			textField_15.setText(rs.getString(6));
		} else {
			JOptionPane.showMessageDialog(null, "您输入的图书名称不存在，请重新输入", "输入错误", JOptionPane.YES_NO_OPTION);
		}
	}

	public void cusbuybuy(String y, Statement sta) throws SQLException {
		Date dt = new Date(0);
		SimpleDateFormat mt = new SimpleDateFormat("yyyy-MM-dd");
		sta.executeUpdate("insert into borrow values('" + textField_16.getText() + "','" + y + "','" + mt.format(dt)
				+ "','null','购买','1')");
		JOptionPane.showMessageDialog(null, "图书购买成功！");
	}

	private void cuimian(String y, Statement sta) {
		frame = new JFrame();
		frame.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		frame.setTitle("顾客前台服务中心");
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.controlHighlight);
		panel.setBounds(0, 0, 782, 80);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JButton btn_cusinf = new JButton("个人信息");
		btn_cusinf.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		btn_cusinf.setBounds(91, 13, 160, 55);
		btn_cusinf.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(btn_cusinf);
		JButton btn_bookbor = new JButton("图书借阅");
		btn_bookbor.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		btn_bookbor.setBounds(300, 13, 160, 55);
		panel.add(btn_bookbor);
		JButton btn_bookbuy = new JButton("图书购买");
		btn_bookbuy.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		btn_bookbuy.setBounds(506, 13, 160, 55);
		panel.add(btn_bookbuy);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 80, 782, 473);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(new CardLayout(0, 0));

		JPanel panelbsinf = new JPanel();
		panelbsinf.setBackground(SystemColor.controlHighlight);
		panel_1.add(panelbsinf, "name_56002379627557");
		panelbsinf.setLayout(null);
		JLabel label = new JLabel("帐户名：");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label.setBounds(28, 13, 96, 44);
		panelbsinf.add(label);
		JLabel label_1 = new JLabel("已借阅：");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_1.setBounds(406, 13, 96, 44);
		panelbsinf.add(label_1);
		JLabel label_2 = new JLabel("已购买：");
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_2.setBounds(28, 101, 96, 44);
		panelbsinf.add(label_2);
		JLabel label_3 = new JLabel("已归还：");
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_3.setBounds(406, 101, 96, 44);
		panelbsinf.add(label_3);
		JLabel label_4 = new JLabel("软件版本：");
		label_4.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_4.setBounds(28, 192, 96, 44);
		panelbsinf.add(label_4);
		JLabel label_5 = new JLabel("电话：");
		label_5.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_5.setBounds(406, 192, 96, 44);
		panelbsinf.add(label_5);
		JLabel cusid = new JLabel(y);
		cusid.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		cusid.setBounds(149, 13, 96, 44);
		panelbsinf.add(cusid);
		JLabel cusbuy = new JLabel(cusbuynum + "");
		cusbuy.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		cusbuy.setBounds(149, 101, 96, 44);
		panelbsinf.add(cusbuy);
		JLabel cusage = new JLabel("V1.00");
		cusage.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		cusage.setBounds(149, 192, 96, 44);
		panelbsinf.add(cusage);
		JLabel cusborrow = new JLabel(cusborrownum + "");
		cusborrow.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		cusborrow.setBounds(528, 13, 96, 44);
		panelbsinf.add(cusborrow);
		JLabel cusback = new JLabel(cusbacknum + "");
		cusback.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		cusback.setBounds(528, 101, 96, 44);
		panelbsinf.add(cusback);
		JLabel cusphone = new JLabel(cusphonenum + "");
		cusphone.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		cusphone.setBounds(528, 192, 188, 44);
		panelbsinf.add(cusphone);

		JTabbedPane tab_bookbor = new JTabbedPane(JTabbedPane.LEFT);
		tab_bookbor.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		panel_1.add(tab_bookbor, "name_112312636927021");
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(SystemColor.controlHighlight);
		tab_bookbor.addTab("查询图书", null, panel_6, null);
		panel_6.setLayout(null);
		JLabel label_6 = new JLabel("请输入图书名称：");
		label_6.setFont(new Font("微软雅黑", Font.PLAIN, 19));
		label_6.setBounds(14, 13, 158, 66);
		panel_6.add(label_6);
		textField = new JTextField("");
		textField.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textField.setColumns(10);
		textField.setBounds(186, 33, 334, 32);
		panel_6.add(textField);
		JButton button = new JButton("查询");
		button.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		button.setBounds(534, 36, 113, 27);
		panel_6.add(button);
		JLabel label_7 = new JLabel("ID：");
		label_7.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_7.setBounds(108, 92, 64, 40);
		panel_6.add(label_7);
		textField_1 = new JTextField();
		textField_1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		textField_1.setColumns(10);
		textField_1.setBounds(186, 101, 334, 24);
		panel_6.add(textField_1);
		JLabel label_8 = new JLabel("名称：");
		label_8.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_8.setBounds(108, 129, 64, 40);
		panel_6.add(label_8);
		textField_2 = new JTextField();
		textField_2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		textField_2.setColumns(10);
		textField_2.setBounds(186, 138, 334, 24);
		panel_6.add(textField_2);
		JLabel label_9 = new JLabel("作者：");
		label_9.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_9.setBounds(108, 166, 64, 40);
		panel_6.add(label_9);
		textField_3 = new JTextField();
		textField_3.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		textField_3.setColumns(10);
		textField_3.setBounds(186, 175, 334, 24);
		panel_6.add(textField_3);
		JLabel label_10 = new JLabel("出版社：");
		label_10.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_10.setBounds(108, 203, 64, 40);
		panel_6.add(label_10);
		textField_4 = new JTextField();
		textField_4.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		textField_4.setColumns(10);
		textField_4.setBounds(186, 212, 334, 24);
		panel_6.add(textField_4);
		JLabel label_11 = new JLabel("数量：");
		label_11.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_11.setBounds(108, 240, 64, 40);
		panel_6.add(label_11);
		textField_5 = new JTextField();
		textField_5.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		textField_5.setColumns(10);
		textField_5.setBounds(186, 249, 334, 24);
		panel_6.add(textField_5);
		JLabel label_12 = new JLabel("价格：");
		label_12.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_12.setBounds(108, 277, 64, 40);
		panel_6.add(label_12);
		textField_6 = new JTextField();
		textField_6.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		textField_6.setColumns(10);
		textField_6.setBounds(186, 286, 334, 24);
		panel_6.add(textField_6);
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(SystemColor.controlHighlight);
		tab_bookbor.addTab("借阅图书", null, panel_7, null);
		panel_7.setLayout(null);
		JLabel label_13 = new JLabel("方式一：");
		label_13.setFont(new Font("微软雅黑", Font.PLAIN, 19));
		label_13.setBounds(14, 13, 158, 66);
		panel_7.add(label_13);
		JLabel label_14 = new JLabel("方式二：");
		label_14.setFont(new Font("微软雅黑", Font.PLAIN, 19));
		label_14.setBounds(14, 222, 158, 66);
		panel_7.add(label_14);
		JLabel label_15 = new JLabel("请用扫描仪扫描图书二维码处：");
		label_15.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_15.setBounds(230, 237, 224, 40);
		panel_7.add(label_15);
		JLabel lblid = new JLabel("请输入图书ID：");
		lblid.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblid.setBounds(285, 28, 113, 40);
		panel_7.add(lblid);
		textField_7 = new JTextField("");
		textField_7.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		textField_7.setColumns(10);
		textField_7.setBounds(176, 92, 334, 32);
		panel_7.add(textField_7);
		JButton button_6 = new JButton("借阅");
		button_6.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		button_6.setBounds(285, 169, 113, 27);
		panel_7.add(button_6);
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("D:\\eclipseworkspace\\BookStoreSystem\\tiaoxingma.jpg"));
		lblNewLabel.setBounds(218, 303, 246, 88);
		panel_7.add(lblNewLabel);
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(SystemColor.controlHighlight);
		tab_bookbor.addTab("归还图书", null, panel_5, null);
		panel_5.setLayout(null);
		JLabel label_16 = new JLabel("方式一：");
		label_16.setFont(new Font("微软雅黑", Font.PLAIN, 19));
		label_16.setBounds(14, 13, 158, 66);
		panel_5.add(label_16);
		JLabel lblid_1 = new JLabel("请输入图书ID：");
		lblid_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblid_1.setBounds(285, 28, 113, 40);
		panel_5.add(lblid_1);
		textField_8 = new JTextField("");
		textField_8.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		textField_8.setColumns(10);
		textField_8.setBounds(176, 92, 334, 32);
		panel_5.add(textField_8);
		JButton button_3 = new JButton("归还");
		button_3.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		button_3.setBounds(285, 169, 113, 27);
		panel_5.add(button_3);
		JLabel label_18 = new JLabel("方式二：");
		label_18.setFont(new Font("微软雅黑", Font.PLAIN, 19));
		label_18.setBounds(14, 222, 158, 66);
		panel_5.add(label_18);
		JLabel label_19 = new JLabel("请用扫描仪扫描图书二维码处：");
		label_19.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_19.setBounds(230, 237, 224, 40);
		panel_5.add(label_19);
		JLabel label_20 = new JLabel("");
		label_20.setIcon(new ImageIcon("D:\\eclipseworkspace\\BookStoreSystem\\tiaoxingma.jpg"));
		label_20.setBounds(218, 303, 246, 88);
		panel_5.add(label_20);
		JTabbedPane tab_bookbuy = new JTabbedPane(JTabbedPane.LEFT);
		tab_bookbuy.setBackground(SystemColor.controlHighlight);
		tab_bookbuy.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		panel_1.add(tab_bookbuy, "name_112490622048714");
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.controlHighlight);
		tab_bookbuy.addTab("查询图书", null, panel_3, null);
		panel_3.setLayout(null);
		JLabel label_21 = new JLabel("请输入图书名称：");
		label_21.setFont(new Font("微软雅黑", Font.PLAIN, 19));
		label_21.setBounds(14, 13, 158, 66);
		panel_3.add(label_21);
		textField_9 = new JTextField("");
		textField_9.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textField_9.setColumns(10);
		textField_9.setBounds(186, 33, 334, 32);
		panel_3.add(textField_9);
		JButton button_1 = new JButton("查询");
		button_1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		button_1.setBounds(534, 36, 113, 27);
		panel_3.add(button_1);
		textField_10 = new JTextField();
		textField_10.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		textField_10.setColumns(10);
		textField_10.setBounds(186, 101, 334, 24);
		panel_3.add(textField_10);
		JLabel label_22 = new JLabel("ID：");
		label_22.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_22.setBounds(108, 92, 64, 40);
		panel_3.add(label_22);
		JLabel label_23 = new JLabel("名称：");
		label_23.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_23.setBounds(108, 129, 64, 40);
		panel_3.add(label_23);
		textField_11 = new JTextField();
		textField_11.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		textField_11.setColumns(10);
		textField_11.setBounds(186, 138, 334, 24);
		panel_3.add(textField_11);
		JLabel label_24 = new JLabel("作者：");
		label_24.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_24.setBounds(108, 166, 64, 40);
		panel_3.add(label_24);
		textField_12 = new JTextField();
		textField_12.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		textField_12.setColumns(10);
		textField_12.setBounds(186, 175, 334, 24);
		panel_3.add(textField_12);
		JLabel label_25 = new JLabel("出版社：");
		label_25.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_25.setBounds(108, 203, 64, 40);
		panel_3.add(label_25);
		textField_13 = new JTextField();
		textField_13.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		textField_13.setColumns(10);
		textField_13.setBounds(186, 212, 334, 24);
		panel_3.add(textField_13);
		JLabel label_26 = new JLabel("数量：");
		label_26.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_26.setBounds(108, 240, 64, 40);
		panel_3.add(label_26);
		textField_14 = new JTextField();
		textField_14.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		textField_14.setColumns(10);
		textField_14.setBounds(186, 249, 334, 24);
		panel_3.add(textField_14);
		JLabel label_27 = new JLabel("价格：");
		label_27.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_27.setBounds(108, 277, 64, 40);
		panel_3.add(label_27);
		textField_15 = new JTextField();
		textField_15.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		textField_15.setColumns(10);
		textField_15.setBounds(186, 286, 334, 24);
		panel_3.add(textField_15);
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(SystemColor.controlHighlight);
		tab_bookbuy.addTab("购买图书", null, panel_4, null);
		panel_4.setLayout(null);
		JLabel label_28 = new JLabel("方式一：");
		label_28.setFont(new Font("微软雅黑", Font.PLAIN, 19));
		label_28.setBounds(14, 13, 158, 66);
		panel_4.add(label_28);
		JLabel label_29 = new JLabel("请输入图书ID：");
		label_29.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_29.setBounds(285, 28, 113, 40);
		panel_4.add(label_29);
		textField_16 = new JTextField("");
		textField_16.setColumns(10);
		textField_16.setBounds(176, 92, 334, 32);
		panel_4.add(textField_16);
		JButton button_2 = new JButton("购买");
		button_2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		button_2.setBounds(285, 169, 113, 27);
		panel_4.add(button_2);
		JLabel label_30 = new JLabel("方式二：");
		label_30.setFont(new Font("微软雅黑", Font.PLAIN, 19));
		label_30.setBounds(14, 222, 158, 66);
		panel_4.add(label_30);
		JLabel label_31 = new JLabel("请用扫描仪扫描图书二维码处：");
		label_31.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_31.setBounds(230, 237, 224, 40);
		panel_4.add(label_31);
		JLabel label_32 = new JLabel("");
		label_32.setIcon(new ImageIcon("D:\\eclipseworkspace\\BookStoreSystem\\tiaoxingma.jpg"));
		label_32.setBounds(218, 303, 246, 88);
		panel_4.add(label_32);

		btn_bookbuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelbsinf.setVisible(false);
				tab_bookbor.setVisible(false);
				tab_bookbuy.setVisible(true);
			}
		});
		btn_cusinf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelbsinf.setVisible(true);
				tab_bookbor.setVisible(false);
				tab_bookbuy.setVisible(false);
			}
		});
		btn_bookbor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelbsinf.setVisible(false);
				tab_bookbor.setVisible(true);
				tab_bookbuy.setVisible(false);
			}
		});
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cusborsearch(y, sta);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cusborbor(y, sta);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cusborback(y, sta);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cusbuysearch(y, sta);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cusbuybuy(y, sta);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
}

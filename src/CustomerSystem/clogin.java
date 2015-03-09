package CustomerSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class clogin {

	private JFrame frame;
	private JTextField cusid;// 登录账户
	private JPasswordField cuspw;// 密码
	private JTextField ncusid;// 账户
	private JTextField ncusage;// 年龄
	private JTextField ncusphone;// 电话 ;
	private JPasswordField ncuspw;// 密码
	private JPasswordField ncuspwr;// 确认密码
	private Connection conDB;
	private Statement staDB;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					clogin window = new clogin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public clogin() {
		OpenDB();
		clogin_ui();
	}

	public void OpenDB() {
		String JDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// 数据库驱动
		System.out.println("数据库驱动成功");
		String connectDB = "jdbc:sqlserver://localhost:1433;DatabaseName=bookstoreDB";// 数据库连接
		try {
			Class.forName(JDriver);// 加载数据库引擎，返回给定字符串名的类
		} catch (ClassNotFoundException e) {// e.printStackTrace();
			System.out.println("加载数据库引擎失败");
			System.exit(0);
		}
		try {
			conDB = DriverManager.getConnection(connectDB, "sa", "123456");// 连接数据库对象
			System.out.println("连接数据库成功");
			staDB = conDB.createStatement();
		} // 创建SQL命令对象
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("数据库连接错误");
			System.exit(0);
		}
	}

	private void readcus() throws SQLException {
		ResultSet rs = staDB.executeQuery("select r_id,r_password from reader");
		String customer = cusid.getText();
		String password = cuspw.getText();
		String a = null;
		String b = null;
		if (customer.equals("")) {
			JOptionPane.showMessageDialog(null, "账号不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		} else if (password.equals("")) {
			JOptionPane.showMessageDialog(null, "密码不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		while (rs.next()) {
			a = rs.getString(1);
			b = rs.getString(2);
			if (a.compareTo(customer) != 0 || b.compareTo(password) != 0) {
			} else {
				frame.setVisible(false);
				new cui(customer, staDB);
				break;
			}
		}
		;
		if (a.compareTo(customer) != 0 || b.compareTo(password) != 0) {
			JOptionPane.showMessageDialog(null, "账号或密码错误！", "错误", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void registercus() throws SQLException {
		String ncustomer = ncusid.getText();
		String nage = ncusage.getText();
		String nphone = ncusphone.getText();
		String npassword = ncuspw.getText();
		String npasswordr = ncuspwr.getText();
		if (ncustomer.equals("")) {
			JOptionPane.showMessageDialog(null, "账号不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		} else if (nage.equals("")) {
			JOptionPane.showMessageDialog(null, "年龄不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		} else if (nphone.equals("")) {
			JOptionPane.showMessageDialog(null, "电话不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		} else if (npassword.equals("")) {
			JOptionPane.showMessageDialog(null, "密码不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		} else if (npasswordr.equals("")) {
			JOptionPane.showMessageDialog(null, "重复密码不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		} else if (npassword.compareTo(npasswordr) != 0) {
			JOptionPane.showMessageDialog(null, "两次输入的密码不一致！", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		} else {
			staDB.executeUpdate("insert into reader values('" + ncustomer + "','" + npassword + "','" + nage + "','"
					+ nphone + "')");
			frame.setVisible(false);
			new cui(ncustomer, staDB);
			JOptionPane.showMessageDialog(null, "注册成功！");
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void clogin_ui() {
		frame = new JFrame();
		frame.setTitle("欢迎登录");
		frame.setBounds(100, 100, 400, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		tabbedPane.setBounds(0, 0, 382, 253);
		frame.getContentPane().add(tabbedPane);

		JPanel panel = new JPanel();
		tabbedPane.addTab("          登录           ", null, panel, null);
		panel.setLayout(null);
		JLabel label = new JLabel("账户名：");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label.setBounds(55, 53, 72, 18);
		panel.add(label);

		cusid = new JTextField();
		cusid.setBounds(118, 51, 205, 24);
		panel.add(cusid);
		cusid.setColumns(10);

		JLabel label_1 = new JLabel("密码：");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_1.setBounds(55, 114, 72, 18);
		panel.add(label_1);

		cuspw = new JPasswordField();
		cuspw.setColumns(10);
		cuspw.setBounds(118, 112, 205, 24);
		panel.add(cuspw);

		JButton btn_clogin = new JButton("登录");
		btn_clogin.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		btn_clogin.setBounds(135, 184, 113, 27);
		panel.add(btn_clogin);
		tabbedPane.setEnabledAt(0, true);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("           注册          ", null, panel_1, null);
		panel_1.setLayout(null);
		JLabel label_2 = new JLabel("账户名：");
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_2.setBounds(34, 13, 72, 18);
		panel_1.add(label_2);
		JLabel label_3 = new JLabel("密码：");
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_3.setBounds(34, 106, 72, 18);
		panel_1.add(label_3);
		JLabel label_4 = new JLabel("年龄：");
		label_4.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_4.setBounds(202, 13, 72, 18);
		panel_1.add(label_4);
		JLabel label_5 = new JLabel("确认密码：");
		label_5.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_5.setBounds(34, 152, 92, 18);
		panel_1.add(label_5);
		JLabel label_6 = new JLabel("电话：");
		label_6.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_6.setBounds(34, 59, 72, 18);
		panel_1.add(label_6);
		JLabel label_7 = new JLabel("性别：");
		label_7.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_7.setBounds(202, 59, 72, 18);
		panel_1.add(label_7);

		ncusid = new JTextField();
		ncusid.setBounds(108, 11, 86, 24);
		panel_1.add(ncusid);
		ncusid.setColumns(10);
		ncusage = new JTextField();
		ncusage.setBounds(252, 11, 86, 24);
		panel_1.add(ncusage);
		ncusage.setColumns(10);
		ncusphone = new JTextField();
		ncusphone.setBounds(108, 57, 86, 24);
		panel_1.add(ncusphone);
		ncusphone.setColumns(10);
		ncuspw = new JPasswordField();
		ncuspw.setBounds(108, 104, 226, 24);
		panel_1.add(ncuspw);
		ncuspw.setColumns(10);
		ncuspwr = new JPasswordField();
		ncuspwr.setBounds(108, 150, 226, 24);
		panel_1.add(ncuspwr);
		ncuspwr.setColumns(10);

		JButton btn_cregister = new JButton("注册");
		btn_cregister.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		btn_cregister.setBounds(135, 184, 113, 27);
		panel_1.add(btn_cregister);

		JRadioButton radioButtonMan = new JRadioButton("男");
		radioButtonMan.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		radioButtonMan.setSelected(true);
		radioButtonMan.setBounds(252, 56, 43, 27);
		panel_1.add(radioButtonMan);
		JRadioButton radioButtonWoman = new JRadioButton("女");
		radioButtonWoman.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		radioButtonWoman.setBounds(295, 56, 43, 27);
		panel_1.add(radioButtonWoman);
		ButtonGroup group = new ButtonGroup();// 创建单选按钮组
		group.add(radioButtonMan);
		group.add(radioButtonWoman);

		btn_clogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					readcus();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btn_cregister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					registercus();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
}

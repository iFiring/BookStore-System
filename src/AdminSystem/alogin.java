package AdminSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class alogin {

	private JFrame loginframe;
	private JTextField adminid;
	private JPasswordField adminpw;
	private Connection conDB;
	private Statement staDB;

	/**
	 * 启动登录程序.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					alogin window = new alogin();
					window.loginframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws SQLException
	 */
	public alogin() {
		OpenDB();
		adminlogin();
	}

	/**
	 * Initialize the contents of the frame.
	 */
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

	private void readadmin() throws SQLException {
		ResultSet resultset = staDB.executeQuery("select a_id,a_password from admin");
		String admin = adminid.getText();
		String password = adminpw.getText();
		String a = null;
		String b = null;
		if (admin.equals("")) {
			JOptionPane.showMessageDialog(null, "账号不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		} else if (password.equals("")) {
			JOptionPane.showMessageDialog(null, "密码不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		while (resultset.next()) {
			a = resultset.getString(1);
			b = resultset.getString(2);
			if (a.compareTo(admin) != 0 || b.compareTo(password) != 0) {
			} else {
				loginframe.setVisible(false);
				new amanager(admin, staDB);
				break;
			}
		}
		;
		if (a.compareTo(admin) != 0 || b.compareTo(password) != 0) {
			JOptionPane.showMessageDialog(null, "账号或密码错误！", "错误", JOptionPane.ERROR_MESSAGE);
		}
		;
	}

	private void adminlogin() {
		loginframe = new JFrame();
		loginframe.setForeground(Color.WHITE);
		loginframe.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		loginframe.setTitle("欢迎登录");
		loginframe.setBackground(Color.WHITE);
		loginframe.setBounds(100, 100, 400, 300);
		loginframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginframe.getContentPane().setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 45, 382, 68);
		loginframe.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		JLabel label = new JLabel("账户：");
		label.setFont(new Font("微软雅黑 Light", Font.PLAIN, 15));
		label.setBounds(38, 16, 75, 25);
		panel_1.add(label);

		adminid = new JTextField();
		adminid.setBounds(100, 18, 218, 25);
		adminid.setColumns(10);
		panel_1.add(adminid);

		JLabel label_2 = new JLabel("管理员登录");
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		label_2.setBounds(156, 8, 75, 32);
		loginframe.getContentPane().add(label_2);
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 111, 382, 68);
		loginframe.getContentPane().add(panel);

		JLabel label_1 = new JLabel("密码：");
		label_1.setFont(new Font("微软雅黑 Light", Font.PLAIN, 15));
		label_1.setBounds(38, 16, 75, 25);
		panel.add(label_1);

		adminpw = new JPasswordField();
		adminpw.setColumns(10);
		adminpw.setBounds(100, 18, 218, 25);
		panel.add(adminpw);

		JButton btn_alogin = new JButton("登录");
		btn_alogin.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		btn_alogin.setBounds(134, 204, 113, 27);
		loginframe.getContentPane().add(btn_alogin);

		btn_alogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					readadmin();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
}
package AdminSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class amanager{

	private JFrame frame;
	private JTextField booksearchtext;
	private int adminnum=0,booknum=0,allnum=0,buy=0, borrow=0,back=0,cusmannum=0,adminmannum=0;
	private JTextField bookids,booknames,bookauthors,bookpublishs,booknums,bookprices;
	private JTextField bookaddid,bookaddname,bookaddauthor,bookaddpublish,bookaddnum,bookaddprice;
	private JTextField textField,textField_1,textField_2,textField_3,textField_4,textField_5,textField_6;
	private JTextField textField_7,textField_8,textField_9,textField_10,textField_11,textField_12;

	/** 启动后台程序.
	 * @throws SQLException */
	public amanager(String x,Statement sta) throws SQLException {
		bookstoreinf(x,sta);
		initialize(x,sta);
	}
	private void bookstoreinf(String x,Statement sta) throws SQLException{
		ResultSet result1=sta.executeQuery("select a_id from admin");
		while(result1.next()){adminnum++;};
		ResultSet result2=sta.executeQuery("select b_id from books");
		while(result2.next()){allnum++;};
		ResultSet result3=sta.executeQuery("select b_id from borrow where b_type='借阅'");
		while(result3.next()){borrow++;};
		ResultSet result4=sta.executeQuery("select b_id from borrow where b_type='购买'");
		while(result4.next()){buy++;};
		ResultSet result5=sta.executeQuery("select b_id from borrow where b_type='已还'");
		while(result5.next()){back++;};
		ResultSet result6=sta.executeQuery("select b_id from borrow where b_type='购买' OR b_type='借阅'");booknum=allnum;
		while(result6.next()){booknum--;};
	}
	private void booksearch(Statement sta) throws SQLException{
		String booksearch=booksearchtext.getText();
		ResultSet rs=sta.executeQuery("select b_id,b_name,b_author,b_publish,b_num,b_price from books where b_name = '"+booksearch+"'");
		if(rs.next()){
		bookids.setText(rs.getString(1));booknames.setText(rs.getString(2));bookauthors.setText(rs.getString(3));
		bookpublishs.setText(rs.getString(4));booknums.setText(rs.getString(5));bookprices.setText(rs.getString(6));}
		else{JOptionPane.showMessageDialog(null,"您输入的图书名称不存在，请重新输入","输入错误", JOptionPane.YES_NO_OPTION);}
	}
	private void bookupdate(Statement sta) throws SQLException{
		sta.executeUpdate("update books set b_id='"+bookids.getText()+"',b_name='"+booknames.getText()+"',b_author='"+bookauthors.getText()+"',b_publish='"+bookpublishs.getText()+"',b_num='"+booknums.getText()+"',b_price='"+bookprices.getText()+"' where b_id='"+bookids.getText()+"'");
		JOptionPane.showMessageDialog(null,"图书数据修改成功！");
	}
	private void bookdelete(Statement sta) throws SQLException{
		sta.execute("delete from books where b_id = '"+bookids.getText()+"'");
		JOptionPane.showMessageDialog(null,"图书数据删除成功！");
	}
	private void bookinsert(Statement sta) throws SQLException{
		sta.execute("insert into books values('"+bookaddid.getText()+"','"+bookaddname.getText()+"','"+bookaddauthor.getText()+"','"+bookaddpublish.getText()+"','"+bookaddnum.getText()+"','"+bookaddprice.getText()+"')");
		JOptionPane.showMessageDialog(null,"图书数据添加成功！");
	}
	private void adminmaninsert(Statement sta) throws SQLException{
		sta.execute("insert into admin values('"+textField.getText()+"','"+textField_1.getText()+"','"+textField_2.getText()+"','"+textField_3.getText()+"')");
		JOptionPane.showMessageDialog(null,"员工数据添加成功！");
	}
	private void adminmansearch(Statement sta) throws SQLException{
		String search=textField_4.getText();
		ResultSet rs=sta.executeQuery("select a_id,a_password,a_age,a_sex from admin where a_id = '"+search+"'");
		if(rs.next()){
		textField_5.setText(rs.getString(1));textField_6.setText(rs.getString(2));textField_7.setText(rs.getString(3));
		textField_8.setText(rs.getString(4));}
		else{JOptionPane.showMessageDialog(null,"您输入的图书名称不存在，请重新输入","输入错误", JOptionPane.YES_NO_OPTION);}
	}
	private void adminmanupdate(Statement sta) throws SQLException{
		sta.executeUpdate("update admin set a_id='"+textField_5.getText()+"',a_password='"+textField_6.getText()+"',a_age='"+textField_7.getText()+"',a_sex='"+textField_8.getText()+"' where a_id='"+textField_4.getText()+"'");
		JOptionPane.showMessageDialog(null,"员工数据修改成功！");
	}
	private void adminmandelete(Statement sta) throws SQLException{
		sta.execute("delete from admin where a_id = '"+textField_4.getText()+"'");
		JOptionPane.showMessageDialog(null,"员工数据删除成功！");
	}
	private void initialize(String x,Statement sta) throws SQLException {
		frame = new JFrame();
		frame.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		frame.setTitle("书店后台管理中心");
		frame.setBounds(100,100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 782, 80);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btn_bsinf = new JButton("书店信息");btn_bsinf.setFont(new Font("微软雅黑", Font.PLAIN, 20));btn_bsinf.setBounds(28, 13, 160, 55);
		btn_bsinf.setAlignmentX(Component.CENTER_ALIGNMENT);panel.add(btn_bsinf);
		JButton btn_cusman = new JButton("会员管理");btn_cusman.setFont(new Font("微软雅黑", Font.PLAIN, 20));btn_cusman.setBounds(404, 13, 160, 55);
		panel.add(btn_cusman);
		JButton btn_bookman = new JButton("图书管理");btn_bookman.setFont(new Font("微软雅黑", Font.PLAIN, 20));btn_bookman.setBounds(216, 13, 160, 55);
		panel.add(btn_bookman);
		JButton btn_sysset = new JButton("系统设置");btn_sysset.setFont(new Font("微软雅黑", Font.PLAIN, 21));btn_sysset.setBounds(594, 13, 160, 55);
		panel.add(btn_sysset);
		
		JPanel panel_1 = new JPanel();panel_1.setBounds(0, 80, 782, 473);frame.getContentPane().add(panel_1);panel_1.setLayout(new CardLayout(0, 0));
		
		JPanel panelbsinf = new JPanel();panel_1.add(panelbsinf, "name_56002379627557");panelbsinf.setLayout(null);
		JLabel label_3 = new JLabel("书店名称：");label_3.setBounds(28, 13, 96, 44);label_3.setFont(new Font("微软雅黑", Font.PLAIN, 18));panelbsinf.add(label_3);
		JLabel label_4 = new JLabel("开店日期：");label_4.setFont(new Font("微软雅黑", Font.PLAIN, 18));label_4.setBounds(405, 13, 96, 44);panelbsinf.add(label_4);
		JLabel label_5 = new JLabel("员工人数：");label_5.setFont(new Font("微软雅黑", Font.PLAIN, 18));label_5.setBounds(28, 101, 96, 44);panelbsinf.add(label_5);
		JLabel label_6 = new JLabel("库存状态：");label_6.setFont(new Font("微软雅黑", Font.PLAIN, 18));label_6.setBounds(405, 101, 96, 44);panelbsinf.add(label_6);
		JLabel label_7 = new JLabel("买书人数：");label_7.setFont(new Font("微软雅黑", Font.PLAIN, 18));label_7.setBounds(28, 195, 96, 44);panelbsinf.add(label_7);
		JLabel label_8 = new JLabel("借书人数：");label_8.setFont(new Font("微软雅黑", Font.PLAIN, 18));label_8.setBounds(405, 195, 96, 44);panelbsinf.add(label_8);
		JLabel label_9 = new JLabel("软件版本：");label_9.setFont(new Font("微软雅黑", Font.PLAIN, 18));label_9.setBounds(28, 283, 96, 44);panelbsinf.add(label_9);
		JLabel bsname = new JLabel("江财书店");bsname.setFont(new Font("微软雅黑", Font.PLAIN, 18));bsname.setBounds(161, 13, 96, 44);panelbsinf.add(bsname);
		JLabel bsopendate = new JLabel("2015-7-1");bsopendate.setFont(new Font("微软雅黑", Font.PLAIN, 18));bsopendate.setBounds(537, 13, 96, 44);panelbsinf.add(bsopendate);
		JLabel bsadminnum = new JLabel(adminnum+"");bsadminnum.setFont(new Font("微软雅黑", Font.PLAIN, 18));bsadminnum.setBounds(161, 101, 96, 44);panelbsinf.add(bsadminnum);
		JLabel bsbooknum = new JLabel(booknum+"/"+allnum+"（剩余/总量）");bsbooknum.setFont(new Font("微软雅黑", Font.PLAIN, 18));bsbooknum.setBounds(537, 101, 151, 44);panelbsinf.add(bsbooknum);
		JLabel bsbuy = new JLabel(buy+"");bsbuy.setFont(new Font("微软雅黑", Font.PLAIN, 18));bsbuy.setBounds(161, 195, 96, 44);panelbsinf.add(bsbuy);
		JLabel bsborrow = new JLabel(borrow+"");bsborrow.setFont(new Font("微软雅黑", Font.PLAIN, 18));bsborrow.setBounds(537, 195, 96, 44);panelbsinf.add(bsborrow);
		JLabel lblV = new JLabel("V1.00");lblV.setFont(new Font("微软雅黑", Font.PLAIN, 18));lblV.setBounds(161, 283, 96, 44);panelbsinf.add(lblV);
		
		JTabbedPane tabbookman = new JTabbedPane(JTabbedPane.LEFT);tabbookman.setFont(new Font("微软雅黑", Font.PLAIN, 20));panel_1.add(tabbookman, "name_112312636927021");
		JPanel panel_6 = new JPanel();tabbookman.addTab("图书信息", null, panel_6, null);panel_6.setLayout(null);
		JLabel label = new JLabel("库存信息：");label.setFont(new Font("微软雅黑", Font.PLAIN, 18));label.setBounds(49, 39, 96, 44);panel_6.add(label);
		JLabel label_1 = new JLabel("已借出：");label_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));label_1.setBounds(314, 39, 96, 44);panel_6.add(label_1);
		JLabel label_2 = new JLabel("已归还：");label_2.setFont(new Font("微软雅黑", Font.PLAIN, 18));label_2.setBounds(49, 120, 96, 44);panel_6.add(label_2);
		JLabel label_10 = new JLabel("已卖出：");label_10.setFont(new Font("微软雅黑", Font.PLAIN, 18));label_10.setBounds(314, 120, 96, 44);panel_6.add(label_10);
		JLabel bsbooknum1 = new JLabel(booknum+"/"+allnum+"（剩余/总量）");bsbooknum1.setFont(new Font("微软雅黑", Font.PLAIN, 18));bsbooknum1.setBounds(159, 39, 141, 44);panel_6.add(bsbooknum1);
		JLabel bsback = new JLabel(back+"");bsback.setFont(new Font("微软雅黑", Font.PLAIN, 18));bsback.setBounds(159, 120, 96, 44);panel_6.add(bsback);
		JLabel bsborrow1 = new JLabel(borrow+"");bsborrow1.setFont(new Font("微软雅黑", Font.PLAIN, 18));bsborrow1.setBounds(424, 39, 96, 44);panel_6.add(bsborrow1);
		JLabel bsbuy1 = new JLabel(buy+"");bsbuy1.setFont(new Font("微软雅黑", Font.PLAIN, 18));bsbuy1.setBounds(424, 120, 96, 44);panel_6.add(bsbuy1);
		JPanel panel_7 = new JPanel();tabbookman.addTab("查询图书", null, panel_7, null);panel_7.setLayout(null);
		JLabel label_11 = new JLabel("请输入图书名称：");label_11.setFont(new Font("微软雅黑", Font.PLAIN, 19));label_11.setBounds(14, 13, 158, 66);panel_7.add(label_11);
		
		booksearchtext = new JTextField("经济法");
		booksearchtext.setFont(new Font("微软雅黑", Font.PLAIN, 15));booksearchtext.setBounds(172, 33, 334, 32);panel_7.add(booksearchtext);booksearchtext.setColumns(10);
		
		JButton btn_bsmansearch = new JButton("查询");
		btn_bsmansearch.setFont(new Font("微软雅黑", Font.PLAIN, 15));btn_bsmansearch.setBounds(520, 36, 113, 27);panel_7.add(btn_bsmansearch);
		bookids = new JTextField();
		bookids.setFont(new Font("微软雅黑", Font.PLAIN, 15));bookids.setBounds(172, 101, 334, 24);panel_7.add(bookids);bookids.setColumns(10);
		booknames = new JTextField();
		booknames.setFont(new Font("微软雅黑", Font.PLAIN, 15));booknames.setColumns(10);booknames.setBounds(172, 138, 334, 24);panel_7.add(booknames);
		bookauthors = new JTextField();
		bookauthors.setFont(new Font("微软雅黑", Font.PLAIN, 15));bookauthors.setColumns(10);bookauthors.setBounds(172, 175, 334, 24);panel_7.add(bookauthors);
		bookpublishs = new JTextField();
		bookpublishs.setFont(new Font("微软雅黑", Font.PLAIN, 15));bookpublishs.setColumns(10);bookpublishs.setBounds(172, 212, 334, 24);panel_7.add(bookpublishs);
		booknums = new JTextField();
		booknums.setFont(new Font("微软雅黑", Font.PLAIN, 15));booknums.setColumns(10);booknums.setBounds(172, 249, 334, 24);panel_7.add(booknums);
		bookprices = new JTextField();
		bookprices.setFont(new Font("微软雅黑", Font.PLAIN, 15));bookprices.setColumns(10);bookprices.setBounds(172, 286, 334, 24);panel_7.add(bookprices);
		JLabel lblid = new JLabel("ID：");lblid.setFont(new Font("微软雅黑", Font.PLAIN, 16));lblid.setBounds(94, 92, 64, 40);panel_7.add(lblid);
		JLabel label_13 = new JLabel("名称：");label_13.setFont(new Font("微软雅黑", Font.PLAIN, 16));label_13.setBounds(94, 129, 64, 40);panel_7.add(label_13);
		JLabel label_14 = new JLabel("作者：");label_14.setFont(new Font("微软雅黑", Font.PLAIN, 16));label_14.setBounds(94, 166, 64, 40);panel_7.add(label_14);
		JLabel label_15 = new JLabel("出版社：");label_15.setFont(new Font("微软雅黑", Font.PLAIN, 16));label_15.setBounds(94, 203, 64, 40);panel_7.add(label_15);
		JLabel label_16 = new JLabel("数量：");label_16.setFont(new Font("微软雅黑", Font.PLAIN, 16));label_16.setBounds(94, 240, 64, 40);panel_7.add(label_16);
		JLabel label_17 = new JLabel("价格：");label_17.setFont(new Font("微软雅黑", Font.PLAIN, 16));label_17.setBounds(94, 277, 64, 40);panel_7.add(label_17);
		JButton btn_update = new JButton("修改");
		btn_update.setFont(new Font("微软雅黑", Font.PLAIN, 15));btn_update.setBounds(195, 379, 113, 27);panel_7.add(btn_update);
		JButton btn_delete = new JButton("删除");
		btn_delete.setFont(new Font("微软雅黑", Font.PLAIN, 15));btn_delete.setBounds(369, 379, 113, 27);panel_7.add(btn_delete);
		JPanel panel_5 = new JPanel();tabbookman.addTab("添加图书", null, panel_5, null);panel_5.setLayout(null);
		bookaddid = new JTextField();
		bookaddid.setFont(new Font("微软雅黑", Font.PLAIN, 15));bookaddid.setColumns(10);bookaddid.setBounds(172, 101, 334, 24);panel_5.add(bookaddid);
		bookaddname = new JTextField();
		bookaddname.setFont(new Font("微软雅黑", Font.PLAIN, 15));bookaddname.setColumns(10);bookaddname.setBounds(172, 138, 334, 24);panel_5.add(bookaddname);
		bookaddauthor = new JTextField();
		bookaddauthor.setFont(new Font("微软雅黑", Font.PLAIN, 15));bookaddauthor.setColumns(10);bookaddauthor.setBounds(172, 175, 334, 24);panel_5.add(bookaddauthor);
		bookaddpublish = new JTextField();
		bookaddpublish.setFont(new Font("微软雅黑", Font.PLAIN, 15));bookaddpublish.setColumns(10);bookaddpublish.setBounds(172, 212, 334, 24);panel_5.add(bookaddpublish);
		bookaddnum = new JTextField();
		bookaddnum.setFont(new Font("微软雅黑", Font.PLAIN, 15));bookaddnum.setColumns(10);bookaddnum.setBounds(172, 251, 334, 24);panel_5.add(bookaddnum);
		bookaddprice = new JTextField();
		bookaddprice.setFont(new Font("微软雅黑", Font.PLAIN, 15));bookaddprice.setColumns(10);bookaddprice.setBounds(172, 288, 334, 24);panel_5.add(bookaddprice);
		JLabel label_12 = new JLabel("ID：");label_12.setFont(new Font("微软雅黑", Font.PLAIN, 16));label_12.setBounds(94, 92, 64, 40);panel_5.add(label_12);
		JLabel label_18 = new JLabel("名称：");label_18.setFont(new Font("微软雅黑", Font.PLAIN, 16));label_18.setBounds(94, 129, 64, 40);panel_5.add(label_18);
		JLabel label_19 = new JLabel("作者：");label_19.setFont(new Font("微软雅黑", Font.PLAIN, 16));label_19.setBounds(94, 166, 64, 40);panel_5.add(label_19);
		JLabel label_20 = new JLabel("出版社：");label_20.setFont(new Font("微软雅黑", Font.PLAIN, 16));label_20.setBounds(94, 203, 64, 40);panel_5.add(label_20);
		JLabel label_21 = new JLabel("数量：");label_21.setFont(new Font("微软雅黑", Font.PLAIN, 16));label_21.setBounds(94, 242, 64, 40);panel_5.add(label_21);
		JLabel label_22 = new JLabel("价格：");label_22.setFont(new Font("微软雅黑", Font.PLAIN, 16));label_22.setBounds(94, 279, 64, 40);panel_5.add(label_22);
		JButton btn_insert = new JButton("添加");
		btn_insert.setFont(new Font("微软雅黑", Font.PLAIN, 15));btn_insert.setBounds(260, 368, 113, 27);panel_5.add(btn_insert);
		JLabel label_23 = new JLabel("请输入图书数据");label_23.setHorizontalAlignment(SwingConstants.CENTER);label_23.setFont(new Font("微软雅黑", Font.PLAIN, 20));label_23.setBounds(126, 13, 334, 75);panel_5.add(label_23);
		
		JTabbedPane tabcusman = new JTabbedPane(JTabbedPane.LEFT);tabcusman.setFont(new Font("微软雅黑", Font.PLAIN, 20));panel_1.add(tabcusman, "name_112490622048714");
		JPanel panel_3 = new JPanel();tabcusman.addTab("会员信息", null, panel_3, null);panel_3.setLayout(null);
		JLabel label_24 = new JLabel("会员人数：");label_24.setBounds(49, 39, 96, 44);label_24.setFont(new Font("微软雅黑", Font.PLAIN, 18));panel_3.add(label_24);
		
		ResultSet rs1=sta.executeQuery("select r_id from reader");
		while(rs1.next()){cusmannum++;};
		
		JLabel label_29 = new JLabel(cusmannum+"");label_29.setFont(new Font("微软雅黑", Font.PLAIN, 18));label_29.setBounds(159, 39, 96, 44);panel_3.add(label_29);
		JLabel label_35 = new JLabel("软件版本：");label_35.setFont(new Font("微软雅黑", Font.PLAIN, 18));label_35.setBounds(314, 39, 96, 44);panel_3.add(label_35);
		JLabel label_40 = new JLabel("V1.00");label_40.setFont(new Font("微软雅黑", Font.PLAIN, 18));label_40.setBounds(424, 39, 96, 44);panel_3.add(label_40);
		JLabel label_41 = new JLabel("开发人员：王川川(JAVA)，颜凯(SQL)，陈曦(E-R)，朱康安(SQL)");label_41.setFont(new Font("微软雅黑", Font.PLAIN, 18));label_41.setBounds(49, 120, 529, 44);panel_3.add(label_41);
		JPanel panel_4 = new JPanel();tabcusman.addTab("查询会员", null, panel_4, null);panel_4.setLayout(null);
		JLabel lblid_2 = new JLabel("请输入会员ID：");lblid_2.setFont(new Font("微软雅黑", Font.PLAIN, 19));lblid_2.setBounds(14, 13, 158, 66);panel_4.add(lblid_2);
		textField_9 = new JTextField("");
		textField_9.setFont(new Font("微软雅黑", Font.PLAIN, 15));textField_9.setColumns(10);textField_9.setBounds(172, 33, 334, 32);panel_4.add(textField_9);
		JButton button_3 = new JButton("查询");
		button_3.setFont(new Font("微软雅黑", Font.PLAIN, 15));button_3.setBounds(520, 36, 113, 27);panel_4.add(button_3);
		JLabel label_43 = new JLabel("ID：");label_43.setFont(new Font("微软雅黑", Font.PLAIN, 16));label_43.setBounds(94, 153, 64, 40);panel_4.add(label_43);
		textField_10 = new JTextField();
		textField_10.setFont(new Font("微软雅黑", Font.PLAIN, 15));textField_10.setColumns(10);textField_10.setBounds(172, 162, 334, 24);panel_4.add(textField_10);
		JLabel label_44 = new JLabel("性别：");label_44.setFont(new Font("微软雅黑", Font.PLAIN, 16));label_44.setBounds(94, 190, 64, 40);panel_4.add(label_44);
		textField_11 = new JTextField();
		textField_11.setFont(new Font("微软雅黑", Font.PLAIN, 15));textField_11.setColumns(10);textField_11.setBounds(172, 199, 334, 24);panel_4.add(textField_11);
		JLabel label_45 = new JLabel("年龄：");label_45.setFont(new Font("微软雅黑", Font.PLAIN, 16));label_45.setBounds(94, 227, 64, 40);panel_4.add(label_45);
		textField_12 = new JTextField();
		textField_12.setFont(new Font("微软雅黑", Font.PLAIN, 15));textField_12.setColumns(10);textField_12.setBounds(172, 236, 334, 24);panel_4.add(textField_12);
		JButton button_4 = new JButton("修改");
		button_4.setFont(new Font("微软雅黑", Font.PLAIN, 15));button_4.setBounds(195, 354, 113, 27);panel_4.add(button_4);
		JButton button_5 = new JButton("删除");
		button_5.setFont(new Font("微软雅黑", Font.PLAIN, 15));button_5.setBounds(367, 354, 113, 27);panel_4.add(button_5);
		
		ResultSet rs2=sta.executeQuery("select a_id from admin");
		while(rs2.next()){adminmannum++;};
		
		JTabbedPane tabsysset = new JTabbedPane(JTabbedPane.LEFT);tabsysset.setFont(new Font("微软雅黑", Font.PLAIN, 20));panel_1.add(tabsysset, "name_112496973125442");
		JPanel panel_8 = new JPanel();tabsysset.addTab("系统信息", null, panel_8, null);panel_8.setLayout(null);
		JLabel label_27 = new JLabel("员工人数：");label_27.setFont(new Font("微软雅黑", Font.PLAIN, 18));label_27.setBounds(49, 39, 108, 44);panel_8.add(label_27);
		JLabel label_28 = new JLabel("软件版本：");label_28.setFont(new Font("微软雅黑", Font.PLAIN, 18));label_28.setBounds(314, 39, 96, 44);panel_8.add(label_28);
		JLabel lbljavasqlersql = new JLabel("开发人员：王川川(JAVA)，颜凯(SQL)，陈曦(E-R)，朱康安(SQL)");lbljavasqlersql.setFont(new Font("微软雅黑", Font.PLAIN, 18));lbljavasqlersql.setBounds(49, 120, 529, 44);panel_8.add(lbljavasqlersql);
		JLabel label_30 = new JLabel("V1.00");label_30.setFont(new Font("微软雅黑", Font.PLAIN, 18));label_30.setBounds(424, 39, 96, 44);panel_8.add(label_30);
		JLabel label_31 = new JLabel(adminmannum+"");label_31.setFont(new Font("微软雅黑", Font.PLAIN, 18));label_31.setBounds(139, 39, 96, 44);panel_8.add(label_31);
		JPanel panel_10 = new JPanel();
		tabsysset.addTab("查询员工", null, panel_10, null);
		panel_10.setLayout(null);
		JLabel lblid_1 = new JLabel("请输入管理员ID：");
		lblid_1.setFont(new Font("微软雅黑", Font.PLAIN, 19));
		lblid_1.setBounds(14, 13, 158, 66);
		panel_10.add(lblid_1);
		textField_4 = new JTextField("");
		textField_4.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		textField_4.setColumns(10);
		textField_4.setBounds(172, 33, 334, 32);
		panel_10.add(textField_4);
		JButton button = new JButton("查询");
		button.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		button.setBounds(520, 36, 113, 27);
		panel_10.add(button);
		JLabel label_36 = new JLabel("ID：");
		label_36.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_36.setBounds(94, 92, 64, 40);
		panel_10.add(label_36);
		textField_5 = new JTextField();
		textField_5.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		textField_5.setColumns(10);
		textField_5.setBounds(172, 101, 334, 24);
		panel_10.add(textField_5);
		textField_6 = new JTextField();
		textField_6.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		textField_6.setColumns(10);
		textField_6.setBounds(172, 138, 334, 24);
		panel_10.add(textField_6);
		JLabel label_37 = new JLabel("密码：");
		label_37.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_37.setBounds(94, 129, 64, 40);
		panel_10.add(label_37);
		JLabel label_38 = new JLabel("年龄：");
		label_38.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_38.setBounds(94, 166, 64, 40);
		panel_10.add(label_38);
		textField_7 = new JTextField();
		textField_7.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		textField_7.setColumns(10);
		textField_7.setBounds(172, 175, 334, 24);
		panel_10.add(textField_7);
		JLabel label_39 = new JLabel("性别：");
		label_39.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_39.setBounds(94, 203, 64, 40);
		panel_10.add(label_39);
		textField_8 = new JTextField();
		textField_8.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		textField_8.setColumns(10);
		textField_8.setBounds(172, 212, 334, 24);
		panel_10.add(textField_8);
		JButton button_1 = new JButton("修改");
		button_1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		button_1.setBounds(194, 275, 113, 27);
		panel_10.add(button_1);
		JButton button_2 = new JButton("删除");
		button_2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		button_2.setBounds(372, 275, 113, 27);
		panel_10.add(button_2);
		button.addActionListener(new ActionListener(){	public void actionPerformed(ActionEvent e){
			try {adminmansearch(sta);} catch (SQLException e1) {e1.printStackTrace();}
		}});
		button_1.addActionListener(new ActionListener(){	public void actionPerformed(ActionEvent e){
			try {adminmanupdate(sta);} catch (SQLException e1) {e1.printStackTrace();}
		}});
		button_2.addActionListener(new ActionListener(){	public void actionPerformed(ActionEvent e){
			try {adminmandelete(sta);} catch (SQLException e1) {e1.printStackTrace();}
		}});
		JPanel panel_9 = new JPanel();tabsysset.addTab("添加员工", null, panel_9, null);panel_9.setLayout(null);
		JLabel label_25 = new JLabel("请输入管理员数据");label_25.setHorizontalAlignment(SwingConstants.CENTER);label_25.setFont(new Font("微软雅黑", Font.PLAIN, 20));label_25.setBounds(172, 13, 334, 75);panel_9.add(label_25);
		textField = new JTextField();
		textField.setFont(new Font("微软雅黑", Font.PLAIN, 15));textField.setColumns(10);textField.setBounds(172, 101, 334, 24);panel_9.add(textField);
		JLabel label_26 = new JLabel("ID：");label_26.setFont(new Font("微软雅黑", Font.PLAIN, 16));label_26.setBounds(94, 92, 64, 40);panel_9.add(label_26);
		JLabel label_32 = new JLabel("密码：");label_32.setFont(new Font("微软雅黑", Font.PLAIN, 16));label_32.setBounds(94, 129, 64, 40);panel_9.add(label_32);
		textField_1 = new JTextField();
		textField_1.setFont(new Font("微软雅黑", Font.PLAIN, 15));textField_1.setColumns(10);textField_1.setBounds(172, 138, 334, 24);panel_9.add(textField_1);
		textField_2 = new JTextField();
		textField_2.setFont(new Font("微软雅黑", Font.PLAIN, 15));textField_2.setColumns(10);textField_2.setBounds(172, 174, 334, 24);panel_9.add(textField_2);
		JLabel label_33 = new JLabel("年龄：");label_33.setFont(new Font("微软雅黑", Font.PLAIN, 16));label_33.setBounds(94, 165, 64, 40);panel_9.add(label_33);
		JLabel label_34 = new JLabel("性别：");label_34.setFont(new Font("微软雅黑", Font.PLAIN, 16));label_34.setBounds(94, 202, 64, 40);panel_9.add(label_34);
		textField_3 = new JTextField();
		textField_3.setFont(new Font("微软雅黑", Font.PLAIN, 15));textField_3.setColumns(10);textField_3.setBounds(172, 211, 334, 24);panel_9.add(textField_3);
		JButton button_0 = new JButton("添加");button_0.setFont(new Font("微软雅黑", Font.PLAIN, 15));button_0.setBounds(285, 266, 113, 27);panel_9.add(button_0);
		
		btn_bsinf.addActionListener(new ActionListener(){	public void actionPerformed(ActionEvent e){
			panelbsinf.setVisible(true);	tabbookman.setVisible(false);	tabcusman.setVisible(false);	tabsysset.setVisible(false);
			}});
		btn_bookman.addActionListener(new ActionListener(){	public void actionPerformed(ActionEvent e){
			panelbsinf.setVisible(false);	tabbookman.setVisible(true);	tabcusman.setVisible(false);	tabsysset.setVisible(false);
			}});
		btn_cusman.addActionListener(new ActionListener(){	public void actionPerformed(ActionEvent e){
			panelbsinf.setVisible(false);	tabbookman.setVisible(false);	tabcusman.setVisible(true);		tabsysset.setVisible(false);
			}});
		btn_sysset.addActionListener(new ActionListener(){	public void actionPerformed(ActionEvent e){
			panelbsinf.setVisible(false);	tabbookman.setVisible(false);	tabcusman.setVisible(false);	tabsysset.setVisible(true);
			}});
		btn_bsmansearch.addActionListener(new ActionListener(){	public void actionPerformed(ActionEvent e){
			try {booksearch(sta);} catch (SQLException e1) {e1.printStackTrace();}
		}});
		btn_update.addActionListener(new ActionListener(){	public void actionPerformed(ActionEvent e){
			try {bookupdate(sta);} catch (SQLException e1) {e1.printStackTrace();}
		}});
		btn_delete.addActionListener(new ActionListener(){	public void actionPerformed(ActionEvent e){
			try {bookdelete(sta);} catch (SQLException e1) {e1.printStackTrace();}
		}});
		btn_insert.addActionListener(new ActionListener(){	public void actionPerformed(ActionEvent e){
			try {bookinsert(sta);} catch (SQLException e1) {e1.printStackTrace();}
		}});
		button_0.addActionListener(new ActionListener(){	public void actionPerformed(ActionEvent e){
			try {adminmaninsert(sta);} catch (SQLException e1) {e1.printStackTrace();}
		}});
	}
}

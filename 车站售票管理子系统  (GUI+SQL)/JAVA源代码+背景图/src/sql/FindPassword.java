package sql;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class FindPassword implements ActionListener{
	String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String dbURL = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Ticket_System";
	String userName = "sa";
	String userPwd = "sa";
	Statement stmt;
	Connection dbConn = null;
	public void actionPerformed(ActionEvent e) {
		JFrame f = new JFrame();
		JLabel lc = new JLabel("  用户名  ");
		JLabel mb = new JLabel("    密保    ");
		JLabel mbdaan = new JLabel("密保答案");
		JButton chax = new JButton("  确认  ");chax.setFont(new Font("黑体",Font.PLAIN,20));//设置字体的字体，大小
		JButton back = new JButton("返回登录");back.setFont(new Font("黑体",Font.PLAIN,20));//设置字体的字体，大小
		JTextField f1 = new JTextField(8);
		JTextField f2 = new JTextField(8);
		JTextField f3 = new JTextField(8);
		f.add(lc);
		f.add(f1);
		f.add(mb);
		f.add(f2);
		f.add(mbdaan);
		f.add(f3);
		f.add(chax);
		f.add(back);
		f.setResizable(false);       //不允许修改窗口
		f.setSize(200,220);
		f.setLayout(new FlowLayout(FlowLayout.CENTER,20,10));///流式布局
		f.setLocationRelativeTo(null);//窗口位置居中
		f.setVisible(true);
		ActionListener listener = new ActionListener()    //确认退票监听
				{
					public void actionPerformed(ActionEvent e) {
						String yhm = f1.getText();
						String mb = f2.getText();
						String mbdaan = f3.getText();
						try
						 {      Class.forName(driverName);
						 		dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
						 		stmt=dbConn.createStatement();
						       String  s="(Select 用户密码 from USER_LIBRARY where 用户名='"+yhm+"' AND 密保='"+mb+"'and 密保答案 = '"+mbdaan+"')";
						       ResultSet r=stmt.executeQuery(s);   //游标 查询表内内容
						       if(r.next()) {
						    	   f1.setText("");f2.setText("");f3.setText("");
						    	   JOptionPane.showMessageDialog(null, "您的密码为："+r.getString(1),"温馨提示",JOptionPane.PLAIN_MESSAGE);//弹窗
						       }else {
						    	   f1.setText("");f2.setText("");f3.setText("");
						    	   JOptionPane.showMessageDialog(null, "输入错误！！！","提示",JOptionPane.ERROR_MESSAGE); 
						       }
						 }catch (Exception e1) {
							}
						
					}
				};
				chax.addActionListener(listener);
		ActionListener listener1 = new ActionListener()    //返回监听
				{
					public void actionPerformed(ActionEvent e) {
						f.setVisible(false);
					}
				};
				back.addActionListener(listener1);
	}

}

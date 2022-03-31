package sql;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
public class LoginUI    
{  
	static String a;
	public void loginUI()   //登录模块
	{
		JFrame login_frame = new JFrame("登录");
		login_frame.setSize(260,200);			
		login_frame.setResizable(false);       //不允许修改窗口
		login_frame.setLocationRelativeTo(null);//窗口位置居中
		login_frame.setLayout(new FlowLayout(FlowLayout.CENTER,20,10));///流式布局
		login_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //关闭窗口，释放内存
		JLabel usrname = new JLabel("用户  ");
		JLabel psw = new JLabel("密码  ");
		JTextField f1 = new JTextField(15);
		JPasswordField f2 = new JPasswordField(15);
		JButton login = new JButton("登录"); 
		JButton reset = new JButton("注册"); 
		JButton forget = new JButton("忘记密码"); 
		login_frame.add(usrname);
		login_frame.add(f1);
		login_frame.add(psw);
		login_frame.add(f2);
		login_frame.add(login);
		login_frame.add(reset);
		login_frame.add(forget);
		login_frame.setVisible(true);
		forget.addActionListener(new FindPassword());
		ActionListener listener = new ActionListener()    //登录监听
		{
			public void actionPerformed(ActionEvent event)
			{
			    String textName = f1.getText();
				String textPassword = new String(f2.getPassword());
				login_frame.setVisible(false);
				String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
				String dbURL = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Ticket_System";
				String userName = "sa";
				String userPwd = "sa";
				Statement stmt;
				Statement stmt1;
				Connection dbConn = null;
				try
				   {
					Class.forName(driverName);
					dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
					stmt=dbConn.createStatement();stmt1=dbConn.createStatement();
					String  s="(Select * from  USER_LIBRARY where 用户名='"+textName+"'And 用户密码='"+textPassword+"' )";
					String  s1="(Select * from  ADMINISTRATOR_LIBARARY where 管理员编号='"+textName+"'And 管理员密码='"+textPassword+"' )";
					ResultSet r=stmt.executeQuery(s);  
					ResultSet r1=stmt1.executeQuery(s1);   
					if(r.next())
		     			{
						 login_frame.dispose();
						  a=f1.getText();
						  JOptionPane.showMessageDialog(null, "尊敬的用户，欢迎使用本系统！","温馨提示",JOptionPane.PLAIN_MESSAGE);//弹窗
						  new MainUI().mainUI();   //菜单模块
						 
		     			}
					else if(r1.next())
	     			{
						login_frame.dispose();
						  a=f1.getText();
						  JOptionPane.showMessageDialog(null, "管理员"+textName+"\n您好，欢迎您的使用！","温馨提示",JOptionPane.PLAIN_MESSAGE);//弹窗
						  new GLYUI().glyUI();
					}
					else {
						JOptionPane.showMessageDialog(null, "用户名或密码输入错误，请重新输入！！！","温馨提示",JOptionPane.ERROR_MESSAGE);//弹窗
						f1.setText("");f2.setText("");
						loginUI();
					}
					 dbConn.close();
					
			  }
				catch (Exception e)
				{
					e.printStackTrace();
					System.out.print("连接失败");
				}
			}
		};
		ActionListener listener1 = new ActionListener()   //注册监听
		{    
			public void actionPerformed(ActionEvent event)
			{   
				login_frame.setVisible(false);
				JFrame Reset_frame = new JFrame("注册");
				Reset_frame.setSize(300,280);			
				//Reset_frame.setResizable(false);       //不允许修改窗口
				Reset_frame.setLocationRelativeTo(null);//窗口位置居中
				Reset_frame.setLayout(new FlowLayout(FlowLayout.CENTER,20,10));///流式布局
				Reset_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //关闭窗口，释放内存
				JLabel usrname = new JLabel("   用户名    ");
				JLabel psw = new JLabel("    密码      ");
				JLabel psw1 = new JLabel("    密保      ");
				JLabel psw2 = new JLabel("密保答案 ");
				JLabel sfz = new JLabel("身份证");
				JLabel dh = new JLabel("电话号码");
				JButton back = new JButton("返回登录");
				JButton sureButton1 = new JButton("确定");
				JTextField f1 = new JTextField(15);
				JTextField f3 = new JTextField(15);
				JTextField f4 = new JTextField(15);
				JTextField f5= new JTextField(15);
				JTextField f6 = new JTextField(15);
				JPasswordField f2 = new JPasswordField(15);
				Reset_frame.add(usrname);
				Reset_frame.add(f1);
				Reset_frame.add(psw);
				Reset_frame.add(f2);
				Reset_frame.add(psw1);
				Reset_frame.add(f3);
				Reset_frame.add(psw2);
				Reset_frame.add(f4);
				Reset_frame.add(sfz);
				Reset_frame.add(f5);
				Reset_frame.add(dh);
				Reset_frame.add(f6);
				Reset_frame.add(sureButton1);
				Reset_frame.add(back);
				Reset_frame.setVisible(true);
				ActionListener listener1 = new ActionListener()    //返回监听
						{
							public void actionPerformed(ActionEvent e) {
								Reset_frame.setVisible(false);
								login_frame.setVisible(true);
							}
						};
						back.addActionListener(listener1);
				ActionListener errorListener = new ActionListener()
				{ 
					public void actionPerformed(ActionEvent event)
					{    
						String textName = f1.getText();
						String Secret = f3.getText();
						String SecretKey = f4.getText();
						String sfz = f5.getText();
						String dh = f6.getText();
						String textPassword = new String(f2.getPassword());
						login_frame.setVisible(false);
						String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
						String dbURL = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Ticket_System";
						String userName = "sa";
						String userPwd = "sa";
						Statement stmt;
						Connection dbConn = null;
						if(textName.isEmpty()||Secret.isEmpty()||SecretKey.isEmpty()||textPassword.isEmpty()||sfz.isEmpty()||dh.isEmpty()) {
							JOptionPane.showMessageDialog(null, "请输入注册的各项内容","温馨提示",JOptionPane.ERROR_MESSAGE);//消息对话框弹窗
							f1.setText("");f2.setText("");f3.setText("");f4.setText("");f5.setText("");f6.setText("");
							Reset_frame.setVisible(true);
						}
						else
						{
							try
							   {
								Class.forName(driverName);
								dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
								stmt=dbConn.createStatement();
								String  s="(Select * from  USER_LIBRARY where 用户名='"+textName+"')";
								ResultSet r=stmt.executeQuery(s);   //游标
								if(r.next())
					     			{       
										    Reset_frame.setVisible(false);
											JOptionPane.showMessageDialog(null, "用户名已经存在，请重新输入。","温馨提示",JOptionPane.ERROR_MESSAGE);//消息对话框弹窗
											f1.setText("");f2.setText("");f3.setText("");f4.setText("");f5.setText("");f6.setText("");
											Reset_frame.setVisible(true);
					     			}
								else {
									String sql = "insert into USER_LIBRARY values('"+textName+"','"+textPassword+"','"+Secret+"','"+SecretKey+"','"+sfz+"','"+dh+"')"; 
									PreparedStatement pst = dbConn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);  
									pst.executeUpdate();
									pst.close();  
									JOptionPane.showMessageDialog(null,"注册成功");   //消息对话框
									Reset_frame.setVisible(false);
									loginUI();
								    } 
								}
							catch (Exception e)
							{
								e.printStackTrace();
								System.out.print("连接失败");
							}
						}
						
					}
				};
				sureButton1.addActionListener(errorListener);
		}  
	};
		login.addActionListener(listener);
		reset.addActionListener(listener1);
}
	public static void main(String[] agrs)
	{
		LoginUI qq = new LoginUI();
		qq.loginUI();
	}
}
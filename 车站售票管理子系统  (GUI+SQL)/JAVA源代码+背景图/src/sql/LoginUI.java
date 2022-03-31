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
	public void loginUI()   //��¼ģ��
	{
		JFrame login_frame = new JFrame("��¼");
		login_frame.setSize(260,200);			
		login_frame.setResizable(false);       //�������޸Ĵ���
		login_frame.setLocationRelativeTo(null);//����λ�þ���
		login_frame.setLayout(new FlowLayout(FlowLayout.CENTER,20,10));///��ʽ����
		login_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //�رմ��ڣ��ͷ��ڴ�
		JLabel usrname = new JLabel("�û�  ");
		JLabel psw = new JLabel("����  ");
		JTextField f1 = new JTextField(15);
		JPasswordField f2 = new JPasswordField(15);
		JButton login = new JButton("��¼"); 
		JButton reset = new JButton("ע��"); 
		JButton forget = new JButton("��������"); 
		login_frame.add(usrname);
		login_frame.add(f1);
		login_frame.add(psw);
		login_frame.add(f2);
		login_frame.add(login);
		login_frame.add(reset);
		login_frame.add(forget);
		login_frame.setVisible(true);
		forget.addActionListener(new FindPassword());
		ActionListener listener = new ActionListener()    //��¼����
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
					String  s="(Select * from  USER_LIBRARY where �û���='"+textName+"'And �û�����='"+textPassword+"' )";
					String  s1="(Select * from  ADMINISTRATOR_LIBARARY where ����Ա���='"+textName+"'And ����Ա����='"+textPassword+"' )";
					ResultSet r=stmt.executeQuery(s);  
					ResultSet r1=stmt1.executeQuery(s1);   
					if(r.next())
		     			{
						 login_frame.dispose();
						  a=f1.getText();
						  JOptionPane.showMessageDialog(null, "�𾴵��û�����ӭʹ�ñ�ϵͳ��","��ܰ��ʾ",JOptionPane.PLAIN_MESSAGE);//����
						  new MainUI().mainUI();   //�˵�ģ��
						 
		     			}
					else if(r1.next())
	     			{
						login_frame.dispose();
						  a=f1.getText();
						  JOptionPane.showMessageDialog(null, "����Ա"+textName+"\n���ã���ӭ����ʹ�ã�","��ܰ��ʾ",JOptionPane.PLAIN_MESSAGE);//����
						  new GLYUI().glyUI();
					}
					else {
						JOptionPane.showMessageDialog(null, "�û�������������������������룡����","��ܰ��ʾ",JOptionPane.ERROR_MESSAGE);//����
						f1.setText("");f2.setText("");
						loginUI();
					}
					 dbConn.close();
					
			  }
				catch (Exception e)
				{
					e.printStackTrace();
					System.out.print("����ʧ��");
				}
			}
		};
		ActionListener listener1 = new ActionListener()   //ע�����
		{    
			public void actionPerformed(ActionEvent event)
			{   
				login_frame.setVisible(false);
				JFrame Reset_frame = new JFrame("ע��");
				Reset_frame.setSize(300,280);			
				//Reset_frame.setResizable(false);       //�������޸Ĵ���
				Reset_frame.setLocationRelativeTo(null);//����λ�þ���
				Reset_frame.setLayout(new FlowLayout(FlowLayout.CENTER,20,10));///��ʽ����
				Reset_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //�رմ��ڣ��ͷ��ڴ�
				JLabel usrname = new JLabel("   �û���    ");
				JLabel psw = new JLabel("    ����      ");
				JLabel psw1 = new JLabel("    �ܱ�      ");
				JLabel psw2 = new JLabel("�ܱ��� ");
				JLabel sfz = new JLabel("���֤");
				JLabel dh = new JLabel("�绰����");
				JButton back = new JButton("���ص�¼");
				JButton sureButton1 = new JButton("ȷ��");
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
				ActionListener listener1 = new ActionListener()    //���ؼ���
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
							JOptionPane.showMessageDialog(null, "������ע��ĸ�������","��ܰ��ʾ",JOptionPane.ERROR_MESSAGE);//��Ϣ�Ի��򵯴�
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
								String  s="(Select * from  USER_LIBRARY where �û���='"+textName+"')";
								ResultSet r=stmt.executeQuery(s);   //�α�
								if(r.next())
					     			{       
										    Reset_frame.setVisible(false);
											JOptionPane.showMessageDialog(null, "�û����Ѿ����ڣ����������롣","��ܰ��ʾ",JOptionPane.ERROR_MESSAGE);//��Ϣ�Ի��򵯴�
											f1.setText("");f2.setText("");f3.setText("");f4.setText("");f5.setText("");f6.setText("");
											Reset_frame.setVisible(true);
					     			}
								else {
									String sql = "insert into USER_LIBRARY values('"+textName+"','"+textPassword+"','"+Secret+"','"+SecretKey+"','"+sfz+"','"+dh+"')"; 
									PreparedStatement pst = dbConn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);  
									pst.executeUpdate();
									pst.close();  
									JOptionPane.showMessageDialog(null,"ע��ɹ�");   //��Ϣ�Ի���
									Reset_frame.setVisible(false);
									loginUI();
								    } 
								}
							catch (Exception e)
							{
								e.printStackTrace();
								System.out.print("����ʧ��");
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
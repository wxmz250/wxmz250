package sql;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class FirstClassTicket  implements ActionListener {
	String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String dbURL = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Ticket_System";
	String userName = "sa";
	String userPwd = "sa";
	Statement stmt;
	String c;
	String c1;
	Connection dbConn = null;
	JComboBox<String>comboBox = new JComboBox<>();
	public void actionPerformed(ActionEvent e) {
   		JFrame f = new JFrame("购买车票");
		JButton sureButton = new JButton("一等购票");sureButton.setFont(new Font("黑体",Font.PLAIN,20));//设置字体的字体，大小
		JTextField f1 = new JTextField(5);
		JButton back = new JButton("返回");back.setFont(new Font("黑体",Font.PLAIN,20));//设置字体的字体，大小
		JLabel lc =new JLabel("列车号");
		JLabel gps =new JLabel("购票数");
		f.setResizable(false);       //不允许修改窗口
		f.setLayout(null);
		f.setSize(200,240);
		f.add(lc);
		f.add(sureButton);
		f.add(gps);
		f.add(f1);
		f.add(back);
		lc.setBounds(20,5, 65, 40);
		gps.setBounds(10, 50, 65, 40);
		f1.setBounds(80,50,40,30);
		sureButton.setBounds(40,100, 120, 45);
		back.setBounds(65,150, 75, 40); 
		f.setLocationRelativeTo(null);//窗口位置居中
		f.setVisible(false);
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Ticket_System";
			String user="sa";//sa超级管理员
			String password="sa";//密码
			Connection conn=DriverManager.getConnection(url,user,password);
			Statement stmt=conn.createStatement();
		 String  s="(SELECT 列车号 FROM TICKET_INFORMATION WHERE 起始站='"+c+"' AND 终点站='"+c1+"')";
	   	 ResultSet rs=stmt.executeQuery(s);   //游标 查询表内内容
	   	while(rs.next()){
	   		comboBox.addItem(rs.getString(1));
	   		}
		}catch (Exception e1)
		  {
			e1.printStackTrace();
			System.out.print("连接失败");
		  }
		comboBox.setBounds(90,10, 70, 30);
		f.add(comboBox);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //关闭窗口，释放内存
		ActionListener listener1 = new ActionListener()    //购票监听
				{
					public void actionPerformed(ActionEvent event)
					{   String shu = f1.getText();
				   		String piao = comboBox.getSelectedItem().toString();   //获取下拉框中的值
						try {
							Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
							String dbURL = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Ticket_System";
							String userName = "sa";
							String userPwd = "sa";
							Connection dbConn=DriverManager.getConnection(dbURL,userName,userPwd);
							Statement stmt=dbConn.createStatement();
							Statement stmt1=dbConn.createStatement();
				            String  s1="UPDATE TICKET_LEVEL SET 一等票数 =一等票数-'"+shu+"'  WHERE 列车号 = '"+piao+"'";
				            int r1=stmt.executeUpdate(s1);
					if(r1==1)
					{     
						String  s="(SELECT * FROM TICKER_BUYER WHERE 用户名='"+LoginUI.a+"')";
						ResultSet rs=stmt.executeQuery(s);   //游标 查询表内内容
						if(!rs.next()) {
							String  s2= "insert into TICKER_BUYER(用户名,列车号,起始站,终点站,一等票数) values('"+LoginUI.a+"','"+piao+"','"+c+"','"+c1+"','"+shu+"')";
							int r2=stmt1.executeUpdate(s2);
							if(r2==1) {
								System.out.println(r2);
								JOptionPane.showMessageDialog(null, "购票成功","提示消息",JOptionPane.PLAIN_MESSAGE);
							}
						}else {
							String  s2="UPDATE TICKER_BUYER  SET 一等票数 = 一等票数+'"+shu+"' WHERE 列车号 = '"+piao+"'and 用户名 ='"+LoginUI.a+"'and 起始站 ='"+c+"'and 终点站 ='"+c1+"'";
							stmt1.executeUpdate(s2);JOptionPane.showMessageDialog(null, "购票成功","提示消息",JOptionPane.PLAIN_MESSAGE);
						}
					}
						else {
							JOptionPane.showMessageDialog(null, "购票失败","提示消息",JOptionPane.ERROR_MESSAGE);
						}
					}
						catch (Exception e)
						  {
							JOptionPane.showMessageDialog(null, "购票失败,余票不足！！！","提示消息",JOptionPane.ERROR_MESSAGE);
						  }
				   }
				};
				sureButton.addActionListener(listener1);
		ActionListener listener = new ActionListener()    //返回监听
				{
					public void actionPerformed(ActionEvent event)
					{
						f.setVisible(false);
					}
				};
				back.addActionListener(listener);
	 }
     public FirstClassTicket(String city,String city1){
    	 c=city;c1=city1;
     }
}
	
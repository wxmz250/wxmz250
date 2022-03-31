package sql;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AddTicket implements ActionListener{
	String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String dbURL = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Ticket_System";
	String userName = "sa";
	String userPwd = "sa";
	Statement stmt;
	Statement stmt1;
	Connection dbConn = null;
	public static java.sql.Date StringToDate(String sDate) {  
	    String str = sDate;  
	    SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");  
	    java.util.Date d = null;  
	    try {  
	        d = format.parse(str);  
	    } catch (Exception e) {  
	    	JOptionPane.showMessageDialog(null, "请输入正确的日期格式！！！","提示",JOptionPane.ERROR_MESSAGE);
	    }  
	    java.sql.Date date = new java.sql.Date(d.getTime());  
	    return date;  
	} 
	public void actionPerformed(ActionEvent e) {
		JFrame jf= new JFrame("增加列车信息");
		JLabel lch = new JLabel(" 列车号  ");
		JLabel lcz = new JLabel(" 列车长  ");
		JLabel starting = new JLabel(" 起始站  ");
		JLabel time = new JLabel("出发时间");
		JLabel ending = new JLabel(" 终点站  ");
		JLabel time2 = new JLabel("到站时间");
		JLabel date = new JLabel("   日期    ");
		JLabel phone = new JLabel("联系电话");
		JButton fh = new JButton("   返回    ");
		JButton zj = new JButton("确认增加");
		JTextField f1 = new JTextField(10);
		JTextField f2 = new JTextField(10);
		JTextField f3 = new JTextField(10);
		JTextField f4 = new JTextField(10);
		JTextField f5 = new JTextField(10);
		JTextField f6 = new JTextField(10);
		JTextField f7 = new JTextField(10);
		JTextField f8 = new JTextField(10);
		jf.add(lch);
		jf.add(f1);
		jf.add(lcz);
		jf.add(f2);
		jf.add(starting);
		jf.add(f3);
		jf.add(time);
		jf.add(f4);
		jf.add(ending);
		jf.add(f5);
		jf.add(time2);
		jf.add(f6);
		jf.add(date);
		jf.add(f7);
		jf.add(phone);
		jf.add(f8);
		jf.add(zj);
		jf.add(fh);
		jf.setResizable(false);       //不允许修改窗口
		jf.setLayout(new FlowLayout(FlowLayout.CENTER,35,10));
		jf.setSize(300,350);
		jf.setLocationRelativeTo(null);//窗口位置居中
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //关闭窗口，释放内存
		ActionListener listener = new ActionListener()    //确认增加监听
	{
			public void actionPerformed(ActionEvent e) {
				String lc = f1.getText();
				String lcz = f2.getText();
				String starting = f3.getText();
				String time = f4.getText();
				String ending = f5.getText();
				String time2 = f6.getText();
				String date= f7.getText();
				String phone = f8.getText();
				java.sql.Date dt=StringToDate(date);
				try
				{  
					Class.forName(driverName);
					dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
					stmt=dbConn.createStatement();
					 String  s="insert into TICKET_INFORMATION values ('"+lc+"','"+lcz+"','"+starting+"','"+time+"','"+ending+"','"+time2+"','"+dt+"','"+phone+"')";
				       stmt.executeUpdate(s);   
				    	JOptionPane.showMessageDialog(null, "添加成功","提示消息",JOptionPane.PLAIN_MESSAGE);
				    	f1.setText("");f2.setText("");f3.setText("");f4.setText("");
				    	f5.setText("");f6.setText("");f7.setText("");f8.setText("");
			}
				catch (Exception e1)
				{
					JOptionPane.showMessageDialog(null, "请输入正确的时间信息格式！！！","提示",JOptionPane.ERROR_MESSAGE);
				}
			}
	};
     zj.addActionListener(listener);
	ActionListener listener1 = new ActionListener()    //返回监听
			{
				public void actionPerformed(ActionEvent e) {
					jf.setVisible(false);
				}
			};
			fh.addActionListener(listener1);
	}

}

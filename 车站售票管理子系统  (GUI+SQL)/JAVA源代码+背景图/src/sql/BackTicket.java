package sql;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
public class BackTicket implements ActionListener {
	String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String dbURL = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Ticket_System";
	String userName = "sa";
	String userPwd = "sa";
	Statement stmt;
	Statement stmt1;
	Connection dbConn = null;
	String c;
	String c1;
	public void actionPerformed(ActionEvent e) {
		JFrame f = new JFrame("退票");
		JTextField f1 = new JTextField(8);
		JTextField f2 = new JTextField(5);
		JLabel lc = new JLabel("列车");
		JComboBox<String>comboBox = new JComboBox<>();
		comboBox.addItem("商务座退票");
		comboBox.addItem("一等座退票");
		comboBox.addItem("二等座退票");
		comboBox.addItem("无座退票");
		JLabel gps =new JLabel("退票数");
		f.add(lc);
		f.add(f1);
		f.add(comboBox);
		JButton chax = new JButton("确认退票");chax.setFont(new Font("黑体",Font.PLAIN,20));//设置字体的字体，大小
		JButton back = new JButton("返回");back.setFont(new Font("黑体",Font.PLAIN,20));//设置字体的字体，大小
		f.setResizable(false);       //不允许修改窗口
		f.setSize(189,250);
		f.add(gps);
		f.add(f2);
		f.add(chax);
		f.add(back);
		f.setLayout(new FlowLayout(FlowLayout.CENTER,28,10));///流式布局
		f.setLocationRelativeTo(null);//窗口位置居中
		f.setVisible(true);
		ActionListener listener = new ActionListener()    //确认退票监听
	{
		public void actionPerformed(ActionEvent e) {
			 String lch = f1.getText();
			 String shu = f2.getText();
			try
			 {     Class.forName(driverName);
			       dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
			       stmt=dbConn.createStatement();
			       stmt1=dbConn.createStatement();
			       String  s="(Select * from TICKER_BUYER where 起始站='"+c+"' AND 终点站='"+c1+"'and 列车号 = '"+lch+"')";
			       ResultSet r=stmt.executeQuery(s);   //游标 查询表内内容
			       if(r.next()) {
			    	   if(comboBox.getSelectedIndex()==0) {
			    		   String q ="UPDATE TICKET_LEVEL SET 商务票数 =商务票数+'"+shu+"'  WHERE 列车号 = '"+lch+"'";
			    		   String  tp="UPDATE TICKER_BUYER  SET 商务票数 = 商务票数-'"+shu+"' WHERE 列车号 = '"+lch+"'and 用户名 ='"+new LoginUI().a+"'";
			    		   stmt.executeUpdate(tp);int r1=stmt1.executeUpdate(q);
							f1.setText("");JOptionPane.showMessageDialog(null, "退票成功！！！","提示",JOptionPane.PLAIN_MESSAGE);
			    		   }
			    	   else if(comboBox.getSelectedIndex()==1) {
			    		   f1.setText(""); JOptionPane.showMessageDialog(null, "退票成功！！！","提示",JOptionPane.PLAIN_MESSAGE);
			    		   String q ="UPDATE TICKET_LEVEL SET 一等票数 =一等票数+'"+shu+"'  WHERE 列车号 = '"+lch+"'";
			    		   String  tp="UPDATE TICKER_BUYER  SET 一等票数 = 一等票数-'"+shu+"' WHERE 列车号 = '"+lch+"'and 用户名 ='"+new LoginUI().a+"'";
					   		stmt.executeUpdate(tp);stmt1.executeUpdate(q);
			    	   }else if(comboBox.getSelectedIndex()==2) {
			    		   f1.setText(""); JOptionPane.showMessageDialog(null, "退票成功！！！","提示",JOptionPane.PLAIN_MESSAGE);
			    		   String q ="UPDATE TICKET_LEVEL SET 二等票数 =二等票数+'"+shu+"'  WHERE 列车号 = '"+lch+"'";
			    		   String  tp="UPDATE TICKER_BUYER  SET 二等票数 = 二等票数-'"+shu+"' WHERE 列车号 = '"+lch+"'and 用户名 ='"+new LoginUI().a+"'";
					   		stmt.executeUpdate(tp);stmt1.executeUpdate(q);
			    	   }else {
			    		   f1.setText(""); JOptionPane.showMessageDialog(null, "退票成功！！！","提示",JOptionPane.PLAIN_MESSAGE);
			    		   String q ="UPDATE TICKET_LEVEL SET 无等票数 =无等票数+'"+shu+"'  WHERE 列车号 = '"+lch+"'";
			    		   String  tp="UPDATE TICKER_BUYER  SET 无座票数 = 无座票数-'"+shu+"' WHERE 列车号 = '"+lch+"'and 用户名 ='"+new LoginUI().a+"'";
					   		stmt.executeUpdate(tp);stmt1.executeUpdate(q);
			    	   }
			       }
			      else {
			    	   f1.setText(""); f2.setText("");
						JOptionPane.showMessageDialog(null, "退票失败，请核对列车信息！！！","提示",JOptionPane.ERROR_MESSAGE);  
					 }
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "您尚未购买该车票！！！","提示",JOptionPane.ERROR_MESSAGE);  
					f.setVisible(true);
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
	public BackTicket(String city,String city1){
   	 c = city;c1 = city1;
    }
}
	
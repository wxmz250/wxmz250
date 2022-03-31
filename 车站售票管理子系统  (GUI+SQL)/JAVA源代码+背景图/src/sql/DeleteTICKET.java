package sql;

import java.awt.FlowLayout;
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

public class DeleteTICKET implements ActionListener{
	String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String dbURL = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Ticket_System";
	String userName = "sa";
	String userPwd = "sa";
	Statement stmt;
	Statement stmt1;
	Connection dbConn = null;
	public void actionPerformed(ActionEvent e) {
		JFrame jf= new JFrame("删除列车信息");
		JLabel lch = new JLabel("列车号 ");
		JButton fh = new JButton("返回    ");
		JButton zj = new JButton("确认删除");
		JTextField f1 = new JTextField(10);
		jf.add(lch);
		jf.add(f1);
		jf.add(zj);
		jf.add(fh);
		jf.setResizable(false);       //不允许修改窗口
		jf.setLayout(new FlowLayout(FlowLayout.CENTER,35,10));
		jf.setSize(300,250);
		jf.setLocationRelativeTo(null);//窗口位置居中
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //关闭窗口，释放内存
		ActionListener listener = new ActionListener()    //确认删除监听
	{
			public void actionPerformed(ActionEvent e) {
				String lc = f1.getText();
				try
				{  
					Class.forName(driverName);
					dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
					stmt=dbConn.createStatement();
					String  s1="(select *from TICKET_INFORMATION where 列车号='"+lc+"')";
					ResultSet r=stmt.executeQuery(s1);   //游标 查询表内内容
					if(r.next()) {
						 String  s="delete from TICKET_INFORMATION where 列车号='"+lc+"'";
					           stmt.executeUpdate(s);   
					    	   JOptionPane.showMessageDialog(null, "列车号为"+lc+"的信息删除成功","提示消息",JOptionPane.PLAIN_MESSAGE);
						    	f1.setText("");
					}
					else {
						  JOptionPane.showMessageDialog(null, "暂无该列车的相关信息，请重新输入","提示消息",JOptionPane.ERROR_MESSAGE);
					}	 
			}
				catch (Exception e1)
				{
					e1.printStackTrace();
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


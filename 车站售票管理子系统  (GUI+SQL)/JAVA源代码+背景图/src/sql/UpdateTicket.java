package sql;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class UpdateTicket implements ActionListener{
	String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String dbURL = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Ticket_System";
	String userName = "sa";
	String userPwd = "sa";
	Statement stmt;
	Statement stmt1;
	Connection dbConn = null;
	public void actionPerformed(ActionEvent e) {
		JFrame jf= new JFrame("修改列车信息");
		JLabel lc = new JLabel("        列车号        ");
		JComboBox<String>comboBox = new JComboBox<>();
		comboBox.addItem("列车长");
		comboBox.addItem("起始站");
		comboBox.addItem("出发时间");
		comboBox.addItem("终点站");
		comboBox.addItem("到站时间");
		comboBox.addItem("日期");
		comboBox.addItem("联系电话");
		JButton fh = new JButton("返回");
		JButton sb = new JButton("确认修改");
		JTextField f1 = new JTextField(10);
		JTextField f2 = new JTextField(10);
		jf.add(lc);
		jf.add(f1);
		jf.add(comboBox);
		jf.add(f2);
		jf.add(sb);
		jf.add(fh);
		jf.setResizable(false);       //不允许修改窗口
		jf.setLayout(new FlowLayout(FlowLayout.CENTER,35,10));
		jf.setSize(300,200);
		jf.setLocationRelativeTo(null);//窗口位置居中
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //关闭窗口，释放内存
		ActionListener listener = new ActionListener()    //确认修改监听
	{
			public void actionPerformed(ActionEvent e) {
				String lc = f1.getText();
				String xg = f2.getText();
				int PD= comboBox.getSelectedIndex();
				try
				{  
					Class.forName(driverName);
					dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
					stmt=dbConn.createStatement();
					 String  s="(Select * from TICKET_INFORMATION where 列车号 = '"+lc+"')";
				       ResultSet r=stmt.executeQuery(s);   //游标 查询表内内容
				       if(r.next()) {
				    	   if(PD==0) {
								String  s1="update TICKET_INFORMATION SET 列车长 = '"+xg+"' WHERE 列车号 = '"+lc+"'";
								stmt.executeUpdate(s1);
								f1.setText("");f2.setText("");
								JOptionPane.showMessageDialog(null, "列车号为"+lc+"的列车长信息修改成功！！！","提示",JOptionPane.PLAIN_MESSAGE);
						    	  }
							else if(PD==1){
								String  s1="update TICKET_INFORMATION SET 起始站 = '"+xg+"' WHERE 列车号 = '"+lc+"'";
								stmt.executeUpdate(s1);
								f1.setText("");f2.setText("");
								JOptionPane.showMessageDialog(null, "列车号为"+lc+"的起始站信息修改成功！！！","提示",JOptionPane.PLAIN_MESSAGE);
				     			}
							else if(PD==2){
									String  s1="update TICKET_INFORMATION SET 出发时间 = '"+xg+"' WHERE 列车号 = '"+lc+"'";
									stmt.executeUpdate(s1);
									f1.setText("");f2.setText("");
									JOptionPane.showMessageDialog(null, "列车号为"+lc+"的出发时间信息修改成功！！！","提示",JOptionPane.PLAIN_MESSAGE);
					     			}
							else if(PD==3){
								String  s1="update TICKET_INFORMATION SET 终点站 = '"+xg+"' WHERE 列车号 = '"+lc+"'";
								stmt.executeUpdate(s1);
								f1.setText("");f2.setText("");
								JOptionPane.showMessageDialog(null, "列车号为"+lc+"的终点站信息修改成功！！！","提示",JOptionPane.PLAIN_MESSAGE);
				     			}
							else if(PD==4){
								String  s1="update TICKET_INFORMATION SET 到站时间 = '"+xg+"' WHERE 列车号 = '"+lc+"'";
								stmt.executeUpdate(s1);
								f1.setText("");f2.setText("");
								JOptionPane.showMessageDialog(null, "列车号为"+lc+"的到站时间信息修改成功！！！","提示",JOptionPane.PLAIN_MESSAGE);
				     			}
							else if(PD==5){
								String  s1="update TICKET_INFORMATION SET 日期 = '"+xg+"' WHERE 列车号 = '"+lc+"'";
								stmt.executeUpdate(s1);
								f1.setText("");f2.setText("");
								JOptionPane.showMessageDialog(null, "列车号为"+lc+"的日期信息修改成功！！！","提示",JOptionPane.PLAIN_MESSAGE);
				     			}
							else if(PD==6){
								String  s1="update TICKET_INFORMATION SET 联系电话= '"+xg+"' WHERE 列车号 = '"+lc+"'";
								stmt.executeUpdate(s1);
								f1.setText("");f2.setText("");
								JOptionPane.showMessageDialog(null, "列车号为"+lc+"的联系电话信息修改成功！！！","提示",JOptionPane.PLAIN_MESSAGE);
				     			}
				       }
				       else {
				    	   JOptionPane.showMessageDialog(null, "请输入正确的列车号！！！","提示",JOptionPane.ERROR_MESSAGE);
				    	   f1.setText("");f2.setText("");
				       }
					
				 }
				catch (Exception e1)
				{
					JOptionPane.showMessageDialog(null, "请输入正确的信息格式！！！","提示",JOptionPane.ERROR_MESSAGE);
				}
			}
	};
	sb.addActionListener(listener);
	ActionListener listener1 = new ActionListener()    //返回监听
			{
				public void actionPerformed(ActionEvent e) {
					jf.setVisible(false);
				}
			};
			fh.addActionListener(listener1);
	}
}

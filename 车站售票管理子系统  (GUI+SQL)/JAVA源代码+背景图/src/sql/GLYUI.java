package sql;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class GLYUI {
	String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String dbURL = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Ticket_System";
	String userName = "sa";
	String userPwd = "sa";
	Statement stmt;
	Connection dbConn = null;
	public void glyUI() {
		JFrame f = new JFrame();
		JButton j1 = new JButton("增加信息");
		JButton j2 = new JButton("删除信息");
		JButton j3 = new JButton("修改信息");
		JButton j4 = new JButton("查询信息");
		JButton fh = new JButton("返回登录");
		f.add(j1);
		f.add(j2);
		f.add(j3);
		f.add(j4);
		f.add(fh);
		f.setResizable(false);       //不允许修改窗口
		f.setLayout(new FlowLayout(FlowLayout.CENTER,20,10));
		f.setSize(250,190);
		f.setLocationRelativeTo(null);//窗口位置居中
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //关闭窗口，释放内存
		j1.addActionListener(new AddTicket());              //增加信息监听
		j2.addActionListener(new DeleteTICKET());         //删除信息监听
		j3.addActionListener(new UpdateTicket());           //修改信息监听
		ActionListener listener = new ActionListener()      //车次查询监听
				{
					public void actionPerformed(ActionEvent event)
					{   
						try
					 {       
							Class.forName(driverName);
							 dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
							 stmt=dbConn.createStatement();
							 String  s="Select * from view_TICKET_INFORMATION_TICKET_LEVEL_GLY order by 列车号";
						   	 ResultSet rs=stmt.executeQuery(s);   //游标 查询表内内容
						   	Vector columnNames=new Vector();//设置列名
						   	columnNames.add("列车号");
							columnNames.add("列车长");
							columnNames.add("日期");
							columnNames.add("终点站");
							columnNames.add("起始站");
							columnNames.add("发车时间");
							columnNames.add("到站时间");
							columnNames.add("商务座票数");
							columnNames.add("一等座票数");
							columnNames.add("二等座票数");
							columnNames.add("无座票数");
							columnNames.add("商务座票价");
							columnNames.add("一等座票价");
							columnNames.add("二等座票价");
							columnNames.add("无座票价");
							columnNames.add("联系电话");
							Vector rowData = new Vector();	
						   	while(rs.next()) {
								//rowData可以存放多行
								Vector hang=new Vector();
								hang.add(rs.getString(1));
								hang.add(rs.getString(2));
								hang.add(rs.getString(3));
								hang.add(rs.getString(4));
								hang.add(rs.getString(5));
								hang.add(rs.getString(6));
								hang.add(rs.getString(7));
								hang.add(rs.getString(8));
								hang.add(rs.getString(9));
								hang.add(rs.getString(10));
								hang.add(rs.getString(11));
								hang.add(rs.getString(12));
								hang.add(rs.getString(13));
								hang.add(rs.getString(14));
								hang.add(rs.getString(15));
								hang.add(rs.getString(16));
								rowData.add(hang);
						   	}
						   	JTable jt = new JTable(rowData,columnNames);
							DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// 设置table内容居中
							tcr.setHorizontalAlignment(SwingConstants.CENTER);// 这句和上句作用一样
							jt.setDefaultRenderer(Object.class, tcr);
							jt.setEnabled(false);//不允许修改表格内容
							jt.setFont(new Font("楷体", Font.PLAIN, 18));// 设置表格字体
							JScrollPane jsp = new JScrollPane(jt);
							JPanel jp1 =new JPanel();
							jp1.add(jsp);
							JFrame jf = new JFrame();
							jf.add(jsp);
							jf.setSize(985,900);
							jf.setLocationRelativeTo(null);//窗口位置居中
							jf.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
				 }
					};
				j4.addActionListener(listener);
		ActionListener listener1 = new ActionListener()    //返回登录监听
				{
					public void actionPerformed(ActionEvent e) {
						f.setVisible(false);
						new LoginUI().loginUI();
					}
				};
				fh.addActionListener(listener1);
	}
}


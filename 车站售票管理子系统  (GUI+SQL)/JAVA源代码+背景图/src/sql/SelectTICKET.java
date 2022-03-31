package sql;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class SelectTICKET implements ActionListener{
	String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String dbURL = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Ticket_System";
	String userName = "sa";
	String userPwd = "sa";
	Statement stmt;
	Statement stmt1;
	Connection dbConn = null;
	@Override
	public void actionPerformed(ActionEvent e) {
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
				jf.setSize(1500,900);
				jf.setLocationRelativeTo(null);//窗口位置居中
				jf.setVisible(true);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		
	}

}

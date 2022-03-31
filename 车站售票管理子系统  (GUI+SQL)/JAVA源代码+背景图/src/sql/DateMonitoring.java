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
public class DateMonitoring  implements ActionListener {
	String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String dbURL = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Ticket_System";
	String userName = "sa";
	String userPwd = "sa";
	Statement stmt;
	String c;
	String c1;
	Connection dbConn = null;
	JComboBox<String>comboBox = new JComboBox<>();
	public static java.sql.Date StringToDate(String sDate) {  
	    String str = sDate;  
	    SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");  
	    java.util.Date d = null;  
	    try {  
	        d = format.parse(str);  
	    } catch (Exception e) {  
	    	JOptionPane.showMessageDialog(null, "日期格式输入错误！！！","提示",JOptionPane.ERROR_MESSAGE);
	    }  
	    java.sql.Date date = new java.sql.Date(d.getTime());  
	    return date;  
	} 
	public void actionPerformed(ActionEvent e) {
		
		JFrame f = new JFrame("列车查询");
		JTextField f1 = new JTextField(15);
		JButton chax = new JButton("查询");chax.setFont(new Font("黑体",Font.PLAIN,20));//设置字体的字体，大小
		JButton back = new JButton("返回");back.setFont(new Font("黑体",Font.PLAIN,20));//设置字体的字体，大小
		JLabel rq =new JLabel("出发日期");rq.setFont(new Font("黑体",Font.PLAIN,20));//设置字体的字体，大小
		f.setResizable(false);       //不允许修改窗口
		f.setSize(200,200);
		f.add(rq);
		f.add(f1);
		f.add(chax);
		f.add(back);
		f.setLayout(new FlowLayout(FlowLayout.CENTER,20,10));///流式布局
		f.setLocationRelativeTo(null);//窗口位置居中
		f.setVisible(true);
		
		ActionListener listener = new ActionListener()    //查询列车监听
	{
		public void actionPerformed(ActionEvent e) {
			try
			 {     String cfrq= f1.getText();
				java.sql.Date dt=StringToDate(cfrq);
					Class.forName(driverName);
					 dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
					 stmt=dbConn.createStatement();
					 String  s="select * from view_TICKET_INFORMATION_TICKET_LEVEL where 日期='"+dt+"'and 起始站 = '"+c+"'and 终点站 = '"+c1+"'order by 列车号";
				   	 ResultSet rs=stmt.executeQuery(s);   //游标 查询表内内容
				   	 if(!rs.next()) {
				   		 		JOptionPane.showMessageDialog(null, "很抱歉！未找到您所查询的车站间的列车信息","提示",JOptionPane.INFORMATION_MESSAGE);
				   		 	f1.setText("");
				   	 }
				   	 else {
				   	 	Vector columnNames=new Vector();//设置列名
						columnNames.add("列车号");
						columnNames.add("日期");
						columnNames.add("发车时间");
						columnNames.add("到站时间");
						columnNames.add("商务座票数");
						columnNames.add("一等座票数");
						columnNames.add("二等座票数");
						columnNames.add("无座票数");
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
						jf.setSize(985,900);
						jf.setLocationRelativeTo(null);//窗口位置居中
						jf.setVisible(true);
				   	 }
				} catch (Exception e1) {
					f1.setText("");f.setVisible(true);
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
	public DateMonitoring(String city,String city1){
	   	 c = city;c1 = city1;
	    }
}
	
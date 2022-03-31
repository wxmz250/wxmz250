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
	    	JOptionPane.showMessageDialog(null, "���ڸ�ʽ������󣡣���","��ʾ",JOptionPane.ERROR_MESSAGE);
	    }  
	    java.sql.Date date = new java.sql.Date(d.getTime());  
	    return date;  
	} 
	public void actionPerformed(ActionEvent e) {
		
		JFrame f = new JFrame("�г���ѯ");
		JTextField f1 = new JTextField(15);
		JButton chax = new JButton("��ѯ");chax.setFont(new Font("����",Font.PLAIN,20));//������������壬��С
		JButton back = new JButton("����");back.setFont(new Font("����",Font.PLAIN,20));//������������壬��С
		JLabel rq =new JLabel("��������");rq.setFont(new Font("����",Font.PLAIN,20));//������������壬��С
		f.setResizable(false);       //�������޸Ĵ���
		f.setSize(200,200);
		f.add(rq);
		f.add(f1);
		f.add(chax);
		f.add(back);
		f.setLayout(new FlowLayout(FlowLayout.CENTER,20,10));///��ʽ����
		f.setLocationRelativeTo(null);//����λ�þ���
		f.setVisible(true);
		
		ActionListener listener = new ActionListener()    //��ѯ�г�����
	{
		public void actionPerformed(ActionEvent e) {
			try
			 {     String cfrq= f1.getText();
				java.sql.Date dt=StringToDate(cfrq);
					Class.forName(driverName);
					 dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
					 stmt=dbConn.createStatement();
					 String  s="select * from view_TICKET_INFORMATION_TICKET_LEVEL where ����='"+dt+"'and ��ʼվ = '"+c+"'and �յ�վ = '"+c1+"'order by �г���";
				   	 ResultSet rs=stmt.executeQuery(s);   //�α� ��ѯ��������
				   	 if(!rs.next()) {
				   		 		JOptionPane.showMessageDialog(null, "�ܱ�Ǹ��δ�ҵ�������ѯ�ĳ�վ����г���Ϣ","��ʾ",JOptionPane.INFORMATION_MESSAGE);
				   		 	f1.setText("");
				   	 }
				   	 else {
				   	 	Vector columnNames=new Vector();//��������
						columnNames.add("�г���");
						columnNames.add("����");
						columnNames.add("����ʱ��");
						columnNames.add("��վʱ��");
						columnNames.add("������Ʊ��");
						columnNames.add("һ����Ʊ��");
						columnNames.add("������Ʊ��");
						columnNames.add("����Ʊ��");
						Vector rowData = new Vector();	
					   	while(rs.next()) {
							//rowData���Դ�Ŷ���
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
						DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// ����table���ݾ���
						tcr.setHorizontalAlignment(SwingConstants.CENTER);// �����Ͼ�����һ��
						jt.setDefaultRenderer(Object.class, tcr);
						jt.setEnabled(false);//�������޸ı������
						jt.setFont(new Font("����", Font.PLAIN, 18));// ���ñ������
						JScrollPane jsp = new JScrollPane(jt);
						JPanel jp1 =new JPanel();
						jp1.add(jsp);
						JFrame jf = new JFrame();
						jf.add(jsp);
						jf.setSize(985,900);
						jf.setLocationRelativeTo(null);//����λ�þ���
						jf.setVisible(true);
				   	 }
				} catch (Exception e1) {
					f1.setText("");f.setVisible(true);
				}
		}
	};
	chax.addActionListener(listener);
	ActionListener listener1 = new ActionListener()    //���ؼ���
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
	
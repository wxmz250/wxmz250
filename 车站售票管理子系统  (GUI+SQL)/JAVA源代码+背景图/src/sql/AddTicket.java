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
	    	JOptionPane.showMessageDialog(null, "��������ȷ�����ڸ�ʽ������","��ʾ",JOptionPane.ERROR_MESSAGE);
	    }  
	    java.sql.Date date = new java.sql.Date(d.getTime());  
	    return date;  
	} 
	public void actionPerformed(ActionEvent e) {
		JFrame jf= new JFrame("�����г���Ϣ");
		JLabel lch = new JLabel(" �г���  ");
		JLabel lcz = new JLabel(" �г���  ");
		JLabel starting = new JLabel(" ��ʼվ  ");
		JLabel time = new JLabel("����ʱ��");
		JLabel ending = new JLabel(" �յ�վ  ");
		JLabel time2 = new JLabel("��վʱ��");
		JLabel date = new JLabel("   ����    ");
		JLabel phone = new JLabel("��ϵ�绰");
		JButton fh = new JButton("   ����    ");
		JButton zj = new JButton("ȷ������");
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
		jf.setResizable(false);       //�������޸Ĵ���
		jf.setLayout(new FlowLayout(FlowLayout.CENTER,35,10));
		jf.setSize(300,350);
		jf.setLocationRelativeTo(null);//����λ�þ���
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //�رմ��ڣ��ͷ��ڴ�
		ActionListener listener = new ActionListener()    //ȷ�����Ӽ���
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
				    	JOptionPane.showMessageDialog(null, "��ӳɹ�","��ʾ��Ϣ",JOptionPane.PLAIN_MESSAGE);
				    	f1.setText("");f2.setText("");f3.setText("");f4.setText("");
				    	f5.setText("");f6.setText("");f7.setText("");f8.setText("");
			}
				catch (Exception e1)
				{
					JOptionPane.showMessageDialog(null, "��������ȷ��ʱ����Ϣ��ʽ������","��ʾ",JOptionPane.ERROR_MESSAGE);
				}
			}
	};
     zj.addActionListener(listener);
	ActionListener listener1 = new ActionListener()    //���ؼ���
			{
				public void actionPerformed(ActionEvent e) {
					jf.setVisible(false);
				}
			};
			fh.addActionListener(listener1);
	}

}

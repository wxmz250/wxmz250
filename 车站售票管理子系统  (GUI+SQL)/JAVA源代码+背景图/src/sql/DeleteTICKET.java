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
		JFrame jf= new JFrame("ɾ���г���Ϣ");
		JLabel lch = new JLabel("�г��� ");
		JButton fh = new JButton("����    ");
		JButton zj = new JButton("ȷ��ɾ��");
		JTextField f1 = new JTextField(10);
		jf.add(lch);
		jf.add(f1);
		jf.add(zj);
		jf.add(fh);
		jf.setResizable(false);       //�������޸Ĵ���
		jf.setLayout(new FlowLayout(FlowLayout.CENTER,35,10));
		jf.setSize(300,250);
		jf.setLocationRelativeTo(null);//����λ�þ���
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //�رմ��ڣ��ͷ��ڴ�
		ActionListener listener = new ActionListener()    //ȷ��ɾ������
	{
			public void actionPerformed(ActionEvent e) {
				String lc = f1.getText();
				try
				{  
					Class.forName(driverName);
					dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
					stmt=dbConn.createStatement();
					String  s1="(select *from TICKET_INFORMATION where �г���='"+lc+"')";
					ResultSet r=stmt.executeQuery(s1);   //�α� ��ѯ��������
					if(r.next()) {
						 String  s="delete from TICKET_INFORMATION where �г���='"+lc+"'";
					           stmt.executeUpdate(s);   
					    	   JOptionPane.showMessageDialog(null, "�г���Ϊ"+lc+"����Ϣɾ���ɹ�","��ʾ��Ϣ",JOptionPane.PLAIN_MESSAGE);
						    	f1.setText("");
					}
					else {
						  JOptionPane.showMessageDialog(null, "���޸��г��������Ϣ������������","��ʾ��Ϣ",JOptionPane.ERROR_MESSAGE);
					}	 
			}
				catch (Exception e1)
				{
					e1.printStackTrace();
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


package sql;

import java.awt.FlowLayout;
import java.awt.Font;
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

public class FindPassword implements ActionListener{
	String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String dbURL = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Ticket_System";
	String userName = "sa";
	String userPwd = "sa";
	Statement stmt;
	Connection dbConn = null;
	public void actionPerformed(ActionEvent e) {
		JFrame f = new JFrame();
		JLabel lc = new JLabel("  �û���  ");
		JLabel mb = new JLabel("    �ܱ�    ");
		JLabel mbdaan = new JLabel("�ܱ���");
		JButton chax = new JButton("  ȷ��  ");chax.setFont(new Font("����",Font.PLAIN,20));//������������壬��С
		JButton back = new JButton("���ص�¼");back.setFont(new Font("����",Font.PLAIN,20));//������������壬��С
		JTextField f1 = new JTextField(8);
		JTextField f2 = new JTextField(8);
		JTextField f3 = new JTextField(8);
		f.add(lc);
		f.add(f1);
		f.add(mb);
		f.add(f2);
		f.add(mbdaan);
		f.add(f3);
		f.add(chax);
		f.add(back);
		f.setResizable(false);       //�������޸Ĵ���
		f.setSize(200,220);
		f.setLayout(new FlowLayout(FlowLayout.CENTER,20,10));///��ʽ����
		f.setLocationRelativeTo(null);//����λ�þ���
		f.setVisible(true);
		ActionListener listener = new ActionListener()    //ȷ����Ʊ����
				{
					public void actionPerformed(ActionEvent e) {
						String yhm = f1.getText();
						String mb = f2.getText();
						String mbdaan = f3.getText();
						try
						 {      Class.forName(driverName);
						 		dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
						 		stmt=dbConn.createStatement();
						       String  s="(Select �û����� from USER_LIBRARY where �û���='"+yhm+"' AND �ܱ�='"+mb+"'and �ܱ��� = '"+mbdaan+"')";
						       ResultSet r=stmt.executeQuery(s);   //�α� ��ѯ��������
						       if(r.next()) {
						    	   f1.setText("");f2.setText("");f3.setText("");
						    	   JOptionPane.showMessageDialog(null, "��������Ϊ��"+r.getString(1),"��ܰ��ʾ",JOptionPane.PLAIN_MESSAGE);//����
						       }else {
						    	   f1.setText("");f2.setText("");f3.setText("");
						    	   JOptionPane.showMessageDialog(null, "������󣡣���","��ʾ",JOptionPane.ERROR_MESSAGE); 
						       }
						 }catch (Exception e1) {
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

}

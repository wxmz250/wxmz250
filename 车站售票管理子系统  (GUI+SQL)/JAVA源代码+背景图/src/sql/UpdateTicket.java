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
		JFrame jf= new JFrame("�޸��г���Ϣ");
		JLabel lc = new JLabel("        �г���        ");
		JComboBox<String>comboBox = new JComboBox<>();
		comboBox.addItem("�г���");
		comboBox.addItem("��ʼվ");
		comboBox.addItem("����ʱ��");
		comboBox.addItem("�յ�վ");
		comboBox.addItem("��վʱ��");
		comboBox.addItem("����");
		comboBox.addItem("��ϵ�绰");
		JButton fh = new JButton("����");
		JButton sb = new JButton("ȷ���޸�");
		JTextField f1 = new JTextField(10);
		JTextField f2 = new JTextField(10);
		jf.add(lc);
		jf.add(f1);
		jf.add(comboBox);
		jf.add(f2);
		jf.add(sb);
		jf.add(fh);
		jf.setResizable(false);       //�������޸Ĵ���
		jf.setLayout(new FlowLayout(FlowLayout.CENTER,35,10));
		jf.setSize(300,200);
		jf.setLocationRelativeTo(null);//����λ�þ���
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //�رմ��ڣ��ͷ��ڴ�
		ActionListener listener = new ActionListener()    //ȷ���޸ļ���
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
					 String  s="(Select * from TICKET_INFORMATION where �г��� = '"+lc+"')";
				       ResultSet r=stmt.executeQuery(s);   //�α� ��ѯ��������
				       if(r.next()) {
				    	   if(PD==0) {
								String  s1="update TICKET_INFORMATION SET �г��� = '"+xg+"' WHERE �г��� = '"+lc+"'";
								stmt.executeUpdate(s1);
								f1.setText("");f2.setText("");
								JOptionPane.showMessageDialog(null, "�г���Ϊ"+lc+"���г�����Ϣ�޸ĳɹ�������","��ʾ",JOptionPane.PLAIN_MESSAGE);
						    	  }
							else if(PD==1){
								String  s1="update TICKET_INFORMATION SET ��ʼվ = '"+xg+"' WHERE �г��� = '"+lc+"'";
								stmt.executeUpdate(s1);
								f1.setText("");f2.setText("");
								JOptionPane.showMessageDialog(null, "�г���Ϊ"+lc+"����ʼվ��Ϣ�޸ĳɹ�������","��ʾ",JOptionPane.PLAIN_MESSAGE);
				     			}
							else if(PD==2){
									String  s1="update TICKET_INFORMATION SET ����ʱ�� = '"+xg+"' WHERE �г��� = '"+lc+"'";
									stmt.executeUpdate(s1);
									f1.setText("");f2.setText("");
									JOptionPane.showMessageDialog(null, "�г���Ϊ"+lc+"�ĳ���ʱ����Ϣ�޸ĳɹ�������","��ʾ",JOptionPane.PLAIN_MESSAGE);
					     			}
							else if(PD==3){
								String  s1="update TICKET_INFORMATION SET �յ�վ = '"+xg+"' WHERE �г��� = '"+lc+"'";
								stmt.executeUpdate(s1);
								f1.setText("");f2.setText("");
								JOptionPane.showMessageDialog(null, "�г���Ϊ"+lc+"���յ�վ��Ϣ�޸ĳɹ�������","��ʾ",JOptionPane.PLAIN_MESSAGE);
				     			}
							else if(PD==4){
								String  s1="update TICKET_INFORMATION SET ��վʱ�� = '"+xg+"' WHERE �г��� = '"+lc+"'";
								stmt.executeUpdate(s1);
								f1.setText("");f2.setText("");
								JOptionPane.showMessageDialog(null, "�г���Ϊ"+lc+"�ĵ�վʱ����Ϣ�޸ĳɹ�������","��ʾ",JOptionPane.PLAIN_MESSAGE);
				     			}
							else if(PD==5){
								String  s1="update TICKET_INFORMATION SET ���� = '"+xg+"' WHERE �г��� = '"+lc+"'";
								stmt.executeUpdate(s1);
								f1.setText("");f2.setText("");
								JOptionPane.showMessageDialog(null, "�г���Ϊ"+lc+"��������Ϣ�޸ĳɹ�������","��ʾ",JOptionPane.PLAIN_MESSAGE);
				     			}
							else if(PD==6){
								String  s1="update TICKET_INFORMATION SET ��ϵ�绰= '"+xg+"' WHERE �г��� = '"+lc+"'";
								stmt.executeUpdate(s1);
								f1.setText("");f2.setText("");
								JOptionPane.showMessageDialog(null, "�г���Ϊ"+lc+"����ϵ�绰��Ϣ�޸ĳɹ�������","��ʾ",JOptionPane.PLAIN_MESSAGE);
				     			}
				       }
				       else {
				    	   JOptionPane.showMessageDialog(null, "��������ȷ���г��ţ�����","��ʾ",JOptionPane.ERROR_MESSAGE);
				    	   f1.setText("");f2.setText("");
				       }
					
				 }
				catch (Exception e1)
				{
					JOptionPane.showMessageDialog(null, "��������ȷ����Ϣ��ʽ������","��ʾ",JOptionPane.ERROR_MESSAGE);
				}
			}
	};
	sb.addActionListener(listener);
	ActionListener listener1 = new ActionListener()    //���ؼ���
			{
				public void actionPerformed(ActionEvent e) {
					jf.setVisible(false);
				}
			};
			fh.addActionListener(listener1);
	}
}

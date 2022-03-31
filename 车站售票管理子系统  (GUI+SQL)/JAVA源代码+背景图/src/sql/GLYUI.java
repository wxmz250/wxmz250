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
		JButton j1 = new JButton("������Ϣ");
		JButton j2 = new JButton("ɾ����Ϣ");
		JButton j3 = new JButton("�޸���Ϣ");
		JButton j4 = new JButton("��ѯ��Ϣ");
		JButton fh = new JButton("���ص�¼");
		f.add(j1);
		f.add(j2);
		f.add(j3);
		f.add(j4);
		f.add(fh);
		f.setResizable(false);       //�������޸Ĵ���
		f.setLayout(new FlowLayout(FlowLayout.CENTER,20,10));
		f.setSize(250,190);
		f.setLocationRelativeTo(null);//����λ�þ���
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //�رմ��ڣ��ͷ��ڴ�
		j1.addActionListener(new AddTicket());              //������Ϣ����
		j2.addActionListener(new DeleteTICKET());         //ɾ����Ϣ����
		j3.addActionListener(new UpdateTicket());           //�޸���Ϣ����
		ActionListener listener = new ActionListener()      //���β�ѯ����
				{
					public void actionPerformed(ActionEvent event)
					{   
						try
					 {       
							Class.forName(driverName);
							 dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
							 stmt=dbConn.createStatement();
							 String  s="Select * from view_TICKET_INFORMATION_TICKET_LEVEL_GLY order by �г���";
						   	 ResultSet rs=stmt.executeQuery(s);   //�α� ��ѯ��������
						   	Vector columnNames=new Vector();//��������
						   	columnNames.add("�г���");
							columnNames.add("�г���");
							columnNames.add("����");
							columnNames.add("�յ�վ");
							columnNames.add("��ʼվ");
							columnNames.add("����ʱ��");
							columnNames.add("��վʱ��");
							columnNames.add("������Ʊ��");
							columnNames.add("һ����Ʊ��");
							columnNames.add("������Ʊ��");
							columnNames.add("����Ʊ��");
							columnNames.add("������Ʊ��");
							columnNames.add("һ����Ʊ��");
							columnNames.add("������Ʊ��");
							columnNames.add("����Ʊ��");
							columnNames.add("��ϵ�绰");
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
						} catch (Exception e) {
							e.printStackTrace();
						}
				 }
					};
				j4.addActionListener(listener);
		ActionListener listener1 = new ActionListener()    //���ص�¼����
				{
					public void actionPerformed(ActionEvent e) {
						f.setVisible(false);
						new LoginUI().loginUI();
					}
				};
				fh.addActionListener(listener1);
	}
}


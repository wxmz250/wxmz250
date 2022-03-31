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
				jf.setSize(1500,900);
				jf.setLocationRelativeTo(null);//����λ�þ���
				jf.setVisible(true);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		
	}

}

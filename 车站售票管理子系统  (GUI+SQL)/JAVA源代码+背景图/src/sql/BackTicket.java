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
public class BackTicket implements ActionListener {
	String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String dbURL = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Ticket_System";
	String userName = "sa";
	String userPwd = "sa";
	Statement stmt;
	Statement stmt1;
	Connection dbConn = null;
	String c;
	String c1;
	public void actionPerformed(ActionEvent e) {
		JFrame f = new JFrame("��Ʊ");
		JTextField f1 = new JTextField(8);
		JTextField f2 = new JTextField(5);
		JLabel lc = new JLabel("�г�");
		JComboBox<String>comboBox = new JComboBox<>();
		comboBox.addItem("��������Ʊ");
		comboBox.addItem("һ������Ʊ");
		comboBox.addItem("��������Ʊ");
		comboBox.addItem("������Ʊ");
		JLabel gps =new JLabel("��Ʊ��");
		f.add(lc);
		f.add(f1);
		f.add(comboBox);
		JButton chax = new JButton("ȷ����Ʊ");chax.setFont(new Font("����",Font.PLAIN,20));//������������壬��С
		JButton back = new JButton("����");back.setFont(new Font("����",Font.PLAIN,20));//������������壬��С
		f.setResizable(false);       //�������޸Ĵ���
		f.setSize(189,250);
		f.add(gps);
		f.add(f2);
		f.add(chax);
		f.add(back);
		f.setLayout(new FlowLayout(FlowLayout.CENTER,28,10));///��ʽ����
		f.setLocationRelativeTo(null);//����λ�þ���
		f.setVisible(true);
		ActionListener listener = new ActionListener()    //ȷ����Ʊ����
	{
		public void actionPerformed(ActionEvent e) {
			 String lch = f1.getText();
			 String shu = f2.getText();
			try
			 {     Class.forName(driverName);
			       dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
			       stmt=dbConn.createStatement();
			       stmt1=dbConn.createStatement();
			       String  s="(Select * from TICKER_BUYER where ��ʼվ='"+c+"' AND �յ�վ='"+c1+"'and �г��� = '"+lch+"')";
			       ResultSet r=stmt.executeQuery(s);   //�α� ��ѯ��������
			       if(r.next()) {
			    	   if(comboBox.getSelectedIndex()==0) {
			    		   String q ="UPDATE TICKET_LEVEL SET ����Ʊ�� =����Ʊ��+'"+shu+"'  WHERE �г��� = '"+lch+"'";
			    		   String  tp="UPDATE TICKER_BUYER  SET ����Ʊ�� = ����Ʊ��-'"+shu+"' WHERE �г��� = '"+lch+"'and �û��� ='"+new LoginUI().a+"'";
			    		   stmt.executeUpdate(tp);int r1=stmt1.executeUpdate(q);
							f1.setText("");JOptionPane.showMessageDialog(null, "��Ʊ�ɹ�������","��ʾ",JOptionPane.PLAIN_MESSAGE);
			    		   }
			    	   else if(comboBox.getSelectedIndex()==1) {
			    		   f1.setText(""); JOptionPane.showMessageDialog(null, "��Ʊ�ɹ�������","��ʾ",JOptionPane.PLAIN_MESSAGE);
			    		   String q ="UPDATE TICKET_LEVEL SET һ��Ʊ�� =һ��Ʊ��+'"+shu+"'  WHERE �г��� = '"+lch+"'";
			    		   String  tp="UPDATE TICKER_BUYER  SET һ��Ʊ�� = һ��Ʊ��-'"+shu+"' WHERE �г��� = '"+lch+"'and �û��� ='"+new LoginUI().a+"'";
					   		stmt.executeUpdate(tp);stmt1.executeUpdate(q);
			    	   }else if(comboBox.getSelectedIndex()==2) {
			    		   f1.setText(""); JOptionPane.showMessageDialog(null, "��Ʊ�ɹ�������","��ʾ",JOptionPane.PLAIN_MESSAGE);
			    		   String q ="UPDATE TICKET_LEVEL SET ����Ʊ�� =����Ʊ��+'"+shu+"'  WHERE �г��� = '"+lch+"'";
			    		   String  tp="UPDATE TICKER_BUYER  SET ����Ʊ�� = ����Ʊ��-'"+shu+"' WHERE �г��� = '"+lch+"'and �û��� ='"+new LoginUI().a+"'";
					   		stmt.executeUpdate(tp);stmt1.executeUpdate(q);
			    	   }else {
			    		   f1.setText(""); JOptionPane.showMessageDialog(null, "��Ʊ�ɹ�������","��ʾ",JOptionPane.PLAIN_MESSAGE);
			    		   String q ="UPDATE TICKET_LEVEL SET �޵�Ʊ�� =�޵�Ʊ��+'"+shu+"'  WHERE �г��� = '"+lch+"'";
			    		   String  tp="UPDATE TICKER_BUYER  SET ����Ʊ�� = ����Ʊ��-'"+shu+"' WHERE �г��� = '"+lch+"'and �û��� ='"+new LoginUI().a+"'";
					   		stmt.executeUpdate(tp);stmt1.executeUpdate(q);
			    	   }
			       }
			      else {
			    	   f1.setText(""); f2.setText("");
						JOptionPane.showMessageDialog(null, "��Ʊʧ�ܣ���˶��г���Ϣ������","��ʾ",JOptionPane.ERROR_MESSAGE);  
					 }
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "����δ����ó�Ʊ������","��ʾ",JOptionPane.ERROR_MESSAGE);  
					f.setVisible(true);
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
	public BackTicket(String city,String city1){
   	 c = city;c1 = city1;
    }
}
	
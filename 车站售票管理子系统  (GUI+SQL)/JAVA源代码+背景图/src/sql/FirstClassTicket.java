package sql;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class FirstClassTicket  implements ActionListener {
	String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String dbURL = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Ticket_System";
	String userName = "sa";
	String userPwd = "sa";
	Statement stmt;
	String c;
	String c1;
	Connection dbConn = null;
	JComboBox<String>comboBox = new JComboBox<>();
	public void actionPerformed(ActionEvent e) {
   		JFrame f = new JFrame("����Ʊ");
		JButton sureButton = new JButton("һ�ȹ�Ʊ");sureButton.setFont(new Font("����",Font.PLAIN,20));//������������壬��С
		JTextField f1 = new JTextField(5);
		JButton back = new JButton("����");back.setFont(new Font("����",Font.PLAIN,20));//������������壬��С
		JLabel lc =new JLabel("�г���");
		JLabel gps =new JLabel("��Ʊ��");
		f.setResizable(false);       //�������޸Ĵ���
		f.setLayout(null);
		f.setSize(200,240);
		f.add(lc);
		f.add(sureButton);
		f.add(gps);
		f.add(f1);
		f.add(back);
		lc.setBounds(20,5, 65, 40);
		gps.setBounds(10, 50, 65, 40);
		f1.setBounds(80,50,40,30);
		sureButton.setBounds(40,100, 120, 45);
		back.setBounds(65,150, 75, 40); 
		f.setLocationRelativeTo(null);//����λ�þ���
		f.setVisible(false);
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Ticket_System";
			String user="sa";//sa��������Ա
			String password="sa";//����
			Connection conn=DriverManager.getConnection(url,user,password);
			Statement stmt=conn.createStatement();
		 String  s="(SELECT �г��� FROM TICKET_INFORMATION WHERE ��ʼվ='"+c+"' AND �յ�վ='"+c1+"')";
	   	 ResultSet rs=stmt.executeQuery(s);   //�α� ��ѯ��������
	   	while(rs.next()){
	   		comboBox.addItem(rs.getString(1));
	   		}
		}catch (Exception e1)
		  {
			e1.printStackTrace();
			System.out.print("����ʧ��");
		  }
		comboBox.setBounds(90,10, 70, 30);
		f.add(comboBox);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //�رմ��ڣ��ͷ��ڴ�
		ActionListener listener1 = new ActionListener()    //��Ʊ����
				{
					public void actionPerformed(ActionEvent event)
					{   String shu = f1.getText();
				   		String piao = comboBox.getSelectedItem().toString();   //��ȡ�������е�ֵ
						try {
							Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
							String dbURL = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Ticket_System";
							String userName = "sa";
							String userPwd = "sa";
							Connection dbConn=DriverManager.getConnection(dbURL,userName,userPwd);
							Statement stmt=dbConn.createStatement();
							Statement stmt1=dbConn.createStatement();
				            String  s1="UPDATE TICKET_LEVEL SET һ��Ʊ�� =һ��Ʊ��-'"+shu+"'  WHERE �г��� = '"+piao+"'";
				            int r1=stmt.executeUpdate(s1);
					if(r1==1)
					{     
						String  s="(SELECT * FROM TICKER_BUYER WHERE �û���='"+LoginUI.a+"')";
						ResultSet rs=stmt.executeQuery(s);   //�α� ��ѯ��������
						if(!rs.next()) {
							String  s2= "insert into TICKER_BUYER(�û���,�г���,��ʼվ,�յ�վ,һ��Ʊ��) values('"+LoginUI.a+"','"+piao+"','"+c+"','"+c1+"','"+shu+"')";
							int r2=stmt1.executeUpdate(s2);
							if(r2==1) {
								System.out.println(r2);
								JOptionPane.showMessageDialog(null, "��Ʊ�ɹ�","��ʾ��Ϣ",JOptionPane.PLAIN_MESSAGE);
							}
						}else {
							String  s2="UPDATE TICKER_BUYER  SET һ��Ʊ�� = һ��Ʊ��+'"+shu+"' WHERE �г��� = '"+piao+"'and �û��� ='"+LoginUI.a+"'and ��ʼվ ='"+c+"'and �յ�վ ='"+c1+"'";
							stmt1.executeUpdate(s2);JOptionPane.showMessageDialog(null, "��Ʊ�ɹ�","��ʾ��Ϣ",JOptionPane.PLAIN_MESSAGE);
						}
					}
						else {
							JOptionPane.showMessageDialog(null, "��Ʊʧ��","��ʾ��Ϣ",JOptionPane.ERROR_MESSAGE);
						}
					}
						catch (Exception e)
						  {
							JOptionPane.showMessageDialog(null, "��Ʊʧ��,��Ʊ���㣡����","��ʾ��Ϣ",JOptionPane.ERROR_MESSAGE);
						  }
				   }
				};
				sureButton.addActionListener(listener1);
		ActionListener listener = new ActionListener()    //���ؼ���
				{
					public void actionPerformed(ActionEvent event)
					{
						f.setVisible(false);
					}
				};
				back.addActionListener(listener);
	 }
     public FirstClassTicket(String city,String city1){
    	 c=city;c1=city1;
     }
}
	
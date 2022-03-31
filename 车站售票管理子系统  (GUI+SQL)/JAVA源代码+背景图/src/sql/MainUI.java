package sql;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class MainUI {
	//�˵�ģ��
	String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String dbURL = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Ticket_System";
	String userName = "sa";
	String userPwd = "sa";
	Statement stmt;
	Connection dbConn = null;
		public void mainUI()
		{
			JFrame f = new JFrame("��ѯ��Ʊ");
			JButton sureButton = new JButton("��ѯ�г�");
			JButton fh = new JButton("���ص�¼");
			f.setResizable(false);       //�������޸Ĵ���
			f.setLayout(new FlowLayout(FlowLayout.CENTER,20,10));
			f.setSize(200,200);
			f.setLocationRelativeTo(null);//����λ�þ���
			f.setVisible(true);
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //�رմ��ڣ��ͷ��ڴ�
			JLabel starting = new JLabel("��ʼվ");
			JLabel end = new JLabel("�յ�վ");
			JComboBox<String>comboBox = new JComboBox<>();
			JComboBox<String>comboBox1 = new JComboBox<>();
			comboBox.addItem("����");comboBox1.addItem("����");
			comboBox.addItem("�Ϻ�");comboBox1.addItem("�Ϻ�");
			comboBox.addItem("����");comboBox1.addItem("����");
			comboBox.addItem("����");comboBox1.addItem("����");
			comboBox.addItem("�ɶ�");comboBox1.addItem("�ɶ�");
			comboBox.addItem("��ɳ");comboBox1.addItem("��ɳ");
			comboBox.addItem("���");comboBox1.addItem("���");
			comboBox.addItem("������");comboBox1.addItem("������");
			f.add(starting);
			f.add(end);
			f.add(comboBox);
			f.add(comboBox1);
			f.add(sureButton);
			f.add(fh);
			ActionListener listener = new ActionListener()    //���ص�¼�ļ���
					{
				
				public void actionPerformed(ActionEvent event)
				{ 
					f.setVisible(false);
					new LoginUI().loginUI();
				}
					};
				fh.addActionListener(listener);
			ActionListener listener1 = new ActionListener()    //��ѯ�г�����
					{
						public void actionPerformed(ActionEvent event)
						{   
							String city = comboBox.getSelectedItem().toString();   //��ȡ�������е�ֵ
							String city1 = comboBox1.getSelectedItem().toString();   //��ȡ�������е�ֵ
							f.setVisible(false);
							try
						 {
								 Class.forName(driverName);
								 dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
								 stmt=dbConn.createStatement();
								 String  s="(Select * from TICKET_INFORMATION where ��ʼվ='"+city+"' AND �յ�վ='"+city1+"')";
								 //��ʱ��ѯ��ʼվ�������յ�վ����
							   	 ResultSet r=stmt.executeQuery(s);   //�α� ��ѯ��������
								 if(r.next())
					     			{   //��ѯ����
									    JLayeredPane layeredPane;  //����һ��JLayeredPane���ڷֲ�ġ�  
								        JPanel jp;  
								        JLabel jl;   //����һ��Panel��һ��Label���ڴ��ͼƬ����Ϊ������  
								        JButton cc =new JButton("���β�ѯ");
								        JButton rq =new JButton("���ڲ�ѯ");
								        JButton sw =new JButton("��������Ʊ");
								        JButton yd =new JButton("һ������Ʊ");
								        JButton ed =new JButton("��������Ʊ");
								        JButton wz =new JButton("������Ʊ");
								        JButton fh =new JButton("����");
								        JButton tp =new JButton("��Ʊ");
								        ImageIcon image;  
								        tp.addActionListener(new BackTicket(city, city1));//��Ʊ�ļ���
								        rq.addActionListener(new DateMonitoring(city, city1)); //���ڲ�ѯ�ļ���
								        sw.addActionListener(new BusinessMonitoring(city, city1)); //����Ʊ�ļ���
								        yd.addActionListener(new FirstClassTicket(city, city1)); //һ�ȹ�Ʊ�ļ���
								        ed.addActionListener(new SecondClassTicket(city, city1)); //���ȹ�Ʊ�ļ���
								        wz.addActionListener(new NoSeatTicket(city, city1)); //������Ʊ�ļ���
								    	JFrame jf= new JFrame();
								        layeredPane=new JLayeredPane();  
								        image=new ImageIcon("C:\\Users\\DEATH\\Desktop\\1.png");     
								        jp=new JPanel();  
								        jp.setBounds(0,0,image.getIconWidth(),image.getIconHeight());  //����λ�úͿ��
								        jl=new JLabel(image);  
								        jp.add(jl);  
								        JPanel train=new JPanel();  //�洢�г���Ϣ����� 
								        JPanel panel_north=new JPanel(); 
								        JPanel panel_north1=new JPanel();
								        JLabel starting_city = new JLabel("������");starting_city.setFont(new Font("����",Font.PLAIN,20));//������������壬��С   	
								        JLabel destination = new JLabel("Ŀ�ĵ�");destination.setFont(new Font("����",Font.PLAIN,20));//������������壬��С   
								        JLabel c = new JLabel(city);c.setFont(new Font("�����п�",Font.PLAIN,25));//������������壬��С   	
								        JLabel c1 = new JLabel(city1);c1.setFont(new Font("�����п�",Font.PLAIN,25));//������������壬��С   	
								        panel_north.add(starting_city);
								        panel_north.add(c);
								        panel_north1.add(destination);
								        panel_north1.add(c1);
								        panel_north.setBounds(10,30, 150, 30);
								        panel_north1.setBounds(190,30, 150, 30);
								        tp.setBounds(50, 148, 100, 48);
								        fh.setBounds(180,148, 100, 48);
								        cc.setBounds(40,85, 100, 50); 
								        rq.setBounds(200,85, 100, 50);
								        sw.setBounds(40, 210, 100, 50);
								        yd.setBounds(200, 210, 100, 50);
								        ed.setBounds(40, 280, 100, 50);
								        wz.setBounds(200, 280, 100, 50);
								        layeredPane.add(jp,JLayeredPane.DEFAULT_LAYER);  
								        layeredPane.add(panel_north,JLayeredPane.MODAL_LAYER);  
								        layeredPane.add(panel_north1,JLayeredPane.MODAL_LAYER); 
								        layeredPane.add(tp,JLayeredPane.MODAL_LAYER); 
								        layeredPane.add(fh,JLayeredPane.MODAL_LAYER); 
								        layeredPane.add(cc,JLayeredPane.MODAL_LAYER); 
								        layeredPane.add(rq,JLayeredPane.MODAL_LAYER);  
								        layeredPane.add(sw,JLayeredPane.MODAL_LAYER);  
								        layeredPane.add(yd,JLayeredPane.MODAL_LAYER);  
								        layeredPane.add(ed,JLayeredPane.MODAL_LAYER);  
								        layeredPane.add(wz,JLayeredPane.MODAL_LAYER);  
								        jf.setLayeredPane(layeredPane);  
								        jf.setLayout(null);
								        jf.setResizable(false);       //�������޸Ĵ���
								        jf.setSize(345,375);  
								        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
								        jf.setLocationRelativeTo(null);//����λ�þ���
								        jf.setVisible(true);    
										f.setVisible(false);
										ActionListener listener = new ActionListener()    //���ؼ���
												{
													public void actionPerformed(ActionEvent event)
													{   
														jf.setVisible(false);
														f.setVisible(true);
													}
													};
										fh.addActionListener(listener);
										ActionListener listener1 = new ActionListener()    //���β�ѯ����
												{
													public void actionPerformed(ActionEvent event)
													{   
														try
													 {       
															Class.forName(driverName);
															 dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
															 stmt=dbConn.createStatement();
															 String  s="Select * from view_TICKET_INFORMATION_TICKET_LEVEL where ��ʼվ='"+city+"' AND �յ�վ='"+city1+"'order by �г���";
														   	 ResultSet rs=stmt.executeQuery(s);   //�α� ��ѯ��������
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
														} catch (Exception e) {
															e.printStackTrace();
														}
												 }
													};
										cc.addActionListener(listener1);
					     			}
								 else {
									JOptionPane.showMessageDialog(null, "�ܱ�Ǹ��δ�ҵ�������ѯ�ĳ�վ����г���Ϣ","��ʾ",JOptionPane.INFORMATION_MESSAGE);
									new MainUI().mainUI();    
								 }
								//dbConn.close();  //�ر����ݿ�
						   }
							catch (Exception e)
							  {
								e.printStackTrace();
								System.out.print("����ʧ��");
							  }
						   }
					};
					sureButton.addActionListener(listener1);
		}
		public static void main(String[] agrs)
		{
			LoginUI qq = new LoginUI();
			qq.loginUI();
		}
	}


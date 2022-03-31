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
	//菜单模块
	String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String dbURL = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Ticket_System";
	String userName = "sa";
	String userPwd = "sa";
	Statement stmt;
	Connection dbConn = null;
		public void mainUI()
		{
			JFrame f = new JFrame("查询车票");
			JButton sureButton = new JButton("查询列车");
			JButton fh = new JButton("返回登录");
			f.setResizable(false);       //不允许修改窗口
			f.setLayout(new FlowLayout(FlowLayout.CENTER,20,10));
			f.setSize(200,200);
			f.setLocationRelativeTo(null);//窗口位置居中
			f.setVisible(true);
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //关闭窗口，释放内存
			JLabel starting = new JLabel("起始站");
			JLabel end = new JLabel("终点站");
			JComboBox<String>comboBox = new JComboBox<>();
			JComboBox<String>comboBox1 = new JComboBox<>();
			comboBox.addItem("北京");comboBox1.addItem("北京");
			comboBox.addItem("上海");comboBox1.addItem("上海");
			comboBox.addItem("广州");comboBox1.addItem("广州");
			comboBox.addItem("重庆");comboBox1.addItem("重庆");
			comboBox.addItem("成都");comboBox1.addItem("成都");
			comboBox.addItem("长沙");comboBox1.addItem("长沙");
			comboBox.addItem("天津");comboBox1.addItem("天津");
			comboBox.addItem("哈尔滨");comboBox1.addItem("哈尔滨");
			f.add(starting);
			f.add(end);
			f.add(comboBox);
			f.add(comboBox1);
			f.add(sureButton);
			f.add(fh);
			ActionListener listener = new ActionListener()    //返回登录的监听
					{
				
				public void actionPerformed(ActionEvent event)
				{ 
					f.setVisible(false);
					new LoginUI().loginUI();
				}
					};
				fh.addActionListener(listener);
			ActionListener listener1 = new ActionListener()    //查询列车监听
					{
						public void actionPerformed(ActionEvent event)
						{   
							String city = comboBox.getSelectedItem().toString();   //获取下拉框中的值
							String city1 = comboBox1.getSelectedItem().toString();   //获取下拉框中的值
							f.setVisible(false);
							try
						 {
								 Class.forName(driverName);
								 dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
								 stmt=dbConn.createStatement();
								 String  s="(Select * from TICKET_INFORMATION where 起始站='"+city+"' AND 终点站='"+city1+"')";
								 //暂时查询起始站北京和终点站广州
							   	 ResultSet r=stmt.executeQuery(s);   //游标 查询表内内容
								 if(r.next())
					     			{   //查询窗口
									    JLayeredPane layeredPane;  //创建一个JLayeredPane用于分层的。  
								        JPanel jp;  
								        JLabel jl;   //创建一个Panel和一个Label用于存放图片，作为背景。  
								        JButton cc =new JButton("车次查询");
								        JButton rq =new JButton("日期查询");
								        JButton sw =new JButton("商务座购票");
								        JButton yd =new JButton("一等座购票");
								        JButton ed =new JButton("二等座购票");
								        JButton wz =new JButton("无座购票");
								        JButton fh =new JButton("返回");
								        JButton tp =new JButton("退票");
								        ImageIcon image;  
								        tp.addActionListener(new BackTicket(city, city1));//退票的监听
								        rq.addActionListener(new DateMonitoring(city, city1)); //日期查询的监听
								        sw.addActionListener(new BusinessMonitoring(city, city1)); //商务购票的监听
								        yd.addActionListener(new FirstClassTicket(city, city1)); //一等购票的监听
								        ed.addActionListener(new SecondClassTicket(city, city1)); //二等购票的监听
								        wz.addActionListener(new NoSeatTicket(city, city1)); //无座购票的监听
								    	JFrame jf= new JFrame();
								        layeredPane=new JLayeredPane();  
								        image=new ImageIcon("C:\\Users\\DEATH\\Desktop\\1.png");     
								        jp=new JPanel();  
								        jp.setBounds(0,0,image.getIconWidth(),image.getIconHeight());  //面板的位置和宽高
								        jl=new JLabel(image);  
								        jp.add(jl);  
								        JPanel train=new JPanel();  //存储列车信息的面板 
								        JPanel panel_north=new JPanel(); 
								        JPanel panel_north1=new JPanel();
								        JLabel starting_city = new JLabel("出发地");starting_city.setFont(new Font("黑体",Font.PLAIN,20));//设置字体的字体，大小   	
								        JLabel destination = new JLabel("目的地");destination.setFont(new Font("黑体",Font.PLAIN,20));//设置字体的字体，大小   
								        JLabel c = new JLabel(city);c.setFont(new Font("华文行楷",Font.PLAIN,25));//设置字体的字体，大小   	
								        JLabel c1 = new JLabel(city1);c1.setFont(new Font("华文行楷",Font.PLAIN,25));//设置字体的字体，大小   	
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
								        jf.setResizable(false);       //不允许修改窗口
								        jf.setSize(345,375);  
								        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
								        jf.setLocationRelativeTo(null);//窗口位置居中
								        jf.setVisible(true);    
										f.setVisible(false);
										ActionListener listener = new ActionListener()    //返回监听
												{
													public void actionPerformed(ActionEvent event)
													{   
														jf.setVisible(false);
														f.setVisible(true);
													}
													};
										fh.addActionListener(listener);
										ActionListener listener1 = new ActionListener()    //车次查询监听
												{
													public void actionPerformed(ActionEvent event)
													{   
														try
													 {       
															Class.forName(driverName);
															 dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
															 stmt=dbConn.createStatement();
															 String  s="Select * from view_TICKET_INFORMATION_TICKET_LEVEL where 起始站='"+city+"' AND 终点站='"+city1+"'order by 列车号";
														   	 ResultSet rs=stmt.executeQuery(s);   //游标 查询表内内容
														   	Vector columnNames=new Vector();//设置列名
															columnNames.add("列车号");
															columnNames.add("日期");
															columnNames.add("发车时间");
															columnNames.add("到站时间");
															columnNames.add("商务座票数");
															columnNames.add("一等座票数");
															columnNames.add("二等座票数");
															columnNames.add("无座票数");
															Vector rowData = new Vector();	
														   	while(rs.next()) {
																//rowData可以存放多行
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
															DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// 设置table内容居中
															tcr.setHorizontalAlignment(SwingConstants.CENTER);// 这句和上句作用一样
															jt.setDefaultRenderer(Object.class, tcr);
															jt.setEnabled(false);//不允许修改表格内容
															jt.setFont(new Font("楷体", Font.PLAIN, 18));// 设置表格字体
															JScrollPane jsp = new JScrollPane(jt);
															JPanel jp1 =new JPanel();
															jp1.add(jsp);
															JFrame jf = new JFrame();
															jf.add(jsp);
															jf.setSize(985,900);
															jf.setLocationRelativeTo(null);//窗口位置居中
															jf.setVisible(true);
														} catch (Exception e) {
															e.printStackTrace();
														}
												 }
													};
										cc.addActionListener(listener1);
					     			}
								 else {
									JOptionPane.showMessageDialog(null, "很抱歉！未找到您所查询的车站间的列车信息","提示",JOptionPane.INFORMATION_MESSAGE);
									new MainUI().mainUI();    
								 }
								//dbConn.close();  //关闭数据库
						   }
							catch (Exception e)
							  {
								e.printStackTrace();
								System.out.print("连接失败");
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


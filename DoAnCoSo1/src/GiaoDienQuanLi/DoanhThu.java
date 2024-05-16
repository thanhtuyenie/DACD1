package GiaoDienQuanLi;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JTextField;

public class DoanhThu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private  JTable table;
	private  JTextField txtTongDoanhThu;
	
	static Connection con;
	static Statement stmt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoanhThu frame = new DoanhThu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DoanhThu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 825, 590);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 10, 777, 493);
		contentPane.add(layeredPane);
		
		
		JLabel lblAnhNen = new JLabel("");
		lblAnhNen.setBounds(0, 0, 801, 522);
		contentPane.add(lblAnhNen);
		lblAnhNen.setIcon(new ImageIcon("D:\\ĐACS1\\doanhthuQL.png"));
		
		
		JLabel lblDoanhThu = new JLabel("Doanh Thu");
		lblDoanhThu.setForeground(new Color(255, 128, 0));
		lblDoanhThu.setFont(new Font("Segoe UI Black", Font.PLAIN, 35));
		lblDoanhThu.setBounds(290, 43, 238, 45);
		layeredPane.add(lblDoanhThu);
		
		JLabel lblTongDoanhThu = new JLabel("Tổng Doanh thu:");
		lblTongDoanhThu.setForeground(new Color(255, 255, 255));
		lblTongDoanhThu.setFont(new Font("Calibri Light", Font.BOLD, 22));
		lblTongDoanhThu.setBounds(150, 347, 182, 32);
		layeredPane.add(lblTongDoanhThu);
		
		txtTongDoanhThu = new JTextField();
		txtTongDoanhThu.setForeground(Color.WHITE);
		txtTongDoanhThu.setFont(new Font("Calibri Light", Font.PLAIN, 22));
		txtTongDoanhThu.setColumns(10);
		txtTongDoanhThu.setCaretColor(Color.BLACK);
		txtTongDoanhThu.setBorder(null);
		txtTongDoanhThu.setBackground(new Color(0, 0, 0, 1));
		txtTongDoanhThu.setBounds(325, 341, 246, 45);
		layeredPane.add(txtTongDoanhThu);
		
		JButton btnThoat = new JButton("");
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				GiaoDienChinh1 main = new GiaoDienChinh1();
				main.setVisible(true);
			}
		});
		btnThoat.setContentAreaFilled(false);
		btnThoat.setBorderPainted(false);
		btnThoat.setBounds(0, 10, 90, 32);
		layeredPane.add(btnThoat);
		
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				GiaoDienChinh1 main = new GiaoDienChinh1();
				main.setVisible(true);
			}
		});
		btnOK.setForeground(new Color(255, 128, 0));
		btnOK.setFont(new Font("Segoe UI Black", Font.PLAIN, 23));
		btnOK.setContentAreaFilled(false);
		btnOK.setBorderPainted(false);
		btnOK.setBounds(343, 434, 125, 42);
		layeredPane.add(btnOK);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(150, 142, 487, 182);
		layeredPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Ng\u00E0y", "Doanh thu"
			}
		));
		table.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		
	
	}
	
	
	 public  void TongCongDoanhThu() {
	    	
	        DefaultTableModel model = (DefaultTableModel) table.getModel();
	        double tongCong = 0;
	        for (int i = 0; i < model.getRowCount(); i++) {
	            double doanhthu = (double) model.getValueAt(i, 1); 
	            tongCong += doanhthu;
	        }
	        txtTongDoanhThu.setText(String.valueOf(tongCong));
	    }
	
	
	public static Connection ketNoiCSDL() {
	  	  
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/app_order?useUnicode=yes&characterEncoding=UTF-8", "root", "");
            stmt = con.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
        
    }
	
	
	public void napDuLieuTuCSDL() {
	    con = ketNoiCSDL(); 
	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	    
	    try {
	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM doanhthu");
	        
	        while (rs.next()) {
	            String ngay = rs.getString("ngay");
	            double doanhThu = rs.getDouble("doanhthu");
	            model.addRow(new Object[]{ngay, doanhThu});
	        }
	       
	        con.close();
	        TongCongDoanhThu();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

}
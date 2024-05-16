package GiaoDienOrder;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class DangNhap extends JFrame {

	private JPanel contentPane;
	private JPasswordField passDN;
	private JTextField tfTenDN;
	static Connection con;
	static Statement stmt;
	private JTextField tfSDT;
	private JTextField tfMaNhanVien;
	public static String manhanvien;
	private JButton btnThoat;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DangNhap frame = new DangNhap();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void LayDuLieuDangNhap() {
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con= DriverManager.getConnection("jdbc:mysql://localhost:3306/app_order?useUnicode=yes&characterEncoding=UTF-8", "root", "");
			stmt= con.createStatement();
		    
			String sql = "SELECT * FROM nhanvien where manhanvien = ? and tennhanvien=? and matkhau=? and sdt=?";	//cau truy vấn sql.
		    PreparedStatement ps = con.prepareStatement(sql);					//cho phép chỉ định tham số đầu vào khi chạy.
		    ps.setString(1, tfMaNhanVien.getText());
		    ps.setString(2, tfTenDN.getText());
		    ps.setString(3, passDN.getText());
		    ps.setString(4, tfSDT.getText());
		    ResultSet rs = ps.executeQuery();								    //exc - trả về 1 đối tượng resultset khi thực thi câu lện select.
		    
		    
		    if (tfTenDN.getText().equals("")|passDN.getText().equals("")|tfSDT.getText().equals("")) {
		    	JOptionPane.showMessageDialog(this, "Chưa nhập tên và mật khẩu và SDT");
		    }
		    
		    else if (rs.next()) {
		    	   // Lấy giá trị mã nhân viên từ ResultSet
                String maNhanVien = rs.getString("manhanvien");
                
                // Gán giá trị mã nhân viên vào biến static
                manhanvien = maNhanVien;
                
		    	dispose();
		    	GiaoDienChinh giaodienchinh = new GiaoDienChinh();
		    	giaodienchinh.setVisible(true);
		    	JOptionPane.showMessageDialog(this, "Đăng nhập thành công");
		    }
		    else {
		    	JOptionPane.showMessageDialog(this, "Có thể tên hoặc mật khẩu không đúng. Vui lòng thử lại");
		    }
		    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void addPlaceholderStyles(JTextField textFiled) {
		Font font = textFiled.getFont();
		font = font.deriveFont(Font.ITALIC);
		textFiled.setFont(font);
		textFiled.setForeground(Color.gray);
	}
	
	public void removePlaceholderStyles(JTextField textFiled) {
		Font font = textFiled.getFont();
		font = font.deriveFont(Font.PLAIN|Font.BOLD);
		textFiled.setFont(font);
		textFiled.setForeground(Color.black);	
	}
	
	
	public DangNhap() {
		setTitle("ĐĂNG NHẬP");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 811, 546);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(-2, -1, 798, 510);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblDANG_NHAP = new JLabel("ĐĂNG NHẬP");
		lblDANG_NHAP.setBounds(301, 55, 191, 63);
		lblDANG_NHAP.setBackground(new Color(218, 165, 32));
		lblDANG_NHAP.setForeground(new Color(208, 15, 53));
		lblDANG_NHAP.setFont(new Font("Calibri", Font.BOLD, 34));
		panel.add(lblDANG_NHAP);
		
		btnThoat = new JButton("");
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				DangKi dk = new DangKi();
				dk.setVisible(true);
			}
		});
		btnThoat.setContentAreaFilled(false);
		btnThoat.setBorderPainted(false);
		btnThoat.setBounds(6, 14, 85, 39);
		panel.add(btnThoat);
		
		tfMaNhanVien = new JTextField();
		tfMaNhanVien.setText("Mã nhân viên");
		tfMaNhanVien.setOpaque(false);
		tfMaNhanVien.setForeground(Color.GRAY);
		tfMaNhanVien.setFont(new Font("Calibri", Font.ITALIC, 19));
		tfMaNhanVien.setBorder(null);
		tfMaNhanVien.setBounds(228, 212, 344, 45);
		panel.add(tfMaNhanVien);
		tfMaNhanVien.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(tfMaNhanVien.getText().equals("Mã nhân viên")) {
					tfMaNhanVien.setText(null);
					tfMaNhanVien.requestFocus();
					removePlaceholderStyles(tfMaNhanVien);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(tfMaNhanVien.getText().length()==0) {
					addPlaceholderStyles(tfMaNhanVien);
					tfMaNhanVien.setText("Mã nhân viên");
				}
			}
		});
		
		
		tfTenDN = new JTextField();
		tfTenDN.setForeground(new Color(128, 128, 128));
		tfTenDN.setText("Tên đăng nhập");
		tfTenDN.setBounds(228, 150, 344, 45);
		tfTenDN.setFont(new Font("Calibri", Font.ITALIC, 19));
		tfTenDN.setOpaque(false); //trong suot
		tfTenDN.setBorder(null);
		panel.add(tfTenDN);
		tfTenDN.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(tfTenDN.getText().equals("Tên đăng nhập")) {
					tfTenDN.setText(null);
					tfTenDN.requestFocus();
					removePlaceholderStyles(tfTenDN);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(tfTenDN.getText().length()==0) {
					addPlaceholderStyles(tfTenDN);
					tfTenDN.setText("Tên đăng kí");
				}
			}
		});
		
		
		passDN = new JPasswordField();
		passDN.setForeground(new Color(128, 128, 128));
		passDN.setText("Mật khẩu");
		passDN.setEchoChar('\u0000');
		passDN.setBounds(228, 276, 344, 45);
		passDN.setFont(new Font("Calibri", Font.ITALIC, 19));
		passDN.setBorder(null);
		passDN.setOpaque(false);
		panel.add(passDN);
		passDN.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(String.valueOf(passDN.getPassword()).equals("Mật khẩu")) {
					passDN.setText(null);
					passDN.requestFocus();
					passDN.setEchoChar('\u25cf');
					removePlaceholderStyles(passDN);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (String.valueOf(passDN.getPassword()).length()==0) {
					addPlaceholderStyles(passDN);
					passDN.setText("Mật khẩu");
					passDN.setEchoChar('\u0000');
				}
			}
		});
		
		
		tfSDT = new JTextField();
		tfSDT.setForeground(new Color(128, 128, 128));
		tfSDT.setText("Số điện thoại");
		tfSDT.setOpaque(false);
		tfSDT.setFont(new Font("Calibri", Font.ITALIC, 19));
		tfSDT.setBorder(null);
		tfSDT.setBounds(228, 337, 344, 39);
		panel.add(tfSDT);
		tfSDT.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(tfSDT.getText().equals("Số điện thoại")) {
					tfSDT.setText(null);
					tfSDT.requestFocus();
					removePlaceholderStyles(tfSDT);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(tfSDT.getText().length()==0) {
					addPlaceholderStyles(tfSDT);
					tfSDT.setText("Số điện thoại");
				}
			}
		});
		
		

		JButton btnDangNhap = new JButton("ĐĂNG NHẬP");
		btnDangNhap.setContentAreaFilled(false);
		btnDangNhap.setBorderPainted(false);
		btnDangNhap.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnDangNhap.setForeground(new Color(0, 0, 0));
		btnDangNhap.setFont(new Font("Calibri", Font.BOLD, 15));
		btnDangNhap.setBounds(221, 397, 363, 45);
		panel.add(btnDangNhap);
		btnDangNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LayDuLieuDangNhap();
				
			}
		});
		
		JButton btnDKi = new JButton("Tạo Tài Khoản Mới");
		btnDKi.setIcon(new ImageIcon("D:\\ĐACS1\\muiten.png"));
		btnDKi.setBounds(318, 459, 160, 27);
		btnDKi.setForeground(new Color(255, 255, 255));
		btnDKi.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnDKi.setContentAreaFilled(false);
		btnDKi.setBorderPainted(false);
		btnDKi.setBorder(null);
		btnDKi.setBackground(new Color(245, 255, 250));
		panel.add(btnDKi);
		btnDKi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();
				DangKi dangkigd = new DangKi();
				dangkigd.setVisible(true);
			}
		});
		
		
		
		JLabel lblAnhDN = new JLabel("");
		lblAnhDN.setIcon(new ImageIcon("D:\\ĐACS1\\dangnhapNV.png"));
		lblAnhDN.setForeground(new Color(255, 255, 255));
		lblAnhDN.setBounds(0, 0, 805, 504);
		panel.add(lblAnhDN);
		
		addPlaceholderStyles(tfMaNhanVien);
		addPlaceholderStyles(tfTenDN);
		addPlaceholderStyles(passDN);
		addPlaceholderStyles(tfSDT);
	}
}
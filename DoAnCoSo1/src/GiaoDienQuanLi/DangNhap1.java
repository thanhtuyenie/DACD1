package GiaoDienQuanLi;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.protocol.Resultset;

import GiaoDienOrder.GiaoDienChinh;

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
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JLayeredPane;

public class DangNhap1 extends JFrame {

	private JPanel contentPane;
	static Connection con;
	static Statement stmt;
	private JTextField txtTen;
	private JPasswordField txtMatKhau;
	private JTextField txtSDT;
	private JTextField txtTenCuaHang;
	private JTextField txtMaCuaHang;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DangNhap1 frame = new DangNhap1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	public DangNhap1() {
		setTitle("ĐĂNG NHẬP");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 811, 538);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 10, 797, 484);
		contentPane.add(layeredPane);
		
		JLabel lblDANG_NHAP = new JLabel("ĐĂNG NHẬP");
		lblDANG_NHAP.setForeground(new Color(208, 15, 53));
		lblDANG_NHAP.setFont(new Font("Calibri", Font.BOLD, 34));
		lblDANG_NHAP.setBackground(new Color(218, 165, 32));
		lblDANG_NHAP.setBounds(310, 37, 191, 63);
		layeredPane.add(lblDANG_NHAP);
		
		txtTen = new JTextField();
		txtTen.setText("Tên đăng nhập");
		txtTen.setOpaque(false);
		txtTen.setForeground(Color.GRAY);
		txtTen.setFont(new Font("Calibri", Font.ITALIC, 19));
		txtTen.setBorder(null);
		txtTen.setBounds(240, 126, 309, 43);
		layeredPane.add(txtTen);
		txtTen.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtTen.getText().equals("Tên đăng nhập")) {
					txtTen.setText(null);
					txtTen.requestFocus();
					removePlaceholderStyles(txtTen);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtTen.getText().length()==0) {
					addPlaceholderStyles(txtTen);
					txtTen.setText("Tên đăng nhập");
				}
			}
		});
		
		txtMatKhau = new JPasswordField();
		txtMatKhau.setForeground(new Color(128, 128, 128));
		txtMatKhau.setText("Mật khẩu");
		txtMatKhau.setOpaque(false);
		txtMatKhau.setFont(new Font("Calibri", Font.ITALIC, 19));
		txtMatKhau.setEchoChar(' ');
		txtMatKhau.setBorder(null);
		txtMatKhau.setBounds(247, 273, 290, 31);
		layeredPane.add(txtMatKhau);
		txtMatKhau.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(String.valueOf(txtMatKhau.getPassword()).equals("Mật khẩu")) {
					txtMatKhau.setText(null);
					txtMatKhau.requestFocus();
					txtMatKhau.setEchoChar('\u25cf');
					removePlaceholderStyles(txtMatKhau);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (String.valueOf(txtMatKhau.getPassword()).length()==0) {
					addPlaceholderStyles(txtMatKhau);
					txtMatKhau.setText("Mật khẩu");
					txtMatKhau.setEchoChar('\u0000');
				}
			}
		});
		
		txtSDT = new JTextField();
		txtSDT.setForeground(new Color(128, 128, 128));
		txtSDT.setText("Số điện thoại");
		txtSDT.setOpaque(false);
		txtSDT.setFont(new Font("Calibri", Font.ITALIC, 19));
		txtSDT.setBorder(null);
		txtSDT.setBounds(245, 336, 298, 31);
		layeredPane.add(txtSDT);
		txtSDT.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtSDT.getText().equals("Số điện thoại")) {
					txtSDT.setText(null);
					txtSDT.requestFocus();
					removePlaceholderStyles(txtSDT);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtSDT.getText().length()==0) {
					addPlaceholderStyles(txtSDT);
					txtSDT.setText("Số điện thoại");
				}
			}
		});
		
		
		txtTenCuaHang = new JTextField();
		txtTenCuaHang.setText("Tên cửa hàng");
		txtTenCuaHang.setOpaque(false);
		txtTenCuaHang.setForeground(new Color(128, 128, 128));
		txtTenCuaHang.setFont(new Font("Calibri", Font.ITALIC, 19));
		txtTenCuaHang.setBorder(null);
		txtTenCuaHang.setBounds(243, 205, 134, 24);
		layeredPane.add(txtTenCuaHang);
		txtTenCuaHang.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtTenCuaHang.getText().equals("Tên cửa hàng")) {
					txtTenCuaHang.setText(null);
					txtTenCuaHang.requestFocus();
					removePlaceholderStyles(txtTenCuaHang);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtTenCuaHang.getText().length()==0) {
					addPlaceholderStyles(txtTenCuaHang);
					txtTenCuaHang.setText("Tên cửa hàng");
				}
			}
		});
		
		txtMaCuaHang = new JTextField();
		txtMaCuaHang.setText("Mã cửa hàng");
		txtMaCuaHang.setOpaque(false);
		txtMaCuaHang.setForeground(new Color(128, 128, 128));
		txtMaCuaHang.setFont(new Font("Calibri", Font.ITALIC, 19));
		txtMaCuaHang.setBorder(null);
		txtMaCuaHang.setBounds(425, 207, 134, 24);
		layeredPane.add(txtMaCuaHang);
		txtMaCuaHang.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtMaCuaHang.getText().equals("Mã cửa hàng")) {
					txtMaCuaHang.setText(null);
					txtMaCuaHang.requestFocus();
					removePlaceholderStyles(txtMaCuaHang);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtMaCuaHang.getText().length()==0) {
					addPlaceholderStyles(txtMaCuaHang);
					txtMaCuaHang.setText("Mã cửa hàng");
				}
			}
		});
		
		
		
		JButton btnDangNhap = new JButton("ĐĂNG NHẬP");
		btnDangNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LayDuLieuDangNhap();
			}
		});
		btnDangNhap.setForeground(Color.BLACK);
		btnDangNhap.setFont(new Font("Calibri", Font.BOLD, 15));
		btnDangNhap.setContentAreaFilled(false);
		btnDangNhap.setBorderPainted(false);
		btnDangNhap.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnDangNhap.setBounds(260, 394, 301, 36);
		layeredPane.add(btnDangNhap);
		
		JButton btnDKi = new JButton("Tạo Tài Khoản Mới");
		btnDKi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				DangKi1 dk1 = new DangKi1();
				dk1.setVisible(true);
			}
		});
		btnDKi.setForeground(Color.WHITE);
		btnDKi.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnDKi.setContentAreaFilled(false);
		btnDKi.setBorderPainted(false);
		btnDKi.setBorder(null);
		btnDKi.setBackground(new Color(245, 255, 250));
		btnDKi.setBounds(336, 440, 160, 27);
		layeredPane.add(btnDKi);
		
		
		
		JButton btnThoat = new JButton("");
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			 dispose();
			 DangKi1 dk1 = new DangKi1();
			 dk1.setVisible(true);
			}
		});
		btnThoat.setForeground(Color.WHITE);
		btnThoat.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnThoat.setContentAreaFilled(false);
		btnThoat.setBorderPainted(false);
		btnThoat.setBorder(null);
		btnThoat.setBackground(new Color(245, 255, 250));
		btnThoat.setBounds(0, 0, 70, 43);
		layeredPane.add(btnThoat);
		
		JLabel lblAnhDN = new JLabel("");
		lblAnhDN.setIcon(new ImageIcon("D:\\ĐACS1\\dangnhapQL.png"));
		lblAnhDN.setForeground(Color.WHITE);
		lblAnhDN.setBounds(0, 0, 802, 510);
		contentPane.add(lblAnhDN);
		
		
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
		    
			String sql = "SELECT * FROM quanli where tenquanli=? and tencuahang=? and macuahang=? and matkhau=? and sdt=?";	//cau truy vấn sql.
		    PreparedStatement ps = con.prepareStatement(sql);					//cho phép chỉ định tham số đầu vào khi chạy.
		    ps.setString(1, txtTen.getText());
		    ps.setString(2, txtTenCuaHang.getText());
		    ps.setString(3, txtMaCuaHang.getText());
		    ps.setString(4, txtMatKhau.getText());
		    ps.setString(5, txtSDT.getText());
		    

		    ResultSet rs = ps.executeQuery();								    //exc - trả về 1 đối tượng resultset khi thực thi câu lện select.
		    
		    
		    if (txtTen.getText().equals("")|txtMatKhau.getText().equals("")|txtSDT.getText().equals("") || txtTenCuaHang.getText().equals("") || txtMaCuaHang.getText().equals("")) {
		    	JOptionPane.showMessageDialog(this, "Không được để trống");
		    }
		    
		    else if (rs.next()) {
		    	dispose();
		    	GiaoDienChinh1 giaodienchinh1 = new GiaoDienChinh1();
		    	giaodienchinh1.setVisible(true);
		    	JOptionPane.showMessageDialog(this, "Đăng nhập thành công");
		    }
		    else {
		    	JOptionPane.showMessageDialog(this, "Có thể dữ liệu chưa trùng khớp với Đăng Kí. Vui lòng thử lại");
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
	
}
package TrangThai;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JTextField;

public class TrangThai extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	static Connection con;
	static Statement stmt;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrangThai frame = new TrangThai();
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

	public TrangThai() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 611, 454);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 20, 587, 387);
		contentPane.add(layeredPane);
		
		
		JLabel lblTieuDe = new JLabel("TY APPLICATION");
		lblTieuDe.setForeground(new Color(255, 128, 0));
		lblTieuDe.setFont(new Font("Segoe UI Black", Font.PLAIN, 30));
		lblTieuDe.setBounds(288, 10, 299, 33);
		layeredPane.add(lblTieuDe);
		
		JLabel lblChucVu = new JLabel("Chức vụ\r\n\r\n");
		lblChucVu.setForeground(new Color(128, 128, 128));
		lblChucVu.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblChucVu.setBounds(242, 152, 131, 80);
		layeredPane.add(lblChucVu);
		
		JLabel lblLoiDan = new JLabel("Phụ vụ các bạn là niềm hân hạnh của chúng tôi\r\n");
		lblLoiDan.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblLoiDan.setForeground(new Color(192, 192, 192));
		lblLoiDan.setBounds(309, 36, 232, 33);
		layeredPane.add(lblLoiDan);
		
		JLabel lblAnhNen = new JLabel("");
		lblAnhNen.setBounds(0, 0, 600, 422);
		contentPane.add(lblAnhNen);
		lblAnhNen.setIcon(new ImageIcon("D:\\ĐACS1\\trangthai.png"));
		
		
		
		JButton btnQuanli = new JButton("Quản Lí");
		btnQuanli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				GiaoDienQuanLi.DangKi1 dangki = new GiaoDienQuanLi.DangKi1();
				dangki.setVisible(true);
			}
		});
		btnQuanli.setForeground(Color.WHITE);
		btnQuanli.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		btnQuanli.setContentAreaFilled(false);
		btnQuanli.setBorderPainted(false);
		btnQuanli.setBounds(167, 266, 116, 33);
		layeredPane.add(btnQuanli);
		
		JButton btnNhanVien = new JButton("Nhân Viên ");
		btnNhanVien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				GiaoDienOrder.DangKi dangki = new GiaoDienOrder.DangKi();
				dangki.setVisible(true);
			}
		});
		btnNhanVien.setForeground(Color.WHITE);
		btnNhanVien.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		btnNhanVien.setContentAreaFilled(false);
		btnNhanVien.setBorderPainted(false);
		btnNhanVien.setBounds(335, 266, 131, 33);
		layeredPane.add(btnNhanVien);
		
		
		
		
	}
	 }


	

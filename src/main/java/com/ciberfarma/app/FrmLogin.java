package com.ciberfarma.app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.ciberfarma.model.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JTextField;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JTextField txtClave;
	private JButton btnIngresar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLogin frame = new FrmLogin();
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
	public FrmLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 501, 182);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 33, 72, 13);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Clave");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(10, 95, 72, 13);
		contentPane.add(lblNewLabel_1);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(93, 31, 151, 19);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);

		txtClave = new JTextField();
		txtClave.setBounds(90, 93, 154, 19);
		contentPane.add(txtClave);
		txtClave.setColumns(10);

		btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validar();
			}
		});
		btnIngresar.setBounds(392, 30, 85, 21);
		contentPane.add(btnIngresar);
	}

	void validar() {
		// read
		String user = leerUsuario();
		String clave = leerClave();

		// validate
		EntityManagerFactory cn = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = cn.createEntityManager();

		try {
			String jpql = "SELECT u FROM Usuario u WHERE u.correo = :xusr  AND u.clave = :xcla";

			Usuario u = em.createQuery(jpql, Usuario.class)
					.setParameter("xusr", user).setParameter("xcla", clave).getSingleResult();

			// show all the messages
			JOptionPane.showMessageDialog(this, "Binvenido " + u.getNom_usua());
		} catch (HeadlessException e) {
			JOptionPane.showMessageDialog(this, "error : " + e);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "usuario o clave incorrecta ");

		}
	}

	String leerUsuario() {
		return txtUsuario.getText().trim();
	}

	String leerClave() {
		return txtClave.getText().trim();
	}

}

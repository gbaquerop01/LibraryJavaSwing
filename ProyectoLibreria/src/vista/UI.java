package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.ScrollPane;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class UI {

	protected JFrame frame;
	
	private JLabel iSBNLabel;
	private JLabel titleLabel;
	private JLabel autorLabel;
	private JLabel editorialLabel;
	private JLabel precioLabel;
	
	protected JTextField iSBNTextField;
	protected JTextField tituloTextField;
	protected JTextField autorTextField;
	protected JTextField editorialTextField;
	protected JTextField precioTextField;
	
	protected JButton btnGuardar;
	protected JButton btnSalir;
	protected JPanel panelBiblioteca;
	private ScrollPane scrollPane;
	protected JTable tableLibros;
	protected JButton btnConsultar;
	protected JPanel panel;
	protected JButton btnBorrar;
	protected JPanel panelFormato;
	protected JRadioButton rdbtnCartone;
	protected JRadioButton rdbtnGrapada;
	protected JRadioButton rdbtnRustica;
	protected JRadioButton rdbtnEspiral;
	private JLabel lblNewLabel_2;
	protected JRadioButton rdbtnReedicion;
	protected JRadioButton rdbtnNovedad;
	protected JPanel panelEdicion;
	protected JButton btnModificar;
	protected JButton btnComprar;
	protected JButton btnVender;

	/**
	 * Create the application.
	 */
	public UI() {
		initialize();
		frame.setVisible(true);;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(true);
		frame.setBounds(100, 100, 703, 459);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorth = new JPanel();
		panelNorth.setBackground(new Color(45, 45, 45));
		frame.getContentPane().add(panelNorth, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Libreria Guillermo Baquero");
		lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 18));
		lblNewLabel.setForeground(Color.WHITE); // Change text color to white
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_panelNorth = new GroupLayout(panelNorth);
		gl_panelNorth.setHorizontalGroup(
			gl_panelNorth.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelNorth.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE))
		);
		gl_panelNorth.setVerticalGroup(
			gl_panelNorth.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelNorth.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
					.addContainerGap())
		);
		panelNorth.setLayout(gl_panelNorth);
		
		JPanel panelSouth = new JPanel();
		panelSouth.setBackground(new Color(45, 45, 45)); // Change background to black
		frame.getContentPane().add(panelSouth, BorderLayout.SOUTH);
		panelSouth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnGuardar.setIcon(new ImageIcon("imagenes\\saveFile.png"));
		btnGuardar.setBackground(Color.WHITE); // Set buttons to white background
		btnGuardar.setForeground(Color.BLACK); // Set text to black
		panelSouth.add(btnGuardar);
		
		btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSalir.setIcon(new ImageIcon("imagenes\\exitIcon.png"));
		btnSalir.setBackground(Color.WHITE);
		btnSalir.setForeground(Color.BLACK);
		panelSouth.add(btnSalir);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(192, 192, 192));
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panelLibro = new JPanel();
		panelLibro.setBackground(new Color(192, 192, 192)); // Set background to white
		tabbedPane.addTab("Libro", null, panelLibro, null);
		panelLibro.setLayout(null);
		
		JPanel panelLibros = new JPanel();
		panelLibros.setBackground(new Color(192, 192, 192));
		panelLibros.setBounds(10, 10, 229, 205);
		panelLibro.add(panelLibros);
		panelLibros.setLayout(new GridLayout(5, 2, 20, 20));
		
		iSBNLabel = new JLabel("ISBN");
		iSBNLabel.setBackground(Color.WHITE); 
		iSBNLabel.setForeground(Color.BLACK); // Set text to black
		panelLibros.add(iSBNLabel);
		iSBNLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		iSBNTextField = new JTextField();
		panelLibros.add(iSBNTextField);
		iSBNTextField.setColumns(10);
		
		titleLabel = new JLabel("Titulo");
		titleLabel.setForeground(Color.BLACK);
		panelLibros.add(titleLabel);
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		tituloTextField = new JTextField();
		panelLibros.add(tituloTextField);
		tituloTextField.setColumns(10);
		
		autorLabel = new JLabel("Autor");
		autorLabel.setForeground(Color.BLACK);
		panelLibros.add(autorLabel);
		autorLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		autorTextField = new JTextField();
		panelLibros.add(autorTextField);
		autorTextField.setColumns(10);
		
		editorialLabel = new JLabel("Editorial");
		editorialLabel.setForeground(Color.BLACK);
		panelLibros.add(editorialLabel);
		editorialLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		editorialTextField = new JTextField();
		panelLibros.add(editorialTextField);
		editorialTextField.setColumns(10);
		
		precioLabel = new JLabel("Precio");
		precioLabel.setForeground(Color.BLACK);
		panelLibros.add(precioLabel);
		precioLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		precioTextField = new JTextField();
		panelLibros.add(precioTextField);
		precioTextField.setColumns(10);
		
		panelFormato = new JPanel();
		panelFormato.setBounds(96, 219, 449, 38);
		panelLibro.add(panelFormato);
		panelFormato.setLayout(new GridLayout(1, 1, 0, 0));
		panelFormato.setBackground(Color.WHITE);
		
		rdbtnCartone = new JRadioButton("Cartone");
		panelFormato.add(rdbtnCartone);
		
		rdbtnRustica = new JRadioButton("Rustica");
		panelFormato.add(rdbtnRustica);
		
		rdbtnGrapada = new JRadioButton("Grapada");
		panelFormato.add(rdbtnGrapada);
		
		rdbtnEspiral = new JRadioButton("Espiral");
		panelFormato.add(rdbtnEspiral);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(341, 27, 301, 161);
		lblNewLabel_1.setIcon(new ImageIcon("imagenes\\libro.jpg"));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		panelLibro.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Formato:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(10, 219, 107, 38);
		panelLibro.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Edicion:");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2_1.setBounds(10, 267, 107, 38);
		panelLibro.add(lblNewLabel_2_1);
		
		panelEdicion = new JPanel();
		panelEdicion.setBounds(93, 267, 236, 38);
		panelLibro.add(panelEdicion);
		panelEdicion.setLayout(new GridLayout(1, 1, 0, 0));
		panelEdicion.setBackground(Color.WHITE);
		
		rdbtnReedicion = new JRadioButton("Re-edicion");
		panelEdicion.add(rdbtnReedicion);
		
		rdbtnNovedad = new JRadioButton("Novedad");
		panelEdicion.add(rdbtnNovedad);
		
		panelBiblioteca = new JPanel();
		tabbedPane.addTab("Biblioteca", null, panelBiblioteca, null);
		panelBiblioteca.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new ScrollPane();
		panelBiblioteca.add(scrollPane, BorderLayout.CENTER);
		
		tableLibros = new JTable();
		scrollPane.add(tableLibros);
		
		panel = new JPanel();
		panelBiblioteca.add(panel, BorderLayout.SOUTH);
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnConsultar.setIcon(new ImageIcon("imagenes\\lupaIcon.png"));
		btnConsultar.setBackground(Color.WHITE);
		btnConsultar.setForeground(Color.BLACK);
		panel.add(btnConsultar);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBorrar.setIcon(new ImageIcon("imagenes\\borrarIcon.png"));
		btnBorrar.setBackground(Color.WHITE);
		btnBorrar.setForeground(Color.BLACK);
		panel.add(btnBorrar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnModificar.setIcon(new ImageIcon("imagenes\\modificarIcon.png"));
		btnModificar.setBackground(Color.WHITE);
		btnModificar.setForeground(Color.BLACK);
		panel.add(btnModificar);
		
		btnComprar = new JButton("Comprar");
		btnComprar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnComprar.setIcon(new ImageIcon("imagenes\\comprarIcon.png"));
		btnComprar.setBackground(Color.WHITE);
		btnComprar.setForeground(Color.BLACK);
		panel.add(btnComprar);
		
		btnVender = new JButton("Vender");
		btnVender.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnVender.setIcon(new ImageIcon("imagenes\\venderIcon.png"));
		btnVender.setBackground(Color.WHITE);
		btnVender.setForeground(Color.BLACK);
		panel.add(btnVender);
		
	}
}

package cluster;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClusterInterfaz {

	private JFrame frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField textFieldNombre;
	private JTextField textFieldDni;

	private JPanel panelPrincipal;
	private JPanel panelPersona;
	private JTextPane textPaneGrupos;

	private JButton btnEliminar;

	private static ClusterLogica clusterLogica = new ClusterLogica();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClusterInterfaz window = new ClusterInterfaz();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClusterInterfaz() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 650, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));

		JPanel panel = new JPanel();
		panelPrincipal = panel;
		frame.getContentPane().add(panel, "name_729925120333");
		panel.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(16, 16, 500, 280);
		panel.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnEliminar.setEnabled(true);
			}
		});
		scrollPane.setViewportView(table);

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("DNI");
		model.addColumn("Nombre");
		model.addColumn("Deportes");
		model.addColumn("Música");
		model.addColumn("Espectáculos");
		model.addColumn("Ciencia");

		table.setModel(model);

		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelPrincipal.setVisible(false);
				panelPersona.setVisible(true);
			}
		});
		btnAgregar.setBounds(521, 16, 117, 55);
		panel.add(btnAgregar);

		JButton btnNewButton = new JButton("Eliminar");
		btnEliminar = btnNewButton;
		btnNewButton.setEnabled(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Integer row = table.getSelectedRow();
				String dni = (String)table.getValueAt(row, 0);
				clusterLogica.eliminarPersona(dni);
				model.removeRow(row);
				
				textPaneGrupos.setText(clusterLogica.obtenerGrupos());
				btnNewButton.setEnabled(false);
			}
		});
		btnNewButton.setBounds(521, 101, 117, 55);
		panel.add(btnNewButton);
		

		JTextPane textPane = new JTextPane();
		textPane.setBounds(12, 308, 504, 243);
		panel.add(textPane);
		textPaneGrupos = textPane;

		JPanel panelAgregarPersona = new JPanel();
		panelPersona = panelAgregarPersona;
		frame.getContentPane().add(panelAgregarPersona, "name_2409736215466");
		panelAgregarPersona.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(93, 61, 70, 15);
		panelAgregarPersona.add(lblNombre);

		JLabel lblNewLabel = new JLabel("DNI:");
		lblNewLabel.setBounds(93, 94, 70, 15);
		panelAgregarPersona.add(lblNewLabel);

		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(255, 59, 153, 19);
		panelAgregarPersona.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		textFieldDni = new JTextField();
		textFieldDni.setColumns(10);
		textFieldDni.setBounds(255, 92, 153, 19);
		panelAgregarPersona.add(textFieldDni);

		JLabel lblIntereses = new JLabel("Intereses:");
		lblIntereses.setBounds(93, 213, 94, 15);
		panelAgregarPersona.add(lblIntereses);

		JLabel lblDeportes = new JLabel("Deportes");
		lblDeportes.setBounds(93, 271, 94, 26);
		panelAgregarPersona.add(lblDeportes);

		JLabel lblMusica = new JLabel("Música");
		lblMusica.setBounds(93, 319, 94, 26);
		panelAgregarPersona.add(lblMusica);

		JLabel lblEspectaculos = new JLabel("Espectáculos");
		lblEspectaculos.setBounds(349, 271, 94, 26);
		panelAgregarPersona.add(lblEspectaculos);

		JLabel lblCiencia = new JLabel("Ciencia");
		lblCiencia.setBounds(349, 319, 94, 26);
		panelAgregarPersona.add(lblCiencia);

		JComboBox comboBoxDeportes = new JComboBox();
		comboBoxDeportes.setBounds(215, 271, 47, 24);
		panelAgregarPersona.add(comboBoxDeportes);
		comboBoxDeportes.setModel(new DefaultComboBoxModel(new Integer[] { 1, 2, 3, 4, 5 }));

		JComboBox comboBoxMusica = new JComboBox();
		comboBoxMusica.setBounds(215, 320, 47, 24);
		panelAgregarPersona.add(comboBoxMusica);
		comboBoxMusica.setModel(new DefaultComboBoxModel(new Integer[] { 1, 2, 3, 4, 5 }));

		JComboBox comboBoxEspectaculos = new JComboBox();
		comboBoxEspectaculos.setBounds(493, 272, 47, 24);
		panelAgregarPersona.add(comboBoxEspectaculos);
		comboBoxEspectaculos.setModel(new DefaultComboBoxModel(new Integer[] { 1, 2, 3, 4, 5 }));

		JComboBox comboBoxCiencia = new JComboBox();
		comboBoxCiencia.setBounds(493, 320, 47, 24);
		panelAgregarPersona.add(comboBoxCiencia);
		comboBoxCiencia.setModel(new DefaultComboBoxModel(new Integer[] { 1, 2, 3, 4, 5 }));

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelPersona.setVisible(false);
				panelPrincipal.setVisible(true);
				comboBoxDeportes.setSelectedIndex(0);
				comboBoxMusica.setSelectedIndex(0);
				comboBoxEspectaculos.setSelectedIndex(0);
				comboBoxCiencia.setSelectedIndex(0);
				textFieldNombre.setText("");
				textFieldDni.setText("");

			}
		});
		btnCancelar.setBounds(98, 439, 117, 25);
		panelAgregarPersona.add(btnCancelar);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(398, 439, 117, 25);
		panelAgregarPersona.add(btnGuardar);

		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nombre = textFieldNombre.getText();
				String dni = textFieldDni.getText();


				boolean dniIngresado = clusterLogica.dniYaFueIngresado(dni);
				//VALIDAR DNI NO ESTE VACIO, NOMBRE no este VACIO y DNI no este repetido
				if( !dniIngresado && !dni.isEmpty() && !nombre.isEmpty()) {
					Integer deporte = (Integer) comboBoxDeportes.getSelectedItem();
					Integer musica = (Integer) comboBoxMusica.getSelectedItem();
					Integer espectaculos = (Integer) comboBoxEspectaculos.getSelectedItem();
					Integer ciencia = (Integer) comboBoxCiencia.getSelectedItem();
					
					try {
						
						
						clusterLogica.agregarPersona(dni, nombre, deporte, musica, espectaculos, ciencia);
						textPaneGrupos.setText(clusterLogica.obtenerGrupos());
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	
					model.addRow(new Object[] { dni, nombre, deporte, musica, espectaculos, ciencia });
	
					panelPersona.setVisible(false);
					panelPrincipal.setVisible(true);
					comboBoxDeportes.setSelectedIndex(0);
					comboBoxMusica.setSelectedIndex(0);
					comboBoxEspectaculos.setSelectedIndex(0);
					comboBoxCiencia.setSelectedIndex(0);
					textFieldNombre.setText("");
					textFieldDni.setText("");
				}else {
					
					JOptionPane.showMessageDialog(frame,"Ingrese nombre y DNI (no repetido)");
				}
			}
		});
		
		
	}
}

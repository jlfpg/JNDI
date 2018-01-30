package View;


import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.Map;
import java.util.Properties;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;

public class view extends JFrame {

	
	public JPanel contentPane;
	public JTextField txtfRuta1;
	public JTextField txtfRuta2;
	public JTextField txtfRuta3;
	public JButton btnBuscar;
	public JComboBox cbRuta;
	String fichero1 = "";
	String fichero2 = "";
	String fichero3 = "";
	

	/**
	 * Launch the application.
	 */

	public void launch() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					view frame = new view();
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
	public view() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtfRuta1 = new JTextField();
		txtfRuta1.setBounds(170, 139, 86, 20);
		contentPane.add(txtfRuta1);
		txtfRuta1.setColumns(10);
		
		txtfRuta2 = new JTextField();
		txtfRuta2.setBounds(170, 170, 86, 20);
		contentPane.add(txtfRuta2);
		txtfRuta2.setColumns(10);
		
		txtfRuta3 = new JTextField();
		txtfRuta3.setBounds(170, 201, 86, 20);
		contentPane.add(txtfRuta3);
		txtfRuta3.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(335, 227, 89, 23);
		contentPane.add(btnBuscar);
		
		cbRuta = new JComboBox();
		cbRuta.setModel(new DefaultComboBoxModel(new String[] {"file:///C:\\", "file:///D:\\"}));
		cbRuta.setBounds(93, 56, 242, 20);
		contentPane.add(cbRuta);
		
		JLabel lblRuta = new JLabel("RUTA");
		lblRuta.setHorizontalAlignment(SwingConstants.CENTER);
		Font font = lblRuta.getFont();
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		lblRuta.setFont(font.deriveFont(attributes));
		lblRuta.setBounds(170, 31, 86, 14);
		contentPane.add(lblRuta);
		
		JLabel lblArchivos = new JLabel("ARCHIVOS");
		lblArchivos.setHorizontalAlignment(SwingConstants.CENTER);
		Font font1 = lblRuta.getFont();
		Map attributes1 = font1.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		lblArchivos.setFont(font1.deriveFont(attributes));
		lblArchivos.setBounds(170, 104, 86, 14);
		contentPane.add(lblArchivos);
		
		JLabel lblNewLabel_1 = new JLabel("ARCHIVO 1");
		lblNewLabel_1.setBounds(93, 142, 67, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("ARCHIVO 2");
		lblNewLabel_2.setBounds(93, 173, 67, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("ARCHIVO 3");
		lblNewLabel_3.setBounds(93, 204, 67, 14);
		contentPane.add(lblNewLabel_3);
		
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String ruta = getCbRuta();
				fichero1 = getTxtfRuta1();
				fichero2 = getTxtfRuta2();
				fichero3 = getTxtfRuta3();
				
				String[] arr = new String[4];
				arr[0] = ruta;
				arr[1] = fichero1;
				arr[2] = fichero2;
				arr[3] = fichero3;
				
				try {
					buscarMod(arr);
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		
		
	}

	public String getTxtfRuta1() {
		try {
			return txtfRuta1.getText();
		} catch (Exception e) {
			return "";
		}
	}

	public String getTxtfRuta2() {
		try {
			return txtfRuta2.getText();
		} catch (Exception e) {
			return "";
		}
		
	}


	public String getTxtfRuta3() {
		try {
			return txtfRuta3.getText();
		} catch (Exception e) {
			return "";
		}
	}


	public String getCbRuta() {
		System.out.println((String)cbRuta.getSelectedItem());
		return (String)cbRuta.getSelectedItem();
	}

	public void buscarMod(String[] args) throws NamingException {
		if (args.length < 2) {
			System.out.println("Indique <directorio> <ficheros...> a buscar");
			return;
		}
		//System.out.println("HEPASAADA");
		// creamos el initial context

		Properties p = new Properties();
		// definimos la clase del driver

		p.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");

		p.put(Context.PROVIDER_URL, args[0]);
		Context ctx = new InitialContext(p);

		// busca los ficheros dentro del contexto
		if(fichero1.equals("") && fichero2.equals("") && fichero3.equals("")){
			System.out.println("Ficheros no introducidos");
		}
		else{
		for (int i = 1; i < args.length; i++) {
			
			try {
				ctx.lookup(args[i]);
				System.out.println(args[i] + "  ENCONTRADO!!");
				
			} catch (NamingException ex) {
				System.out.println(args[i] + "  NO EXISTE");
			}
		}
		}

	}
}

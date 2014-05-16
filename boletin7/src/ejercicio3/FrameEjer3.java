package ejercicio3;

import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FrameEjer3 extends JFrame implements ActionListener,ItemListener{

	//Propiedades
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtDNI;
	private JTextField txtEdad;
	private JLabel lblNombre;
	private JLabel lblApellidos;
	private JLabel lblDNI;
	private JLabel lblEdad;
	private JLabel lblSexo;
	private JRadioButton sexMasc;
	private JRadioButton sexFem;
	private ButtonGroup GroupSexo;
	private JButton btnAñadir;
	private JButton btnVisualizar;
	private JButton btnCerrar;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JButton btnSiguiente;
	private JButton btnAnterior;
	private JButton btnAñadirDatos;
	private sexo EnumSexo;
	private Persona persona;
	private ArrayList<Persona> collection;
	private RandomAccessFile fileRand=null;
	private File f=new File("/home/alumnoinfo/Escritorio/prueba/datos.txt");
	private int cont=-1;
	
	//Constructor
	public FrameEjer3(){
		super("Formulario Datos Personales");
		this.setLayout(null);
		//lblNombre
		lblNombre=new JLabel("Nombre");
		lblNombre.setSize(lblNombre.getPreferredSize());
		lblNombre.setLocation(10,10);
		add(lblNombre);
		//txtNombre
		txtNombre=new JTextField("Nombre");
		txtNombre.setToolTipText("Campo para poner el nombre");
		txtNombre.setSize(txtNombre.getPreferredSize());
		txtNombre.setLocation(10,10+lblNombre.getHeight());
		txtNombre.setEnabled(false);
		add(txtNombre);
		
		//lblApellido
		lblApellidos=new JLabel("Apellidos");
		lblApellidos.setSize(lblApellidos.getPreferredSize());
		lblApellidos.setLocation(20+lblNombre.getWidth(),10);
		add(lblApellidos);
		//txtApellido
		txtApellidos=new JTextField("Apellidos              ");
		txtApellidos.setToolTipText("Campo para rellenar con el apellido");
		txtApellidos.setSize(txtApellidos.getPreferredSize());
		txtApellidos.setLocation(20+txtNombre.getWidth(),10+lblApellidos.getHeight());
		txtApellidos.setEnabled(false);
		add(txtApellidos);
		
		//lblDNI
		lblDNI=new JLabel("nº DNI");
		lblDNI.setSize(lblDNI.getPreferredSize());
		lblDNI.setLocation(10,50);
		add(lblDNI);
		//txtDNI
		txtDNI=new JTextField("Num DNI");
		txtDNI.setToolTipText("Inserte su DNI sin letra");
		txtDNI.setSize(txtDNI.getPreferredSize());
		txtDNI.setLocation(10,50+lblDNI.getHeight());
		txtDNI.setEnabled(false);
		add(txtDNI);
		//lblEdad
		lblEdad=new JLabel("Edad");
		lblEdad.setSize(lblEdad.getPreferredSize());
		lblEdad.setLocation(30+lblDNI.getWidth(),50);
		add(lblEdad);
		//txtEdad
		txtEdad=new JTextField("Edad");
		txtEdad.setToolTipText("Escriba aqui su Edad actual");
		txtEdad.setSize(txtEdad.getPreferredSize());
		txtEdad.setLocation(15+txtDNI.getWidth(),50+lblEdad.getHeight());
		txtEdad.setEnabled(false);
		add(txtEdad);		
		//lblSexo
		lblSexo=new JLabel("Sexo");
		lblSexo.setSize(lblSexo.getPreferredSize());
		lblSexo.setLocation(10,90);
		add(lblSexo);
		//sexMasc
		sexMasc= new JRadioButton("Masculino");
		sexMasc.setToolTipText("Seleccione esto si es un Hombre");
		sexMasc.setSize(sexMasc.getPreferredSize());
		sexMasc.setLocation(10,110);
		sexMasc.addItemListener(this);
		sexMasc.setEnabled(false);
		add(sexMasc);
		//sexFem
		sexFem=new JRadioButton("Femenino");
		sexFem.setToolTipText("Seleccione si es una Mujer");
		sexFem.setSize(sexFem.getPreferredSize());
		sexFem.setLocation(10,130);
		sexFem.addItemListener(this);
		sexFem.setEnabled(false);
		add(sexFem);
		//GroupSexo
		GroupSexo=new ButtonGroup();
		GroupSexo.add(sexMasc);
		GroupSexo.add(sexFem);
		sexMasc.setSelected(true);
		//btnAñadir
		btnAñadir=new JButton("Añadir Datos");
		btnAñadir.setToolTipText("Añadir nuevos datos a la base de datos");
		btnAñadir.setSize(btnAñadir.getPreferredSize());
		btnAñadir.setLocation(10,160);
		btnAñadir.addActionListener(this);
		add(btnAñadir);
		//btnVisualizar
		btnVisualizar=new JButton("Visualizar");
		btnVisualizar.setToolTipText("Presione para ver la base de datos");
		btnVisualizar.setSize(btnVisualizar.getPreferredSize());
		btnVisualizar.setLocation(20+btnAñadir.getWidth(),160);
		btnVisualizar.addActionListener(this);
		add(btnVisualizar);
		//btnCerrar
		btnCerrar=new JButton("Cerrar Prog");
		btnCerrar.setToolTipText("Presione para cerrar el programa");
		btnCerrar.setSize(btnCerrar.getPreferredSize());
		btnCerrar.setLocation(30+btnAñadir.getWidth()+btnVisualizar.getWidth(),160);
		btnCerrar.addActionListener(this);
		add(btnCerrar);	
		//btnAceptar
		btnAceptar= new JButton("Aceptar");
		btnAceptar.setToolTipText("Presionar para añadir a la base de datos");
		btnAceptar.setSize(btnAñadir.getSize());
		btnAceptar.setLocation(btnAñadir.getLocation());
		btnAceptar.setVisible(false);
		btnAceptar.addActionListener(this);
		add(btnAceptar);
		//btnCancelar
		btnCancelar= new JButton("Cancelar");
		btnCancelar.setToolTipText("Presionar para volver atras");
		btnCancelar.setSize(btnCerrar.getSize());
		btnCancelar.setLocation(btnCerrar.getLocation());
		btnCancelar.setVisible(false);
		btnCancelar.addActionListener(this);
		add(btnCancelar);
		//btnSiguiente
		btnSiguiente=new JButton("Siguiente");
		btnSiguiente.setToolTipText("Presione para ver la Siguiente persona");
		btnSiguiente.setSize(btnSiguiente.getPreferredSize());
		btnSiguiente.setLocation(10,200);
		btnSiguiente.addActionListener(this);
		add(btnSiguiente);
		//btnAnterior
		btnAnterior=new JButton("Anterior");
		btnAnterior.setToolTipText("Presione para ver la persona Anterior");
		btnAnterior.setSize(btnAnterior.getPreferredSize());
		btnAnterior.setLocation(20+btnSiguiente.getWidth(),200);
		btnAnterior.addActionListener(this);
		add(btnAnterior);
		//btnAñadirDatos
		btnAñadirDatos=new JButton("Cargar Datos");
		btnAñadirDatos.setToolTipText("Presione para cargar datos desde un archivo de texto");
		btnAñadirDatos.setSize(btnAñadirDatos.getPreferredSize());
		btnAñadirDatos.setLocation(10, 240);
		btnAñadirDatos.addActionListener(this);
		add(btnAñadirDatos);
		//collection
		collection=new ArrayList<Persona>();
		//LeerArchivo
		//File f=new File("/home/alumnoinfo/Escritorio/prueba/datos.txt");
		if(f.exists()){
			System.err.println("Existe");
			try{
				fileRand=new RandomAccessFile(f, "r");
				String temp="";
				while(fileRand.getFilePointer()!=fileRand.length()){					
					Persona p1=new Persona();
					System.err.println("Nueva persona");
					p1.setNombre(fileRand.readUTF());
					System.err.println("Nombre añadido");
					p1.setApellido(fileRand.readUTF());
					System.err.println("Apellido añadido");
					p1.setDNI(fileRand.readUTF());
					System.err.println("dni añadido");
					temp=fileRand.readUTF();
					System.err.println("temp creado");
					if(temp.contains("MASCULINO")){						
						p1.setSexo1(EnumSexo.MASCULINO);
						System.err.println("masculino añadido");
					}
					else{						
						p1.setSexo1(EnumSexo.FEMENINO);
						System.err.println("femenino añadido");
					}
					p1.setEdad(Integer.parseInt(fileRand.readUTF()));
					System.err.println("edad añadida");
					collection.add(p1);
					System.err.println("persona añadida");
				}
				fileRand.close();
				System.err.println("Datos cargados con exito");
			}
			catch(FileNotFoundException e){
				System.err.println("Archivo datos.txt no encontrado");
			}
			catch(IOException e){
				System.err.println("Error leyendo datos");
				e.getMessage();
			}
			
		}
		else{
			System.err.println("No existe");
		}
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int res = JOptionPane
						.showConfirmDialog(null, "Deseas cerrar el programa?",
								"Eventos Teclado", JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE);
				if (res == JOptionPane.OK_OPTION){
					e.getWindow().dispose();
					if(f.exists()){
						try{
							fileRand=new RandomAccessFile(f, "rw");
							for(int i=0;i<collection.size();i++){
								System.err.println("dentro for");
								fileRand.writeUTF(collection.get(i).getNombre());
								fileRand.writeUTF(collection.get(i).getApellido());
								fileRand.writeUTF(collection.get(i).getDNI());
								if(collection.get(i).getSexo1()==sexo.MASCULINO){
									fileRand.writeUTF("MASCULINO");
								}
								else{
									fileRand.writeUTF("FEMENINO");
								}
								fileRand.writeUTF(String.format("%d",collection.get(i).getEdad()));
							}
							fileRand.close();
							System.err.println("Fuera for");
						}
						catch(FileNotFoundException e2){
							System.err.println("archivo no encontrado, btnAñadir");
						}
						catch(IOException e2){
							System.err.println("Fallo escribiendo, btnAñadir");
						}
					}
				}
				
			}
		});
	}
	
	//Metodos
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnAñadirDatos){
			FileNameExtensionFilter filtro=new FileNameExtensionFilter("Texto", "txt");
			JFileChooser fc=new JFileChooser();
			fc.addChoosableFileFilter(filtro);
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int respuesta=fc.showOpenDialog(this);
			if(respuesta==JFileChooser.APPROVE_OPTION){
				File f=new File(fc.getSelectedFile().getPath());
				if(f.exists()){
					System.err.println("Existe");
					try{
						fileRand=new RandomAccessFile(f, "r");
						String temp="";
						while(fileRand.getFilePointer()!=fileRand.length()){					
							Persona p1=new Persona();
							System.err.println("Nueva persona");
							p1.setNombre(fileRand.readUTF());
							System.err.println("Nombre añadido");
							p1.setApellido(fileRand.readUTF());
							System.err.println("Apellido añadido");
							p1.setDNI(fileRand.readUTF());
							System.err.println("dni añadido");
							temp=fileRand.readUTF();
							System.err.println("temp creado");
							if(temp.contains("MASCULINO")){						
								p1.setSexo1(EnumSexo.MASCULINO);
								System.err.println("masculino añadido");
							}
							else{						
								p1.setSexo1(EnumSexo.FEMENINO);
								System.err.println("femenino añadido");
							}
							p1.setEdad(Integer.parseInt(fileRand.readUTF()));
							System.err.println("edad añadida");
							collection.add(p1);
							System.err.println("persona añadida");
						}
						fileRand.close();
						System.err.println("Datos cargados con exito");
					}
					catch(FileNotFoundException e2){
						System.err.println("Archivo datos.txt no encontrado");
					}
					catch(IOException e2){
						System.err.println("Error leyendo datos");
						e2.getMessage();
					}
				}
			}
		}
		if(e.getSource()==btnAñadir){
			btnAñadir.setVisible(false);
			btnAceptar.setVisible(true);
			btnCancelar.setVisible(true);
			btnCerrar.setVisible(false);
			btnSiguiente.setEnabled(false);
			btnAnterior.setEnabled(false);
			sexMasc.setEnabled(true);
			sexFem.setEnabled(true);
			for(int i=0;i<getContentPane().getComponentCount();i++){
				if(getContentPane().getComponent(i).getClass()==JTextField.class){
					((JTextField)getContentPane().getComponent(i)).setText("");
					((JTextField)getContentPane().getComponent(i)).setEnabled(true);
				}				
			}
		}
		if(e.getSource()==btnAceptar){
			if(txtNombre.getText().trim().equals("")){
				JOptionPane.showMessageDialog(this, "No ha introducido nombre",
						"Error con el nombre",JOptionPane.WARNING_MESSAGE);
			}else{
				try{
					int edad=0;
					if(txtEdad.getText().trim().equals("Edad") || txtEdad.getText().trim().equals("")){
						txtEdad.setText("0");
					}
					char letraDNI=persona.letraDNI(txtDNI.getText());
					try{
						edad=Integer.parseInt(txtEdad.getText());
						if(edad>=0){
							persona=new Persona(txtNombre.getText().trim(),txtApellidos.getText().trim(),txtDNI.getText().trim()+letraDNI,EnumSexo,edad);
							boolean repetido=false;
							for(int i=0 ;i<collection.size() && !repetido;i++){
								//System.out.println("bucle for");
								//System.out.printf("%s\n%s", persona.numeroDNI(),collection.get(i).numeroDNI());
								if(persona.numeroDNI().equals(collection.get(i).numeroDNI())){
									//System.out.println("if");
									JOptionPane.showMessageDialog(this, "DNI repetido","DNI repetido",JOptionPane.WARNING_MESSAGE);
									repetido=true;
								}
								else{
									repetido=false;
								}
							}
							if(!repetido){
								collection.add(persona);
								JOptionPane.showMessageDialog(this, "Datos añadidos correctamente","Datos añadidos",JOptionPane.INFORMATION_MESSAGE);
								btnAñadir.setVisible(true);
								btnAceptar.setVisible(false);
								btnCancelar.setVisible(false);
								btnCerrar.setVisible(true);
								btnSiguiente.setEnabled(true);
								btnAnterior.setEnabled(true);
								txtApellidos.setText("Apellidos");
								txtNombre.setText("Nombre");
								txtDNI.setText("Num DNI");
								txtEdad.setText("Edad");
								for(int i=0 ;i<getContentPane().getComponentCount();i++){
									if(getContentPane().getComponent(i).getClass()==JTextField.class){
										((JTextField)getContentPane().getComponent(i)).setEnabled(false);
									}
								}
							}
						}
						else{
							JOptionPane.showMessageDialog(this, "La edad no puede ser negativa","Benjamin Button",JOptionPane.WARNING_MESSAGE);
						}
					}
					catch (NumberFormatException e2){
						JOptionPane.showMessageDialog(this, "Solo numeros para la edad,gracias","Problemas de edad",JOptionPane.WARNING_MESSAGE);
					}					
				}
				catch(FormatterClosedException e3){
					JOptionPane.showMessageDialog(this, "Formato DNI no valido",
							"Error con el DNI",JOptionPane.WARNING_MESSAGE);
				}
			}	
			
		}
		if(e.getSource()==btnCerrar){
			System.exit(0);
		}
		if(e.getSource()==btnAnterior){
			try{
				cont--;
				txtApellidos.setText(collection.get(cont).getApellido());
				txtDNI.setText(collection.get(cont).getDNI());
				txtEdad.setText(String.format("%d", collection.get(cont).getEdad()));
				txtNombre.setText(collection.get(cont).getNombre());				
			}
			catch(IndexOutOfBoundsException e3){
				cont++;
				JOptionPane.showMessageDialog(this, "No hay elemento anterior",
						"Elemento no Disponible",JOptionPane.WARNING_MESSAGE);
			}
		}
		if(e.getSource()==btnSiguiente){
			try{
				cont++;
				txtApellidos.setText(collection.get(cont).getApellido());
				txtDNI.setText(collection.get(cont).getDNI());
				txtEdad.setText(String.format("%d", collection.get(cont).getEdad()));
				txtNombre.setText(collection.get(cont).getNombre());				
			}
			catch(IndexOutOfBoundsException e3){
				cont--;
				JOptionPane.showMessageDialog(this, "No hay elemento siguiente",
						"Elemento no Disponible",JOptionPane.WARNING_MESSAGE);
			}
		}
		if(e.getSource()==btnCancelar){
			btnAñadir.setVisible(true);
			btnAceptar.setVisible(false);
			btnCancelar.setVisible(false);
			btnCerrar.setVisible(true);
			btnSiguiente.setEnabled(true);
			btnAnterior.setEnabled(true);
			txtApellidos.setText("Apellidos");
			txtNombre.setText("Nombre");
			txtDNI.setText("Num DNI");
			txtEdad.setText("Edad");
			for(int i=0 ;i<getContentPane().getComponentCount();i++){
				if(getContentPane().getComponent(i).getClass()==JTextField.class){
					((JTextField)getContentPane().getComponent(i)).setEnabled(false);
				}
			}
		}
		if(e.getSource()==btnVisualizar){
			String mostrar="";
			if(collection.size()>0){
				mostrar+=collection.get(0).Cabecera();
				for(int i=0;i<collection.size();i++){
					mostrar+=collection.get(i).devulveDatos();
				}
				JOptionPane.showMessageDialog(this, mostrar,"The Collection",JOptionPane.INFORMATION_MESSAGE);
			}
			else{
				JOptionPane.showMessageDialog(this, "No hay personas en la base de datos","The Collection",JOptionPane.WARNING_MESSAGE);
			}
		}
		
	}	

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == sexMasc) {
			EnumSexo = EnumSexo.MASCULINO;
		}
		if (e.getSource() == sexFem) {
			EnumSexo = EnumSexo.FEMENINO;
		}
	}
}


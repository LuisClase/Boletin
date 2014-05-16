package ejercicio2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FrameEjer2 extends JFrame implements ActionListener{

	//Propiedades
	private JButton btnSeleccionar;
	private JTextArea txtTexto;
	private JTextArea txtContenido;
	private JLabel	lblImagen;
	private JScrollPane scrollContenido;
	private JScrollPane scrollTexto;
	private JScrollPane scrollImagen;
	
	//Constructores
	
	public FrameEjer2(){
		super("Frame Ejer 2");
		this.setLayout(null);
		//btnSeleccionar
		btnSeleccionar=new JButton("Seleccionar");
		btnSeleccionar.setSize(btnSeleccionar.getPreferredSize());
		btnSeleccionar.setLocation(10,10);
		btnSeleccionar.addActionListener(this);
		add(btnSeleccionar);
		//txtTexto
		txtTexto=new JTextArea("Texto");
		txtTexto.setEditable(false);
		//ScrollTexto
		scrollTexto=new JScrollPane(txtTexto);
		scrollTexto.setSize(150,150);
		scrollTexto.setLocation(10, 20+btnSeleccionar.getHeight());
		add(scrollTexto);
		//lblImagen
		lblImagen=new JLabel("Imagen");
		//ScrollImagen
		scrollImagen=new JScrollPane(lblImagen);
		scrollImagen.setSize(300,300);
		scrollImagen.setLocation(170,scrollTexto.getY());
		add(scrollImagen);
		//txtContenido
		txtContenido=new JTextArea("Contenido Texto");
		txtContenido.setEditable(false);
		//ScrollTexto
		scrollTexto=new JScrollPane(txtContenido);
		scrollTexto.setSize(150,150);
		scrollTexto.setLocation(10, 200);
		add(scrollTexto);
		
	}
	//Metodos

	@Override
	public void actionPerformed(ActionEvent e) {
		int respuesta;
		FileNameExtensionFilter filtro=new FileNameExtensionFilter("Imagenes", "jpg","jpeg","gif","png");
		FileNameExtensionFilter filtroTexto=new FileNameExtensionFilter("Texto", "txt");
		JFileChooser fc=new JFileChooser();
		fc.addChoosableFileFilter(filtro);
		fc.addChoosableFileFilter(filtroTexto);
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		respuesta=fc.showOpenDialog(this);
		if(respuesta==JFileChooser.APPROVE_OPTION){
			if(fc.getSelectedFile().getName().endsWith(".txt")){				
				try{
					String archivo=fc.getSelectedFile().getPath();
					Scanner f=new Scanner(new File(archivo));
					txtContenido.setText("");
					while(f.hasNext()){
						txtContenido.setText(txtContenido.getText()+f.nextLine());
						txtContenido.setText(String.format(txtContenido.getText()+"\n"));
					}
					f.close();
				}
				catch(Exception e2){
					System.err.println("Error de acceso al archivo: "+e2.getMessage());
				}
			}
			else if(fc.getSelectedFile().getName().endsWith(".jpg") ||
					fc.getSelectedFile().getName().endsWith(".jpeg") ||
					fc.getSelectedFile().getName().endsWith(".gif") ||
					fc.getSelectedFile().getName().endsWith("png")){
				txtContenido.setText("");
				txtTexto.setText("");
				lblImagen.setText("");
				lblImagen.setIcon(new ImageIcon(fc.getSelectedFile().getPath()));	
			}
			else{
				if(fc.getSelectedFile().exists()){
					txtTexto.setText(String.format("Informacion\n" +
							"Nombre: "+fc.getSelectedFile().getName()+
							"\nTrayectoria: "+fc.getSelectedFile().getPath()+
							"\nTamaño(kb): %.3f"+
							"\n%s leer"+"\n%s escribir"+
							"\n%s ejecutar", fc.getSelectedFiles().length/1024f,
							fc.getSelectedFile().canRead() ? "Se puede":"No se puede",
									fc.getSelectedFile().canWrite() ? "Se puede":"No se puede",
											fc.getSelectedFile().canExecute() ? "Se puede":"No se puede"));
					try{
						RandomAccessFile FileIn=new RandomAccessFile(fc.getSelectedFile(), "r");
						txtContenido.setText("");
						for(int i=0;i<FileIn.length();i++){
							txtContenido.setText(String.format( txtContenido.getText()+"%x",FileIn.readByte()));
							if(i%5==0){
								txtContenido.setText(txtContenido.getText()+String.format("%n"));
							}
						}
					}
					catch(FileNotFoundException e2){
						System.err.println("Archivo no encontrado:"+e2.getMessage());
					}
					catch (IOException e2) {
						System.err.println("Error en fileIN read byte "+e2.getMessage());
					}
				}
				else{
					txtTexto.setText("Archivo no encontrado");
				}
			}
		}
		
	}

}

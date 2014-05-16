package ejercicio1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class FrameEjer1 extends JFrame implements ActionListener{

	//Propiedades
	private JTextField txtOrigen;
	private JTextField txtDestino;
	private JButton btnCopiar;
	private JButton btnMover;
	private JButton btnBorrar;
	private JLabel lblInformacion;
	
	//Constructor
	public FrameEjer1(){
		super("FrameEjer1");
		this.setLayout(null);
		//txtOrigen
		txtOrigen= new JTextField("Path origen");
		txtOrigen.setSize(200,20);
		txtOrigen.setLocation(10, 10);
		add(txtOrigen);
		//txtDestino
		txtDestino= new JTextField("Path destino");
		txtDestino.setSize(200,20);
		txtDestino.setLocation(10, 40);
		add(txtDestino);
		//btnCopiar
		btnCopiar=new JButton("Copiar");
		btnCopiar.setSize(100, 30);
		btnCopiar.setLocation(10, 70);
		btnCopiar.addActionListener(this);
		add(btnCopiar);
		//btnmover
		btnMover= new JButton("Mover");
		btnMover.setSize(100,30);
		btnMover.setLocation(120,70);
		btnMover.addActionListener(this);
		add(btnMover);
		//btnborrar
		btnBorrar= new JButton("Borrar");
		btnBorrar.setSize(100,30);
		btnBorrar.setLocation(10, 110);
		btnBorrar.addActionListener(this);
		add(btnBorrar);
		//lblInformacion
		lblInformacion=new JLabel("Informacion");
		lblInformacion.setSize(150,30);
		lblInformacion.setLocation(120, 110);
		add(lblInformacion);
	}
	
	//Metodos

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnMover || e.getSource()==btnCopiar){
			String path=txtOrigen.getText();
			String destino=txtDestino.getText();
			String texto="";
			try{
				File f=new File(path);
				File f2=new File(destino+"/"+f.getName());
				String temp=destino+"/"+f.getName();
				//path.equals(temp)
				if(f2.exists()){
					System.err.println(path);
					System.err.println(destino+"/"+f.getName());
					JOptionPane.showMessageDialog(this, "Sobreescritura detectada,no se puede realizar la accion"
							,"Error",JOptionPane.WARNING_MESSAGE);
				}
				else{
					System.err.println(path);
					System.err.println(destino+"/"+f.getName());
					if(f.exists()){
						RandomAccessFile fileIn=new RandomAccessFile(f,"r");
						RandomAccessFile fileOut= new RandomAccessFile(destino+"/"+f.getName(), "rw");			
						for(int i=0;i<fileIn.length();i++){
							fileOut.writeByte(fileIn.readByte());
						}
						if(e.getSource()==btnMover){
							f.delete();
							lblInformacion.setText("Movido");
						}
						else{
							lblInformacion.setText("Copiado");
						}
					}
					else{
						lblInformacion.setText("No existe");
					}
				}				
			}
			catch(Exception e2){
				lblInformacion.setText("Error");
				System.err.println(e2.getMessage());
			}
		}
		if(e.getSource()==btnBorrar){
			File f=new File(txtOrigen.getText());
			if(f.exists()){
				int opcion=JOptionPane.showConfirmDialog(this, "Esta seguro que desea eliminar el archivo?", "¿Eliminar?", JOptionPane.YES_NO_OPTION);
				if(opcion==JOptionPane.YES_OPTION){
					if(!f.delete()){
						JOptionPane.showMessageDialog(this, "No se ha podido borrar","No se pudo borrar",JOptionPane.WARNING_MESSAGE);
					} else
						lblInformacion.setText("Eliminado");
				}
				
			}
			else{
				JOptionPane.showMessageDialog(this, "No existe ese archivo","No existe",JOptionPane.WARNING_MESSAGE);
			}			
		}
	}
}

package ejercicio4;

import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class FrameEjer4 extends JFrame implements ActionListener{

	//Propiedades
	
	private JButton btnCargar;
	private JButton btnCrear;
	private JTextArea txtTexto;
	private JScrollPane scrollTexto;
	private JLabel lblMedia;
	
	//Constructores
	
	public FrameEjer4(){
		super("Ejercicio4");
		this.setLayout(null);
		//btnCargar
		btnCargar=new JButton("Cargar");
		btnCargar.setLocation(10, 10);
		btnCargar.setSize(100,30);
		btnCargar.addActionListener(this);
		add(btnCargar);
		//btnCrear
		btnCrear=new JButton("Crear");
		btnCrear.setLocation(120, 10);
		btnCrear.setSize(100,30);
		btnCrear.addActionListener(this);
		add(btnCrear);
		//txtTexto
		txtTexto=new JTextArea("");
		txtTexto.setEditable(false);
		//scrollTexto
		scrollTexto=new JScrollPane(txtTexto);
		scrollTexto.setLocation(10,50);
		scrollTexto.setSize(210,200);
		add(scrollTexto);
		
	}
	
	//Metodos
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	

}

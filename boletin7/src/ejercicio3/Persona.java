package ejercicio3;

import java.util.FormatterClosedException;

enum sexo{
	MASCULINO,FEMENINO,SEXO
}

public class Persona {

	//Propiedades
	
	private String nombre;
	private String apellido;
	private String DNI;
	private sexo sexo1;
	private Integer edad;
	
	//Constructores
	
	public Persona(String nombre,String apellido,
			String DNI,sexo sexo1,int edad){
		this.setNombre(nombre);
		this.setApellido(apellido);
		this.setDNI(DNI);
		this.setSexo1(sexo1);
		this.setEdad(edad);
	}
	public Persona(){
		this("","","",sexo.SEXO,0);
	}
	
	//Metodos
	public String numeroDNI(){
		String numeros = "";
		//System.err.println("numeroDNI:"+this.getDNI());
		//System.err.printf("%d\n",this.getDNI().length());
		if ((this.getDNI().length() == 9)	|| (this.getDNI().length() == 11 && this.getDNI().charAt(2) == '.' && this.getDNI().charAt(6) == '.')) {
			//System.out.println("numeroDNI if");
			for (int i = 0; i < this.getDNI().length(); i++) {
				//System.err.println("numeroDNIañadir for");
				if (this.getDNI().charAt(i) >= '0' && this.getDNI().charAt(i) <= '9') {
					//System.err.println("numeroDNIañadir letra");
					numeros+= this.getDNI().charAt(i);
				} 
			}
		}
		//System.out.println("Numeros:"+numeros);
		return numeros;
	}
	public String Cabecera(){
		return String.format("%20s%20s%12s%11s%12s\n", "Nombre","Apellidos","DNI","Sexo","Edad");
	}
	
	public String devulveDatos(){
		return String.format("%20s%20s%11s%11s%4d\n", this.getNombre(),this.getApellido(),this.getDNI(),this.getSexo1().toString(),this.getEdad());
	}
	
	/**
	 * funciopn para descubrir la letra del dni
	 * @param DNI dni del que se quiere averiguar la letra
	 * @return letra del dni
	 * @throws FormatterClosedException
	 */
	public static char letraDNI(String DNI) throws FormatterClosedException{		
		if (DNI != null && (DNI.length() == 8 || DNI.length() == 10)) {
			String cadena = "TRWAGMYFPDXBNJZSQVHLCKE";
			char letra = ' ';
			String numeros = "";
			int resto = 0;
			if ((DNI.length() == 8)
					|| (DNI.length() == 10 && DNI.charAt(2) == '.' && DNI
							.charAt(6) == '.')) {
				for (int i = 0; i < DNI.length(); i++) {
					if (DNI.charAt(i) >= '0' && DNI.charAt(i) <= '9') {
						numeros += DNI.charAt(i);
					} else if (i != 2 && i != 6) {
						throw new FormatterClosedException();
					}
				}
				resto = Integer.parseInt(numeros) % 23;
				letra = cadena.charAt(resto);
			} else {
				throw new FormatterClosedException();
			}
			return letra;
		} else {
			throw new FormatterClosedException();
		}
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public sexo getSexo1() {
		return sexo1;
	}

	public void setSexo1(sexo sexo1) {
		this.sexo1 = sexo1;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}
	
}

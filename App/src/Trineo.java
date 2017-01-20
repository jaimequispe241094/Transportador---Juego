import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Trineo {
	double x;
	double y;
	double angulo;
	double alto;
	double ancho;
	boolean giro=true;
	Image imagen;
	Image saludo;
	
	Trineo(double x, double y,double angulo,double alto,double ancho){
		this.x=x;
		this.y=y;
		this.angulo=angulo;
		this.alto=alto;
		this.ancho=ancho;
		this.imagen=Herramientas.cargarImagen("trineo.gif");
		this.saludo=Herramientas.cargarImagen("saludo.gif");
		}

	public void desplazar(){        
		if (giro==true) 
			this.x+=10;                 //se desplaza el trineo controlando que no se vaya de pantalla
		else{this.x-=10;}
		
		if (this.x<65)
			giro=true;
		if (this.x>740)
			giro=false;			
	}
	public void Mover(Entorno ent){
		if(ent.estaPresionada(ent.TECLA_INICIO))	//desplazamiento del trineo mientras este presione la tecla inicio
			desplazar();
	}
	
	public void dibujar(Entorno ent,Regalo r){
	if (r==null)
		ent.dibujarImagen(saludo, x, y-30, 0,0.7);     //papa noel con los regalos
	else
		ent.dibujarImagen(imagen, x+7, y-20, 0,0.6);  // trineo
		
	}
	


}

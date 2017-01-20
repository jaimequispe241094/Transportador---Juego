import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Cañon {
	double x;
	double y;
	double alto;
	double ancho;
	Image cañon;
	
	Cañon(double x, double y,double alto,double ancho){
		this.x=x;
		this.y=y;
		this.ancho=ancho;
		this.alto=alto;
		this.cañon=Herramientas.cargarImagen("2000.png");
	}
	
	public void DesplazarCañon(Entorno ent){
		if(ent.estaPresionada(ent.TECLA_IZQUIERDA)){       //se desplaza cañon ala izquierda 
			if (this.x>30)								//se controla que no se vaya de pantalla
				this.x-=5;}	
		
		if(ent.estaPresionada(ent.TECLA_DERECHA)){				//se desplaza cañon ala izquierda
			if (this.x<775)										//se controla que no se vaya de pantalla
				this.x+=5;}									
	}
	
	public void Dibujar(Entorno ent,boolean bala){
		ent.dibujarImagen(this.cañon,this.x+5, this.y,0,0.8);			//cañon
		if(bala==true){						
		ent.dibujarCirculo(x+9, y-23,21, Color.GREEN);}				//municion que tiene cargada
		else{ent.dibujarCirculo(x+9, y-23,21, Color.RED); }
		
	}
}

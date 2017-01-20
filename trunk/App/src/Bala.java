import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;


public class Bala {
	
	double x;
	double y;
	double angulo;
	double alto;
	double ancho;
	boolean disparo;
	double altoPlat=0;
	double ancho1=0;
	double ancho2=0;
	double anchoBala1=0;
	double anchoBala2=0;
	Image rojo;
	Image verde;
	
	Bala(double X, double Y, double Angulo, double alto, double ancho,boolean disparo) {
		
		this.x = X;
		this.y = Y;
		this.angulo = Angulo;
		this.alto=alto;
		this.ancho=ancho;
		this.disparo=disparo;
		this.rojo = Herramientas.cargarImagen("rojo.png");
		this.verde= Herramientas.cargarImagen("verde.png");
	}
 

	public void seDispara(){        //disparo de bala
		this.y -=10;
		}
	
	boolean Impacta(double x,double y,double alto,double ancho){		//  metodo PROPIO de la bala para controlar  
			 this.altoPlat=y+alto/2;									//  si impacta con algun objeto (plataforma, regalo)
			 this.ancho1=x-ancho/2;
			 this.ancho2=x+ancho/2;										// se controlo todos los bordes de la bala	
			 anchoBala1=this.x-(this.ancho/2);							// con los boredes de un objeto 
			 anchoBala2=this.x+(this.ancho/2);
			 if(altoPlat>this.y-(this.alto/2)){
				 if((anchoBala1>=ancho1 && anchoBala1<=ancho2) || (anchoBala2>=ancho1 && anchoBala2<=ancho2)){
						 return true;} 
				 	}
		 	return false;
	}
		
	public void dibujar(Entorno ent,boolean disparo) {
		if(this.disparo==true){
			ent.dibujarImagen(this.verde, x, y,0,0.2);} //bala magnetica
		else{
			ent.dibujarImagen(this.rojo, x, y,0,0.16);}	//bala destructiva
	}
}

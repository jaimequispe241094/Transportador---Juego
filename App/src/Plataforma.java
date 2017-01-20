import java.awt.Image;
import java.util.Random;
import entorno.Entorno;
import entorno.Herramientas;

public class Plataforma {

	double x;
	double y;
	double angulo;
	double alto;
	double ancho;
	boolean Direccion=true;
	boolean dir=true;
	Image imagenDerec;
	Image imagenIzquier;
	
	Plataforma(double x, double y,double angulo,double alto,double ancho){
		Random gen = new Random();
		this.x=x;
		this.y=y;
		this.angulo=angulo;
		this.alto=alto;
		this.ancho=ancho;
		this.Direccion=gen.nextBoolean();
		this.imagenDerec=Herramientas.cargarImagen("flechas derecha.png");
		this.imagenIzquier=Herramientas.cargarImagen("flechas izquierda.png");
		
		}
	 
	public static void CrearPlataforma(Plataforma[] pla1){           //se crean todas las plataformas
		Random gen=new Random();
		int a=130;
		int c=160+gen.nextInt(80);
		int d=gen.nextInt(80);
		for(int i=0;i<pla1.length;i++) {
			if (i<=2 ){
				pla1[i] = new Plataforma(a,100+(d/4),0.0,50,180);
				a=a+240+(d/2);}
			if (i==3 || i==4 ){
				pla1[i]=new Plataforma(c,185+(d/2),0.0,50,180);
				c+=345;
				a=120;}
			if (i==6 || i==7 || i==5){
				pla1[i]=new Plataforma(a+(d/2),280+(d/2),0.0,50,180);
				a+=250;
				c=190;}
			if (i==8 || i==9){
				pla1[i]=new Plataforma(c+d,400,0.0,50,190);
				c+=350;}
		}
	}
	
	public void dibujar(Entorno ent){
		if(this.Direccion==true){
			ent.dibujarImagen(this.imagenDerec, this.x, this.y, this.angulo, 0.23); //plataforma direccion derecha
			}
		else{
			ent.dibujarImagen(this.imagenIzquier, this.x, this.y, this.angulo,0.22); //plataforma direccion izquierda
		}}
	

}

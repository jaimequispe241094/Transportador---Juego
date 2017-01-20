import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Regalo {
	double x;
	double y;
	double angulo;
	double alto;
	double ancho;
	Image imagen;
	double altotri=0;
	double anchotri1=0;
	double anchotri2=0;
	double anchoreg1;
	double anchoreg2;
	
	Regalo(double x, double y,double angulo,double alto,double ancho){
		this.x=x;
		this.y=y;
		this.angulo=angulo;
		this.alto=alto;
		this.ancho=ancho;
		this.imagen=Herramientas.cargarImagen("regalos.gif");
	}

	public void caeRegalo(){					//cae el regalo hacia abajo
		this.y+=1;
	}

	public void seDezplasa (Plataforma pla){
		if(pla.Direccion==true){
			if(this.x+this.ancho/2>=0 && this.x+this.ancho/2<=800){    //se desplaza por la plataforma hacia la derecha
					this.x+=1;}	
		}
		else {
			if(this.x-this.ancho/2>=0 && this.x-this.ancho/2<=800){ 	//se desplaza por la plataforma hacia la izquierda
				this.x-=1;}
		}
	}
	

	int CaeEnTrineo(Trineo trineo){          
		this.altotri=trineo.y-(trineo.alto/2);
		this.anchotri1=trineo.x-(trineo.ancho/2);        //bordes de el regalo y el trineo
		this.anchotri2=trineo.x+(trineo.ancho/2);
		this.anchoreg1=this.x-(this.ancho/2);
		this.anchoreg2=this.x+(this.ancho/2);
		
		if(altotri<this.y && this.y<trineo.y){   //esta completamente dentro del trineo
				if((anchoreg1>=anchotri1 && anchoreg1<=anchotri2) && (anchoreg2<=anchotri2 &&anchoreg2>=anchotri1)){
						return 1;}}
		if(altotri<this.y+(this.alto/2)){    //toco el trineo pero no entro completamente
				if((anchoreg1>=anchotri1 && anchoreg1<=anchotri2) && (anchoreg2>=anchotri2) ||(anchoreg2>=anchotri1 && anchoreg2<=anchotri2) && (anchoreg1<=anchotri1)){
				 	return 2;
				}
		}
		return 3;							//cayo fuera del trineo
	}
	
	boolean TocaPlataforma(Plataforma pla){     
			 if(pla.y-pla.alto/2<(this.y+this.alto/2) && this.y+this.alto/2<pla.y){
				 if((this.x-this.ancho/2>=pla.x-pla.ancho/2 && this.x-this.ancho/2<=pla.x+pla.ancho/2) || (this.x+this.ancho/2>=pla.x-pla.ancho/2 && this.x+this.ancho/2<=pla.x+pla.ancho/2)){
						 return true;}   //toco la plataforma
				 	}
			 
		 	return false;			//no toco la plataforma
	}

	public void dibujar(Entorno ent){
	ent.dibujarImagen(imagen, this.x, this.y,-0.22,0.21);
	}
}

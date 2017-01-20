import java.awt.Color;
import java.awt.Image;
import java.util.Random;
import javax.sound.sampled.Clip;
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego{
	 
private Entorno entorno; 
private Cañon cañon;
private Regalo regalo;
private Regalo [] fe;
private Plataforma[] plataforma;
private int can=10;
private Trineo trineo;
private Trineo noel;
private Bala bala;
int auxiliar=40;
private Image fondo;
private Image fin;
boolean tiro=false;
boolean permiso=false;
boolean a=true;
boolean b=true;
Clip tono; 
boolean star=false;
Image inicio;
	 
	 Juego(){
		this.entorno = new Entorno(this,"El Transportador",800, 600);
		Random gen = new Random();
		int pos=gen.nextInt(650);
		int pos2=gen.nextInt(600);
		this.cañon=new Cañon(400,530,90,40);
		this.regalo=new Regalo(pos2+40,-300,0,40,65);
		this.trineo=new Trineo(pos+80,540,0.0,75,125);
		this.noel=new Trineo(900,320,0,70,130);
		this.plataforma=new Plataforma[this.can];
		Plataforma.CrearPlataforma(plataforma);
		this.fe=new Regalo[10];
		for(int i=0;i<fe.length;i++){
			this.fe[i]=new Regalo(auxiliar,-auxiliar,0,40,40); 
			auxiliar+=80;}
		
		this.fondo = Herramientas.cargarImagen("arbol.gif");
		this.fin= Herramientas.cargarImagen("gano.gif");
		this.inicio= Herramientas.cargarImagen("01.gif");
		this.tono=Herramientas.cargarSonido("tono.wav");
		}
	
	 public void tick(){
		this.tono.loop(5);
	
		if(this.star==false){	
		this.entorno.dibujarImagen(this.inicio,400,300,0,1.3);  	//menu con controles del juego
		entorno.cambiarFont("Rockwell Extra Bold",50, Color.BLUE);
		entorno.escribirTexto("EL TRANSPORTADOR",100, 100);
		entorno.cambiarFont("Rockwell Extra Bold",27, Color.YELLOW);
		entorno.escribirTexto("CONTROLES: ",0, 200);
		entorno.escribirTexto("DISPARAR= TECLA_ENTER",0, 250);
		entorno.escribirTexto("CARGAR MUNICION DESTRUCTIVA= TECLA_DELETE",0,300);
		entorno.escribirTexto("CARGAR MUNICION MAGNETICA= TECLA_INSERT",0,350);
		entorno.escribirTexto("DESPLAZAR TRINEO= TECLA_INICIO",0,400);
		entorno.escribirTexto("DESPLAZAR CAÑON= TECLA_IZQ Y TECLA_DER",0,450);
		entorno.cambiarFont("Rockwell Extra Bold",20, Color.MAGENTA);
		entorno.escribirTexto("PRESIONE LA TECLA-ESPACIO PARA COMENZAR A JUGAR",110,530);}
		
		if(entorno.estaPresionada(entorno.TECLA_ESPACIO))  //comienza el juego
			this.star=true;
		
		if(this.star==true){ 
		this.entorno.dibujarImagen(this.fondo,400,300,0,3.1);
		if (entorno.estaPresionada(entorno.TECLA_ENTER)&& permiso==true && bala==null){ //dispara la bala cargada
			this.bala=new Bala(this.cañon.x,this.cañon.y,0.0,40,15,a);				
			tiro=true;}

		if(entorno.estaPresionada(entorno.TECLA_INSERT)&& tiro==false){ //se carga bala magnetica
			a=true;											
			permiso=true;}
		
		if(entorno.estaPresionada(entorno.TECLA_DELETE)&& tiro==false){
			a=false;													 //se carga bala destructiva
			permiso=true;}
		
		if(tiro==true && permiso==true){
			if(this.bala!=null){
				this.bala.seDispara();				//se ejecuta el disparo 
				this.bala.dibujar(entorno,a);
			}
		}
		
		for(int i=0;i<this.plataforma.length;i++){ //diferentes impactos de la bala con la plataforma
			if (plataforma[i]!=null && bala!=null){
				if(this.bala.Impacta(this.plataforma[i].x,this.plataforma[i].y,this.plataforma[i].alto,this.plataforma[i].ancho)){ //si controla si la impacta
					if( a==false){
						this.plataforma[i]=null;		//se destruye la plataforma		
						this.bala=null;							
						tiro=false;						
						b=true;}
					else{
						if(this.plataforma[i].Direccion){
							this.plataforma[i].Direccion=false; //se cambia el sentido de la plataforma(der-izq)
							this.bala=null;
							tiro=false;	}
						else {
							this.plataforma[i].Direccion=true;	//se cambia el sentido de la plataforma(izq-der)
							this.bala=null;						
							tiro=false;}
					}}
				else{
					if(this.bala.y<0){
						tiro=false;					//si la bala se va de pantalla sin impactar con nada desaparece			
						bala=null;}}	
			}}
			
		if(this.regalo!=null){          //movimiento del regalo
				Random num=new Random();
				if (b==true){
					this.regalo.caeRegalo();}		//cae hacia bajo si no toca ninguna plataforma
				for(int i=0;i<this.plataforma.length;i++){
					if(this.plataforma[i]!=null){
						if (this.regalo.TocaPlataforma(this.plataforma[i])){  //si toca una plataforma se desplaza en el sentido de la plataforma  
							b=false;										// y deja de caer			
							this.regalo.seDezplasa(plataforma[i]);       
							if (!this.regalo.TocaPlataforma(this.plataforma[i])) //si deja de hacer contacto con esta , sigue cayendo
									b=true;
						}
					}
				}
				this.regalo.dibujar(entorno);
				if(this.regalo.CaeEnTrineo(trineo)==1){   
					this.regalo=null;}         					//el regalo cae dentro del trineo 
				else if(this.regalo.CaeEnTrineo(trineo)==2){
					this.regalo.y=-50;						   //no entra por completo 
					this.regalo.x=num.nextInt(700)+50;}			// vuelve caer desde arriba posicionado aleatoriamente
				else{ 
					if(this.regalo.y>640){
						this.regalo.y=-50;					//el regalo cae fuera del trineo 
						this.regalo.x=num.nextInt(700)+50;}	 //vuelve caer desde arriba posicionado aleatoriamente
				}
			}
			
		if (this.bala!=null && regalo!=null){   //la bala desaparece si impacta el regalo 
			if (this.bala.Impacta(this.regalo.x,this.regalo.y,this.regalo.alto,this.regalo.ancho)){
				this.bala=null;
				tiro=false;}                }
		
		this.trineo.Mover(entorno);
		this.trineo.dibujar(entorno,regalo);		//se dezplaza(usuario) y dibuja tanto el trineo como el cañon
		this.cañon.DesplazarCañon(entorno);
		this.cañon.Dibujar(entorno,a);
		
		for (int i=0;i<this.plataforma.length;i++){
			 if (plataforma[i]!=null)                           //se dibujan las plataformas
						 this.plataforma[i].dibujar(entorno);}

		if (this.regalo==null){
				this.entorno.dibujarImagen(this.fin,entorno.ancho()/2,entorno.alto()/2, 0,1.43);
				for(int i=0;i<fe.length;i++){
						if(fe[i].y<580)		   //lluvia de regalos!
							fe[i].caeRegalo();
						fe[i].dibujar(entorno);}
				entorno.cambiarFont("Ravie",80, Color.BLUE);
				entorno.escribirTexto("GANASTE!!",150, 250);  
				entorno.cambiarFont("Ravie",60, Color.GREEN);   //se felicita al usuario por haber ganado
				entorno.escribirTexto("FELICITACIONES!!",90, 390);
				this.noel.dibujar(entorno,regalo);
					this.noel.x-=1.4;							//papa noel se lleva los regalos
					if(this.noel.x<-100)
						this.noel.x=900;}
		}
	}

		@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
		}
}


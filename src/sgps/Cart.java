package sgps;

//import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.sql.*;
import java.awt.image.*;
import javax.swing.*;

//import com.borland.jbcl.layout.*;
import javax.swing.border.*;

/**
 *
 * <p>Titre : Structure d'une Carte </p>
 * <p>Description : Structure permetant de geree une carte avec ses parametres pour GPS.
 *    elle integre les info qui permetre la syncronisation entre la carte et la tajectoire a afficher.</p>
 * <p>Copyright : Copyright (c) 28.5.2003</p>
 * <p>Société : NewTec</p>
 * @author Chouba Nabil &Nizar Grame
 * @version 1.0
 */

class Cart{

  /**
   * chaque replique est composer de nombre de bloc en largeur et langeur
   *   0 -> 10% ,1 -> 25% ,2 -> 50% ,3 -> 100%*/
  int largZoomingBloc[]=new int[10];

   /**chaque replique est composer de nombre de bloc en largeur et langeur*/
  int longgZoomingBloc[]=new int[10];

  /*valeur de Zoom des repliqueutiliser*/
  int ValeurZoomBloc[]={10,25,50,100};
  /**
   *marque tous les bloc scaler pour une nouvelle scallage
   */
  void setReScall(){
    for ( int x=0;x<largeur;x++)
      for (int y=0;y<hauteur;y++)
        if (BolcForPaint[x][y] ==3) {BolcForPaint[x][y]=2;ImageScaller[x][y]=null;}//a rescaller
    System.gc();System.gc();
  }
  /**
   *libere tous les bloc d'image et les marque par 0 ne sont pas charger c'est une sorte d'reinisalisation
   * */
  void setReLoadl(){
    for ( int x=0;x<largeur;x++)
      for (int y=0;y<hauteur;y++)
      {
        BolcForPaint[x][y]=0;//a reloader
        ImageOriginal[x][y]=null;
        ImageScaller[x][y]=null;
      }
      System.gc();System.gc();
  }

int indiceZoomActuelle=0;
  /**
   *
   * reinisalise la carte pour l'utilisation d'une autre replique
   * @param indiceReplique indice de la replique qui est introduit dans le tableux ValeurZoomBloc
   */
  void setNewDuplic(int indiceReplique){
     setReLoadl();

     largeur =largZoomingBloc[indiceReplique];
     hauteur =longgZoomingBloc[indiceReplique];
     indiceZoomActuelle=indiceReplique;


     PointPosCartX=(int) (PointPosCartXOriginal *100/40 /(100/ValeurZoomBloc[indiceReplique]));
     PointPosCartY=(int) (PointPosCartYOriginal *100/40  /(100/ValeurZoomBloc[indiceReplique]));

     //metre a lechelle de la replique pour optimiser
     echellCart      = 0.6381445330849965 * 40/100 * (100/ValeurZoomBloc[indiceReplique]);

  }

  /**nom de la carte */
  String Name;

  /**nom ficher de basse */
  String jpgFile;

  /**nombre largeur blooc  */
  int largeur;

  /**nombre hauteur blooc  */
  int hauteur;

  /**nombre largeur blooc  */
  int largeurBloc;

  /**nombre hauteur blooc  */
  int hauteurBolc;
  /**position dans l'ecron du point de reference pour tracer des point*/
  Point ReferencePoint;

  /**elle depend de la replique position (0x)dans l'ecron de la carte % point dorigine fixer par calage*/
  long PointPosCartX;

  /**elle depend de la replique  position (0y)dans l'ecron de la carte % point dorigine fixer par calage*/
  long PointPosCartY;

  /**position (0x)dans l'ecron de la carte % point dorigine fixer par calage*/
   long PointPosCartXOriginal;

   /**position (0y)dans l'ecron de la carte % point dorigine fixer par calage*/
  long PointPosCartYOriginal;
  /**position dans l'ecron du point de reference pour tracer des point Zoomer*/
  Point RPZoomer;

  /**degree Latitude du point du reference*/
  double degreeLatitude;

  /**degree Longitude du point du reference*/
  double degreeLongitude;

  /**echelle de la carte a afficher*/
  double echellCart;

  /**contient les blocs d'image original charger en memoire*/
  Image ImageOriginal[][];

  /**contient les blocs d'image Scaller charger en memoire*/
  Image ImageScaller[][];

  /**c'est le marckage des blocs pour l'affichage selon un algorithme de markage
   * 0 - elle n est pas dans le chant de vision
   * 1 - marquer pour le chargenemt from the disk
   * 2 - elle est charger en memoire et sa taille est originale
   * 3 - elle est scaller et pres pour l'affichage
   * */
  int BolcForPaint[][];
/**
 * @param name nom de la carte qlq
 * @param JpgFile non du ficher de basse des bolcs
 * @param Largeur nombre de bolc en Largeur
 * @param Hauteur nombre de bolc en Hauteur
 * @param indiceZoomSelected indice de la Zoom a utiliser
 * */

  Cart(String name,String JpgFile,int Largeur,int Hauteur,int indiceZoomSelected){
    Name=name;
    jpgFile=JpgFile;
    largeur=Largeur;
    hauteur=Hauteur;

   ImageOriginal = new Image[35][35];
   ImageScaller  = new Image[35][35];
   BolcForPaint  = new int[35][35];
   //EcranVisible= new boolean[35][35];

    indiceZoomActuelle=indiceZoomSelected;
 }
 /**
  * elle charge les image de la boucle dans leur taille original
  *
  * @param comp elle permet de optenire le chemain des blocs d'image
  *
  */

  void chargeurImage(JFrame comp){
    int imageWaiter=0;
    String sX,sY;
    MediaTracker tracker = new MediaTracker(comp);
    for (int x=0;x<largeur;x++)
      for (int y=0;y<hauteur;y++)
      {

        if (BolcForPaint[x][y]==1) {
          if (x<10) sX="0"+x;else sX=""+x;
          if (y<10) sY="0"+y;else sY=""+y;
          ImageOriginal[x][y]=comp.getToolkit().getImage(
          ""
              +ValeurZoomBloc[indiceZoomActuelle]+""+jpgFile+sX+""+""+sY+".jpg");
          tracker.addImage(ImageOriginal[x][y], imageWaiter++);
          BolcForPaint[x][y]=2;
        }
      //  else {if (BolcForPaint[x][y]==0) ImageOriginal[x][y]=null;}
      }
      try {tracker.waitForAll();}
      catch(InterruptedException e) {
        System.out.println("erreur nabil load image ");
      }
      tracker=null;
  }
/**
 * elle permet de faire la Zoom corespantante a limage original
 * dejat chatger dans ImageOriginal
 *
 * @param ZoomImgTrajtoir Zoom de la carte et le trajet choisit par l'utilisateur
 * @param comp permet l'utilisation du MediaTracker de notre application
 * @param tracker pour gere le wait de scalage
 * @param observer pour gere le wait de scalage
 */

 void ScallingImage(double ZoomImgTrajtoir,MediaTracker tracker,ImageObserver observer) {
  int imageWaiter=100;


  for ( int x=0;x<largeur;x++)
    for (int y=0;y<hauteur;y++)
   {
      if (BolcForPaint[x][y]==2) {
        ImageScaller[x][y]=ImageOriginal[x][y].getScaledInstance((int) (ImageOriginal[x][y].getWidth(observer)*ZoomImgTrajtoir*echellCart),(int)(ImageOriginal[x][y].getHeight(observer)*ZoomImgTrajtoir*echellCart),Image.SCALE_FAST);
        tracker.addImage(ImageScaller[x][y], imageWaiter++);
        BolcForPaint[x][y] = 3 ;
      }
//      else   if (BolcForPaint[x][y]==0) {ImageOriginal[x][y]=ImageScaller[x][y]=null;}
   }

   try { tracker.waitForAll(); }
   catch(InterruptedException e) {
     System.out.println("error scall");

   }
//   System.gc();System.gc();
}
/**
 * elle permet de afficher les bloc d image ImSacall qui sont marquer par BolcPaint
 *
 * @param ZoomImgTrajtoir Zoom de la carte et le trajet choisit par l'utilisateur
 * @param g context et la structure de l'affichage
 * @param origine point origine d'affichage dans lecron
 * @param comp pour le controle d'affichge des blocs d'image
 *
  */
  void paintImageBlocs(Graphics g,Point origine,double ZoomImgTrajtoir,ImageObserver observer,boolean voireCarre){

    for ( int x=0;x<largeur;x++)
      for (int y=0;y<hauteur;y++)
      {
        if (BolcForPaint[x][y]==3) {
         //   g.drawImage(ImageScaller[x][y],((int) (300*x*ZoomImgTrajtoir*echellCart))+(int)(origine.x),((int) (300*y*ZoomImgTrajtoir*echellCart))+(int)(origine.y),comp);
           g.drawImage(ImageScaller[x][y],((int) ((largeurBloc*x+PointPosCartX)*ZoomImgTrajtoir*echellCart))+(int)(origine.x),((int) ((hauteurBolc*y+PointPosCartY)*ZoomImgTrajtoir*echellCart))+(int)(origine.y),observer);
           if (voireCarre)
              g.draw3DRect(((int) (( largeurBloc*x+PointPosCartX)*ZoomImgTrajtoir*echellCart))+(int)(origine.x),((int) ((hauteurBolc*y+PointPosCartY)*ZoomImgTrajtoir*echellCart))+(int)(origine.y),
                        ((int) (largeurBloc*ZoomImgTrajtoir*echellCart + PointPosCartX*echellCart*ZoomImgTrajtoir)),
                        ((int) (hauteurBolc*ZoomImgTrajtoir*echellCart + PointPosCartX*echellCart*ZoomImgTrajtoir)),
                        false);
//            NotreCarte.paintImageBlocs(new Point((int)((calagePlusX+1710)*ZoomImgTrajtoir*NotreCarte.echellCart)+orRepX-210,(int)((calagePlusY+3270)*ZoomImgTrajtoir*NotreCarte.echellCart)+orRepY-51),ZoomImgTrajtoir,this);
        }
      }
//       System.gc();System.gc();
  }
  /**
   * elle permet de afficher les bloc d image ImSacall qui sont marquer par BolcPaint
   *
   * @param ZoomImgTrajtoir Zoom de la carte et le trajet choisit par l'utilisateur
   * @param g context et la structure de l'affichage
   * @param origine point origine d'affichage dans lecron
   * @param comp pour le controle d'affichge des blocs d'image
   *
    */
    void paintImageBlocsOriginal(Graphics g,Point origine,double ZoomImgTrajtoir,ImageObserver observer,boolean voireCarre){

      for ( int x=0;x<largeur;x++)
        for (int y=0;y<hauteur;y++)
        {
          if (BolcForPaint[x][y]>=2 ) {

             g.drawImage(ImageOriginal[x][y],((int) ((largeurBloc*x+PointPosCartX)*ZoomImgTrajtoir*echellCart))+(int)(origine.x),((int) ((hauteurBolc*y+PointPosCartY)*ZoomImgTrajtoir*echellCart))+(int)(origine.y),observer);
             if (voireCarre)
                g.draw3DRect(((int) (( largeurBloc*x+PointPosCartX)*ZoomImgTrajtoir*echellCart))+(int)(origine.x),((int) ((hauteurBolc*y+PointPosCartY)*ZoomImgTrajtoir*echellCart))+(int)(origine.y),
                          ((int) (largeurBloc*ZoomImgTrajtoir*echellCart + PointPosCartX*echellCart*ZoomImgTrajtoir)),
                          ((int) (hauteurBolc*ZoomImgTrajtoir*echellCart + PointPosCartX*echellCart*ZoomImgTrajtoir)),
                          false);

          }
        }

    }

  /**
 * elle permet de marker les bloc Image qui sont visible avec la position et la resolution du ecron
 * la partie visible seulement sera afficher et de ceci on marke seulemt leur bloc
 *
 * @param origineEcron origine de ecron.
 * @param VisibleEcronDebut point de debut rejigon de ecron que on veur marcker (parsuite aficher sa map)
 * @param VisibleEcronFin point de fin rejigon de ecron que on veur marcker (parsuite aficher sa map)
 * @param ZoomImgTrajtoir Zoom de la carte
 *
   * */
//  boolean EcranVisible[][];
  void markageBIParEcronVisible(Point origineEcron,Point VisibleEcronDebut,Point VisibleEcronFin,double ZoomImgTrajtoir){
   int BordureP =1200;
   int BordureM =1200;
   int effetCoop =300;
   for ( int x=0;x<largeur;x++)
     for (int y=0;y<hauteur;y++){

   //avec bordure
    if (( VisibleEcronDebut.x-BordureM-effetCoop   <= (((int) (largeurBloc*x*ZoomImgTrajtoir*echellCart + PointPosCartX*echellCart*ZoomImgTrajtoir))+(int)(origineEcron.x)))
        && (VisibleEcronFin.x+BordureP   >= (((int) (largeurBloc*x*ZoomImgTrajtoir*echellCart+ PointPosCartX*echellCart*ZoomImgTrajtoir))+(int)(origineEcron.x)))
        && (VisibleEcronDebut.y -BordureM-effetCoop<= (((int) (hauteurBolc*y*ZoomImgTrajtoir*echellCart+ PointPosCartY*echellCart*ZoomImgTrajtoir))+(int)(origineEcron.y)))
        && (VisibleEcronFin.y  +BordureP >= (((int) (hauteurBolc*y*ZoomImgTrajtoir*echellCart+ PointPosCartY*echellCart*ZoomImgTrajtoir))+(int)(origineEcron.y)))

           )
    {
/*      //sana bordure juste ecran visible
      if (( VisibleEcronDebut.x-effetCoop   <= (((int) (largeurBloc*x*ZoomImgTrajtoir*echellCart + PointPosCartX*echellCart*ZoomImgTrajtoir))+(int)(origineEcron.x)))
       && (VisibleEcronFin.x   >= (((int) (largeurBloc*x*ZoomImgTrajtoir*echellCart+ PointPosCartX*echellCart*ZoomImgTrajtoir))+(int)(origineEcron.x)))
       && (VisibleEcronDebut.y -effetCoop<= (((int) (hauteurBolc*y*ZoomImgTrajtoir*echellCart+ PointPosCartY*echellCart*ZoomImgTrajtoir))+(int)(origineEcron.y)))
       && (VisibleEcronFin.y   >= (((int) (hauteurBolc*y*ZoomImgTrajtoir*echellCart+ PointPosCartY*echellCart*ZoomImgTrajtoir))+(int)(origineEcron.y)))

           )  EcranVisible[x][y]=true;*/
      //markage de tous les rigien
      if (BolcForPaint[x][y]==0) {
        BolcForPaint[x][y]=1;
      }


    }
    else
    {/*if (BolcForPaint[x][y]!=0) */
      { BolcForPaint[x][y]=0;ImageOriginal[x][y]=null;ImageScaller[x][y]=null;}}

    }

System.gc();System.runFinalization();
System.gc();System.runFinalization();


  }
  /**
   * elle permet de marker seul les bloc d'image qui on va les telecharger et les afficher
   * il y plusieur implimentation de marckage
   * bloc visiter par le trajet
   * rectangle des bolcs visiter par le trajet
   *
   * @param tarjet la trajet sur la quelle on va faire le markage
   * @param ZoomImgTrajtoir Zoom de la carte
   * @param algorithme
   *  (0 bloc visiter par le trajet)
   *  (1 rectangle des bolcs visiter par le trajet)
   * */
  void markageBIParTrajet(Trajet tarjet,double ZoomImgTrajtoir,int algorithme){

    int x,y,xMax=-1,xMin=9999,yMax=-1,yMin=9999;
    for (int i=0;i<tarjet.nombrePointGPS;i++){
      if (tarjet.pointGPS[i].cordonneXZoomer!=0) {
        x=(int) ((tarjet.pointGPS[i].cordonneXZoomer+RPZoomer.x-PointPosCartX*ZoomImgTrajtoir*echellCart)/(largeurBloc*ZoomImgTrajtoir*echellCart));
        y=(int)((tarjet.pointGPS[i].cordonneYZoomer+RPZoomer.y-PointPosCartY*ZoomImgTrajtoir*echellCart)/(hauteurBolc*ZoomImgTrajtoir*echellCart));



        //on marke que les visiter par un trajet
        if (0<=x && 0<=y && x<largeur && y<hauteur){
          //pour marquer tous les bloc image enapparent comme rectangle
          if (x < xMin) xMin=x;
          if (y < yMin) yMin=y;
          if (xMax < x) xMax=x;
          if (yMax < y) yMax=y;
//       int hx=0,hy=0;

//if ((BolcForPaint[x+hx][y+hy]==0) ) {BolcForPaint[x+hx][y+hy]=1;System.out.println("x="+x+" y="+y+" i="+i) ;}
      if (algorithme!=3)
            for (int hx=-algorithme;hx<=algorithme;hx++)
             for (int hy=-algorithme;hy<=algorithme;hy++){
               if (0<=x+hx && 0<=y+hy && x+hx<largeur && y+hy <hauteur &&(BolcForPaint[x+hx][y+hy]==0) ) BolcForPaint[x+hx][y+hy]=1;
             }
        }
      }
    }
    //markage du rectangle des bolcs visiter par le trajet
    if (algorithme==3){
    for ( x=xMin;x<=xMax;x++)
      for ( y=yMin;y<=yMax;y++)
        if (0<=x && 0<=y && x<largeur && y<hauteur)  BolcForPaint[x][y]=1;
  }

  }
/**
 *elle recalcule le point fixe de limage corspandant a cette Zoom
 *
 * @param ZoomImgTrajtoir Zoom de la carte et le trajet choisit par l'utilisateur
 */
  void setNewRPZoom(double ZoomImgTrajtoir){

    RPZoomer = new Point((int) ((ReferencePoint.x)*ZoomImgTrajtoir),(int) ((ReferencePoint.y)*ZoomImgTrajtoir));

  }
}




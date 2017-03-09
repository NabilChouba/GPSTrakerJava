package sgps;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.sql.*;

import javax.swing.*;

//import com.borland.jbcl.layout.*;
import javax.swing.border.*;

/**
 *
 * <p>Titre : Structure du Trajet</p>
 * <p>Description :  c'est une structure qui contient tous les information utile pour
 *  la representation une carte et la manipulation.</p>
 * <p>Copyright : Copyright (c) 28.5.2003</p>
 * <p>Société : NewTec</p>
 * @author Chouba Nabil &Nizar Grame
 * @version 1.0
 */
class Trajet{
/**tableux de point donne par le gps */
  PointGps pointGPS[];

/**langeur du tableux de point donne par le gps */
  int nombrePointGPS=0;

/**km*1000=metre*/
 int echellTrajet;

/**
 * Constructeur permet L'inisalisation
 *
 * @param Maxpoint nombre max des point a aficher c'est une inisalisation primaire
 *
 * */
 Trajet(int Maxpoint){
  pointGPS =new PointGps[Maxpoint];
}

/**
 * Charger les point apartire d'un fichier
 *
 * @param NomFicher nom du ficher contenant la trajectoire
 * @param comp elle permet de donner le chemaint de basse
 *
 * */
 public void loadPoint(String NomFicher)
 {
   try{


      RandomAccessFile File_List_Cache= new RandomAccessFile(NomFicher,"rw");

    String ligne;


   while ((ligne = File_List_Cache.readLine()) != null)
   {

     PN380 tr=new PN380(ligne);
     if (tr.isValid()) {
            //ajouter a la liste qui va etre afficher
      pointGPS [nombrePointGPS]=new PointGps();
      pointGPS [nombrePointGPS].latitude =tr.getLat();
      pointGPS [nombrePointGPS].longitude =tr.getLong();
      pointGPS [nombrePointGPS].DateHeur =tr.getDate() +" "+tr.getTime();
//      pointGPS [nombrePointGPS].cap = java.lang.Integer.parseInt(tr.getCap());;
//      pointGPS [nombrePointGPS].vittes =java.lang.Integer.parseInt(tr.getVittes());

       nombrePointGPS++;
     }
     else  System.out.println("line problem syntax ");

   }
   File_List_Cache.close();
//   System.out.println("point sms lut nombre a afficher ="+nombrePointGPS);
   }
   catch(Exception e) {
        System.out.println("url error");
  }
 }

/**
 * elle calcule la distance reel entre les point et le poit dorigine fixe dans la carte
 *
 * @param glatOrgin en degree le latitude du point d origine du carte
 * @param glonOrgin en degree le lagitude du point d origine du carte
 *
 * */
void CalculeDistanceReel(double glatOrgin,double glonOrgin){
  double degreLong, degreLat;
  for (int k=0;k<nombrePointGPS;k++){
    //convertion en degre
    degreLat= ellips.degLat2rad(pointGPS[k].latitude,true);
    degreLong= ellips.degLat2rad(pointGPS[k].longitude,false);

    //calcule des destances
    pointGPS[k].cordonneXReel =ellips.crsdist_ell( glatOrgin, glonOrgin,glatOrgin,degreLong );
    pointGPS[k].cordonneYReel =-ellips.crsdist_ell(glatOrgin, glonOrgin,degreLat ,glonOrgin );

    //reglage de pois
    if ((degreLat-glatOrgin)<0) pointGPS[k].cordonneYReel*=-1;
    if ((degreLong-glonOrgin)<0) pointGPS[k].cordonneYReel*=-1;

  }
}


/**
 * elle calcule la distance Zoomer entre les point et le poit dorigine fixe dans la carte
 * apartire d'une valeur de zoom
 *
 * @param ZoomImgTrajtoir Zoom de la carte et le trajet choisit par l'utilisateur
 *
 * */
void CalculeDistanceZoomer(double ZoomImgTrajtoir){

  for (int k=0;k<nombrePointGPS;k++){
    //distance pares Zooomer
    pointGPS[k].cordonneXZoomer =(int)(pointGPS[k].cordonneXReel *echellTrajet*ZoomImgTrajtoir);//km*echell*ZoomImgTrajtoir
    pointGPS[k].cordonneYZoomer =(int)(pointGPS[k].cordonneYReel *echellTrajet*ZoomImgTrajtoir);
  }
}


/**
 * Elle affiche la trajet point par point
 *
 * @param origine represente le point d'origine fixer dans la cart et son deplasement dans l'ecrant
 * @param g c#est le contexe ou on va faire l'affichage
 * @param navigationMode permet de sinuliluer une navigation
 *
 * */
void AfficheParPoint(Graphics g,Point origine,boolean navigationMode){
  for (int k=0;k<nombrePointGPS;k++){
    //Affichge du point sous forme de point
    g.setColor(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
    g.fillOval((int)pointGPS[k].cordonneXZoomer+origine.x-2,(int)pointGPS[k].cordonneYZoomer+origine.y-2,6,6);
    if (navigationMode) for (int kkk=1;kkk<=100000;kkk++)for (int kkgk=1;kkgk<=50;kkgk++);;//une simple waite
  }

}


/**
 * Elle affiche la trajet par des ligne
 *
 * @param origine represente le point d'origine fixer dans la cart et son deplasement dans l'ecrant
 * @param g c'est le contexe ou on va faire l'affichage
 * @param navigationMode permet de sinuliluer une navigation
 *
 * */
 void AfficheParTrajet(Graphics g,Point origine,boolean navigationMode){
  //g.setColor(new Color (255,255,0));
  for (int k=0;k<nombrePointGPS-1;k++){



   if (navigationMode)
    for (int kk=1;kk<=100;kk++){
        g.drawLine(pointGPS[k].cordonneXZoomer+origine.x  , pointGPS[k].cordonneYZoomer+origine.y ,
                (int)((pointGPS[k+1].cordonneXZoomer-pointGPS[k].cordonneXZoomer)*0.01*kk +pointGPS[k].cordonneXZoomer+origine.x) ,
                (int)((pointGPS[k+1].cordonneYZoomer-pointGPS[k].cordonneYZoomer)*0.01*kk +pointGPS[k].cordonneYZoomer+origine.y));

//        g.drawLine(orX,orY,(int)(((orRepX+distx-orX)*0.01*kk)+orX)
//                             ,(int)(((orRepY+disty-orY)*0.01*kk))+orY);

        for (int kkk=1;kkk<=100000;kkk++);//une simple waite
     }
     else
       g.drawLine(pointGPS[k].cordonneXZoomer+origine.x  , pointGPS[k].cordonneYZoomer+origine.y ,
          pointGPS[k+1].cordonneXZoomer+origine.x,pointGPS[k+1].cordonneYZoomer+origine.y);

  }
 }

}

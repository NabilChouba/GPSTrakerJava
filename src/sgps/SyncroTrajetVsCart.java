package sgps;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;
import javax.swing.border.*;
/**
 * <p>Titre : </p>
 * <p>Description : </p>
 * <p>Copyright : Copyright (c) 2003</p>
 * <p>Société : </p>
 * @author non attribué
 * @version 1.0
 */

public class SyncroTrajetVsCart {


  SyncroTrajetVsCart(JFrame comp,double Zomm){
  Zoom =Zomm;
  workCart.initCart(Zoom);
  loadTrajet("trajet\\facToMaison.txt");
  }
//  double Zoom=3.1;int ORigineEcronX=-2345;int  ORigineEcronY=-4493;

//  int ORigineEcronX=-23;int  ORigineEcronY=-44;double Zoom=0.1;

  int ORigineEcronX=157;int  ORigineEcronY=-264;
  double Zoom;
  boolean voireCarre=false;
  boolean ImageZommed=false;
  boolean ShowTrajet=true;
  boolean ShowPoint=true;
  boolean navigationModeSet=false;
  int algoMarkage=-1;//-1 ecron visible //0bloc dans le traje //1 voisinage1 //4 rectangle contenemt le trajet

  Trajet trajet;

  AllCart workCart=new AllCart();

  void loadTrajet(String nomTrajet)
  {
    //gestion du trajet
      trajet =new Trajet(1300);
      trajet.loadPoint(nomTrajet);
      trajet.echellTrajet =200;
      trajet.CalculeDistanceReel(workCart.NotreCarte[0].degreeLatitude, workCart.NotreCarte[0].degreeLongitude);
      trajet.CalculeDistanceZoomer(Zoom);

  }
  void show(Graphics Desktop,MediaTracker  tracker,ImageObserver observer){
//gestion de la Zoom
     if (ImageZommed) {
       ImageZommed=false;

       if (Zoom!=1/(0.6381445330849965 * 40/100 * (100/ workCart.NotreCarte[0].ValeurZoomBloc[workCart.NotreCarte[0].indiceZoomActuelle])))
         workCart.ScallingImage(Zoom,tracker,observer);
       trajet.CalculeDistanceZoomer(Zoom);
   }


   if (Zoom!=1/(0.6381445330849965 * 40/100 *(100/ workCart.NotreCarte[0].ValeurZoomBloc[workCart.NotreCarte[0].indiceZoomActuelle])))

     workCart.paintImageBlocs(Desktop,new Point(ORigineEcronX-0, ORigineEcronY-90),Zoom,observer,voireCarre);

   else {workCart.paintImageBlocsOriginal(Desktop, new Point(ORigineEcronX-0, ORigineEcronY-90),Zoom,observer,voireCarre);
         //System.out.println("original");
         }

//afffichage trajet
    if (ShowPoint)
      trajet.AfficheParPoint (Desktop,new Point(ORigineEcronX-0+ workCart.NotreCarte[0].RPZoomer.x, workCart.NotreCarte[0].RPZoomer.y+ ORigineEcronY-90),navigationModeSet);
    if (ShowTrajet )
      trajet.AfficheParTrajet(Desktop,new Point(ORigineEcronX-0+ workCart.NotreCarte[0].RPZoomer.x, workCart.NotreCarte[0].RPZoomer.y+ ORigineEcronY-90),navigationModeSet);
    navigationModeSet=false;
  }
}





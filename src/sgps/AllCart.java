package sgps;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.sql.*;
import java.awt.image.*;
import javax.swing.*;


import javax.swing.border.*;

/**
 *
 * <p>Titre : Structure generaliser d'une Carte </p>
 * <p>Description : Structure permetant de geree des Carte et faire comme si on manibuler une seul</p>
 * <p>Copyright : Copyright (c) 28.5.2003</p>
 * <p>Société : NewTec</p>
 * @author Chouba Nabil &Nizar Grame
 * @version 1.0
 */

class AllCart{

  /** nombre de carte mise en oeuvre*/
  int NombreCarte=0;

  /**structure de chargement de tous les carte*/
  Cart NotreCarte[];

  /**
   * permet de inisalise les carte utiliser et les ajouter a la liste de travaille
   *
   * @param ZoomImgTrajtoir Zoom de la carte et le trajet choisit par l'utilisateur
   *
   */

  void initCart(double ZoomImgTrajtoir){

      NombreCarte=4;

      NotreCarte=new Cart[NombreCarte];

    // initialisation des cartes
      int echellExplaire =50;

     NotreCarte[0]=new Cart("carte Reana","/ariana2/",11,15,2);
     NotreCarte[1]=new Cart("carte ben arous","/ben arouse2/",13,10,2);
     NotreCarte[2]=new Cart("carte bon lieu du","/bon lieu2/",12,8,2);
     NotreCarte[3]=new Cart("carte la marsa","/la marssa/",11,15,2);

     NotreCarte[0].longgZoomingBloc[0] =3;
     NotreCarte[0].longgZoomingBloc[1] =8;
     NotreCarte[0].longgZoomingBloc[2] =15;
     NotreCarte[0].longgZoomingBloc[3] =30;

     NotreCarte[1].longgZoomingBloc[0] =2;
     NotreCarte[1].longgZoomingBloc[1] =5;
     NotreCarte[1].longgZoomingBloc[2] =10;
     NotreCarte[1].longgZoomingBloc[3] =19;

     NotreCarte[2].longgZoomingBloc[0] =2;
     NotreCarte[2].longgZoomingBloc[1] =4;
     NotreCarte[2].longgZoomingBloc[2] =8;
     NotreCarte[2].longgZoomingBloc[3] =16;

     NotreCarte[3].longgZoomingBloc[0] =2;
     NotreCarte[3].longgZoomingBloc[1] =8;
     NotreCarte[3].longgZoomingBloc[2] =15;
     NotreCarte[3].longgZoomingBloc[3] =29;

     NotreCarte[3].largZoomingBloc[0] =3;
     NotreCarte[3].largZoomingBloc[1] =6;
     NotreCarte[3].largZoomingBloc[2] =11;
     NotreCarte[3].largZoomingBloc[3] =21;

     NotreCarte[2].largZoomingBloc[0] =3;
     NotreCarte[2].largZoomingBloc[1] =6;
     NotreCarte[2].largZoomingBloc[2] =12;
     NotreCarte[2].largZoomingBloc[3] =24;

     NotreCarte[0].largZoomingBloc[0] =3;
     NotreCarte[0].largZoomingBloc[1] =6;
     NotreCarte[0].largZoomingBloc[2] =11;
     NotreCarte[0].largZoomingBloc[3] =21;

     NotreCarte[1].largZoomingBloc[0] =3;
     NotreCarte[1].largZoomingBloc[1] =7;
     NotreCarte[1].largZoomingBloc[2] =13;
     NotreCarte[1].largZoomingBloc[3] =24;

     NotreCarte[0].PointPosCartXOriginal=0;
     NotreCarte[0].PointPosCartYOriginal=0;
     NotreCarte[1].PointPosCartXOriginal=(int) (1710);
     NotreCarte[1].PointPosCartYOriginal=(int) (3270);
     NotreCarte[2].PointPosCartXOriginal=(int) (4658);
     NotreCarte[2].PointPosCartYOriginal=(int) (4276);
     NotreCarte[3].PointPosCartXOriginal=4128;
     NotreCarte[3].PointPosCartYOriginal=-671;

     NotreCarte[0].PointPosCartX=0;
     NotreCarte[0].PointPosCartY=0;


     NotreCarte[1].PointPosCartX=(int) (1710 *100/40 /(100/echellExplaire));
     NotreCarte[1].PointPosCartY=(int) (3270 *100/40  /(100/echellExplaire));


     NotreCarte[2].PointPosCartX=(int) (4658 *100/40  /(100/echellExplaire));
     NotreCarte[2].PointPosCartY=(int) (4276 *100/40  /(100/echellExplaire));


     NotreCarte[3].PointPosCartX=4128 *100/40  /(100/echellExplaire);
     NotreCarte[3].PointPosCartY=-671 *100/40  /(100/echellExplaire);

      for (int i=0;i<NombreCarte;i++)
      {
        NotreCarte[i].largeurBloc=NotreCarte[i].hauteurBolc=300;
        NotreCarte[i].degreeLatitude  = 0.64288056897669   ;
        NotreCarte[i].degreeLongitude = 0.17710632591503597;
        NotreCarte[i].echellCart      = 0.6381445330849965 * 40/100 * (100/echellExplaire);
        NotreCarte[i].ReferencePoint  = new Point(418,1004);
        NotreCarte[i].RPZoomer =new Point(418,1004);
        NotreCarte[i].setNewRPZoom(ZoomImgTrajtoir);
      }
  }
  void setNewDuplic(int k){
    for (int i=0;i<NombreCarte;i++)
      NotreCarte[i].setNewDuplic(k);
  }
  void setNewReload(){
    for (int i=0;i<NombreCarte;i++)
      NotreCarte[i].setReLoadl();
  }
  /**
   * elle permet de faire la Zoom corespantante a limage original
   * dejat chatger dans ImageOriginal
   * generalisation traitement fais a tous les carte .
   *
   * @param ZoomImgTrajtoir Zoom de la carte et le trajet choisit par l'utilisateur
   * @param comp permet l'utilisation du MediaTracker de notre application
   *
 */
  void ScallingImage(double ZoomImgTrajtoir,MediaTracker  tracker,ImageObserver observer){
    for (int i=0;i<NombreCarte;i++){
      NotreCarte[i].setReScall();
      NotreCarte[i].ScallingImage(ZoomImgTrajtoir,tracker,observer);
      NotreCarte[i].setNewRPZoom(ZoomImgTrajtoir);
    }
  }
  void useOriginalImage(double ZoomImgTrajtoir,MediaTracker  tracker,ImageObserver observer){
   for (int i=0;i<NombreCarte;i++){
     //NotreCarte[i].setReScall();
     NotreCarte[i].setNewRPZoom(ZoomImgTrajtoir);
   }
  }
    /** elle permet de afficher les bloc d image ImSacall qui sont marquer par BolcPaint
     *  pour tous les carte dans sa liste
     *
     * @param ZoomImgTrajtoir Zoom de la carte et le trajet choisit par l'utilisateur
     * @param g context et la structure de l'affichage
     * @param origineEcron point origine d'affichage dans lecron
     * @param comp pour le controle d'affichge des blocs d'image
     *
  */
  void paintImageBlocs(Graphics g,Point origineEcron,double ZoomImgTrajtoir,ImageObserver observer,boolean voireCarre){
  for (int i=0;i<NombreCarte;i++)
     NotreCarte[i].paintImageBlocs(g,new Point(origineEcron.x,origineEcron.y),ZoomImgTrajtoir,observer,voireCarre);
  }
  void paintImageBlocsOriginal(Graphics g,Point origineEcron,double ZoomImgTrajtoir,ImageObserver observer,boolean voireCarre){
for (int i=0;i<NombreCarte;i++)
   NotreCarte[i].paintImageBlocsOriginal(g,new Point(origineEcron.x,origineEcron.y),ZoomImgTrajtoir,observer,voireCarre);
  }
  /** permet de ajouter une carte*/
  void addOneCart(){

  }
  /** permet de supprimer une carte*/
  void deleteOneCart(){

  }

  /**
 * elle permet de marker les bloc Image ,pour tous les carte utiliser, qui sont visible dans l'ecron
 *
 * @param origineEcron origine de ecron.
 * @param VisibleEcronDebut point de debut rejigon de ecron que on veur marcker (parsuite aficher sa map)
 * @param VisibleEcronFin point de fin rejigon de ecron que on veur marcker (parsuite aficher sa map)
 * @param ZoomImgTrajtoir Zoom de la carte
 *
   * */
  void markageBIParEcronVisible(Point origineEcron,Point VisibleEcronDebut,Point VisibleEcronFin,double ZoomImgTrajtoir){
   for (int i=0;i<NombreCarte;i++){
     NotreCarte[i].markageBIParEcronVisible(origineEcron,VisibleEcronDebut,VisibleEcronFin,ZoomImgTrajtoir);
   }
  }
  /**
  * elle permet de marker seul les bloc d'image ,pour tous les carte utiliser,qui on va les telecharger et les afficher
  * il y plusieur implimentation de marckage
  * -bloc visiter par le trajet
  * -rectangle des bolcs visiter par le trajet
  *
  * @param tarjet la trajet sur la quelle on va faire le markage
  * @param ZoomImgTrajtoir Zoom de la carte
  * @param algorithme
  *  (0 bloc visiter par le trajet)
  *  (1 rectangle des bolcs visiter par le trajet)
  * */
  void markageBIParTrajet(Trajet tarjet,double ZoomImgTrajtoir,int algorithme){
    for (int i=0;i<NombreCarte;i++){
      NotreCarte[i].markageBIParTrajet(tarjet,ZoomImgTrajtoir,algorithme);
      System.out.println("algo"+algorithme);
   }
  }
  /**
 * elle charge les image de la boucle dans leur taille original
 * pour tous les carte utiliser
 *
 * @param comp elle permet de optenire le chemain des blocs d'image
 *
  */
    void chargeurImage(JFrame tracker){
       for (int i=0;i<NombreCarte;i++){
         NotreCarte[i].chargeurImage(tracker);
       }
    }
}
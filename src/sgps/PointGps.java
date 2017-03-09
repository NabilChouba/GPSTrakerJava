package sgps;


/**
 *
 * <p>Titre : Structure d'un Point GPS </p>
 * <p>Description : represente la structure contenent les informations utile pour un point donnee par le GPS.</p>
 * <p>Copyright : Copyright (c) 28.5.2003</p>
 * <p>Société : NewTec</p>
 * @author Chouba Nabil &Nizar Grame
 * @version 1.0
 */
class PointGps{
/** longitude donnee par le GPS*/
  public String longitude;
/** latitude donnee par le GPS*/
  public String latitude ;
/** vittes donnee par le GPS*/
  public int vittes ;
/** cap donnee par le GPS*/
  public int cap ;
/** le date et l'heur donnee par le GPS*/
  public String DateHeur ;
  /**
* represente la destance reel en Km sur (oX) entre le point d'origine et notre point.
   */
  public double cordonneXReel;
  /**
* represente la destance reel en Km sur (oY) entre le point d'origine et notre point.
 */
  public double cordonneYReel;
  /**
* represente le cordonnesuivant (oX) entre le point d'origine et notre point en prenant conte du la ZooM.
   */
  public int cordonneXZoomer;
  /**
* represente le cordonnesuivant (oY) entre le point d'origine et notre point en prenant conte du la ZooM.
   */
  public int cordonneYZoomer;
}


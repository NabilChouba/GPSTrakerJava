package sgps;

import java.math.*;
import java.lang.*;
/**
 *
 * <p>Titre : WGS-84 Geoid</p>
 * <p>Description : Calcule la distance entre deux point.
 * par le modelle WGS-84 Geoid qui est le Modele adapter par le GPS</p>
 * <p>Copyright : Copyright (c) 28.5.2003</p>
 * <p>Société : NewTec</p>
 * @author Chouba Nabil &Nizar Grame
 * @version 1.0
 */
/**
 *Calcule la distance entre deux point.
 * par le modelle WGS-84 Geoid qui est le Modele adapter par le GPS
 */
class ellips {
  /**parmatre du l'elipse (terre)  a = semi-major axe*/
  static double a;
  /**parmatre du l'elipse (terre)  f = 1/Flating*/
  static double f;

  /**
   *Calcule la distance entre deux point.
   * par le modelle WGS-84 Geoid qui est le Modele adapter par le GPS
   *
   * @param glat1 initial geodetic latitude dont radians N positive
   * @param glon1 initial geodetic longitude dont radians E positive
   * @param glat2 final geodetic latitude in radians N positive
   * @param glon2 final geodetic longitude in radians E positive
   * @return la distance en Km
   **/
  public static  double crsdist_ell(double glat1,double glon1,double glat2,double glon2){
// glat1 initial geodetic latitude in radians N positive
// glon1 initial geodetic longitude in radians E positive
// glat2 final geodetic latitude in radians N positive
// glon2 final geodetic longitude in radians E positive
/*glon1*=-1;
glon2*=-1;*/
a=6378.137/1.852;
f=1/298.257223563;
//alert("a="+a+" f="+f)
double r, tu1, tu2, cu1, su1, cu2, s1, b1, f1;
double x, sx=0., cx=0., sy=0., cy=0.,y=0., sa=0., c2a=0., cz=0., e=0., c, d;
double EPS= 0.00000000001;
double faz=0., baz=0., s=0.;
int  iter=1;
int  MAXITER=500;
  if ((glat1+glat2==0.) && (Math.abs(glon1-glon2)==Math.PI)){
    System.out.println("Course and distance between antipodal points is undefined");
    glat1=glat1+0.  ;// allow algorithm to complete
    }
  if (glat1==glat2 && (glon1==glon2 || Math.abs(Math.abs(glon1-glon2)-2*Math.PI) <  EPS)){
    System.out.println("Points 1 and 2 are identical- course undefined");
    /*out=new MakeArray(0)
    out.d=0
    out.crs12=0
    out.crs21=Math.PI */
    return 0;
  }
  r = 1 - f;
  tu1 = r * Math.tan (glat1);
  tu2 = r * Math.tan (glat2);
  cu1 = 1. / Math.sqrt (1. + tu1 * tu1);
  su1 = cu1 * tu1;
  cu2 = 1. / Math.sqrt (1. + tu2 * tu2);
  s1 = cu1 * cu2;
  b1 = s1 * tu2;
  f1 = b1 * tu1;
  x = glon2 - glon1;
  d = x + 1 ;// force one pass
  while ((Math.abs(d - x) > EPS) && (iter < MAXITER))
    {
      iter=iter+1;
      sx = Math.sin (x);
//       alert("sx="+sx)
      cx = Math.cos (x);
      tu1 = cu2 * sx;
      tu2 = b1 - su1 * cu2 * cx;
      sy = Math.sqrt(tu1 * tu1 + tu2 * tu2);
      cy = s1 * cx + f1;
      y = atan2 (sy, cy);
      sa = s1 * sx / sy;
      c2a = 1 - sa * sa;
      cz = f1 + f1;
      if (c2a > 0.)
        cz = cy - cz / c2a;
      e = cz * cz * 2. - 1.;
      c = ((-3. * c2a + 4.) * f + 4.) * c2a * f / 16.;
      d = x;
      x = ((e * cy * c + cz) * sy * c + y) * sa;
      x = (1. - c) * x * f + glon2 - glon1;
    }
  faz = modcrs(atan2(tu1, tu2));
  baz = modcrs(atan2(cu1 * sx, b1 * cx - su1 * cu2) + Math.PI);
  x = Math.sqrt ((1 / (r * r) - 1) * c2a + 1);
  x +=1;
  x = (x - 2.) / x;
  c = 1. - x;
  c = (x * x / 4. + 1.) / c;
  d = (0.375 * x * x - 1.) * x;
  x = e * cy;
  s = ((((sy*sy*4.-3.)*(1.-e-e)*cz*d/6.-x)*d/4.+cz)*sy*d+y)*c*a*r;
  /*out=new MakeArray(0)
  out.d=s
  out.crs12=faz
  out.crs21=baz*/
  if (Math.abs(iter-MAXITER)<EPS){
    System.out.println("Algorithm did not converge");
  }
  return s*1.852;

}
static double atan2(double y,double x){

  if (x <0)            { return Math.atan(y/x)+Math.PI;}
  if ((x >0) && (y>=0)){ return Math.atan(y/x);}
  if ((x >0) && (y<0)) { return Math.atan(y/x)+2*Math.PI;}
  if ((x==0) && (y>0)) { return Math.PI/2;}
  if ((x==0) && (y<0)) { return 3*Math.PI/2;}
  if ((x==0) && (y==0)) {
    System.out.println("atan2(0,0) undefined");
    return 0. ;
  }
 return 0.;
}
static double modcrs(double x){
  return mod(x,2*Math.PI);
}
static double mod(double x,double y){
  return x-y*Math.floor(x/y);
}
/**
 *
 * convertion deg  "ddmm.mmmm"--->rad Latitude
 * @param deg "ddmm.mmmm"
 * @param ForLatitude pour latitude ou longitude
 * @return rad Latitude
 * */
static double degLat2rad(String deg,boolean ForLatitude){
//convertion deg  "ddmm.mmmm"--->rad Latitude
int i=3;
if (ForLatitude)  i=2;
String dd= deg.substring(0,i);
String mm=deg.substring(i,deg.length());
double mmm=Double.parseDouble(mm);
int ddd =Integer.parseInt(dd);

return Math.toRadians(ddd+mmm/60) ;

}
}
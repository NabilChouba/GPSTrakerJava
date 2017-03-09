package sgps;


/**
 *
 * <p>Titre : trame PN380 methode</p>
 * <p>Description : structure elementaire permet de extaire les donnee utile d'une trame PN380</p>
 * <p>Copyright : Copyright (c) 28.5.2003</p>
 * <p>Société : NewTec</p>
 * @author Chouba Nabil &Nizar Grame
 * @version 1.0
 */
 class PN380 {
  /**message a traiter*/
  private String msg;
  /**teste de validiter donc peux etre couper sont erreur*/
  private boolean valide;
  /**index des ',' utile pour les getooles*/
  private int []index=new int[14];
  /**@param message trame ou message a traiter (decoupage des infonormation)*/
  public PN380(String message) {
    msg =message;
    valide=isValid();
  }

  /**
   * Elle teste la validiter de la trame PN380 elle dois avoire cette forme la:
   * $PNNN,060011,A,3649.0215,N,10000.064,W,001.2,000.0,110698,
   *      0      1 2        3 4         5 6     7     8      9
   * @return true si la trane est valide
   */
  boolean isValid(){
       int i,j;
    for ( j=i=0;i<msg.length();i++)
      if ( msg.charAt(i)==',') {
        index[j]=i;
        j++;
      }

    if (j<8) return(false);
    //test si elle commence par $PNNN
    if (!msg.startsWith("$PNNN")) return(false);

    return(true);

  }
  /**extraitre l'heur

   * @return l'heur trouver dans la trame
   */
  String getTime(){
   return (new String (msg.substring(index[0]+1,index[1])));
  }
// v (valide)
  /**extraitre latitude
   *
   * @return latitude trouver dans la trame
   * */
  String getLat(){
      return (new String (msg.substring(index[2]+1,index[3])));
}
// N
  /**extraitre longitude
   *
   * @return longitude trouver dans la trame
   * */
  String getLong(){
    return (new String (msg.substring(index[4]+1,index[5])));
  }
// W
    /**extraitre vittese
     *
     * @return la vittese trouver dans la trame
     * */
  String getVittes(){
    return (new String (msg.substring(index[6]+1,index[7])));
  }
    /**extraitre la cap
     *
     * @return la cap trouver dans la trame
     */
  String getCap(){
    return (new String (msg.substring(index[7]+1,index[8])));
  }
    /**extraitre la date
     *
     * @return la date trouver dans la trame
     */
  String getDate(){
    return (new String (msg.substring(index[8]+1,index[9])));
  }
}
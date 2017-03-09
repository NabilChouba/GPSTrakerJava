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

public class CadreMain extends JFrame implements Runnable {
  //declararion des variable
   static SyncroTrajetVsCart SyncroPaint;
   static ImageObserver observer;
   static MediaTracker tracker;
   static CadreMain cadreMain;
   static CadreMain thread1;
   static Thread runable ;
   static boolean LoadBoloc ;
   Cursor cOver;
   Cursor cFermer;
   Cursor cZome;

   public void run() {
   while (true)
   {
    //System.out.println(Thread.currentThread() + " : début d'exécution...");
     LoadBoloc =true;
   //markage

   if (SyncroPaint.algoMarkage==-1)
     //ecrant visible
    SyncroPaint.workCart.markageBIParEcronVisible(new Point(SyncroPaint.ORigineEcronX-0,
          SyncroPaint.ORigineEcronY-90 ),new Point(0,90),
          new Point(cadreMain.getWidth(),cadreMain.getHeight()), SyncroPaint.Zoom);
   else //par traget de voisinage
     SyncroPaint.workCart.markageBIParTrajet(SyncroPaint.trajet,SyncroPaint.Zoom,SyncroPaint.algoMarkage);


    //chargement des bloc marquer
    SyncroPaint.workCart.chargeurImage(cadreMain);

    if (SyncroPaint.Zoom!=1/(0.6381445330849965 * 40/100 *(100/ SyncroPaint.workCart.NotreCarte[0].ValeurZoomBloc[SyncroPaint.workCart.NotreCarte[0].indiceZoomActuelle])))
      // on fait le scalge des bloc
      SyncroPaint.workCart.ScallingImage(SyncroPaint.Zoom,tracker,observer);
   //on utilise la duplique original sans rescalage
    else   SyncroPaint.workCart.useOriginalImage(SyncroPaint.Zoom,tracker,observer);

    //System.out.println(Thread.currentThread() + " : fin d'exécution.");
    LoadBoloc =false;
    cadreMain.repaint();

    try { Thread.currentThread().suspend(); }
    catch(Exception e) {e.printStackTrace();}
   }
  }

  public static void main(String[] args) {
    cadreMain = new CadreMain();
    cadreMain.show();
    cadreMain.setSize(600,600 );
    CadreMain.SyncroPaint=new SyncroTrajetVsCart(cadreMain,1/(0.6381445330849965 * 40/100 * (100/10)));
    CadreMain.observer =(ImageObserver)(cadreMain);
    CadreMain.tracker = new MediaTracker(cadreMain);

    CadreMain.SyncroPaint.ImageZommed=true;
    CadreMain.SyncroPaint.workCart.setNewDuplic(0);

    //demarage du thread
    thread1=new CadreMain();
    runable = new Thread(thread1);
    runable.start();

  }
Graphics Desktop;
  public void paint (Graphics g)
 {
  Desktop =g.create(0,90,this.getWidth(),this.getHeight());//(210,51,1000,1000);


 //if (!ConnecToBasse)   logoApplet(g);
 //else

  if (temp==0   || !this.LoadBoloc){this.LoadBoloc=true;temp++;
     g.clearRect(0,90,this.getWidth(),this.getHeight());

    if (SyncroPaint!=null) {
      SyncroPaint.show(Desktop,tracker,observer);

    }
 }

 //  panelLogin.paintAll(g);
 //  panelOption1.paintAll(g);

}

  public CadreMain() {
    try {
      jbInit();

      choiceZoomOriginal.add(" 10%");choiceZoomOriginal.add(" 25%");
      choiceZoomOriginal.add(" 50%");choiceZoomOriginal.add("100%");
      choiceMarkage.add("ecran visible");
      choiceMarkage.add("juste tajet V0");choiceMarkage.add("juste tajet V1");
      choiceMarkage.add("juste tajet V3");choiceMarkage.add("rectangle tajet");
      cOver  =new Cursor(0);
      cFermer=new Cursor(0);
      cZome  =new Cursor(0);
      panelOption1.setCursor(new Cursor (0));
      setCursor(cOver);

      String base ="";

         cOver= this.getToolkit().createCustomCursor(this.getToolkit().getImage(base+"icone\\handOver.gif"),new Point(0,0)  ,"go");
         cFermer= this.getToolkit().createCustomCursor(this.getToolkit().getImage(base+"icone\\handFermer.gif"),new Point(0,0),"go2");
         cZome= this.getToolkit().createCustomCursor(this.getToolkit().getImage(base+"icone\\zoomouse.gif"),new Point(0,0),"go2");
         jButtonnavgMode1.setText("");
         jButtonnavgMode1.setIcon(new ImageIcon((base+"icone/playn.gif")));
         jButtonPoint1.setText("");
         jButtonPoint1.setIcon(new ImageIcon((base+"icone/point.gif")));
         jButtonZoomMoin1.setText("");
         jButtonZoomMoin1.setIcon(new ImageIcon((base+"icone/zoom-.gif")));
         jButtonZoomMode1.setText("");
         jButtonZoomMode1.setIcon(new ImageIcon((base+"icone/zoom.gif")));
         jButtonTrajetMode1.setText("");
         jButtonTrajetMode1.setIcon(new ImageIcon((base+"icone/target.gif")));
         jButtonDeplaceMode1.setText("");
         jButtonDeplaceMode1.setIcon(new ImageIcon((base+"icone/hand.gif")));
         jButtonCenter1.setText("");
         jButtonCenter1.setIcon(new ImageIcon((base+"icone/Centre.gif")));
         jButtonZoomPlus1.setText("");
         jButtonZoomPlus1.setIcon(new ImageIcon((base+"icone/zoom+.gif")));



    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    titledBorder1 = new TitledBorder("");
    panelOption1.setBackground(SystemColor.menu);
    jButtonDeplaceMode1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonDeplaceMode1_actionPerformed(e);
      }
    });
    jButtonDeplaceMode1.setText("Mov Mode");
    jButtonDeplaceMode1.setToolTipText("permet de deplacer la carte");
    jButtonDeplaceMode1.setBorder(BorderFactory.createRaisedBevelBorder());
    jButtonCenter1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonCenter1_actionPerformed(e);
      }
    });
    jButtonCenter1.setText("Centrage");
    jButtonCenter1.setToolTipText("Centrage de l\'image");
    jButtonCenter1.setBorder(BorderFactory.createRaisedBevelBorder());
    jButtonZoomPlus1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonZoomPlus1_actionPerformed(e);
      }
    });
    jButtonZoomPlus1.setText("Zoom +");
    jButtonZoomPlus1.setToolTipText("ZooM  + la carte");
    jButtonZoomPlus1.setBorder(BorderFactory.createRaisedBevelBorder());
    jButtonnavgMode1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonnavgMode1_actionPerformed(e);
      }
    });
    jButtonnavgMode1.setText("Navigation");
    jButtonnavgMode1.setToolTipText("Simule une navigetion");
    jButtonnavgMode1.setBorder(BorderFactory.createRaisedBevelBorder());
    jButtonPoint1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonPoint1_actionPerformed(e);
      }
    });
    jButtonPoint1.setText("En point");
    jButtonPoint1.setToolTipText("Afiche par point");
    jButtonPoint1.setBorder(BorderFactory.createRaisedBevelBorder());
    this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
      public void mouseDragged(MouseEvent e) {
        this_mouseDragged(e);
      }
    });
    this.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        this_mouseClicked(e);
      }
      public void mousePressed(MouseEvent e) {
        this_mousePressed(e);
      }
      public void mouseReleased(MouseEvent e) {
        this_mouseReleased(e);
      }
    });
    jButtonZoomMoin1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonZoomMoin1_actionPerformed(e);
      }
    });
    jButtonZoomMoin1.setBorder(BorderFactory.createRaisedBevelBorder());
    jButtonZoomMoin1.setToolTipText("ZooM  - la carte");
    jButtonZoomMoin1.setText("Zoom -");
    jButtonZoomMode1.setBorder(BorderFactory.createRaisedBevelBorder());
    jButtonZoomMode1.setToolTipText("Mode Zoom");
    jButtonZoomMode1.setText("Zoom Mode");
    jButtonZoomMode1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonZoomMode1_actionPerformed(e);
      }
    });
    jButtonTrajetMode1.setBorder(BorderFactory.createRaisedBevelBorder());
    jButtonTrajetMode1.setToolTipText("Afiche par Trajet (poit relier)");
    jButtonTrajetMode1.setText("En trajet");
    jButtonTrajetMode1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonTrajetMode1_actionPerformed(e);
      }
    });
    choiceZoomOriginal.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        choiceZoomOriginal_itemStateChanged(e);
      }
    });
    menuComConfig.setLabel("Com Config");
    menuComConfig.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        menuComConfig_actionPerformed(e);
      }
    });
    menuItem7.setLabel("Fermer Une carte");
    menuItem2.setLabel("cadrer les blocs");
    menuItem2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        menuItem2_actionPerformed(e);
      }
    });
    menuItem2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        menuItem2_actionPerformed(e);
      }
    });
    menuFermerTraget.setLabel("Fermer target");
    menuFermerTraget.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        menuFermerTraget_actionPerformed(e);
      }
    });
    menuOuvrir.setLabel("Ovrir target");
    menuOuvrir.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        menuOuvrir_actionPerformed(e);
      }
    });
    menuOuvrir.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        menuOuvrir_actionPerformed(e);
      }
    });


    menu6.setLabel("Reglage");
    menu3.setLabel("Traget");
    menu2.setLabel("Carte");
    menuItem1.setLabel("SMS-Data-Cable");
    menuItem1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        menuItem1_actionPerformed(e);
      }
    });
    menu4.setLabel("Gestion flot");
    menuItem3.setLabel("Telecharger Simple position");
    menuItem4.setLabel("Telecharger  Trajet complet");
    menuItem5.setLabel("Envoi Zone Alarme");
    menu1.setLabel("Aide");
    menuItem6.setLabel("A propos de chounaba");
    menuItem6.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        menuItem6_actionPerformed(e);
      }
    });
    choiceMarkage.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        choiceMarkage_itemStateChanged(e);
      }
    });
    menuItem8.setLabel("Ajouter carte Mode Calage");
    menuItem9.setLabel("Select carte pour affichage");
    menuItem10.setLabel("enregistre paramatre de Calage");
    this.setTitle("Chounaba 38");
    panelOption1.add(jButtonnavgMode1, null);
    panelOption1.add(jButtonPoint1, null);
    panelOption1.add(jButtonTrajetMode1, null);
    panelOption1.add(jButtonDeplaceMode1, null);
    panelOption1.add(jButtonZoomMode1, null);
    panelOption1.add(jButtonCenter1, null);
    panelOption1.add(jButtonZoomPlus1, null);
    panelOption1.add(choiceZoomOriginal, null);
    panelOption1.add(jButtonZoomMoin1, null);
    panelOption1.add(choiceMarkage, null);
    menuBar1.add(menu2);
    menuBar1.add(menu3);
    menuBar1.add(menu6);
    menuBar1.add(menu4);
    menuBar1.add(menu1);
    menu6.add(menuComConfig);
    menu6.add(menuItem1);
    menu3.add(menuOuvrir);
    menu3.add(menuFermerTraget);
    menu2.add(menuItem2);
    menu2.add(menuItem8);
    menu2.add(menuItem9);
    menu2.add(menuItem10);
    menu2.add(menuItem7);
    this.getContentPane().add(panelOption1, BorderLayout.NORTH);
    menu4.add(menuItem3);
    menu4.add(menuItem4);
    menu4.add(menuItem5);
    menu1.add(menuItem6);
    this.setMenuBar(menuBar1);
  }
int modePaint =1;
  void this_mouseClicked(MouseEvent e) {

      if (modePaint ==0)//si on est en mode Zoom
      {

        if (e.BUTTON1_MASK == e.getModifiers() )
          SyncroPaint.Zoom*=2;

        else   SyncroPaint.Zoom/=2;

       //calcule le deplasement du origine pour rester a sa position lors de la zoom
        SyncroPaint.ORigineEcronX-=(SyncroPaint.ORigineEcronX-e.getX())-(SyncroPaint.ORigineEcronX-e.getX())*2;
        SyncroPaint.ORigineEcronY-=(SyncroPaint.ORigineEcronY-e.getY())-(SyncroPaint.ORigineEcronY-e.getY())*2;

        SyncroPaint.ImageZommed=true;
      }

       //positione l'image au centre de la zoom
        SyncroPaint.ORigineEcronX-=e.getX()-(this.getWidth()/2);
        SyncroPaint.ORigineEcronY-=e.getY()-(this.getHeight()/2);

//        System.out.println("centrage x= " +SyncroPaint.ORigineEcronX +" y ="+SyncroPaint.ORigineEcronY+" zomm ="+SyncroPaint.Zoom);
        repaint();

  }
int div =0;
int temp=0;
int xmax=0;
int ymax=0;
int i=0;
boolean rectangleZoom=false;
int x=0;
int y=0;
  private JButton jButtonPoint1 = new JButton();
  private JButton jButtonnavgMode1 = new JButton();
  private JButton jButtonZoomPlus1 = new JButton();
  private JButton jButtonCenter1 = new JButton();
  private JButton jButtonDeplaceMode1 = new JButton();
  private Panel panelOption1 = new Panel();
  private JButton jButtonZoomMoin1 = new JButton();
  private JButton jButtonZoomMode1 = new JButton();
  private JButton jButtonTrajetMode1 = new JButton();
  private TitledBorder titledBorder1;
  private Choice choiceZoomOriginal = new Choice();
  private MenuItem menuComConfig = new MenuItem();
  private MenuItem menuItem7 = new MenuItem();
  private MenuItem menuItem2 = new MenuItem();
  private MenuBar menuBar1 = new MenuBar();
  private MenuItem menuFermerTraget = new MenuItem();
  private MenuItem menuOuvrir = new MenuItem();
  private Menu menu6 = new Menu();
  private Menu menu3 = new Menu();
  private Menu menu2 = new Menu();

  void this_mouseDragged(MouseEvent e) {
//     System.out.println("ici je suis mouseDragged "+(e.getX()-x) +"   "+(e.getY()-y));
     if (modePaint==1)//si on est dans le mode mov
     {
       temp+=Math.abs(e.getX()-x)+Math.abs(e.getY()-y);
       SyncroPaint.ORigineEcronX+=e.getX()-x;
       SyncroPaint.ORigineEcronY+=e.getY()-y;
       x=e.getX();
       y=e.getY();
       if (temp>20)
       {
      if (div++>4){

        runable.resume();
      div=0;
 //     System.gc();System.gc();
      System.out.println("telechargement");

      }
      else repaint(10);
      temp=0;
       }
     }
     else
     {
       rectangleZoom=true;
       Graphics g=this.getGraphics() ;
       temp+=e.getX()-x+e.getY()-y;
       if (xmax< e.getX()-x)  xmax=e.getX()-x;
       if (ymax< e.getY()-y)  ymax=e.getY()-y;

       if (temp>1000)
       {
  //      System.out.println("Temp "+temp);
         /*  repaint(x,y, e.getX()-x,1);
           repaint(x,y,1,e.getY()-y);
           repaint(x,e.getY(),e.getX()-x,1);
           repaint(e.getX(),y,1,e.getY()-y);
         */
         repaint(x,y,xmax+1,ymax+1);
           temp=0;
           xmax=0;
           ymax=0;
       }

       g.drawRect(x,y, e.getX()-x,e.getY()-y);

     }
  }

  void this_mousePressed(MouseEvent e) {
    //this.setCursor(13);

    if (modePaint==1)  this.setCursor(cFermer);

//   System.out.println("this_mousePressed  "+i);
    x=e.getX();
   y=e.getY();
  }

  void this_mouseReleased(MouseEvent e) {
    //System.out.println("repX :"+SyncroPaint.ORigineEcronX+"   repY :"+SyncroPaint.ORigineEcronY);
    if (modePaint==1) this.setCursor(cOver);
    else
      if (rectangleZoom){
      rectangleZoom=false;
      //System.out.println("ZooM locac avec rectangle="+SyncroPaint.Zoom);


      SyncroPaint.Zoom*=1.2;//(int)(SyncroPaint.Zoom*ZomCart*I.getWidth(this)/(e.getX()-x));
      SyncroPaint.ImageZommed=true;//pour reloader l'image

      //calcule le deplasement du origine pour rester a sa position lors de la zoom
      SyncroPaint.ORigineEcronX-=(SyncroPaint.ORigineEcronX-e.getX())-(SyncroPaint.ORigineEcronX-e.getX())*2;
      SyncroPaint.ORigineEcronY-=(SyncroPaint.ORigineEcronY-e.getY())-(SyncroPaint.ORigineEcronY-e.getY())*2;

       //positione l'image au centre de la zoom de notre carre
      SyncroPaint.ORigineEcronX-=e.getX()-((e.getX()-x)/2)-(this.getWidth()/2);
      SyncroPaint.ORigineEcronY-=e.getY()-((e.getY()-y)/2)-(this.getHeight()/2);

       runable.resume();
    }

  }


  void jButtonPoint1_actionPerformed(ActionEvent e) {
    SyncroPaint.ShowPoint=!SyncroPaint.ShowPoint;
    repaint();
  }
  void jButtonnavgMode1_actionPerformed(ActionEvent e) {
    SyncroPaint.navigationModeSet=true;
    repaint();
  }
  void jButtonZoomPlus1_actionPerformed(ActionEvent e) {
    SyncroPaint.Zoom*=2;
    //calcule le deplasement du origine pour rester a sa position lors de la zoom
    //positione l'image au centre de la zoom
    SyncroPaint.ORigineEcronX-=(SyncroPaint.ORigineEcronX-(this.getWidth()/2))-(SyncroPaint.ORigineEcronX-(this.getWidth()/2))*2;
    SyncroPaint.ORigineEcronY-=(SyncroPaint.ORigineEcronY-(this.getWidth()/2))-(SyncroPaint.ORigineEcronY-(this.getWidth()/2))*2;

    SyncroPaint.ImageZommed=true;
    runable.resume();
  }
  void jButtonCenter1_actionPerformed(ActionEvent e) {
//positione l'image au centre de la zoom
repaint();
  }
  void jButtonDeplaceMode1_actionPerformed(ActionEvent e) {
    modePaint=1;//utiliser la sourie pour le movement deplacement de la image
    this.setCursor(cOver);

  }
  void jButtonZoomMoin1_actionPerformed(ActionEvent e) {
    SyncroPaint.Zoom/=2;

    //calcule le deplasement du origine pour rester a sa position lors de la zoom
    //positione l'image au centre de la zoom
    SyncroPaint.ORigineEcronX-=(SyncroPaint.ORigineEcronX-(this.getWidth()/2))-(SyncroPaint.ORigineEcronX-(this.getWidth()/2))/2;
    SyncroPaint.ORigineEcronY-=(SyncroPaint.ORigineEcronY-(this.getWidth()/2))-(SyncroPaint.ORigineEcronY-(this.getWidth()/2))/2;

    //metre a la Zoom
    if (SyncroPaint.Zoom!=1/(0.6381445330849965 * 40/100 * (100/ SyncroPaint.workCart.NotreCarte[0].ValeurZoomBloc[SyncroPaint.workCart.NotreCarte[0].indiceZoomActuelle])))
      SyncroPaint.workCart.ScallingImage(SyncroPaint.Zoom,tracker,observer);

    SyncroPaint.trajet.CalculeDistanceZoomer(SyncroPaint.Zoom);

    runable.resume();

  }
  void jButtonZoomMode1_actionPerformed(ActionEvent e) {
  modePaint=0;//mode Zoom (choit de la rectangle a Zommer a laide de la sourie
  this.setCursor(cZome);
  }
  void jButtonTrajetMode1_actionPerformed(ActionEvent e) {
    SyncroPaint.ShowTrajet=!SyncroPaint.ShowTrajet;
    repaint();
  }

  void choiceZoomOriginal_itemStateChanged(ItemEvent e) {
    int choit =choiceZoomOriginal.getSelectedIndex();
    int Zoom=1;
    switch (choit) {
      case 0:    Zoom=10; break;
      case 1:    Zoom=25; break;
      case 2:    Zoom=50; break;
      case 3:    Zoom=100;break;
    }
 double porsentage= Zoom/SyncroPaint.workCart.NotreCarte[0].ValeurZoomBloc[SyncroPaint.workCart.NotreCarte[0].indiceZoomActuelle];
 //SyncroPaint.workCart.setNewReload();

 //calcule le deplasement du origine pour rester a sa position lors de la zoom
 //positione l'image au centre de la zoom
 SyncroPaint.ORigineEcronX-=(SyncroPaint.ORigineEcronX-90-(this.getWidth()/2))-(-90+SyncroPaint.ORigineEcronX-(this.getWidth()/2))*porsentage;
 SyncroPaint.ORigineEcronY-=(SyncroPaint.ORigineEcronY-0-(this.getWidth()/2))-(-0+SyncroPaint.ORigineEcronY-(this.getWidth()/2))*porsentage;

 SyncroPaint.ImageZommed=true;
 SyncroPaint.Zoom=1/(0.6381445330849965 * 40/100 * (100/Zoom));
 SyncroPaint.workCart.useOriginalImage(SyncroPaint.Zoom,tracker,observer);
 SyncroPaint.trajet.CalculeDistanceZoomer(SyncroPaint.Zoom);
 SyncroPaint.workCart.setNewDuplic(choit);

 System.out.println("choit"+choit+"   z="+Zoom);
 runable.resume();

 }

  void menuItem2_actionPerformed(ActionEvent e) {
    SyncroPaint.voireCarre=!SyncroPaint.voireCarre;
    if (!menuItem2.equals("sup cadrage"))
      menuItem2.setLabel("sup cadrage");
    else menuItem2.setLabel("cadrer les block");
    //repaint();juste pour forcer le paint
    runable.resume();
  }

  void menuFermerTraget_actionPerformed(ActionEvent e) {
    SyncroPaint.trajet.nombrePointGPS=0;
    repaint();
  }
static ChoitFile ovrirTraget;
  private MenuItem menuItem1 = new MenuItem();
  private Menu menu4 = new Menu();
  private MenuItem menuItem3 = new MenuItem();
  private MenuItem menuItem4 = new MenuItem();
  private MenuItem menuItem5 = new MenuItem();
  private Menu menu1 = new Menu();
  private MenuItem menuItem6 = new MenuItem();
  private Choice choiceMarkage = new Choice();
  private MenuItem menuItem8 = new MenuItem();
  private MenuItem menuItem9 = new MenuItem();
  private MenuItem menuItem10 = new MenuItem();

  void menuOuvrir_actionPerformed(ActionEvent e) {
    //ferme le corant trajet
    SyncroPaint.trajet.nombrePointGPS=0;
    repaint();

    ovrirTraget = new ChoitFile(1);
    ovrirTraget.setTitle("donner le ficher a ouvrir");
    ovrirTraget.show();


  }

  void choiceMarkage_itemStateChanged(ItemEvent e) {
       SyncroPaint.algoMarkage=choiceMarkage.getSelectedIndex()-1;
  }

  void menuItem6_actionPerformed(ActionEvent e) {
  Apropos cool=new Apropos();
  cool.setLocation((this.getWidth()-cool.getWidth())/2,(getHeight()-cool.getHeight())/2);
  cool.show();
  }

  void menuItem1_actionPerformed(ActionEvent e) {
    TypeConnection fenetre=new TypeConnection();
    fenetre.show();

  }

  void menuComConfig_actionPerformed(ActionEvent e) {
    ComConfig femetre=new ComConfig();
    femetre.show();
  }
}

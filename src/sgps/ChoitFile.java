package sgps;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


/**
 * <p>Titre : </p>
 * <p>Description : </p>
 * <p>Copyright : Copyright (c) 2003</p>
 * <p>Soci�t� : </p>
 * @author non attribu�
 * @version 1.0
 */


public class ChoitFile extends Frame {
  JFileChooser jFileChooser1 = new JFileChooser();
int type;
  public ChoitFile(int type) {//1 laodTrajectoire
                            //2 laodSmsPoint
    try {
      this.type=type;
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    this.setBackground(Color.lightGray);
    this.setSize(550,300);
    jFileChooser1.setBackground(Color.lightGray);


    jFileChooser1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jFileChooser1_actionPerformed(e);
      }
    });
    this.add(jFileChooser1, BorderLayout.CENTER);


  }

  void jFileChooser1_actionPerformed(ActionEvent e) {

     System.out.println("Fichier Selectionner: "+ jFileChooser1.getSelectedFile() );

     this.hide();



     try{
  //     if (type==1) {
         CadreMain.SyncroPaint.loadTrajet(jFileChooser1.getSelectedFile().toString());

    //   }

//       if (type==2) PaintFrame.laodSmsPoint( jFileChooser1.getSelectedFile().toString() );
//       if (type==3) PaintFrame.SaveSmsPoint( jFileChooser1.getSelectedFile().toString() );
//       if (type==4) PaintFrame.dowloandTarget( jFileChooser1.getSelectedFile().toString() );
     }
    catch (Exception eee)
    {System.out.println("problem lecture de fichier");}


  }
}

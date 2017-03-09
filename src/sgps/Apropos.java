package sgps;

import java.awt.*;
import com.borland.jbcl.layout.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

/**
 * <p>Titre : </p>
 * <p>Description : </p>
 * <p>Copyright : Copyright (c) 2003</p>
 * <p>Société : </p>
 * @author non attribué
 * @version 1.0
 */

public class Apropos extends JFrame {
  private XYLayout xYLayout1 = new XYLayout();
  private Label label1 = new Label();
  private Label label2 = new Label();
  private Label label3 = new Label();
  private Label label4 = new Label();
  private JButton jButton1 = new JButton();
  private Label label5 = new Label();
  private Label label6 = new Label();
  private JButton jButton2 = new JButton();
  private TitledBorder titledBorder1;
  private TitledBorder titledBorder2;

  public Apropos() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    titledBorder1 = new TitledBorder("");
    titledBorder2 = new TitledBorder("");
    this.getContentPane().setLayout(xYLayout1);
    label1.setFont(new java.awt.Font("Dialog", 1, 12));
    label1.setForeground(Color.white);
    label1.setText("Copyright (c) 2003 -2004");
    label2.setForeground(Color.white);
    label2.setText("Chounaba Software");
    label3.setFont(new java.awt.Font("Dialog", 1, 12));
    label3.setForeground(Color.white);
    label3.setText("My home Corporation");
    label4.setFont(new java.awt.Font("Dialog", 2, 12));
    label4.setForeground(Color.white);
    label4.setText("All Right Reserved");
    this.getContentPane().setBackground(Color.black);
    this.setForeground(Color.black);
    this.setResizable(false);
    this.setTitle("383838838388388883888388888838888888888833333333883838388383838833883838");
    jButton1.setBackground(Color.black);
    jButton1.setForeground(Color.darkGray);
    jButton1.setBorder(null);
    jButton1.setIcon(new ImageIcon("algo.class"));
    xYLayout1.setWidth(429);
    xYLayout1.setHeight(366);
    label5.setFont(new java.awt.Font("Dialog", 3, 14));
    label5.setForeground(Color.white);
    label5.setText("Developed by CHOUBA Nabil");
    label6.setFont(new java.awt.Font("Dialog", 2, 12));
    label6.setForeground(Color.white);
    label6.setText("N_380@yahoo.fr");
    jButton2.setBackground(Color.darkGray);
    jButton2.setFont(new java.awt.Font("Serif", 2, 12));
    jButton2.setForeground(Color.red);
    jButton2.setBorder(BorderFactory.createRaisedBevelBorder());
    jButton2.setRequestFocusEnabled(false);
    jButton2.setText("je suis contre le peratage");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton2_actionPerformed(e);
      }
    });
    this.getContentPane().add(label5,                     new XYConstraints(232, 317, 265, 26));
    this.getContentPane().add(label6,                                                                                                                   new XYConstraints(326, 339, 150, 24));
    this.getContentPane().add(label4,                                                                               new XYConstraints(53, 338, 186, 30));
    this.getContentPane().add(label3,              new XYConstraints(2, 316, 152, 30));
    this.getContentPane().add(label2,     new XYConstraints(16, 297, 119, 28));
    this.getContentPane().add(label1,      new XYConstraints(4, 278, 145, 25));
    this.getContentPane().add(jButton2,  new XYConstraints(220, 285, 190, 22));
    this.getContentPane().add(jButton1,        new XYConstraints(28, 13, 389, 263));
    this.setSize(440, 390);

  }

  void jButton2_actionPerformed(ActionEvent e) {
    this.hide();
  }
}
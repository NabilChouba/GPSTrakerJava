package sgps;

import java.awt.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;

/**
 * <p>Titre : </p>
 * <p>Description : </p>
 * <p>Copyright : Copyright (c) 2003</p>
 * <p>Société : </p>
 * @author non attribué
 * @version 1.0
 */

public class ComConfig extends JFrame {
  private XYLayout xYLayout1 = new XYLayout();
  private Button button1 = new Button();
  private Choice choiceNumCom = new Choice();
  private Choice choiceVittes = new Choice();
  private Label label1 = new Label();

  public ComConfig() {
    try {
      jbInit();
      choiceNumCom.add("4800");choiceNumCom.add("9600");choiceNumCom.add("11200");
      choiceVittes.add("Com1");choiceVittes.add("Com2");choiceVittes.add("Com3");
      this.setSize(226,182);
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    button1.setLabel("OK");
    button1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        button1_actionPerformed(e);
      }
    });
    this.getContentPane().setLayout(xYLayout1);
    choiceVittes.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        choiceVittes_itemStateChanged(e);
      }
    });
    xYLayout1.setWidth(227);
    xYLayout1.setHeight(184);
    this.setResizable(false);
    this.setTitle("Cnfiguration du Com");
    label1.setFont(new java.awt.Font("Dialog", 1, 12));
    label1.setText("Parametre du Com :");
    this.getContentPane().add(choiceVittes, new XYConstraints(49, 74, 129, 24));
    this.getContentPane().add(button1, new XYConstraints(47, 106, 131, 34));
    this.getContentPane().add(choiceNumCom, new XYConstraints(49, 40, 129, 25));
    this.getContentPane().add(label1,  new XYConstraints(14, 5, 122, 26));
  }

  void choiceVittes_itemStateChanged(ItemEvent e) {

  }

  void button1_actionPerformed(ActionEvent e) {
    this.hide();
  }
}
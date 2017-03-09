package sgps;

import java.awt.*;
import javax.swing.JFrame;
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

public class TypeConnection extends JFrame {
  private CheckboxGroup checkboxGroup1 = new CheckboxGroup();
  private Checkbox checkbox1 = new Checkbox();
  private XYLayout xYLayout1 = new XYLayout();
  private Checkbox checkDircect = new Checkbox();
  private Checkbox checSMS = new Checkbox();
  private Checkbox checkData = new Checkbox();
  private Button button1 = new Button();
  private Label label1 = new Label();

  public TypeConnection() {
    try {
      jbInit();
      this.setSize(320,210);
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    checkDircect.setCheckboxGroup(checkboxGroup1);
    checkDircect.setLabel("Mode Direct Cable");
    this.getContentPane().setLayout(xYLayout1);
    checkbox1.setLabel("checkbox1");
    checSMS.setCheckboxGroup(checkboxGroup1);
    checSMS.setLabel("Mode Sms");
    checSMS.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        checSMS_itemStateChanged(e);
      }
    });
    checkData.setCheckboxGroup(checkboxGroup1);
    checkData.setLabel("Mode Data");
    button1.setLabel("OK");
    button1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        button1_actionPerformed(e);
      }
    });
    xYLayout1.setWidth(323);
    xYLayout1.setHeight(201);
    this.setTitle("Configuration de type de comminucation");
    label1.setFont(new java.awt.Font("Dialog", 1, 12));
    label1.setText("Type connection :");
    this.getContentPane().add(button1, new XYConstraints(43, 146, 120, 32));
    this.getContentPane().add(label1,  new XYConstraints(18, 7, 132, 12));
    this.getContentPane().add(checkData,  new XYConstraints(46, 103, 194, 26));
    this.getContentPane().add(checkDircect, new XYConstraints(45, 31, 186, 24));
    this.getContentPane().add(checSMS, new XYConstraints(45, 67, 188, 22));
  }

  void button1_actionPerformed(ActionEvent e) {
    this.hide();
  }

  void checSMS_itemStateChanged(ItemEvent e) {

  }
}
package ui;

import busisness.ContactBusissness;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactForm extends JFrame {

    private JPanel rootPainel;
    private JTextField textName;
    private JTextField textPhone;
    private JButton buttonSave;
    private JButton buttonCancel;

    private ContactBusissness mContractBusissness;

    public ContactForm() {
        setContentPane(rootPainel);
        setSize(500, 250);
        setVisible(true);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mContractBusissness = new ContactBusissness();
        setListeners();
    }

    private void setListeners() {

        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    String name = textName.getText();
                    String phone = textPhone.getText();

                    mContractBusissness.save(name,phone);

                    new MainForm();
                    dispose();
                } catch (Exception ecpx){
                    JOptionPane.showMessageDialog(new JFrame(),ecpx.getMessage());
                }
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainForm();
                dispose();
            }
        });
    }
}

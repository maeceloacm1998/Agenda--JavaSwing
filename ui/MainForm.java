package ui;

import busisness.ContactBusissness;
import entity.ContactEntity;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainForm extends JFrame {
    private JPanel rootPainel;
    private JButton buttonNewContact;
    private JButton buttonRemove;
    private JTable tableContacts;
    private JLabel labelConstactCount;

    private ContactBusissness mContactBusissness;

    private String name;
    private String phone;

    public MainForm() {
        setContentPane(rootPainel);
        setSize(500, 250);
        setVisible(true);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mContactBusissness = new ContactBusissness();

        setListeners();
        loadContacts();
    }

    private void setListeners() {
        buttonNewContact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ContactForm();
                dispose();
            }
        });

        tableContacts.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {

                    if (tableContacts.getSelectedRow() != -1) {
                        name = tableContacts.getValueAt(tableContacts.getSelectedRow(), 0).toString();
                        phone = tableContacts.getValueAt(tableContacts.getSelectedRow(), 1).toString();
                    }

                }
            }
        });

        buttonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    mContactBusissness.delete(name, phone);
                    loadContacts();

                    name = "";
                    phone = "";
                } catch (Exception ecpx) {
                    JOptionPane.showMessageDialog(new JFrame(), ecpx.getMessage());
                }

            }
        });
    }

    private void loadContacts() {
        List<ContactEntity> contactList = mContactBusissness.getList();

        String[] columNames = {"Nome", "Telefone"};

        DefaultTableModel model = new DefaultTableModel(new Object[0][0], columNames);

        for (ContactEntity i : contactList) {
            Object[] o = new Object[2];

            o[0] = i.getName();
            o[1] = i.getPhone();

            model.addRow(o);
        }

        tableContacts.clearSelection();
        tableContacts.setModel(model);
        labelConstactCount.setText(mContactBusissness.getContactCountDescription());
    }
}

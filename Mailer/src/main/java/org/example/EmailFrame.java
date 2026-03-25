package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EmailFrame extends JFrame {

    private JTextField toField;
    private JTextField subjectField;
    private JTextArea bodyArea;
    private JButton sendButton;

    public EmailFrame() {
        setTitle("Gmail SMTP");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JPanel topPanel = new JPanel(new GridLayout(2, 2));

        topPanel.add(new JLabel("Destinatario:"));
        toField = new JTextField();
        topPanel.add(toField);

        topPanel.add(new JLabel("Oggetto:"));
        subjectField = new JTextField();
        topPanel.add(subjectField);

        add(topPanel, BorderLayout.NORTH);

        bodyArea = new JTextArea();
        add(new JScrollPane(bodyArea), BorderLayout.CENTER);

        sendButton = new JButton("Invia");
        add(sendButton, BorderLayout.SOUTH);

        sendButton.addActionListener(e -> sendMail());
    }

    private void sendMail() {
        try {
            EmailSender.send(
                    toField.getText(),
                    subjectField.getText(),
                    bodyArea.getText()
            );
            JOptionPane.showMessageDialog(this, "Email inviata!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Errore: " + ex.getMessage());
        }
    }
}
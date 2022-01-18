package com.Library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Main {
    public static void main(String[] args){

        Lanterna lanterna = new Lanterna();
        Swing swing = new Swing();

        JFrame window = new JFrame("Choose UI");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel();

        GridLayout menuLayout = new GridLayout(0,3);

        mainPanel.add(new JLabel("Which UI you want to choose?"));

        JButton graphic = new JButton("GRAPHIC");
        mainPanel.add(graphic);
        graphic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swing.swingView();
            }
        });

        JButton text = new JButton("TEXT");
        mainPanel.add(text);
        text.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    lanterna.lanternaView();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        window.add(BorderLayout.NORTH, mainPanel);
        window.setSize(600, 150);
        window.setVisible(true);
    }
}
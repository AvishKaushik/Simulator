package com.csa.simulator.components;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.util.ArrayList;

/**
 *  This file contains instructions for printing and connecting to input and output devices while the simulator is operating.
 */
public class Devices extends JFrame{
    private final JTextArea ConsoleOut;
    private final JTextArea ConsoleIn;
    public Devices(){
        super("Console");
        this.setSize(960,480);
        this.setLayout(null);
        ArrayList<JPanel> panel = new ArrayList<JPanel>();
        panel.add(new JPanel());
        panel.add(new JPanel());
        panel.add(new JPanel());

        TitledBorder ConsoleOutBorder = new TitledBorder("Console Printer");
        ConsoleOutBorder.setTitleJustification(TitledBorder.CENTER);
        ConsoleOutBorder.setTitlePosition(TitledBorder.TOP);
        TitledBorder ConsoleInBorder = new TitledBorder("Console Keyboard");
        ConsoleInBorder.setTitleJustification(TitledBorder.CENTER);
        ConsoleInBorder.setTitlePosition(TitledBorder.TOP);

        ConsoleIn = new JTextArea("", 1, 20);
        ConsoleOut = new JTextArea(16,38);
        ConsoleOut.setFont(new Font("Courrier New",Font.BOLD,14));
        //ConsoleOut.setBounds(0,0,350,300);
        ConsoleOut.setEditable(false);
        ConsoleOut.setBackground(Color.BLACK);
        ConsoleOut.setForeground(Color.green);

        panel.get(0).setBorder(ConsoleOutBorder);
        panel.get(0).add(ConsoleOut);

        panel.get(1).setBorder(ConsoleInBorder);
        panel.get(1).add(ConsoleIn);

        panel.get(0).setBounds(10, 10, 480, 340);
        panel.get(1).setBounds((480-250)/2+10, 360, 250, 60);
        panel.get(2).setBounds(500,10,440,420);
        for(int i=0;i<3;i++)
            this.add(panel.get(i));
    }
    public void emptyConsole(){
        ConsoleOut.setText(null);
        ConsoleIn.setText(null);
    }
}

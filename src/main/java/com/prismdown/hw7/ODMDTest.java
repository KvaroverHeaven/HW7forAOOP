/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prismdown.hw7;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author kvarnoel
 */
public class ODMDTest {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException
                    | IllegalAccessException ex) {
                ex.printStackTrace();
            }
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        ODMDView odmdView = new ODMDView();
        odmdView.setDefaultCloseOperation(EXIT_ON_CLOSE);
        odmdView.setSize(1280, 720);
        odmdView.setLocationByPlatform(true);
        odmdView.setVisible(true);
    }

}

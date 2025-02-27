package br.code.dio;

import br.code.dio.ui.custom.frame.MainFrame;
import br.code.dio.ui.custom.panel.MainPanel;

import javax.swing.*;
import java.awt.*;

public class UIMain {
    public static void main(String[] args) {
        var dimension = new Dimension(600, 600);
        MainPanel panel = new MainPanel(dimension);
        JFrame mainFrame = new MainFrame(dimension, panel);
        mainFrame.revalidate();
        mainFrame.repaint();

    }
}

package ru.vsu.csf.sapegin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame {
    private final DrawPanel drawPanel = new DrawPanel();
    private final Panel panelOfTools = new Panel();

    private final JRadioButton radioButtonTriangle = new JRadioButton("Треугольник");
    private final JRadioButton radioButtonRectangle = new JRadioButton("Прямоугольник");
    private final JButton buttonCallPanelParametersOfFigure = new JButton("Задать координаты");
    private final JButton buttonDrawFigure = new JButton("Нарисовать");
    private final PanelParametersOfFigure panelParametersOfFigure = new PanelParametersOfFigure();

    private final PanelTransformations panelForTransformations = new PanelTransformations(this);

    JPanel panelForRadioButtons = new JPanel(new GridLayout(3, 10));

    public DrawPanel getDrawPanel() {
        return drawPanel;
    }

    public Window() throws HeadlessException {
        ButtonGroup radio = new ButtonGroup();
        radio.add(radioButtonTriangle);
        radio.add(radioButtonRectangle);

        Dimension minDimension = new Dimension(1250, 500);
        Dimension maxDimension = new Dimension(100, 500);

        JScrollPane scrollPaneForDrawPanel = new JScrollPane(); //про это почитать
        scrollPaneForDrawPanel.setMinimumSize(minDimension);
        scrollPaneForDrawPanel.setViewportView(drawPanel);  //про это почитать

        JScrollPane scrollPaneForPanelOfTools = new JScrollPane();
        scrollPaneForPanelOfTools.setMaximumSize(maxDimension);
        scrollPaneForPanelOfTools.setViewportView(panelOfTools);
        panelOfTools.setLayout(new BoxLayout(panelOfTools, BoxLayout.Y_AXIS));

        panelForRadioButtons.setMaximumSize(new Dimension(200, 90));
        panelForRadioButtons.add(radioButtonTriangle);
        panelForRadioButtons.add(radioButtonRectangle);
        panelForRadioButtons.add(buttonCallPanelParametersOfFigure);

        panelOfTools.add(panelForRadioButtons);

        panelParametersOfFigure.setLayout(new GridLayout(3,6));
        panelParametersOfFigure.setMaximumSize(new Dimension(300, 100));

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPaneForDrawPanel, scrollPaneForPanelOfTools);

        buttonCallPanelParametersOfFigure.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                callPanelParametersOfFigure();
            }
        });

        buttonDrawFigure.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawFigure();
                panelOfTools.add(panelForTransformations);
                panelOfTools.revalidate();
            }
        });
        this.add(splitPane);
        this.pack();
        this.setVisible(true);
    }

    private void callPanelParametersOfFigure() {
        if (radioButtonTriangle.isSelected()) {
            panelParametersOfFigure.displayFieldsForTriangle();
        } else if (radioButtonRectangle.isSelected()) {
            panelParametersOfFigure.displayFieldsForRectangle();
        }
        panelOfTools.add(panelParametersOfFigure);
//        panelOfTools.add(Box.createVerticalStrut(10));
        panelOfTools.add(buttonDrawFigure);
//        panelOfTools.add(Box.createVerticalStrut(10));
        buttonDrawFigure.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        buttonDrawFigure.setAlignmentY(JPanel.BOTTOM_ALIGNMENT);
        panelOfTools.revalidate();
    }

    private void drawFigure() {
        drawPanel.draw(panelParametersOfFigure.getMatrixOfCoordinates());
    }
}

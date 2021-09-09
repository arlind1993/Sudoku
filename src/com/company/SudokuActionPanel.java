package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class SudokuActionPanel extends JPanel {

    public static final int WIDTH=265;
    public static final int HEIGHT=395;

    public final int rows = 6;
    public final int cols = 4;

    public final int offset = 5;

    private JPanelGroup panelGroup;
    private JCustomButton [][] actionButtons;
    private MyGridLayout<JPanel> jPanelMyGridLayout;

    private JGrid jGrid;
    public SudokuActionPanel(JGrid jGrid){
        this.jGrid=jGrid;
        this.setLayout(null);
        initButtons();

        this.setSize(WIDTH,HEIGHT);
        this.setBackground(Color.DARK_GRAY);
    }

    private void initButtons() {
        panelGroup = new JPanelGroup();
        actionButtons= new JCustomButton[rows][cols];
        jPanelMyGridLayout = new MyGridLayout<>(rows,cols, offset, actionButtons);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                actionButtons[i][j]= new JCustomButton();
                actionButtons[i][j].setBackground(MyColorPalette.WHITE);
                jPanelMyGridLayout.setLocationOfType(i, j);
                this.add(actionButtons[i][j]);
            }
        }

        actionButtons[0][3].setName("Digit");
        actionButtons[1][3].setName("Corner");
        actionButtons[2][3].setName("Center");
        actionButtons[3][3].setName("Color");

        actionButtons[0][0].setName("1");
        actionButtons[0][1].setName("2");
        actionButtons[0][2].setName("3");
        actionButtons[1][0].setName("4");
        actionButtons[1][1].setName("5");
        actionButtons[1][2].setName("6");
        actionButtons[2][0].setName("7");
        actionButtons[2][1].setName("8");
        actionButtons[2][2].setName("9");
        actionButtons[3][1].setName("0");

        actionButtons[3][2].setName("Clear");

        actionButtons[3][0].setName("-");

        actionButtons[4][0].setName("Undo");
        actionButtons[4][1].setName("Redo");
        actionButtons[4][2].setName("Check");
        actionButtons[4][3].setName("Select");

        panelGroup.add(actionButtons[0][3]);
        panelGroup.add(actionButtons[1][3]);
        panelGroup.add(actionButtons[2][3]);
        panelGroup.add(actionButtons[3][3]);
        panelGroup.setSelected(actionButtons[0][3]);
        updateToggleColor();
        for (int i = 0; i <= 3; i++) {
            int finalI = i;
            actionButtons[i][3].setMouseListener(new MouseListener() {
                @Override public void mouseClicked(MouseEvent e) {

                }
                @Override public void mousePressed(MouseEvent e) {
                    panelGroup.setSelected(actionButtons[finalI][3]);
                    updateToggleColor();
                }
                @Override public void mouseReleased(MouseEvent e) {

                }
                @Override public void mouseEntered(MouseEvent e) {
                    actionButtons[finalI][3].setHovering(true);
                    updateToggleColor();
                }
                @Override public void mouseExited(MouseEvent e) {
                    actionButtons[finalI][3].setHovering(false);
                    updateToggleColor();
                }
            });
        }

        actionButtons[4][3].setMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                actionButtons[4][3].setClicked(!actionButtons[4][3].isClicked());
                jGrid.setMarkPenActivated(actionButtons[4][3].isClicked());
                updateToggleColor();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                actionButtons[4][3].setHovering(false);
                updateToggleColor();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                actionButtons[4][3].setHovering(true);
                updateToggleColor();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                updateToggleColor();
            }
        });

        Integer[][] numberMapping= new Integer[][]{
                {0,0}, {0,1}, {0,2},
                {1,0}, {1,1}, {1,2},
                {2,0}, {2,1}, {2,2},
                       {3,1}
        };
        updateCLickNrColor();
        for (int i = 0; i < numberMapping.length; i++) {
            JCustomButton actButton = actionButtons[numberMapping[i][0]][numberMapping[i][1]];
                actButton.setMouseListener(new MouseListener() {
                    @Override public void mouseClicked(MouseEvent e) {

                    }
                    @Override public void mousePressed(MouseEvent e) {
                        for (Cell markedCell : jGrid.getGrid().getMarkedCells()) {
                            if (panelGroup.getActualButtonSelected().getName().equals("Digit")) {
                                if (markedCell.getFinalDigit()==null){
                                    markedCell.setFinalDigit(Integer.parseInt(actButton.getName()));
                                }else{
                                    if (markedCell.getFinalDigit()==Integer.parseInt(actButton.getName())){
                                        markedCell.setFinalDigit(null);
                                    }else{
                                        markedCell.setFinalDigit(Integer.parseInt(actButton.getName()));
                                    }
                                }

                            }else if (panelGroup.getActualButtonSelected().getName().equals("Corner")) {

                                if (markedCell.getFinalDigit()==null){
                                    if (markedCell.getCornerDigits().contains(Integer.parseInt(actButton.getName()))){
                                        markedCell.removeCornerDigit(Integer.parseInt(actButton.getName()));
                                    }else{
                                        markedCell.addCornerDigit(Integer.parseInt(actButton.getName()));
                                    }
                                }
                            }else if (panelGroup.getActualButtonSelected().getName().equals("Center")) {
                                if (markedCell.getFinalDigit()==null){
                                    if (markedCell.getCenterDigits().contains(Integer.parseInt(actButton.getName()))){
                                        markedCell.removeCenterDigit(Integer.parseInt(actButton.getName()));
                                    }else{
                                        markedCell.addCenterDigit(Integer.parseInt(actButton.getName()));
                                    }
                                }
                            }else if (panelGroup.getActualButtonSelected().getName().equals("Color")) {
                                if (markedCell.getColors().contains(MyColorPalette.cellColoring(Integer.parseInt(actButton.getName())))){
                                    markedCell.removeColor(MyColorPalette.cellColoring(Integer.parseInt(actButton.getName())));
                                }else{
                                    markedCell.addColor(MyColorPalette.cellColoring(Integer.parseInt(actButton.getName())));
                                }
                            }
                        }

                    }
                    @Override public void mouseReleased(MouseEvent e) {

                    }
                    @Override public void mouseEntered(MouseEvent e) {
                        actButton.setHovering(true);
                        updateCLickNrColor();
                    }
                    @Override public void mouseExited(MouseEvent e) {
                        actButton.setHovering(false);
                        updateCLickNrColor();
                    }
                });
        }
        actionButtons[3][2].setMouseListener(new MouseListener() {
            @Override public void mouseClicked(MouseEvent e) {

            }
            @Override public void mousePressed(MouseEvent e) {
                for (Cell markedCell : jGrid.getGrid().getMarkedCells()) {
                    if (markedCell.getFinalDigit()!=null){
                        markedCell.setFinalDigit(null);
                    }
                    else if (markedCell.getCenterDigits().size()>0 &&
                            panelGroup.getActualButtonSelected().getName().equals("Center")){
                        markedCell.getCenterDigits().clear();
                    }
                    else if (markedCell.getCornerDigits().size()>0 &&
                            panelGroup.getActualButtonSelected().getName().equals("Corner")){
                        markedCell.getCornerDigits().clear();
                    }
                    else if (markedCell.getColors().size()>0 &&
                            panelGroup.getActualButtonSelected().getName().equals("Color")){
                        markedCell.getColors().clear();
                    }
                    else if (markedCell.getCenterDigits().size()>0){
                        markedCell.getCenterDigits().clear();
                    }
                    else if (markedCell.getCornerDigits().size()>0){
                        markedCell.getCornerDigits().clear();
                    }
                    else if (markedCell.getColors().size()>0){
                        markedCell.getColors().clear();
                    }
                }
            }
            @Override public void mouseReleased(MouseEvent e) {

            }
            @Override public void mouseEntered(MouseEvent e) {
                actionButtons[3][2].setHovering(true);
                updateCLickNrColor();
            }
            @Override public void mouseExited(MouseEvent e) {
                actionButtons[3][2].setHovering(false);
                updateCLickNrColor();
            }
        });

    }

    private void updateToggleColor() {
        for (int i = 0; i <= 4; i++) {
            JCustomButton jCustomButton= actionButtons[i][3];
            if (jCustomButton.isClicked()) {
                if (jCustomButton.isHovering()) {
                    jCustomButton.setBackground(MyColorPalette.DARK_VIOLET);
                } else {
                    jCustomButton.setBackground(MyColorPalette.ORCHID);
                }
            } else {
                if (jCustomButton.isHovering()) {
                    jCustomButton.setBackground(MyColorPalette.PASTEL_VIOLET);
                } else {
                    jCustomButton.setBackground(MyColorPalette.WHITE);
                }
            }
        }
    }
    private void updateCLickNrColor() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                JCustomButton jCustomButton= actionButtons[i][j];
                if (jCustomButton.isHovering()) {
                    jCustomButton.setBackground(MyColorPalette.DARK_VIOLET);
                } else {
                    jCustomButton.setBackground(MyColorPalette.ORCHID);
                }
            }
        }
    }

}

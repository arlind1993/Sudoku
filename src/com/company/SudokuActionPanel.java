package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Stack;

public class SudokuActionPanel extends JPanel {
    public static final int WIDTH=265;
    public static final int HEIGHT=395;

    public final int rows = 6;
    public final int cols = 4;

    public final int offset = 5;

    private Stack<JPanelGroup> panelGroup;
    private JCustomButton [][] actionButtons;
    private MyGridLayout<JPanel> jPanelMyGridLayout;

    private JGrid jGrid;
    public SudokuActionPanel(JGrid jGrid){
        this.jGrid=jGrid;
        this.setLayout(null);
        this.setSize(WIDTH,HEIGHT);
        this.setBackground(Color.DARK_GRAY);
        initButtons();

    }

    private void initButtons() {
        panelGroup=new Stack<>();
        actionButtons= new JCustomButton[rows][cols];
        jPanelMyGridLayout = new MyGridLayout<>(rows,cols, offset, actionButtons);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(!(j==3&&i<=3)) {
                    actionButtons[i][j] = new JCustomButton();
                    actionButtons[i][j].setBackground(MyColorPalette.WHITE);
                    jPanelMyGridLayout.setLocationOfType(i, j);
                    this.add(actionButtons[i][j]);
                }
            }
        }
        panelGroup.add(new JPanelGroup(this));

        FIStackUpdate();
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

        actionButtons[3][0].setName("Check");

        actionButtons[4][0].setName("Reset");
        actionButtons[4][1].setName("Undo");
        actionButtons[4][2].setName("Redo");
        actionButtons[4][3].setName("Select");

        actionButtons[4][0].setMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jGrid.resetUndoGridAcceptLastElement();
                jGrid.resetRedoGrid();
                jGrid.repaint();
            }
            @Override public void mousePressed(MouseEvent e) {}
            @Override public void mouseReleased(MouseEvent e) {}
            @Override public void mouseEntered(MouseEvent e) {
                actionButtons[4][0].setHovering(true);
                updateToggleColor(actionButtons[4][0]);
            }
            @Override public void mouseExited(MouseEvent e) {
                actionButtons[4][0].setHovering(false);
                updateToggleColor(actionButtons[4][0]);
            }
        });
        
        actionButtons[4][1].setMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(jGrid.getUndoGrids().size()>1) {
                    jGrid.addToRedoGrid(jGrid.createNewGrid(true));
                    jGrid.removeFromUndoGridFI();
                    jGrid.repaint();
                }
            }
            @Override public void mousePressed(MouseEvent e) {}
            @Override public void mouseReleased(MouseEvent e) {}
            @Override public void mouseEntered(MouseEvent e) {
                actionButtons[4][1].setHovering(true);
                updateToggleColor(actionButtons[4][1]);
            }
            @Override public void mouseExited(MouseEvent e) {
                actionButtons[4][1].setHovering(false);
                updateToggleColor(actionButtons[4][1]);
            }
        });

        actionButtons[4][2].setMouseListener(new MouseListener() {
            @Override public void mouseClicked(MouseEvent e) {
                if(jGrid.getRedoGrids().size()>0) {
                    jGrid.addToUndoGrid(jGrid.createNewGrid(false));
                    jGrid.removeFromRedoGridFI();
                    jGrid.repaint();
                }
            }
            @Override public void mousePressed(MouseEvent e) {}
            @Override public void mouseReleased(MouseEvent e) {}
            @Override public void mouseEntered(MouseEvent e) {
                actionButtons[4][2].setHovering(true);
                updateToggleColor(actionButtons[4][2]);
            }
            @Override public void mouseExited(MouseEvent e) {
                actionButtons[4][2].setHovering(false);
                updateToggleColor(actionButtons[4][2]);
            }
        });
        actionButtons[4][3].setMouseListener(new MouseListener() {
            @Override public void mouseClicked(MouseEvent e) {}
            @Override public void mousePressed(MouseEvent e) {
                actionButtons[4][3].setClicked(!actionButtons[4][3].isClicked());
                jGrid.setMarkPenActivated(actionButtons[4][3].isClicked());
                updateToggleColor(actionButtons[4][3]);
            }
            @Override public void mouseReleased(MouseEvent e) {}
            @Override public void mouseEntered(MouseEvent e) {
                actionButtons[4][3].setHovering(true);
                updateToggleColor(actionButtons[4][3]);
            }
            @Override public void mouseExited(MouseEvent e) {
                actionButtons[4][3].setHovering(false);
                updateToggleColor(actionButtons[4][3]);
            }
        });

        Integer[][] numberMapping= new Integer[][]{
                {0,0}, {0,1}, {0,2},
                {1,0}, {1,1}, {1,2},
                {2,0}, {2,1}, {2,2},
                       {3,1}
        };

        for (Integer[] integers : numberMapping) {
            JCustomButton actButton = actionButtons[integers[0]][integers[1]];
            updateCLickNrColor(actButton);
            actButton.setMouseListener(new MouseListener() {
                @Override public void mouseClicked(MouseEvent e) {}
                @Override public void mousePressed(MouseEvent e) {
                    if ( jGrid.getFIGrid().getMarkedCells().size()>0) {
                        jGrid.actionHappenedLogic();
                    }
                    for (Cell markedCell : jGrid.getFIGrid().getMarkedCells()) {
                        switch (getFIPanelGroup().getActualButtonSelected().getName()) {
                            case "Digit":
                                if (markedCell.getFinalDigit() == null) {
                                    markedCell.setFinalDigit(Integer.parseInt(actButton.getName()));
                                } else {
                                    if (markedCell.getFinalDigit() == Integer.parseInt(actButton.getName())) {
                                        markedCell.setFinalDigit(null);
                                    } else {
                                        markedCell.setFinalDigit(Integer.parseInt(actButton.getName()));
                                    }
                                }
                                break;
                            case "Corner":
                                if (markedCell.getFinalDigit() == null) {
                                    if (markedCell.getCornerDigits().contains(Integer.parseInt(actButton.getName()))) {
                                        markedCell.removeCornerDigit(Integer.parseInt(actButton.getName()));
                                    } else {
                                        markedCell.addCornerDigit(Integer.parseInt(actButton.getName()));
                                    }
                                }
                                break;
                            case "Center":
                                if (markedCell.getFinalDigit() == null) {
                                    if (markedCell.getCenterDigits().contains(Integer.parseInt(actButton.getName()))) {
                                        markedCell.removeCenterDigit(Integer.parseInt(actButton.getName()));
                                    } else {
                                        markedCell.addCenterDigit(Integer.parseInt(actButton.getName()));
                                    }
                                }
                                break;
                            case "Color":
                                if (actButton.getName().equals("0")) {
                                    markedCell.getColors().clear();
                                } else {
                                    if (markedCell.getColors().contains(MyColorPalette.cellColoring(Integer.parseInt(actButton.getName())))) {
                                        markedCell.removeColor(MyColorPalette.cellColoring(Integer.parseInt(actButton.getName())));
                                    } else {
                                        markedCell.addColor(MyColorPalette.cellColoring(Integer.parseInt(actButton.getName())));
                                    }
                                }
                                break;
                        }
                    }
                    jGrid.repaint();
                }
                @Override public void mouseReleased(MouseEvent e) {}
                @Override public void mouseEntered(MouseEvent e) {
                    actButton.setHovering(true);
                    updateCLickNrColor(actButton);
                }
                @Override public void mouseExited(MouseEvent e) {
                    actButton.setHovering(false);
                    updateCLickNrColor(actButton);
                }
            });
        }
        updateCheckColor(actionButtons[3][0]);
        actionButtons[3][0].setMouseListener(new MouseListener() {
            @Override public void mouseClicked(MouseEvent e) {}
            @Override public void mousePressed(MouseEvent e) {
                Grid.checkGrid(jGrid.getFIGrid());
            }
            @Override public void mouseReleased(MouseEvent e) {}
            @Override public void mouseEntered(MouseEvent e) {
                actionButtons[3][0].setHovering(true);
                updateCheckColor(actionButtons[3][0]);
            }
            @Override public void mouseExited(MouseEvent e) {
                actionButtons[3][0].setHovering(false);
                updateCheckColor(actionButtons[3][0]);
            }
        });
        updateCLickNrColor(actionButtons[3][2]);
        actionButtons[3][2].setMouseListener(new MouseListener() {
            @Override public void mouseClicked(MouseEvent e) {}
            @Override public void mousePressed(MouseEvent e) {
                if ( jGrid.getFIGrid().getMarkedCells().size()>0) {
                    jGrid.actionHappenedLogic();
                }
                for (Cell markedCell : jGrid.getFIGrid().getMarkedCells()) {
                    if (markedCell.getFinalDigit()!=null){
                        markedCell.setFinalDigit(null);
                    }
                    else if (markedCell.getCenterDigits().size()>0 &&
                            getFIPanelGroup().getActualButtonSelected().getName().equals("Center")){
                        markedCell.getCenterDigits().clear();
                    }
                    else if (markedCell.getCornerDigits().size()>0 &&
                            getFIPanelGroup().getActualButtonSelected().getName().equals("Corner")){
                        markedCell.getCornerDigits().clear();
                    }
                    else if (markedCell.getColors().size()>0 &&
                            getFIPanelGroup().getActualButtonSelected().getName().equals("Color")){
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
                jGrid.repaint();
            }
            @Override public void mouseReleased(MouseEvent e) {}
            @Override public void mouseEntered(MouseEvent e) {
                actionButtons[3][2].setHovering(true);
                updateCLickNrColor(actionButtons[3][2]);
            }
            @Override public void mouseExited(MouseEvent e) {
                actionButtons[3][2].setHovering(false);
                updateCLickNrColor(actionButtons[3][2]);
            }
        });

    }

    public void updateCheckColor(JCustomButton jCustomButton){
        if (jCustomButton.isHovering()) {
            jCustomButton.setBackground(MyColorPalette.DARK_ROSE);
        } else {
            jCustomButton.setBackground(MyColorPalette.ROSE);
        }
        revalidate();
        repaint();
    }

    public void updateToggleColor(JCustomButton jCustomButton) {
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
        revalidate();
        repaint();

    }

    public void updateToggleColorForPanelGroup(){
        for (JCustomButton button: getFIPanelGroup().getButtons()) {
            updateToggleColor(button);
        }

    }

    public void updateCLickNrColor(JCustomButton jCustomButton) {
        if (jCustomButton.isHovering()) {
            jCustomButton.setBackground(MyColorPalette.DARK_VIOLET);
        } else {
            jCustomButton.setBackground(MyColorPalette.ORCHID);
        }
        revalidate();
        repaint();
    }

    public Stack<JPanelGroup> getPanelGroup() {
        return panelGroup;
    }

    public JPanelGroup getFIPanelGroup() {
        return panelGroup.peek();
    }

    public JCustomButton[][] getActionButtons() {
        return actionButtons;
    }

    public MyGridLayout<JPanel> getjPanelMyGridLayout() {
        return jPanelMyGridLayout;
    }

    public void FIStackUpdate() {

        for (int i = 0; i < 4; i++) {
            if (getActionButtons()[i][3]!=null) {
                if (getActionButtons()[i][3].getParent()==this) {
                    remove(getActionButtons()[i][3]);
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            int I = i;
            getActionButtons()[i][3] = getFIPanelGroup().getButtons().get(i);
            getjPanelMyGridLayout().setLocationOfType(i, 3);
            add(getActionButtons()[i][3]);
            updateToggleColor(getActionButtons()[i][3]);
            getActionButtons()[i][3].setMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {
                    getFIPanelGroup().setActualButtonSelected(getActionButtons()[I][3]);
                    updateToggleColorForPanelGroup();
                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    getActionButtons()[I][3].setHovering(true);
                    updateToggleColor(getActionButtons()[I][3]);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    getActionButtons()[I][3].setHovering(false);
                    updateToggleColor(getActionButtons()[I][3]);
                }
            });
        }
    }
}

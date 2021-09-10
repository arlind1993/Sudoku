package com.company;

import java.awt.event.FocusAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class JGridMouseListener implements MouseListener, MouseMotionListener {
    private JGrid jGrid;
    private int oldPosX=-1;
    private int oldPosY=-1;
    private int posX;
    private int posY;


    private int state=0;//0- nothing 1- adding 2- deleting

    public JGridMouseListener(JGrid jGrid){
        this.jGrid=jGrid;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        posX=(e.getX()-JGrid.LINE_WIDTH)/(JGrid.SIZE_PER_CELL+JGrid.LINE_WIDTH);
        posY=(e.getY()-JGrid.LINE_WIDTH)/(JGrid.SIZE_PER_CELL+JGrid.LINE_WIDTH);

        System.out.println(e.getPoint() +"  "+ jGrid.getGrid().getCells()[posY][posX]);

        if (!jGrid.isMarkPenActivated()){
            for (int i = 0; i < Grid.SIZE; i++) {
                for (int j = 0; j < Grid.SIZE; j++) {
                    jGrid.getGrid().getCells()[i][j].setMarked(false);
                }
            }
        }

        if (posX>=0&&posX<Grid.SIZE && posY>=0&&posY<Grid.SIZE){
            boolean check = false;
            for (int i = 0; i < jGrid.getGrid().getMarkedCells().size(); i++) {
                if (posY== jGrid.getGrid().getMarkedCells().get(i).getRow()&&posX== jGrid.getGrid().getMarkedCells().get(i).getCol()){
                    check = true;
                    break;
                }
            }
            if (check) {
                state=2;
                jGrid.getGrid().getCells()[posY][posX].setMarked(false);
            }else {
                state=1;
                jGrid.getGrid().getCells()[posY][posX].setMarked(true);
            }
            oldPosX=posX;
            oldPosY=posY;
        }
        jGrid.repaint();

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        posX=(e.getX()-JGrid.LINE_WIDTH)/(JGrid.SIZE_PER_CELL+JGrid.LINE_WIDTH);
        posY=(e.getY()-JGrid.LINE_WIDTH)/(JGrid.SIZE_PER_CELL+JGrid.LINE_WIDTH);

        if (posX>=0&&posX<Grid.SIZE && posY>=0&&posY<Grid.SIZE){
            if (jGrid.isMarkPenActivated()){
                if (state==1){
                    jGrid.getGrid().getCells()[posY][posX].setMarked(true);
                }else if (state==2){
                    jGrid.getGrid().getCells()[posY][posX].setMarked(false);
                }
            }
            else {
                if (!(oldPosX == posX && oldPosY == posY)) {
                    jGrid.getGrid().getCells()[posY][posX].setMarked(true);
                    oldPosX = posX;
                    oldPosY = posY;
                }
            }
        }
        jGrid.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}

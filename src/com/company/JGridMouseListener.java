package com.company;

import java.awt.event.FocusAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

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

        if (!((posX>=0&&posX<9)||(posY>=0&&posY<9))){
            return;
        }

        System.out.println(e.getPoint() +"  "+ jGrid.getFIGrid().getCells()[posY][posX]);

        jGrid.actionHappenedLogic();

        if (!jGrid.isMarkPenActivated()){
            ArrayList<Cell> mC = jGrid.getFIGrid().getMarkedCells();
            if (mC.size()==1){
                if(mC.get(0).isMarked() && jGrid.getFIGrid().getMarkedCells().get(0).getRow()==posY
                        && jGrid.getFIGrid().getMarkedCells().get(0).getCol()==posX){
                    mC.get(0).setMarked(false);
                    oldPosX=-1;
                    oldPosY=-1;
                    jGrid.repaint();
                    return;
                }
            }
            for (Cell cell:jGrid.getFIGrid().getMarkedCells()) {
                cell.setMarked(false);
            }
        }
        if (posX>=0&&posX<Grid.SIZE && posY>=0&&posY<Grid.SIZE){
            boolean check = false;
            for (int i = 0; i < jGrid.getFIGrid().getMarkedCells().size(); i++) {
                if (posY == jGrid.getFIGrid().getMarkedCells().get(i).getRow() &&
                        posX== jGrid.getFIGrid().getMarkedCells().get(i).getCol()){
                    check = true;
                    break;
                }
            }
            if (check) {
                state=2;
                jGrid.getFIGrid().getCells()[posY][posX].setMarked(false);
            }else {
                state=1;
                jGrid.getFIGrid().getCells()[posY][posX].setMarked(true);
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
                    jGrid.getFIGrid().getCells()[posY][posX].setMarked(true);
                }else if (state==2){
                    jGrid.getFIGrid().getCells()[posY][posX].setMarked(false);
                }
            }
            else {
                if (!(oldPosX == posX && oldPosY == posY)) {
                    jGrid.getFIGrid().getCells()[posY][posX].setMarked(true);
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

package com.company;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class JGridMouseListener implements MouseListener, MouseMotionListener {
    private JGrid jGrid;
    private int oldPosX=-1;
    private int oldPosY=-1;
    private int posX;
    private int posY;
    public JGridMouseListener(JGrid jGrid){
        this.jGrid=jGrid;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println(jGrid.getGrid().getMarkedCells().toString());
        System.out.print(e.getPoint());

        posX=(e.getX()-JGrid.LINE_WIDTH)/(JGrid.SIZE_PER_CELL+JGrid.LINE_WIDTH);
        posY=(e.getY()-JGrid.LINE_WIDTH)/(JGrid.SIZE_PER_CELL+JGrid.LINE_WIDTH);


        System.out.println(" "+posY+" , "+posX);
        jGrid.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (posX>=0&&posX<Grid.SIZE && posY>=0&&posY<Grid.SIZE){
            if (oldPosX==posX&&oldPosY==posY) {
                jGrid.getGrid().getCells()[posY][posX].setMarked(false);
                oldPosX=-1;
                oldPosY=-1;
            }else {
                jGrid.getGrid().getCells()[posY][posX].setMarked(true);
                oldPosX=posX;
                oldPosY=posY;
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}

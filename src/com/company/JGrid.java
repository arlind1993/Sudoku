package com.company;

import com.company.fileAndJSON.CellInput;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class JGrid extends JPanel {

    public static final int MAX_STACK_LENGTH = 50;

    public static final int LINE_WIDTH=5;
    public static final int SIZE_PER_CELL = 60;
    public static final int SIZE=SIZE_PER_CELL*Grid.SIZE+LINE_WIDTH*(Grid.SIZE+1);//590

    private Stack<Grid> undoGrids = new Stack<>();
    private Stack<Grid> redoGrids = new Stack<>();
    private JGridMouseListener jgml;

    private boolean markPenActivated = false;

    public JGrid(){
        this(null);
    }

    public JGrid(ArrayList<ArrayList<CellInput>> cellInput){

        this.setLayout(null);
        this.setSize(SIZE,SIZE);
        this.setBackground(Color.WHITE);
        if (cellInput!=null){
            Grid g=new Grid();
            for (int i = 0; i < g.getCells().length; i++) {
                for (int j = 0; j < g.getCells()[i].length; j++) {
                    g.getCells()[i][j].setFinalDigit(cellInput.get(i).get(j).getfI());
                    g.getCells()[i][j].setCenterDigits(cellInput.get(i).get(j).getCenterDigits());
                    g.getCells()[i][j].setCornerDigits(cellInput.get(i).get(j).getCornerDigits());
                    g.getCells()[i][j].setColors(cellInput.get(i).get(j).getColors());
                    g.getCells()[i][j].setExtra(cellInput.get(i).get(j).getExtra());
                }
            }
            addToUndoGrid(g);
        }else {
            addToUndoGrid(new Grid());
        }
        System.out.println(SIZE);


        jgml = new JGridMouseListener(this);
        this.addMouseListener(jgml);
        this.addMouseMotionListener(jgml);
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        drawBoundaries(g);
        drawMarkingsInCell(g);
        drawMarkedCells(g);
    }

    private void drawMarkingsInCell(Graphics g) {
        for (Cell[] rows : getFIGrid().getCells()) {
            for (Cell cell : rows) {
                int posX = LINE_WIDTH + cell.getCol() * (SIZE_PER_CELL + LINE_WIDTH);
                int posY = LINE_WIDTH + cell.getRow() * (SIZE_PER_CELL + LINE_WIDTH);
                switch (cell.getColors().size()) {
                    case 0:
                        g.setColor(MyColorPalette.WHITE);
                        g.fillRect(posX, posY, SIZE_PER_CELL, SIZE_PER_CELL);
                        break;
                    case 1:
                        g.setColor(cell.getColors().get(0));
                        g.fillRect(posX, posY, SIZE_PER_CELL, SIZE_PER_CELL);
                        break;
                    case 2:
                        g.setColor(cell.getColors().get(0));
                        g.fillRect(posX, posY, SIZE_PER_CELL / 2, SIZE_PER_CELL);
                        g.setColor(cell.getColors().get(1));
                        g.fillRect(posX + SIZE_PER_CELL / 2, posY, SIZE_PER_CELL / 2, SIZE_PER_CELL);
                        break;
                    case 3:
                        g.setColor(cell.getColors().get(0));
                        g.fillRect(posX, posY, SIZE_PER_CELL, SIZE_PER_CELL / 2);
                        g.setColor(cell.getColors().get(1));
                        g.fillRect(posX, posY + SIZE_PER_CELL / 2, SIZE_PER_CELL / 2, SIZE_PER_CELL / 2);
                        g.setColor(cell.getColors().get(2));
                        g.fillRect(posX + SIZE_PER_CELL / 2, posY + SIZE_PER_CELL / 2, SIZE_PER_CELL / 2, SIZE_PER_CELL / 2);
                        break;
                    case 4:
                        g.setColor(cell.getColors().get(0));
                        g.fillRect(posX, posY, SIZE_PER_CELL / 2, SIZE_PER_CELL / 2);
                        g.setColor(cell.getColors().get(1));
                        g.fillRect(posX + SIZE_PER_CELL / 2, posY, SIZE_PER_CELL / 2, SIZE_PER_CELL / 2);
                        g.setColor(cell.getColors().get(2));
                        g.fillRect(posX, posY + SIZE_PER_CELL / 2, SIZE_PER_CELL / 2, SIZE_PER_CELL / 2);
                        g.setColor(cell.getColors().get(3));
                        g.fillRect(posX + SIZE_PER_CELL / 2, posY + SIZE_PER_CELL / 2, SIZE_PER_CELL / 2, SIZE_PER_CELL / 2);
                        break;
                    default:
                        break;
                }
                g.setColor(MyColorPalette.BLACK);
                if (cell.getFinalDigit() != null) {
                    drawCenteredString(g, cell.getFinalDigit().toString(), new Rectangle(posX, posY, SIZE_PER_CELL, SIZE_PER_CELL),
                            new Font("Times", Font.PLAIN, 40));
                } else {
                    String centerNumbersString = "";
                    for (Integer number : cell.getCenterDigits()) {
                        centerNumbersString += number.toString();
                    }
                    drawCenteredString(g, centerNumbersString, new Rectangle(posX, posY, SIZE_PER_CELL, SIZE_PER_CELL),
                            new Font("Times", Font.PLAIN, (int) (20-Math.min(0.5*20*(centerNumbersString.length()/5),11))));

                    Font font= new Font("Times", Font.PLAIN,14);
                    g.setFont(font);
                    int fW = 8;
                    int fH = 12;
                    switch (cell.getCornerDigits().size()){
                        case 1:
                            g.drawString(cell.getCornerDigits().get(0).toString(),posX+LINE_WIDTH,posY+LINE_WIDTH+fH);
                            break;
                        case 2:
                            g.drawString(cell.getCornerDigits().get(0).toString(),posX+LINE_WIDTH,posY+LINE_WIDTH+fH);
                            g.drawString(cell.getCornerDigits().get(1).toString(),posX+SIZE_PER_CELL-LINE_WIDTH-fW,posY+LINE_WIDTH+fH);
                            break;
                        case 3:
                            g.drawString(cell.getCornerDigits().get(0).toString(),posX+LINE_WIDTH,posY+LINE_WIDTH+fH);
                            g.drawString(cell.getCornerDigits().get(1).toString(),posX+SIZE_PER_CELL-LINE_WIDTH-fW,posY+LINE_WIDTH+fH);
                            g.drawString(cell.getCornerDigits().get(2).toString(),posX+SIZE_PER_CELL-LINE_WIDTH-fW,posY+SIZE_PER_CELL-LINE_WIDTH);
                            break;
                        case 4:
                            g.drawString(cell.getCornerDigits().get(0).toString(),posX+LINE_WIDTH,posY+LINE_WIDTH+fH);
                            g.drawString(cell.getCornerDigits().get(1).toString(),posX+SIZE_PER_CELL-LINE_WIDTH-fW,posY+LINE_WIDTH+fH);
                            g.drawString(cell.getCornerDigits().get(2).toString(),posX+SIZE_PER_CELL-LINE_WIDTH-fW,posY+SIZE_PER_CELL-LINE_WIDTH);
                            g.drawString(cell.getCornerDigits().get(3).toString(),posX+LINE_WIDTH,posY+SIZE_PER_CELL-LINE_WIDTH);
                            break;
                        case 5:
                            g.drawString(cell.getCornerDigits().get(0).toString(),posX+LINE_WIDTH,posY+LINE_WIDTH+fH);
                            g.drawString(cell.getCornerDigits().get(1).toString(),posX+(SIZE_PER_CELL-fW)/2,posY+LINE_WIDTH+fH);
                            g.drawString(cell.getCornerDigits().get(2).toString(),posX+SIZE_PER_CELL-LINE_WIDTH-fW,posY+LINE_WIDTH+fH);
                            g.drawString(cell.getCornerDigits().get(3).toString(),posX+SIZE_PER_CELL-LINE_WIDTH-fW,posY+SIZE_PER_CELL-LINE_WIDTH);
                            g.drawString(cell.getCornerDigits().get(4).toString(),posX+LINE_WIDTH,posY+SIZE_PER_CELL-LINE_WIDTH);
                            break;
                        case 6:
                            g.drawString(cell.getCornerDigits().get(0).toString(),posX+LINE_WIDTH,posY+LINE_WIDTH+fH);
                            g.drawString(cell.getCornerDigits().get(1).toString(),posX+(SIZE_PER_CELL-fW)/2,posY+LINE_WIDTH+fH);
                            g.drawString(cell.getCornerDigits().get(2).toString(),posX+SIZE_PER_CELL-LINE_WIDTH-fW,posY+LINE_WIDTH+fH);
                            g.drawString(cell.getCornerDigits().get(3).toString(),posX+SIZE_PER_CELL-LINE_WIDTH-fW,posY+(SIZE_PER_CELL+fH)/2);
                            g.drawString(cell.getCornerDigits().get(4).toString(),posX+SIZE_PER_CELL-LINE_WIDTH-fW,posY+SIZE_PER_CELL-LINE_WIDTH);
                            g.drawString(cell.getCornerDigits().get(5).toString(),posX+LINE_WIDTH,posY+SIZE_PER_CELL-LINE_WIDTH);
                            break;
                        case 7:
                            g.drawString(cell.getCornerDigits().get(0).toString(),posX+LINE_WIDTH,posY+LINE_WIDTH+fH);
                            g.drawString(cell.getCornerDigits().get(1).toString(),posX+(SIZE_PER_CELL-fW)/2,posY+LINE_WIDTH+fH);
                            g.drawString(cell.getCornerDigits().get(2).toString(),posX+SIZE_PER_CELL-LINE_WIDTH-fW,posY+LINE_WIDTH+fH);
                            g.drawString(cell.getCornerDigits().get(3).toString(),posX+SIZE_PER_CELL-LINE_WIDTH-fW,posY+(SIZE_PER_CELL+fH)/2);
                            g.drawString(cell.getCornerDigits().get(4).toString(),posX+SIZE_PER_CELL-LINE_WIDTH-fW,posY+SIZE_PER_CELL-LINE_WIDTH);
                            g.drawString(cell.getCornerDigits().get(5).toString(),posX+(SIZE_PER_CELL-fW)/2,posY+SIZE_PER_CELL-LINE_WIDTH);
                            g.drawString(cell.getCornerDigits().get(6).toString(),posX+LINE_WIDTH,posY+SIZE_PER_CELL-LINE_WIDTH);
                            break;
                        case 8:
                            g.drawString(cell.getCornerDigits().get(0).toString(),posX+LINE_WIDTH,posY+LINE_WIDTH+fH);
                            g.drawString(cell.getCornerDigits().get(1).toString(),posX+(SIZE_PER_CELL-fW)/2,posY+LINE_WIDTH+fH);
                            g.drawString(cell.getCornerDigits().get(2).toString(),posX+SIZE_PER_CELL-LINE_WIDTH-fW,posY+LINE_WIDTH+fH);
                            g.drawString(cell.getCornerDigits().get(3).toString(),posX+SIZE_PER_CELL-LINE_WIDTH-fW,posY+(SIZE_PER_CELL+fH)/2);
                            g.drawString(cell.getCornerDigits().get(4).toString(),posX+SIZE_PER_CELL-LINE_WIDTH-fW,posY+SIZE_PER_CELL-LINE_WIDTH);
                            g.drawString(cell.getCornerDigits().get(5).toString(),posX+(SIZE_PER_CELL-fW)/2,posY+SIZE_PER_CELL-LINE_WIDTH);
                            g.drawString(cell.getCornerDigits().get(6).toString(),posX+LINE_WIDTH,posY+SIZE_PER_CELL-LINE_WIDTH);
                            g.drawString(cell.getCornerDigits().get(7).toString(),posX+LINE_WIDTH,posY+(SIZE_PER_CELL+fH)/2);
                            break;
                        default:
                            break;
                    }

                }
            }
        }
    }

    private void drawMarkedCells(Graphics g) {
        g.setColor(MyColorPalette.LIGHT_BLUE);
        for (Cell markedCell: getFIGrid().getMarkedCells()) {
            g.fillRect(LINE_WIDTH+(SIZE_PER_CELL+LINE_WIDTH)*markedCell.getCol(),
                    LINE_WIDTH+(SIZE_PER_CELL+LINE_WIDTH)*markedCell.getRow(),
                    SIZE_PER_CELL,LINE_WIDTH);
            g.fillRect(SIZE_PER_CELL+(SIZE_PER_CELL+LINE_WIDTH)*markedCell.getCol(),
                    LINE_WIDTH+(SIZE_PER_CELL+LINE_WIDTH)*markedCell.getRow(),
                    LINE_WIDTH,SIZE_PER_CELL);
            g.fillRect(LINE_WIDTH+(SIZE_PER_CELL+LINE_WIDTH)*markedCell.getCol(),
                    SIZE_PER_CELL+(SIZE_PER_CELL+LINE_WIDTH)*markedCell.getRow(),
                    SIZE_PER_CELL,LINE_WIDTH);
            g.fillRect(LINE_WIDTH+(SIZE_PER_CELL+LINE_WIDTH)*markedCell.getCol(),
                    LINE_WIDTH+(SIZE_PER_CELL+LINE_WIDTH)*markedCell.getRow(),
                    LINE_WIDTH,SIZE_PER_CELL);
        }
    }

    private void drawBoundaries(Graphics g) {
        for (int i = 0; i <= SIZE; i++) {
            g.setColor(Color.LIGHT_GRAY);
            if (i%3==1||i%3==2){
                g.fillRect(0,i*(SIZE_PER_CELL+LINE_WIDTH),SIZE,LINE_WIDTH);
                g.fillRect(i*(SIZE_PER_CELL+LINE_WIDTH),0,LINE_WIDTH,SIZE);
            }

        }
        for (int i = 0; i < SIZE; i++) {
            g.setColor(Color.BLACK);
            if(i%3==0) {
                g.fillRect(0, i * (SIZE_PER_CELL + LINE_WIDTH), SIZE, LINE_WIDTH);
                g.fillRect(i * (SIZE_PER_CELL + LINE_WIDTH), 0, LINE_WIDTH, SIZE);
            }
        }

    }

    public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(font);
        g.drawString(text, x, y);
    }

    public Grid getFIGrid() {
        return undoGrids.size()==0?null:undoGrids.peek();
    }

    public void addToUndoGrid(Grid element) {
        if (undoGrids!=null) {
            if (undoGrids.size() >= MAX_STACK_LENGTH) {
                undoGrids.remove(undoGrids.size() - 1);
            }
            undoGrids.add(element);
            System.out.println("Added in Undo List: "+undoGrids.size());
        }
    }
    public void addToRedoGrid(Grid element) {
        if (undoGrids!=null) {
            if (redoGrids.size() >= MAX_STACK_LENGTH) {
                redoGrids.remove(redoGrids.size()-1);
            }
            redoGrids.add(element);
            System.out.println("Added in Redo List: "+redoGrids.size());
        }
    }


    public void removeFromUndoGridFI() {
        if (undoGrids.size() > 1) {
            undoGrids.pop();
            System.out.println("Removed in Undo List: " + undoGrids.size());
        }
    }

    public void removeFromRedoGridFI(){
        redoGrids.pop();
        System.out.println("Removed in Redo List: "+redoGrids.size());
    }

    public void resetUndoGridAcceptLastElement(){
        int size=undoGrids.size();
        for (int i = 0; i < size; i++) {
            removeFromUndoGridFI();
        }
    }

    public void resetRedoGrid(){
        redoGrids.clear();
    }

    public Stack<Grid> getUndoGrids() {
        return undoGrids;
    }

    public Stack<Grid> getRedoGrids() {
        return redoGrids;
    }

    public Grid createNewGrid(boolean getFromUndo){
        Grid result = new Grid();
        Grid getFrom;
        if (getFromUndo){
            getFrom=undoGrids.peek();
        }else {
            getFrom=redoGrids.peek();
        }
        for (int i = 0; i < getFrom.getCells().length; i++) {
            for (int j = 0; j < getFrom.getCells()[i].length; j++) {
                result.getCells()[i][j]=new Cell(getFrom.getCells()[i][j]);
            }
        }
        if (getFrom.getLastCellMarked()!=null) {
            result.setLastCellMarked(new Cell(getFrom.getLastCellMarked()));
        }
        return result;
    }

    public boolean isMarkPenActivated() {
        return markPenActivated;
    }

    public void setMarkPenActivated(boolean markPenActivated) {
        this.markPenActivated = markPenActivated;
    }

    public void actionHappenedLogic(){
        addToUndoGrid(createNewGrid(true));
        resetRedoGrid();
    }

}

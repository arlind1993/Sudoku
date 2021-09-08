package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class JCustomButton extends JPanel {
    public static final int SIZE = 60;

    private boolean isHovering= false;
    private boolean isClicked = false;

    private ButtonModel buttonModel;

    private MouseListener mouseListener;

    public JCustomButton(){
        this.setLayout(null);
        this.setSize(SIZE,SIZE);

        buttonModel = new DefaultButtonModel();
    }

    public boolean isHovering() {
        return isHovering;
    }
    public boolean isClicked() {
        return isClicked;
    }

    public ButtonModel getButtonModel() {
        return buttonModel;
    }

    public MouseListener getMouseListener() {
        return mouseListener;
    }

    public void setMouseListener(MouseListener mouseListener) {
        System.out.println("..");
        this.mouseListener = mouseListener;
        addMouseListener(this.mouseListener);
    }

    public void setButtonModel(ButtonModel buttonModel) {
        this.buttonModel = buttonModel;
    }

    public void setHovering(boolean hovering) {
        isHovering = hovering;
    }
    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }
}

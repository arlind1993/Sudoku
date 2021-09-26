package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.Objects;

public class JCustomButton extends JPanel {
    public static final int SIZE = 60;

    private boolean isHovering = false;
    private boolean isClicked = false;

    private ButtonModel buttonModel;

    private MouseListener mouseListener;

    public JCustomButton() {
        this.setLayout(null);
        this.setSize(SIZE, SIZE);

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

    @Override
    public String toString() {
        return "JCB{" +
                "name=" + getName() +
                ", isC=" + isClicked +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        JCustomButton that = (JCustomButton) o;
        return isClicked == that.isClicked && Objects.equals(getName(), that.getName());
    }

    @Override
    public void setName(String name) {
        super.setName(name);

        setToolTipText(name);
    }

    @Override
    public JToolTip createToolTip() {
        JToolTip tip = new JToolTip();
        tip.setBackground(Color.WHITE);
        tip.setBorder(null);
        tip.setComponent(this);
        return tip;
    }

}
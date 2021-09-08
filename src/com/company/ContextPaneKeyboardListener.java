package com.company;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ContextPaneKeyboardListener implements KeyListener {
    private ContextPane cp;
    public ContextPaneKeyboardListener(ContextPane cp){
        this.cp=cp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

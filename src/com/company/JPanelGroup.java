package com.company;

import java.util.Vector;

public class JPanelGroup {
    private Vector<JCustomButton> buttons = new Vector<>();
    private JCustomButton actualButtonSelected = null;

    public JPanelGroup() {

    }
    public Vector<JCustomButton> getButtons() {
        return buttons;
    }
    public JCustomButton getActualButtonSelected() {
        return actualButtonSelected;
    }
    public boolean isSelected(JCustomButton m) {
        return (m == actualButtonSelected);
    }
    public int getButtonCount() {
        if (buttons == null) {
            return 0;
        } else {
            return buttons.size();
        }
    }

    public void add(JCustomButton b) {
        if(b == null) {
            return;
        }
        buttons.addElement(b);
        if (b.isClicked()) {
            if (actualButtonSelected == null) {
                actualButtonSelected = b;
            } else {
                b.setClicked(false);
            }
        }
    }

    public void remove(JCustomButton b) {
        if(b == null) {
            return;
        }
        buttons.removeElement(b);
        if(b == actualButtonSelected) {
            actualButtonSelected = null;
        }

    }

    public void setSelected(JCustomButton m) {
        if (m != null && m != actualButtonSelected) {
            JCustomButton oldSelection = actualButtonSelected;
            actualButtonSelected = m;
            if (oldSelection != null) {
                oldSelection.setClicked(false);
            }
            m.setClicked(true);
        }
    }

    public void clearSelection() {
        if (actualButtonSelected != null) {
            JCustomButton old = actualButtonSelected;
            actualButtonSelected = null;
            old.setClicked(false);
        }
    }

}

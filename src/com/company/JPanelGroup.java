package com.company;

import java.util.ArrayList;

public class JPanelGroup {
    private SudokuActionPanel sap;
    private ArrayList<JCustomButton> buttons = new ArrayList<>();
    private JCustomButton actualButtonSelected = null;

    public JPanelGroup(SudokuActionPanel sap) {
        this.sap=sap;
        initButtons();
    }

    private void initButtons() {
        for (int i = 0; i < 4; i++) {
            buttons.add(new JCustomButton());
            buttons.get(i).setBackground(MyColorPalette.WHITE);
        }


        buttons.get(0).setName("Digit");
        buttons.get(1).setName("Corner");
        buttons.get(2).setName("Center");
        buttons.get(3).setName("Color");
        setActualButtonSelected(buttons.get(0));


    }

    public ArrayList<JCustomButton> getButtons() {
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
        buttons.add(b);
        if (b.isClicked()) {
            if (actualButtonSelected == null) {
                setActualButtonSelected(b);
            } else {
                b.setClicked(false);
            }
        }
    }

    public void remove(JCustomButton b) {
        if(b == null) {
            return;
        }
        buttons.remove(b);
        if(b == actualButtonSelected) {
            actualButtonSelected = null;
        }

    }

    public void setActualButtonSelected(JCustomButton m) {
        if (m != null) {
            for (int i = 0; i < buttons.size(); i++) {
                if (buttons.get(i).equals(m)){
                    actualButtonSelected=buttons.get(i);
                    buttons.get(i).setClicked(true);
                }else {
                    buttons.get(i).setClicked(false);
                }
            }
        }

    }

    public void clearSelection() {
        if (actualButtonSelected != null) {
            JCustomButton old = actualButtonSelected;
            actualButtonSelected = null;
            old.setClicked(false);
        }
    }

    @Override
    public String toString() {
        int pos=-1;
        for (int i = 0, buttonsSize = buttons.size(); i < buttonsSize; i++) {
            if (buttons.get(i).equals(actualButtonSelected)){
                pos=i;
                break;
            }
        }

        return "JPanelGroup{" +
                "buttons=" + buttons +
                ", aBS={"+pos+ "_" + actualButtonSelected +"}"+
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        JPanelGroup that = (JPanelGroup) o;

        return buttons.size()==that.buttons.size() && actualButtonSelected.equals(that.actualButtonSelected);
    }

}

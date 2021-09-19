package com.company;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Stack;

public class ContextPaneKeyboardListener implements KeyListener {
    private ContextPane cp;
    private Stack<Key> heldKeys = new Stack<>();
    private Stack<Key> heldKeysCheckChanges = new Stack<>();
    private Key clickKey;
    private Key tempKey;
    public ContextPaneKeyboardListener(ContextPane cp){
        this.cp=cp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        Key evk=new Key(e.getKeyCode(),e.getKeyLocation(),true);
        if (evk.isClickable || evk.isHoldable){
            boolean beenClicked = evk.equals(clickKey);
            if (!beenClicked){
                for (int i = 0; i < heldKeys.size(); i++) {
                    if (heldKeys.get(i).equals(evk)){
                        beenClicked=true;
                        break;
                    }
                }
            }
            clickKey=evk;

            if (!beenClicked){
                if (clickKey.isHoldable){
                    System.out.println("add");
                    heldKeysCheckChanges.clear();
                    heldKeysCheckChanges.addAll(heldKeys);
                    heldKeys.add(clickKey);
                    actionHold();
                }
                actionClick();
                System.out.println("P:"+this);
            }
        }



    }

    @Override
    public void keyReleased(KeyEvent e) {
        cp.getSap().repaint();
        Key evk=new Key(e.getKeyCode(),e.getKeyLocation(),true);
        if (evk.isHoldable||evk.isClickable){
            if (clickKey!=null) {
                if (clickKey.equals(evk)) {
                    clickKey = null;
                }
            }
            if (evk.isHoldable){
                for (int i = 0; i < heldKeys.size(); i++) {
                    //System.out.println(heldKeys.get(i) +" ---- " + evk);
                    if (heldKeys.get(i).equals(evk)){
                        System.out.println("remove");
                        heldKeysCheckChanges.clear();
                        heldKeysCheckChanges.addAll(heldKeys);
                        heldKeys.remove(evk);
                        System.out.println("++++++");
                        actionHold();
                        break;
                    }
                }
            }
            System.out.println("R:"+this);
        }
    }

    private void actionHold() {
        if (heldKeys.size()!=0||heldKeysCheckChanges.size()!=0){
            if (!heldKeys.equals(heldKeysCheckChanges)){
                boolean adding=heldKeys.size()>heldKeysCheckChanges.size();
                //System.out.println(adding+"\\\\\\\\\\\\\\\\\\\\\\\\");
                if (adding){
                    switch (heldKeys.peek().keyCode){
                        case 16: //SHIFT - actionButtons[1][3].setName("Corner");
                            addNewStack(1);
                            break;
                        case 17: //CTRL - actionButtons[2][3].setName("Center");
                            addNewStack(2);
                            break;
                        case 18: //ALT - actionButtons[3][3].setName("Color");
                            addNewStack(3);
                            break;
                    }
                }else{
                    Key keyRemoved = null;
                    for (int i = 0; i < heldKeysCheckChanges.size(); i++) {
                        if (i>=heldKeys.size()){
                            keyRemoved=heldKeysCheckChanges.get(i);
                            break;
                        }
                        if (!(heldKeysCheckChanges.get(i).equals(heldKeys.get(i)))){
                            keyRemoved=heldKeysCheckChanges.get(i);
                            break;
                        }
                    }
                    if (keyRemoved!=null){
                        switch (keyRemoved.keyCode){
                            case 16: //SHIFT - actionButtons[1][3].setName("Corner");
                                removeFromStack(1);
                                break;
                            case 17: //CTRL - actionButtons[2][3].setName("Center");
                                removeFromStack(2);
                                break;
                            case 18: //ALT - actionButtons[3][3].setName("Color");
                                removeFromStack(3);
                                break;
                        }
                    }else{
                        System.out.println("No keys removed?!");
                    }
                }

            }


        }
    }

    private void addNewStack(int buttonPos) {
        JPanelGroup newPanelGroup = new JPanelGroup(cp.getSap());
        newPanelGroup.setActualButtonSelected(newPanelGroup.getButtons().get(buttonPos));
        cp.getSap().getPanelGroup().add(newPanelGroup);

        cp.getSap().FIStackUpdate();
        for (int i = 0; i < cp.getSap().getPanelGroup().size(); i++) {
            //System.out.println(i+" "+cp.getSap().getPanelGroup().get(i));
        }

    }

    private void removeFromStack(int buttonPos) {

        JPanelGroup newPanelGroup = new JPanelGroup(cp.getSap());
        newPanelGroup.setActualButtonSelected(newPanelGroup.getButtons().get(buttonPos));
        int positionInStack=-1;
        for (int i = 0; i < cp.getSap().getPanelGroup().size() ; i++) {
            if (cp.getSap().getPanelGroup().get(i).equals(newPanelGroup)){
                positionInStack=i;
                break;
            }
        }
        if (positionInStack>=0){
            cp.getSap().getPanelGroup().remove(positionInStack);
        }else{
            System.out.println("No sequence found");
        }
        cp.getSap().FIStackUpdate();
    }


    private void actionClick() {

    }

    @Override
    public String toString() {
        return "CPKL{" +
                "hK=" + heldKeys +
                ", cK=" + clickKey +
                '}';
    }
}
class Key{
    int keyCode;
    int location;
    String name;
    boolean isClickable;
    boolean isHoldable;

    public Key(int keyCode,int location){
        this.keyCode=keyCode;
        this.location=location;
        calcClick();
        calcHold();
    }

    public Key(int keyCode,int location, boolean calcName){
        this(keyCode, location);
        if (calcName){
            calcName();
        }
    }

    private void calcName(){
        name="";
        switch (location){
            case 2:
                name+="LEFT_";
                break;
            case 3:
                name+="RIGHT_";
                break;
            case 4:
                name+="NUM_PAD_";
                break;
            default:
                break;
        }
        if (keyCode>=65 && keyCode<=90){
            name+=(char)('A'+keyCode-65);
        }
        else if (keyCode>=48 && keyCode<=57){
            name+=(char)('0'+keyCode-48);
        }
        else if (keyCode>=96 && keyCode<=105){
            name+=(char)('0'+keyCode-96);
        }
        else {
            switch (keyCode){
                case 37:
                    name+="AK_LEFT";
                    break;
                case 38:
                    name+="AK_UP";
                    break;
                case 39:
                    name+="AK_RIGHT";
                    break;
                case 40:
                    name+="AK_DOWN";
                    break;
                case 16:
                    name+="SHIFT";
                    break;
                case 17:
                    name+="CTRL";
                    break;
                case 18:
                    name+="ALT";
                    break;
                case 27:
                    name+="ESC";
                    break;
                case 8:
                    name+="BSP";
                    break;
                case 127:
                    name+="DEL";
                    break;
            }
        }

    }
    private void calcClick(){
        isClickable = keyCode==27 ||
                (keyCode>=96 && keyCode<=105) || (keyCode>=48 && keyCode<=57) ||
                keyCode==8 || keyCode==127 ||
                keyCode==90 || keyCode==88 || keyCode==67 || keyCode==86 ||
                keyCode==65 || keyCode==83 || keyCode==68 || keyCode==87||
                keyCode==37 || keyCode==38 || keyCode==39 || keyCode==40;
    }
    private void calcHold(){
        isHoldable = keyCode==16 || keyCode==17 || keyCode==18;
    }
    @Override
    public String toString() {
        return "Key{" +
                "kC=" + keyCode +
                ", l=" + location +
                ", n=" + (name==null || name.equals("") ? "NULL" :('\''+ name + '\'')) +
                ", isC=" + (isClickable ? "T" : "F") +
                ", isH=" + (isHoldable ? "T" : "F") +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Key key = (Key) o;
        return keyCode == key.keyCode && location == key.location;
    }
    public boolean equalsVal(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Key key = (Key) o;
        return keyCode == key.keyCode;
    }
}

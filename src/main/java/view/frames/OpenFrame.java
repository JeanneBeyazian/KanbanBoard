package view.frames;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenFrame extends JScrollPane {

    public OpenFrame() {
        setSize(500,500);
//        add.
    }

    public static void main(String[] args) {
        OpenFrame frame = new OpenFrame();
        frame.setVisible(true);
    }
}

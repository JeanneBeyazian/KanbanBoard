package view.frames;

import view.boardComponents.BoardPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCardFrame extends AddFrame implements ActionListener {

    public AddCardFrame(BoardPanel currentPanel) {
        super("card", currentPanel);
    }

    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == submit) {

        }
        else {
            showError();
        }

    }
}

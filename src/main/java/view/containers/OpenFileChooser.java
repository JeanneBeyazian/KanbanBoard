package view.containers;

import annotations.ClassAnnotation;
import controller.Load;
import view.KanbanBoard;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

@ClassAnnotation(
        classAuthors = "Jeanne",
        classEditors = "Nathan",
        creationDate = "13/12/2019",
        lastEdit = "13/12/2019"
)
/**
 * JFileChooser that allows the user to open a new board.
 * Only permits to choose json files.
 */
public class OpenFileChooser extends JFileChooser {

    public OpenFileChooser() {
        setFileSelectionMode(JFileChooser.FILES_ONLY);
        addChoosableFileFilter(new FileNameExtensionFilter("json file (*.json)", "json"));
        setAcceptAllFileFilterUsed(true);

        int response = showOpenDialog(this);

        if (response == JFileChooser.APPROVE_OPTION) {
            File file = getSelectedFile();

            // Make sure a file has been selected
            if (file != null) {
                String openBoard = file.getName().substring(0, file.getName().length() - 5);
                new KanbanBoard(openBoard).setVisible(true);
                KanbanBoard.openBoard(Load.loadBoard(openBoard));
            }
        }
    }
}

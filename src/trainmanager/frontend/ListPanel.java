package trainmanager.frontend;

import trainmanager.TrainManager;
import trainmanager.backend.Locomotive;
import trainmanager.backend.Train;
import trainmanager.backend.TrainList;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ListPanel extends JPanel {

    public final TrainManager root;
    public final TrainList trainList;

    public ListPanel(TrainManager root, TrainList trainList) {
        super();
        this.root = root;
        this.trainList = trainList;
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setBorder(
                new CompoundBorder(
                        new EmptyBorder(30, 60, 30, 60),
                        new LineBorder(Color.BLACK, 2)
                ));
        this.render();
    }

    public void render() {
        this.removeAll();
        for (Train train : this.trainList) {
            this.add(new TrainPanel(this, train));
            this.add(new JSeparator());
        }
        JPanel lowerPanel = new JPanel();

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(actionEvent -> this.root.trySaveData());
        lowerPanel.add(saveButton);

        JButton addButton = new JButton("Add Train");
        addButton.addActionListener(actionEvent -> this.addTrain());
        lowerPanel.add(addButton);

        this.add(lowerPanel);
        this.revalidate();
    }

    public void addTrain() {
        try {
            int maxLoad = Integer.parseUnsignedInt(
                    JOptionPane.showInputDialog(
                            this,
                            "Enter locomotive's maximum load"
                    ));
            this.trainList.add(new Train(new Locomotive(maxLoad)));
            this.render();
            this.root.pack();
        } catch (NumberFormatException e) {
            this.root.showErrorDialog("Invalid input");
        }
    }

    public void removeTrain(Train train) {
        this.trainList.remove(train);
        this.render();
        this.root.pack();
    }
}


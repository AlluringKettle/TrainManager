package trainmanager.frontend;

import trainmanager.backend.Carriage;
import trainmanager.backend.Locomotive;
import trainmanager.backend.Train;

import javax.swing.*;
import java.awt.*;

public class TrainPanel extends JPanel {

    public final ListPanel parent;
    public final Train train;

    public TrainPanel(ListPanel parent, Train train) {
        super(new FlowLayout(FlowLayout.LEFT));
        this.parent = parent;
        this.train = train;
        this.render();
    }

    public void render() {
        this.removeAll();
        for (Carriage car : this.train) {
            JButton carButton = new JButton(car.getText());
            carButton.setBackground(Color.LIGHT_GRAY);
            carButton.addActionListener(actionEvent -> this.removeCar(car));
            this.add(carButton);
        }
        JButton button = new JButton("+");
        button.addActionListener(actionEvent -> this.addCar());

        this.add(button);
        this.revalidate();
        this.repaint();
    }

    public void addCar() {
        try {
            this.train.addCar(new Carriage());
            this.render();
            this.parent.root.pack();
        } catch (Train.LocomotiveLoadExceeded e) {
            JOptionPane.showMessageDialog(
                    this,
                    "This locomotive cannot pull more cars!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void removeCar(Carriage car) {
        if (car instanceof Locomotive) {
            this.parent.removeTrain(train);
        } else {
            this.train.remove(car);
            this.render();
            this.parent.root.pack();
        }
    }
}



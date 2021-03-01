package trainmanager;

import trainmanager.backend.TrainList;
import trainmanager.frontend.ListPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

public class TrainManager extends JFrame {

    public TrainList trainList;
    public String dataFileName = "data.bin";

    public TrainManager() throws HeadlessException {
        super("Train Manager");
        this.tryReadData();
        this.setResizable(false);
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                trySaveData();
            }
        });
        this.getContentPane().add(new ListPanel(this, this.trainList));
        this.pack();
        this.setVisible(true);
    }

    public void showInfoDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void trySaveData() {
        try {
            saveToFile(this.dataFileName, this.trainList);
        } catch (IOException e) {
            e.printStackTrace();
            this.showErrorDialog("Unable to save data");
        }
    }

    public void tryReadData() {
        try {
            this.trainList = (TrainList) readFromFile(this.dataFileName);
        } catch (FileNotFoundException e) {
            this.showInfoDialog("Data file not found, creating blank file");
            this.trainList = new TrainList();
            this.trySaveData();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            this.showErrorDialog("Unable to read data");
            this.dispose();
        }
    }

    public static void saveToFile(String fileName, Object object) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(object);
        oos.close();
    }

    public static Object readFromFile(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object object = ois.readObject();
        ois.close();
        return object;
    }

    public static void main(String[] args) {
        new TrainManager();
    }
}


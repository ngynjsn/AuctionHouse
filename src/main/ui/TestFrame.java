package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//public class AddFrame extends Frame implements ActionListener {

public class TestFrame extends Frame implements ActionListener {
    private Label lblInput;
    private Label lblOutput;
    private TextField tfInput;
    private TextField tfOutput;
    private int sum = 0;

    public TestFrame() {
        setLayout(new FlowLayout());

        lblInput = new Label("Enter number of students: ");
        add(lblInput);

        tfInput = new TextField(5);
        add(tfInput);

        tfInput.addActionListener(this);

        lblOutput = new Label("The cost per student is: ");
        add(lblOutput);

        tfOutput = new TextField(20);
        tfOutput.setEditable(false);
        add(tfOutput);

        setTitle("Task1GUI");
        setSize(350, 120);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0); //calling the method is a must
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        int numOfStudents = Integer.parseInt(tfInput.getText());
        int coachCost = 550;
        int entrycost = 30;
        int totalcost;
        int numFree;
        int discount;
        int costPerPerson;
        if (numOfStudents < 45) {
            totalcost = coachCost + (numOfStudents * 30);
            numFree = numOfStudents / 10;
            discount = numFree * 30;
            costPerPerson = (totalcost - discount) / numOfStudents;
            tfInput.setText("");
            tfOutput.setText(costPerPerson + "");
        } else {
            tfOutput.setText("Too many students entered");
        }
    }

    public static void main(String[] args) {
        new TestFrame();
    }
}

package askey;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

	public class EmployeeForm extends JFrame {
	    private JTextField empNoField, empNameField, ageField, salaryField;

	    public EmployeeForm() {
	        setTitle("Employee Form");
	        setSize(300, 200);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        JPanel panel = new JPanel(new GridLayout(5, 2));

	        panel.add(new JLabel("Emp No:"));
	        empNoField = new JTextField();
	        panel.add(empNoField);

	        panel.add(new JLabel("Emp Name:"));
	        empNameField = new JTextField();
	        panel.add(empNameField);

	        panel.add(new JLabel("Age:"));
	        ageField = new JTextField();
	        panel.add(ageField);

	        panel.add(new JLabel("Salary:"));
	        salaryField = new JTextField();
	        panel.add(salaryField);

	        JButton saveButton = new JButton("Save");
	        saveButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                saveEmployee();
	            }
	        });
	        panel.add(saveButton);

	        add(panel);
	        setVisible(true);
	    }

	    private void saveEmployee() {
	        try {
	            String empNo = empNoField.getText();
	            String empName = empNameField.getText();
	            int age = Integer.parseInt(ageField.getText());
	            double salary = Double.parseDouble(salaryField.getText());

	            // JDBC connection
	            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/asky","root","PHW#84#jeor");

	            // SQL query
	            String query = "INSERT INTO employees (empNo, empName, age, salary) VALUES (?, ?, ?, ?)";
	            PreparedStatement preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setString(1, empNo);
	            preparedStatement.setString(2, empName);
	            preparedStatement.setInt(3, age);
	            preparedStatement.setDouble(4, salary);

	            // Execute the query
	            preparedStatement.executeUpdate();

	            JOptionPane.showMessageDialog(this, "Employee saved successfully!");
	            
	            // Close resources
	            preparedStatement.close();
	            connection.close();

	        } catch (SQLException | NumberFormatException ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(this, "Error saving employee!");
	        }
	    }

	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                new EmployeeForm();
	            }
	        });
	    }
	}



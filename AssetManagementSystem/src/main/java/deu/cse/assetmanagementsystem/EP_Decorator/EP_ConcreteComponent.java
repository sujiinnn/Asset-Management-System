/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deu.cse.assetmanagementsystem.EP_Decorator;

import java.io.*;
import javax.swing.JOptionPane;

/**
 *
 * @author 이가연
 */
public class EP_ConcreteComponent implements EP_Component {

    private String logFilePath;

    public EP_ConcreteComponent(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    @Override
    public void registerExpenditure(String amount,String EP, String category) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFilePath, true))) {
            writer.write(category);
            writer.write("  ("+EP+")  ");
            writer.write(": ");
            writer.write(amount);
            writer.write("\n");
            System.out.println("등록 내역 -> " + amount);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

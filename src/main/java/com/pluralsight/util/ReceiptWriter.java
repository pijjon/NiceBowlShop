package com.pluralsight.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptWriter {

    public void saveReceiptToFile(String receiptString) {
        String fileName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss")) + ".txt";
        String path = "receipts/" + fileName;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write("Receipt: " + fileName);
            writer.write("\n" + receiptString);
        }
        catch (IOException e) {
            System.out.println("Error writing to receipt");
            e.printStackTrace();
        }
    }

}

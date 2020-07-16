package lesson2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MakeFile {
    public static void main (String [] args){
        try (FileOutputStream fileToBeWritten = new FileOutputStream("File1.csv")) {
            fileToBeWritten.write("Java".getBytes());
        } catch (IOException e){
            e.printStackTrace();

            try (FileInputStream in = new FileInputStream(new FileInputStream("File.csv")) {
                int x;
                while ((x = in.read()) != -1) {
                    System.out.print((char)x);
                }
            } catch (IOException e) {
                e.printStackTrace();
        }
    }
}

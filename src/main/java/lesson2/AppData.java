package lesson2;

import org.sqlite.util.StringUtils;

import java.io.*;
import java.util.ArrayList;

public class AppData {
    private String[] header;
    private int[][] data;

    public String[] getHeader() {
        return header;
    }

    public void setHeader(String[] header) {
        this.header = header;
    }

    public int[][] getData() {
        return data;
    }

    public void setData(int[][] data) {
        this.data = data;
    }

    public void save(String filename) {
        try (BufferedWriter fileToBeWritten = new BufferedWriter(new FileWriter(filename))) {
            fileToBeWritten.write(String.join(";", this.header) + "\n");

            for (int[] row : this.data) {
                for (int i=0; i<row.length; i++) {
                    fileToBeWritten.write(Integer.toString(row[i]));
                    if (i<row.length-1) {
                        fileToBeWritten.write(";");
                    }
                }
                fileToBeWritten.write("\n");
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static AppData load(String filename) {
        AppData appData = new AppData();

        try (BufferedReader fileToBeRead = new BufferedReader(new FileReader(filename))) {
            String header = fileToBeRead.readLine();
            appData.setHeader(header.split(";"));

            ArrayList<int[]> data = new ArrayList<int[]>();
            String row;
            while ( (row = fileToBeRead.readLine()) != null) {
                String[] splitted = row.split(";");
                int[] intRow = new int[splitted.length];
                for (int i=0; i<splitted.length;i++) {
                    intRow[i] = Integer.parseInt(splitted[i]);
                }
                data.add(intRow);
            }

            int[][] dataArray = new int[data.size()][];
            for (int i=0; i<data.size(); i++ ) {
                dataArray[i] = data.get(i);
            }
            appData.setData(dataArray);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return appData;
    }
}

package com.dialogs.output;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.dialogs.ds.Employee;

public class Display {
	public static void readData(){
	{
	 try (FileReader reader = new FileReader("C:\\Users\\jyotic\\test1");
             BufferedReader br = new BufferedReader(reader)) {

            // read line by line
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
	}
	
	 
}
}

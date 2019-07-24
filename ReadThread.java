package com.dialogs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Display;

import com.dialogs.ds.Employee;

public class ReadThread extends Thread {

	private Display display;
	private List<Employee> employeeList;
	private TableViewer employeetableViewer;

	public ReadThread(Display display,List<Employee> employeeList, TableViewer employeetableViewer) {
		this.display = display;
		this.employeeList =employeeList;
		this.employeetableViewer = employeetableViewer;
	}

	@Override
	public void run() {
		try (FileReader reader = new FileReader("C:\\Users\\jyotic\\employeeData.csv");
	             BufferedReader br = new BufferedReader(reader)) {
	            String line;
	            while ((line = br.readLine()) != null) {
	            	 
	            	String[] stringArray=line.split(",");
	            	Employee e=new Employee(stringArray[0],Integer.parseInt(stringArray[1]),stringArray[2],stringArray[3]);
	            	employeeList.add(e);
	            }
	            display.asyncExec(new Runnable() {
					@Override
					public void run() {
						employeetableViewer.refresh();
					}
				});
	            
	        } catch (IOException e) {
	            System.err.format("IOException: %s%n", e);
	        }
	}

}

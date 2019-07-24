package com.dialogs;


import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import com.dialogs.ds.Employee;
import com.dialogs.exception.EmployeeException;

import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

public class JfaceTitleDialog1 extends TitleAreaDialog {
	private Text Name_text;
	private Text Age_text;
	private StyledText Address_text;
	private ArrayList<Employee> employeeList = new ArrayList<>();
	private TableViewer employeetableViewer;
	private Table table;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	
	public JfaceTitleDialog1(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 *
	 */
	
	
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
	
		Composite titleComposite = new Composite(container, SWT.NONE);
		titleComposite.setLayout(new GridLayout(2, false));
		GridData gd_titleComposite = new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1);
		gd_titleComposite.widthHint = 424;
		gd_titleComposite.heightHint = 28;
		titleComposite.setLayoutData(gd_titleComposite);
		new Label(titleComposite, SWT.NONE);
		
		Label title = new Label(titleComposite, SWT.NONE);
		title.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1));
		title.setText("Employee Data Entry");
		
		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		GridData gd_composite = new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1);
		gd_composite.widthHint = 424;
		gd_composite.heightHint = 505;
		composite.setLayoutData(gd_composite);
		
		Label lblName = new Label(composite, SWT.NONE);
		lblName.setText("Name");
		
		Name_text = new Text(composite, SWT.BORDER);
		Name_text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		Name_text.setTextLimit(50);
		
		
		
		
		Label lblAge = new Label(composite, SWT.NONE);
		lblAge.setText("Age");
		
		Age_text = new Text(composite, SWT.BORDER);
		/*Age_text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try{
					int i = Integer.parseInt(Age_text.getText());
				}catch(NumberFormatException ne){
					JOptionPane.showMessageDialog(null,"Please enter only numbers");
				}
			}
		});
		*/
		
		
		Age_text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		
		
		Label lblAddress = new Label(composite, SWT.NONE);
		lblAddress.setText("Address");
		
		Address_text = new StyledText(composite, SWT.BORDER);
		GridData gd_Address_text = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_Address_text.widthHint = 229;
		gd_Address_text.heightHint = 79;
		Address_text.setLayoutData(gd_Address_text);
		Address_text.setTextLimit(10);
		
		Label lblGender = new Label(composite, SWT.NONE);
		lblGender.setText("Gender");
		
		Combo combo = new Combo(composite, SWT.NONE);
		String[] items = new String[] { "Male", "Female" };
		combo.setItems(items);
		
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite, SWT.NONE);
		
		Button btnSubmit = new Button(composite, SWT.NONE);
		btnSubmit.addMouseListener(new MouseAdapter() {
		
		});
		
		
		btnSubmit.addSelectionListener(new SelectionAdapter() {
			@SuppressWarnings("resource")
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				try{
					
					FileWriter fileWriter = new FileWriter("C:\\Users\\jyotic\\employeeData.csv", true); //Set true for append mode
					Employee employee=new Employee(Name_text.getText(),Integer.parseInt(Age_text.getText()),Address_text.getText(),combo.getText());
					
					employeeList.add(employee);

					try{
						Pattern nameValid = Pattern.compile("^[a-zA-Z0-9]*$");
						Matcher matcher = nameValid.matcher(Name_text.getText());
						if(!matcher.matches()) {
							throw new EmployeeException("Please enter valid name");
						}	
							
					}catch (EmployeeException ex) 
			        { 
			            System.err.println(ex.getMessage()); 
			        } 
					try{
						Pattern addressValid = Pattern.compile("^[a-zA-Z0-9]*$");
						Matcher matcher2 = addressValid.matcher(Address_text.getText());
						if(!matcher2.matches()) {
							throw new EmployeeException("Please enter valid address");
						}	
					}catch (EmployeeException ex) 
			        { 
			            System.err.println(ex.getMessage()); 
			        } 
					
					
					try{
						Pattern ageValid = Pattern.compile("^[0-9]{1,2}[:.,-]?$");
						Matcher matcher3 = ageValid.matcher(Age_text.getText());
						if(!matcher3.matches()) {
							throw new EmployeeException("Please enter valid age");
						}
					}catch (EmployeeException ex) 
			        { 
			            System.err.println(ex.getMessage()); 
			        } 
					 PrintWriter printWriter = new PrintWriter(fileWriter);
					 //printWriter.write("Name"+","+"Age"+","+"Address"+","+"Gender");
					 printWriter.println(employee.getName()+","+employee.getAge()+","+employee.getAddress()+","+employee.getGender());
					   
					    printWriter.close();
					
					
					employeetableViewer.refresh();
					
					
					
					//System.out.println(employeeList);
				
					
				}catch(ArithmeticException ae){
					ae.printStackTrace();
					Name_text.setText("");
					Age_text.setText("");
					Address_text.setText("");
					combo.setText("");
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(Name_text.getText().isEmpty()|| Age_text.getText().isEmpty() || Address_text.getText().isEmpty()|| combo.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null,"Please enter all fields");
				}
			}
			
		});
		
		
		GridData gd_btnSubmit = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 2);
		gd_btnSubmit.widthHint = 563;
		btnSubmit.setLayoutData(gd_btnSubmit);
		btnSubmit.setText("Submit");
		
		
		
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		
		
		employeetableViewer = new TableViewer(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table = employeetableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		TableViewerColumn tableViewerColumn = new TableViewerColumn(employeetableViewer, SWT.NONE);
		TableColumn tblclmnEmployeeName = tableViewerColumn.getColumn();
		tblclmnEmployeeName.setWidth(100);
		tblclmnEmployeeName.setText("Name");
       tableViewerColumn.setLabelProvider(new ColumnLabelProvider(){
			
			@Override
			public String getText(Object element) {
				Employee employee = (Employee) element;
				return employee.getName();
			}
		});
       
      
       //private void createTableViewer(Composite viewDataComposite) {
		
		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(employeetableViewer, SWT.NONE);
		TableColumn tblclmnAge = tableViewerColumn_1.getColumn();
		tblclmnAge.setWidth(100);
		tblclmnAge.setText("Age");
		
		
        tableViewerColumn_1.setLabelProvider(new ColumnLabelProvider(){
			
			@Override
			public String getText(Object element) {
				Employee employee = (Employee) element;
				return String.valueOf(employee.getAge());
			}
		});
		
		TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(employeetableViewer, SWT.NONE);
		TableColumn tblclmnAddress = tableViewerColumn_2.getColumn();
		tblclmnAddress.setWidth(100);
		tblclmnAddress.setText("Address");
		
		
		
		tableViewerColumn_2.setLabelProvider(new ColumnLabelProvider(){
			
			@Override
			public String getText(Object element) {
				Employee employee = (Employee) element;
				return employee.getAddress();
			}
		});
		
		TableViewerColumn tableViewerColumn_3 = new TableViewerColumn(employeetableViewer, SWT.NONE);
		TableColumn tblclmnGender = tableViewerColumn_3.getColumn();
		tblclmnGender.setWidth(100);
		tblclmnGender.setText("Gender");
		
		
		tableViewerColumn_3.setLabelProvider(new ColumnLabelProvider(){
			
			@Override
			public String getText(Object element) {
				Employee employee = (Employee) element;
				return employee.getGender();
			}
		});
		
       //}
       
     employeetableViewer.setContentProvider(new ArrayContentProvider());
     employeetableViewer.setInput(employeeList);
      readData();
	return container;
	}
	
	
	
	 

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.FINISH_LABEL, true);
		
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}
	
	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(623, 685);
	}
	
	
	
	private  void readData(){
		Display display = getShell().getDisplay().getCurrent();
		ReadThread readThread = new ReadThread(display, employeeList, employeetableViewer);
		readThread.start();
	}	

	
	
}

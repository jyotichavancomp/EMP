package com.dialogs;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class Dialog1 extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text namTextBox;
	private Text ageTextBox;
	private Table studentDataTable;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public Dialog1(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), getStyle());
		shell.setSize(450, 300);
		shell.setText(getText());
		shell.setLayout(new GridLayout(1, false));
		
		Composite mainComposite = new Composite(shell, SWT.NONE);
		mainComposite.setLayout(new GridLayout(1, false));
		mainComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Composite headerComposite = new Composite(mainComposite, SWT.NONE);
		headerComposite.setLayout(new GridLayout(1, false));
		GridData gd_headerComposite = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_headerComposite.heightHint = 38;
		headerComposite.setLayoutData(gd_headerComposite);
		
		Label lblStudentData = new Label(headerComposite, SWT.NONE);
		lblStudentData.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1));
		lblStudentData.setText("STUDENT DATA");
		
		Composite dataComposite = new Composite(mainComposite, SWT.NONE);
		dataComposite.setLayout(new GridLayout(2, false));
		dataComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		Composite viewDataComposite = new Composite(mainComposite, SWT.NONE);
		viewDataComposite.setLayout(new GridLayout(1, false));
		viewDataComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		studentDataTable = new Table(viewDataComposite, SWT.BORDER | SWT.FULL_SELECTION);
		studentDataTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		studentDataTable.setHeaderVisible(true);
		studentDataTable.setLinesVisible(true);
		
		TableColumn tblclmnStudentName = new TableColumn(studentDataTable, SWT.NONE);
		tblclmnStudentName.setWidth(211);
		tblclmnStudentName.setText("Student Name");
		
		TableColumn tblclmnNewColumn = new TableColumn(studentDataTable, SWT.NONE);
		tblclmnNewColumn.setWidth(195);
		tblclmnNewColumn.setText("Age");
		
		Label lblStudentName = new Label(dataComposite, SWT.NONE);
		lblStudentName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblStudentName.setText("Student Name");
		
		namTextBox = new Text(dataComposite, SWT.BORDER);
		namTextBox.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		namTextBox.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				System.out.println("Name:"+namTextBox.getText());
			}
		});
		
		Label lblAge = new Label(dataComposite, SWT.NONE);
		lblAge.setText("Age");
		
		ageTextBox = new Text(dataComposite, SWT.BORDER);
		ageTextBox.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(dataComposite, SWT.NONE);
		
		Button btnSubmit = new Button(dataComposite, SWT.NONE);
		btnSubmit.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnSubmit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem = new TableItem(studentDataTable, SWT.NONE);
				tableItem.setText(new String[] {namTextBox.getText(), ageTextBox.getText()});
			}
		});
		btnSubmit.setText("Submit");

	}
}

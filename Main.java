package com.test;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

import com.dialogs.Dialog1;
import com.dialogs.JfaceDialog1;
import com.dialogs.JfaceTitleDialog1;

public class Main {

	
	public static void main(String[] args) {
		
		JfaceTitleDialog1 jfaceTitleDialog1 = new JfaceTitleDialog1(new Shell());
		jfaceTitleDialog1.open();
		
	}
}

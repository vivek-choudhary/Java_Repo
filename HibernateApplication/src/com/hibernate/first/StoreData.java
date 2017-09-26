package com.hibernate.first;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;



public class StoreData {
	public static void main(String[] args) {

		System.out.println("Start");
		FileInputStream file2;
		Employee e1 = null;
		try {
			file2 = new FileInputStream(new File("D://excel.xls"));
			HSSFWorkbook workbook = new HSSFWorkbook(file2);
	        HSSFSheet sheet = workbook.getSheetAt(0);
	        Iterator<Row> rowIterator = sheet.iterator();
	        rowIterator.next();
	        while(rowIterator.hasNext())
	        {
	            Row row = rowIterator.next();
	            //For each row, iterate through each columns
	            Iterator<Cell> cellIterator = row.cellIterator();
	            while(cellIterator.hasNext())
	            {
	            	int i = 0;
	                Cell cell = cellIterator.next();
					String value2 = cell.getStringCellValue();
					System.out.println("value1: "+value2);
					e1 = new Employee(i,value2,value2);
					i++;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// creating configuration object
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");// populates the data of the
											// configuration file

		// creating seession factory object
		SessionFactory factory = cfg.buildSessionFactory();

		// creating session object
		Session session = factory.openSession();

		// creating transaction object
		Transaction t = session.beginTransaction();
		
		session.persist(e1);// persisting the object

		t.commit();// transaction is committed
		session.close();
		System.out.println("successfully saved");

	}
}

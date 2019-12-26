/*
Team: Team4
Date: 7 Dec 2019
PA 7 -- Database Class
Bugs: None
*/
package cs12b.mySQL_PA;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;


public class Database {
	/**
	 * fields
	 */
	private LinkedList<Table> database;
	private DatabaseBackup backup;
	private int undoCount;
	/**
	 * constructor
	 */
	public Database() {
		this.database=new LinkedList<>();
		this.backup=new DatabaseBackup();
		this.undoCount = 0;
	}
	/**
	 * constructor
	 * @param db
	 * @param backup
	 */
	public Database(LinkedList<Table> db, DatabaseBackup backup) {
		this.database = db;
		this.backup = backup;
		this.undoCount = 0;
	}
	/**
	 * clone method for backup
	 */
	public Database clone() {
		LinkedList<Table> newlst = new LinkedList<>();
		for(Table t:this.database) {
			newlst.add(t.clone());
		}
		Database newdb = new Database(newlst, this.backup);
		return newdb;
	}
	/**
	 * backup helper for backup database
	 */
	private void backUpHelper() {
		//Database backupdb = new Database();
		Database db = this.clone();
		//Database db = new Database(this);
		this.backup.backUp(db);
	}
	/**
	 * add table to the database
	 * @param table
	 */
	public void addTable(Table table){
		backUpHelper();
		database.add(table);
		//backup.backUp(this);
	}
	/**
	 * remove table from the database
	 * @param table
	 */
	public void removeTable(Table table) {
		backUpHelper();
		for(Table t:database) {
			if(t.equals(table)) {
				database.remove(t);
				return;
			}
		}
		throw new NoSuchElementException("table does not exist");
		//database.remove(table);
		//backup.backUp(this);
	}
	/**
	 * add row to the given table
	 * @param tableName
	 * @param args
	 */
	public void addRowToTable(String tableName, String[] args){
		backUpHelper();
		Iterator<Table> iter=database.iterator();
		while(iter.hasNext()) {
			Table t=iter.next();
			if(t.getName().equals(tableName)) {
				Row r=new Row(args);
				t.addRow(r);
				//backup.backUp(this);
				return;
			}
				//else return -1;
		}
		throw new NoSuchElementException("Table does not exist.");
	}
	/**
	 * get the database field
	 * @return
	 */
	public LinkedList<Table> getDatabase(){
		return this.database;
	}
	/**
	 * get the backup field
	 * @return
	 */
	private DatabaseBackup getBackUp() {
		return this.backup;
	}
	/**
	 * retrieve database before the lastes operation
	 */
	public void undo() {
		if(this.undoCount < 3) {
			this.database = this.backup.getLatestBackUp().getDatabase();
			this.undoCount++;
		}
	}
	/**
	 * create new table
	 * @param name
	 * @param args
	 */
	public void create(String name, String[] args) {
		backUpHelper();
		TableColumns tc = new TableColumns(args);
		Table t = new Table(name,tc);
		addTable(t);
	}
	/**
	 * insert row into table
	 * @param name
	 * @param row
	 */
	public void insertInto(String name, String[] row){
		//if(addRowToTable(name,row)==1){
		//	return true;
		//}
		//else {
		//	return false;
		//}
		backUpHelper();
		addRowToTable(name, row);
	}
	/**
	 * select data from table
	 * @param columns
	 * @param t
	 * @return
	 */
	public Table selectFrom(String[] columns, Table t) {
		//Iterator<Table> iter=database.iterator();
		//Table work = null;
		//while(iter.hasNext()) {
			//Table t=iter.next();
			//if(t.name.equals(name)) {
			//	work=t;
				//break;
			//}
		//}
		Table toReturn=new Table("",new TableColumns(columns)); 
		LinkedList<Integer> colIndex=new LinkedList<>();
		for(String i:columns) {
			int a=t.getTableColumn().getColumn(i);
			if(a!=-1) {
			colIndex.add(a);
			}
		}
		for(Row r:t.getRow()) {
			List<String> l=new LinkedList<>();
			for(Integer i:colIndex) {
			l.add(r.getRowData()[i]);
			}
			String[] array = l.toArray(new String[0]);
			toReturn.addRow(new Row(array));
			
		}
		return toReturn;
	}
	/**
	 * find table by name
	 * @param name
	 * @return
	 */
	public Table foundTByName(String name) {
		Iterator<Table> t=database.iterator();
		while(t.hasNext()) {
			Table table=t.next();
			if(table.getName().equals(name)) {
				return table; 
			}
		}
		return null;
	}
	/**
	 * join table by column
	 * @param name1
	 * @param name2
	 * @param col1
	 * @param col2
	 * @return
	 */
	public Table joinOn(String name1, String name2,String col1, String col2) {
		Table table1=foundTByName(name1);
		Table table2=foundTByName(name2);
		String[] newcol=new String[table1.getTableColumn().getSize()+table2.getTableColumn().getSize()];
		int i=0;
		for(;i<table1.getTableColumn().getSize();i++) {
			newcol[i]=table1.getTableColumn().getColumnLst()[i];
		}
		for(;i<table1.getTableColumn().getSize()+table2.getTableColumn().getSize();i++) {
			newcol[i]=table2.getTableColumn().getColumnLst()[i-table1.getTableColumn().getSize()];
		}
		
		Table work=new Table("temp",new TableColumns(newcol));
		int a1=table1.getTableColumn().getColumn(col1);
		int a2=table2.getTableColumn().getColumn(col2);
		for(Row r:table1.getRow()) {
			for(Row r1:table2.getRow()) {
				if(r.getRowData()[a1].equals(r1.getRowData()[a2])) {
					String[] newrow=new String[r.getRowData().length+r1.getRowData().length];
					int a=0;
					for(;a<r.getRowData().length;a++) {
						newrow[a]=r.getRowData()[a];
					}
					for(;a<r.getRowData().length+r1.getRowData().length;a++) {
						newrow[a]=r1.getRowData()[a-r.getRowData().length];
					}
					
					work.addRow(new Row(newrow));
				}
			}
		}
		return work;
	}
	/**
	 * order table
	 * @param order
	 * @param t
	 * @param col
	 * @return
	 */
	public Table orderBy(String order, Table t, String col) {
		int index=t.getTableColumn().getColumn(col);
		Table list=new Table("",t.getTableColumn());
		
		if(order.equals("ASC")) {
		for(Row r:t.getRow()) {
			if(list.getRow().isEmpty()) {
				list.addRow(r);
			}
			else {
				Iterator<Row> iter=list.getRow(). iterator();
				int a=0;
				while(iter.hasNext()) {
					if(iter.next().getRowData()[index].compareTo(r.getRowData()[index])<0) {
						a++;
					}
					else {
						break;
					}
				}
				list.addRow(r,a);
			}
		}
		}
		else {
			for(Row r:t.getRow()) {
				if(list.getRow(). isEmpty()) {
					list.addRow(r);
				}
				else {
					Iterator<Row> iter=list.getRow().iterator();
					int a=0;
					while(iter.hasNext()) {
						if(iter.next().getRowData()[index].compareTo(r.getRowData()[index])>0) {
							a++;
						}
						else {
							break;
						}
					}
					list.addRow(r,a);
				}
			}
			
		}
		return list;
		
	}
	
}

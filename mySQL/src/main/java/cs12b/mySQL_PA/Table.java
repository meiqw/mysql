/*
Team: Team4
Date: 7 Dec 2019
PA 7 -- Table Class
Bugs: None
*/
package cs12b.mySQL_PA;

import java.util.LinkedList;

public class Table {
	/**
	 * fields
	 */
	private TableColumns column;
	private String name;
	private LinkedList<Row> data;
	/**
	 * constructor
	 * @param name
	 * @param column
	 */
	public Table(String name, TableColumns column) {
		this.column=column;
		this.name=name;
		this.data=new LinkedList<>();
	}
	/**
	 * constructor
	 * @param name
	 * @param column
	 * @param data
	 */
	public Table(String name, TableColumns column, LinkedList<Row> data) {
		this.column=column;
		this.name=name;
		this.data=data;
	}
	/**
	 * add row to the table
	 * @param row
	 */
	public void addRow(Row row) {
		if(row.getSize() != this.column.getSize()) {
			throw new IllegalArgumentException("Wrong row size.");
		}
		
		this.data.add(row);
	}
	/**
	 * add row to certain index of the table
	 * @param row
	 * @param index
	 */
	public void addRow(Row row,int index) {
		if(row.getSize() != this.column.getSize()) {
			throw new IllegalArgumentException("Wrong row size.");
		}
		
		this.data.add(index, row);
	}
	/**
	 * clone table
	 */
	public Table clone() {
		//TableColumns newt = this.column.clone();
		//String newname = new String(this.name);
		LinkedList<Row> newdata = new LinkedList<>();
		for(Row r:this.data) {
			newdata.add(r);
		}
		Table copy = new Table(this.name, this.column, newdata);
		return copy;
	}
	/**
	 * get name of the table
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * get Table Column field of the table
	 * @return
	 */
	public TableColumns getTableColumn() {
		return this.column;
	}
	/**
	 * get rows of the table
	 * @return
	 */
	public LinkedList<Row> getRow(){
		return this.data;
	}

}

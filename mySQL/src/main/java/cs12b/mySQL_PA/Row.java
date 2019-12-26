/*
Team: Team4
Date: 7 Dec 2019
PA 7 -- Row Class
Bugs: None
*/
package cs12b.mySQL_PA;

public class Row {
	/**
	 * field
	 */
	private String[] data;
	/**
	 * constructor
	 * @param row
	 */
	public Row(String[] row) {
		this.data=row;
	}
	/**
	 * get cell at index of the row
	 * @param index
	 * @return
	 */
	public String getCell(int index) {
		return this.data[index];
	}
	/**
	 * get size of the row
	 * @return
	 */
	public int getSize() {
		return this.data.length;
	}
	/**
	 * get the data in the row
	 * @return
	 */
	public String[] getRowData() {
		return this.data;
	}
}



/*
Team: Team4
Date: 7 Dec 2019
PA 7 -- TableColumns Class
Bugs: None
*/
package cs12b.mySQL_PA;

public class TableColumns {
	/**
	 * field
	 */
	private String[] list;
	/**
	 * constructor
	 * @param columnList
	 */
	public TableColumns(String[] columnList) {
		this.list=columnList;
	}
	/**
	 * get size of the TableColumns
	 * @return
	 */
	public int getSize() {
		return this.list.length;
	}
	/**
	 * get the index of certain column
	 * @param alias
	 * @return
	 */
	public int getColumn(String alias) {
		for(int i=0;i<this.list.length;i++) {
			if(this.list[i].equals(alias)) {
				return i;
			}
		}
		return -1;
	}
	/**
	 * get the columnlist
	 * @return
	 */
	public String[] getColumnLst() {
		return this.list;
	}
}

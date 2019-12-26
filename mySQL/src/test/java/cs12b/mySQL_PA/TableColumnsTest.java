/*
Team: Team4
Date: 7 Dec 2019
PA 7 -- TableColumnsTest Class
Bugs: None
*/
package cs12b.mySQL_PA;

import static org.junit.Assert.*;

import org.junit.Test;

public class TableColumnsTest {

	@Test
	public void test() {
		String[] fields = {"class","professor","TA_Id<i>"};
		TableColumns test = new TableColumns(fields);
		assertEquals(3, test.getSize());
		assertEquals(0,test.getColumn("class"));
		assertEquals(-1, test.getColumn("name"));
		assert(test.getColumnLst()[0].equals("class"));
		assert(test.getColumnLst()[1].equals("professor"));
		assert(test.getColumnLst()[2].equals("TA_Id<i>"));
	}

}

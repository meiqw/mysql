/*
Team: Team4
Date: 7 Dec 2019
PA 7 -- RowTest Class
Bugs: None
*/
package cs12b.mySQL_PA;

import static org.junit.Assert.*;

import org.junit.Test;

public class RowTest {

	@Test
	public void test() {
		String[] data = {"1","Ben","Segal"};
		Row test = new Row(data);
		assert(test.getCell(1).equals("Ben"));
		assertEquals(3,test.getSize());
		assert(test.getRowData()[0].equals("1"));
		assert(test.getRowData()[1].equals("Ben"));
		assert(test.getRowData()[2].equals("Segal"));
	}
}

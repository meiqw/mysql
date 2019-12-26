/*
Team: Team4
Date: 7 Dec 2019
PA 7 --TableTest Class
Bugs: None
*/
package cs12b.mySQL_PA;

import static org.junit.Assert.*;

import org.junit.Test;

public class TableTest {

	@Test
	public void test() {
		String[] fields = {"studentId<i>","name","surname"};
		TableColumns col = new TableColumns(fields);
		String[] data1 = {"1","Ben","Segal"};
		Row r1 = new Row(data1);
		Table t1 = new Table("t1",col);
		
		String[] data2 = {"1","Ben","Segal","2"};
		Row r2 = new Row(data2);
		boolean thrown = false;
		try {
			t1.addRow(r2);
		}catch(Exception e){
			thrown = true;
		}
		assertTrue(thrown);
		
		t1.addRow(r1);
		Row f1 = t1.getRow().get(0);
		assert(f1.getRowData()[0].equals("1"));
		assert(f1.getRowData()[1].equals("Ben"));
		assert(f1.getRowData()[2].equals("Segal"));
		
		String[] data3 = {"2","Sam","Stern"};
		Row r3 = new Row(data3);
		t1.addRow(r3,0);
		Row f2 = t1.getRow().get(0);
		assert(f2.getRowData()[0].equals("2"));
		assert(f2.getRowData()[1].equals("Sam"));
		assert(f2.getRowData()[2].equals("Stern"));
		
		assert(t1.getName().equals("t1"));
		
		assert(t1.getTableColumn().getColumnLst()[0].equals("studentId<i>"));
		assert(t1.getTableColumn().getColumnLst()[1].equals("name"));
		assert(t1.getTableColumn().getColumnLst()[2].equals("surname"));
		
		Table copy = t1.clone();
        assert(copy.getName().equals("t1"));
		
		assert(copy.getTableColumn().getColumnLst()[0].equals("studentId<i>"));
		assert(copy.getTableColumn().getColumnLst()[1].equals("name"));
		assert(copy.getTableColumn().getColumnLst()[2].equals("surname"));
		Row f3 = copy.getRow().get(0);
		assert(f3.getRowData()[0].equals("2"));
		assert(f3.getRowData()[1].equals("Sam"));
		assert(f3.getRowData()[2].equals("Stern"));
		
	}

}

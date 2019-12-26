/*
Team: Team4
Date: 7 Dec 2019
PA 7 -- DatabaseTest Class
Bugs: None
*/
package cs12b.mySQL_PA;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class DatabaseTest {

	@Test
	void test() {
		Database db=new Database();
		String[] args=new String[3];
		args="name age id".split(" ");
		db.create("test", args);
		//System.out.println(Arrays.deepToString(db.getDatabase().get(0).getTableColumn().getColumnLst()));
		assertEquals(db.getDatabase().size(),1);
		//CREATE [TAS] (studentId<i>, name, surname)
		db.create("TAS", "studentId<i> name surname".split(" "));
		//		"INSERT INTO [TAS] (1, Ben, Segal)", 
		db.insertInto("TAS","1 Ben Segal".split(" "));
//		"INSERT INTO [TAS] (2, Sam, Stern)",
		db.insertInto("TAS","2 Sam Stern".split(" "));
//		"INSERT INTO [TAS] (3, Hanyu, Song)",
		db.insertInto("TAS","3 Hanyu Song".split(" "));
//		"INSERT INTO [TAS] (4, Hangyu, Du)",
		db.insertInto("TAS","4 Hangyu Du".split(" "));
//		"CREATE [CLASSES] (class, professor, TA_Id<i>)",
		db.create("CLASSES", "class professor TA_Id<i>".split(" "));
//		"INSERT INTO [CLASSES] (12B, DiLillo, 1)",
		db.insertInto("CLASSES","12B DiLillo 1".split(" "));
//		"INSERT INTO [CLASSES] (21A, Liu, 4)",
		db.insertInto("CLASSES","21A Liu 4".split(" "));
//		"INSERT INTO [CLASSES] (131A, Papaemmanouil, 2)",
		db.insertInto("CLASSES","131A Papaemmanouil 2".split(" "));
//		"INSERT INTO [CLASSES] (36A, Patterson, 3)",
		db.insertInto("CLASSES","36A Patterson 3".split(" "));
		//"SELECT (name, surname, class, professor) FROM [TAS] JOIN [CLASSES] ON [TAS].studentId<i> = [CLASSES].TA_Id<i> ORDER BY (name) ASC",
		Table t=db.joinOn("TAS", "CLASSES", "studentId<i>", "TA_Id<i>");
		//System.out.println(Arrays.toString(t.getTableColumn().getColumnLst()));
		Table t1=db.selectFrom("name surname class professor".split(" "), t);
		Table t2=db.orderBy("ASC", t1, "name");
		
		for(Row r:t2.getRow()) {
			System.out.println(Arrays.toString(r.getRowData()));
		}
		
		Database db2 = new Database();
		String[] args2= {"name", "age", "id"};
		TableColumns column = new TableColumns(args2);
		Table t3 = new Table("t",column);
		db2.addTable(t3);
		assertEquals(db2.getDatabase().size(),1);
		db2.undo();
		assertEquals(db2.getDatabase().size(),0);
		db2.create("test2", args);
		assertEquals(db2.getDatabase().size(),1);
		db2.undo();
		assertEquals(db2.getDatabase().size(),0);
		db2.addTable(t);
		assertEquals(db2.getDatabase().size(),1);
		db2.removeTable(t);
		assertEquals(db2.getDatabase().size(),0);
		
		Database db3 = new Database();
		db3.addTable(t);
		db3.addTable(t3);
		db3.addTable(t2);
		db3.addTable(t1);
		db3.undo();
		db3.undo();
		db3.undo();
		assertEquals(db3.getDatabase().size(),1);
		db3.undo();
		assertEquals(db3.getDatabase().size(),1);
		assert(db3.getDatabase().get(0).getName().equals("temp"));
		
		Database copy = db3.clone();
		assertEquals(copy.getDatabase().size(),1);
		
		String[] args3= {"1", "Ben", "Segal", "cosi12b", "Antonella", "1"};
		db3.addRowToTable("temp", args3);
		assertEquals(db3.getDatabase().get(0).getRow().size(),5);
	}

}

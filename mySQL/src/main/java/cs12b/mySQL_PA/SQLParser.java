/*
Team: Team4
Date: 7 Dec 2019
PA 7 -- SQLParser Class
Bugs: None
*/
package cs12b.mySQL_PA;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SQLParser {
	/**
	 * fields
	 */
	private Database db;
	public SQLParser(Database db) {
		this.db=db;
	}
	/**
	 * The method parses the queries
	 * @param sql
	 */
	public void parse(String sql) {
		//System.out.println(sql);
		String[] s=splitStringIntoChars(sql);
		//System.out.println(Arrays.toString(s));
		//System.out.println(s[0].toLowerCase());
		if(s[0].toLowerCase().equals("c")) {
			String name=extractStringFromBrackets(sql);
			String[] args=extractStringsFromParens(sql);
			db.create(name, args);
		}
		else if(s[0].toLowerCase().equals("i")) {
			String name=extractStringFromBrackets(sql);
			String[] args=extractStringsFromParens(sql);
			db.addRowToTable(name, args);
		}
		else if(s[0].toLowerCase().equals("s")) {
			String name1=extractStringFromBrackets(sql);
			String name2=extractStringFromBrackets(sql.substring(sql.toUpperCase().indexOf("JOIN")));
			//System.out.println(sql.substring(sql.toUpperCase().indexOf("ON")).split(" ")[1].split("\\.")[1]);
			String col1=sql.substring(sql.toUpperCase().indexOf("ON")).split(" ")[1].split("\\.")[1];
			String col2=sql.substring(sql.toUpperCase().indexOf("ON")).split(" ")[3].split("\\.")[1];
			Table t=db.joinOn(name1, name2, col1, col2);
			String[] args=extractStringsFromParens(sql);
			Table list=db.selectFrom(args, t);
			String byCol=extractOrderByAlias(sql);
			String[] order=sql.split(" ");
			Table ans=db.orderBy(order[order.length-1], list, byCol);
			//System.out.println(ans.data.size());
			for(Row r:ans.getRow()) {
				
				for(String s1:r.getRowData()) {
				System.out.print(s1+" ");
				}
				System.out.println();
			}
		}
	}
	
	/**
	 * This method will split a given String into an array of characters 
	 * @param str the input String
	 * @return a String array of each character in str
	 */
	public String[] splitStringIntoChars(String str) {
		return str.split("");
	}
	
	/**
	 * This method will extract a given String from brackets [ ] if it's found inside them 
	 * @param str the String to extract
	 * @return str without its brackets
	 * @throws IllegalArgumentException if str does not have brackets
	 */
	public String extractStringFromBrackets(String str) {
		try {
			return str.substring(str.indexOf("[") + 1, str.indexOf("]"));
		} catch (Exception IndexOutOfBoundsException){
			throw new IllegalArgumentException("expected [...] got " + str);
		}
	}
	
	/**
	 * This method will extract a given String from parens ( ) if it's found inside them 
	 * @param str the String to extract
	 * @return a String array of string arguments extracted from parens
	 * @throws IllegalArgumentException if str does not have parens
	 */
	public static String[] extractStringsFromParens(String str) {
		try {
			return str.substring(str.indexOf("(") + 1, str.indexOf(")")).split(", ");
		} catch (Exception IndexOutOfBoundsException){
			throw new IllegalArgumentException("expected (...) got " + str);
		}
	}
	
	/**
	 * Retrieves the String representation of the "order by" alias from the given String 
	 * @param str the given String 
	 * @return a String representing the order by alias
	 */
	public String extractOrderByAlias(String str) {
		try {
			return extractStringsFromParens(str.substring(str.toUpperCase().indexOf("ORDER BY")))[0];
		} catch (Exception IndexOutOfBoundsException){
			return null;
		}
	}
}

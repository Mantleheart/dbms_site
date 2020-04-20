import java.sql.*;


//THIS AINT DONE YET








































/*
jdbc_insert_student.java    // java program that is called by php that just does the insert; calls jdbc_db.java to connect and do the actual insert
jdbc_db.java // class (no main program) that has useful methods

CREATE TABLE Student(StudentId int,studentName varchar(64), Major varchar(8));
*/

public class jdbc_insert_student
{
   // The main program that inserts a student
   public static void main(String[] args) throws SQLException
   {
      String Username = "cdd008";              // Change to your own username
      String mysqlPassword = "John11";    // Change to your own mysql Password

      // Connect to the database
      jdbc_db myDB = new jdbc_db();
      myDB.connect(Username, mysqlPassword);
      myDB.initDatabase();

      // For debugging purposes:  Show the database before the insert
      StringBuilder builder = new StringBuilder();
      String query1 = "SELECT * from Student";
      builder.append("<br> Table Student before:" + myDB.query(query1) + "<br>");

      // Parse input string to get student Name, ID, MAJOR

      String name = "NAME";
      String id = "ID";
      String major = "MAJOR";

      // Read command line arguments
      // args[0] is the first parameter
      id = args[0];
      name = args[1];
      major = args[2];

      // Insert the new student
      String input =  id + ",'" + name + "','" + major + "'";
      myDB.insert("Student", input);    // insert new student

      // For debugging purposes:  Show the database after the insert
      builder.append("<br><br><br> Table Student after:" + myDB.query(query1));
      System.out.println(builder.toString());

      myDB.disConnect();
   }
}

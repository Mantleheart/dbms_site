import java.sql.*;

// This class has some useful methods that can be used by other programs
public class jdbc_db 
{

   // The instance variables for the class
   private Connection connection;
   private Statement statement;

   // The constructor for the class
   public jdbc_db() 
   {
     connection = null;
     statement = null;
   }
   
   // Connect to the database
   public void connect(String Username, String mysqlPassword) throws SQLException 
   {
      try 
      {
         connection = DriverManager.getConnection("jdbc:mysql://localhost/" + Username + "?" +
                      "user=" + Username + "&password=" + mysqlPassword);
       }
      catch (Exception e) 
      {
         throw e;
      }
}
   
   // Disconnect from the database
   public void disConnect() throws SQLException 
   {
      connection.close();
      statement.close();
   }
   
   // Execute an SQL query passed in as a String parameter
   // and print the resulting relation
   public String query(String q) 
   {
   StringBuilder builder = new StringBuilder();
   
      try 
      {
         ResultSet resultSet = statement.executeQuery(q);
         builder.append("<br>---------------------------------<br>");
         builder.append("Query: <br>" + q + "<br><br>Result: ");
         builder.append(print(resultSet));
      }
      catch (SQLException e) 
      {
         e.printStackTrace();
      }
      return builder.toString();
   }
   
   // Print the results of a query with attribute names on the first line
   // Followed by the tuples, one per line
   public String print(ResultSet resultSet) throws SQLException 
   {
   StringBuilder builder = new StringBuilder();
   ResultSetMetaData metaData = resultSet.getMetaData();
   int numColumn = metaData.getColumnCount();
   
      builder.append(printHeader(metaData, numColumn));
      builder.append(printRecords(resultSet, numColumn));
   
      return builder.toString();
   }
   
   // Print the attribute names
   public String printHeader(ResultSetMetaData metaData, int numColumn) throws SQLException 
   {
   StringBuilder builder = new StringBuilder();
   
      for (int i = 1; i <= numColumn; i++) 
      {
         if (i > 1)
             builder.append(",  ");
         builder.append(metaData.getColumnName(i));
      }
      builder.append("<br>");
      return builder.toString();
   }
   
   // Print the attribute values for all tuples in the result
   public String printRecords(ResultSet resultSet, int numColumn) throws SQLException 
   {
   StringBuilder builder = new StringBuilder();
   String columnValue;
   
      while (resultSet.next()) 
      {
         for (int i = 1; i <= numColumn; i++) 
         {
            if (i > 1)
              builder.append(",  ");
            columnValue = resultSet.getString(i);
            builder.append(columnValue);
         }
         builder.append("<br>");
      }
      return builder.toString();
   }
   
   // Insert into any table, any values from data passed in as String parameters
   public void insert(String table, String values) 
   {
   String query = "INSERT into " + table + " values (" + values + ")" ;
   
      try 
      {
         statement.executeUpdate(query);
      } 
      catch (SQLException e) 
      {
         e.printStackTrace();
      }
   }
   
   // Remove all records and fill them with values for testing
   // Assumes that the tables are already created
   public void initDatabase() throws SQLException 
   {
      statement = connection.createStatement();
      statement.executeUpdate("DELETE from FoodOrder");
      statement.executeUpdate("DELETE from MenuItem");
      statement.executeUpdate("DELETE from Dish");
      statement.executeUpdate("DELETE from Restaurant");

      statement.executeUpdate("DELETE from Student");
      statement.executeUpdate("DELETE from Course");
      statement.executeUpdate("DELETE from Enrollment");

      
      insert ("Student", "101,      'Mike Oldfield',  'MUSC'   ");
      insert ("Student", "506,      'Johnny Joestar', 'LIBR'   ");
      insert ("Student", "8675309,  'Tommy Tutone',   'ROCK'   ");
      insert ("Student", "0,        'Frank Frank',    'ASIA'   ");
      insert ("Student", "1,        'Diego Brando',   'DIO'    ");
      insert ("Student", "2,        'Fleetwood Mac',  'TUSK'   ");

      insert ("Course", "20005,  20, 'Horseback Riding',                            150.23   ");
      insert ("Course", "20006,  25, 'History of The United States of Valentine',   1985.85  ");
      insert ("Course", "12345,  50, 'Music Theory 101',                            99.3     ");
      insert ("Course", "1,      0,  'Horseback Riding',                            150.23   ");
      insert ("Course", "2,      5,  'Vampires For Dummies',                        75.75    ");
      insert ("Course", "3,      10, 'How to Create Stone Masks',                   1880     ");
      insert ("Course", "4,      15, 'Fear and Loathing',                           13.0     ");
      insert ("Course", "5,      20, 'Golden Spin',                                 16.3     ");

      insert ("Enrollment", "506,   20005,   20    ");
      insert ("Enrollment", "0,     12345,   50    ");
      insert ("Enrollment", "1,     5,       20    ");
      insert ("Enrollment", "2,     3,       5     ");
/*
      insert("Restaurant", "0,   'Tasty Thai',     'Asian', 'Dallas'                ");
      insert("Dish",       "13,  'Spring Roll',    'ap'                             ");
      insert("MenuItem",   "0,   0,                13,      8.00                    ");
      insert("FoodOrder",  "0,   2, STR_To_DATE('01,03,2017', '%d,%m,%Y'),  '10:30' ");
*/
   }
}

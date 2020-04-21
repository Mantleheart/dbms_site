<html>
<body>
<h3>Enter information about a course to add to the database:</h3>

<a href="http://csce.uark.edu/~cdd008/PR5/dbms.html">Go Back</a>
<form action="jdbc_insert_course.php" method="post">
    Dept Code:      <input type="text"  name="DeptCode">    <br>
    Course Number:  <input type="int"  name="CourseNum">   <br>
    Title:          <input type="text"  name="Title">       <br>
    Credit Hours:   <input type="float" name="CreditHours"> <br>
    <input name="submit" type="submit" >
</form>
<br><br>

</body>
</html>
<!--CREATE TABLE Course(DeptCode varchar(48), CourseNum varchar(10), Title varchar(64),CreditHours decimal(3,2));-->
<?php
if (isset($_POST['submit'])) 
{
    // replace ' ' with '\ ' in the strings so they are treated as single command line args
    $deptCode = escapeshellarg($_POST[DeptCode]);
    $courseNum = escapeshellarg($_POST[CourseNum]);
    $title = escapeshellarg($_POST[Title]);
    $creditHours = escapeshellarg($_POST[CreditHours]);
                                                                                    //What is the order?
    $command = 'java -cp .:mysql-connector-java-5.1.40-bin.jar jdbc_insert_course ' . $deptcode . ' ' . $coursenum . ' ' . $title . ' ' . $credithours;
                                                                                //' . $id . ' ' . $name . ' ' . $major;

    // remove dangerous characters from command to protect web server
    $command = escapeshellcmd($command);
    echo "<p>command: $command <p>";
 
    // run jdbc_insert_course.exe
    system($command);           
}
?>
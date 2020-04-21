<html>
<body>
<h3>Enter information about a course to add to the database:</h3>

<a href="http://csce.uark.edu/~cdd008/PR5/dbms.html">Go Back</a>
<form action="jdbc_view_course.php" method="post">
    <!--<button type="submit" value="Submit">View Course</button>-->
    <input name="submit" type="submit" >
</form>
<br><br>

</body>
</html>
]
<!--CREATE TABLE Course(DeptCode varchar(48), CourseNum varchar(10), Title varchar(64),CreditHours decimal(3,2));-->
<?php
if (isset($_POST['submit'])) 
{
    // replace ' ' with '\ ' in the strings so they are treated as single command line args
                                                                                    //What is the order?
    $command = 'java -cp .:mysql-connector-java-5.1.40-bin.jar jdbc_view_course';

    // remove dangerous characters from command to protect web server
    $command = escapeshellcmd($command);
    echo "<p>command: $command <p>";
 
    // run jdbc_insert_course.exe
    system($command);           
}
?>
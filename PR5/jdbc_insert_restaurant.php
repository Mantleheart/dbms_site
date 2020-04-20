<html>
<body>
<h3>Enter information about a restaurant to add to the database:</h3>

<form action="jdbc_insert_restaurant.php" method="post">
    Name: <input type="text" name="Name"><br>
    Id: <input type="text" name="Id"><br>
    Major: <input type="text" name="Major"><br>
    <input name="submit" type="submit" >
</form>
<br><br>

</body>
</html>

<?php
if (isset($_POST['submit'])) 
{
    // replace ' ' with '\ ' in the strings so they are treated as single command line args
    $name = escapeshellarg($_POST[Name]);
    $id = escapeshellarg($_POST[Id]);
    $major = escapeshellarg($_POST[Major]);

    $command = 'java -cp .:mysql-connector-java-5.1.40-bin.jar jdbc_insert_restaurant ' . $id . ' ' . $name . ' ' . $major;

    // remove dangerous characters from command to protect web server
    $command = escapeshellcmd($command);
    echo "<p>command: $command <p>";
 
    // run jdbc_insert_restaurant.exe
    system($command);           
}
?>
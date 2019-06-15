<?php
error_reporting(0);
include_once("dbconnect.php");
$email = $_POST['email'];
$password = sha1($_POST['password']);

$sql = "SELECT * FROM user WHERE email = '$email' AND password = '$password'";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    while ($row = $result ->fetch_assoc()){
        echo $data = $row["name"].",".$row["phone"];
    }
}else{
    echo "failed";
}

?>
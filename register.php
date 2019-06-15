<?php
error_reporting(0);
include_once("dbconnect.php");
$email = $_POST['email'];
$password = sha1($_POST['password']);
$phone = $_POST['phone'];
$name = $_POST['name'];
$location = $_POST['location'];
if (strlen($email) > 0){
    $sqlinsert = "INSERT INTO user(email,password,phone,name,location) VALUES ('$email','$password','$phone','$name','$location')";
    if ($conn->query($sqlinsert) === TRUE){
       echo "success";
    }else {
        echo "failed";
    }
}else{
    echo "nodata";
}

?>
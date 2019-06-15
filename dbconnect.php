<?php
$servername = "kitkat";
$username   = "lastyear_langkawi";
$password   = "OTx4k7q~=zSJ";
$dbname     = "lastyear_langkawi";

$conn = new mysqli($servername, $username, $password, $dbname);
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
?>
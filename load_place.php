<?php
error_reporting(0);
include_once("dbconnect.php");
$location = $_POST['location'];
if (strcasecmp($location, "All") == 0){
    $sql = "SELECT * FROM place"; 
}else{
    $sql = "SELECT * FROM place WHERE loation = '$location'";
}
$result = $conn->query($sql);
if ($result->num_rows > 0) {
    $response["place"] = array();
    while ($row = $result ->fetch_assoc()){
        $restlist = array();
        $restlist[placeid] = $row["placeid"];
        $restlist[name] = $row["name"];
        $restlist[phone] = $row["phone"];
        $restlist[address] = $row["address"];
        $restlist[location] = $row["location"];
        array_push($response["place"], $placelist);
    }
    echo json_encode($response);
}else{
    echo "nodata";
}
?>v
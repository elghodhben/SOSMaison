<?php
header('Content-type: text/html; charset=utf-8');
ini_set('display_errors', '1');
ini_set('display_startup_errors', '1');
error_reporting(E_ALL);
require "DataBase.php";
$db = new DataBase();
$con=mysqli_connect("localhost","root","","sosmaison");

if (isset($_GET["email"]))
{
    $email=$_GET["email"];
    $req="select * from notification where email='$email'";
}

$arr=array();
$result = $con->query($req);
while($row = $result->fetch_assoc()) {
    $arr[]=array("email"=>$row["email"],
    "sujet_noti"=>$row["sujet_noti"],
    "description"=>$row["description"],
    "date_noti"=>$row["date_noti"]
    );

 }

echo json_encode($arr,JSON_PRETTY_PRINT );

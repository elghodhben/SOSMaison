<?php
header('Content-type: text/html; charset=utf-8');
ini_set('display_errors', '1');
ini_set('display_startup_errors', '1');
error_reporting(E_ALL);
require "DataBase.php";
$db = new DataBase();
$con=mysqli_connect("localhost","root","","sosmaison");
$req="select * from artisan";
if (isset($_GET["type"]))
{
    $type=$_GET["type"];
    $req="select * from artisan where nature_travail='$type'";
}

$arr=array();
$result = $con->query($req);
while($row = $result->fetch_assoc()) {
    $email=$row["email"];
    $req1="select * from  particulier where emailpart = '$email'";
    $result1=$con->query($req1);
    $row1=$result1->fetch_assoc();
    $arr[]=array("nature_travail"=>$row["nature_travail"],
    "niveau_competence"=>$row["niveau_competence"],
    "annees_competence"=>$row["annees_competence"],
    "nature_diplome"=>$row["nature_diplome"],
    "type_cours"=>$row["type_cours"],
    "annee_obtention"=>$row["annee_obtention"],
    "detail_service"=>$row["detail_service"],
    "prix_jours"=>$row["prix_jours"],
    "description_service"=>$row["description_service"],
    "latitude"=>$row["latitude"],
    "longitude"=>$row["longitude"],
    "tel"=>$row1["telpart"],
    "nom"=>$row1["nompart"]." ".$row1["prenompart"]

    );

 }

echo json_encode($arr,JSON_PRETTY_PRINT );
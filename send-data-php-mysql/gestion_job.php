<?php
header('Content-type: text/html; charset=utf-8');
ini_set('display_errors', '1');
ini_set('display_startup_errors', '1');
error_reporting(E_ALL);
require "DataBase.php";
$db = new DataBase();
$con=mysqli_connect("localhost","root","","sosmaison");
$stravail=$_POST["stravail"];
$scompetences=$_POST["scompetences"];
$sannec=$_POST["sannec"];
$sdiplom=$_POST["sdiplom"];
$stypecours=$_POST["stypecours"];
$sanneobtention=$_POST["sanneobtention"];
$sdesc=$_POST["sdesc"];
$sprix=$_POST["sprix"];
$sdesc_service=$_POST["sdesc_service"];
$latitude=$_POST["lat"];
$longitude=$_POST["long"];
$email=$_POST["email"];
$req="insert into artisan ( nature_travail,niveau_competence,annees_competence,nature_diplome,type_cours,annee_obtention,detail_service,prix_jours,
description_service,longitude,latitude,email) values ('$stravail','$scompetences',$sannec,'$sdiplom','$stypecours',$sanneobtention,'$sdesc',$sprix,'$sdesc_service',$longitude,$latitude,'$email')";
echo $req;
if ($con->query($req))
    echo "artisan inseree avec success ";
else
    echo $con->error;

?>


<?php
require "DataBase.php";
$db = new DataBase();
$con=mysqli_connect("localhost","root","","sosmaison");
if (isset($_POST['email']) && isset($_POST['password'])) {

    $email= $_POST['email'];
    $mdp = $_POST['password'];
    if ($db->dbConnect()) {

        $result=mysqli_query($con,"SELECT * FROM `particulier` WHERE `emailpart`='$email' && `mdppart`='$mdp'");
        
        $count=mysqli_num_rows($result);
        if($count==1)
        {
            echo "Connexion réussie";
        }
        else
        {
            echo "validé votre mot de passe validé ou bien votre adresse email";
        } 

    } else echo "Erreur: connexion à la base de données";
} else echo "Tous les champs sont requis";



?>

<?php

require "DataBase.php";
$db = new DataBase();
$con=mysqli_connect("localhost","root","","sosmaison");
// importé le fichier en php 
require "message/PHPMailer.php";
require "message/SMTP.php";
require "message/Exception.php";

//
use PHPMailer\PHPMailer\PHPMailer;
use PHPMailer\PHPMailer\SMTP;
use PHPMailer\PHPMailer\Exception;

//
$mail = new PHPMailer;
//
$mail->isSMTP ();

//
$mail->Host = 'smtp.gmail.com';
//
$mail->SMTPAuth = 'true';
//
$mail->SMTPDebug = 0;
//
$mail->SMTPSecure = 'tls';
//
$mail->CharSet = 'UTF-8';
//

$mail->Port = '587';
//
$mail->Username = 'sossaisonkontakt@gmail.com';
//
$mail->Password = 'IYEDBAC2017';
//
$mail->Subject = 'Completez Votre Inscription';
//
$mail->setFrom('sossaisonkontakt@gmail.com');
//

//






$phone = $_POST['phone'];
$res=mysqli_query($con,"SELECT telpart FROM `particulier` WHERE `telpart`='$phone'");



if (isset($_POST['nom']) && isset($_POST['prenom'])  && isset($_POST['email']) && isset($_POST['phone']) && isset($_POST['password'])) {
    if ($db->dbConnect()) {

        
        
      
         

              
            if(mysqli_num_rows($res) == 0)
            {
                
                if ($db->signUp("particulier",$_POST['nom'] , $_POST['prenom'] , $_POST['email'], $_POST['phone'], $_POST['password']))
             {
         
            echo "succes de l'inscription";
            $mail->addAddress($_POST['email']);
            $mail->Body = 'Merci pour votre Inscription '.$_POST['prenom']."\t".$_POST['nom']."\n".
            'Votre adresse email est: '.$_POST['email']."\n".
            'Votre Mot de passe est: '.$_POST['password'] ;

            if ($mail->Send())
                {
                echo "... Veuillez vérifier votre adresse email ..! "; 
                }
              
                
              }else echo "Inscription échouée...Il y a un compte avec le même adresse email";

            } else echo "\n Veuillez vous choisir un autre numéro de téléphone.Ce numéro Réservé à un autre client..!";
         
         
     

        
        
    } else echo "Erreur: connexion à la base de données";
} else echo "Tous les champs sont requis";
?>
          
         
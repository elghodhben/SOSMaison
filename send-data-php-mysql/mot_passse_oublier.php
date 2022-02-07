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
$mail->Subject = 'Verifier Votre Mot de passe ';
//
$mail->setFrom('sossaisonkontakt@gmail.com');
//

//










if (isset($_POST['email'])) {
    
    $email= $_POST['email'];

    if ($db->dbConnect()) {

        $result=mysqli_query($con,"SELECT * FROM `particulier` WHERE `emailpart`='$email'");
        $row = mysqli_fetch_assoc($result);
        $count=mysqli_num_rows($result);
        
        if($count==1)
        {
           

            
            $mail->addAddress($_POST['email']);
            $mail->Body ='Bonjour Mr ou Mme '."\t".$row['prenompart']."".$row['nompart']."\n". 
            ' Votre Mot de passe est: '.$row['mdppart'] ;

            if ($mail->Send())
        {
                    echo "Veuillez vérifier votre adresse email ..!";
                    
            $mail->addAddress($_POST['email']);
           
        }
        
    
    } else echo "Votre adresse email est incorrecte..!";

    } else echo "Erreur: connexion à la base de données";
} else echo "Le champs se requis";
      
?> 
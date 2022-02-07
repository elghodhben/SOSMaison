<?php
require "DataBase.php";
$db = new DataBase();
$con=mysqli_connect("localhost","root","","sosmaison");
if (isset($_POST['email']) && isset($_POST['phone']) && isset($_POST['phon'])) {

    $email= $_POST['email'];
    $phone=$_POST["phone"];
    $phon=$_POST["phon"];

    if ($db->dbConnect()) {

        $result=mysqli_query($con,"SELECT telpart FROM `particulier` WHERE `emailpart`='$email'");
        $res=mysqli_query($con,"SELECT telpart FROM `particulier` WHERE `telpart`='$phon'");
       
       
            $row = mysqli_fetch_assoc($result);
            if($phone==$row['telpart'])
            {

                if( mysqli_num_rows($res) > 0)
                {

                    echo "Veuillez vous choisir un autre numéro de téléphone.Ce numéro Réservé à un autre client..!";
                   
                }else 
                {
                    $sql = " UPDATE particulier SET telpart='$phon' WHERE emailpart='$email'";
            
                    if ($con->query($sql) === TRUE) 
                    {
                      echo "Enregistrement mis à jour avec succes "."\t".$phon;
                
                    }
                }
             
            
            }else echo "numéro de téléphone n'est existe pas...veuillez vérifier votre numéro..!";
       
    

    } else echo "Erreur: connexion à la base de données";
} else echo "Tous les champs sont requis";






?>
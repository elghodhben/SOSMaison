<?php


$con=mysqli_connect("localhost","root","","sosmaison");
if (isset($_POST['email']) && isset($_POST['password']) && isset($_POST['pass']) ) {

  
    $password= $_POST['password'];
    $email= $_POST['email'];
    $pass= $_POST['pass'];

    $result=mysqli_query($con,"SELECT mdppart FROM `particulier` WHERE `emailpart`='$email'");
        

    
    
        while ($row = $result->fetch_assoc()) {

        if ($password == $row['mdppart'])
        {
     

                    $sql = " UPDATE particulier SET mdppart='$pass' WHERE emailpart='$email'";
            
                    if ($con->query($sql) === TRUE) 
                    {
                      exit("Enregistrement mis à jour avec succes ");
                
                    
                }else  exit("erreur");
             
        } else exit("Ce compte contient déjà votre mot de passe, veuillez le modifier.");
         
        }
} else echo "Tous les champs sont requis";



?>   
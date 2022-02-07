<?php




$con=mysqli_connect("localhost","root","","sosmaison");
if (isset($_POST['email']) && isset($_POST['emailn']) ) {

    $email= $_POST['email'];
    $emailn= $_POST['emailn'];

    $result=mysqli_query($con,"SELECT emailpart FROM `particulier` WHERE `emailpart`='$email'");
        

    
        $row = mysqli_fetch_assoc($result);

        if($email == $row['emailpart'])
        {

                    $sql = " UPDATE particulier SET emailpart='$emailn' WHERE emailpart='$email'";
            
                    if ($con->query($sql) === TRUE) 
                    {
                      echo "Enregistrement mis à jour avec succes "."\t".$emailn;
                
                    
                }else echo "Inscription échouée...Il y a un compte avec le même adresse email";
             
        } else echo "Ce compte contient déjà votre adresse e-mail, veuillez le modifier.";
         
    
} else echo "Tous les champs sont requis";



?>   
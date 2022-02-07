<?php

require "DataBase.php";
$db = new DataBase();








if (isset($_POST['email']) && isset($_POST['sujet'])  && isset($_POST['description'])) {

    
    if ($db->dbConnect()) {
        if ($db->descriptionUp("reclamation", $_POST['email'], $_POST['sujet'] , $_POST['description'])) {
            echo "Reclamation avec succès";
         
                
        } else echo "Reclamation a échouée";
    } else echo "Erreur: connexion à la base de données";
} else echo "Tous les champs sont requis";
?>
  
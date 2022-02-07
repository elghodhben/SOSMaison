<?php
require "DataBaseConfig.php";

class DataBase
{
    public $connect;
    public $data;
    private $sql;
    protected $servername;
    protected $username;
    protected $password;
    protected $databasename;

    public function __construct()
    {
        $this->connect = null;
        $this->data = null;
        $this->sql = null;
        $dbc = new DataBaseConfig();
        $this->servername = $dbc->servername;
        $this->username = $dbc->username;
        $this->password = $dbc->password;
        $this->databasename = $dbc->databasename;
    }

    function dbConnect()
    {
        $this->connect = mysqli_connect($this->servername, $this->username, $this->password, $this->databasename);
        return $this->connect;
    }

    function prepareData($data)
    {
        return mysqli_real_escape_string($this->connect, stripslashes(htmlspecialchars($data)));
    }


   
    function signUp($table,$nom,$prenom,$email, $phone, $password)
    {
        
        $nom = $this->prepareData($nom);
        $prenom = $this->prepareData($prenom);
        $email = $this->prepareData($email);
        $phone = $this->prepareData($phone);
        $password = $this->prepareData($password);
        $this->sql =
            "INSERT INTO " . $table . " ( nompart, prenompart, emailpart, telpart, mdppart) VALUES ('" . $nom . "','" . $prenom . "','" . $email . "', '" . $phone . "', '" . $password . "')";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }


    function descriptionUp($table,$email,$sujet,$description)
    {
        $email = $this->prepareData($email);
        $sujet = $this->prepareData($sujet);
        $description = $this->prepareData($description);

        $this->sql = 
        "INSERT INTO " . $table . " ( email_reclamation, sujet_reclamation, description_textuelle) VALUES ('" . $email . "','" . $sujet . "','" . $description . "')";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }
    


}

?>

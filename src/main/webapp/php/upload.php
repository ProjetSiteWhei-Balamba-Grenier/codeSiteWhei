<html>

    <head>
        <meta charset="UTF-8">
    </head>

    <body>

        <?php

            if(isset($_FILES['image']))
            {
                $dossier = 'img/';
                $fichier = basename($_FILES['image']['name']);
                if(move_uploaded_file($_FILES['image']['tmp_name'], $dossier . $fichier)) //Si la fonction renvoie TRUE, c'est que ça a fonctionné...
                {
                    echo 'Upload effectué avec succès !';
                }
                else //Sinon (la fonction renvoie FALSE).
                {
                    echo 'Echec de l\'upload !';
                }
            }

        ?>

    </body>

</html>
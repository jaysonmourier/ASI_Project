<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>API REST - Projet Universitaire</title>
    <!-- Intégration de Bootstrap -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <!-- Style personnalisé -->
    <style>
        body {
            background-color: #f8f9fa;
        }
        .jumbotron {
            background-color: #17a2b8;
            color: white;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            margin-bottom: 30px;
        }
        .container {
            max-width: 960px;
            margin: 30px auto;
            padding: 15px;
        }
    </style>
</head>
<body>
<div class="jumbotron text-center">
    <h1>API REST - Projet Universitaire</h1>

</div>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <p>
                Bienvenue sur la page de présentation de notre API REST développée dans le cadre d'un projet universitaire. Cette API a été conçue pour faciliter l'accès aux données et offrir un moyen efficace d'interagir avec notre application.
            </p>
            <p>
                Notre API utilise le framework Jersey et est basée sur le standard Maven pour la gestion de projet. Elle est conçue pour être flexible, performante et facile à utiliser, tout en respectant les principes de l'architecture RESTful.
            </p>
            <p>
                Vous pouvez explorer les différentes ressources disponibles en utilisant les points d'accès suivants (les liens peuvent être adaptés en fonction de la structure de votre API) :
            </p>
            <ul>
                <li><a href="/ASIProjet_war/api/hello">hello world</a></li>
                <!-- Ajoutez d'autres liens vers les ressources de votre API ici -->
            </ul>
            <p>
                N'hésitez pas à consulter notre documentation complète pour en savoir plus sur l'utilisation de notre API et les différentes fonctionnalités qu'elle propose.
            </p>
            <a href="#" class="btn btn-primary">Consulter la documentation</a>
        </div>
    </div>
</div>
</body>
</html>
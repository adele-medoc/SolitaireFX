Solitaire – Java / JavaFX 🇬🇧

Why Solitaire games are so ugly ? Personally, I decided to create the cutest solitaire game ever! 

This repository contains my Solitaire project, built in Java with JavaFX, which I developed in my spare time to deepen my knowledge of object-oriented programming and MVC architecture. The game is complete and functional, but the code is intended to evolve over time; some parts are not yet fully optimized.

Building the Application
    
    1 - Clone the repository
    
    2 - Open the project in your Java IDE (IntelliJ, Eclipse, VS Code…)
    
    3 - Download JavaFX 21: available at https://gluonhq.com/products/javafx/
    
    4 - Add JavaFX to the project 
            The IDE may not be able to locate the JavaFX lib folder
            so you will need to add the following to the VM options: 
            --module-path /path/to/javafx/lib --add-modules javafx.controls,javafx.fxml
            
    Technologies
        java version : 21
        javaFX version : 21.0.10 disponible sur https://gluonhq.com/products/javafx/



Running the Application

    1 - Open the main class: Main.java

    2 - Run the application from your IDE (RUN) -> A JavaFX window will open with the game menu.

Playing the Game

    The game follows the classic rules of Solitaire
    Objective: Build the 4 foundation piles (♥ ♦ ♣ ♠) starting with the Ace and ending with the King.

    Main Rules
    - Cards on the table must be stacked in descending order
    - Suits must alternate (red / black)
    - Only Kings can be placed on an empty column
    - Cards can be moved via drag & drop
    - The draw pile allows you to reveal new cards

    When all foundations are completed or if all cards on the board are face up, the game is won! 
------------------------------------------------------------------------------------------------------------------------
Solitaire – Java / JavaFX 🇫🇷

Pourquoi les solitaires sont toujours couleur vert bouteille et triste ? Perso j'ai choisi de créer le plus mignon des solitaires ! 

Ce dépôt contient mon projet de Solitaire réalisé en Java avec JavaFX, dans le cadre de mon temps personnel afin d'approfondir mes connaisance en programmation orientée objet et en architecture MVC. Le jeu est complet et fonctionnelle mais le code est voué à évolue au fur et à mesure, certaines parties sont encore pas totalement optimisées.

Installer l'application

    1 - Cloner le dépot
    
    2 - Ouvrir le projet dans votre IDE Java (IntelliJ, Eclipse, VS Code…)
    
    3 - Télécharger JavaFX 21 : disponible sur https://gluonhq.com/products/javafx/
    
    4 - Ajouter JavaFX au projet 
            il est possible que l'IDE ne trouve pas comment accéder au dossier lib de JavaFX
            donc il faudra rajouter aux VM options : 
            --module-path /chemin/vers/javafx/lib --add-modules javafx.controls,javafx.fxml
            
    Technologies
    Version java du projet : 21
    Version de javaFX : 21.0.10 disponible sur https://gluonhq.com/products/javafx/

Lancer l'application

    1 - Ouvrir la classe principale : Main.java

    2 - Lancer l'application depuis votre IDE (RUN) -> Une fenêtre JavaFX s'ouvre alors avec le menu du jeu.

Jouer au jeu

    Le jeu implémente les règles classiques du Solitaire

    Objectif : Construire les 4 piles de fondation (♥ ♦ ♣ ♠) en commençant par l’As et en terminant par le Roi.

Règles principales

    - Les cartes dans le tableau doivent être empilées en ordre décroissant
    - Les couleurs doivent alterner (rouge / noir)
    - Seuls les Rois peuvent être placés sur une colonne vide
    - Les cartes peuvent être déplacées par drag & drop
    - La pioche permet de révéler de nouvelles cartes

    Lorsque toutes les fondations sont complétées ou si toutes les cartes du plateau sont face recto la partie est gagnée ! 

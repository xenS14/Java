Une fois le projet cloné, compiler l'app grâce à :

- javac -d out -cp ".:lib/mysql-connector-j-9.2.0.jar" $(find src -name "\*.java")

Puis lancer le projet grâce à :

- cd out/
- java -cp "../lib/mysql-connector-j-9.2.0.jar:." main.App

Ensuite vous aurez le choix entre plusieurs options :

- 1 Ajouter un apprenant
- 2 Afficher tous les apprenants
- 3 Modifier les absences d'un apprenant
- 4 Supprimer un apprenant
- 5 Voir les apprenants d'une promotion
- 6 Rechercher un apprenant par email
- 7 Voir les statistiques générales
- 8 Voir l'apprenant avec le plus d'absences
- 9 Quitter

Vous retrouverez un export d'une base de données avec plusieurs valeurs afin de pouvoir profiter des fonctionnalités du programme.

BDD : BDD>java.sql

package main;

import dao.ApprenantDAO;
import models.Apprenant;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ApprenantDAO dao;

        try {
            dao = new ApprenantDAO();
        } catch (SQLException e) {
            System.out.println("Erreur de connexion à la base de données !");
            e.printStackTrace();
            return;
        }

        while (true) {
            System.out.println("\n===== MENU GESTION APPRENANTS =====");
            System.out.println("1 Ajouter un apprenant");
            System.out.println("2 Afficher tous les apprenants");
            System.out.println("3 Modifier les absences d'un apprenant");
            System.out.println("4 Supprimer un apprenant");
            System.out.println("5 Quitter");
            System.out.print("Choisissez une option : ");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    ajouterApprenant(scanner, dao);
                    break;
                case 2:
                    afficherApprenants(dao);
                    break;
                case 3:
                    modifierAbsences(scanner, dao);
                    break;
                case 4:
                    supprimerApprenant(scanner, dao);
                    break;
                case 5:
                    System.out.println("Fin du programme.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Option invalide, réessayez.");
            }
        }
    }

    private static void ajouterApprenant(Scanner scanner, ApprenantDAO dao) {
        try {
            System.out.print("Entrez le nom : ");
            String nom = scanner.nextLine();
            System.out.print("Entrez le prénom : ");
            String prenom = scanner.nextLine();
            System.out.print("Entrez l'email : ");
            String email = scanner.nextLine();
            System.out.print("Entrez le téléphone : ");
            String telephone = scanner.nextLine();
            System.out.print("Entrez la promotion : ");
            String promotion = scanner.nextLine();
            System.out.print("Entrez le nombre d'absences : ");
            int absences = scanner.nextInt();
            System.out.print("Est-il délégué ? (true/false) : ");
            boolean delegue = scanner.nextBoolean();

            if (!dao.existeApprenant(email)) {
                Apprenant apprenant = new Apprenant(0, promotion, nom, prenom, email, telephone, absences, delegue);
                dao.ajouterApprenant(apprenant);
                System.out.println("Apprenant ajouté avec succès !");
            } else {
                System.out.println("Cet email est déjà utilisé !");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout !");
            e.printStackTrace();
        }
    }

    private static void afficherApprenants(ApprenantDAO dao) {
        try {
            List<Apprenant> apprenants = dao.getApprenants();
            System.out.println("\nListe des apprenants :");
            for (Apprenant a : apprenants) {
                System.out.println(a);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'affichage !");
            e.printStackTrace();
        }
    }

    private static void modifierAbsences(Scanner scanner, ApprenantDAO dao) {
        try {
            System.out.print("Entrez l'ID de l'apprenant à modifier : ");
            int id = scanner.nextInt();
            System.out.print("Entrez le nouveau nombre d'absences : ");
            int absences = scanner.nextInt();
            dao.modifierAbsences(id, absences);
            System.out.println("Nombre d'absences mis à jour !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification !");
            e.printStackTrace();
        }
    }

    private static void supprimerApprenant(Scanner scanner, ApprenantDAO dao) {
        try {
            System.out.print("Entrez l'ID de l'apprenant à supprimer : ");
            int id = scanner.nextInt();
            dao.supprimerApprenant(id);
            System.out.println("Apprenant supprimé avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression !");
            e.printStackTrace();
        }
    }
}

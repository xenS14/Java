package dao;

import models.Apprenant;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApprenantDAO {
    private Connection connection;

    public ApprenantDAO() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java?serverTimezone=UTC", "root",
                "");
    }

    public void ajouterApprenant(Apprenant apprenant) throws SQLException {
        String sql = "INSERT INTO apprenant (promotion, nom, prenom, email, telephone, nombre_absences, delegue) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, apprenant.getPromotion());
            stmt.setString(2, apprenant.getNom());
            stmt.setString(3, apprenant.getPrenom());
            stmt.setString(4, apprenant.getEmail());
            stmt.setString(5, apprenant.getTelephone());
            stmt.setInt(6, apprenant.getNombreAbsences());
            stmt.setBoolean(7, apprenant.isDelegue());
            stmt.executeUpdate();
        }
    }

    public void supprimerApprenant(int id) throws SQLException {
        // Vérifier si c'est un délégué avant suppression
        String checkQuery = "SELECT delegue FROM apprenant WHERE id = ?";
        try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {
            checkStmt.setInt(1, id);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getBoolean("delegue")) {
                System.out.println("Impossible de supprimer un délégué.");
                return;
            }
        }

        String sql = "DELETE FROM apprenant WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Apprenant> getApprenants() throws SQLException {
        List<Apprenant> apprenants = new ArrayList<>();
        String sql = "SELECT * FROM apprenant ORDER BY nom, nombre_absences";
        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                apprenants.add(new Apprenant(
                        rs.getInt("id"),
                        rs.getString("promotion"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("telephone"),
                        rs.getInt("nombre_absences"),
                        rs.getBoolean("delegue")));
            }
        }
        return apprenants;
    }

    public void modifierAbsences(int id, int nouvellesAbsences) throws SQLException {
        String sql = "UPDATE apprenant SET nombre_absences = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, nouvellesAbsences);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }

    public List<Apprenant> rechercherParPromotion(String promotion) throws SQLException {
        List<Apprenant> apprenants = new ArrayList<>();
        String sql = "SELECT * FROM apprenant WHERE promotion LIKE ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + promotion + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                apprenants.add(new Apprenant(
                        rs.getInt("id"),
                        rs.getString("promotion"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("telephone"),
                        rs.getInt("nombre_absences"),
                        rs.getBoolean("delegue")));
            }
        }
        return apprenants;
    }

    public boolean existeApprenant(String email) throws SQLException {
        String sql = "SELECT COUNT(*) FROM apprenant WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    public Apprenant getApprenantPlusAbsent() throws SQLException {
        String sql = "SELECT * FROM apprenant ORDER BY nombre_absences DESC LIMIT 1";
        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return new Apprenant(
                        rs.getInt("id"),
                        rs.getString("promotion"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("telephone"),
                        rs.getInt("nombre_absences"),
                        rs.getBoolean("delegue"));
            }
        }
        return null; // Aucun apprenant trouvé
    }

    public int getTotalAbsences() throws SQLException {
        String sql = "SELECT SUM(nombre_absences) AS total FROM apprenant";
        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt("total"); // Retourne le total des absences
            }
        }
        return 0; // Retourne 0 si aucun résultat
    }

    public Apprenant getApprenantByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM apprenant WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Apprenant(
                        rs.getInt("id"),
                        rs.getString("promotion"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("telephone"),
                        rs.getInt("nombre_absences"),
                        rs.getBoolean("delegue"));
            }
        }
        return null; // Retourne null si aucun apprenant trouvé
    }
}

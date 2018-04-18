package test.modele.dao;

import java.sql.*;
import java.util.List;
import DAO.UtilisateurDao;
import DAO.Jdbc;
import metier.Utilisateur;

public class TestDaoUtilisateur {

    public static void main(String[] args) {

        java.sql.Connection cnx = null;

        try {
            test0_Connexion();
            System.out.println("Test0 effectué : connexion\n");
            test1_SelectUnique(1);
            System.out.println("Test1 effectué : sélection unique\n");
            test2_SelectMultiple();
            System.out.println("Test2 effectué : sélection multiple\n");
        } catch (ClassNotFoundException e) {
            System.err.println("Erreur de pilote JDBC : " + e);
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e);
        } finally {
            try {
                if (cnx != null) {
                    cnx.close();
                }
            } catch (SQLException e) {
                System.err.println("Erreur de fermeture de la connexion JDBC : " + e);
            }
        }
    }

    /**
     * Vérifie qu'une connexion peut être ouverte sur le SGBD
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void test0_Connexion() throws ClassNotFoundException, SQLException {
        Jdbc.creer("com.mysql.jdbc.Driver", "jdbc:mysql://", "localhost/", "festival2", "groupe1", "groupe1");
//        Jdbc.creer("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:", "@localhost:1521:XE", "", "btssio", "btssio");
        Jdbc.getInstance().connecter();
        Connection cnx = Jdbc.getInstance().getConnexion();
    }

    /**
     * Affiche une adresse d'après son identifiant
     * @param idUtilisateur
     * @throws SQLException
     */
    public static void test1_SelectUnique(int idUtilisateur) throws SQLException {
        Utilisateur ceUtilisateur = UtilisateurDao.selectOne(idUtilisateur);
        System.out.println("Utilisateur d'identifiant : "+ idUtilisateur +" : "+ceUtilisateur.toString());
    }

    /**
     * Affiche toutes les representations
     * @throws SQLException
     */
    public static void test2_SelectMultiple() throws SQLException {
        List<Utilisateur> desUtilisateurs = UtilisateurDao.selectAll();
        System.out.println("Les utilisateurs lus : "+desUtilisateurs.toString());
    }
}
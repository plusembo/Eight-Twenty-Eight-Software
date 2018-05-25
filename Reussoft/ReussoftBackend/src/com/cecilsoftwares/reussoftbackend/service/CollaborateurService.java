package com.cecilsoftwares.reussoftbackend.service;

import com.cecilsoftwares.reussoftbackend.dao.CollaborateurDao;
import com.cecilsoftwares.reussoftmiddleend.model.Collaborateur;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class CollaborateurService {

    private static CollaborateurService uniqueInstance;

    public CollaborateurService() {
    }

    public static synchronized CollaborateurService getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new CollaborateurService();
        }
        return uniqueInstance;
    }

    public List<Collaborateur> listerTousLesCollaborateurs() throws ClassNotFoundException, SQLException {
        return CollaborateurDao.getInstance().listerTousLesCollaborateurs();
    }

    public Collaborateur selectionnerCollaborateurParCode(int codeCollaborateur) throws ClassNotFoundException, SQLException {
        return CollaborateurDao.getInstance().selectionnerCollaborateurParCode(codeCollaborateur);
    }

    public boolean enregistrerCollaborateur(Collaborateur collaborateur) throws ClassNotFoundException, SQLException {
        return CollaborateurDao.getInstance().enregistrerCollaborateur(collaborateur);
    }

    public boolean actualiserCollaborateur(Collaborateur collaborateur) throws ClassNotFoundException, SQLException {
        return CollaborateurDao.getInstance().actualiserCollaborateur(collaborateur);
    }

    public int selectionnerCodeCollaborateurSubsequent() throws ClassNotFoundException, SQLException {
        return CollaborateurDao.getInstance().selectionnerCodeCollaborateurSubsequent();
    }

}
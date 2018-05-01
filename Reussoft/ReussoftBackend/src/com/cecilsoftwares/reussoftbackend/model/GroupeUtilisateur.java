package com.cecilsoftwares.reussoftbackend.model;

import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class GroupeUtilisateur {

    private int codeGroupeUtilizateur;
    private String description;
    private String descriptionAbregee;
    private List<Collaborateur> listeCollaborateurs;

    public GroupeUtilisateur(GroupeUtilisateurBuilder groupeUtilisateurBuilder) {
        codeGroupeUtilizateur = groupeUtilisateurBuilder.codeGroupeUtilizateur;
        description = groupeUtilisateurBuilder.description;
        descriptionAbregee = groupeUtilisateurBuilder.descriptionAbregee;
        listeCollaborateurs = groupeUtilisateurBuilder.listeCollaborateurs;
    }

    public int getCodeGroupeUtilizateur() {
        return codeGroupeUtilizateur;
    }

    public String getDescription() {
        return description;
    }

    public String getDescriptionAbregee() {
        return descriptionAbregee;
    }

    public List<Collaborateur> getListeCollaborateurs() {
        return listeCollaborateurs;
    }

    public static class GroupeUtilisateurBuilder {

        private int codeGroupeUtilizateur;
        private String description;
        private String descriptionAbregee;
        private List<Collaborateur> listeCollaborateurs;

        public GroupeUtilisateurBuilder(int codeGroupeUtilizateur) {
            this.codeGroupeUtilizateur = codeGroupeUtilizateur;
        }

        public GroupeUtilisateurBuilder description(String description) {
            this.description = description;
            return this;
        }

        public GroupeUtilisateurBuilder descriptionAbregee(String descriptionAbregee) {
            this.descriptionAbregee = descriptionAbregee;
            return this;
        }

        public GroupeUtilisateurBuilder listeCollaborateurs(List<Collaborateur> listeCollaborateurs) {
            this.listeCollaborateurs = listeCollaborateurs;
            return this;
        }

    }
}
package com.example.sosmaison;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Artisan {

    @SerializedName("nature_travail")
    @Expose
    private String natureTravail;
    @SerializedName("niveau_competence")
    @Expose
    private String niveauCompetence;
    @SerializedName("annees_competence")
    @Expose
    private String anneesCompetence;
    @SerializedName("nature_diplome")
    @Expose
    private String natureDiplome;
    @SerializedName("type_cours")
    @Expose
    private String typeCours;
    @SerializedName("annee_obtention")
    @Expose
    private String anneeObtention;
    @SerializedName("detail_service")
    @Expose
    private String detailService;
    @SerializedName("prix_jours")
    @Expose
    private String prixJours;
    @SerializedName("description_service")
    @Expose
    private String descriptionService;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;

    public String getNatureTravail() {
        return natureTravail;
    }

    public void setNatureTravail(String natureTravail) {
        this.natureTravail = natureTravail;
    }

    public String getNiveauCompetence() {
        return niveauCompetence;
    }

    public void setNiveauCompetence(String niveauCompetence) {
        this.niveauCompetence = niveauCompetence;
    }

    public String getAnneesCompetence() {
        return anneesCompetence;
    }

    public void setAnneesCompetence(String anneesCompetence) {
        this.anneesCompetence = anneesCompetence;
    }

    public String getNatureDiplome() {
        return natureDiplome;
    }

    public void setNatureDiplome(String natureDiplome) {
        this.natureDiplome = natureDiplome;
    }

    public String getTypeCours() {
        return typeCours;
    }

    public void setTypeCours(String typeCours) {
        this.typeCours = typeCours;
    }

    public String getAnneeObtention() {
        return anneeObtention;
    }

    public void setAnneeObtention(String anneeObtention) {
        this.anneeObtention = anneeObtention;
    }

    public String getDetailService() {
        return detailService;
    }

    public void setDetailService(String detailService) {
        this.detailService = detailService;
    }

    public String getPrixJours() {
        return prixJours;
    }

    public void setPrixJours(String prixJours) {
        this.prixJours = prixJours;
    }

    public String getDescriptionService() {
        return descriptionService;
    }

    public void setDescriptionService(String descriptionService) {
        this.descriptionService = descriptionService;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

}
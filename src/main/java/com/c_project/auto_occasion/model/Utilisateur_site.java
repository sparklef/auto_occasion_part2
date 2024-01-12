package com.c_project.auto_occasion.model;

public class Utilisateur_site {
    int idUser;
    String email;
    String nom;
    String prenom;
    String mdp;
    int contact;

    public Utilisateur_site() {
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public Utilisateur_site(int idUser, String email, String nom, String prenom, String mdp, int contact) {
        this.idUser = idUser;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
        this.contact = contact;
    }
    
    
    public Utilisateur_site(String email, String mdp) {
        this.email = email;
        this.mdp = mdp;
    }

    public String generateBearerToken() {
        // Clé secrète utilisée pour signer le token (à remplacer par une clé plus
        // sécurisée)
        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        // Date d'expiration du token (par exemple, 1 heure)
        Date expirationDate = new Date(System.currentTimeMillis() + 3600000); // 1 heure en millisecondes

        // Génération du token JWT au format Bearer Token
        String token = Jwts.builder()
                .setSubject(this.getNom())
                .claim("userId", this.getIduser())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        return token;
    }
}

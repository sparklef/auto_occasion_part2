CREATE TABLE utilisateur_site (
    idUser SERIAL PRIMARY KEY,
    email VARCHAR(255),
    nom VARCHAR(55),
    prenom VARCHAR(55),
    mdp VARCHAR(255),
    contact VARCHAR(255)
);

CREATE TABLE utilisateur_token (
    idtokenuser SERIAL PRIMARY KEY,
    token_user VARCHAR(255),
    idUser INTEGER REFERENCES utilisateur_site(idUser)
);

CREATE TABLE admin_site (
    idAdmin SERIAL PRIMARY KEY,
    email VARCHAR(55),
    nom VARCHAR(35),
    prenom VARCHAR(55),
    mdp VARCHAR(255),
    contact VARCHAR(255)
);

CREATE TABLE marque (
    idMarque SERIAL PRIMARY KEY,
    marque VARCHAR(35)
);

CREATE TABLE categorie (
    idCategorie SERIAL PRIMARY KEY,
    categorie VARCHAR(35)
);

CREATE TABLE detail_voiture (
    idDetail SERIAL PRIMARY KEY,
    couleur VARCHAR(15),
    nbr_portes INTEGER,
    boite_devitesse VARCHAR(25),
    source_energie VARCHAR(55),
    annee INTEGER,
    modele VARCHAR(55)
);

CREATE TABLE voiture (
    idCar SERIAL PRIMARY KEY,
    nom_voiture VARCHAR(255),
    matricule VARCHAR(55),
    idMarque INTEGER REFERENCES marque(idMarque),
    idCategorie INTEGER REFERENCES categorie(idCategorie),
    idDetail INTEGER REFERENCES detail_voiture(idDetail)
);

CREATE TABLE annonce (
    idAnnonce SERIAL PRIMARY KEY,
    idUser INTEGER REFERENCES utilisateur_site(idUser),
    idCar INTEGER REFERENCES voiture(idCar),
    statut INTEGER REFERENCES statut(idstatut),
    date_annonce DATE,
    lieu VARCHAR(55),
    image_car VARCHAR(255),
    prix NUMERIC(10,2),
    description_annonce VARCHAR(255),
    validation_annonce boolean -- true si validée et false si non validée
);

CREATE TABLE statut (
    idstatut SERIAL PRIMARY KEY,
    statut VARCHAR(255)
);

CREATE TABLE favoris (
    idFavoris SERIAL PRIMARY KEY,
    idAnnonce INTEGER REFERENCES annonce(idAnnonce),
    user_id INTEGER REFERENCES utilisateur_site(iduser)
);

CREATE TABLE commission (
    idCommission SERIAL PRIMARY KEY,
    idAdmin INTEGER REFERENCES admin_site(idAdmin),
    idAnnonce INTEGER REFERENCES annonce(idAnnonce),
    commission_percentage NUMERIC(5,2), -- percentage of commission
    commission_amount NUMERIC(10,2), -- calculated commission amount
    commission_status VARCHAR(255) -- status of commission (paid, unpaid, etc.)
);
--- TRIGGER
CREATE OR REPLACE FUNCTION calculate_commission_amount()
RETURNS TRIGGER AS $$
BEGIN
    NEW.commission_amount := (NEW.prix * NEW.commission_percentage) / 100;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER commission_amount_trigger
BEFORE INSERT OR UPDATE ON commission
FOR EACH ROW EXECUTE PROCEDURE calculate_commission_amount();
---TRIGGER
DROP TABLE utilisateur_site CASCADE;
DROP TABLE utilisateur_token CASCADE;
DROP TABLE admin_site CASCADE;
DROP TABLE voiture CASCADE;
DROP TABLE marque CASCADE;
DROP TABLE categorie CASCADE;
DROP TABLE detail_voiture CASCADE;
DROP TABLE annonce CASCADE;
DROP TABLE favoris CASCADE;
DROP TABLE statut CASCADE;

UPDATE annonce SET nom_voiture = 'Voiture 1' WHERE idannonce = 1;
UPDATE annonce SET nom_voiture = 'Voiture 2' WHERE idannonce = 2;
UPDATE annonce SET nom_voiture = 'Voiture 3' WHERE idannonce = 3;
UPDATE annonce SET nom_voiture = 'Voiture 4' WHERE idannonce = 4;
UPDATE annonce SET nom_voiture = 'Voiture 5' WHERE idannonce = 5;
UPDATE annonce SET nom_voiture = 'Voiture 6' WHERE idannonce = 6;
UPDATE annonce SET nom_voiture = 'Voiture 7' WHERE idannonce = 8;

UPDATE annonce SET nom_voiture = 'Toyota Hilux' WHERE idannonce = 1;

UPDATE detail_voiture SET annonce =1 WHERE iddetail = 1;
UPDATE detail_voiture SET annonce =2 WHERE iddetail = 2;
UPDATE detail_voiture SET annonce =3 WHERE iddetail = 3;
UPDATE detail_voiture SET annonce =4 WHERE iddetail = 4;
UPDATE detail_voiture SET annonce =5 WHERE iddetail = 5;
UPDATE detail_voiture SET annonce =6 WHERE iddetail = 6;
UPDATE detail_voiture SET annonce =8 WHERE iddetail = 7;
UPDATE detail_voiture SET annonce =9 WHERE iddetail = 8;

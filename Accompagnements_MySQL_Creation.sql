DROP TABLE IF EXISTS demande;
DROP TABLE IF EXISTS accompagnement;
DROP TABLE IF EXISTS commentaire;
DROP TABLE IF EXISTS client;
DROP TABLE IF EXISTS voiture;
DROP TABLE IF EXISTS equipe;
DROP TABLE IF EXISTS benevole;
DROP TABLE IF EXISTS secteur;

CREATE TABLE secteur (
    NumSecteur INTEGER NOT NULL AUTO_INCREMENT,
    NomSecteur VARCHAR(45),
    CodePostal VARCHAR(7),
    CONSTRAINT pk_Secteur_NumSecteur PRIMARY KEY(NumSecteur)
);

CREATE TABLE client (
    NASClient VARCHAR(11),
    NomClient VARCHAR(45),
    PrenomClient VARCHAR(45),
    DateNaissance DATE,
    AdresseClient VARCHAR(45) NOT NULL,
    NumSecteur INTEGER,
    CONSTRAINT pk_Client_NASClient PRIMARY KEY(NASClient),
    CONSTRAINT fk_Client_NumSecteur FOREIGN KEY(NumSecteur) REFERENCES secteur(NumSecteur)
);

CREATE TABLE benevole (
    NASBenevole VARCHAR(11),
    NomBenevole VARCHAR(45),
    PrenomBenevole VARCHAR(45),
    AdresseBenevole VARCHAR(45) NOT NULL,
    DispoBenevole TINYINT,
    NumSecteur INTEGER,
    CONSTRAINT pk_Benevole_NASBenevole PRIMARY KEY(NASBenevole),
    CONSTRAINT fk_Benevole_NumSecteur FOREIGN KEY(NumSecteur) REFERENCES secteur(NumSecteur)
);

CREATE TABLE equipe (
    IDEquipe INTEGER NOT NULL AUTO_INCREMENT,
    DispoEquipe TINYINT,
    StatutEquipe TINYINT,
    NASBenevole1 VARCHAR(11),
    NASBenevole2 VARCHAR(11),
    NumSecteur INTEGER,
    CONSTRAINT pk_Equipe_IDEquipe PRIMARY KEY(IDEquipe),
    CONSTRAINT fk_Equipe_NumSecteur FOREIGN KEY(NumSecteur) REFERENCES secteur(NumSecteur),
    CONSTRAINT fk_Equipe_NASBenevole1 FOREIGN KEY (NASBenevole1) REFERENCES benevole(NASBenevole),
    CONSTRAINT fk_Equipe_NASBenevole2 FOREIGN KEY (NASBenevole2) REFERENCES benevole(NASBenevole)
);

CREATE TABLE demande (
    NumDemande INTEGER NOT NULL AUTO_INCREMENT,
    DateHeureDemande DATETIME,
    TypeAccomp VARCHAR(45),
    NASClient VARCHAR(11),
    CONSTRAINT pk_Demande_NumDemande PRIMARY KEY(NumDemande),
    CONSTRAINT fk_Demande_NASClient FOREIGN KEY (NASClient) REFERENCES client(NASClient)
);

CREATE TABLE voiture (
    NoImmatriculation VARCHAR(11),
    MarqueVehicule VARCHAR(45),
    AnneeVehicule VARCHAR(8),
    TypeVehicule VARCHAR(45),
    NASBenevole VARCHAR(11),
    IDEquipe INTEGER,
    CONSTRAINT pk_Voiture_NoImmatriculation PRIMARY KEY(NoImmatriculation),
    CONSTRAINT fk_Voiture_NASBenevole FOREIGN KEY (NASBenevole) REFERENCES benevole(NASBenevole),
    CONSTRAINT fk_Voiture_IDEquipe FOREIGN KEY (IDEquipe) REFERENCES equipe(IDEquipe)
);

CREATE TABLE accompagnement (
    IDAccomp INTEGER NOT NULL AUTO_INCREMENT,
    DebutAccomp DATETIME,
    FinAccomp DATETIME,
    TypeAccomp VARCHAR(45),
    NASBenevole VARCHAR(11),
    NASClient VARCHAR(11),
    IDEquipe INTEGER,
    CONSTRAINT pk_Accompagnement_IDAccomp PRIMARY KEY(IDAccomp),
    CONSTRAINT fk_Accompagnement_NASBenevole FOREIGN KEY (NASBenevole) REFERENCES benevole(NASBenevole),
    CONSTRAINT fk_Accompagnement_NASClient FOREIGN KEY (NASClient) REFERENCES client(NASClient),
    CONSTRAINT fk_Accompagnement_IDEquipe FOREIGN KEY (IDEquipe) REFERENCES equipe(IDEquipe)
);

CREATE TABLE commentaire (
    NumCommentaire INTEGER NOT NULL AUTO_INCREMENT,
    DateHeureCommentaire DATETIME,
    TypeCommentaire VARCHAR(45),
    NASClient VARCHAR(11),
    CONSTRAINT pk_Commentaire_NumCommentaire PRIMARY KEY(NumCommentaire),
    CONSTRAINT fk_Commentaire_NASClient FOREIGN KEY (NASClient) REFERENCES client(NASClient)
);

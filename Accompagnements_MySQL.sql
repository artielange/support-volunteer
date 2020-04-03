DROP TABLE secteur;
DROP TABLE client;
DROP TABLE benevole;
DROP TABLE equipe;
DROP TABLE demande;
DROP TABLE voiture;
DROP TABLE accompagnement;
DROP TABLE commentaire;

CREATE TABLE secteur (
    NumSecteur INTEGER,
    NomSecteur VARCHAR(45),
    CodePostal VARCHAR(7),
    CONSTRAINT pk_Secteur_NumSecteur PRIMARY KEY(NumSecteur)
);

CREATE TABLE client (
    NASClient VARCHAR(11),
    NomClient VARCHAR(45),
    PrenomClient VARCHAR(45),
    DateNaissance VARCHAR(45),
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
    IDEquipe INTEGER NOT NULL,
    DispoEquipe TINYINT,
    StatutEquipe TINYINT,
    NASBenevole VARCHAR(11),
    NumSecteur INTEGER,
    CONSTRAINT pk_Equipe_IDEquipe PRIMARY KEY(IDEquipe),
    CONSTRAINT fk_Equipe_NumSecteur FOREIGN KEY(NumSecteur) REFERENCES secteur(NumSecteur),
    CONSTRAINT fk_Equipe_NASBenevole FOREIGN KEY (NASBenevole) REFERENCES benevole(NASBenevole)
);

CREATE TABLE demande (
    NumDemande INTEGER NOT NULL,
    HeureDemande VARCHAR(45),
    TypeAccomp VARCHAR(45),
    NASClient VARCHAR(11),
    CONSTRAINT pk_Demande_NumDemande PRIMARY KEY(NumDemande),
    CONSTRAINT fk_Demande_NASClient FOREIGN KEY (NASClient) REFERENCES client(NASClient)
);

CREATE TABLE voiture (
    NoImmatriculation VARCHAR(11),
    MarqueVehicule VARCHAR(45),
    AnneeVehicule VARCHAR(4),
    TypeVehicule VARCHAR(45),
    NASBenevole VARCHAR(11),
    IDEquipe INTEGER,
    CONSTRAINT pk_Voiture_NoImmatriculation PRIMARY KEY(NoImmatriculation),
    CONSTRAINT fk_Voiture_NASBenevole FOREIGN KEY (NASBenevole) REFERENCES benevole(NASBenevole),
    CONSTRAINT fk_Voiture_IDEquipe FOREIGN KEY (IDEquipe) REFERENCES equipe(IDEquipe)
);

CREATE TABLE accompagnement (
    IDAccomp INTEGER,
    DateAccomp VARCHAR(10),
    HeureDebutAccomp VARCHAR(8),
    HeureFinAccomp VARCHAR(8),
    TypeAccomp VARCHAR(45),
    NASBenevole VARCHAR(11),
    NASClient VARCHAR(11),
    IDEquipe INTEGER NOT NULL,
    CONSTRAINT pk_Accompagnement_IDAccomp PRIMARY KEY(IDAccomp),
    CONSTRAINT fk_Accompagnement_NASBenevole FOREIGN KEY (NASBenevole) REFERENCES benevole(NASBenevole),
    CONSTRAINT fk_Accompagnement_NASClient FOREIGN KEY (NASClient) REFERENCES client(NASClient),
    CONSTRAINT fk_Accompagnement_IDEquipe FOREIGN KEY (IDEquipe) REFERENCES equipe(IDEquipe)
);

CREATE TABLE commentaire (
    NumCommentaire INTEGER,
    DateCommentaire VARCHAR(10),
    HeureCommentaiClientNASClientre VARCHAR(8),
    TypeCommentaire VARCHAR(45),
    NASClient VARCHAR(11),
    CONSTRAINT pk_Commentaire_NumCommentaire PRIMARY KEY(NumCommentaire),
    CONSTRAINT fk_Commentaire_NASClient FOREIGN KEY (NASClient) REFERENCES client(NASClient)
);

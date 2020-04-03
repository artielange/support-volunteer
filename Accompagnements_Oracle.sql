DROP TABLE Secteur;
DROP TABLE Client;
DROP TABLE Benevole;
DROP TABLE Equipe;
DROP TABLE Demande;
DROP TABLE Voiture;
DROP TABLE Accompagnement;
DROP TABLE Commentaire;

CREATE TABLE Secteur (
    NumSecteur INTEGER,
    NomSecteur VARCHAR(45),
    CodePostal VARCHAR(7),
    CONSTRAINT pk_Secteur_NumSecteur PRIMARY KEY(NumSecteur)
);

CREATE TABLE Client (
    NASClient VARCHAR(11),
    NomClient VARCHAR(45),
    PrenomClient VARCHAR(45),
    DateNaissance DATE,
    AdresseClient VARCHAR(45) NOT NULL,
    NumSecteur INTEGER,
    CONSTRAINT pk_Client_NASClient PRIMARY KEY(NASClient),
    CONSTRAINT fk_Client_NumSecteur FOREIGN KEY(NumSecteur) REFERENCES Secteur(NumSecteur)
);

CREATE TABLE Benevole (
    NASBenevole VARCHAR(11),
    NomBenevole VARCHAR(45),
    PrenomBenevole VARCHAR(45),
    AdresseBenevole VARCHAR(45) NOT NULL,
    DispoBenevole NUMBER(1),
    NumSecteur INTEGER,
    CONSTRAINT pk_Benevole_NASBenevole PRIMARY KEY(NASBenevole),
    CONSTRAINT fk_Benevole_NumSecteur FOREIGN KEY(NumSecteur) REFERENCES Secteur(NumSecteur)
);

CREATE TABLE Equipe (
    IDEquipe INTEGER NOT NULL,
    DispoEquipe NUMBER(1),
    StatutEquipe NUMBER(1),
    NASBenevole VARCHAR(11),
    NumSecteur INTEGER,
    CONSTRAINT pk_Equipe_IDEquipe PRIMARY KEY(IDEquipe),
    CONSTRAINT fk_Equipe_NumSecteur FOREIGN KEY(NumSecteur) REFERENCES Secteur(NumSecteur),
    CONSTRAINT fk_Equipe_NASBenevole FOREIGN KEY (NASBenevole) REFERENCES Benevole(NASBenevole)
);

CREATE TABLE Demande (
    NumDemande INTEGER NOT NULL,
    HeureDemande VARCHAR(45),
    TypeAccomp VARCHAR(45),
    NASClient VARCHAR(11),
    CONSTRAINT pk_Demande_NumDemande PRIMARY KEY(NumDemande),
    CONSTRAINT fk_Demande_NASClient FOREIGN KEY (NASClient) REFERENCES Client(NASClient)
);

CREATE TABLE Voiture (
    NoImmatriculation VARCHAR(11),
    MarqueVehicule VARCHAR(45),
    AnneeVehicule VARCHAR(4),
    TypeVehicule VARCHAR(45),
    NASBenevole VARCHAR(11),
    IDEquipe INTEGER,
    CONSTRAINT pk_Voiture_NoImmatriculation PRIMARY KEY(NoImmatriculation),
    CONSTRAINT fk_Voiture_NASBenevole FOREIGN KEY (NASBenevole) REFERENCES Benevole(NASBenevole),
    CONSTRAINT fk_Voiture_IDEquipe FOREIGN KEY (IDEquipe) REFERENCES Equipe(IDEquipe)
);

CREATE TABLE Accompagnement (
    IDAccomp INTEGER,
    DateAccomp VARCHAR(10),
    HeureDebutAccomp VARCHAR(8),
    HeureFinAccomp VARCHAR(8),
    TypeAccomp VARCHAR(45),
    NASBenevole VARCHAR(11),
    NASClient VARCHAR(11),
    IDEquipe INTEGER NOT NULL,
    CONSTRAINT pk_Accompagnement_IDAccomp PRIMARY KEY(IDAccomp),
    CONSTRAINT fk_Accompagnement_NASBenevole FOREIGN KEY (NASBenevole) REFERENCES Benevole(NASBenevole),
    CONSTRAINT fk_Accompagnement_NASClient FOREIGN KEY (NASClient) REFERENCES Client(NASClient),
    CONSTRAINT fk_Accompagnement_IDEquipe FOREIGN KEY (IDEquipe) REFERENCES Equipe(IDEquipe)
);

CREATE TABLE Commentaire (
    NumCommentaire INTEGER,
    DateCommentaire VARCHAR(10),
    HeureCommentaire VARCHAR(8),
    TypeCommentaire VARCHAR(45),
    NASClient VARCHAR(11),
    CONSTRAINT pk_Commentaire_NumCommentaire PRIMARY KEY(NumCommentaire),
    CONSTRAINT fk_Commentaire_NASClient FOREIGN KEY (NASClient) REFERENCES Client(NASClient)
);

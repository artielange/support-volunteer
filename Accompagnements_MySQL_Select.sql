-- 1. trouver le plus jeune client avec type d'accompagnement 'Hopital'
select NomClient, PrenomClient, max(DateNaissance) as Date_Naissance, NomSecteur 
from client 
inner join accompagnement on accompagnement.NASClient = client.NASClient
inner join secteur on secteur.NumSecteur = client.NumSecteur
where accompagnement.TypeAccomp = 'Hopital';

-- 2. trouver les secteurs ou le service d'accompagnement n’était pas de type Support à domicile et dont le service a été effectué au moins une fois (nous déduisons que support domicile ne requiert pas de voiture, donc cette requête permet de voir les accompagnements effectués SANS voiture) 
select distinct NomSecteur 
from secteur 
inner join client on client.NumSecteur = secteur.NumSecteur 
inner join benevole on benevole.NumSecteur = client.NumSecteur 
WHERE secteur.NumSecteur IN (
	select NumSecteur from client inner join accompagnement on accompagnement.NASClient = client.NASClient
	where accompagnement.TypeAccomp = 'SupportDomicile'
) 
and NASClient IN (
	select NASClient from accompagnement where TypeAccomp not in ('SupportDomicile')
);

--  3. afficher le bénévole et l'immatriculation et le numéro de commentaire dont son véhicule a recu un commentaire de saleté
select distinct NumCommentaire, DateHeureCommentaire, NomBenevole, PrenomBenevole, NoImmatriculation from commentaire 
inner join accompagnement on accompagnement.NASClient = commentaire.NASClient 
inner join voiture on voiture.IDEquipe = accompagnement.IDEquipe 
inner join benevole on benevole.NASBenevole = voiture.NASBenevole WHERE commentaire.NASClient IN
(select NASClient from commentaire where TypeCommentaire = 'Saleté véhicule');

-- 4. afficher le nombre d'heures requis pour chaque type d'accompagnement par secteur
select distinct NomSecteur, TypeAccomp, FORMAT(time_to_sec(timediff(FinAccomp, DebutAccomp)) / 3600, 0) AS HeuresRequises FROM accompagnement
inner join equipe on equipe.IDEquipe = accompagnement.IDEquipe
inner join benevole on benevole.NumSecteur = equipe.NumSecteur
inner join secteur on secteur.NumSecteur = benevole.NumSecteur 
order by NomSecteur;

-- 5. trouver le nombre d"accompagnement avec véhicule de marque HONDA dans la période du 2015-01-01 au '2019-01-01'
select count(*) AS Nombre_Accompagnement from accompagnement inner join voiture on voiture.IDEquipe = accompagnement.IDEquipe where accompagnement.IDEquipe in 
(select IDEquipe from voiture where voiture.MarqueVehicule = 'Honda')
and DebutAccomp between '2015-01-01' and '2019-01-01';

-- 6. afficher le nombre de bénévole par secteur
select secteur.NomSecteur, count(NASBenevole) 
from benevole, secteur
where benevole.NumSecteur = secteur.NumSecteur
group by secteur.NomSecteur
having count(secteur.NumSecteur);

-- 7. trouver les accompagnements de notre cher Sid Abel fait depuis les 5 dernieres années
select * from accompagnement inner join benevole on benevole.NASBenevole = accompagnement.NASBenevole
where DebutAccomp BETWEEN '2015-01-01' and '2020-01-01'
and benevole.NomBenevole = 'Abel' AND benevole.PrenomBenevole = 'Sid'; 

-- 8.  Noms des clients en ordre alphabétique 
SELECT NomClient, PrenomClient FROM client
ORDER BY NomClient, PrenomClient;

-- 9. afficher les informations des voitures de type 'SUV' et le nom de secteur desservi pour les accompagnements effectués entre le 2016-01-01 et 2019-31-12
select NoImmatriculation, MarqueVehicule, AnneeVehicule, TypeVehicule, NomSecteur, DebutAccomp from voiture inner join accompagnement on accompagnement.NASBenevole = voiture.NASBenevole
inner join equipe on equipe.NASBenevole1 = voiture.NASBenevole
inner join secteur on secteur.NumSecteur = equipe.NumSecteur
where accompagnement.DebutAccomp between '2016-01-01' and '2019-12-31'
and voiture.TypeVehicule = 'SUV';

-- 10. nombre d'accompagnements par secteur
SELECT secteur.NomSecteur, COUNT(*) AS Nombre_Accompagements
FROM accompagnement 
INNER JOIN equipe 
ON accompagnement.IDEquipe = equipe.IDEquipe
INNER JOIN secteur
ON equipe.NumSecteur = secteur.NumSecteur
GROUP BY accompagnement.IDEquipe;

-- 11. Secteurs qui n'ont pas d'équipes
SELECT NumSecteur, NomSecteur FROM secteur WHERE NumSecteur NOT IN (SELECT NumSecteur FROM equipe);
SELECT NumSecteur, NomSecteur FROM secteur WHERE NumSecteur IN (SELECT NumSecteur FROM equipe);


-- PL\SQL

CREATE TRIGGER fin_supp
AFTER DELETE statement ON demande

CREATE VIEW dispo_ben
AS SELECT PrenomBenevole, NomBenevole, NomSecteur
FROM benevole INNER JOIN secteur ON benevoleNumSecteur = secteur.NumSecteur
WHERE benevole.DispoBenevole = 1 ;

DECLARE

x INTEGER;
y INTEGER;
cmpt INTEGER;
pas_de_demande EXCEPTION;

CURSOR ComptDemande IS
	SELECT * FROM demande;
cursdem demande%ROWTYPE;
	
BEGIN

SELECT COUNT(*) INTO cmpt FROM demande;
IF cmpt = 0 THEN RAISE pas_de_demande;
END IF;

SELECT IDAccomp INTO x FROM accompagnement WHERE IDAccomp = MIN(IDAccomp);
SELECT IDAccomp INTO y FROM accompagnement WHERE IDAccomp = MAX(IDAccomp);

WHILE x>y LOOP

DELETE FROM demande INNER JOIN demande ON demande=NumDemande = accompagnement.NumDemande WHERE IDAccomp = x;
x:=x+1;

IF DELETING THEN
DBMS_OUTPUT.PUT_LINE('Demandes supprimées');

END LOOP;

OPEN cursdem;

LOOP

	FETCH demande INTO cursdem;
	EXIT WHEN demande%NOTFOUND;
	DBMS_OUTPUT.PUT_LINE(cursdem);
	
END LOOP;

EXCEPTION

	WHEN pas_de_demande THEN RAISE_APPLICATION_ERROR(-20501, 'Erreur : Table Demande Vide');
	
END;
-- 1. Afficher les clients, leurs dates de naissance ainsi que le secteur desservi pour un accompagnement de type ‘Hopital’
select NomClient, PrenomClient, DateNaissance as Date_Naissance, NomSecteur
from client
inner join accompagnement on accompagnement.NASClient = client.NASClient
inner join secteur on secteur.NumSecteur = client.NumSecteur
where accompagnement.TypeAccomp = 'Hopital';

-- 2. Trouver les secteurs où le service d'accompagnement n’était pas de type ‘Support à domicile’ et dont le service a été effectué au moins une fois (nous déduisons que support domicile ne requiert pas de voiture, donc cette requête permet de voir les accompagnements effectués SANS voiture)
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

--  3. Afficher les bénévole, l'immatriculation de la voiture et le numéro de commentaire dont le véhicule a reçu un commentaire de ‘Saleté’
select distinct NumCommentaire, DateHeureCommentaire, NomBenevole, PrenomBenevole, NoImmatriculation from commentaire
inner join accompagnement on accompagnement.NASClient = commentaire.NASClient
inner join voiture on voiture.IDEquipe = accompagnement.IDEquipe
inner join benevole on benevole.NASBenevole = voiture.NASBenevole WHERE commentaire.NASClient IN
(select NASClient from commentaire where TypeCommentaire = 'Saleté véhicule');

-- 4. Afficher le nombre d'heures requises pour chaque type d'accompagnement trié par nom du secteur
select distinct NomSecteur, TypeAccomp, FORMAT(time_to_sec(timediff(FinAccomp, DebutAccomp)) / 3600, 0) AS HeuresRequises FROM accompagnement
inner join equipe on equipe.IDEquipe = accompagnement.IDEquipe
inner join benevole on benevole.NumSecteur = equipe.NumSecteur
inner join secteur on secteur.NumSecteur = benevole.NumSecteur
order by NomSecteur;

-- 5. Trouver le nombre d’accompagnements effectués avec un véhicule de marque ‘HONDA’ dans la période du 2015-01-01 au '2019-01-01'
select count(*) AS Nombre_Accompagnement from accompagnement inner join voiture on voiture.IDEquipe = accompagnement.IDEquipe where accompagnement.IDEquipe in
(select IDEquipe from voiture where voiture.MarqueVehicule = 'Honda')
and DebutAccomp between '2015-01-01' and '2019-01-01';

-- 6. Afficher le nombre de bénévole par secteur
select secteur.NomSecteur, count(NASBenevole)
from benevole, secteur
where benevole.NumSecteur = secteur.NumSecteur
group by secteur.NomSecteur
having count(secteur.NumSecteur);

-- 7. Trouver les accompagnements faits par ‘Sidi Abel’ depuis les 5 dernières années
select * from accompagnement inner join benevole on benevole.NASBenevole = accompagnement.NASBenevole
where DebutAccomp BETWEEN '2015-01-01' and '2020-01-01'
and benevole.NomBenevole = 'Abel' AND benevole.PrenomBenevole = 'Sid';

-- 8.  Afficher les noms et prénoms des clients par ordre alphabétique
SELECT NomClient, PrenomClient FROM client
ORDER BY NomClient, PrenomClient;

-- 9. Afficher les informations des voitures de type 'SUV' et le nom de secteur desservi pour les accompagnements effectués entre le 2016-01-01 et 2019-31-12
select NoImmatriculation, MarqueVehicule, AnneeVehicule, TypeVehicule, NomSecteur, DebutAccomp from voiture inner join accompagnement on accompagnement.NASBenevole = voiture.NASBenevole
inner join equipe on equipe.NASBenevole1 = voiture.NASBenevole
inner join secteur on secteur.NumSecteur = equipe.NumSecteur
where accompagnement.DebutAccomp between '2016-01-01' and '2019-12-31'
and voiture.TypeVehicule = 'SUV';

-- 10. Afficher le nombre d’accompagnements effectués par secteur
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

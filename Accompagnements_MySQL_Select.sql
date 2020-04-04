-- trouver le client le plus jeune ainsi que sa date de naissance ainsi que le nom du benevole de son accompagnement pour un type d'accompagnement sans voiture
select NomClient, PrenomClient, DateNaissance, NomBenevole, PrenomBenevole from secteur inner join client on client.NumSecteur = secteur.NumSecteur
inner join benevole on benevole.NumSecteur = client.NumSecteur
inner join equipe on equipe.NASBenevole2 = benevole.NASBenevole WHERE secteur.NumSecteur IN
(select NumSecteur from client where DateNaissance in (select max(DateNaissance) from client inner join demande on demande.NASClient = client.NASClient
where demande.TypeAccomp = 'SupportDomicile') and NASClient IN
(SELECT NASClient from accompagnement where TypeAccomp in ('SupportDomicile')
));

-- trouver les secteurs ou le service d'accompagnement avec voiture a été utilisé au moins une fois
select distinct NomSecteur from secteur inner join client on client.NumSecteur = secteur.NumSecteur
inner join benevole on benevole.NumSecteur = client.NumSecteur
WHERE secteur.NumSecteur IN (select NumSecteur from client inner join demande on demande.NASClient = client.NASClient
where demande.TypeAccomp = 'SupportDomicile') and NASClient IN (select NASClient from accompagnement where TypeAccomp not in ('SupportDomicile')
);

-- afficher le bénévole et l'immatriculation et le numéro de commentaire dont son véhicule a recu un commentaire de saleté
select distinct NumCommentaire, DateCommentaire, NomBenevole, PrenomBenevole, NoImmatriculation from commentaire
inner join accompagnement on accompagnement.NASClient = commentaire.NASClient
inner join voiture on voiture.IDEquipe = accompagnement.IDEquipe
inner join benevole on benevole.NASBenevole = voiture.NASBenevole WHERE commentaire.NASClient IN
(select NASClient from commentaire where TypeCommentaire = 'Saleté véhicule');

--afficher le nombre d'heures requis pour chaque type d'accompagnement par secteur
select distinct NomSecteur, TypeAccomp, (HeureFinAccomp - HeureDebutAccomp) from accompagnement
inner join equipe on equipe.IDEquipe = accompagnement.IDEquipe
inner join benevole on benevole.NumSecteur = equipe.NumSecteur
inner join secteur on secteur.NumSecteur = benevole.NumSecteur
order by NomSecteur;


-- trouver le nombre d"accompagnement avec véhicule de marque HONDA dans la période du 2015-01-01 au '2019-01-01'
select count(*) from accompagnement inner join voiture on voiture.IDEquipe = accompagnement.IDEquipe where accompagnement.IDEquipe in
(select IDEquipe from voiture where voiture.MarqueVehicule = 'Honda')
and DateAccomp between '2015-01-01' and '2019-01-01';

-- afficher le nombre de bénévole par secteur
select secteur.NomSecteur, count(NASBenevole) from benevole, secteur
where benevole.NumSecteur = secteur.NumSecteur
group by secteur.NomSecteur
having count(secteur.NumSecteur);


--select * from Accompagnement inner join benevole on benevole.NASBenevole = accompagnement.NASBenevole
--where DateAccomp BETWEEN '2015-01-01' and '2020-01-01'
--and benevole.NomBenevole = 'Abel';
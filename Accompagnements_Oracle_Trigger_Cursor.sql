-- Declaration d'un trigger permettant de bloquer l'ajout d'un client si ce dernier est agé de moins de 18 ans
CREATE OR REPLACE TRIGGER age_verif
BEFORE INSERT ON client
  FOR EACH ROW

DECLARE

-- Declaration de la variable pour le calcul de l'age
age_client NUMBER;

-- Declaration de curseur
CURSOR ChangTypVehic IS
	SELECT * FROM voiture WHERE typevehicule = 'PICKUP';
cursv voiture%ROWTYPE;

BEGIN

-- Calcul de l'age du client
SELECT MONTHS_BETWEEN(TRUNC(sysdate), TO_DATE(:new.datenaissance,'YYYY-MM-DD'))/12 INTO age_client FROM DUAL;

-- Vérifier si le client est agé de moins de 18 ans
    IF (age_client < 18) THEN
      RAISE_APPLICATION_ERROR(-20000,'Client doit être agé de 18 an et plus');
    END IF;

-- Fermeture de curseur
OPEN ChangTypVehic;


LOOP

-- Balayage des tuples
	FETCH ChangTypVehic INTO cursv;
-- Mise à jour des voitures de type PICKUP
    UPDATE voiture
    SET typevehicule = 'SUV' WHERE typevehicule = cursv.typevehicule;
	EXIT WHEN ChangTypVehic%NOTFOUND;

END LOOP;

-- Fermeture de curseur
CLOSE ChangTypVehic;

END;
/
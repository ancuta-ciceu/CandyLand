select ID, denumire_produs,cantitate,pret from produse
DELETE FROM produse WHERE cantitate="Cremes";

INSERT INTO produse(denumire_produs, cantitate, pret) VALUE ('Surpize dulci','150','200 lei')
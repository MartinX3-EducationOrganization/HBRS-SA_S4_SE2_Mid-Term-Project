ALTER TABLE "public".anzeige DROP CONSTRAINT check_anstellungsart;

ALTER TABLE "public".anzeige
ADD CONSTRAINT check_anstellungsart 	 
CHECK (anstellungsart::text = ANY (ARRAY['Ausbildung'::character varying, 'befristete Anstellung'::character varying, 'Festanstellung'::character varying, 'Praktikum'::character varying, 'Werkstudent/-in'::character varying]::text[]));
```sql
CREATE TABLE Asiakas (
	Id integer PRIMARY KEY,
	nimi varchar(100),
	pNumero Integer
);

CREATE TABLE Tila (
	nimi varchar(100) PRIMARY KEY, 
	paikkoja Integer
);

CREATE TABLE Varustelu (
	Id integer PRIMARY KEY,
	varuste varchar(200)
);

CREATE TABLE Varaus (
	Id integer PRIMARY KEY,
	Asiakas_Id integer,
	Tila_nimi varchar(100),
	nimi varchar(200),
	Alkaa Date, 
	Loppuu Date, 
	FOREIGN KEY (Asiakas_id) REFERENCES Asiakas(id), 
	FOREIGN KEY (Tila_nimi) REFERENCES Tila(nimi)
);

CREATE TABLE TilaVarustelu(
	Tila_nimi varchar(100),
	varustelu_id integer, 
	FOREIGN KEY (Tila_nimi) REFERENCES Tila(nimi), 
	FOREIGN KEY (varustelu_id) REFERENCES Varustelu(id)
);
```

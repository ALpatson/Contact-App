
CREATE TABLE IF NOT EXISTS person (
    idperson INTEGER PRIMARY KEY AUTOINCREMENT,
    lastname VARCHAR(45) NOT NULL,
    firstname VARCHAR(45) NOT NULL,
    nickname VARCHAR(45) NOT NULL,
    phone_number VARCHAR(15),
    address VARCHAR(200),
    email_address VARCHAR(150),
    birth_date DATE
);
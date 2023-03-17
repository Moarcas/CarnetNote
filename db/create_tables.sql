CREATE TABLE IF NOT EXISTS users (
    id INTEGER PRIMARY KEY,
    nume TEXT NOT NULL,
    prenume TEXT NOT NULL,
    email TEXT NOT NULL,
    passwordHash TEXT NOT NULL,
    rol TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS students (
    id INTEGER PRIMARY KEY,
    an INTEGER NOT NULL,
    grupa INTEGER NOT NULL,
    FOREIGN KEY(id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS subjects (
    id INTEGER PRIMARY KEY,
    nume TEXT NOT NULL,
    descriere TEXT
);
    
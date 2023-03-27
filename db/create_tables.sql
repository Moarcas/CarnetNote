CREATE TABLE IF NOT EXISTS users (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nume TEXT NOT NULL,
    prenume TEXT NOT NULL,
    email TEXT NOT NULL,
    passwordHash TEXT NOT NULL,
    rol TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS students (
    id INTEGER PRIMARY KEY REFERENCES users(id),
    idGrupa INTEGER REFERENCES classes(id),
    FOREIGN KEY(id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS teachers (
    id INTEGER PRIMARY KEY REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS subjects (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nume TEXT NOT NULL,
    descriere TEXT
);

CREATE TABLE IF NOT EXISTS classes (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nume TEXT NOT NULL,
    an INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS teacher_subject (
    idProfesor INTEGER REFERENCES teachers(id),
    idMaterie INTEGER REFERENCES subjects(id),
    idGrupa INTEGER REFERENCES classes(id),
    PRIMARY KEY (idProfesor, idMaterie, idGrupa) 
);


-- CREATE TABLE IF NOT EXISTS admins (
--     id INTEGER PRIMAY KEY users(id) REFERENCES users(id)
-- );


-- CREATE TABLE IF NOT EXISTS grades (
--     id INTEGER PRIMARY KEY AUTOINCREMENT, 
--     idStudent INTEGER REFERENCES students(id) NOT NULL,
--     idMaterie INTEGER REFERENCES subjects(id) NOT NULL,
--     nota REAL NOT NULL
-- );



    


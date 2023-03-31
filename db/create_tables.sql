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

CREATE TABLE IF NOT EXISTS teacher_subject ( -- vreau sa imi dea eroare cand adaug aici un profesor care nu exista in tabela teachers
    idProfesor INTEGER REFERENCES teachers(id) ON DELETE CASCADE ON UPDATE CASCADE,
    idMaterie INTEGER REFERENCES subjects(id) ON DELETE CASCADE ON UPDATE CASCADE,
    idGrupa INTEGER REFERENCES classes(id) ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY (idProfesor, idMaterie, idGrupa) 
);


-- CREATE TABLE IF NOT EXISTS admins (
--     id INTEGER PRIMAY KEY users(id) REFERENCES users(id)
-- );


-- CREATE TABLE IF NOT EXISTS grades (
--     id INTEGER PRIMARY KEY AUTOINCREMENT, 
--     idStudent INTEGER REFERENCES students(id) NOT NULL ON DELETE CASCADE ON UPDATE CASCADE,
--     idMaterie INTEGER REFERENCES subjects(id) NOT NULL ON DELETE CASCADE ON UPDATE CASCADE,
--     nota REAL NOT NULL
-- );



    


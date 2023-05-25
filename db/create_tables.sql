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
    idProfesor INTEGER,
    idMaterie INTEGER,
    idGrupa INTEGER,
    PRIMARY KEY (idProfesor, idMaterie, idGrupa),
    FOREIGN KEY (idProfesor) REFERENCES teachers(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (idMaterie) REFERENCES subjects(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (idGrupa) REFERENCES classes(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS student_subject (
    idStudent INTEGER,
    idMaterie INTEGER,
    PRIMARY KEY (idStudent, idMaterie),
    FOREIGN KEY (idStudent) REFERENCES students(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (idMaterie) REFERENCES subjects(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS grades (
    id INTEGER PRIMARY KEY AUTOINCREMENT, 
    idStudent INTEGER REFERENCES students(id),
    idMaterie INTEGER REFERENCES subjects(id),
    nota REAL NOT NULL,
    date TEXT
);


-- CREATE TABLE IF NOT EXISTS admins (
--     id INTEGER PRIMAY KEY users(id) REFERENCES users(id)
-- );




    


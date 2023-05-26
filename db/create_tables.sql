DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS students;
DROP TABLE IF EXISTS teachers;
DROP TABLE IF EXISTS subjects;
DROP TABLE IF EXISTS classes;
DROP TABLE IF EXISTS teacher_subject;
DROP TABLE IF EXISTS student_subject;
DROP TABLE IF EXISTS grades;


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

INSERT INTO classes (nume, an) VALUES ('151', 1);
INSERT INTO classes (nume, an) VALUES ('152', 1);
INSERT INTO classes (nume, an) VALUES ('251', 2);
INSERT INTO classes (nume, an) VALUES ('252', 2);
INSERT INTO classes (nume, an) VALUES ('351', 3);
INSERT INTO classes (nume, an) VALUES ('352', 3);

INSERT INTO subjects (nume, descriere) VALUES ('Matematica', 'Materie de baza');
INSERT INTO subjects (nume, descriere) VALUES ('Informatica', 'Materie de baza');
INSERT INTO subjects (nume, descriere) VALUES ('Fizica', 'Materie de baza');
INSERT INTO subjects (nume, descriere) VALUES ('Chimie', 'Materie de baza');
INSERT INTO subjects (nume, descriere) VALUES ('Biologie', 'Materie de baza');
INSERT INTO subjects (nume, descriere) VALUES ('Istorie', 'Materie de baza');
INSERT INTO subjects (nume, descriere) VALUES ('Geografie', 'Materie de baza');
INSERT INTO subjects (nume, descriere) VALUES ('Economie', 'Materie de baza');
INSERT INTO subjects (nume, descriere) VALUES ('Filosofie', 'Materie de baza');
INSERT INTO subjects (nume, descriere) VALUES ('Religie', 'Materie de baza');
INSERT INTO subjects (nume, descriere) VALUES ('Educatie Fizica', 'Materie de baza');

-- CREATE TABLE IF NOT EXISTS admins (
--     id INTEGER PRIMAY KEY users(id) REFERENCES users(id)
-- );




    


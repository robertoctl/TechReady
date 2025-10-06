-- Usage:
--   psql -v student_id='alice01' -f sql-practice-postgres.sql "postgres://user:pass@host:5432/db"

-- 0) psql variables → schema name
\set raw_id :student_id
\if :{?raw_id}
\else
\set raw_id student
\endif
\set schema_name 'student_' :raw_id

-- 1) Create schema + set search_path
CREATE SCHEMA IF NOT EXISTS :schema_name;
SET search_path TO :schema_name;

-- 2) Tables
CREATE TABLE IF NOT EXISTS departments (
  dept_id SERIAL PRIMARY KEY,
  name    TEXT NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS authors (
  author_id SERIAL PRIMARY KEY,
  full_name TEXT NOT NULL,
  dept_id   INTEGER REFERENCES departments(dept_id) ON UPDATE CASCADE ON DELETE SET NULL,
  h_index   INTEGER DEFAULT 0 CHECK (h_index >= 0)
);

-- ✅ widen year constraint to allow classic papers
CREATE TABLE IF NOT EXISTS papers (
  paper_id  SERIAL PRIMARY KEY,
  author_id INTEGER NOT NULL REFERENCES authors(author_id) ON UPDATE CASCADE ON DELETE CASCADE,
  title     TEXT NOT NULL,
  pub_year  INTEGER CHECK (pub_year BETWEEN 1800 AND EXTRACT(YEAR FROM now())::int)
);

CREATE TABLE IF NOT EXISTS citations (
  paper_id  INTEGER PRIMARY KEY REFERENCES papers(paper_id) ON DELETE CASCADE,
  cites     INTEGER NOT NULL CHECK (cites >= 0)
);

-- 3) Seed data (idempotent)

-- Departments
INSERT INTO departments (dept_id, name) VALUES
  (1,'Computer Science'),
  (2,'Mathematics'),
  (3,'Physics')
ON CONFLICT (dept_id) DO NOTHING;

-- Authors
INSERT INTO authors (author_id, full_name, dept_id, h_index) VALUES
  (1,'Ada Lovelace',1,45),
  (2,'Alan Turing',1,60),
  (3,'Emmy Noether',2,55),
  (4,'Marie Curie',3,70)
ON CONFLICT (author_id) DO NOTHING;

-- Papers  ✅ explicit paper_id so citations match
INSERT INTO papers (paper_id, author_id, title, pub_year) VALUES
  (1,1,'Notes on the Analytical Engine',1843),
  (2,2,'On Computable Numbers',1936),
  (3,3,'Invariants in Algebra',1921),
  (4,4,'Radioactivity of Polonium',1898),
  (5,2,'Intelligent Machinery',1948)
ON CONFLICT (paper_id) DO NOTHING;

-- Citations
INSERT INTO citations (paper_id, cites) VALUES
  (1,12000),(2,22000),(3,15000),(4,18000),(5,9000)
ON CONFLICT (paper_id) DO NOTHING;

-- 4) Ensure sequences are in sync with the max id (important after explicit ids)
SELECT setval(pg_get_serial_sequence('departments','dept_id'), COALESCE((SELECT MAX(dept_id) FROM departments), 1), true);
SELECT setval(pg_get_serial_sequence('authors','author_id'),   COALESCE((SELECT MAX(author_id) FROM authors),   1), true);
SELECT setval(pg_get_serial_sequence('papers','paper_id'),     COALESCE((SELECT MAX(paper_id) FROM papers),     1), true);

-- 5) Helpful view
CREATE OR REPLACE VIEW vw_author_papers AS
SELECT a.author_id, a.full_name, d.name AS department, a.h_index,
       p.paper_id, p.title, p.pub_year,
       COALESCE(c.cites,0) AS cites
FROM authors a
LEFT JOIN departments d ON d.dept_id = a.dept_id
LEFT JOIN papers p      ON p.author_id = a.author_id
LEFT JOIN citations c   ON c.paper_id = p.paper_id;

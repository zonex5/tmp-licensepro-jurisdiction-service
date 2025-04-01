CREATE SCHEMA IF NOT EXISTS jurisdictions;

CREATE TABLE jurisdictions.jurisdictions
(
  id               TEXT NOT NULL DEFAULT cast(gen_random_uuid() as TEXT) PRIMARY KEY,
  state            TEXT,
  state_abr        TEXT,
  state_board_url  TEXT,
  renewal_page_url TEXT
);

CREATE TABLE jurisdictions.jurisdiction_links
(
  id              TEXT NOT NULL DEFAULT cast(gen_random_uuid() as TEXT) PRIMARY KEY,
  jurisdiction_id TEXT NOT NULL,
  url             TEXT,
  position        INTEGER,
  CONSTRAINT jurisdiction_fk FOREIGN KEY (jurisdiction_id) REFERENCES jurisdictions.jurisdictions (id)
);

CREATE TABLE jurisdictions.jurisdiction_variants
(
  id              TEXT NOT NULL DEFAULT cast(gen_random_uuid() as TEXT) PRIMARY KEY,
  jurisdiction_id TEXT NOT NULL,
  variant         TEXT,
  CONSTRAINT jurisdiction_fk FOREIGN KEY (jurisdiction_id) REFERENCES jurisdictions.jurisdictions (id)
);

CREATE TABLE jurisdictions.jurisdiction_notification_checklists
(
  id              TEXT NOT NULL DEFAULT cast(gen_random_uuid() as TEXT) PRIMARY KEY,
  jurisdiction_id TEXT NOT NULL,
  text            TEXT,
  CONSTRAINT jurisdiction_fk FOREIGN KEY (jurisdiction_id) REFERENCES jurisdictions.jurisdictions (id)
);

CREATE TABLE jurisdictions.jurisdiction_plain_statues
(
  id              TEXT NOT NULL DEFAULT cast(gen_random_uuid() as TEXT) PRIMARY KEY,
  jurisdiction_id TEXT NOT NULL,
  content         TEXT,
  CONSTRAINT jurisdiction_fk FOREIGN KEY (jurisdiction_id) REFERENCES jurisdictions.jurisdictions (id)
);

CREATE TABLE jurisdictions.reqs_comparison_questions
(
  id       TEXT NOT NULL DEFAULT cast(gen_random_uuid() as TEXT) PRIMARY KEY,
  question TEXT,
  position INTEGER
);

CREATE TABLE jurisdictions.reqs_comparison_answers
(
  id              TEXT NOT NULL DEFAULT cast(gen_random_uuid() as TEXT) PRIMARY KEY,
  jurisdiction_id TEXT NOT NULL,
  question_id     TEXT NOT NULL,
  answer          BOOLEAN,
  CONSTRAINT jurisdiction_fk FOREIGN KEY (jurisdiction_id) REFERENCES jurisdictions.jurisdictions (id),
  CONSTRAINT question_fk FOREIGN KEY (question_id) REFERENCES jurisdictions.reqs_comparison_questions (id)
);

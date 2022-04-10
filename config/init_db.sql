CREATE TABLE resumes(
    uuid VARCHAR (36) PRIMARY KEY,
    full_name TEXT
);

CREATE TABLE contacts(
    id SERIAL,
    type TEXT NOT NULL,
    value TEXT NOT NULL,
    resume_uuid VARCHAR (36) NOT NULL REFERENCES resumes (uuid) ON DELETE CASCADE
);

CREATE  UNIQUE INDEX contacts_uuid_type_idx ON contacts(resume_uuid, type);

ALTER TABLE resumes
ALTER COLUMN uuid
SET NOT NULL,
ALTER COLUMN full_name
SET NOT NULL;

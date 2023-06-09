CREATE SCHEMA IF NOT EXISTS DEV;

GRANT ALL PRIVILEGES ON DATABASE DEV TO DEV;

CREATE TABLE IF NOT EXISTS DEV."person"  (
    "id" INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    "username" VARCHAR(100) NOT NULL, "year_of_birth" INTEGER NOT NULL,
    "password" VARCHAR NOT NULL,
    "role" VARCHAR(50) NOT NULL,
    CONSTRAINT "person_pkey" PRIMARY KEY ("id")
);
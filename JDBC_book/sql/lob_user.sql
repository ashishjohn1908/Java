-- The SQL*Plus script lob_user.sql performs the following:
--   1. Creates lob_user
--   2. Creates the database objects
--   3. Populates the database tables with example data

-- This script should be run by the system user (or the DBA)
CONNECT system/London92;

-- drop lob_user
DROP USER lob_user CASCADE;

-- create lob_user
CREATE USER lob_user IDENTIFIED BY lob_password;

-- allow the user to connect, create database objects and
-- create directory objects (for the BFILEs)
GRANT connect, resource, create any directory TO lob_user;
GRANT UNLIMITED TABLESPACE TO lob_user;

-- connect as lob_user
CONNECT lob_user/lob_password;

-- create the tables
CREATE TABLE clob_content (
  file_name   VARCHAR2(40) NOT NULL,
  clob_column CLOB NOT NULL
);

CREATE TABLE blob_content (
  file_name   VARCHAR2(40) NOT NULL,
  blob_column BLOB NOT NULL
);

CREATE TABLE bfile_content (
  file_name    VARCHAR2(40) NOT NULL,
  bfile_column BFILE NOT NULL
);

CREATE TABLE long_content (
  file_name   VARCHAR2(40) NOT NULL,
  long_column LONG NOT NULL
);

CREATE TABLE long_raw_content (
  file_name       VARCHAR2(40) NOT NULL,
  long_raw_column LONG RAW NOT NULL
);

-- create the BFILE directory
CREATE OR REPLACE DIRECTORY SAMPLE_FILES_DIR AS 'C:\sample_files';
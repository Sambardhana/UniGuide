BEGIN;

-- ============================================================
-- UniGuide - CUTM Knowledge Database
-- Migration: V2__cutm_knowledge_schema.sql
--
-- Purpose:
-- Create the comprehensive CUTM knowledge layer.
--
-- Existing UniGuide tables are NOT modified or deleted.
--
-- Existing tables:
-- activities
-- campus_locations
-- clubs
-- contacts
-- courses
-- departments
-- events
-- facilities
-- hostels
-- notices
-- users
--
-- This migration only creates the new knowledge tables.
-- ============================================================


-- ============================================================
-- 1. UNIVERSITY
-- ============================================================

CREATE TABLE IF NOT EXISTS university (
    id BIGSERIAL PRIMARY KEY,

    name VARCHAR(255) NOT NULL,
    short_name VARCHAR(100),

    description TEXT,

    website_url VARCHAR(500),
    admission_url VARCHAR(500),
    contact_url VARCHAR(500),

    headquarters TEXT,

    email VARCHAR(255),
    phone VARCHAR(100),

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);


-- ============================================================
-- 2. KNOWLEDGE SOURCES
-- ============================================================

CREATE TABLE IF NOT EXISTS knowledge_sources (
    id BIGSERIAL PRIMARY KEY,

    title VARCHAR(500) NOT NULL,

    source_type VARCHAR(100) NOT NULL,

    url VARCHAR(1000),

    description TEXT,

    academic_year VARCHAR(50),

    retrieved_at TIMESTAMPTZ,

    is_official BOOLEAN NOT NULL DEFAULT TRUE,

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);


-- ============================================================
-- 3. CAMPUSES
-- ============================================================

CREATE TABLE IF NOT EXISTS campuses (
    id BIGSERIAL PRIMARY KEY,

    university_id BIGINT NOT NULL,

    name VARCHAR(255) NOT NULL,

    city VARCHAR(150),
    state VARCHAR(150),
    country VARCHAR(100) DEFAULT 'India',

    address TEXT,

    description TEXT,

    latitude NUMERIC(10,7),
    longitude NUMERIC(10,7),

    phone VARCHAR(100),
    email VARCHAR(255),

    website_url VARCHAR(500),

    official_source_id BIGINT,

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_campus_university
        FOREIGN KEY (university_id)
        REFERENCES university(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_campus_source
        FOREIGN KEY (official_source_id)
        REFERENCES knowledge_sources(id)
        ON DELETE SET NULL
);


-- ============================================================
-- 4. SCHOOLS
-- ============================================================

CREATE TABLE IF NOT EXISTS schools (
    id BIGSERIAL PRIMARY KEY,

    university_id BIGINT NOT NULL,

    name VARCHAR(255) NOT NULL,

    short_name VARCHAR(100),

    description TEXT,

    dean_name VARCHAR(255),

    email VARCHAR(255),
    phone VARCHAR(100),

    website_url VARCHAR(500),

    official_source_id BIGINT,

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_school_university
        FOREIGN KEY (university_id)
        REFERENCES university(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_school_source
        FOREIGN KEY (official_source_id)
        REFERENCES knowledge_sources(id)
        ON DELETE SET NULL
);


-- ============================================================
-- 5. SCHOOL - CAMPUS RELATIONSHIP
-- ============================================================

CREATE TABLE IF NOT EXISTS school_campuses (
    id BIGSERIAL PRIMARY KEY,

    school_id BIGINT NOT NULL,
    campus_id BIGINT NOT NULL,

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT uq_school_campus
        UNIQUE (school_id, campus_id),

    CONSTRAINT fk_school_campus_school
        FOREIGN KEY (school_id)
        REFERENCES schools(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_school_campus_campus
        FOREIGN KEY (campus_id)
        REFERENCES campuses(id)
        ON DELETE CASCADE
);


-- ============================================================
-- 6. PROGRAMS
-- ============================================================

CREATE TABLE IF NOT EXISTS programs (
    id BIGSERIAL PRIMARY KEY,

    school_id BIGINT,

    name VARCHAR(500) NOT NULL,

    short_name VARCHAR(150),

    program_level VARCHAR(100),

    degree_type VARCHAR(150),

    duration VARCHAR(100),

    description TEXT,

    code VARCHAR(150),

    website_url VARCHAR(500),

    official_source_id BIGINT,

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_program_school
        FOREIGN KEY (school_id)
        REFERENCES schools(id)
        ON DELETE SET NULL,

    CONSTRAINT fk_program_source
        FOREIGN KEY (official_source_id)
        REFERENCES knowledge_sources(id)
        ON DELETE SET NULL
);


-- ============================================================
-- 7. PROGRAM - CAMPUS RELATIONSHIP
-- ============================================================

CREATE TABLE IF NOT EXISTS program_campuses (
    id BIGSERIAL PRIMARY KEY,

    program_id BIGINT NOT NULL,
    campus_id BIGINT NOT NULL,

    academic_year VARCHAR(50),

    is_active BOOLEAN NOT NULL DEFAULT TRUE,

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT uq_program_campus_year
        UNIQUE (program_id, campus_id, academic_year),

    CONSTRAINT fk_program_campus_program
        FOREIGN KEY (program_id)
        REFERENCES programs(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_program_campus_campus
        FOREIGN KEY (campus_id)
        REFERENCES campuses(id)
        ON DELETE CASCADE
);


-- ============================================================
-- 8. PROGRAM ELIGIBILITY
-- ============================================================

CREATE TABLE IF NOT EXISTS program_eligibility (
    id BIGSERIAL PRIMARY KEY,

    program_id BIGINT NOT NULL,

    academic_year VARCHAR(50),

    eligibility_text TEXT NOT NULL,

    minimum_percentage NUMERIC(5,2),

    entrance_exam VARCHAR(255),

    additional_requirements TEXT,

    official_source_id BIGINT,

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_eligibility_program
        FOREIGN KEY (program_id)
        REFERENCES programs(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_eligibility_source
        FOREIGN KEY (official_source_id)
        REFERENCES knowledge_sources(id)
        ON DELETE SET NULL
);


-- ============================================================
-- 9. PROGRAM FEES
-- ============================================================

CREATE TABLE IF NOT EXISTS program_fees (
    id BIGSERIAL PRIMARY KEY,

    program_id BIGINT NOT NULL,
    campus_id BIGINT,

    academic_year VARCHAR(50) NOT NULL,

    fee_type VARCHAR(150) NOT NULL,

    amount NUMERIC(12,2) NOT NULL,

    currency VARCHAR(10) NOT NULL DEFAULT 'INR',

    frequency VARCHAR(100),

    notes TEXT,

    official_source_id BIGINT,

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_program_fee_program
        FOREIGN KEY (program_id)
        REFERENCES programs(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_program_fee_campus
        FOREIGN KEY (campus_id)
        REFERENCES campuses(id)
        ON DELETE SET NULL,

    CONSTRAINT fk_program_fee_source
        FOREIGN KEY (official_source_id)
        REFERENCES knowledge_sources(id)
        ON DELETE SET NULL
);


-- ============================================================
-- 10. ADDITIONAL FEES
-- ============================================================

CREATE TABLE IF NOT EXISTS additional_fees (
    id BIGSERIAL PRIMARY KEY,

    campus_id BIGINT,

    academic_year VARCHAR(50) NOT NULL,

    fee_name VARCHAR(255) NOT NULL,

    description TEXT,

    amount NUMERIC(12,2),

    currency VARCHAR(10) NOT NULL DEFAULT 'INR',

    frequency VARCHAR(100),

    applicable_to VARCHAR(255),

    official_source_id BIGINT,

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_additional_fee_campus
        FOREIGN KEY (campus_id)
        REFERENCES campuses(id)
        ON DELETE SET NULL,

    CONSTRAINT fk_additional_fee_source
        FOREIGN KEY (official_source_id)
        REFERENCES knowledge_sources(id)
        ON DELETE SET NULL
);


-- ============================================================
-- 11. ADMISSIONS
-- ============================================================

CREATE TABLE IF NOT EXISTS admissions (
    id BIGSERIAL PRIMARY KEY,

    campus_id BIGINT,
    program_id BIGINT,

    academic_year VARCHAR(50),

    title VARCHAR(500) NOT NULL,

    description TEXT,

    application_start_date DATE,
    application_end_date DATE,

    entrance_exam VARCHAR(255),

    application_process TEXT,

    eligibility TEXT,

    application_url VARCHAR(1000),

    official_source_id BIGINT,

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_admission_campus
        FOREIGN KEY (campus_id)
        REFERENCES campuses(id)
        ON DELETE SET NULL,

    CONSTRAINT fk_admission_program
        FOREIGN KEY (program_id)
        REFERENCES programs(id)
        ON DELETE SET NULL,

    CONSTRAINT fk_admission_source
        FOREIGN KEY (official_source_id)
        REFERENCES knowledge_sources(id)
        ON DELETE SET NULL
);


-- ============================================================
-- 12. SCHOLARSHIPS
-- ============================================================

CREATE TABLE IF NOT EXISTS scholarships (
    id BIGSERIAL PRIMARY KEY,

    university_id BIGINT,

    name VARCHAR(500) NOT NULL,

    academic_year VARCHAR(50),

    description TEXT,

    eligibility TEXT,

    benefit TEXT,

    percentage_or_amount VARCHAR(255),

    application_process TEXT,

    application_url VARCHAR(1000),

    official_source_id BIGINT,

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_scholarship_university
        FOREIGN KEY (university_id)
        REFERENCES university(id)
        ON DELETE SET NULL,

    CONSTRAINT fk_scholarship_source
        FOREIGN KEY (official_source_id)
        REFERENCES knowledge_sources(id)
        ON DELETE SET NULL
);


-- ============================================================
-- 13. ACADEMIC CALENDARS
-- ============================================================

CREATE TABLE IF NOT EXISTS academic_calendars (
    id BIGSERIAL PRIMARY KEY,

    university_id BIGINT,

    campus_id BIGINT,

    school_id BIGINT,

    academic_year VARCHAR(50) NOT NULL,

    program_level VARCHAR(100),

    year_or_semester VARCHAR(100),

    title VARCHAR(500) NOT NULL,

    description TEXT,

    start_date DATE,
    end_date DATE,

    document_url VARCHAR(1000),

    official_source_id BIGINT,

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_calendar_university
        FOREIGN KEY (university_id)
        REFERENCES university(id)
        ON DELETE SET NULL,

    CONSTRAINT fk_calendar_campus
        FOREIGN KEY (campus_id)
        REFERENCES campuses(id)
        ON DELETE SET NULL,

    CONSTRAINT fk_calendar_school
        FOREIGN KEY (school_id)
        REFERENCES schools(id)
        ON DELETE SET NULL,

    CONSTRAINT fk_calendar_source
        FOREIGN KEY (official_source_id)
        REFERENCES knowledge_sources(id)
        ON DELETE SET NULL
);


-- ============================================================
-- 14. HOSTEL DETAILS
-- ============================================================

CREATE TABLE IF NOT EXISTS hostel_details (
    id BIGSERIAL PRIMARY KEY,

    campus_id BIGINT,

    name VARCHAR(255) NOT NULL,

    hostel_type VARCHAR(100),

    gender VARCHAR(50),

    capacity INTEGER,

    description TEXT,

    facilities TEXT,

    warden_name VARCHAR(255),

    warden_contact VARCHAR(100),

    fee_amount NUMERIC(12,2),

    fee_academic_year VARCHAR(50),

    location_description TEXT,

    official_source_id BIGINT,

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_hostel_detail_campus
        FOREIGN KEY (campus_id)
        REFERENCES campuses(id)
        ON DELETE SET NULL,

    CONSTRAINT fk_hostel_detail_source
        FOREIGN KEY (official_source_id)
        REFERENCES knowledge_sources(id)
        ON DELETE SET NULL
);


-- ============================================================
-- 15. FACILITY DETAILS
-- ============================================================

CREATE TABLE IF NOT EXISTS facility_details (
    id BIGSERIAL PRIMARY KEY,

    campus_id BIGINT,

    name VARCHAR(500) NOT NULL,

    facility_type VARCHAR(150),

    description TEXT,

    facilities TEXT,

    opening_hours VARCHAR(255),

    contact_number VARCHAR(100),

    email VARCHAR(255),

    location_description TEXT,

    official_source_id BIGINT,

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_facility_detail_campus
        FOREIGN KEY (campus_id)
        REFERENCES campuses(id)
        ON DELETE SET NULL,

    CONSTRAINT fk_facility_detail_source
        FOREIGN KEY (official_source_id)
        REFERENCES knowledge_sources(id)
        ON DELETE SET NULL
);


-- ============================================================
-- 16. PLACEMENTS
-- ============================================================

CREATE TABLE IF NOT EXISTS placements (
    id BIGSERIAL PRIMARY KEY,

    university_id BIGINT,

    campus_id BIGINT,

    school_id BIGINT,

    academic_year VARCHAR(50),

    title VARCHAR(500) NOT NULL,

    description TEXT,

    highest_package NUMERIC(14,2),

    average_package NUMERIC(14,2),

    median_package NUMERIC(14,2),

    total_recruiters INTEGER,

    total_offers INTEGER,

    placement_percentage NUMERIC(5,2),

    recruiters TEXT,

    official_source_id BIGINT,

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_placement_university
        FOREIGN KEY (university_id)
        REFERENCES university(id)
        ON DELETE SET NULL,

    CONSTRAINT fk_placement_campus
        FOREIGN KEY (campus_id)
        REFERENCES campuses(id)
        ON DELETE SET NULL,

    CONSTRAINT fk_placement_school
        FOREIGN KEY (school_id)
        REFERENCES schools(id)
        ON DELETE SET NULL,

    CONSTRAINT fk_placement_source
        FOREIGN KEY (official_source_id)
        REFERENCES knowledge_sources(id)
        ON DELETE SET NULL
);


-- ============================================================
-- 17. RESEARCH
-- ============================================================

CREATE TABLE IF NOT EXISTS research (
    id BIGSERIAL PRIMARY KEY,

    university_id BIGINT,

    school_id BIGINT,

    title VARCHAR(500) NOT NULL,

    category VARCHAR(150),

    description TEXT,

    researcher_name VARCHAR(255),

    department VARCHAR(255),

    research_area VARCHAR(500),

    website_url VARCHAR(1000),

    official_source_id BIGINT,

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_research_university
        FOREIGN KEY (university_id)
        REFERENCES university(id)
        ON DELETE SET NULL,

    CONSTRAINT fk_research_school
        FOREIGN KEY (school_id)
        REFERENCES schools(id)
        ON DELETE SET NULL,

    CONSTRAINT fk_research_source
        FOREIGN KEY (official_source_id)
        REFERENCES knowledge_sources(id)
        ON DELETE SET NULL
);


-- ============================================================
-- 18. ENTREPRENEURSHIP
-- ============================================================

CREATE TABLE IF NOT EXISTS entrepreneurship (
    id BIGSERIAL PRIMARY KEY,

    university_id BIGINT,

    campus_id BIGINT,

    title VARCHAR(500) NOT NULL,

    category VARCHAR(150),

    description TEXT,

    eligibility TEXT,

    facilities TEXT,

    contact_name VARCHAR(255),

    contact_email VARCHAR(255),

    contact_phone VARCHAR(100),

    website_url VARCHAR(1000),

    official_source_id BIGINT,

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_entrepreneurship_university
        FOREIGN KEY (university_id)
        REFERENCES university(id)
        ON DELETE SET NULL,

    CONSTRAINT fk_entrepreneurship_campus
        FOREIGN KEY (campus_id)
        REFERENCES campuses(id)
        ON DELETE SET NULL,

    CONSTRAINT fk_entrepreneurship_source
        FOREIGN KEY (official_source_id)
        REFERENCES knowledge_sources(id)
        ON DELETE SET NULL
);


-- ============================================================
-- 19. INTERNATIONAL PROGRAMS
-- ============================================================

CREATE TABLE IF NOT EXISTS international_programs (
    id BIGSERIAL PRIMARY KEY,

    university_id BIGINT,

    title VARCHAR(500) NOT NULL,

    partner_institution VARCHAR(500),

    country VARCHAR(150),

    program_type VARCHAR(150),

    description TEXT,

    eligibility TEXT,

    duration VARCHAR(100),

    application_process TEXT,

    website_url VARCHAR(1000),

    official_source_id BIGINT,

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_international_university
        FOREIGN KEY (university_id)
        REFERENCES university(id)
        ON DELETE SET NULL,

    CONSTRAINT fk_international_source
        FOREIGN KEY (official_source_id)
        REFERENCES knowledge_sources(id)
        ON DELETE SET NULL
);


-- ============================================================
-- 20. OFFICIAL DOCUMENTS
-- ============================================================

CREATE TABLE IF NOT EXISTS official_documents (
    id BIGSERIAL PRIMARY KEY,

    university_id BIGINT,

    campus_id BIGINT,

    school_id BIGINT,

    title VARCHAR(500) NOT NULL,

    document_type VARCHAR(150),

    academic_year VARCHAR(50),

    description TEXT,

    document_url VARCHAR(1000) NOT NULL,

    published_date DATE,

    official_source_id BIGINT,

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_document_university
        FOREIGN KEY (university_id)
        REFERENCES university(id)
        ON DELETE SET NULL,

    CONSTRAINT fk_document_campus
        FOREIGN KEY (campus_id)
        REFERENCES campuses(id)
        ON DELETE SET NULL,

    CONSTRAINT fk_document_school
        FOREIGN KEY (school_id)
        REFERENCES schools(id)
        ON DELETE SET NULL,

    CONSTRAINT fk_document_source
        FOREIGN KEY (official_source_id)
        REFERENCES knowledge_sources(id)
        ON DELETE SET NULL
);


-- ============================================================
-- 21. KNOWLEDGE FACTS
-- ============================================================

CREATE TABLE IF NOT EXISTS knowledge_facts (
    id BIGSERIAL PRIMARY KEY,

    university_id BIGINT,

    campus_id BIGINT,

    school_id BIGINT,

    category VARCHAR(150) NOT NULL,

    subject VARCHAR(500),

    fact_key VARCHAR(255) NOT NULL,

    fact_value TEXT NOT NULL,

    academic_year VARCHAR(50),

    official_source_id BIGINT,

    verified BOOLEAN NOT NULL DEFAULT FALSE,

    verified_at TIMESTAMPTZ,

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_fact_university
        FOREIGN KEY (university_id)
        REFERENCES university(id)
        ON DELETE SET NULL,

    CONSTRAINT fk_fact_campus
        FOREIGN KEY (campus_id)
        REFERENCES campuses(id)
        ON DELETE SET NULL,

    CONSTRAINT fk_fact_school
        FOREIGN KEY (school_id)
        REFERENCES schools(id)
        ON DELETE SET NULL,

    CONSTRAINT fk_fact_source
        FOREIGN KEY (official_source_id)
        REFERENCES knowledge_sources(id)
        ON DELETE SET NULL
);


-- ============================================================
-- INDEXES
-- ============================================================

CREATE INDEX IF NOT EXISTS idx_campuses_university
    ON campuses(university_id);

CREATE INDEX IF NOT EXISTS idx_schools_university
    ON schools(university_id);

CREATE INDEX IF NOT EXISTS idx_programs_school
    ON programs(school_id);

CREATE INDEX IF NOT EXISTS idx_program_campuses_program
    ON program_campuses(program_id);

CREATE INDEX IF NOT EXISTS idx_program_campuses_campus
    ON program_campuses(campus_id);

CREATE INDEX IF NOT EXISTS idx_program_fees_program
    ON program_fees(program_id);

CREATE INDEX IF NOT EXISTS idx_program_fees_campus
    ON program_fees(campus_id);

CREATE INDEX IF NOT EXISTS idx_admissions_program
    ON admissions(program_id);

CREATE INDEX IF NOT EXISTS idx_academic_calendars_year
    ON academic_calendars(academic_year);

CREATE INDEX IF NOT EXISTS idx_hostel_details_campus
    ON hostel_details(campus_id);

CREATE INDEX IF NOT EXISTS idx_facility_details_campus
    ON facility_details(campus_id);

CREATE INDEX IF NOT EXISTS idx_placements_year
    ON placements(academic_year);

CREATE INDEX IF NOT EXISTS idx_knowledge_facts_category
    ON knowledge_facts(category);

CREATE INDEX IF NOT EXISTS idx_knowledge_facts_key
    ON knowledge_facts(fact_key);

CREATE INDEX IF NOT EXISTS idx_knowledge_sources_type
    ON knowledge_sources(source_type);


-- ============================================================
-- INITIAL UNIVERSITY RECORD
-- ============================================================

INSERT INTO university (
    name,
    short_name,
    website_url,
    description
)
SELECT
    'Centurion University of Technology and Management',
    'CUTM',
    'https://cutm.ac.in/',
    'University-level knowledge record for official Centurion University of Technology and Management information.'
WHERE NOT EXISTS (
    SELECT 1
    FROM university
    WHERE short_name = 'CUTM'
);


-- ============================================================
-- COMPLETE
-- ============================================================

COMMIT;
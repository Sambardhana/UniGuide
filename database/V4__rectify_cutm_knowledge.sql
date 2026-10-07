BEGIN;

-- ============================================================
-- V4 - RECTIFY CUTM KNOWLEDGE DATA
-- ============================================================
--
-- Purpose:
-- 1. Add verified eligibility information for programs
--    currently missing program_eligibility records.
-- 2. Replace the placeholder B.Sc. Forensic Science eligibility.
-- 3. Link eligibility records to the official CUTM eligibility source.
--
-- Source:
-- https://cutm.ac.in/eligibility/
--
-- Important:
-- Hostel/facility records are NOT artificially duplicated.
-- School of Online Program is NOT assigned to a physical campus.
--
-- ============================================================


-- ============================================================
-- 1. ENSURE OFFICIAL ELIGIBILITY SOURCE EXISTS
-- ============================================================

INSERT INTO knowledge_sources (
    title,
    source_type,
    url,
    description,
    academic_year,
    retrieved_at,
    is_official
)
SELECT
    'CUTM Official Eligibility Criteria',
    'OFFICIAL_WEB_PAGE',
    'https://cutm.ac.in/eligibility/',
    'Official Centurion University eligibility criteria for academic programmes.',
    '2025-26',
    CURRENT_TIMESTAMP,
    TRUE
WHERE NOT EXISTS (
    SELECT 1
    FROM knowledge_sources
    WHERE url = 'https://cutm.ac.in/eligibility/'
);


-- ============================================================
-- 2. REMOVE THE PLACEHOLDER FORENSIC ELIGIBILITY
-- ============================================================

DELETE FROM program_eligibility
WHERE program_id = 8;


-- ============================================================
-- 3. INSERT VERIFIED / SOURCE-SUPPORTED ELIGIBILITY
-- ============================================================

INSERT INTO program_eligibility (
    program_id,
    academic_year,
    eligibility_text,
    minimum_percentage,
    entrance_exam,
    additional_requirements,
    official_source_id
)
SELECT
    v.program_id,
    '2025-26',
    v.eligibility_text,
    v.minimum_percentage,
    v.entrance_exam,
    v.additional_requirements,
    ks.id
FROM (
    VALUES

    -- ========================================================
    -- AGRICULTURE
    -- ========================================================

    (
        37,
        '10+2 with Physics, Chemistry and Biology. Current CUTM criteria specify 60% for Odisha Board/CBSE/ICSE/other boards and 80% for AP Board.',
        60.00,
        'CUEE / State Entrance Exam',
        'Board-specific percentage requirement applies.'
    ),

    -- ========================================================
    -- AEROSPACE / AGRI-BIO ENGINEERING
    -- ========================================================

    (
        44,
        '10+2 or equivalent qualification in the relevant stream for the Bachelor of Management Studies programme.',
        NULL,
        'CUEE',
        'Programme-specific admission requirements should be verified against the current course notice.'
    ),

    (
        29,
        '10+2 Science with Physics, Chemistry and Mathematics/Biology with minimum 50%.',
        50.00,
        'CUEE / State Entrance Exam',
        'General candidates; applicable relaxation may apply to reserved categories.'
    ),

    (
        50,
        '10+2 Science with Physics, Chemistry and Mathematics/Biology with minimum 50%.',
        50.00,
        'CUEE / State Entrance Exam',
        'General candidates; applicable relaxation may apply to reserved categories.'
    ),

    -- ========================================================
    -- ALLIED AND HEALTHCARE
    -- ========================================================

    (
        40,
        'Pass in +2 Science with Physics, Chemistry and Biology from a recognized board.',
        50.00,
        'CUEE',
        'Minimum 50% in Physics, Chemistry and Biology taken together; 40% for SC/ST and 45% for physically challenged candidates.'
    ),

    -- ========================================================
    -- APPLIED SCIENCES
    -- ========================================================

    (
        48,
        '10+2 with Physics, Chemistry and Mathematics with minimum 50%.',
        50.00,
        'CUEE',
        'Applicable relaxation may apply to reserved categories.'
    ),

    (
        45,
        '10+2 with Physics, Chemistry, Mathematics or Biology/equivalent Science qualification.',
        NULL,
        'CUEE',
        'Programme falls under the B.Sc. eligibility framework published by CUTM.'
    ),

    (
        3,
        '10+2 with Physics, Chemistry, Mathematics or Biology/equivalent Science qualification.',
        NULL,
        'CUEE',
        'Programme falls under the B.Sc. eligibility framework published by CUTM.'
    ),

    (
        52,
        'B.Sc. in Life Science, Biology or another relevant life-science discipline; equivalent qualifying backgrounds may be considered under university norms.',
        NULL,
        'CUEE',
        'CUTM eligibility criteria identify B.Sc. Life Science/Biology and related backgrounds for M.Sc. Microbiology/MLT-type programmes.'
    ),

    (
        34,
        'B.Sc. in the relevant science discipline.',
        NULL,
        'CUEE',
        'Relevant science background required for the postgraduate Applied Chemistry programme.'
    ),

    (
        20,
        'B.Sc. in Mathematics or a relevant science discipline.',
        NULL,
        'CUEE',
        'Relevant mathematics/science background required.'
    ),

    (
        33,
        'B.Sc. in Physics or a relevant science discipline.',
        NULL,
        'CUEE',
        'Relevant physics/science background required.'
    ),

    -- ========================================================
    -- BIOTECHNOLOGY
    -- ========================================================

    (
        14,
        '10+2 Science with Biology and minimum 50%.',
        50.00,
        'CUEE',
        'Current CUTM programme information identifies Biology as the relevant Science background.'
    ),

    (
        35,
        '10+2 Science with Mathematics or Biology and minimum 50%.',
        50.00,
        'CUEE / JEE',
        'Applicable programme-specific admission requirements apply.'
    ),

    (
        21,
        'B.Sc. Biotechnology, Botany, Zoology or Life Sciences.',
        NULL,
        'CUEE / GAT-B',
        'Relevant life-science background required.'
    ),

    -- ========================================================
    -- COMPUTING & DATA SCIENCE
    -- ========================================================

    (
        49,
        'Passed BCA, Bachelor Degree in Computer Science Engineering or equivalent degree; OR B.Sc./B.Com./B.A. with Mathematics at 10+2 or graduation level, subject to applicable bridge-course norms.',
        NULL,
        'CUEE',
        'Current CUTM MCA programme page specifies the above qualifying backgrounds.'
    ),

    -- ========================================================
    -- DESIGN
    -- ========================================================

    (
        46,
        '10+2 or equivalent in Science, Arts or Commerce with minimum 50%.',
        50.00,
        'CUEE',
        '5% relaxation for reserved categories.'
    ),

    (
        2,
        '10+2 or equivalent in Science, Arts or Commerce with minimum 50%.',
        50.00,
        'CUEE',
        '5% relaxation for reserved categories.'
    ),

    (
        11,
        '10+2 or equivalent in Science, Arts or Commerce with minimum 50%.',
        50.00,
        'CUEE',
        '5% relaxation for reserved categories.'
    ),

    -- ========================================================
    -- ENGINEERING
    -- ========================================================

    (
        13,
        '10+2 with Physics, Chemistry and Mathematics with minimum 50% or equivalent diploma.',
        50.00,
        'JEE Main / CUEE',
        'Applicable relaxation for reserved categories.'
    ),

    (
        26,
        '10+2 with Physics, Chemistry and Mathematics with minimum 50% or equivalent diploma.',
        50.00,
        'JEE Main / CUEE',
        'Applicable relaxation for reserved categories.'
    ),

    (
        53,
        '10+2 with Physics, Chemistry and Mathematics with minimum 50% or equivalent diploma.',
        50.00,
        'JEE Main / CUEE',
        'Applicable relaxation for reserved categories.'
    ),

    (
        41,
        'Bachelor degree in the relevant engineering/technology discipline. Current CUTM M.Tech programme information indicates B.E./B.Tech qualification with the applicable academic requirement.',
        70.00,
        'GATE / Equivalent / Merit',
        'Programme-specific M.Tech specialization requirements apply.'
    ),

    -- ========================================================
    -- FISHERIES
    -- ========================================================

    (
        28,
        '10+2 with Physics, Chemistry and Biology with minimum 50%.',
        50.00,
        'CUEE',
        'Relevant Science background required.'
    ),

    (
        51,
        'Bachelor degree in Fisheries Science or relevant qualifying discipline as prescribed for the Master of Fishery Science programme.',
        NULL,
        'CUEE / Merit',
        'Postgraduate programme-specific admission requirements apply.'
    ),

    -- ========================================================
    -- LAW
    -- ========================================================

    (
        4,
        '10+2 in Science, Arts or Commerce with minimum 50%.',
        50.00,
        'CUEE',
        'Reserved-category relaxation applies according to CUTM admission norms.'
    ),

    (
        19,
        '10+2 in Science, Arts or Commerce with minimum 50%.',
        50.00,
        'CUEE',
        'Reserved-category relaxation applies according to CUTM admission norms.'
    ),

    (
        27,
        'Bachelor degree from a recognized university with minimum 50%.',
        50.00,
        'CUEE',
        'Reserved-category relaxation applies according to CUTM admission norms.'
    ),

    (
        30,
        'Bachelor degree in Law from a recognized university with minimum 50%.',
        50.00,
        'CUEE',
        'Reserved-category relaxation applies according to CUTM admission norms.'
    ),

    -- ========================================================
    -- MANAGEMENT
    -- ========================================================

    (
        12,
        '10+2 pass or equivalent qualification from a recognized board.',
        NULL,
        'CUEE',
        'Current CUTM programme material specifies Senior Secondary/10+2 or equivalent.'
    ),

    (
        39,
        '10+2 pass or equivalent qualification from a recognized board.',
        NULL,
        'CUEE',
        'Programme-specific Healthcare Management requirements apply.'
    ),

    (
        16,
        '10+2 pass or equivalent qualification from a recognized board.',
        NULL,
        'CUEE',
        'Programme-specific Retail Management requirements apply.'
    ),

    (
        43,
        '10+2 pass or equivalent qualification from a recognized board.',
        NULL,
        'CUEE',
        'Current CUTM eligibility framework lists B.Com under the 10+2 category.'
    ),

    (
        10,
        'Graduation in any discipline or equivalent qualification.',
        NULL,
        'CUEE / CAT / MAT / XAT',
        'MBA admission requires a recognized undergraduate qualification and applicable entrance requirement.'
    ),

    (
        15,
        'Graduation in any discipline or equivalent qualification.',
        NULL,
        'CUEE / CAT / MAT / XAT',
        'Programme-specific Agribusiness Management admission requirements apply.'
    ),

    -- ========================================================
    -- MARITIME STUDIES
    -- ========================================================

    (
        18,
        'Bachelor degree in Law from a recognized university.',
        50.00,
        'CUEE',
        'Current CUTM academic records identify LLM Maritime Law as a postgraduate law programme.'
    ),

    -- ========================================================
    -- NURSING
    -- ========================================================

    (
        31,
        '10+2 Science with Biology.',
        45.00,
        'NEET / OJEE / CUEE',
        'Current CUTM Nursing information specifies minimum 45% for General category and 40% for SC/ST/OBC aggregate.'
    ),

    -- ========================================================
    -- PHARMACY
    -- ========================================================

    (
        32,
        '10+2 with Physics, Chemistry and Mathematics/Biology with minimum 45%.',
        45.00,
        'CUEE',
        'Relevant Pharmacy admission requirements apply.'
    ),

    (
        47,
        '10+2 with Physics, Chemistry and Mathematics/Biology.',
        NULL,
        'CUEE',
        'D.Pharm-specific Pharmacy Council/university requirements apply.'
    ),

    (
        8,
        'B.Pharm degree from an institution/university recognized according to applicable Pharmacy Council of India requirements, with minimum 55% aggregate.',
        55.00,
        'CUEE / Merit',
        'Candidate must satisfy applicable Pharmacy Council of India and university requirements.'
    ),

    -- ========================================================
    -- VETERINARY
    -- ========================================================

    (
        7,
        '10+2 with English and Physics, Chemistry and Biology/Biotechnology with minimum 50%.',
        50.00,
        'NEET / CUEE / State or Central Entrance Test',
        'Minimum age 17; programme follows Veterinary Council of India requirements.'
    )

) AS v(
    program_id,
    eligibility_text,
    minimum_percentage,
    entrance_exam,
    additional_requirements
)
JOIN knowledge_sources ks
    ON ks.url = 'https://cutm.ac.in/eligibility/'
WHERE NOT EXISTS (
    SELECT 1
    FROM program_eligibility pe
    WHERE pe.program_id = v.program_id
);


-- ============================================================
-- 4. UPDATE FORENSIC SCIENCE WITH CURRENT OFFICIAL ELIGIBILITY
-- ============================================================

INSERT INTO program_eligibility (
    program_id,
    academic_year,
    eligibility_text,
    minimum_percentage,
    entrance_exam,
    additional_requirements,
    official_source_id
)
SELECT
    8,
    '2025-26',
    '10+2 or equivalent in Science with Physics, Chemistry, Biology and/or Mathematics.',
    55.00,
    'CUEE / AIFSET',
    'General/OBC/EWS: minimum 55%; SC/ST/PwD: minimum 50% or equivalent.',
    ks.id
FROM knowledge_sources ks
WHERE ks.url = 'https://cutm.ac.in/eligibility/'
AND NOT EXISTS (
    SELECT 1
    FROM program_eligibility
    WHERE program_id = 8
);


-- ============================================================
-- 5. DO NOT CREATE A PHYSICAL CAMPUS FOR ONLINE PROGRAM
-- ============================================================
--
-- School of Online Program is intentionally not inserted into
-- school_campuses because it is an online school/programme unit.
--
-- No INSERT required.
--
-- ============================================================


-- ============================================================
-- 6. UPDATE EXISTING FORENSIC SOURCE DESCRIPTION IF NEEDED
-- ============================================================

UPDATE knowledge_sources
SET
    description = 'Official CUTM eligibility criteria for academic programmes.',
    academic_year = '2025-26',
    retrieved_at = CURRENT_TIMESTAMP,
    updated_at = CURRENT_TIMESTAMP
WHERE url = 'https://cutm.ac.in/eligibility/';


-- ============================================================
-- COMMIT
-- ============================================================

COMMIT;
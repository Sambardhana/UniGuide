BEGIN;

-- ============================================================
-- UniGuide - CUTM Official Knowledge Seed
-- V3__cutm_official_data.sql
-- Uses the exact V2 schema supplied by the project.
--
-- Sources are official CUTM pages. Records are inserted only
-- when an equivalent record does not already exist.
-- ============================================================

-- ------------------------------------------------------------
-- 1. OFFICIAL SOURCES
-- ------------------------------------------------------------
INSERT INTO knowledge_sources
(title, source_type, url, description, academic_year, retrieved_at, is_official)
SELECT v.title, v.source_type, v.url, v.description, v.academic_year,
       CURRENT_TIMESTAMP, TRUE
FROM (VALUES
 ('CUTM Official Website','WEBSITE','https://cutm.ac.in/','Official CUTM homepage',NULL),
 ('CUTM Contact Page','WEBSITE','https://cutm.ac.in/contact/','Official campus/contact information',NULL),
 ('CUTM Course Fees','FEES','https://cutm.ac.in/course-fees/','Official programme fee information for 2026', '2026'),
 ('CUTM Admission Process','ADMISSION','https://cutm.ac.in/admission-process/','Official admission process and CUEE information','2026'),
 ('CUTM Scholarship and Loan','SCHOLARSHIP','https://cutm.ac.in/scholarship-loan/','Official scholarship information', '2026'),
 ('CUTM Residential Facilities','FACILITY','https://cutm.ac.in/residential-facilities/','Official hostel/residential information',NULL),
 ('CUTM Campus Facilities','FACILITY','https://cutm.ac.in/campus-facilities/','Official campus facilities information',NULL),
 ('CUTM Academic Calendar','ACADEMIC_CALENDAR','https://cutm.ac.in/academic-calendar/','Official academic calendar index','2026-27'),
 ('CUTM Entrepreneurship','ENTREPRENEURSHIP','https://cutm.ac.in/entrepreneurship/','Official entrepreneurship/CIE information',NULL),
 ('CUTM International Outreach','INTERNATIONAL','https://cutm.ac.in/international-outreach/','Official international outreach information',NULL),
 ('CUTM Annual Report 2024-25','ANNUAL_REPORT','https://cutm.ac.in/','Official CUTM annual-report information referenced by the university', '2024-25'),
 ('CUTM Vision and Mission','WEBSITE','https://cutm.ac.in/vision-mission/','Official vision and mission',NULL),
 ('CUTM Acts Statutes and Ordinances','DOCUMENT','https://cutm.ac.in/acts-statutes-ordinances/','Official statutory documents index',NULL),
 ('CUTM Engineering School','SCHOOL','https://cutm.ac.in/school-of-engineering-technology/','Official School of Engineering and Technology information',NULL),
 ('CUTM Applied Sciences','SCHOOL','https://cutm.ac.in/school-of-applied-sciences/','Official School of Applied Sciences information',NULL),
 ('CUTM Pharmacy','SCHOOL','https://cutm.ac.in/school-of-pharmacy/','Official Pharmacy information',NULL),
 ('CUTM Nursing','SCHOOL','https://cutm.ac.in/school-of-nursing/','Official Nursing information',NULL),
 ('CUTM Forensic Sciences','SCHOOL','https://cutm.ac.in/school-of-forensic-sciences/','Official Forensic Sciences information',NULL),
 ('CUTM Aerospace','SCHOOL','https://cutm.ac.in/school-of-aerospace-engineering-and-aviation-management/','Official Aerospace/Aviation information',NULL)
) AS v(title,source_type,url,description,academic_year)
WHERE NOT EXISTS (
    SELECT 1 FROM knowledge_sources ks WHERE ks.url = v.url
);

-- ------------------------------------------------------------
-- 2. SCHOOL <-> CAMPUS
-- ------------------------------------------------------------
INSERT INTO school_campuses (school_id, campus_id)
SELECT s.id, c.id
FROM (VALUES
 ('School of Engineering and Technology','Paralakhemundi Campus'),
 ('School of Engineering and Technology','Bhubaneswar Campus'),

 ('School of Fisheries','Paralakhemundi Campus'),

 ('School of Allied and Healthcare Sciences','Bhubaneswar Campus'),
 ('School of Allied and Healthcare Sciences','Rayagada Campus'),

 ('School of Agricultural & Bio-Engineering','Paralakhemundi Campus'),
 ('M.S. Swaminathan School of Agriculture','Paralakhemundi Campus'),

 ('School of Media & Communication','Bhubaneswar Campus'),
 ('School of Applied Sciences','Paralakhemundi Campus'),
 ('School of Applied Sciences','Bhubaneswar Campus'),
 ('School of Applied Sciences','Rayagada Campus'),
 ('School of Applied Sciences','Balangir Campus'),
 ('School of Applied Sciences','Chatrapur Campus'),

 ('School of Law','Bhubaneswar Campus'),
 ('School of Management','Bhubaneswar Campus'),
 ('School of Management','Paralakhemundi Campus'),

 ('School of Pharmacy and Life Sciences','Bhubaneswar Campus'),
 ('School of Pharmacy and Life Sciences','Rayagada Campus'),
 ('School of Pharmacy and Life Sciences','Balangir Campus'),
 ('School of Pharmacy and Life Sciences','Balasore Campus'),

 ('School of Forensic Sciences','Bhubaneswar Campus'),
 ('School of Forensic Sciences','Paralakhemundi Campus'),
 ('School of Forensic Sciences','Rayagada Campus'),
 ('School of Forensic Sciences','Balangir Campus'),
 ('School of Forensic Sciences','Balasore Campus'),
 ('School of Forensic Sciences','Chatrapur Campus'),

 ('School of Maritime Studies','Bhubaneswar Campus'),
 ('School of Veterinary and Animal Sciences','Paralakhemundi Campus'),
 ('School of Biotechnology','Bhubaneswar Campus'),
 ('School of Nursing','Paralakhemundi Campus'),
 ('School of Nursing','Bhubaneswar Campus'),
 ('School of Nursing','Balangir Campus'),
 ('School of Nursing','Balasore Campus'),
 ('School of Nursing','Rayagada Campus'),

 ('School of Vocational Education and Training','Bhubaneswar Campus'),
 ('School of Vocational Education and Training','Paralakhemundi Campus'),
 ('School of Vocational Education and Training','Balasore Campus'),

 ('School of Design Studies','Bhubaneswar Campus'),
 ('School of Aerospace Engineering and Aviation Management','Bhubaneswar Campus'),
 ('School of Computing & Data Science','Bhubaneswar Campus')
) AS v(school_name,campus_name)
JOIN schools s ON s.name = v.school_name
JOIN campuses c ON c.name = v.campus_name
WHERE NOT EXISTS (
    SELECT 1 FROM school_campuses sc
    WHERE sc.school_id=s.id AND sc.campus_id=c.id
);

-- ------------------------------------------------------------
-- 3. PROGRAMS
-- ------------------------------------------------------------
WITH program_data(name, school_name, short_name, program_level, degree_type, duration, description, source_url) AS (
VALUES
('Bachelor of Technology in Computer Science and Engineering','School of Engineering and Technology','B.Tech CSE','UG','B.Tech','4 years','Computer Science and Engineering programme.','https://cutm.ac.in/course-fees/'),
('Bachelor of Technology in Computer Science and Engineering (AIML)','School of Engineering and Technology','B.Tech CSE-AIML','UG','B.Tech','4 years','Computer Science and Engineering with Artificial Intelligence and Machine Learning.','https://cutm.ac.in/course-fees/'),
('Bachelor of Technology in Cyber','School of Engineering and Technology','B.Tech Cyber','UG','B.Tech','4 years','Cyber-focused engineering programme.','https://cutm.ac.in/course-fees/'),
('Bachelor of Technology in Aerospace Engineering','School of Aerospace Engineering and Aviation Management','B.Tech Aerospace','UG','B.Tech','4 years','Aerospace Engineering programme.','https://cutm.ac.in/course-fees/'),
('Bachelor of Technology in Civil Engineering','School of Engineering and Technology','B.Tech Civil','UG','B.Tech','4 years','Civil Engineering programme.','https://cutm.ac.in/course-fees/'),
('Bachelor of Technology in Mechanical Engineering','School of Engineering and Technology','B.Tech Mechanical','UG','B.Tech','4 years','Mechanical Engineering programme.','https://cutm.ac.in/course-fees/'),
('Bachelor of Technology in Electrical and Electronics Engineering','School of Engineering and Technology','B.Tech EEE','UG','B.Tech','4 years','Electrical and Electronics Engineering programme.','https://cutm.ac.in/course-fees/'),
('Bachelor of Technology in Electronics and Communication Engineering','School of Engineering and Technology','B.Tech ECE','UG','B.Tech','4 years','Electronics and Communication Engineering programme.','https://cutm.ac.in/course-fees/'),
('Bachelor of Technology in Mining Engineering','School of Engineering and Technology','B.Tech Mining','UG','B.Tech','4 years','Mining Engineering programme.','https://cutm.ac.in/course-fees/'),
('Bachelor of Technology in Biotechnology','School of Biotechnology','B.Tech Biotechnology','UG','B.Tech','4 years','Biotechnology programme.','https://cutm.ac.in/course-fees/'),
('Bachelor of Technology in Dairy Technology','School of Agricultural & Bio-Engineering','B.Tech Dairy Technology','UG','B.Tech','4 years','Dairy Technology programme.','https://cutm.ac.in/course-fees/'),
('Bachelor of Technology in Agricultural Engineering','School of Agricultural & Bio-Engineering','B.Tech Agricultural Engineering','UG','B.Tech','4 years','Agricultural Engineering programme.','https://cutm.ac.in/course-fees/'),

('Bachelor of Computer Application','School of Computing & Data Science','BCA','UG','BCA','4 years','Bachelor of Computer Application.','https://cutm.ac.in/course-fees/'),
('Master of Computer Applications','School of Computing & Data Science','MCA','PG','MCA','2 years','Master of Computer Applications.','https://cutm.ac.in/course-fees/'),

('Master of Technology','School of Engineering and Technology','M.Tech','PG','M.Tech','2 years','Master of Technology programme.','https://cutm.ac.in/course-fees/'),
('Bachelor of Business Administration','School of Management','BBA','UG','BBA','4 years','Bachelor of Business Administration.','https://cutm.ac.in/course-fees/'),
('Bachelor of Business Administration (Healthcare Management)','School of Management','BBA Healthcare','UG','BBA','4 years','Bachelor of Business Administration in Healthcare Management.','https://cutm.ac.in/course-fees/'),
('Bachelor of Commerce','School of Management','B.Com','UG','B.Com','4 years','Bachelor of Commerce.','https://cutm.ac.in/course-fees/'),
('Bachelor of Business Administration (Retail Management)','School of Management','BBA Retail','UG','BBA','4 years','BBA Retail Management.','https://cutm.ac.in/course-fees/'),
('Master of Business Administration','School of Management','MBA','PG','MBA','2 years','Master of Business Administration.','https://cutm.ac.in/course-fees/'),
('Master of Business Administration (Healthcare Management)','School of Management','MBA Healthcare','PG','MBA','2 years','Master of Business Administration in Healthcare Management.','https://cutm.ac.in/course-fees/'),
('Master of Business Administration in Agribusiness Management','School of Management','MBA Agribusiness','PG','MBA','2 years','MBA in Agribusiness Management.','https://cutm.ac.in/course-fees/'),
('Bachelor of Management Studies (Airlines and Airport Management)','School of Aerospace Engineering and Aviation Management','BMS Airline/Airport','UG','BMS','4 years','Bachelor of Management Studies in Airlines and Airport Management.','https://cutm.ac.in/course-fees/'),

('Bachelor of Science in Physics','School of Applied Sciences','B.Sc Physics','UG','B.Sc','4 years','B.Sc Physics.','https://cutm.ac.in/course-fees/'),
('Bachelor of Science in Chemistry','School of Applied Sciences','B.Sc Chemistry','UG','B.Sc','4 years','B.Sc Chemistry.','https://cutm.ac.in/course-fees/'),
('Bachelor of Science in Mathematics','School of Applied Sciences','B.Sc Mathematics','UG','B.Sc','4 years','B.Sc Mathematics.','https://cutm.ac.in/course-fees/'),
('Bachelor of Science in Botany','School of Applied Sciences','B.Sc Botany','UG','B.Sc','4 years','B.Sc Botany.','https://cutm.ac.in/course-fees/'),
('Bachelor of Science in Zoology','School of Applied Sciences','B.Sc Zoology','UG','B.Sc','4 years','B.Sc Zoology.','https://cutm.ac.in/course-fees/'),
('Bachelor of Science CSE','School of Applied Sciences','B.Sc CSE','UG','B.Sc','4 years','B.Sc Computer Science and Engineering.','https://cutm.ac.in/course-fees/'),
('Master of Science in Applied Chemistry','School of Applied Sciences','M.Sc Applied Chemistry','PG','M.Sc','2 years','M.Sc Applied Chemistry.','https://cutm.ac.in/course-fees/'),
('Master of Science in Applied Mathematics','School of Applied Sciences','M.Sc Applied Mathematics','PG','M.Sc','2 years','M.Sc Applied Mathematics.','https://cutm.ac.in/course-fees/'),
('Master of Science in Applied Physics','School of Applied Sciences','M.Sc Applied Physics','PG','M.Sc','2 years','M.Sc Applied Physics.','https://cutm.ac.in/course-fees/'),
('Master of Science in Applied and Clinical Microbiology','School of Applied Sciences','M.Sc Applied/Clinical Microbiology','PG','M.Sc','2 years','M.Sc Applied and Clinical Microbiology.','https://cutm.ac.in/course-fees/'),

('BBA+LLB Integrated','School of Law','BBA+LLB','UG','Integrated','5 years','Integrated BBA and LLB programme.','https://cutm.ac.in/course-fees/'),
('BA+LLB Integrated','School of Law','BA+LLB','UG','Integrated','5 years','Integrated BA and LLB programme.','https://cutm.ac.in/course-fees/'),
('LLB','School of Law','LLB','UG','LLB','3 years','Bachelor of Laws.','https://cutm.ac.in/course-fees/'),
('LLM','School of Law','LLM','PG','LLM','2 years','Master of Laws.','https://cutm.ac.in/course-fees/'),
('LL.M. in Maritime Law','School of Maritime Studies','LLM Maritime Law','PG','LLM','2 years','LL.M. in Maritime Law.','https://cutm.ac.in/course-fees/'),

('Bachelor of Science in Biotechnology','School of Biotechnology','B.Sc Biotechnology','UG','B.Sc','4 years','B.Sc Biotechnology.','https://cutm.ac.in/course-fees/'),
('Master of Science in Biotechnology','School of Biotechnology','M.Sc Biotechnology','PG','M.Sc','2 years','M.Sc Biotechnology.','https://cutm.ac.in/course-fees/'),

('Diploma in Pharmacy','School of Pharmacy and Life Sciences','D.Pharm','Diploma','D.Pharm','2 years','Diploma in Pharmacy.','https://cutm.ac.in/course-fees/'),
('Bachelor of Pharmacy','School of Pharmacy and Life Sciences','B.Pharm','UG','B.Pharm','4 years','Bachelor of Pharmacy.','https://cutm.ac.in/course-fees/'),
('Master of Pharmacy','School of Pharmacy and Life Sciences','M.Pharm','PG','M.Pharm','2 years','Master of Pharmacy.','https://cutm.ac.in/course-fees/'),
('Bachelor of Physiotherapy','School of Allied and Healthcare Sciences','BPT','UG','BPT','4 years','Bachelor of Physiotherapy.','https://cutm.ac.in/course-fees/'),
('Bachelor of Science in Forensic Science','School of Forensic Sciences','B.Sc Forensic Science','UG','B.Sc','4 years','B.Sc Forensic Science.','https://cutm.ac.in/course-fees/'),
('Bachelor of Science in Nursing','School of Nursing','B.Sc Nursing','UG','B.Sc Nursing','4 years','Bachelor of Science in Nursing.','https://cutm.ac.in/course-fees/'),
('Bachelor of Fisheries Science (Hons.)','School of Fisheries','B.F.Sc','UG','B.F.Sc','4 years','Bachelor of Fisheries Science (Hons.).','https://cutm.ac.in/course-fees/'),
('Master of Fishery Science','School of Fisheries','M.F.Sc','PG','M.F.Sc','2 years','Master of Fishery Science.','https://cutm.ac.in/course-fees/'),
('B.Sc. Agriculture (Hons.)','M.S. Swaminathan School of Agriculture','B.Sc Agriculture','UG','B.Sc Agriculture','4 years','B.Sc Agriculture (Hons.).','https://cutm.ac.in/course-fees/'),
('B.V.Sc. and A.H.','School of Veterinary and Animal Sciences','B.V.Sc & A.H.','UG','B.V.Sc & A.H.','5 years','Bachelor of Veterinary Science and Animal Husbandry.','https://cutm.ac.in/course-fees/'),

('Bachelor of Design - Automobile & Transportation Design','School of Design Studies','B.Des Automobile','UG','B.Des','4 years','Bachelor of Design in Automobile and Transportation Design.','https://cutm.ac.in/course-fees/'),
('Bachelor of Design - Graphic/Visual Communication Design','School of Design Studies','B.Des Graphic/Visual','UG','B.Des','4 years','Bachelor of Design in Graphic/Visual Communication Design.','https://cutm.ac.in/course-fees/'),
('Bachelor of Design - UI/UX Design','School of Design Studies','B.Des UI/UX','UG','B.Des','4 years','Bachelor of Design in UI/UX Design.','https://cutm.ac.in/course-fees/')
)
INSERT INTO programs
(school_id,name,short_name,program_level,degree_type,duration,description,website_url,official_source_id)
SELECT s.id,p.name,p.short_name,p.program_level,p.degree_type,p.duration,p.description,
       p.source_url,ks.id
FROM program_data p
LEFT JOIN schools s ON s.name=p.school_name
LEFT JOIN knowledge_sources ks ON ks.url=p.source_url
WHERE NOT EXISTS (
    SELECT 1 FROM programs x
    WHERE x.name=p.name
      AND COALESCE(x.school_id,-1)=COALESCE(s.id,-1)
);

-- ------------------------------------------------------------
-- 4. PROGRAM <-> CAMPUS
-- ------------------------------------------------------------
INSERT INTO program_campuses(program_id,campus_id,academic_year,is_active)
SELECT p.id,c.id,'2026',TRUE
FROM (VALUES
 ('Bachelor of Technology in Computer Science and Engineering','Bhubaneswar Campus'),
 ('Bachelor of Technology in Computer Science and Engineering','Paralakhemundi Campus'),
 ('Bachelor of Technology in Computer Science and Engineering (AIML)','Bhubaneswar Campus'),
 ('Bachelor of Technology in Computer Science and Engineering (AIML)','Paralakhemundi Campus'),
 ('Bachelor of Technology in Cyber','Bhubaneswar Campus'),
 ('Bachelor of Technology in Aerospace Engineering','Bhubaneswar Campus'),
 ('Bachelor of Technology in Civil Engineering','Bhubaneswar Campus'),
 ('Bachelor of Technology in Civil Engineering','Paralakhemundi Campus'),
 ('Bachelor of Technology in Mechanical Engineering','Bhubaneswar Campus'),
 ('Bachelor of Technology in Mechanical Engineering','Paralakhemundi Campus'),
 ('Bachelor of Technology in Electrical and Electronics Engineering','Bhubaneswar Campus'),
 ('Bachelor of Technology in Electrical and Electronics Engineering','Paralakhemundi Campus'),
 ('Bachelor of Technology in Electronics and Communication Engineering','Bhubaneswar Campus'),
 ('Bachelor of Technology in Electronics and Communication Engineering','Paralakhemundi Campus'),
 ('Bachelor of Technology in Mining Engineering','Bhubaneswar Campus'),
 ('Bachelor of Technology in Biotechnology','Bhubaneswar Campus'),
 ('Bachelor of Technology in Dairy Technology','Paralakhemundi Campus'),
 ('Bachelor of Technology in Agricultural Engineering','Paralakhemundi Campus'),
 ('Bachelor of Computer Application','Bhubaneswar Campus'),
 ('Bachelor of Computer Application','Paralakhemundi Campus'),
 ('Bachelor of Computer Application','Balangir Campus'),
 ('Master of Computer Applications','Bhubaneswar Campus'),
 ('Master of Computer Applications','Paralakhemundi Campus'),
 ('Master of Technology','Bhubaneswar Campus'),
 ('Master of Technology','Paralakhemundi Campus'),
 ('Bachelor of Business Administration','Bhubaneswar Campus'),
 ('Bachelor of Business Administration','Paralakhemundi Campus'),
 ('Bachelor of Business Administration (Healthcare Management)','Bhubaneswar Campus'),
 ('Bachelor of Commerce','Bhubaneswar Campus'),
 ('Bachelor of Business Administration (Retail Management)','Bhubaneswar Campus'),
 ('Master of Business Administration','Bhubaneswar Campus'),
 ('Master of Business Administration (Healthcare Management)','Bhubaneswar Campus'),
 ('Master of Business Administration in Agribusiness Management','Paralakhemundi Campus'),
 ('Bachelor of Management Studies (Airlines and Airport Management)','Bhubaneswar Campus'),
 ('Bachelor of Science in Physics','Bhubaneswar Campus'),
 ('Bachelor of Science in Physics','Rayagada Campus'),
 ('Bachelor of Science in Physics','Balangir Campus'),
 ('Bachelor of Science in Chemistry','Bhubaneswar Campus'),
 ('Bachelor of Science in Chemistry','Rayagada Campus'),
 ('Bachelor of Science in Chemistry','Balangir Campus'),
 ('Bachelor of Science in Mathematics','Bhubaneswar Campus'),
 ('Bachelor of Science in Mathematics','Rayagada Campus'),
 ('Bachelor of Science in Mathematics','Balangir Campus'),
 ('Bachelor of Science in Botany','Bhubaneswar Campus'),
 ('Bachelor of Science in Botany','Rayagada Campus'),
 ('Bachelor of Science in Botany','Balangir Campus'),
 ('Bachelor of Science in Zoology','Bhubaneswar Campus'),
 ('Bachelor of Science in Zoology','Rayagada Campus'),
 ('Bachelor of Science in Zoology','Balangir Campus'),
 ('Bachelor of Science CSE','Bhubaneswar Campus'),
 ('Master of Science in Applied Chemistry','Bhubaneswar Campus'),
 ('Master of Science in Applied Chemistry','Balangir Campus'),
 ('Master of Science in Applied Mathematics','Bhubaneswar Campus'),
 ('Master of Science in Applied Physics','Bhubaneswar Campus'),
 ('Master of Science in Applied Physics','Balangir Campus'),
 ('Master of Science in Applied and Clinical Microbiology','Bhubaneswar Campus'),
 ('BBA+LLB Integrated','Bhubaneswar Campus'),
 ('BA+LLB Integrated','Bhubaneswar Campus'),
 ('LLB','Bhubaneswar Campus'),
 ('LLM','Bhubaneswar Campus'),
 ('LL.M. in Maritime Law','Bhubaneswar Campus'),
 ('Bachelor of Science in Biotechnology','Bhubaneswar Campus'),
 ('Master of Science in Biotechnology','Bhubaneswar Campus'),
 ('Diploma in Pharmacy','Bhubaneswar Campus'),
 ('Bachelor of Pharmacy','Bhubaneswar Campus'),
 ('Bachelor of Pharmacy','Rayagada Campus'),
 ('Bachelor of Pharmacy','Balasore Campus'),
 ('Bachelor of Pharmacy','Balangir Campus'),
 ('Master of Pharmacy','Bhubaneswar Campus'),
 ('Bachelor of Physiotherapy','Bhubaneswar Campus'),
 ('Bachelor of Science in Forensic Science','Bhubaneswar Campus'),
 ('Bachelor of Science in Nursing','Bhubaneswar Campus'),
 ('Bachelor of Science in Nursing','Paralakhemundi Campus'),
 ('Bachelor of Science in Nursing','Balangir Campus'),
 ('Bachelor of Science in Nursing','Balasore Campus'),
 ('Bachelor of Science in Nursing','Rayagada Campus'),
 ('Bachelor of Fisheries Science (Hons.)','Paralakhemundi Campus'),
 ('Master of Fishery Science','Paralakhemundi Campus'),
 ('B.Sc. Agriculture (Hons.)','Paralakhemundi Campus'),
 ('B.V.Sc. and A.H.','Paralakhemundi Campus'),
 ('Bachelor of Design - Automobile & Transportation Design','Bhubaneswar Campus'),
 ('Bachelor of Design - Graphic/Visual Communication Design','Bhubaneswar Campus'),
 ('Bachelor of Design - UI/UX Design','Bhubaneswar Campus')
) AS v(program_name,campus_name)
JOIN programs p ON p.name=v.program_name
JOIN campuses c ON c.name=v.campus_name
WHERE NOT EXISTS (
    SELECT 1 FROM program_campuses pc
    WHERE pc.program_id=p.id AND pc.campus_id=c.id
      AND pc.academic_year='2026'
);

-- ------------------------------------------------------------
-- 5. ELIGIBILITY
-- ------------------------------------------------------------
WITH e(program_name,academic_year,eligibility_text,minimum_percentage,entrance_exam,additional_requirements,source_url) AS (
VALUES
('Bachelor of Technology in Computer Science and Engineering','2026','10+2 with Physics, Chemistry and Mathematics with minimum 50% or equivalent diploma; reserved categories receive applicable relaxation.',50,'JEE Main / CUEE','Programme-specific admission requirements apply.','https://cutm.ac.in/school-of-engineering-technology/'),
('Bachelor of Technology in Computer Science and Engineering (AIML)','2026','10+2 with Physics, Chemistry and Mathematics with minimum 50% or equivalent diploma; reserved categories receive applicable relaxation.',50,'JEE Main / CUEE','Programme-specific admission requirements apply.','https://cutm.ac.in/school-of-engineering-technology/'),
('Bachelor of Technology in Civil Engineering','2026','10+2 with Physics, Chemistry and Mathematics with minimum 50% or equivalent diploma; reserved categories receive applicable relaxation.',50,'JEE Main / CUEE','Programme-specific admission requirements apply.','https://cutm.ac.in/school-of-engineering-technology/'),
('Bachelor of Technology in Mechanical Engineering','2026','10+2 with Physics, Chemistry and Mathematics with minimum 50% or equivalent diploma; reserved categories receive applicable relaxation.',50,'JEE Main / CUEE','Programme-specific admission requirements apply.','https://cutm.ac.in/school-of-engineering-technology/'),
('Bachelor of Technology in Electronics and Communication Engineering','2026','10+2 with Physics, Chemistry and Mathematics with minimum 50% or equivalent diploma; reserved categories receive applicable relaxation.',50,'JEE Main / CUEE','Programme-specific admission requirements apply.','https://cutm.ac.in/school-of-engineering-technology/'),
('Bachelor of Technology in Aerospace Engineering','2026','10+2 with Physics, Chemistry and Mathematics with minimum 50%; reserved categories receive applicable relaxation.',50,'CUEE','Programme-specific admission requirements apply.','https://cutm.ac.in/school-of-aerospace-engineering-and-aviation-management/'),
('Bachelor of Computer Application','2026','10+2 or equivalent diploma in any stream with Mathematics as applicable to the programme.',NULL,'CUEE','Check current programme-specific admission requirements.','https://cutm.ac.in/school-of-engineering-technology/'),
('Master of Business Administration','2026','Graduation or equivalent qualification as required by the School of Management.',NULL,'CUEE / CAT / MAT / XAT','GD/PI and other current School of Management requirements may apply.','https://cutm.ac.in/'),
('Bachelor of Science in Physics','2026','10+2 Science or equivalent qualification for the four-year B.Sc. programme.',NULL,'CUEE','Programme-specific requirements apply.','https://cutm.ac.in/school-of-applied-sciences/'),
('Bachelor of Science in Chemistry','2026','10+2 Science or equivalent qualification for the four-year B.Sc. programme.',NULL,'CUEE','Programme-specific requirements apply.','https://cutm.ac.in/school-of-applied-sciences/'),
('Bachelor of Science in Mathematics','2026','10+2 Science or equivalent qualification for the four-year B.Sc. programme.',NULL,'CUEE','Programme-specific requirements apply.','https://cutm.ac.in/school-of-applied-sciences/'),
('Bachelor of Science in Forensic Science','2026','Eligibility should be checked against the current School of Forensic Sciences admission requirements.',NULL,'CUEE','Do not infer additional subject requirements without official confirmation.','https://cutm.ac.in/school-of-forensic-sciences/')
)
INSERT INTO program_eligibility
(program_id,academic_year,eligibility_text,minimum_percentage,entrance_exam,additional_requirements,official_source_id)
SELECT p.id,e.academic_year,e.eligibility_text,e.minimum_percentage,e.entrance_exam,
       e.additional_requirements,ks.id
FROM e
JOIN programs p ON p.name=e.program_name
LEFT JOIN knowledge_sources ks ON ks.url=e.source_url
WHERE NOT EXISTS (
    SELECT 1 FROM program_eligibility x
    WHERE x.program_id=p.id AND x.academic_year=e.academic_year
);

-- ------------------------------------------------------------
-- 6. PROGRAM FEES - 2026
-- ------------------------------------------------------------
WITH f(program_name,campus_name,amount,notes) AS (
VALUES
('Bachelor of Technology in Computer Science and Engineering','Bhubaneswar Campus',185000,'Official CUTM fee for 2026.'),
('Bachelor of Technology in Computer Science and Engineering','Paralakhemundi Campus',150000,'Official CUTM fee for 2026.'),
('Bachelor of Technology in Computer Science and Engineering (AIML)','Bhubaneswar Campus',200000,'Official CUTM fee for 2026.'),
('Bachelor of Technology in Computer Science and Engineering (AIML)','Paralakhemundi Campus',165000,'Official CUTM fee for 2026.'),
('Bachelor of Technology in Cyber','Bhubaneswar Campus',185000,'Official CUTM fee for 2026.'),
('Bachelor of Technology in Aerospace Engineering','Bhubaneswar Campus',160000,'Official CUTM fee for 2026.'),
('Bachelor of Technology in Civil Engineering','Bhubaneswar Campus',155000,'Official CUTM fee for 2026.'),
('Bachelor of Technology in Civil Engineering','Paralakhemundi Campus',110000,'Official CUTM fee for 2026.'),
('Bachelor of Technology in Mechanical Engineering','Bhubaneswar Campus',155000,'Official CUTM fee for 2026.'),
('Bachelor of Technology in Mechanical Engineering','Paralakhemundi Campus',110000,'Official CUTM fee for 2026.'),
('Bachelor of Technology in Electrical and Electronics Engineering','Bhubaneswar Campus',155000,'Official CUTM fee for 2026.'),
('Bachelor of Technology in Electrical and Electronics Engineering','Paralakhemundi Campus',110000,'Official CUTM fee for 2026.'),
('Bachelor of Technology in Electronics and Communication Engineering','Bhubaneswar Campus',155000,'Official CUTM fee for 2026.'),
('Bachelor of Technology in Electronics and Communication Engineering','Paralakhemundi Campus',120000,'Official CUTM fee for 2026.'),
('Bachelor of Technology in Mining Engineering','Bhubaneswar Campus',155000,'Official CUTM fee for 2026.'),
('Bachelor of Technology in Biotechnology','Bhubaneswar Campus',165000,'Official CUTM fee for 2026.'),
('Bachelor of Technology in Dairy Technology','Paralakhemundi Campus',110000,'Official CUTM fee for 2026.'),
('Bachelor of Technology in Agricultural Engineering','Paralakhemundi Campus',110000,'Official CUTM fee for 2026; official page groups Agricultural Engineering with the listed PKD engineering fee group.'),
('Bachelor of Computer Application','Bhubaneswar Campus',85000,'Official CUTM fee for 2026.'),
('Bachelor of Computer Application','Paralakhemundi Campus',70000,'Official CUTM fee for 2026.'),
('Bachelor of Computer Application','Balangir Campus',50000,'Official CUTM fee for 2026.'),
('Master of Computer Applications','Bhubaneswar Campus',95000,'Official CUTM fee for 2026.'),
('Master of Computer Applications','Paralakhemundi Campus',550000,'Official CUTM fee for 2026 as displayed by CUTM; verify before student-facing publication.'),
('Master of Technology','Bhubaneswar Campus',90000,'Official CUTM fee for 2026.'),
('Master of Technology','Paralakhemundi Campus',85000,'Official CUTM fee for 2026.'),
('Bachelor of Business Administration','Bhubaneswar Campus',90000,'Official CUTM fee for 2026.'),
('Bachelor of Business Administration','Paralakhemundi Campus',65000,'Official CUTM fee for 2026.'),
('Bachelor of Business Administration (Healthcare Management)','Bhubaneswar Campus',75000,'Official CUTM fee for 2026.'),
('Bachelor of Commerce','Bhubaneswar Campus',75000,'Official CUTM fee for 2026.'),
('Bachelor of Business Administration (Retail Management)','Bhubaneswar Campus',60000,'Official CUTM fee for 2026.'),
('Master of Business Administration','Bhubaneswar Campus',240000,'Official CUTM fee for 2026.'),
('Master of Business Administration (Healthcare Management)','Bhubaneswar Campus',120000,'Official CUTM fee for 2026.'),
('Master of Business Administration in Agribusiness Management','Paralakhemundi Campus',700000,'Official CUTM fee for 2026.'),
('Bachelor of Management Studies (Airlines and Airport Management)','Bhubaneswar Campus',120000,'Official CUTM fee for 2026.'),
('Bachelor of Science in Physics','Bhubaneswar Campus',50000,'Official CUTM fee for 2026.'),
('Bachelor of Science in Physics','Rayagada Campus',60000,'Official CUTM fee for 2026.'),
('Bachelor of Science in Physics','Balangir Campus',90000,'Official CUTM fee for 2026.'),
('Bachelor of Science in Chemistry','Bhubaneswar Campus',50000,'Official CUTM fee for 2026.'),
('Bachelor of Science in Chemistry','Rayagada Campus',60000,'Official CUTM fee for 2026.'),
('Bachelor of Science in Chemistry','Balangir Campus',90000,'Official CUTM fee for 2026.'),
('Bachelor of Science in Mathematics','Bhubaneswar Campus',50000,'Official CUTM fee for 2026.'),
('Bachelor of Science in Mathematics','Rayagada Campus',60000,'Official CUTM fee for 2026.'),
('Bachelor of Science in Mathematics','Balangir Campus',90000,'Official CUTM fee for 2026.'),
('Bachelor of Science in Botany','Bhubaneswar Campus',50000,'Official CUTM fee for 2026.'),
('Bachelor of Science in Botany','Rayagada Campus',60000,'Official CUTM fee for 2026.'),
('Bachelor of Science in Botany','Balangir Campus',90000,'Official CUTM fee for 2026.'),
('Bachelor of Science in Zoology','Bhubaneswar Campus',50000,'Official CUTM fee for 2026.'),
('Bachelor of Science in Zoology','Rayagada Campus',60000,'Official CUTM fee for 2026.'),
('Bachelor of Science in Zoology','Balangir Campus',90000,'Official CUTM fee for 2026.'),
('Bachelor of Science CSE','Bhubaneswar Campus',70000,'Official CUTM fee for 2026.'),
('Master of Science in Applied Chemistry','Bhubaneswar Campus',80000,'Official CUTM fee for 2026.'),
('Master of Science in Applied Chemistry','Balangir Campus',90000,'Official CUTM fee for 2026.'),
('Master of Science in Applied Mathematics','Bhubaneswar Campus',80000,'Official CUTM fee for 2026.'),
('Master of Science in Applied Physics','Bhubaneswar Campus',80000,'Official CUTM fee for 2026.'),
('Master of Science in Applied Physics','Balangir Campus',90000,'Official CUTM fee for 2026.'),
('Master of Science in Applied and Clinical Microbiology','Bhubaneswar Campus',100000,'Official CUTM fee for 2026.'),
('BBA+LLB Integrated','Bhubaneswar Campus',115000,'Official CUTM fee for 2026.'),
('BA+LLB Integrated','Bhubaneswar Campus',115000,'Official CUTM fee for 2026.'),
('LLB','Bhubaneswar Campus',80000,'Official CUTM fee for 2026.'),
('LLM','Bhubaneswar Campus',175000,'Official CUTM fee for 2026.'),
('LL.M. in Maritime Law','Bhubaneswar Campus',175000,'Official CUTM fee for 2026.'),
('Bachelor of Science in Biotechnology','Bhubaneswar Campus',85000,'Official CUTM fee for 2026.'),
('Master of Science in Biotechnology','Bhubaneswar Campus',90000,'Official CUTM fee for 2026.'),
('Diploma in Pharmacy','Bhubaneswar Campus',80000,'Official CUTM fee for 2026.'),
('Bachelor of Pharmacy','Bhubaneswar Campus',150000,'Official CUTM fee for 2026.'),
('Bachelor of Pharmacy','Rayagada Campus',80000,'Official CUTM fee for 2026.'),
('Bachelor of Pharmacy','Balasore Campus',90000,'Official CUTM fee for 2026.'),
('Bachelor of Pharmacy','Balangir Campus',120000,'Official CUTM fee for 2026.'),
('Master of Pharmacy','Bhubaneswar Campus',170000,'Official CUTM fee for 2026.'),
('Bachelor of Physiotherapy','Bhubaneswar Campus',100000,'Official CUTM fee for 2026.'),
('Bachelor of Science in Forensic Science','Bhubaneswar Campus',110000,'Official CUTM fee for 2026.'),
('Bachelor of Science in Nursing','Bhubaneswar Campus',120000,'Official CUTM fee for 2026.'),
('Bachelor of Science in Nursing','Paralakhemundi Campus',100000,'Official CUTM fee for 2026.'),
('Bachelor of Science in Nursing','Balangir Campus',150000,'Official CUTM fee for 2026.'),
('Bachelor of Science in Nursing','Balasore Campus',75000,'Official CUTM fee for 2026.'),
('Bachelor of Science in Nursing','Rayagada Campus',80000,'Official CUTM fee for 2026.'),
('Bachelor of Fisheries Science (Hons.)','Paralakhemundi Campus',125000,'Official CUTM fee for 2026.'),
('Master of Fishery Science','Paralakhemundi Campus',110000,'Official CUTM fee for 2026.'),
('B.Sc. Agriculture (Hons.)','Paralakhemundi Campus',150000,'Official CUTM fee for 2026.'),
('B.V.Sc. and A.H.','Paralakhemundi Campus',750000,'Official CUTM fee for 2026.'),
('Bachelor of Design - Automobile & Transportation Design','Bhubaneswar Campus',250000,'Official CUTM fee for 2026.'),
('Bachelor of Design - Graphic/Visual Communication Design','Bhubaneswar Campus',250000,'Official CUTM fee for 2026.'),
('Bachelor of Design - UI/UX Design','Bhubaneswar Campus',250000,'Official CUTM fee for 2026.')
)
INSERT INTO program_fees
(program_id,campus_id,academic_year,fee_type,amount,currency,frequency,notes,official_source_id)
SELECT p.id,c.id,'2026','TUITION',f.amount,'INR','YEARLY',f.notes,ks.id
FROM f
JOIN programs p ON p.name=f.program_name
JOIN campuses c ON c.name=f.campus_name
LEFT JOIN knowledge_sources ks ON ks.url='https://cutm.ac.in/course-fees/'
WHERE NOT EXISTS (
    SELECT 1 FROM program_fees x
    WHERE x.program_id=p.id AND x.campus_id=c.id
      AND x.academic_year='2026' AND x.fee_type='TUITION'
);

-- ------------------------------------------------------------
-- 7. ADDITIONAL FEES - 2026
-- ------------------------------------------------------------
WITH af(campus_name,fee_name,description,amount,frequency,applicable_to) AS (
VALUES
(NULL,'University Registration Fee','First-year common fee.',2500,'ONE_TIME','Graduate and PG courses - all campuses'),
(NULL,'Processing / Counselling Fee','First-year common fee.',3000,'ONE_TIME','Graduate and PG courses - all campuses'),
(NULL,'Caution Deposit','First-year common fee.',7500,'ONE_TIME','Graduate and PG courses - all campuses'),
(NULL,'Examination Fee','First-year common fee.',7000,'ONE_TIME','Graduate and PG courses - all campuses'),
(NULL,'Sports Fee','First-year common fee.',500,'ONE_TIME','Graduate and PG courses - all campuses'),
(NULL,'Insurance','First-year common fee.',2500,'ONE_TIME','Graduate and PG courses - all campuses'),
(NULL,'Backpack with Bottle','First-year common fee.',2000,'ONE_TIME','Graduate and PG courses - all campuses'),
(NULL,'University Registration Fee','First-year diploma fee.',2500,'ONE_TIME','Diploma - all campuses except D.Pharm'),
(NULL,'Processing / Counselling Fee','First-year diploma fee.',3000,'ONE_TIME','Diploma - all campuses except D.Pharm'),
(NULL,'Examination Fee','First-year diploma fee.',3000,'ONE_TIME','Diploma - all campuses except D.Pharm'),
(NULL,'Insurance','First-year diploma fee.',1500,'ONE_TIME','Diploma - all campuses except D.Pharm'),
(NULL,'Sports Fee','First-year diploma fee.',500,'ONE_TIME','Diploma - all campuses except D.Pharm'),
(NULL,'Backpack with Bottle','First-year diploma fee.',2000,'ONE_TIME','Diploma - all campuses except D.Pharm'),
('Bhubaneswar Campus','University Registration Fee','First-year D.Pharm fee.',2000,'ONE_TIME','D.Pharm'),
('Bhubaneswar Campus','Processing / Counselling Fee','First-year D.Pharm fee.',2500,'ONE_TIME','D.Pharm'),
('Bhubaneswar Campus','Examination Fee','First-year D.Pharm fee.',7000,'ONE_TIME','D.Pharm'),
('Bhubaneswar Campus','Insurance','First-year D.Pharm fee.',1500,'ONE_TIME','D.Pharm'),
('Bhubaneswar Campus','Caution Deposit','First-year D.Pharm fee.',5000,'ONE_TIME','D.Pharm'),
('Bhubaneswar Campus','Sports Fee','First-year D.Pharm fee.',500,'ONE_TIME','D.Pharm'),
('Bhubaneswar Campus','Backpack with Bottle','First-year D.Pharm fee.',2000,'ONE_TIME','D.Pharm'),
('Bhubaneswar Campus','Swayam Certification / Training / Newspaper / Industry Visit','Additional first-year MBA fee.',10000,'ONE_TIME','MBA-Bhubaneswar'),
(NULL,'Training and Certification Fees','Additional first-year fee.',5000,'ONE_TIME','BBA PKD/BBSR and B.Sc/M.Sc Agriculture PKD')
)
INSERT INTO additional_fees
(campus_id,academic_year,fee_name,description,amount,currency,frequency,applicable_to,official_source_id)
SELECT c.id,'2026',af.fee_name,af.description,af.amount,'INR',af.frequency,af.applicable_to,ks.id
FROM af
LEFT JOIN campuses c ON c.name=af.campus_name
LEFT JOIN knowledge_sources ks ON ks.url='https://cutm.ac.in/course-fees/'
WHERE NOT EXISTS (
    SELECT 1 FROM additional_fees x
    WHERE COALESCE(x.campus_id,-1)=COALESCE(c.id,-1)
      AND x.academic_year='2026'
      AND x.fee_name=af.fee_name
      AND COALESCE(x.applicable_to,'')=COALESCE(af.applicable_to,'')
);

-- ------------------------------------------------------------
-- 8. ADMISSION
-- ------------------------------------------------------------
INSERT INTO admissions
(campus_id,program_id,academic_year,title,description,entrance_exam,
 application_process,eligibility,application_url,official_source_id)
SELECT NULL,NULL,'2026','CUTM Admission Process 2026',
'Candidates are required to follow the current CUTM admission process. The official process describes registration for CUEE-2026, the online proctored examination, contact/provisional admission for qualified applicants, and final admission after original-document verification.',
'CUEE-2026',
'1. Register for CUEE-2026 and pay the application fee. 2. Appear for the online proctored CUEE examination. 3. Qualified applicants are contacted for provisional admission and minimum fee payment. 4. Submit original documents with photocopies and pay the applicable final fee according to the provisional admission letter.',
'Eligibility is programme-specific and must be checked against the official programme information.',
'https://admissions.cutm.ac.in/',
ks.id
FROM knowledge_sources ks
WHERE ks.url='https://cutm.ac.in/admission-process/'
AND NOT EXISTS (
    SELECT 1 FROM admissions a
    WHERE a.academic_year='2026' AND a.title='CUTM Admission Process 2026'
);

-- ------------------------------------------------------------
-- 9. SCHOLARSHIPS
-- ------------------------------------------------------------
WITH sh(name,description,eligibility,benefit,percentage_or_amount,application_process) AS (
VALUES
('Amrit Kaal Scholarship',
'Merit-based scholarship categories published by CUTM for eligible programmes.',
'Eligibility depends on programme, qualifying percentage and listed entrance-exam performance.',
'Scholarship percentage is applied according to the applicable CUTM category and conditions.',
'Examples published by CUTM include 10%, 15% and 20% categories for specified programmes.',
'Final qualifying score verification and university scholarship committee approval are required. CUEE qualification and non-duplication conditions apply where stated.'),
('Chandrika Scholarship',
'Scholarship category published by CUTM for eligible students, including specified girls/non-CSE and selected science categories.',
'Programme and qualifying-score conditions apply as published by CUTM.',
'Percentage-based fee scholarship according to the applicable category.',
'Published categories include 10% and 20% in specified cases.',
'Follow current CUTM scholarship conditions and verification process.'),
('Bhoomiputra Scholarship',
'Scholarship/fee support category for eligible children of defence, state police and paramilitary personnel as published by CUTM.',
'Applicable to eligible students under the published category; programme exclusions apply.',
'20% scholarship for eligible students under the published conditions.',
'20%',
'Apply/verify according to the university scholarship process.'),
('Sports Scholarship',
'Scholarship category for eligible state and national sports achievers.',
'State-level or national-level sports achievement according to CUTM conditions.',
'Published benefit includes 25% for state sports and 40% for national sports, subject to conditions.',
'25% state / 40% national',
'Original certificates and university verification are required.'),
('Centurion Staff Children Scholarship',
'Scholarship category for eligible children of Centurion staff.',
'Eligible staff-child category under CUTM conditions.',
'20% scholarship under published conditions.',
'20%',
'Verification through university scholarship process.'),
('Second-Year Academic Scholarship',
'Continuation scholarship described by CUTM for eligible students from the second year onward.',
'8.5 CGPA and 80% attendance are stated conditions, with listed programme exclusions.',
'5% academic-fee scholarship under published conditions.',
'5%',
'Academic performance and attendance are checked by the university.')
)
INSERT INTO scholarships
(university_id,name,academic_year,description,eligibility,benefit,percentage_or_amount,application_process,application_url,official_source_id)
SELECT u.id,sh.name,'2026',sh.description,sh.eligibility,sh.benefit,sh.percentage_or_amount,
       sh.application_process,'https://cutm.ac.in/scholarship-loan/',ks.id
FROM sh
CROSS JOIN university u
LEFT JOIN knowledge_sources ks ON ks.url='https://cutm.ac.in/scholarship-loan/'
WHERE u.short_name='CUTM'
AND NOT EXISTS (
    SELECT 1 FROM scholarships x
    WHERE x.university_id=u.id AND x.name=sh.name AND x.academic_year='2026'
);

-- ------------------------------------------------------------
-- 10. HOSTELS
-- ------------------------------------------------------------
WITH h(campus_name,name,hostel_type,gender,capacity,description) AS (
VALUES
('Paralakhemundi Campus','Mahanadi Boys Hostel','HOSTEL','BOYS',243,'Official listed intake capacity: 243.'),
('Paralakhemundi Campus','Nagavali Boys Hostel','HOSTEL','BOYS',240,'Official listed intake capacity: 240; 48 rooms.'),
('Paralakhemundi Campus','Vansadhara Boys Hostel','HOSTEL','BOYS',138,'Official listed intake capacity: 138; 38 rooms.'),
('Paralakhemundi Campus','Mahendra Tanaya Girls Hostel','HOSTEL','GIRLS',276,'Official listed intake capacity: 276; 38 rooms and 300 beds are listed.'),
('Paralakhemundi Campus','Indravati Girls Hostel','HOSTEL','GIRLS',240,'Official listed intake capacity: 240.'),
('Paralakhemundi Campus','Brahmani Girls Hostel','HOSTEL','GIRLS',96,'Official listed intake capacity: 96.'),
('Paralakhemundi Campus','MDC AC Hostel','HOSTEL',NULL,137,'Official listed intake capacity: 137.'),
('Paralakhemundi Campus','Baitarani Boys Hostel','HOSTEL','BOYS',96,'Official listed intake capacity: 96.'),
('Paralakhemundi Campus','Nagabali Boys Hostel','HOSTEL','BOYS',240,'Official listed intake capacity: 240.'),
('Paralakhemundi Campus','CPS Girls','HOSTEL','GIRLS',NULL,'Official page lists the hostel but does not provide an intake capacity.'),
('Paralakhemundi Campus','Subarnarekha Hostel','HOSTEL',NULL,264,'Official listed intake capacity: 264.'),
('Paralakhemundi Campus','Bhargavi Hostel','HOSTEL',NULL,240,'Official listed intake capacity: 240.'),
('Paralakhemundi Campus','Baitarani Hostel','HOSTEL',NULL,140,'Official listed intake capacity: 140.'),
('Bhubaneswar Campus','Kushabhadra Boys Hostel','HOSTEL','BOYS',220,'Official listed capacity: 220.'),
('Bhubaneswar Campus','Yara Boys Hostel','HOSTEL','BOYS',220,'Official listed capacity: 220.'),
('Bhubaneswar Campus','Bhargavi Boys Hostel','HOSTEL','BOYS',425,'Official listed capacity: 425.'),
('Bhubaneswar Campus','Bansadhara Boys Hostel','HOSTEL','BOYS',325,'Official listed capacity: 325.'),
('Bhubaneswar Campus','Baitarani Boys Hostel','HOSTEL','BOYS',350,'Official listed capacity: 350.'),
('Bhubaneswar Campus','Chitroptala Boys Hostel','HOSTEL','BOYS',350,'Official listed capacity: 350.'),
('Bhubaneswar Campus','Nagabali Boys Hostel','HOSTEL','BOYS',336,'Official listed capacity: 336.'),
('Bhubaneswar Campus','Boys Hostel 08','HOSTEL','BOYS',240,'Official listed capacity: 240.'),
('Bhubaneswar Campus','Sabari Girls Hostel','HOSTEL','GIRLS',183,'Official listed capacity: 183.'),
('Bhubaneswar Campus','Prachi Girls Hostel','HOSTEL','GIRLS',213,'Official listed capacity: 213.'),
('Bhubaneswar Campus','Daya Girls Hostel','HOSTEL','GIRLS',288,'Official listed capacity: 288.'),
('Bhubaneswar Campus','Devi Girls Hostel','HOSTEL','GIRLS',273,'Official listed capacity: 273.'),
('Bhubaneswar Campus','Sileru Girls Hostel','HOSTEL','GIRLS',400,'Official listed capacity: 400.')
)
INSERT INTO hostel_details
(campus_id,name,hostel_type,gender,capacity,description,facilities,official_source_id)
SELECT c.id,h.name,h.hostel_type,h.gender,h.capacity,h.description,
       'Residential facilities include separate female accommodation, 24-hour power and medical facilities, full-time security, Wi-Fi connectivity and on-campus ATM facility.',
       ks.id
FROM h
JOIN campuses c ON c.name=h.campus_name
LEFT JOIN knowledge_sources ks ON ks.url='https://cutm.ac.in/residential-facilities/'
WHERE NOT EXISTS (
    SELECT 1 FROM hostel_details x
    WHERE x.campus_id=c.id AND x.name=h.name
);

-- ------------------------------------------------------------
-- 11. FACILITIES
-- ------------------------------------------------------------
WITH fac(name,facility_type,description) AS (
VALUES
('Library','ACADEMIC','Academic library facilities.'),
('Lecture Theatres','ACADEMIC','Lecture theatre facilities.'),
('Boardroom','ACADEMIC','Boardroom facility.'),
('Hall No. 5 and 6','ACADEMIC','Hall facilities.'),
('Auditorium','ACADEMIC','Auditorium facility.'),
('Swimming','SPORTS','Swimming facility.'),
('Yoga','SPORTS','Yoga facility.'),
('Basketball','SPORTS','Basketball facility.'),
('Cricket','SPORTS','Cricket facility.'),
('Volleyball','SPORTS','Volleyball facility.'),
('Gymnasium','SPORTS','Gymnasium facility.'),
('Organic Farming','SUSTAINABILITY','Organic farming facility.'),
('Rainwater Harvesting','SUSTAINABILITY','Rainwater harvesting facility.'),
('Vermicomposting','SUSTAINABILITY','Vermicomposting facility.'),
('Aquarium','SUSTAINABILITY','Aquarium facility.'),
('Guest Houses','RESIDENTIAL','Guest house facilities.'),
('Hostels','RESIDENTIAL','Hostel facilities.'),
('Staff Quarters','RESIDENTIAL','Staff residential quarters.'),
('Waiting Area','RESIDENTIAL','Waiting area facilities.'),
('Bus Fleet and Ambulance','TRANSPORT','Bus fleet and ambulance facilities.'),
('E-Vehicles','TRANSPORT','E-vehicle facilities.'),
('Biodigester','WASTE_RECYCLING','Biodigester facility.'),
('Paper Unit','WASTE_RECYCLING','Paper unit for reuse/recycling.'),
('Paver Block Manufacturing','WASTE_RECYCLING','Paver block manufacturing/reuse facility.'),
('Central Mess','FOOD','Central mess facilities.'),
('Restaurant and Market Complex','FOOD','Restaurant and market complex.')
)
INSERT INTO facility_details
(campus_id,name,facility_type,description,official_source_id)
SELECT NULL,fac.name,fac.facility_type,fac.description,ks.id
FROM fac
LEFT JOIN knowledge_sources ks ON ks.url='https://cutm.ac.in/campus-facilities/'
WHERE NOT EXISTS (
    SELECT 1 FROM facility_details x
    WHERE x.name=fac.name AND x.facility_type=fac.facility_type
);

-- ------------------------------------------------------------
-- 12. ACADEMIC CALENDAR INDEX
-- ------------------------------------------------------------
WITH ac(school_name,title) AS (
VALUES
('School of Engineering and Technology','Academic Calendar 2026-27 - School of Engineering and Technology'),
('School of Fisheries','Academic Calendar 2026-27 - School of Fisheries'),
('School of Allied and Healthcare Sciences','Academic Calendar 2026-27 - School of Allied and Healthcare Sciences'),
('M.S. Swaminathan School of Agriculture','Academic Calendar 2026-27 - M.S. Swaminathan School of Agriculture'),
('School of Agricultural & Bio-Engineering','Academic Calendar 2026-27 - School of Agricultural & Bio-Engineering'),
('School of Applied Sciences','Academic Calendar 2026-27 - School of Applied Sciences'),
('School of Law','Academic Calendar 2026-27 - School of Law'),
('School of Management','Academic Calendar 2026-27 - School of Management'),
('School of Pharmacy and Life Sciences','Academic Calendar 2026-27 - School of Pharmacy and Life Sciences'),
('School of Forensic Sciences','Academic Calendar 2026-27 - School of Forensic Sciences'),
('School of Media & Communication','Academic Calendar 2026-27 - School of Media & Communication'),
('School of Maritime Studies','Academic Calendar 2026-27 - School of Maritime Studies'),
('School of Veterinary and Animal Sciences','Academic Calendar 2026-27 - School of Veterinary and Animal Sciences'),
('School of Biotechnology','Academic Calendar 2026-27 - School of Biotechnology'),
('School of Nursing','Academic Calendar 2026-27 - School of Nursing'),
('School of Computing & Data Science','Academic Calendar 2026-27 - School of Computing & Data Science'),
('School of Design Studies','Academic Calendar 2026-27 - School of Design Studies'),
('School of Vocational Education and Training','Academic Calendar 2026-27 - School of Vocational Education and Training')
)
INSERT INTO academic_calendars
(university_id,school_id,academic_year,title,description,document_url,official_source_id)
SELECT u.id,s.id,'2026-27',ac.title,
       'Official CUTM academic-calendar index. The page contains separate calendar documents by school, programme level and year/semester.',
       'https://cutm.ac.in/academic-calendar/',ks.id
FROM ac
JOIN schools s ON s.name=ac.school_name
CROSS JOIN university u
LEFT JOIN knowledge_sources ks ON ks.url='https://cutm.ac.in/academic-calendar/'
WHERE u.short_name='CUTM'
AND NOT EXISTS (
    SELECT 1 FROM academic_calendars x
    WHERE x.school_id=s.id AND x.academic_year='2026-27'
);

-- ------------------------------------------------------------
-- 13. PLACEMENTS
-- ------------------------------------------------------------
INSERT INTO placements
(university_id,academic_year,title,description,highest_package,total_recruiters,total_offers,official_source_id)
SELECT u.id,'2024','CUTM Placement Highlights 2024',
       'CUTM official placement highlights report a highest package of Rs. 35 lakhs, 150 companies and 8100 candidates placed.',
       3500000,150,8100,ks.id
FROM university u
LEFT JOIN knowledge_sources ks ON ks.url='https://cutm.ac.in/'
WHERE u.short_name='CUTM'
AND NOT EXISTS (
    SELECT 1 FROM placements p
    WHERE p.university_id=u.id AND p.academic_year='2024'
);

-- ------------------------------------------------------------
-- 14. RESEARCH
-- ------------------------------------------------------------
WITH r(title,category,description,research_area,website_url) AS (
VALUES
('CUTM Research Ecosystem 2024-25','RESEARCH_INFRASTRUCTURE',
 'The CUTM Annual Report 2024-25 reports 22 research centres, 11 Centers of Excellence and 30 Live Labs.',
 'Research centres, Centers of Excellence and Live Labs',
 'https://cutm.ac.in/'),
('CUTM Research and Innovation Output 2024-25','RESEARCH_OUTPUT',
 'The CUTM Annual Report 2024-25 reports 685 journal articles, 159 books/book chapters, 69 conference proceedings and more than 111 patents and copyrights published.',
 'Publications, patents and innovation',
 'https://cutm.ac.in/')
)
INSERT INTO research
(university_id,title,category,description,research_area,website_url,official_source_id)
SELECT u.id,r.title,r.category,r.description,r.research_area,r.website_url,ks.id
FROM r
CROSS JOIN university u
LEFT JOIN knowledge_sources ks ON ks.url='https://cutm.ac.in/'
WHERE u.short_name='CUTM'
AND NOT EXISTS (
    SELECT 1 FROM research x
    WHERE x.university_id=u.id AND x.title=r.title
);

-- ------------------------------------------------------------
-- 15. ENTREPRENEURSHIP
-- ------------------------------------------------------------
INSERT INTO entrepreneurship
(university_id,title,category,description,facilities,website_url,official_source_id)
SELECT u.id,
       'Centurion Innovation and Entrepreneurship / CIE',
       'INCUBATION',
       'CUTM entrepreneurship information describes the Centre for Innovation and Entrepreneurship under the CSTEFC as a Section 8 not-for-profit initiative supporting ideas from concept and prototype through market testing, revenue and scale.',
       'Incubation and innovation support across agriculture/allied, education/skill, waste management, healthcare, art/craft, fashion/retail and alternate-energy sectors.',
       'https://cutm.ac.in/entrepreneurship/',
       ks.id
FROM university u
LEFT JOIN knowledge_sources ks ON ks.url='https://cutm.ac.in/entrepreneurship/'
WHERE u.short_name='CUTM'
AND NOT EXISTS (
    SELECT 1 FROM entrepreneurship e
    WHERE e.university_id=u.id AND e.title='Centurion Innovation and Entrepreneurship / CIE'
);

-- ------------------------------------------------------------
-- 16. INTERNATIONAL
-- ------------------------------------------------------------
INSERT INTO international_programs
(university_id,title,partner_institution,country,program_type,description,website_url,official_source_id)
SELECT u.id,
       'International Outreach and Collaboration',
       'International outreach partners including Morocco delegation / maritime collaboration contacts',
       'Morocco / International',
       'ACADEMIC_AND_INSTITUTIONAL_COLLABORATION',
       'CUTM international outreach information describes academic exchange, train-the-trainer opportunities, maritime curriculum and e-learning collaboration, exchange of maritime programmes, simulator assistance, joint research, consultancy and partnerships within international maritime networks.',
       'https://cutm.ac.in/international-outreach/',
       ks.id
FROM university u
LEFT JOIN knowledge_sources ks ON ks.url='https://cutm.ac.in/international-outreach/'
WHERE u.short_name='CUTM'
AND NOT EXISTS (
    SELECT 1 FROM international_programs i
    WHERE i.university_id=u.id AND i.title='International Outreach and Collaboration'
);

-- ------------------------------------------------------------
-- 17. OFFICIAL DOCUMENT / PAGE INDEX
-- ------------------------------------------------------------
WITH d(title,document_type,academic_year,description,url) AS (
VALUES
('CUTM Course Fees','FEES','2026','Official programme fee and additional-fee page.','https://cutm.ac.in/course-fees/'),
('CUTM Admission Process','ADMISSION','2026','Official admission process and CUEE information.','https://cutm.ac.in/admission-process/'),
('CUTM Scholarship and Loan','SCHOLARSHIP','2026','Official scholarship information.','https://cutm.ac.in/scholarship-loan/'),
('CUTM Residential Facilities','FACILITY',NULL,'Official hostel and residential facilities information.','https://cutm.ac.in/residential-facilities/'),
('CUTM Campus Facilities','FACILITY',NULL,'Official campus facilities information.','https://cutm.ac.in/campus-facilities/'),
('CUTM Academic Calendar','ACADEMIC_CALENDAR','2026-27','Official academic calendar index and school calendar documents.','https://cutm.ac.in/academic-calendar/'),
('CUTM Entrepreneurship','ENTREPRENEURSHIP',NULL,'Official entrepreneurship information.','https://cutm.ac.in/entrepreneurship/'),
('CUTM International Outreach','INTERNATIONAL',NULL,'Official international outreach information.','https://cutm.ac.in/international-outreach/'),
('CUTM Vision and Mission','UNIVERSITY',NULL,'Official vision and mission page.','https://cutm.ac.in/vision-mission/'),
('CUTM Acts, Statutes and Ordinances','STATUTORY',NULL,'Official statutory documents index.','https://cutm.ac.in/acts-statutes-ordinances/')
)
INSERT INTO official_documents
(university_id,title,document_type,academic_year,description,document_url,official_source_id)
SELECT u.id,d.title,d.document_type,d.academic_year,d.description,d.url,ks.id
FROM d
CROSS JOIN university u
LEFT JOIN knowledge_sources ks ON ks.url=d.url
WHERE u.short_name='CUTM'
AND NOT EXISTS (
    SELECT 1 FROM official_documents x
    WHERE x.university_id=u.id AND x.document_url=d.url
);

-- ------------------------------------------------------------
-- 18. KNOWLEDGE FACTS
-- ------------------------------------------------------------
WITH k(category,subject,fact_key,fact_value,academic_year,source_url) AS (
VALUES
('CAMPUS','University','campus_count','CUTM currently presents 6 campuses in Odisha.','2026', 'https://cutm.ac.in/'),
('EDUCATION','University','skill_courses','CUTM presents 100 skill courses across 12 sectors.','2026','https://cutm.ac.in/'),
('OUTREACH','University','outreach_centers','CUTM presents 40 outreach centers in remote areas.','2026','https://cutm.ac.in/'),
('INDUSTRY','University','industry_sponsored_labs','CUTM presents 52 industry sponsored labs.','2026','https://cutm.ac.in/'),
('PEOPLE','University','staff_count','CUTM presents 2,000 teaching and non-teaching staff.','2026','https://cutm.ac.in/'),
('SDG','University','focused_sdg','CUTM presents 9 focused SDGs.','2026','https://cutm.ac.in/'),
('RESEARCH','University','patents','CUTM presents 50+ patents in its current institutional highlights.','2026','https://cutm.ac.in/'),
('STUDENTS','University','student_count','CUTM presents 20,000+ students in its current institutional highlights.','2026','https://cutm.ac.in/'),
('PLACEMENTS','University','highest_package_2024','CUTM placement highlights for 2024 report a highest package of Rs. 35 lakhs.','2024','https://cutm.ac.in/'),
('PLACEMENTS','University','companies_2024','CUTM placement highlights for 2024 report 150 companies.','2024','https://cutm.ac.in/'),
('PLACEMENTS','University','candidates_placed_2024','CUTM placement highlights for 2024 report 8100 candidates placed.','2024','https://cutm.ac.in/'),
('FACILITIES','University','sports','Campus facilities include swimming, yoga, basketball, cricket, volleyball and gymnasium facilities.',NULL,'https://cutm.ac.in/campus-facilities/'),
('FACILITIES','University','sustainability','Campus facilities include organic farming, rainwater harvesting, vermicomposting and aquarium facilities.',NULL,'https://cutm.ac.in/campus-facilities/'),
('FACILITIES','University','transport','Campus facilities include a bus fleet/ambulance and e-vehicles.',NULL,'https://cutm.ac.in/campus-facilities/'),
('HOSTELS','University','residential','Residential facilities include separate female accommodation, 24-hour power and medical facilities, security, Wi-Fi and ATM facilities.',NULL,'https://cutm.ac.in/residential-facilities/')
)
INSERT INTO knowledge_facts
(university_id,category,subject,fact_key,fact_value,academic_year,official_source_id,verified,verified_at)
SELECT u.id,k.category,k.subject,k.fact_key,k.fact_value,k.academic_year,ks.id,TRUE,CURRENT_TIMESTAMP
FROM k
CROSS JOIN university u
LEFT JOIN knowledge_sources ks ON ks.url=k.source_url
WHERE u.short_name='CUTM'
AND NOT EXISTS (
    SELECT 1 FROM knowledge_facts x
    WHERE x.university_id=u.id AND x.fact_key=k.fact_key
      AND COALESCE(x.academic_year,'')=COALESCE(k.academic_year,'')
);

-- ------------------------------------------------------------
-- 19. SUMMARY CHECKS
-- ------------------------------------------------------------
SELECT 'school_campuses' AS table_name, COUNT(*) AS rows FROM school_campuses
UNION ALL
SELECT 'programs', COUNT(*) FROM programs
UNION ALL
SELECT 'program_campuses', COUNT(*) FROM program_campuses
UNION ALL
SELECT 'program_eligibility', COUNT(*) FROM program_eligibility
UNION ALL
SELECT 'program_fees', COUNT(*) FROM program_fees
UNION ALL
SELECT 'additional_fees', COUNT(*) FROM additional_fees
UNION ALL
SELECT 'admissions', COUNT(*) FROM admissions
UNION ALL
SELECT 'scholarships', COUNT(*) FROM scholarships
UNION ALL
SELECT 'hostel_details', COUNT(*) FROM hostel_details
UNION ALL
SELECT 'facility_details', COUNT(*) FROM facility_details
UNION ALL
SELECT 'academic_calendars', COUNT(*) FROM academic_calendars
UNION ALL
SELECT 'placements', COUNT(*) FROM placements
UNION ALL
SELECT 'research', COUNT(*) FROM research
UNION ALL
SELECT 'entrepreneurship', COUNT(*) FROM entrepreneurship
UNION ALL
SELECT 'international_programs', COUNT(*) FROM international_programs
UNION ALL
SELECT 'official_documents', COUNT(*) FROM official_documents
UNION ALL
SELECT 'knowledge_facts', COUNT(*) FROM knowledge_facts
ORDER BY table_name;

COMMIT;

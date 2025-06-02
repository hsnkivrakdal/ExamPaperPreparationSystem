-- Create database schema for Exam Paper Preparation System

CREATE TABLE IF NOT EXISTS universities (
    UniversityId INT AUTO_INCREMENT PRIMARY KEY,
    UniversityName VARCHAR(50) NOT NULL,
    UniversityAddress VARCHAR(256) NOT NULL,
    UniversityWebsite VARCHAR(60) NOT NULL
);

CREATE TABLE IF NOT EXISTS faculties (
    FacultyId INT AUTO_INCREMENT PRIMARY KEY,
    FacultyName VARCHAR(50) NOT NULL,
    UniversityId INT,
    FOREIGN KEY (UniversityId) REFERENCES universities(UniversityId)
);

CREATE TABLE IF NOT EXISTS departments (
    DepartmentId INT AUTO_INCREMENT PRIMARY KEY,
    DepartmentName VARCHAR(50) NOT NULL,
    FacultyId INT,
    FOREIGN KEY (FacultyId) REFERENCES faculties(FacultyId)
);

CREATE TABLE IF NOT EXISTS programs (
    ProgramId INT AUTO_INCREMENT PRIMARY KEY,
    ProgramName VARCHAR(50) NOT NULL,
    DepartmentId INT,
    FOREIGN KEY (DepartmentId) REFERENCES departments(DepartmentId)
);

CREATE TABLE IF NOT EXISTS academicterms (
    AcademicTermId INT AUTO_INCREMENT PRIMARY KEY,
    AcademicTermName VARCHAR(50) NOT NULL,
    AcademicTermStartDate DATE NOT NULL,
    AcademicTermEndDate DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS lecturertitles (
    LecturerTitleId INT AUTO_INCREMENT PRIMARY KEY,
    LecturerTitleName VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS users (
    UserId INT AUTO_INCREMENT PRIMARY KEY,
    UserFirstName VARCHAR(50) NOT NULL,
    UserLastName VARCHAR(50) NOT NULL,
    UserEmail VARCHAR(50) NOT NULL UNIQUE,
    UserPassword VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS lecturers (
    LecturerId INT AUTO_INCREMENT PRIMARY KEY,
    LecturerFirstName VARCHAR(50) NOT NULL,
    LecturerLastName VARCHAR(50) NOT NULL,
    LecturerEmail VARCHAR(50) NOT NULL,
    LecturerPhoneNumber VARCHAR(15),
    LecturerTitleId INT,
    DepartmentId INT,
    UserId INT,
    FOREIGN KEY (LecturerTitleId) REFERENCES lecturertitles(LecturerTitleId),
    FOREIGN KEY (DepartmentId) REFERENCES departments(DepartmentId),
    FOREIGN KEY (UserId) REFERENCES users(UserId)
);

CREATE TABLE IF NOT EXISTS courses (
    CourseId INT AUTO_INCREMENT PRIMARY KEY,
    CourseName VARCHAR(50) NOT NULL,
    CourseCode VARCHAR(10) NOT NULL,
    CourseCredit TINYINT,
    DepartmentId INT,
    FOREIGN KEY (DepartmentId) REFERENCES departments(DepartmentId)
);

CREATE TABLE IF NOT EXISTS courselecturers (
    CourseLecturerId INT AUTO_INCREMENT PRIMARY KEY,
    CourseId INT,
    LecturerId INT,
    AcademicTermId INT,
    FOREIGN KEY (CourseId) REFERENCES courses(CourseId),
    FOREIGN KEY (LecturerId) REFERENCES lecturers(LecturerId),
    FOREIGN KEY (AcademicTermId) REFERENCES academicterms(AcademicTermId)
);

CREATE TABLE IF NOT EXISTS examtypes (
    ExamTypeId INT AUTO_INCREMENT PRIMARY KEY,
    ExamTypeName VARCHAR(50) NOT NULL,
    ExamTypeDescription VARCHAR(256)
);

CREATE TABLE IF NOT EXISTS coursesexams (
    CourseExamId INT AUTO_INCREMENT PRIMARY KEY,
    CourseExamDate DATE NOT NULL,
    CourseExamStartTime TIME NOT NULL,
    CourseExamEndTime TIME NOT NULL,
    CourseLecturerId INT,
    ExamTypeId INT,
    FOREIGN KEY (CourseLecturerId) REFERENCES courselecturers(CourseLecturerId),
    FOREIGN KEY (ExamTypeId) REFERENCES examtypes(ExamTypeId)
);

CREATE TABLE IF NOT EXISTS exampapers (
    ExamPaperId INT AUTO_INCREMENT PRIMARY KEY,
    ExamPaperTime SMALLINT,
    ExamVersion VARCHAR(60),
    CourseExamId INT,
    LecturerId INT,
    FOREIGN KEY (CourseExamId) REFERENCES coursesexams(CourseExamId),
    FOREIGN KEY (LecturerId) REFERENCES lecturers(LecturerId)
);

CREATE TABLE IF NOT EXISTS questiontypes (
    QuestionTypeId INT AUTO_INCREMENT PRIMARY KEY,
    QuestionTypeName VARCHAR(50) NOT NULL,
    QuestionTypeDescription VARCHAR(256)
);

CREATE TABLE IF NOT EXISTS examquestions (
    ExamQuestionId INT AUTO_INCREMENT PRIMARY KEY,
    ExamQuestionContent TEXT NOT NULL,
    ExamQuestionPoint SMALLINT,
    ExamQuestionDifficulty TINYINT,
    CourseId INT,
    QuestionTypeId INT,
    FOREIGN KEY (CourseId) REFERENCES courses(CourseId),
    FOREIGN KEY (QuestionTypeId) REFERENCES questiontypes(QuestionTypeId)
);

CREATE TABLE IF NOT EXISTS exampaperquestions (
    ExamPaperQuestionId INT AUTO_INCREMENT PRIMARY KEY,
    ExamPaperId INT,
    ExamQuestionId INT,
    FOREIGN KEY (ExamPaperId) REFERENCES exampapers(ExamPaperId),
    FOREIGN KEY (ExamQuestionId) REFERENCES examquestions(ExamQuestionId)
);

CREATE TABLE IF NOT EXISTS questionoptions (
    QuestionOptionId INT AUTO_INCREMENT PRIMARY KEY,
    QuestionOptionContent VARCHAR(256) NOT NULL,
    QuestionOptionIsCorrect BOOLEAN NOT NULL DEFAULT FALSE,
    ExamQuestionId INT,
    FOREIGN KEY (ExamQuestionId) REFERENCES examquestions(ExamQuestionId)
);

CREATE TABLE IF NOT EXISTS roles (
    RoleId INT AUTO_INCREMENT PRIMARY KEY,
    RoleTitle VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS userinroles (
    UserInRoles INT AUTO_INCREMENT PRIMARY KEY,
    UserId INT,
    RoleId INT,
    FOREIGN KEY (UserId) REFERENCES users(UserId),
    FOREIGN KEY (RoleId) REFERENCES roles(RoleId)
); 
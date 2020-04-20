CREATE TABLE Student(StudentId int,studentName varchar(64), Major varchar(4));
 
CREATE TABLE Course(DeptCode varchar(48), CourseNum varchar(10), Title varchar(64),CreditHours decimal(3,2)); 

CREATE TABLE Enrollment(StudentId int,DeptCode varchar(48), CourseNum varchar(10));

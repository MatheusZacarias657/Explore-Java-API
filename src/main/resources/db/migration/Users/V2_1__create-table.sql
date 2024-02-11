CREATE TABLE Users
(
    id           INT PRIMARY KEY  IDENTITY(1,1),
    login         NVARCHAR(100) NOT NULL,
    password        NVARCHAR(255) NOT NULL
);
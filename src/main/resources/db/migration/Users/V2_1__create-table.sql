CREATE TABLE Users
(
    id           INT PRIMARY KEY  IDENTITY(1,1),
    login         NVARCHAR(100) NOT NULL,
    password        NVARCHAR(255) NOT NULL
);

Insert into Users (login, password) Values ('admin@admin.com', '$2a$12$rQ127Lggk.q2d5RsMZfx4ef0dSJQg7zajXto9LllBF78u4sksgB8q')
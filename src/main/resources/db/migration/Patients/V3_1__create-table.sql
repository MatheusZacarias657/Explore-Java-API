CREATE TABLE Patients
(
    id           INT PRIMARY KEY  IDENTITY(1,1),
    name         NVARCHAR(255) NOT NULL,
    email        NVARCHAR(255) NOT NULL,
    telephone    NVARCHAR(20) NOT NULL,
    cpf          NVARCHAR(50) NOT NULL,
    active       BIT DEFAULT 1 not null,

    street       NVARCHAR(255) NOT NULL,
    neighborhood NVARCHAR(255) NOT NULL,
    zipcode      NVARCHAR(20) NOT NULL,
    city         NVARCHAR(100) NOT NULL,
    state        NVARCHAR(50) NOT NULL,
    number       NVARCHAR(20),
    complement   NVARCHAR(255),
);
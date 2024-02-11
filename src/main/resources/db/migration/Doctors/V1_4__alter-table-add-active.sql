ALTER TABLE dbo.Doctors
    ADD alive BIT DEFAULT 1;
go

UPDATE dbo.Doctors SET alive = 1;

ALTER TABLE dbo.Doctors
ALTER COLUMN alive BIT NOT NULL;

ALTER TABLE dbo.Doctors
    ADD active BIT DEFAULT 1;
go

UPDATE dbo.Doctors SET active = 1;

ALTER TABLE dbo.Doctors
ALTER COLUMN active BIT NOT NULL;

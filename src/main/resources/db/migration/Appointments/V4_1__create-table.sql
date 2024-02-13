CREATE TABLE Appointments
(
    id           INT PRIMARY KEY  IDENTITY(1,1),
    id_patient    INT NOT NULL,
    id_doctor     INT NOT NULL,
    date         Datetime not null,

    constraint fk_Appointment_doctor_id foreign key (id_doctor) references Doctors(id),
    constraint fk_Appointment_patient_id foreign key (id_patient) references Patients(id),

);
 #!/bin/bash

    export LD_LIBRARY_PATH=/usr/lib/oracle/12.1/client64/lib

    username="******"
    password="******"

    sqlplus64 "$username/$password@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(Host=oracle.scs.ryerson.ca)(Port=1521))(CONNECT_DATA=(SID=orcl)))>

CREATE TABLE Hotel (
    Hotel_ID INTEGER PRIMARY KEY, 
    Name VARCHAR(50) NOT NULL, 
    Location VARCHAR(100) NOT NULL, 
    Rating INTEGER NOT NULL CHECK (Rating BETWEEN 1 AND 5), 
    NumberOfRooms INTEGER NOT NULL CHECK (NumberOfRooms > 0)
);

CREATE TABLE Address (
    Address_ID INTEGER PRIMARY KEY, 
    Street VARCHAR(30) NOT NULL, 
    PostalCode VARCHAR(6) NOT NULL, 
    City VARCHAR(30) NOT NULL, 
    Province VARCHAR(15) DEFAULT 'ONTARIO'
);

CREATE TABLE Guest (
    Guest_ID INTEGER PRIMARY KEY, 
    Name VARCHAR(50) NOT NULL, 
    Address_ID INTEGER NOT NULL, 
    PhoneNumber VARCHAR(12) NOT NULL, 
    Email VARCHAR(50) NOT NULL, 
    PaymentMethod VARCHAR(10) CHECK (PaymentMethod IN ('Card', 'Cash')), 
    FOREIGN KEY (Address_ID) REFERENCES Address(Address_ID)
);

CREATE TABLE Room (
    Room_ID INTEGER PRIMARY KEY, 
    Hotel_ID INTEGER NOT NULL, 
    RoomType VARCHAR(20) NOT NULL, 
    Price DECIMAL(10, 2) NOT NULL CHECK (Price >= 0), 
    AvailabilityStatus VARCHAR(10) NOT NULL CHECK (AvailabilityStatus IN ('Available', 'Occupied')), 
    FOREIGN KEY (Hotel_ID) REFERENCES Hotel(Hotel_ID)
);

CREATE TABLE Reservation (
    Reservation_ID INTEGER NOT NULL, 
    Guest_ID INTEGER NOT NULL, 
    Room_ID INTEGER NOT NULL, 
    CheckInDate DATE NOT NULL, 
    CheckOutDate DATE NOT NULL, 
    NumberOfGuests INTEGER NOT NULL, 
    PRIMARY KEY (Reservation_ID, Guest_ID), 
    FOREIGN KEY (Guest_ID) REFERENCES Guest(Guest_ID), 
    FOREIGN KEY (Room_ID) REFERENCES Room(Room_ID)
);

ALTER TABLE Reservation 
ADD CONSTRAINT unique_reservation_id UNIQUE (Reservation_ID);

CREATE TABLE Bill (
    Bill_ID INTEGER PRIMARY KEY, 
    Reservation_ID INTEGER NOT NULL, 
    Amount DECIMAL(10, 2) NOT NULL CHECK (Amount >= 0), 
    PaymentStatus VARCHAR(20) NOT NULL CHECK (PaymentStatus IN ('Paid', 'Unpaid')), 
    BillingDate DATE NOT NULL, 
    FOREIGN KEY (Reservation_ID) REFERENCES Reservation(Reservation_ID)
);

CREATE TABLE Staff (
    Staff_ID INTEGER PRIMARY KEY, 
    Hotel_ID INTEGER NOT NULL, 
    Name VARCHAR(50) NOT NULL, 
    Position VARCHAR(30) NOT NULL, 
    PhoneNumber VARCHAR(12) NOT NULL, 
    FOREIGN KEY (Hotel_ID) REFERENCES Hotel(Hotel_ID)
);

CREATE TABLE FullTime_Staff (
    Staff_ID INTEGER PRIMARY KEY, 
    Salary DECIMAL(10, 2) NOT NULL, 
    FOREIGN KEY (Staff_ID) REFERENCES Staff(Staff_ID)
);

CREATE TABLE PartTime_Staff (
    Staff_ID INTEGER PRIMARY KEY, 
    HourlyRate DECIMAL(6, 2) NOT NULL, 
    FOREIGN KEY (Staff_ID) REFERENCES Staff(Staff_ID)
);

CREATE TABLE Service (
    Service_ID INTEGER PRIMARY KEY, 
    Hotel_ID INTEGER NOT NULL, 
    Name VARCHAR(50) NOT NULL, 
    Price DECIMAL(10, 2) NOT NULL CHECK (Price >= 0), 
    Description VARCHAR(255) NOT NULL, 
    FOREIGN KEY (Hotel_ID) REFERENCES Hotel(Hotel_ID)
);

CREATE TABLE Uses (
    Guest_ID INTEGER, 
    Service_ID INTEGER, 
    PRIMARY KEY (Guest_ID, Service_ID), 
    FOREIGN KEY (Guest_ID) REFERENCES Guest(Guest_ID), 
    FOREIGN KEY (Service_ID) REFERENCES Service(Service_ID)
);
EOF

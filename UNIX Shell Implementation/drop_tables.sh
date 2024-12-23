#!/bin/bash

    export LD_LIBRARY_PATH=/usr/lib/oracle/12.1/client64/lib

    username="******"
    password="******"

    sqlplus64 "$username/$password@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(Host=oracle.scs.ryerson.ca)(Port=1521))(CONNECT_DATA=(SID=orcl)))>

DROP TABLE Uses;

DROP TABLE FullTime_Staff;
DROP TABLE PartTime_Staff;

DROP TABLE Service;
DROP TABLE Bill;
DROP TABLE Reservation;
DROP TABLE Room;
DROP TABLE Staff;
DROP TABLE Guest;
DROP TABLE Address;
DROP TABLE Hotel;
quit
EOF

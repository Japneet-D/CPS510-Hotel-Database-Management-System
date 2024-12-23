#!/bin/bash

    export LD_LIBRARY_PATH=/usr/lib/oracle/12.1/client64/lib

    username="******"
    password="******"

        echo '1. List all Guests who have booked rooms in Hotels with a 5-star rating.'
        echo '2. List all Hotels with No Part-Time Staff Assigned'
        echo '3. Lists Full-time Staff whose salary is greater than the average salary of all full-time staff at their Hotel.'
        echo '4. Lists all Staff members, inlcuding ID, Name, and Employment Type'
        echo '5. Lists the names of Hotels that have at least one available room'

    read input

        if [ "$input" = "1" ]; then
         sqlplus64 "$username/$password@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(Host=oracle.scs.ryerson.ca)(Port=1521))(CONNECT_DATA=(SID=or>
     elif [ "$input" = "2" ]; then
         sqlplus64 "$username/$password@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(Host=oracle.scs.ryerson.ca)(Port=1521))(CONNECT_DATA=(SID=or>
     elif [ "$input" = "3" ]; then
         sqlplus64 "$username/$password@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(Host=oracle.scs.ryerson.ca)(Port=1521))(CONNECT_DATA=(SID=or>
     elif [ "$input" = "4" ]; then
         sqlplus64 "$username/$password@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(Host=oracle.scs.ryerson.ca)(Port=1521))(CONNECT_DATA=(SID=or>
     elif [ "$input" = "5" ]; then
             sqlplus64 "$username/$password@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(Host=oracle.scs.ryerson.ca)(Port=1521))(CONNECT_DATA=(SI>
     else
         echo 'Invalid Input'
     fi

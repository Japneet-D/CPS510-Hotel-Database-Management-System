#!/bin/bash

echo $'\n\nWelcome to the Hotel Database Management System! Type the corresponding digit to get said result!\n\n'
echo '1: Create Tables'
echo '2: Populate Tables'
echo '3: Drop Tables'
echo '4: Query Tables'
echo 'q: Quit/Exit'

read input

while [ "$input" != "q" ] && [ "$input" != "Q" ];
do
    if [ "$input" = "1" ]; then
        echo 'You have chosen option 1'
        ./create_tables.sh
    elif [ "$input" = "2" ]; then
        echo 'You have chosen option 2'
        ./populate_tables.sh
    elif [ "$input" = "3" ]; then
        echo 'You have chosen option 3'
        ./drop_tables.sh
    elif [ "$input" = "4" ]; then
        echo 'You have chosen option 4'
        ./queries.sh
    else
        echo 'Invalid Input'
    fi

    echo $'\n'
    echo '1: Create Tables'
    echo '2: Populate Tables'
    echo '3: Drop Tables'
    echo '4: Query Tables'
    echo 'q: Quit'

    read input
done

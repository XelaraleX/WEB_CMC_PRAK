DROP TABLE IF EXISTS Aircraft CASCADE;
DROP TABLE IF EXISTS Flights CASCADE;
DROP TABLE IF EXISTS Airports CASCADE;
DROP TABLE IF EXISTS Tickets CASCADE;
DROP TABLE IF EXISTS Users CASCADE;
DROP TABLE IF EXISTS BonusProgram CASCADE;
DROP TABLE IF EXISTS Airlines CASCADE;

CREATE TABLE IF NOT EXISTS Aircraft(
    aircraft_id SERIAL PRIMARY KEY,
    model_name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS Airports(
    airport_id SERIAL PRIMARY KEY,
    airport_name TEXT NOT NULL,
    airport_town TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS Users(
    user_id SERIAL PRIMARY KEY,
    user_status TEXT NOT NULL,
    full_name TEXT NOT NULL,
    address TEXT,
    email TEXT NOT NULL,
    phone_number TEXT NOT NULL,
    user_login TEXT NOT NULL,
    user_password TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS Airlines(
    airline_id SERIAL PRIMARY KEY,
    airline_name TEXT NOT NULL,
    airline_email TEXT,
    phone_number TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS Flights(
    flight_id SERIAL PRIMARY KEY,
    airline_id INTEGER REFERENCES Airlines ON DELETE CASCADE NOT NULL,
    airport_id_dep INTEGER REFERENCES Airports ON DELETE CASCADE NOT NULL,
    airport_id_arr INTEGER REFERENCES Airports ON DELETE CASCADE NOT NULL,
    aircraft_id INTEGER REFERENCES Aircraft ON DELETE CASCADE NOT NULL,
    time_dep TIMESTAMP NOT NULL ,
    time_arr TIMESTAMP NOT NULL,
    flight_cost INTEGER CHECK ( flight_cost > 0 ) NOT NULL,
    cnt_seats INTEGER CHECK ( cnt_seats > 0 ) NOT NULL,
    cnt_available_seats INTEGER CHECK ( cnt_available_seats >= 0 ) NOT NULL
);

CREATE TABLE IF NOT EXISTS Tickets(
    ticket_id SERIAL PRIMARY KEY,
    flight_id INTEGER REFERENCES Flights ON DELETE CASCADE NOT NULL,
    status TEXT NOT NULL,
    user_id INTEGER REFERENCES Users ON DELETE CASCADE NOT NULL
);

CREATE TABLE IF NOT EXISTS BonusProgram(
    bonus_id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES Users ON DELETE CASCADE NOT NULL,
    airline_id INTEGER REFERENCES Airlines ON DELETE  CASCADE NOT NULL,
    bonus_card TEXT NOT NULL,
    cnt_km INTEGER CHECK ( cnt_km >= 0 ) NOT NULL,
    cnt_use_km INTEGER CHECK ( cnt_use_km >= 0 ) NOT NULL
);

INSERT INTO Aircraft (aircraft_id, model_name)
    VALUES (1, 'Boeing 777-300'),
           (2, 'Boeing 767-300'),
           (3, 'Sukhoi Superjet-100'),
           (4, 'Airbus A320-200'),
           (5, 'Boeing 737-300');

INSERT INTO Airports (airport_id, airport_name, airport_town)
VALUES (1, 'Domodedovo International Airport', 'Moscow'),
       (2, 'Bratsk Airport', 'Bratsk'),
       (3, 'Pulkovo Airport', 'Saint Petersburg'),
       (4, 'Tolmachevo Airport', 'Novosibirsk'),
       (5, 'Kazan International Airport', 'Kazan');

INSERT INTO Users (user_id, user_status, full_name, address, email, phone_number, user_login, user_password)
VALUES (1, 'admin', 'admin', NULL, 'admin@m.ru', '+77777777', 'alexander', 'ZZ62ph0a7p'),
       (2, 'user', 'Ivanov Mikhail', NULL, 'mikhail@m.ru', '+77777778', 'mikhail', 'ljIr0rcDRe'),
       (3, 'user', 'Stepanov Alexey', NULL, 'alexey@m.ru', '+77777779', 'alexey', 'sH8a93HMNl'),
       (4, 'user', 'Petrov Vladimir', NULL, 'vladimir@m.ru', '+77777738', 'vladimir', 'aUylpdbBr0'),
       (5, 'user', 'Ignatov Daniil', NULL, 'daniil@m.ru', '+77777798', 'danya', 'W0u9zYqcjn');

INSERT INTO Airlines (airline_id, airline_name, airline_email, phone_number)
VALUES (1, 'Aeroflot', NULL, '88004445555'),
       (2, 'S7 Airlines', NULL, '88007000707'),
       (3, 'Nordwind Airlines', NULL, '84957305080'),
       (4, 'Pegas Fly', NULL, '84954784944'),
       (5, 'Utair', NULL, '88002340088');

INSERT INTO Flights (flight_id, airline_id, airport_id_dep, airport_id_arr,
                     aircraft_id, time_dep, time_arr, flight_cost, cnt_seats,
                     cnt_available_seats)
    VALUES (1, 1, 1, 2, 1, '2022-03-10 09:50:00+03', '2022-03-10 14:55:00', 13000, 550, 0),
           (2, 2, 2, 1, 2, '2022-03-11 09:50:00+03', '2022-03-11 14:45:00', 16000, 375, 0),
           (3, 3, 1, 3, 3, '2022-03-15 09:35:00+03', '2022-03-15 10:30:00', 4000, 103, 0),
           (4, 4, 1, 4, 4, '2022-04-01 11:05:00+03', '2022-04-01 14:30:00', 9000, 180, 21),
           (5, 5, 1, 5, 5, '2022-05-01 10:40:00+03', '2017-05-01 11:35:00', 5000, 189, 189);


INSERT INTO  Tickets (ticket_id, flight_id, status, user_id)
    VALUES (1, 1, 'paid', 2),
           (2, 1, 'paid', 2),
           (3, 2, 'paid', 3),
           (4, 3, 'paid', 4),
           (5, 4, 'paid', 5);

INSERT INTO BonusProgram (bonus_id, user_id, airline_id, bonus_card, cnt_km, cnt_use_km)
    VALUES (1, 1, 1, '+', 123, 123),
           (2, 2, 2, '-', 1234, 0),
           (3, 3, 3, '+', 2345, 1000),
           (4, 4, 4, '-', 10000, 0),
           (5, 5, 5, '-', 0, 0);
DROP TABLE IF EXISTS Aircraft CASCADE;
DROP TABLE IF EXISTS Flights CASCADE;
DROP TABLE IF EXISTS Airports CASCADE;
DROP TABLE IF EXISTS AccountsLP CASCADE;
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
    width REAL NOT NULL,
    longitude REAL NOT NULL,
    timezone TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS Users(
    user_id SERIAL PRIMARY KEY,
    user_status TEXT NOT NULL,
    full_name TEXT NOT NULL,
    address TEXT,
    email TEXT NOT NULL,
    phone_number TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS AccountsLP(
    user_id INTEGER REFERENCES Users ON DELETE CASCADE NOT NULL,
    user_login TEXT NOT NULL,
    user_password TEXT NOT NULL,
    PRIMARY KEY (user_id)
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
    time_dep TIMESTAMP WITH TIME ZONE NOT NULL,
    time_arr TIMESTAMP WITH TIME ZONE NOT NULL,
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
    user_id INTEGER REFERENCES Users ON DELETE CASCADE NOT NULL,
    airline_id INTEGER REFERENCES Airlines ON DELETE  CASCADE NOT NULL,
    bonus_card TEXT NOT NULL,
    cnt_km INTEGER CHECK ( cnt_km >= 0 ) NOT NULL,
    cnt_use_km INTEGER CHECK ( cnt_use_km >= 0 ) NOT NULL,
    PRIMARY KEY (user_id, airline_id)
);

INSERT INTO Aircraft (aircraft_id, model_name)
    VALUES (1, 'Boeing 777-300'),
           (2, 'Boeing 767-300'),
           (3, 'Sukhoi Superjet-100'),
           (4, 'Airbus A320-200'),
           (5, 'Boeing 737-300');

INSERT INTO Airports (airport_id, airport_name, width, longitude, timezone)
VALUES (1, 'Domodedovo International Airport', 37.9062995910644531, 55.4087982177734375, 'Europe/Moscow'),
       (2, 'Bratsk Airport', 101.697998046875, 56.3706016540527344, 'Asia/Irkutsk'),
       (3, 'Pulkovo Airport', 30.2625007629394531, 59.8003005981445312, 'Europe/Moscow'),
       (4, 'Tolmachevo Airport', 82.6507034301759944, 55.012599945067997, 'Asia/Novosibirsk'),
       (5, 'Kazan International Airport', 49.278701782227003, 55.606201171875, 'Europe/Moscow');

INSERT INTO Users (user_id, user_status, full_name, address, email, phone_number)
VALUES (1, 'admin', 'admin', NULL, 'admin@m.ru', '+77777777'),
       (2, 'user', 'Ivanov Mikhail', NULL, 'mikhail@m.ru', '+77777778'),
       (3, 'user', 'Stepanov Alexey', NULL, 'alexey@m.ru', '+77777779'),
       (4, 'user', 'Petrov Vladimir', NULL, 'vladimir@m.ru', '+77777738'),
       (5, 'user', 'Ignatov Daniil', NULL, 'daniil@m.ru', '+77777798');

INSERT INTO AccountsLP (user_id, user_login, user_password)
VALUES (1, 'alexander', 'ZZ62ph0a7p'),
       (2, 'mikhail', 'ljIr0rcDRe'),
       (3, 'alexey', 'sH8a93HMNl'),
       (4, 'vladimir', 'aUylpdbBr0'),
       (5, 'danya', 'W0u9zYqcjn');

INSERT INTO Airlines (airline_id, airline_name, airline_email, phone_number)
VALUES (1, 'Aeroflot', NULL, '88004445555'),
       (2, 'S7 Airlines', NULL, '88007000707'),
       (3, 'Nordwind Airlines', NULL, '84957305080'),
       (4, 'Pegas Fly', NULL, '84954784944'),
       (5, 'Utair', NULL, '88002340088');

INSERT INTO Flights (flight_id, airline_id, airport_id_dep, airport_id_arr,
                     aircraft_id, time_dep, time_arr, flight_cost, cnt_seats,
                     cnt_available_seats)
    VALUES (1, 1, 1, 2, 1, '2022-03-10 09:50:00+03', '2022-03-10 14:55:00+03', 13000, 550, 0),
           (2, 2, 2, 1, 2, '2022-03-11 09:50:00+03', '2022-03-11 14:45:00+03', 16000, 375, 0),
           (3, 3, 1, 3, 3, '2022-03-15 09:35:00+03', '2022-03-15 10:30:00+03', 4000, 103, 0),
           (4, 4, 1, 4, 4, '2022-04-01 11:05:00+03', '2022-04-01 14:30:00+03', 9000, 180, 21),
           (5, 5, 1, 5, 5, '2022-05-01 10:40:00+03', '2017-05-01 11:35:00+03', 5000, 189, 189);


INSERT INTO  Tickets (ticket_id, flight_id, status, user_id)
    VALUES (1, 1, 'paid', 2),
           (2, 1, 'paid', 2),
           (3, 2, 'paid', 3),
           (4, 3, 'paid', 4),
           (5, 4, 'paid', 5);

INSERT INTO BonusProgram (user_id, airline_id, bonus_card, cnt_km, cnt_use_km)
    VALUES (1, 1, '+', 123, 123),
           (2, 2, '-', 1234, 0),
           (3, 3, '+', 2345, 1000),
           (4, 4, '-', 10000, 0),
           (5, 5, '-', 0, 0);
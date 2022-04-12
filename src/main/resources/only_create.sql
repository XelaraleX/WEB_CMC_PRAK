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
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS trips CASCADE;
DROP TABLE IF EXISTS locations CASCADE;
DROP TABLE IF EXISTS route_points CASCADE;
DROP TABLE IF EXISTS events CASCADE;

CREATE TABLE IF NOT EXISTS users (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL DEFAULT NOW()
);


CREATE TABLE IF NOT EXISTS trips (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    budget NUMERIC(12, 2) CHECK (budget >= 0),
    status VARCHAR(20) NOT NULL CHECK (status IN ('PLANNED', 'ACTIVE', 'COMPLETED')),
    owner_id BIGINT NOT NULL,
    CONSTRAINT fk_trip_owner FOREIGN KEY (owner_id) REFERENCES users(id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS locations (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    city VARCHAR(100),
    country VARCHAR(100),
    address TEXT,
    latitude DOUBLE PRECISION,
    longitude DOUBLE PRECISION
);


CREATE TABLE IF NOT EXISTS route_points (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    trip_id BIGINT NOT NULL,
    location_id BIGINT NOT NULL,
    day_order INTEGER NOT NULL,
    note TEXT,
    CONSTRAINT fk_routepoint_trip FOREIGN KEY (trip_id) REFERENCES trips(id) ON DELETE CASCADE,
    CONSTRAINT fk_routepoint_location FOREIGN KEY (location_id) REFERENCES locations(id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS events (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    trip_id BIGINT NOT NULL,
    name VARCHAR(100) NOT NULL,
    location_id BIGINT NOT NULL,
    event_date_time TIMESTAMP NOT NULL,
    price NUMERIC(12, 2),
    description TEXT,
    CONSTRAINT fk_event_trip FOREIGN KEY (trip_id) REFERENCES trips(id) ON DELETE CASCADE,
    CONSTRAINT fk_event_location FOREIGN KEY (location_id) REFERENCES locations(id) ON DELETE CASCADE
);

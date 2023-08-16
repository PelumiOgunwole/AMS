
CREATE TABLE aircraft (
    aircraft_id INT PRIMARY KEY,
    aircraft_code VARCHAR(10) NOT NULL,
    aircraft_model VARCHAR(50) NOT NULL

);

CREATE TABLE component (
    component_id INT PRIMARY KEY,
    component_name VARCHAR(50) NOT NULL

);

CREATE TABLE serial_number (
    serial_number_id INT PRIMARY KEY,
    serial_number_code VARCHAR(20) NOT NULL

);

CREATE TABLE maintenance_record (
    record_id INT PRIMARY KEY,
    maintenance_date DATE NOT NULL

);

CREATE TABLE worker (
    worker_id INT PRIMARY KEY,
    worker_name VARCHAR(50) NOT NULL,
    specialization VARCHAR(50) NOT NULL

);

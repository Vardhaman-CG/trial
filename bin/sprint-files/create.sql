DROP DATABASE IF EXISTS busticketbooking;

CREATE DATABASE busticketbooking;

USE busticketbooking;


-- Agency Table
CREATE TABLE agencies (
    agency_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    contact_person_name VARCHAR(30) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(15) NOT NULL
);

-- Address Table
CREATE TABLE addresses (
    address_id INT PRIMARY KEY AUTO_INCREMENT,
    address VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    state VARCHAR(255) NOT NULL,
    zip_code VARCHAR(10) NOT NULL
);

-- Offices Table
CREATE TABLE agency_offices(
	office_id INT PRIMARY KEY AUTO_INCREMENT,
	agency_id INT,
	office_mail VARCHAR(100),
	office_contact_person_name VARCHAR(50),
	office_contact_number CHAR(10),
	office_address_id INT,
	FOREIGN KEY (agency_id) REFERENCES agencies(agency_id),
	FOREIGN KEY (office_address_id) REFERENCES addresses(address_id)
);

-- Customers Table
CREATE TABLE customers (
    customer_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    address_id INT,
    FOREIGN KEY (address_id) REFERENCES addresses(address_id)
);

-- Buses Table
CREATE TABLE buses (
    bus_id INT PRIMARY KEY AUTO_INCREMENT,
    office_id INT NOT NULL,
    registration_number VARCHAR(20) NOT NULL,
    capacity INT NOT NULL,
    type VARCHAR(30) NOT NULL,
	FOREIGN KEY (office_id) REFERENCES agency_offices(office_id)
   
);

-- Drivers Table
CREATE TABLE drivers (
    driver_id INT PRIMARY KEY AUTO_INCREMENT,
	license_number VARCHAR(20) NOT NULL,    
	name VARCHAR(255) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    office_id INT,
	address_id INT,
	FOREIGN KEY (office_id) REFERENCES agency_offices(office_id),
	FOREIGN KEY (address_id) REFERENCES addresses(address_id)
);

-- Routes Table
CREATE TABLE routes (
    route_id INT PRIMARY KEY AUTO_INCREMENT,
    from_city VARCHAR(255) NOT NULL,
    to_city VARCHAR(255) NOT NULL,
    break_points INT,
    duration INT
);
-- Trips Schedule Table
CREATE TABLE trips(
    trip_id INT PRIMARY KEY AUTO_INCREMENT,
    route_id INT NOT NULL,
    bus_id INT NOT NULL,
    boarding_address_id INT NOT NULL,
    dropping_address_id INT NOT NULL,
    departure_time DATETIME NOT NULL,
    arrival_time DATETIME NOT NULL,
    driver1_driver_id INT NOT NULL,
    driver2_driver_id INT NOT NULL,
    available_seats INT NOT NULL,
    fare DECIMAL(10, 2) NOT NULL,
    trip_date DATETIME NOT NULL,
    FOREIGN KEY (bus_id) REFERENCES buses(bus_id),
    FOREIGN KEY (driver1_driver_id) REFERENCES drivers(driver_id),
    FOREIGN KEY (driver2_driver_id) REFERENCES drivers(driver_id),
    FOREIGN KEY (route_id) REFERENCES routes(route_id)
);

-- Bookings Table
CREATE TABLE bookings (
    booking_id INT PRIMARY KEY AUTO_INCREMENT,
    trip_id INT,
    seat_number INT NOT NULL,
    status ENUM("Available", "Booked") DEFAULT "Available",
    FOREIGN KEY (trip_id) REFERENCES trips(trip_id)
);

-- Payments table
CREATE TABLE payments (
    payment_id INT PRIMARY KEY AUTO_INCREMENT,
    booking_id INT NOT NULL,
    customer_id INT,
    amount DECIMAL(10, 2),
    payment_date DATETIME,
    payment_status ENUM("Success", "Failed"),
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id),
    FOREIGN KEY (booking_id) REFERENCES bookings(booking_id)
);

-- Reviews table
CREATE TABLE reviews (
    review_id INT PRIMARY KEY,
    customer_id INT NOT NULL,
    trip_id INT NOT NULL,
    rating INT NOT NULL,
    comment TEXT,
    review_date DATETIME,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id),
    FOREIGN KEY (trip_id) REFERENCES trips(trip_id)
);


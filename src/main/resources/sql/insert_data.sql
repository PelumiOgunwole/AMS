
-- Insert example data for aircraft table
INSERT INTO aircraft (aircraft_id, name, model, manufacturer, registration_number) VALUES
(1, 'Aircraft One', 'Model X', 'Manufacturer A', 'REG-123'),
(2, 'Aircraft Two', 'Model Y', 'Manufacturer B', 'REG-456');

-- Insert example data for component table
INSERT INTO components (component_id, component_name, manufacturer, flight_hours, status) VALUES
(1, 'Engine', 'EngineCorp', 1000, 'ACTIVE'),
(2, 'Wing', 'WingCorp', 2000, 'MAINTENANCE');

-- Insert example data for maintenance_records table
INSERT INTO maintenance_records (maintenance_id, component_id, maintenance_date, description) VALUES
(1, 1, '2023-07-01', 'Engine maintenance'),
(2, 2, '2023-06-15', 'Wing repair');

-- Insert example data for serial_numbers table
INSERT INTO serial_numbers (serial_number, component_id) VALUES
('SN-123', 1),
('SN-456', 2);

-- Insert example data for workers table
INSERT INTO workers (worker_id, name, role, contact) VALUES
(1, 'John Smith', 'Engineer', 'john@example.com'),
(2, 'Jane Doe', 'Technician', 'jane@example.com');

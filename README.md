QUERY SQL & The stages of carrying out the project

-- Create the customer table
CREATE TABLE customer (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(20) NOT NULL
);

-- Create the project table
CREATE TABLE project (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    surfacearea DOUBLE PRECISION NOT NULL,
    profitmerge DOUBLE PRECISION,
    totalcost DOUBLE PRECISION,
    projectstatus VARCHAR(50),
    customer_id INT,
    FOREIGN KEY (customer_id) REFERENCES customer(id) ON DELETE CASCADE
);

-- Create the component table (shared table for materials and labor)
CREATE TABLE component (
    id SERIAL PRIMARY KEY,
    project_id INT,
    component_type VARCHAR(50) NOT NULL,  -- 'material' or 'labor'
    FOREIGN KEY (project_id) REFERENCES project(id) ON DELETE CASCADE
);

-- Create the material table (shares primary key with component)
CREATE TABLE material (
    id INT PRIMARY KEY,  -- This is also a foreign key to component
    name VARCHAR(100) NOT NULL,
    quantity DOUBLE PRECISION NOT NULL,
    unit_cost DOUBLE PRECISION NOT NULL,
    quality_coefficient DOUBLE PRECISION NOT NULL DEFAULT 1.0,
    transport_cost DOUBLE PRECISION NOT NULL,
    FOREIGN KEY (id) REFERENCES component(id) ON DELETE CASCADE
);

-- Create the labor table (shares primary key with component)
CREATE TABLE labor (
    id INT PRIMARY KEY,  -- This is also a foreign key to component
    name VARCHAR(100) NOT NULL,
    hourly_rate DOUBLE PRECISION NOT NULL,
    hours_worked DOUBLE PRECISION NOT NULL,
    worker_productivity DOUBLE PRECISION NOT NULL DEFAULT 1.0,
    FOREIGN KEY (id) REFERENCES component(id) ON DELETE CASCADE
);-- Create the customer table
CREATE TABLE customer (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(20) NOT NULL
);

-- Create the project table
CREATE TABLE project (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    surfacearea DOUBLE PRECISION NOT NULL,
    profitmerge DOUBLE PRECISION,
    totalcost DOUBLE PRECISION,
    projectstatus VARCHAR(50),
    customer_id INT,
    FOREIGN KEY (customer_id) REFERENCES customer(id) ON DELETE CASCADE
);

-- Create the component table (shared table for materials and labor)
CREATE TABLE component (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    project_id INT,
    component_type VARCHAR(50) NOT NULL,  -- 'material' or 'labor'
    FOREIGN KEY (project_id) REFERENCES project(id) ON DELETE CASCADE
);

-- Create the material table (shares primary key with component)
CREATE TABLE material (
    id INT PRIMARY KEY,  -- This is also a foreign key to component
    quantity DOUBLE PRECISION NOT NULL,
    unit_cost DOUBLE PRECISION NOT NULL,
    quality_coefficient DOUBLE PRECISION NOT NULL DEFAULT 1.0,
    transport_cost DOUBLE PRECISION NOT NULL,
    FOREIGN KEY (id) REFERENCES component(id) ON DELETE CASCADE
);

-- Create the labor table (shares primary key with component)
CREATE TABLE labor (
    id INT PRIMARY KEY,  -- This is also a foreign key to component
    hourly_rate DOUBLE PRECISION NOT NULL,
    hours_worked DOUBLE PRECISION NOT NULL,
    worker_productivity DOUBLE PRECISION NOT NULL DEFAULT 1.0,
    FOREIGN KEY (id) REFERENCES component(id) ON DELETE CASCADE
);

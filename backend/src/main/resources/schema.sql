CREATE TABLE IF NOT EXISTS resturants (
    resturant_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL,
    join_code VARCHAR(10) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS employees (
    employee_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    phone_number VARCHAR(30),
    resturant_id INT,
    CONSTRAINT fk_employee_resturant
        FOREIGN KEY (resturant_id)
        REFERENCES resturants(resturant_id)
        ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS deviations (
    deviation_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    registered_by INT,
    date_registered TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_deviation_employee
        FOREIGN KEY (registered_by)
        REFERENCES employees(employee_id)
        ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS tasks (
    task_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    assigned_to INT,
    due_date DATE,
    category VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL,
    recurring BOOLEAN DEFAULT FALSE,
    recurring_frequency VARCHAR(50),
    CONSTRAINT fk_task_employee
        FOREIGN KEY (assigned_to)
        REFERENCES employees(employee_id)
        ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS courses (
    course_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    employee_id INT,
    date_completed DATE,
    date_expires DATE,
    documentation VARCHAR(500),
    category VARCHAR(50) NOT NULL,
    CONSTRAINT fk_course_employee
        FOREIGN KEY (employee_id)
        REFERENCES employees(employee_id)
        ON DELETE SET NULL
);
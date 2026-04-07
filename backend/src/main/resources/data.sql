INSERT INTO resturants (name, location)
SELECT 'Egon Trondheim', 'Trondheim'
WHERE NOT EXISTS (
    SELECT 1 FROM resturants
    WHERE name = 'Egon Trondheim' AND location = 'Trondheim'
);

INSERT INTO resturants (name, location)
SELECT 'Sabrura Solsiden', 'Trondheim'
WHERE NOT EXISTS (
    SELECT 1 FROM resturants
    WHERE name = 'Sabrura Solsiden' AND location = 'Trondheim'
);

INSERT INTO employees (name, email, password, role, phone_number, resturant_id)
SELECT 'Ola Nordmann', 'ola@test.no', 'pass123', 'EMPLOYEE', '12345678', r.resturant_id
FROM resturants r
WHERE r.name = 'Egon Trondheim' AND r.location = 'Trondheim'
  AND NOT EXISTS (
      SELECT 1 FROM employees WHERE email = 'ola@test.no'
  );

INSERT INTO employees (name, email, password, role, phone_number, resturant_id)
SELECT 'Kari Hansen', 'kari@test.no', 'pass123', 'MANAGER', '87654321', r.resturant_id
FROM resturants r
WHERE r.name = 'Egon Trondheim' AND r.location = 'Trondheim'
  AND NOT EXISTS (
      SELECT 1 FROM employees WHERE email = 'kari@test.no'
  );

INSERT INTO employees (name, email, password, role, phone_number, resturant_id)
SELECT 'Per Olsen', 'per@test.no', 'pass123', 'EMPLOYEE', '23456789', r.resturant_id
FROM resturants r
WHERE r.name = 'Sabrura Solsiden' AND r.location = 'Trondheim'
  AND NOT EXISTS (
      SELECT 1 FROM employees WHERE email = 'per@test.no'
  );

INSERT INTO tasks (name, description, assigned_to, due_date, category, status, recurring, recurring_frequency)
SELECT 'Vask kjølerom', 'Grundig vask av kjølerom', e.employee_id, CURRENT_DATE, 'Renhold', 'PENDING', TRUE, 'WEEKLY'
FROM employees e
WHERE e.email = 'ola@test.no'
  AND NOT EXISTS (
      SELECT 1 FROM tasks
      WHERE name = 'Vask kjølerom' AND assigned_to = e.employee_id
  );

INSERT INTO tasks (name, description, assigned_to, due_date, category, status, recurring, recurring_frequency)
SELECT 'Sjekk brannslukker', 'Kontroller brannslukker', e.employee_id, DATEADD('DAY', 10, CURRENT_DATE), 'Sikkerhet', 'PENDING', FALSE, NULL
FROM employees e
WHERE e.email = 'ola@test.no'
  AND NOT EXISTS (
      SELECT 1 FROM tasks
      WHERE name = 'Sjekk brannslukker' AND assigned_to = e.employee_id
  );

INSERT INTO tasks (name, description, assigned_to, due_date, category, status, recurring, recurring_frequency)
SELECT 'Rydd på tørrlager', 'Rydd på tørrlageret', e.employee_id, DATEADD('DAY', 7, CURRENT_DATE), 'Renhold', 'PENDING', TRUE, 'WEEKLY'
FROM employees e
WHERE e.email = 'ola@test.no'
  AND NOT EXISTS (
      SELECT 1 FROM tasks
      WHERE name = 'Rydd på tørrlager' AND assigned_to = e.employee_id
  );

INSERT INTO tasks (name, description, assigned_to, due_date, category, status, recurring, recurring_frequency)
SELECT 'Sjekk brannslukker', 'Kontroller brannslukker', e.employee_id, DATEADD('DAY', 3, CURRENT_DATE), 'Sikkerhet', 'DONE', FALSE, NULL
FROM employees e
WHERE e.email = 'kari@test.no'
  AND NOT EXISTS (
      SELECT 1 FROM tasks
      WHERE name = 'Sjekk brannslukker' AND assigned_to = e.employee_id
  );

INSERT INTO deviations (title, description, registered_by, date_registered)
SELECT 'For høy temperatur', 'Kjøleskapet var på 10 grader', e.employee_id, NOW()
FROM employees e
WHERE e.email = 'ola@test.no'
  AND NOT EXISTS (
      SELECT 1 FROM deviations
      WHERE title = 'For høy temperatur' AND registered_by = e.employee_id
  );

INSERT INTO deviations (title, description, registered_by, date_registered)
SELECT 'Manglende vask', 'Vaskerutine ble ikke gjennomført', e.employee_id, NOW()
FROM employees e
WHERE e.email = 'kari@test.no'
  AND NOT EXISTS (
      SELECT 1 FROM deviations
      WHERE title = 'Manglende vask' AND registered_by = e.employee_id
  );

INSERT INTO courses (name, description, employee_id, date_completed, date_expires, documentation, category)
SELECT 'Brannvernkurs', 'Grunnleggende brannvern', e.employee_id, '2026-01-10', '2027-01-10', 'brannvern.pdf', 'Sikkerhet'
FROM employees e
WHERE e.email = 'ola@test.no'
  AND NOT EXISTS (
      SELECT 1 FROM courses
      WHERE name = 'Brannvernkurs' AND employee_id = e.employee_id
  );

INSERT INTO courses (name, description, employee_id, date_completed, date_expires, documentation, category)
SELECT 'Hygienekurs', 'Mattrygghet og hygiene', e.employee_id, '2026-02-15', '2027-02-15', 'hygiene.pdf', 'Mattrygghet'
FROM employees e
WHERE e.email = 'kari@test.no'
  AND NOT EXISTS (
      SELECT 1 FROM courses
      WHERE name = 'Hygienekurs' AND employee_id = e.employee_id
  );
INSERT INTO user
    (email, password, username)
    values
    ('admin@gmail.com', '$2a$11$oJMZeoz106YMB/vlrQkfs.3zijMw4EoTQmUNUuyoKYwbCAuvKx6tS', 'admin');

INSERT INTO user_role
    (user_id, roles)
    VALUES
    (1, 'ADMIN'), (1, 'USER');
INSERT INTO garagesaledb.asset(category, price, quantity) VALUES
('LAPTOP', 220.0, 2),
('LAPTOP', 150.0, 1),
('MOUSE', 20.0, 5),
('MOUSE', 30.0, 1),
('MOUSE', 25.0, 1),
('HEADPHONES', 60.0, 3),
('HEADPHONES', 20.0, 1),
('KEYBOARD', 30.0, 4),
('KEYBOARD', 15.0, 1),
('MONITOR', 160.0, 3),
('MONITOR', 100.0, 1);

INSERT INTO garagesaledb.issue(description, asset_id) values
('enter keyboard is broken',2),
('scroll wheel is broken',3),
('right sponge is almost gone',7),
('esc button is broken',9),
('1 dead pixel in the right bottom',11);

create table employee
(
    id   bigint       not null primary key,
    name varchar(255) null
);

INSERT INTO mmcs3dev.employee (id, name) VALUES (1, 'Kevin');
INSERT INTO mmcs3dev.employee (id, name) VALUES (2, 'Alice');
INSERT INTO mmcs3dev.employee (id, name) VALUES (3, 'Kelly');

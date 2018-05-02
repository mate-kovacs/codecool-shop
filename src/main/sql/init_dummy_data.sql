DELETE FROM product_categories;
DELETE FROM suppliers;
DELETE FROM products;


INSERT INTO product_categories (name, description, department)
VALUES ('animal', 'Heterotrophe living creature sensable', 'Animal Department');

INSERT INTO product_categories (name, description, department)
VALUES ('plant', 'Autotrophe living creature', 'Plant Department');


INSERT INTO suppliers (name, description)
VALUES ('Állatkert', 'Fővárosi Állatkert Kft.');

INSERT INTO suppliers (name, description)
VALUES ('Füvészkert', 'Füvészkert Kft.');


INSERT INTO products(name, description, default_price, default_currency, product_category, supplier)
VALUES ('kutya', 'Egy ugatós kutya', 13, 'HUF',
        (SELECT id FROM product_categories WHERE product_categories.name = 'animal') ,
        (SELECT id FROM suppliers WHERE suppliers.name = 'Állatkert')
);

INSERT INTO products(name, description, default_price, default_currency, product_category, supplier)
VALUES ('cica', 'Egy nyávogós cica', 11, 'HUF',
        (SELECT id FROM product_categories WHERE product_categories.name = 'animal') ,
        (SELECT id FROM suppliers WHERE suppliers.name = 'Állatkert')
);

INSERT INTO products(name, description, default_price, default_currency, product_category, supplier)
VALUES ('fikusz', 'Egy virágzó fikusz', 6, 'HUF',
        (SELECT id FROM product_categories WHERE product_categories.name = 'plant') ,
        (SELECT id FROM suppliers WHERE suppliers.name = 'Füvészkert')
);

INSERT INTO products(name, description, default_price, default_currency, product_category, supplier)
VALUES ('pálma', 'Egy nagy pálma', 9, 'HUF',
        (SELECT id FROM product_categories WHERE product_categories.name = 'plant') ,
        (SELECT id FROM suppliers WHERE suppliers.name = 'Füvészkert')
);


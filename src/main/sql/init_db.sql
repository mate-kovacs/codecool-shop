DROP TABLE IF EXISTS suppliers CASCADE;
CREATE TABLE suppliers
(
  id SERIAL PRIMARY KEY,
  name VARCHAR(36),
  description TEXT
);


DROP TABLE IF EXISTS product_categories CASCADE;
CREATE TABLE product_categories
(
  id SERIAL PRIMARY KEY,
  name VARCHAR(36),
  description TEXT,
  department VARCHAR(36)
);

DROP TABLE IF EXISTS products CASCADE ;
CREATE TABLE products
(
  id SERIAL PRIMARY KEY,
  name VARCHAR(30),
  description TEXT,
  default_price INT,
  default_currency VARCHAR(3),
  product_category INT REFERENCES product_categories(id) ON DELETE CASCADE,
  supplier INT REFERENCES suppliers(id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users
(
  id SERIAL PRIMARY KEY,
  username VARCHAR(36),
  email VARCHAR(36),
  password TEXT
);

DROP TABLE IF EXISTS orders CASCADE;
CREATE TABLE orders
(
  id SERIAL PRIMARY KEY,
  user_id INT REFERENCES users(id) ,
  name VARCHAR(36),
  email VARCHAR(36),
  billing_address VARCHAR(150),
  shipping_address VARCHAR(150),
  phone VARCHAR(46),
  creation_date TIMESTAMP ,
  status VARCHAR(36),
  shopping_cart_id INT
);

DROP TABLE IF EXISTS shopping_carts CASCADE;
CREATE TABLE shopping_carts
(
  id SERIAL PRIMARY KEY,
  creation_date TIMESTAMP,
  status VARCHAR(36)
);

DROP TABLE IF EXISTS user_shopping_cart CASCADE;
CREATE TABLE user_shopping_cart
(
  id SERIAL PRIMARY KEY,
  user_id INT references users(id),
  shopping_cart_id INT REFERENCES shopping_carts(id) 
);

DROP TABLE IF EXISTS shopping_cart_product CASCADE;
CREATE TABLE shopping_cart_product
(
  id SERIAL PRIMARY KEY,
  shopping_cart_id INT REFERENCES shopping_carts(id),
  product_id INT REFERENCES  products(id) 
);
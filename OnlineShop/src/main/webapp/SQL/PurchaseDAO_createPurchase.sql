CREATE TABLE purchases(
  id SERIAL PRIMARY KEY,
  product_id INTEGER NOT NULL,
  customers_id INTEGER NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  canceled_at TIMESTAMP,
  Foreign key (product_id) references products(id),
  Foreign key (customers_id) references customers(id)
);
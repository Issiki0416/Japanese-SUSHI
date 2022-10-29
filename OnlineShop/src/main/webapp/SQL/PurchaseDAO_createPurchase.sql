CREATE TABLE purchases(
  id SERIAL PRIMARY KEY,
  items_id INTEGER NOT NULL,
  customers_id INTEGER NOT NULL,
  name TEXT NOT NULL,
  address TEXT NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  canceled_at TIMESTAMP,
  Foreign key (items_id) references items(id),
  Foreign key (customers_id) references customers(id)
);
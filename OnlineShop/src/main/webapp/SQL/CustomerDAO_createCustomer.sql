create table customers(
　　　id SERIAL PRIMARY KEY,
　　　　name TEXT NOT NULL,
　　　　password TEXT NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  deleted_at TIMESTAMP
);

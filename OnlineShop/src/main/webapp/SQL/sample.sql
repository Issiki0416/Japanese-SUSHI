create table customers(
	id SERIAL PRIMARY KEY,
	name TEXT NOT NULL,
	password TEXT NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  deleted_at TIMESTAMP
);

insert into customer values(1, 'aaa', 'aaa');
insert into customer values(2, 'iii', 'iii');
insert into customer values(3, 'uuu', 'uuu');

CREATE TABLE items(
  id SERIAL PRIMARY KEY,
  name TEXT NOT NULL,
  price INTEGER NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  deleted_at TIMESTAMP
);
insert into items values(1, 'まぐろ', 100);
insert into items values(2, 'サーモン', 100);
insert into items values(3, 'えび', 100);
insert into items values(4, 'いか', 100);
insert into items values(5, 'えんがわ', 100);
insert into items values(6, 'あなご', 100);
insert into items values(7, 'たまご', 100);
insert into items values(8, 'ほたて', 100);
insert into items values(9, '赤貝', 100);
insert into items values(10, 'つぶ貝', 100);
insert into items values(11, 'サラダ軍艦', 150);
insert into items values(12, 'ねぎとろ軍艦', 150);
insert into items values(13, 'ねぎとろ巻', 150);
insert into items values(14, 'アボガド巻', 150);
insert into items values(15, 'トロ', 200);
insert into items values(16, 'いくら', 200);
insert into items values(17, 'うに', 200);

CREATE TABLE purchases(
  id SERIAL PRIMARY KEY,
  product_id INTEGER NOT NULL,
  customers_id INTEGER NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  canceled_at TIMESTAMP,
  Foreign key (product_id) references products(id),
  Foreign key (customers_id) references customers(id)
);

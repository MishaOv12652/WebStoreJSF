-- auto-generated definition
create table dreambuy.actors
(
  id int not null auto_increment
    primary key,
  name varchar(50) null
)
;

INSERT INTO dreambuy.actors (name) VALUES ('Tom Hanks');
INSERT INTO dreambuy.actors (name) VALUES ('Robert De Niro ');
INSERT INTO dreambuy.actors (name) VALUES ('Jack Nicholson ');
INSERT INTO dreambuy.actors (name) VALUES (' Johnny Depp ');

-- auto-generated definition
create table dreambuy.age_lvl
(
  id int not null auto_increment
    primary key,
  age_lvl varchar(10) null
)
;

INSERT INTO dreambuy.age_lvl (age_lvl) VALUES ('children');
INSERT INTO dreambuy.age_lvl (age_lvl) VALUES ('youth');
INSERT INTO dreambuy.age_lvl (age_lvl) VALUES ('elder');

-- auto-generated definition
create table dreambuy.categories
(
  id int not null auto_increment
    primary key,
  category_name varchar(25) null
)
;

INSERT INTO dreambuy.categories (category_name) VALUES ('Books');
INSERT INTO dreambuy.categories (category_name) VALUES ('Cell Phones');
INSERT INTO dreambuy.categories (category_name) VALUES ('Computers and Tablets');
INSERT INTO dreambuy.categories (category_name) VALUES ('DVD And Movies');
INSERT INTO dreambuy.categories (category_name) VALUES ('Other');

-- auto-generated definition
create table dreambuy.cellphone_brands
(
  id int not null auto_increment
    primary key,
  brand varchar(25) null
)
;

INSERT INTO dreambuy.cellphone_brands (brand) VALUES ('Samsung');
INSERT INTO dreambuy.cellphone_brands (brand) VALUES ('LG');
INSERT INTO dreambuy.cellphone_brands (brand) VALUES ('Huawai');
INSERT INTO dreambuy.cellphone_brands (brand) VALUES ('HTC');
INSERT INTO dreambuy.cellphone_brands (brand) VALUES ('Nokia');

-- auto-generated definition
create table dreambuy.city
(
  id int not null auto_increment
    primary key,
  name varchar(25) null
)
;

INSERT INTO dreambuy.city (name) VALUES ('Netanya');
INSERT INTO dreambuy.city (name) VALUES ('Kfar Yona');
INSERT INTO dreambuy.city (name) VALUES ('Tel Aviv');
INSERT INTO dreambuy.city (name) VALUES ('Jerusalem');
INSERT INTO dreambuy.city (name) VALUES ('Haifa');
INSERT INTO dreambuy.city (name) VALUES ('Hadera');
INSERT INTO dreambuy.city (name) VALUES ('Eilat');

-- auto-generated definition
create table dreambuy.cpu_list
(
  id int not null auto_increment
    primary key,
  cpu varchar(25) null
)
;

INSERT INTO dreambuy.cpu_list (cpu) VALUES ('i5 3400');
INSERT INTO dreambuy.cpu_list (cpu) VALUES ('i3 2100');
INSERT INTO dreambuy.cpu_list (cpu) VALUES ('i7 2500');
INSERT INTO dreambuy.cpu_list (cpu) VALUES ('Quad Core');

-- auto-generated definition
create table dreambuy.credit_companies
(
  id int not null auto_increment
    primary key,
  name varchar(25) null
)
;

INSERT INTO dreambuy.credit_companies (name) VALUES ('Visa');
INSERT INTO dreambuy.credit_companies (name) VALUES ('MasterCard');
INSERT INTO dreambuy.credit_companies (name) VALUES ('American Express');
INSERT INTO dreambuy.credit_companies (name) VALUES ('Isra Card');

-- auto-generated definition
create table dreambuy.directors
(
  id int not null auto_increment
    primary key,
  name varchar(50) null
)
;

INSERT INTO dreambuy.directors (name) VALUES (' Steven Spielberg ');
INSERT INTO dreambuy.directors (name) VALUES (' Martin Scorsese ');
INSERT INTO dreambuy.directors (name) VALUES (' Quentin Tarantino ');
INSERT INTO dreambuy.directors (name) VALUES (' Alfred Hitchcock ');

-- auto-generated definition
create table dreambuy.genres
(
  id int not null auto_increment
    primary key,
  genre varchar(25) null
)
;

INSERT INTO dreambuy.genres (genre) VALUES ('Science Fiction');
INSERT INTO dreambuy.genres (genre) VALUES ('Drama');
INSERT INTO dreambuy.genres (genre) VALUES ('Satire');
INSERT INTO dreambuy.genres (genre) VALUES ('Action and Adventure');
INSERT INTO dreambuy.genres (genre) VALUES ('Horror');
INSERT INTO dreambuy.genres (genre) VALUES ('Romance');
INSERT INTO dreambuy.genres (genre) VALUES ('Mystery');

-- auto-generated definition
create table dreambuy.gpu_list
(
  id int not null auto_increment
    primary key,
  gpu varchar(255) null
)
;

INSERT INTO dreambuy.gpu_list (gpu) VALUES ('Nvidia GeForce Titan V - 12GB');
INSERT INTO dreambuy.gpu_list (gpu) VALUES ('Nvidia GeForce Titan Xp	- 11GB');
INSERT INTO dreambuy.gpu_list (gpu) VALUES ('Nvidia GeForce GTX 1080 Ti - 8GB	');
INSERT INTO dreambuy.gpu_list (gpu) VALUES ('Nvidia GeForce GTX 1080 - 8GB');
INSERT INTO dreambuy.gpu_list (gpu) VALUES ('Nvidia GeForce GTX 1070 Ti - 8GB');
INSERT INTO dreambuy.gpu_list (gpu) VALUES ('AMD GeForce GTX 1070 - 8GB');
INSERT INTO dreambuy.gpu_list (gpu) VALUES ('AMD GeForce GTX 1070 - 8GB');
INSERT INTO dreambuy.gpu_list (gpu) VALUES ('AMD GeForce GTX 1070 - 8GB');

-- auto-generated definition
create table dreambuy.known_authers
(
  id int not null auto_increment
    primary key,
  auther_name varchar(255) null
)
;

INSERT INTO dreambuy.known_authers (auther_name) VALUES ('J.K. Rowling');
INSERT INTO dreambuy.known_authers (auther_name) VALUES ('Stephan King');
INSERT INTO dreambuy.known_authers (auther_name) VALUES ('Mark Twein');
INSERT INTO dreambuy.known_authers (auther_name) VALUES ('Charles Dickens
');
INSERT INTO dreambuy.known_authers (auther_name) VALUES ('Leo Tolstoy');
INSERT INTO dreambuy.known_authers (auther_name) VALUES ('Agatha Christie
');
INSERT INTO dreambuy.known_authers (auther_name) VALUES ('Dr. Seuss');

-- auto-generated definition
create table dreambuy.os_systems
(
  id int not null auto_increment
    primary key,
  os varchar(255) null
)
;

INSERT INTO dreambuy.os_systems (os) VALUES ('Android 6');
INSERT INTO dreambuy.os_systems (os) VALUES ('iOS');
INSERT INTO dreambuy.os_systems (os) VALUES ('Windows 10');
INSERT INTO dreambuy.os_systems (os) VALUES ('Linux');
INSERT INTO dreambuy.os_systems (os) VALUES ('Android 7');
INSERT INTO dreambuy.os_systems (os) VALUES ('Android 8');
INSERT INTO dreambuy.os_systems (os) VALUES ('Windows 7');
INSERT INTO dreambuy.os_systems (os) VALUES ('Windows 8.1');
INSERT INTO dreambuy.os_systems (os) VALUES ('Windows 8');

-- auto-generated definition
create table dreambuy.product_condition
(
  id int not null auto_increment
    primary key,
  `condition` varchar(255) null
)
;

INSERT INTO dreambuy.product_condition (`condition`) VALUES ('New');
INSERT INTO dreambuy.product_condition (`condition`) VALUES ('Used');
INSERT INTO dreambuy.product_condition (`condition`) VALUES ('Seller Refurbished');
INSERT INTO dreambuy.product_condition (`condition`) VALUES ('Manufacture Refurbished');
INSERT INTO dreambuy.product_condition (`condition`) VALUES ('Not Working');

-- auto-generated definition
create table dreambuy.storage_capacity
(
  id int not null auto_increment
    primary key,
  capacity varchar(8) null
)
;

INSERT INTO dreambuy.storage_capacity (capacity) VALUES ('16');
INSERT INTO dreambuy.storage_capacity (capacity) VALUES ('32');
INSERT INTO dreambuy.storage_capacity (capacity) VALUES ('64');
INSERT INTO dreambuy.storage_capacity (capacity) VALUES ('128');
INSERT INTO dreambuy.storage_capacity (capacity) VALUES ('256');
INSERT INTO dreambuy.storage_capacity (capacity) VALUES ('512');
INSERT INTO dreambuy.storage_capacity (capacity) VALUES ('500');
INSERT INTO dreambuy.storage_capacity (capacity) VALUES ('1000');
INSERT INTO dreambuy.storage_capacity (capacity) VALUES ('2000');
INSERT INTO dreambuy.storage_capacity (capacity) VALUES ('3000');

-- auto-generated definition
create table dreambuy.storage_type
(
  id int not null auto_increment
    primary key,
  storage_type varchar(25) null
)
;

INSERT INTO dreambuy.storage_type (storage_type) VALUES ('SD Card');
INSERT INTO dreambuy.storage_type (storage_type) VALUES ('Micro SD Card');
INSERT INTO dreambuy.storage_type (storage_type) VALUES ('HDD');
INSERT INTO dreambuy.storage_type (storage_type) VALUES ('SSD');

-- auto-generated definition
create table dreambuy.user
(
  id int not null auto_increment
    primary key,
  email varchar(30) null,
  f_name varchar(20) null,
  l_name varchar(20) null,
  password varchar(20) null,
  phone_number varchar(10) null,
  city int null,
  address varchar(50) null,
  credit_card_number varchar(20) null,
  credit_card_comp int null,
  credit_card_exp varchar(6) null,
  zip int null,
  constraint User_email_uindex
  unique (email),
  constraint user_city_id_fk
  foreign key (city) references dreambuy.city (id),
  constraint user_credit_companies_id_fk
  foreign key (credit_card_comp) references dreambuy.credit_companies (id)
)
;

create index user_city_id_fk
  on dreambuy.user (city)
;

create index user_credit_companies_id_fk
  on dreambuy.user (credit_card_comp)
;

INSERT INTO dreambuy.user (f_name, l_name, email, password, city, address, credit_card_number, credit_card_comp, credit_card_exp, phone_number, zip) VALUES ('Michael', 'Ovodenko', 'MishaOvodenko@gmail.com', '123456', 1, 'Ha Carmel 4', '102310231023', 1, '1/2018', '0546262472', 4240716);

-- auto-generated definition
create table dreambuy.books_specs
(
  id int not null auto_increment
    primary key,
  series varchar(25) null,
  age_lvl int null,
  author int null,
  genre int null,
  constraint books_specs_age_lvl_id_fk
  foreign key (age_lvl) references dreambuy.age_lvl (id),
  constraint books_specs_known_authers_id_fk
  foreign key (author) references dreambuy.known_authers (id),
  constraint books_specs_genres_id_fk
  foreign key (genre) references dreambuy.genres (id)
)
;

create index books_specs_age_lvl_id_fk
  on dreambuy.books_specs (age_lvl)
;

create index books_specs_genres_id_fk
  on dreambuy.books_specs (genre)
;

create index books_specs_known_authers_id_fk
  on dreambuy.books_specs (author)
;

INSERT INTO dreambuy.books_specs (series, age_lvl, author, genre) VALUES ('Harry Potter', 2, 1, 1);
INSERT INTO dreambuy.books_specs (series, age_lvl, author, genre) VALUES ('1111', 3, 3, 7);

-- auto-generated definition
create table dreambuy.cellphone_specs
(
  id int not null auto_increment
    primary key,
  screen_size double null,
  model varchar(25) null,
  ram int null,
  colour int null,
  brand int null,
  mem_card_type int null,
  os int null,
  storage int null,
  battery_cap int null,
  constraint cellphone_specs_colours_id_fk
  foreign key (colour) references dreambuy.colours (id),
  constraint cellphone_specs_cellphone_brands_id_fk
  foreign key (brand) references dreambuy.cellphone_brands (id),
  constraint cellphone_specs_storage_type_id_fk
  foreign key (mem_card_type) references dreambuy.storage_type (id),
  constraint cellphone_specs_os_systems_id_fk
  foreign key (os) references dreambuy.os_systems (id),
  constraint cellphone_specs_storage_capacity_id_fk
  foreign key (storage) references dreambuy.storage_capacity (id)
)
;

create index cellphone_specs_cellphone_brands_id_fk
  on dreambuy.cellphone_specs (brand)
;

create index cellphone_specs_colours_id_fk
  on dreambuy.cellphone_specs (colour)
;

create index cellphone_specs_os_systems_id_fk
  on dreambuy.cellphone_specs (os)
;

create index cellphone_specs_storage_type_id_fk
  on dreambuy.cellphone_specs (mem_card_type)
;

create index cellphone_specs_storage_capacity_id_fk
  on dreambuy.cellphone_specs (storage)
;

INSERT INTO dreambuy.cellphone_specs (screen_size, model, ram, colour, brand, mem_card_type, os, storage, battery_cap) VALUES (5.7, 'a7', 4, null, 1, 1, 1, 1, 3600);
INSERT INTO dreambuy.cellphone_specs (screen_size, model, ram, colour, brand, mem_card_type, os, storage, battery_cap) VALUES (5.7, 'a7', 4, null, 1, 1, 1, 1, 3600);

-- auto-generated definition
create table dreambuy.computer_specs
(
  id int not null auto_increment
    primary key,
  type varchar(255) null,
  os int null,
  cpu int null,
  cpu_speed double null,
  ram int null,
  gpu int null,
  brand int null,
  screen_size double null,
  release_year int(4) null,
  hdd int null,
  ssd int null,
  model varchar(50) null,
  constraint computer_specs_os_systems_id_fk
  foreign key (os) references dreambuy.os_systems (id),
  constraint computer_specs_cpu_list_id_fk
  foreign key (cpu) references dreambuy.cpu_list (id),
  constraint computer_specs_gpu_list_id_fk
  foreign key (gpu) references dreambuy.gpu_list (id),
  constraint computer_specs_cellphone_brands_id_fk
  foreign key (brand) references dreambuy.cellphone_brands (id),
  constraint computer_specs_storage_capacity_id_fk_hdd
  foreign key (hdd) references dreambuy.storage_capacity (id),
  constraint computer_specs_storage_capacity_id_fk_ssd
  foreign key (ssd) references dreambuy.storage_capacity (id)
)
  comment 'also for tablets'
;

create index computer_specs_cellphone_brands_id_fk
  on dreambuy.computer_specs (brand)
;

create index computer_specs_cpu_list_id_fk
  on dreambuy.computer_specs (cpu)
;

create index computer_specs_gpu_list_id_fk
  on dreambuy.computer_specs (gpu)
;

create index computer_specs_os_systems_id_fk
  on dreambuy.computer_specs (os)
;

create index computer_specs_storage_capacity_id_fk_hdd
  on dreambuy.computer_specs (hdd)
;

create index computer_specs_storage_capacity_id_fk_ssd
  on dreambuy.computer_specs (ssd)
;

INSERT INTO dreambuy.computer_specs (type, os, cpu, cpu_speed, ram, gpu, brand, screen_size, release_year, hdd, ssd, model) VALUES ('laptop', 3, 1, 3.4, 8, 1, 1, 15.6, 2015, 1, 1, 'comp model');


-- auto-generated definition
create table dreambuy.movie_specs
(
  id int not null auto_increment
    primary key,
  director int null,
  length int null,
  year int(4) null,
  actor int null,
  age_lvl int null,
  genre int null,
  constraint movie_specs_directors_id_fk
  foreign key (director) references dreambuy.directors (id),
  constraint movie_specs_actors_id_fk
  foreign key (actor) references dreambuy.actors (id),
  constraint movie_specs_age_lvl_id_fk
  foreign key (age_lvl) references dreambuy.age_lvl (id),
  constraint movie_specs_genres_id_fk
  foreign key (genre) references dreambuy.genres (id)
)
;

create index movie_specs_actors_id_fk
  on dreambuy.movie_specs (actor)
;

create index movie_specs_age_lvl_id_fk
  on dreambuy.movie_specs (age_lvl)
;

create index movie_specs_directors_id_fk
  on dreambuy.movie_specs (director)
;

create index movie_specs_genres_id_fk
  on dreambuy.movie_specs (genre)
;

INSERT INTO dreambuy.movie_specs (director, length, year, actor, age_lvl, genre) VALUES (4, 120, 2015, 4, 3, 7);

-- auto-generated definition
create table dreambuy.products
(
  id int not null auto_increment
    primary key,
  name varchar(100) null,
  price float null,
  item_desc mediumtext null,
  category int null,
  condition_id int null,
  img blob null,
  seller_id int null,
  numOfItems int null,
  book_spec_id int null,
  cellphone_spec_id int null,
  computer_spec_id int null,
  movie_spec_id int null,
  constraint products_categories_id_fk
  foreign key (category) references dreambuy.categories (id),
  constraint products_product_condition_id_fk
  foreign key (condition_id) references dreambuy.product_condition (id),
  constraint products_user_id_fk
  foreign key (seller_id) references dreambuy.user (id),
  constraint products_books_specs_id_fk
  foreign key (book_spec_id) references dreambuy.books_specs (id),
  constraint products_cellphone_specs_id_fk
  foreign key (cellphone_spec_id) references dreambuy.cellphone_specs (id),
  constraint products_computer_specs_id_fk
  foreign key (computer_spec_id) references dreambuy.computer_specs (id),
  constraint products_movie_specs_id_fk
  foreign key (movie_spec_id) references dreambuy.movie_specs (id)
)
;

create index products_categories_id_fk
  on dreambuy.products (category)
;

create index products_product_condition_id_fk
  on dreambuy.products (condition_id)
;

create index products_user_id_fk
  on dreambuy.products (seller_id)
;

create index products_books_specs_id_fk
  on dreambuy.products (book_spec_id)
;

create index products_cellphone_specs_id_fk
  on dreambuy.products (cellphone_spec_id)
;

create index products_computer_specs_id_fk
  on dreambuy.products (computer_spec_id)
;

create index products_movie_specs_id_fk
  on dreambuy.products (movie_spec_id)
;

INSERT INTO dreambuy.products (name, price, item_desc, category, condition_id, img, seller_id, numOfItems, book_spec_id, cellphone_spec_id, computer_spec_id, movie_spec_id) VALUES ('Harry Potter And The Prisoner Of Azkaban', 10, 'Harry Potter and the Prisoner of Azkaban is a 2004 fantasy film directed by Alfonso Cuarón and distributed by Warner Bros. Pictures.[3] It is based on the novel of the same name by J. K. Rowling. The film, which is the third instalment in the Harry Potter film series, was written by Steve Kloves and produced by Chris Columbus (director of the first two instalments), David Heyman, and Mark Radcliffe. The story follows Harry Potter''s third year at Hogwarts as he is informed that a prisoner named Sirius Black has escaped from Azkaban intending to kill him.', 1, 1, null, 16, 2, 39, null, null, null);
INSERT INTO dreambuy.products (name, price, item_desc, category, condition_id, img, seller_id, numOfItems, book_spec_id, cellphone_spec_id, computer_spec_id, movie_spec_id) VALUES ('Car', 2500, 'Mitsubisi', 5, 1, null, 16, 1, null, null, null, null);
INSERT INTO dreambuy.products (name, price, item_desc, category, condition_id, img, seller_id, numOfItems, book_spec_id, cellphone_spec_id, computer_spec_id, movie_spec_id) VALUES ('CellPhone', 0, 'samsung a7', 2, 1, null, 16, 0, null, 5, null, null);
INSERT INTO dreambuy.products (name, price, item_desc, category, condition_id, img, seller_id, numOfItems, book_spec_id, cellphone_spec_id, computer_spec_id, movie_spec_id) VALUES ('Movie', 12.5, 'Movie', 4, 1, null, 16, 2, null, null, null, 2);
INSERT INTO dreambuy.products (name, price, item_desc, category, condition_id, img, seller_id, numOfItems, book_spec_id, cellphone_spec_id, computer_spec_id, movie_spec_id) VALUES ('Computer', 650, 'best laptop ever', 3, 1, null, 16, 3, null, null, 9, null);
INSERT INTO dreambuy.products (name, price, item_desc, category, condition_id, img, seller_id, numOfItems, book_spec_id, cellphone_spec_id, computer_spec_id, movie_spec_id) VALUES ('Harry Potter And The Prisoner Of Azkaban', 0, '', 1, 1, null, 16, 0, 40, null, null, null);



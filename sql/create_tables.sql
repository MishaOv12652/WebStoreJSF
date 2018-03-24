CREATE TABLE dreambuy.categories
(
  id            INT         NOT NULL AUTO_INCREMENT
    PRIMARY KEY,
  category_name VARCHAR(25) NULL
);

CREATE TABLE dreambuy.city
(
  id   INT         NOT NULL AUTO_INCREMENT
    PRIMARY KEY,
  name VARCHAR(25) NULL
);

CREATE TABLE dreambuy.credit_companies
(
  id   INT         NOT NULL AUTO_INCREMENT
    PRIMARY KEY,
  name VARCHAR(25) NULL
);

CREATE TABLE dreambuy.product_condition
(
  id          INT         NOT NULL AUTO_INCREMENT
    PRIMARY KEY,
  `condition` VARCHAR(25) NULL
);

-- auto-generated definition
CREATE TABLE dreambuy.age_lvl
(
  id      INT         NOT NULL AUTO_INCREMENT
    PRIMARY KEY,
  age_lvl VARCHAR(10) NULL
);

-- auto-generated definition
CREATE TABLE dreambuy.genres
(
  id    INT         NOT NULL AUTO_INCREMENT
    PRIMARY KEY,
  genre VARCHAR(25) NULL
);

-- auto-generated definition
CREATE TABLE dreambuy.known_authers
(
  id          INT         NOT NULL AUTO_INCREMENT
    PRIMARY KEY,
  auther_name VARCHAR(25) NULL
);
-- auto-generated definition
CREATE TABLE dreambuy.storage_capacity
(
  id       INT        NOT NULL AUTO_INCREMENT
    PRIMARY KEY,
  capacity VARCHAR(5) NULL
);

-- auto-generated definition
CREATE TABLE dreambuy.os_systems
(
  id INT         NOT NULL AUTO_INCREMENT
    PRIMARY KEY,
  os VARCHAR(25) NULL
);

-- auto-generated definition
CREATE TABLE dreambuy.directors
(
  id   INT         NOT NULL AUTO_INCREMENT
    PRIMARY KEY,
  name VARCHAR(50) NULL
);

-- auto-generated definition
CREATE TABLE dreambuy.colours
(
  id     INT         NOT NULL AUTO_INCREMENT
    PRIMARY KEY,
  colour VARCHAR(25) NULL
);

-- auto-generated definition
CREATE TABLE dreambuy.cellphone_brands
(
  id    INT         NOT NULL AUTO_INCREMENT
    PRIMARY KEY,
  brand VARCHAR(25) NULL
);

-- auto-generated definition
CREATE TABLE dreambuy.storage_type
(
  id           INT         NOT NULL AUTO_INCREMENT
    PRIMARY KEY,
  storage_type VARCHAR(25) NULL
);

-- auto-generated definition
CREATE TABLE dreambuy.actors
(
  id   INT         NOT NULL AUTO_INCREMENT
    PRIMARY KEY,
  name VARCHAR(50) NULL
);

CREATE TABLE dreambuy.cpu_list
(
  id  INT PRIMARY KEY AUTO_INCREMENT,
  cpu VARCHAR(25)
);

CREATE TABLE dreambuy.gpu_list
(
  id  INT PRIMARY KEY AUTO_INCREMENT,
  gpu VARCHAR(25)
);

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
  credit_card_exp varchar(7) null,
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
  on user (city)
;

create index user_credit_companies_id_fk
  on user (credit_card_comp)
;
-- auto-generated definition
CREATE TABLE dreambuy.movie_specs
(
  id       INT    NOT NULL AUTO_INCREMENT
    PRIMARY KEY,
  director INT    NULL,
  length   INT    NULL,
  year     INT(4) NULL,
  actor    INT    NULL,
  age_lvl  INT    NULL,
  genre    INT    NULL,
  CONSTRAINT movie_specs_directors_id_fk
  FOREIGN KEY (director) REFERENCES dreambuy.directors (id),
  CONSTRAINT movie_specs_actors_id_fk
  FOREIGN KEY (actor) REFERENCES dreambuy.actors (id),
  CONSTRAINT movie_specs_age_lvl_id_fk
  FOREIGN KEY (age_lvl) REFERENCES dreambuy.age_lvl (id),
  CONSTRAINT movie_specs_genres_id_fk
  FOREIGN KEY (genre) REFERENCES dreambuy.genres (id)
);

CREATE INDEX movie_specs_actors_id_fk
  ON dreambuy.movie_specs (actor);

CREATE INDEX movie_specs_age_lvl_id_fk
  ON dreambuy.movie_specs (age_lvl);

CREATE INDEX movie_specs_directors_id_fk
  ON dreambuy.movie_specs (director);

CREATE INDEX movie_specs_genres_id_fk
  ON dreambuy.movie_specs (genre);

-- auto-generated definition
CREATE TABLE dreambuy.books_specs
(
  id      INT         NOT NULL AUTO_INCREMENT
    PRIMARY KEY,
  series  VARCHAR(25) NULL,
  age_lvl INT         NULL,
  author  INT         NULL,
  genre   INT         NULL,
  CONSTRAINT books_specs_genres_id_fk
  FOREIGN KEY (genre) REFERENCES dreambuy.genres (id),
  CONSTRAINT books_specs_age_lvl_id_fk
  FOREIGN KEY (age_lvl) REFERENCES dreambuy.age_lvl (id),
  CONSTRAINT books_specs_known_authers_id_fk
  FOREIGN KEY (author) REFERENCES dreambuy.known_authers (id)
);

CREATE INDEX books_specs_known_authers_id_fk
  ON dreambuy.books_specs (author);

CREATE INDEX books_specs_age_lvl_id_fk
  ON dreambuy.books_specs (age_lvl);

CREATE INDEX books_specs_genres_id_fk
  ON dreambuy.books_specs (genre);

-- auto-generated definition
CREATE TABLE dreambuy.cellphone_specs
(
  id            INT         NOT NULL AUTO_INCREMENT
    PRIMARY KEY,
  screen_size   DOUBLE      NULL,
  model         VARCHAR(25) NULL,
  ram           INT         NULL,
  colour        INT         NULL,
  brand         INT         NULL,
  mem_card_type INT         NULL,
  os            INT         NULL,
  storage       INT         NULL,
  battery_cap   INT         NULL,
  CONSTRAINT cellphone_specs_colours_id_fk
  FOREIGN KEY (colour) REFERENCES dreambuy.colours (id),
  CONSTRAINT cellphone_specs_cellphone_brands_id_fk
  FOREIGN KEY (brand) REFERENCES dreambuy.cellphone_brands (id),
  CONSTRAINT cellphone_specs_storage_type_id_fk
  FOREIGN KEY (mem_card_type) REFERENCES dreambuy.storage_type (id),
  CONSTRAINT cellphone_specs_os_systems_id_fk
  FOREIGN KEY (os) REFERENCES dreambuy.os_systems (id)
);

CREATE INDEX cellphone_specs_cellphone_brands_id_fk
  ON dreambuy.cellphone_specs (brand);

CREATE INDEX cellphone_specs_colours_id_fk
  ON dreambuy.cellphone_specs (colour);

CREATE INDEX cellphone_specs_os_systems_id_fk
  ON dreambuy.cellphone_specs (os);

CREATE INDEX cellphone_specs_storage_type_id_fk
  ON dreambuy.cellphone_specs (mem_card_type);

CREATE TABLE dreambuy.computer_specs
(
  id           INT PRIMARY KEY AUTO_INCREMENT,
  type         INT,
  os           INT,
  cpu          INT,
  cpu_speed    DOUBLE,
  ram          INT,
  gpu          INT,
  brand        INT,
  screen_size  DOUBLE,
  release_year INT(4),
  hdd          INT,
  ssd          INT,
  CONSTRAINT computer_specs_os_systems_id_fk FOREIGN KEY (os) REFERENCES os_systems (id),
  CONSTRAINT computer_specs_cpu_list_id_fk FOREIGN KEY (cpu) REFERENCES cpu_list (id),
  CONSTRAINT computer_specs_gpu_list_id_fk FOREIGN KEY (gpu) REFERENCES gpu_list (id),
  CONSTRAINT computer_specs_cellphone_brands_id_fk FOREIGN KEY (brand) REFERENCES cellphone_brands (id),
  CONSTRAINT computer_specs_storage_capacity_id_fk_ssd FOREIGN KEY (ssd) REFERENCES storage_capacity (id),
  CONSTRAINT computer_specs_storage_capacity_id_fk_hdd FOREIGN KEY (hdd) REFERENCES storage_capacity (id)
);
ALTER TABLE dreambuy.computer_specs
  COMMENT = 'also for tablets';
ALTER TABLE dreambuy.computer_specs
  ADD model VARCHAR(50) NULL;

-- auto-generated definition
CREATE TABLE dreambuy.products
(
  id                 INT              NOT NULL AUTO_INCREMENT
    PRIMARY KEY,
  name               VARCHAR(25)      NULL,
  price              FLOAT            NULL,
  item_desc          VARCHAR(500)     NULL,
  category           INT              NULL,
  condition_id       INT              NULL,
  img                BLOB             NULL,
  seller_id          INT              NULL,
  book_specs_id      INT DEFAULT '-1' NULL,
  movie_specs_id     INT              NULL,
  cellphone_specs_id INT              NULL,
  CONSTRAINT products_categories_id_fk
  FOREIGN KEY (category) REFERENCES dreambuy.categories (id),
  CONSTRAINT products_product_condition_id_fk
  FOREIGN KEY (condition_id) REFERENCES dreambuy.product_condition (id),
  CONSTRAINT products_user_id_fk
  FOREIGN KEY (seller_id) REFERENCES dreambuy.user (id),
  CONSTRAINT products_books_specs_id_fk
  FOREIGN KEY (book_specs_id) REFERENCES dreambuy.books_specs (id),
  CONSTRAINT products_movie_specs_id_fk
  FOREIGN KEY (movie_specs_id) REFERENCES dreambuy.movie_specs (id),
  CONSTRAINT products_cellphone_specs_id_fk
  FOREIGN KEY (cellphone_specs_id) REFERENCES dreambuy.cellphone_specs (id)
);

CREATE INDEX products_categories_id_fk
  ON dreambuy.products (category);

CREATE INDEX products_product_condition_id_fk
  ON dreambuy.products (condition_id);

CREATE INDEX products_user_id_fk
  ON dreambuy.products (seller_id);

CREATE INDEX products_books_specs_id_fk
  ON dreambuy.products (book_specs_id);

CREATE INDEX products_movie_specs_id_fk
  ON dreambuy.products (movie_specs_id);

CREATE INDEX products_cellphone_specs_id_fk
  ON dreambuy.products (cellphone_specs_id);









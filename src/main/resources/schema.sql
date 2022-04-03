CREATE TABLE stocks (
   id IDENTITY NOT NULL AUTO_INCREMENT,
   ticker VARCHAR(10) NOT NULL,
   row_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   row_modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   PRIMARY KEY(id)
);

CREATE TABLE price_history (
    id IDENTITY NOT NULL AUTO_INCREMENT,
    stock_id INT NOT NULL,
    close_date DATE,
    price NUMERIC,
    row_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    row_modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(id),
    FOREIGN KEY (stock_id) references stocks(id)
);

CREATE TABLE users (
   id IDENTITY NOT NULL AUTO_INCREMENT,
   username VARCHAR(50),
   row_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   row_modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   PRIMARY KEY(id)
);

CREATE TABLE transactions(
    id IDENTITY NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    stock_id INT NOT NULL,
    quantity NUMERIC NOT NULL,
    purchase_timestamp TIMESTAMP NOT NULL,
    row_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    row_modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(id),
    FOREIGN KEY (user_id) references users(id),
    FOREIGN KEY (stock_id) references stocks(id)
);
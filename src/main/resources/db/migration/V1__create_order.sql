CREATE TABLE orders (
    id BIGINT NOT NULL AUTO_INCREMENT,
    costumer_id BIGINT NOT NULL,
    service_provider_id BIGINT NOT NULL,
    service_id BIGINT NOT NULL,
    status_order VARCHAR(10) NOT NULL,
    year INT NOT NULL,
    opening_date DATE NOT NULL,
    total_cost DEC(15,2) NOT NULL,
    start_date DATE NOT NULL,
    start_time TIME NOT NULL,
    finish_date DATE NOT NULL,
    updated_date_time DATETIME NOT NULL,
    PRIMARY KEY(id)
);
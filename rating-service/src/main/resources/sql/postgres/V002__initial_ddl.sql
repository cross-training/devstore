SET search_path TO rating;

CREATE TABLE Rating (
    id VARCHAR(30) NOT NULL PRIMARY KEY,
    email VARCHAR(127) NOT NULL,
    product_id INTEGER NOT NULL,
    rating NUMERIC(2, 1) NOT NULL CHECK (rating >= 0.1 AND rating <= 5.0),
    event_datetime TIMESTAMP WITH TIME ZONE NOT NULL,
    UNIQUE (user, product_id)
);
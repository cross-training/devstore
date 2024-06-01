SET search_path TO catalog;

GRANT SELECT ON TABLE Categories TO catalog;
GRANT SELECT ON TABLE Products TO catalog;
GRANT SELECT ON TABLE Product_Category TO catalog;
GRANT USAGE, SELECT ON SEQUENCE products_id_seq TO catalog;
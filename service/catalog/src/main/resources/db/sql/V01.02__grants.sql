SET search_path TO catalog;

GRANT SELECT ON TABLE tbl_categories TO catalog;
GRANT SELECT ON TABLE tbl_products TO catalog;
GRANT USAGE, SELECT ON SEQUENCE tbl_products_id_seq TO catalog;
CREATE TABLE stop (
  id                 BIGINT NOT NULL,
  latitude           DECIMAL(10, 8),
  longitude          DECIMAL(10, 8),
  delivery_id 		 BIGINT,
  PRIMARY KEY (id)
);

CREATE SEQUENCE IF NOT EXISTS stop_seq INCREMENT BY 1 START WITH 1
NO CYCLE OWNED BY stop.id;

ALTER TABLE stop
  ADD CONSTRAINT FK_stop FOREIGN KEY (delivery_id) REFERENCES delivery;
CREATE TABLE "customer" (
  id serial primary key,
  name character varying NOT NULL,
  active boolean NOT NULL DEFAULT true
);

INSERT INTO "customer"(name, active) VALUES ('Big News Media Corp',true),('Online Mega Store',true),('Nachoroo Delivery',false),('Euro Telecom Group',true);

CREATE TABLE "ip_blacklist" (
  ip bigint NOT NULL,
  CONSTRAINT ip_blacklist_pkey PRIMARY KEY (ip)
);

INSERT INTO "ip_blacklist" VALUES (0),(2130706433),(4294967295);

CREATE TABLE "ua_blacklist" (
  ua character varying primary key
);

INSERT INTO "ua_blacklist" VALUES ('A6-Indexer'),('Googlebot-News'),('Googlebot');

CREATE TABLE "hourly_stats" (
  id serial primary key,
  customer_id integer NOT NULL REFERENCES customer (id) ON DELETE CASCADE,
  time timestamp NOT NULL,
  valid_count bigint NOT NULL DEFAULT '0',
  invalid_count bigint NOT NULL DEFAULT '0',
  UNIQUE (customer_id, time)
);

--CREATE INDEX idx_customer_id ON hourly_stats USING (customer_id);

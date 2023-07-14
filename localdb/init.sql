CREATE TABLE convert_table (
  convertionId VARCHAR(255) NOT NULL,
  conversionTimelife VARCHAR(255) NOT NULL,
  model VARCHAR(255) NOT NULL,
  version VARCHAR(255) NOT NULL,
  priceUsd DOUBLE PRECISION NOT NULL,
  priceCryptocurrency DOUBLE PRECISION NOT NULL,
  cryptocurrency VARCHAR(255) NOT NULL
);

CREATE TABLE buy_table (
  id SERIAL PRIMARY KEY,
  fullName VARCHAR(255) NOT NULL,
  version VARCHAR(255) NOT NULL,
  model VARCHAR(255) NOT NULL,
  cryptocurrency VARCHAR(255) NOT NULL,
  priceUsd DOUBLE PRECISION NOT NULL,
  priceCryptocurrency DOUBLE PRECISION NOT NULL,
  date DATE NOT NULL,
  purchaseId VARCHAR(255) NOT NULL
);
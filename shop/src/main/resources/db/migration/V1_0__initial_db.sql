CREATE TABLE "CUSTOMER"(
    "ID" INTEGER NOT NULL,
    "EMAIL_ADDRESS" VARCHAR(255),
    "FIRST_NAME" VARCHAR(255),
    "LAST_NAME" VARCHAR(255),
    "USER_NAME" VARCHAR(255)
);
ALTER TABLE "CUSTOMER" ADD CONSTRAINT "CONSTRAINT_5" PRIMARY KEY("ID");

CREATE TABLE "LOCATION"(
    "ID" INTEGER NOT NULL,
    "ADDRESS_CITY" VARCHAR(255),
    "ADDRESS_COUNTRY" VARCHAR(255),
    "ADDRESS_COUNTY" VARCHAR(255),
    "ADDRESS_STREET_ADDRESS" VARCHAR(255),
    "NAME" VARCHAR(255)
);
ALTER TABLE "LOCATION" ADD CONSTRAINT "CONSTRAINT_9" PRIMARY KEY("ID");

CREATE TABLE "ORDER_DETAIL"(
    "ORDER_DETAIL_ID" INTEGER NOT NULL,
    "QUANTITY" INTEGER,
    "ORDER_ID" INTEGER,
    "PRODUCT_ID" INTEGER
);
ALTER TABLE "ORDER_DETAIL" ADD CONSTRAINT "CONSTRAINT_4" PRIMARY KEY("ORDER_DETAIL_ID");

CREATE TABLE "SUPPLIER"(
    "ID" INTEGER NOT NULL,
    "NAME" VARCHAR(255)
);

CREATE TABLE "PRODUCT"(
    "ID" INTEGER NOT NULL,
    "DESCRIPTION" VARCHAR(255),
    "IMAGE_URL" VARCHAR(255),
    "NAME" VARCHAR(255),
    "PRICE" DECIMAL(19, 2),
    "WEIGHT" DOUBLE,
    "PRODUCT_CATEGORY_ID" INTEGER,
    "SUPPLIER_ID" INTEGER
);

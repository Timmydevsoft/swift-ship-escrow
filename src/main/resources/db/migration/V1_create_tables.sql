CREATE table users(
    id UUID PRIMARY KEY,
    email VARCHAR(250) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    is_active BOOLEAN DEFAULT true,
    created_at TIMESTAMP,
    updated_date TIMESTAMP
);

CREATE TABLE user_roles (
    user_id UUID NOT NULL,
    role VARCHAR(100) NOT NULL,
    CONSTRAINT fk_user_roles_user
    FOREIGN KEY (user_id)
    REFERENCES users(id)
    ON DELETE CASCADE
);

CREATE TABLE customers(
    id UUID PRIMARY KEY,
    first_name VARCHAR(250) NOT NULL,
    last_name VARCHAR(259) NOT NULL,
    avatar VARCHAR(250) NOT NULL,
    balance DECIMAL(19,2) DEFAULT 0,
    user_id UUID UNIQUE,
    CONSTRAINT fk_customer_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL
);


CREATE TABLE merchants(
  id UUID PRIMARY KEY,
  business_name VARCHAR(250) NOT NULL,
  bio TEXT NOT NULL,
  user_id UUID UNIQUE,
  balance DECIMAL(19, 2) DEFAULT 0,
  CONSTRAINT fk_merchant_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL
);

CREATE TABLE escrows(
    id UUID PRIMARY KEY,
    price DECIMAL(19, 2) DEFAULT 0,
    description TEXT NOT NULL,
    item_name VARCHAR(250),
    status VARCHAR(100),
    customer_id UUID,
    merchant_id UUID,
    CONSTRAINT fk_escrow_customer
        FOREIGN KEY (customer_id)
        REFERENCES customers(id)
        ON DELETE SET NULL,
    CONSTRAINT fk_escrow_merchant
        FOREIGN KEY (merchant_id)
        REFERENCES merchants(id)
        ON DELETE SET NULL
);

CREATE TABLE transactions(
    id UUID PRIMARY KEY,
    amount DECIMAL(19,2) DEFAULT 0,
    description TEXT NOT NULL,
    status VARCHAR(100),
    type VARCHAR(100),
    escrow_id UUID,
    customer_id UUID,
    merchant_id UUID,
    CONSTRAINT fk_transaction_escrow
        FOREIGN KEY (escrow_id)
        REFERENCES escrows(id)
        ON DELETE SET NULL,
    CONSTRAINT fk_transaction_customer
        FOREIGN KEY (customer_id)
        REFERENCES customers(id)
        ON DELETE SET NULL,
    CONSTRAINT fk_transaction_merchant
        FOREIGN KEY (merchant_id)
        REFERENCES merchants(id)
)
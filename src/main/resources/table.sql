CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE user_role (
   id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
   role VARCHAR(30) NOT NULL,
   description VARCHAR(255),
   create_at BIGINT DEFAULT (EXTRACT(EPOCH FROM CURRENT_TIMESTAMP) * 1000)::BIGINT,
   update_at BIGINT DEFAULT (EXTRACT(EPOCH FROM CURRENT_TIMESTAMP) * 1000)::BIGINT
);

CREATE TABLE "user" (
   id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
   first_name VARCHAR(255),
   last_name VARCHAR(255),
   phone_number VARCHAR(15) NOT NULL UNIQUE,
   email VARCHAR(255),
   create_at BIGINT DEFAULT (EXTRACT(EPOCH FROM CURRENT_TIMESTAMP) * 1000)::BIGINT,
   update_at BIGINT DEFAULT (EXTRACT(EPOCH FROM CURRENT_TIMESTAMP) * 1000)::BIGINT,
   FOREIGN KEY (role_id) REFERENCES "user_role" (id)
);

CREATE TABLE "user_role_mapping" (
   id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
   role_id UUID NOT NULL,
   user_id UUID NOT NULL
   create_at BIGINT DEFAULT (EXTRACT(EPOCH FROM CURRENT_TIMESTAMP) * 1000)::BIGINT,
   update_at BIGINT DEFAULT (EXTRACT(EPOCH FROM CURRENT_TIMESTAMP) * 1000)::BIGINT,
   FOREIGN KEY (role_id) REFERENCES "user_role" (id),
   FOREIGN KEY (user_id) REFERENCES "user" (id)
);

CREATE TABLE user_device (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    user_id UUID NOT NULL,
    device_token VARCHAR(255) UNIQUE NOT NULL,
    platform VARCHAR(30) NOT NULL,
    status VARCHAR(30) NOT NULL,
    last_login BIGINT,
	create_at BIGINT DEFAULT (EXTRACT(EPOCH FROM CURRENT_TIMESTAMP) * 1000)::BIGINT,
	update_at BIGINT DEFAULT (EXTRACT(EPOCH FROM CURRENT_TIMESTAMP) * 1000)::BIGINT,
    FOREIGN KEY (user_id) REFERENCES "user" (id)
);

CREATE TABLE notification_delivery_type (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    delivery_type VARCHAR(30) UNIQUE NOT NULL,
    description TEXT,
	create_at BIGINT DEFAULT (EXTRACT(EPOCH FROM CURRENT_TIMESTAMP) * 1000)::BIGINT,
	update_at BIGINT DEFAULT (EXTRACT(EPOCH FROM CURRENT_TIMESTAMP) * 1000)::BIGINT
);

CREATE TABLE notification_type (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    type VARCHAR(30),
    description TEXT,
	create_at BIGINT DEFAULT (EXTRACT(EPOCH FROM CURRENT_TIMESTAMP) * 1000)::BIGINT,
	update_at BIGINT DEFAULT (EXTRACT(EPOCH FROM CURRENT_TIMESTAMP) * 1000)::BIGINT
);

CREATE TABLE notification (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    title VARCHAR(255) UNIQUE NOT NULL,
    message TEXT,
    notification_type_id UUID NOT NULL,
    notification_delivery_type_id UUID NOT NULL,
	create_at BIGINT DEFAULT (EXTRACT(EPOCH FROM CURRENT_TIMESTAMP) * 1000)::BIGINT,
	update_at BIGINT DEFAULT (EXTRACT(EPOCH FROM CURRENT_TIMESTAMP) * 1000)::BIGINT,
    FOREIGN KEY (notification_type_id) REFERENCES notification_type (id),
    FOREIGN KEY (notification_delivery_type_id) REFERENCES notification_delivery_type (id)
);

CREATE TABLE notification_recipient (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    notification_id UUID NOT NULL,
    message TEXT,
    user_id UUID NOT NULL,
    status VARCHAR(30) NOT NULL,
    sent_at BIGINT,
    read_at BIGINT,
	create_at BIGINT DEFAULT (EXTRACT(EPOCH FROM CURRENT_TIMESTAMP) * 1000)::BIGINT,
	update_at BIGINT DEFAULT (EXTRACT(EPOCH FROM CURRENT_TIMESTAMP) * 1000)::BIGINT,
    FOREIGN KEY (notification_id) REFERENCES notification (id),
    FOREIGN KEY (user_id) REFERENCES "user" (id)
);

CREATE TABLE notification_provider (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    provider_name VARCHAR(255),
    notification_delivery_type_id UUID,
    api_key TEXT,
    username VARCHAR(255),
    password VARCHAR(255),
    host VARCHAR(255),
    port INTEGER,
    active BOOLEAN,
    provider_specific_config jsonb,
	create_at BIGINT DEFAULT (EXTRACT(EPOCH FROM CURRENT_TIMESTAMP) * 1000)::BIGINT,
	update_at BIGINT DEFAULT (EXTRACT(EPOCH FROM CURRENT_TIMESTAMP) * 1000)::BIGINT,
    FOREIGN KEY (notification_delivery_type_id) REFERENCES notification_delivery_type (id)
);

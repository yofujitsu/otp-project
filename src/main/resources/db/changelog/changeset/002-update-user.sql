-- liquibase formatted sql

-- changeset yofuj:1777829208356-1
ALTER TABLE users ADD COLUMN email VARCHAR(255);
ALTER TABLE users ADD COLUMN phone VARCHAR(50);
ALTER TABLE users ADD COLUMN tg_chat_id VARCHAR(100);
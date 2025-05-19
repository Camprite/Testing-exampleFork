CREATE TABLE person_contact (
          id BIGSERIAL PRIMARY KEY,
          person_id BIGINT REFERENCES person(id) NOT NULL,
          contact_id BIGINT REFERENCES contact(id) NOT NULL
);

CREATE TABLE contact (
                         id BIGSERIAL PRIMARY KEY,
                         contact VARCHAR(255) NOT NULL,
                         additionalInformation VARCHAR(255) NOT NULL,
                         contactType VARCHAR(50) NOT NULL
);
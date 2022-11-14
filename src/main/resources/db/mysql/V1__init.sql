/* fcm token 테이블 */

CREATE TABLE cloud_messaging_token (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(255),
    user_role VARCHAR(31),
    token VARCHAR(255),
    created_date TIMESTAMP,
    last_modified_date TIMESTAMP
);
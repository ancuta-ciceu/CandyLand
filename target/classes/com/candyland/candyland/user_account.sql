SELECT  firstname, lastname, username, role, password FROM user_account
DELETE FROM user_account WHERE username="ancuta";
INSERT INTO user_account(firstname, lastname, username, role, password) VALUE ('','','','','')
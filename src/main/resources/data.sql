-- Test kullanıcısı ve rolleri için örnek veriler

-- Roller
INSERT IGNORE INTO roles (RoleId, RoleTitle) VALUES (1, 'ADMIN');
INSERT IGNORE INTO roles (RoleId, RoleTitle) VALUES (2, 'LECTURER');
INSERT IGNORE INTO roles (RoleId, RoleTitle) VALUES (3, 'USER');

-- Test kullanıcıları
INSERT IGNORE INTO users (UserId, UserFirstName, UserLastName, UserEmail, UserPassword) 
VALUES (1, 'Admin', 'User', 'admin@test.com', 'admin123');

INSERT IGNORE INTO users (UserId, UserFirstName, UserLastName, UserEmail, UserPassword) 
VALUES (2, 'Öğretim', 'Görevlisi', 'lecturer@test.com', 'lecturer123');

-- Kullanıcı-rol ilişkileri
INSERT IGNORE INTO userinroles (UserInRoles, UserId, RoleId) VALUES (1, 1, 1);
INSERT IGNORE INTO userinroles (UserInRoles, UserId, RoleId) VALUES (2, 2, 2); 
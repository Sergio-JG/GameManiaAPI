INSERT INTO role (role_id, name) VALUES
    (UUID_TO_BIN(UUID()), 'Admin'),
    (UUID_TO_BIN(UUID()), 'User');
INSERT INTO platform (platform_id, name) VALUES
    (UUID_TO_BIN(UUID()), 'PC'),
    (UUID_TO_BIN(UUID()), 'PlayStation'),
    (UUID_TO_BIN(UUID()), 'Xbox'),
    (UUID_TO_BIN(UUID()), 'Nintendo Switch'),
    (UUID_TO_BIN(UUID()), 'Móvil');
INSERT INTO genre (genre_id, name) VALUES
    (UUID_TO_BIN(UUID()), 'Acción'),
    (UUID_TO_BIN(UUID()), 'Aventura'),
    (UUID_TO_BIN(UUID()), 'Carrera'),
    (UUID_TO_BIN(UUID()), 'RPG'),
    (UUID_TO_BIN(UUID()), 'Simulación');
INSERT INTO game (game_id, title, price, description, image, release_date, number_of_sales, stock, total_score) VALUES
    (UUID_TO_BIN(UUID()), 'The Legend of Zelda: Breath of the Wild', 59.99, 'Explora el vasto reino de Hyrule en esta aventura de mundo abierto.', 'TheLegendOfZeldaBreathOfTheWild.jpg', '2017-03-03', 15000000, 3000, 9.5),
    (UUID_TO_BIN(UUID()), 'The Witcher 3: Wild Hunt', 39.99, 'Embárcate en una búsqueda como Geralt de Rivia en este épico RPG.', 'TheWitcher3WildHunt.jpg', '2015-05-19', 10000000, 3000, 9.8),
    (UUID_TO_BIN(UUID()), 'Red Dead Redemption 2', 49.99, 'Vive la vida de un forajido en el Salvaje Oeste americano.', 'RedDeadRedemption2.jpg', '2018-10-26', 11000000, 3000, 9.7),
    (UUID_TO_BIN(UUID()), 'Grand Theft Auto V', 29.99, 'Experimenta el inframundo criminal en el estado ficticio de San Andreas.', 'GrandTheftAutoV.jpg', '2013-09-17', 140000000, 3000, 9.6),
    (UUID_TO_BIN(UUID()), 'The Elder Scrolls V: Skyrim', 19.99, 'Descubre los misterios de Skyrim en este RPG de mundo abierto.', 'TheElderScrollsVSkyrim.jpg', '2011-11-11', 30000000, 3000, 9.3),
    (UUID_TO_BIN(UUID()), 'Minecraft', 19.99, 'Construye, explora y sobrevive en un mundo abierto hecho de bloques.', 'Minecraft.jpg', '2011-11-18', 200000000, 3000, 9.0),
    (UUID_TO_BIN(UUID()), 'Fortnite', 0.00, 'Lucha contra otros jugadores en esta sensación de battle royale.', 'Fortnite.jpg', '2017-07-25', 350000000, 3000, 8.6),
    (UUID_TO_BIN(UUID()), 'Among Us', 4.99, 'Descubre quiénes son los impostores en este juego multijugador de engaño.', 'AmongUs.jpg', '2018-11-16', 30000000, 3000, 8.4),
    (UUID_TO_BIN(UUID()), 'Cyberpunk 2077', 39.99, 'Entra en un futuro distópico en este RPG lleno de acción.', 'Cyberpunk2077.jpg', '2020-12-10', 13000000, 3000, 7.2),
    (UUID_TO_BIN(UUID()), 'Call of Duty: Warzone', 0.00, 'Participa en batallas intensas en este battle royale gratuito para jugar.', 'CallOfDutyWarzone.jpg', '2020-03-10', 90000000, 3000, 8.0);
INSERT INTO social (social_id, steam_url, twitch_url, youtube_url, discord_tag) VALUES
    (UUID_TO_BIN(UUID()), 'steam/johndoe', 'twitch/johndoe', 'youtube/johndoe', 'JohnDoe#1234'),
    (UUID_TO_BIN(UUID()), 'steam/alice', 'twitch/alice', 'youtube/alice', 'Alice#5678'),
    (UUID_TO_BIN(UUID()), 'steam/bob', 'twitch/bob', 'youtube/bob', 'Bob#9876'),
    (UUID_TO_BIN(UUID()), 'steam/charlie', 'twitch/charlie', 'youtube/charlie', 'Charlie#6789'),
    (UUID_TO_BIN(UUID()), 'steam/david', 'twitch/david', 'youtube/david', 'David#5432');
INSERT INTO user (user_id, email, first_name, last_name, password, phone, profile_pic, username, address_id, role_id, social_id) VALUES
    (UUID_TO_BIN(UUID()), 'admin@admin.com', 'John', 'Doe', 'adminpass', 672345643, 'john_doe.jpg', 'john_doe', (SELECT address_id FROM address WHERE street_address = 'Calle fake 1'), (SELECT role_id FROM role WHERE name = 'Admin'), (SELECT social_id FROM social WHERE discord_tag = 'JohnDoe#1234')),
    (UUID_TO_BIN(UUID()), 'alice@email.com', 'Alice', 'Wonder', 'hashed_password', 679124543, 'alice.jpg', 'alice_wonder', (SELECT address_id FROM address WHERE street_address = 'Calle fake 2'), (SELECT role_id FROM role WHERE name = 'User'), (SELECT social_id FROM social WHERE discord_tag = 'Alice#5678')),
    (UUID_TO_BIN(UUID()), 'bob@email.com', 'Bob', 'Aventurar', 'hashed_password', 680687233, 'bob.jpg', 'bob_Aventurar', (SELECT address_id FROM address WHERE street_address = 'Calle fake 3'), (SELECT role_id FROM role WHERE name = 'User'), (SELECT social_id FROM social WHERE discord_tag = 'Bob#9876')),
    (UUID_TO_BIN(UUID()), 'charlie@email.com', 'Charlie', 'Gamer', 'hashed_password', 679237543, 'charlie.jpg', 'charlie_gamer', (SELECT address_id FROM address WHERE street_address = 'Calle fake 4'), (SELECT role_id FROM role WHERE name = 'User'), (SELECT social_id FROM social WHERE discord_tag = 'Charlie#6789')),
    (UUID_TO_BIN(UUID()), 'david@email.com', 'David', 'Player', 'hashed_password', 679687543, 'david.jpg', 'david_player', (SELECT address_id FROM address WHERE street_address = 'Calle fake 5'), (SELECT role_id FROM role WHERE name = 'User'), (SELECT social_id FROM social WHERE discord_tag = 'David#5432')),
    (UUID_TO_BIN(UUID()), 'eliza@email.com', 'Eliza', 'Gamer', 'hashed_password', 739682243, 'eliza.jpg', 'eliza_gamer', (SELECT address_id FROM address WHERE street_address = 'Calle fake 6'), (SELECT role_id FROM role WHERE name = 'User'), null);
INSERT INTO game_platform (platform_id, game_id) VALUES
    ((SELECT platform_id FROM platform WHERE name = 'PC'), (SELECT game_id FROM game WHERE title = 'The Legend of Zelda: Breath of the Wild')),
    ((SELECT platform_id FROM platform WHERE name = 'PlayStation'), (SELECT game_id FROM game WHERE title = 'The Witcher 3: Wild Hunt')),
    ((SELECT platform_id FROM platform WHERE name = 'Xbox'), (SELECT game_id FROM game WHERE title = 'Red Dead Redemption 2')),
    ((SELECT platform_id FROM platform WHERE name = 'Nintendo Switch'), (SELECT game_id FROM game WHERE title = 'The Legend of Zelda: Breath of the Wild')),
    ((SELECT platform_id FROM platform WHERE name = 'Móvil'), (SELECT game_id FROM game WHERE title = 'Fortnite'));
INSERT INTO game_genre (game_id, genre_id) VALUES
    ((SELECT game_id FROM game WHERE title = 'The Legend of Zelda: Breath of the Wild'), (SELECT genre_id FROM genre WHERE name = 'Acción')),
    ((SELECT game_id FROM game WHERE title = 'The Witcher 3: Wild Hunt'), (SELECT genre_id FROM genre WHERE name = 'RPG')),
    ((SELECT game_id FROM game WHERE title = 'Red Dead Redemption 2'), (SELECT genre_id FROM genre WHERE name = 'Acción')),
    ((SELECT game_id FROM game WHERE title = 'Grand Theft Auto V'), (SELECT genre_id FROM genre WHERE name = 'RPG')),
    ((SELECT game_id FROM game WHERE title = 'Minecraft'), (SELECT genre_id FROM genre WHERE name = 'Simulación'));
INSERT INTO review (review_id, user_id, game_id, score, comment) VALUES
    (UUID_TO_BIN(UUID()), (SELECT user_id FROM user WHERE username = 'john_doe'), (SELECT game_id FROM game WHERE title = 'The Legend of Zelda: Breath of the Wild'), 4.5, 'Exciting Aventura!'),
    (UUID_TO_BIN(UUID()), (SELECT user_id FROM user WHERE username = 'alice_wonder'), (SELECT game_id FROM game WHERE title = 'The Witcher 3: Wild Hunt'), 4.2, 'Great RPG game!'),
    (UUID_TO_BIN(UUID()), (SELECT user_id FROM user WHERE username = 'bob_Aventurar'), (SELECT game_id FROM game WHERE title = 'Red Dead Redemption 2'), 4.8, 'Amazing open world!'),
    (UUID_TO_BIN(UUID()), (SELECT user_id FROM user WHERE username = 'charlie_gamer'), (SELECT game_id FROM game WHERE title = 'Grand Theft Auto V'), 3.9, 'Crime-filled Aventura!'),
    (UUID_TO_BIN(UUID()), (SELECT user_id FROM user WHERE username = 'david_player'), (SELECT game_id FROM game WHERE title = 'Minecraft'), 4.1, 'Endless creativity!');
INSERT INTO provider (provider_id, name, address, phone, email) VALUES
    (UUID_TO_BIN(UUID()), 'GameCo', '123 Game St, Gametown', '123-456-7890', 'contact@gameco.com'),
    (UUID_TO_BIN(UUID()), 'Aventura Games', '456 Quest Ave, Aventura City', '987-654-3210', 'info@Aventuragames.com'),
    (UUID_TO_BIN(UUID()), 'Space Explorers', '789 Universe Rd, Space Center', '111-222-3333', 'support@spaceexplorers.com');
INSERT INTO account (account_id, provider_id, account_holder_name, account_number, bank_name, bank_address, bank_routing_number, account_balance) VALUES
    (UUID_TO_BIN(UUID()), (SELECT provider_id FROM provider WHERE name = 'GameCo'), 'GameCo Account Holder', '12345678', 'Game Bank', '123 Bank St', '98765432', 1000000.00),
    (UUID_TO_BIN(UUID()), (SELECT provider_id FROM provider WHERE name = 'Aventura Games'), 'Aventura Games Account Holder', '87654321', 'Aventura Bank', '456 Aventura Ave', '12345678', 750000.00),
    (UUID_TO_BIN(UUID()), (SELECT provider_id FROM provider WHERE name = 'Space Explorers'), 'Space Explorers Account Holder', '1111222233334444', 'Space Bank', '789 Space Rd', '55556666', 500000.00);
INSERT INTO purchase (purchase_id, provider_id, purchase_date, total_amount) VALUES
    (UUID_TO_BIN(UUID()), (SELECT provider_id FROM provider WHERE name = 'GameCo'), '2023-08-07', 299.99),
    (UUID_TO_BIN(UUID()), (SELECT provider_id FROM provider WHERE name = 'Aventura Games'), '2023-08-08', 199.99),
    (UUID_TO_BIN(UUID()), (SELECT provider_id FROM provider WHERE name = 'Space Explorers'), '2023-08-09', 399.99);
INSERT INTO purchase_detail (purchase_detail_id, purchase_id, game_id, quantity, subtotal) VALUES
    (UUID_TO_BIN(UUID()), (SELECT purchase_id FROM purchase WHERE purchase_date = '2023-08-07'), (SELECT game_id FROM game WHERE title = 'The Legend of Zelda: Breath of the Wild'), 1, 59.99),
    (UUID_TO_BIN(UUID()), (SELECT purchase_id FROM purchase WHERE purchase_date = '2023-08-08'), (SELECT game_id FROM game WHERE title = 'The Witcher 3: Wild Hunt'), 1, 39.99),
    (UUID_TO_BIN(UUID()), (SELECT purchase_id FROM purchase WHERE purchase_date = '2023-08-09'), (SELECT game_id FROM game WHERE title = 'Red Dead Redemption 2'), 1, 49.99);
INSERT INTO sale (sale_id, user_id, sale_date, total_amount) VALUES
    (UUID_TO_BIN(UUID()), (SELECT user_id FROM user WHERE username = 'john_doe'), '2023-08-10', 59.99),
    (UUID_TO_BIN(UUID()), (SELECT user_id FROM user WHERE username = 'alice_wonder'), '2023-08-11', 49.99),
    (UUID_TO_BIN(UUID()), (SELECT user_id FROM user WHERE username = 'bob_Aventurar'), '2023-08-12', 69.99);
INSERT INTO sale_detail (sale_detail_id, sale_id, game_id, quantity, subtotal) VALUES
    (UUID_TO_BIN(UUID()), (SELECT sale_id FROM sale WHERE sale_date = '2023-08-10'), (SELECT game_id FROM game WHERE title = 'The Legend of Zelda: Breath of the Wild'), 1, 59.99),
    (UUID_TO_BIN(UUID()), (SELECT sale_id FROM sale WHERE sale_date = '2023-08-11'), (SELECT game_id FROM game WHERE title = 'The Witcher 3: Wild Hunt'), 1, 39.99),
    (UUID_TO_BIN(UUID()), (SELECT sale_id FROM sale WHERE sale_date = '2023-08-12'), (SELECT game_id FROM game WHERE title = 'Red Dead Redemption 2'), 1, 49.99);



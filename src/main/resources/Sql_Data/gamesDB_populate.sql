-- games
INSERT INTO games (game_id, game_name, creator_name) VALUES('10001', 'Monopoly', 'Hasbro');
INSERT INTO games (game_id, game_name, creator_name) VALUES('10002', 'Hektor', 'Attaway');
INSERT INTO games (game_id, game_name, creator_name) VALUES('10003', 'Torbjørg', 'Maynard');
INSERT INTO games (game_id, game_name, creator_name) VALUES('10004', 'Agni', 'Van Altena');
INSERT INTO games (game_id, game_name, creator_name) VALUES('10005', 'Terzo', 'Kappel');


-- expansions
INSERT INTO expansions (expansion_id, game_fk, expansion_name) VALUES('20001', 1, 'WRANGLER');
INSERT INTO expansions (expansion_id, game_fk, expansion_name) VALUES('20002', 2, 'WRANGLER');
INSERT INTO expansions (expansion_id, game_fk, expansion_name) VALUES('20003', 3, 'WRANGLER');
INSERT INTO expansions (expansion_id, game_fk, expansion_name) VALUES('20004', 4, 'WRANGLER');
INSERT INTO expansions (expansion_id, game_fk, expansion_name) VALUES('20005', 5, 'WRANGLER');

-- rules
INSERT INTO rules (rules_id, game_fk, rule_text) VALUES('30001', 1, 'thththth');
INSERT INTO rules (rules_id, game_fk, rule_text) VALUES('30002', 2, 'sdfgsdfgsd');
INSERT INTO rules (rules_id, game_fk, rule_text) VALUES('30003', 3, 'werwerwe');
INSERT INTO rules (rules_id, game_fk, rule_text) VALUES('30004', 4, 'cvbcvbcv');

-- cards
INSERT INTO cards (card_id, expansion_fk, card_name) VALUES('40001', 1, 'Tom');
INSERT INTO cards (card_id, expansion_fk, card_name) VALUES('40002', 2, 'Knife');
INSERT INTO cards (card_id, expansion_fk, card_name) VALUES('40003', 3, 'Goblin');
INSERT INTO cards (card_id, expansion_fk, card_name) VALUES('40004', 4, 'House');

-- users
INSERT INTO users (user_id, user_name) VALUES('50001', 'Sport');
INSERT INTO users (user_id, user_name) VALUES('50002', '80th');
INSERT INTO users (user_id, user_name) VALUES('50003', 'Limited');
INSERT INTO users (user_id, user_name) VALUES('50004', 'Trailhawk');

-- decks
INSERT INTO decks (deck_id, user_fk, deck_name) VALUES('60001',  1, 'Cats');
INSERT INTO decks (deck_id, user_fk, deck_name) VALUES('60002',  2, 'Dogs');
INSERT INTO decks (deck_id, user_fk, deck_name) VALUES('60003',  3, 'Turtles');

-- cardsInDecks
INSERT INTO cardsInDecks (card_fk, deck_fk) VALUES(1, 1);
INSERT INTO cardsInDecks (card_fk, deck_fk) VALUES(1, 1);
INSERT INTO cardsInDecks (card_fk, deck_fk) VALUES(1, 2);
INSERT INTO cardsInDecks (card_fk, deck_fk) VALUES(2, 3);
INSERT INTO cardsInDecks (card_fk, deck_fk) VALUES(2, 1);
INSERT INTO cardsInDecks (card_fk, deck_fk) VALUES(2, 3);
INSERT INTO cardsInDecks (card_fk, deck_fk) VALUES(3, 1);

-- userOwnedGames
INSERT INTO userOwnedGames (user_fk, game_fk) VALUES(1, 1);
INSERT INTO userOwnedGames (user_fk, game_fk) VALUES(1, 2);
INSERT INTO userOwnedGames (user_fk, game_fk) VALUES(1, 3);
INSERT INTO userOwnedGames (user_fk, game_fk) VALUES(2, 2);
INSERT INTO userOwnedGames (user_fk, game_fk) VALUES(2, 2);
INSERT INTO userOwnedGames (user_fk, game_fk) VALUES(3, 1);

-- gamepieces
INSERT INTO gamepieces(game_piece_name,game_piece_desc, game_fk) VALUES ('6-Sided Die', 'A standard die', 1);
INSERT INTO gamepieces(game_piece_name,game_piece_desc, game_fk) VALUES ('One-dollar Bill', 'Paper bill of $1 denomination.', 1);
INSERT INTO gamepieces(game_piece_name,game_piece_desc, game_fk) VALUES ('Five-dollar Bill', 'Paper bill of $5 denomination.', 1);
INSERT INTO gamepieces(game_piece_name,game_piece_desc, game_fk) VALUES ('Metal Car', 'Miniature car figurine.', 1);
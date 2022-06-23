create database if not exists gameDB;
use gameDB;


DROP TABLE IF EXISTS cardsInDecks;
DROP TABLE IF EXISTS userOwnedGames;
DROP TABLE IF EXISTS decks;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS cards;
DROP TABLE IF EXISTS rules;
DROP TABLE IF EXISTS expansions;
DROP TABLE IF EXISTS gamepieces;
DROP TABLE IF EXISTS games;

CREATE TABLE games (
  game_pk int unsigned NOT NULL AUTO_INCREMENT,
  game_id varchar(40) NOT NULL,
  game_name varchar(45) NOT NULL, 
  creator_name varchar(45) NOT NULL,
  PRIMARY KEY (game_pk)
);

CREATE TABLE expansions (
  expansion_pk int unsigned NOT NULL AUTO_INCREMENT,
  expansion_id varchar(40) NOT NULL,
  game_fk int unsigned NOT NULL,
  expansion_name varchar(45) NOT NULL,
  PRIMARY KEY (expansion_pk),
  FOREIGN KEY (game_fk) REFERENCES games (game_pk)
);

CREATE TABLE rules (
  rules_pk int unsigned NOT NULL AUTO_INCREMENT,
  rules_id varchar(40) NOT NULL,
  game_fk int unsigned NOT NULL,
  rule_text varchar(100) NOT NULL,
  PRIMARY KEY (rules_pk),
  FOREIGN KEY (game_fk) REFERENCES games (game_pk)
);

CREATE TABLE cards (
  card_pk int unsigned NOT NULL AUTO_INCREMENT,
  card_id varchar(40) NOT NULL,
  expansion_fk int unsigned NOT NULL,
  card_name varchar(45) NOT NULL,
  PRIMARY KEY (card_pk),
  FOREIGN KEY (expansion_fk) REFERENCES expansions (expansion_pk)
);

CREATE TABLE users (
  user_pk int unsigned NOT NULL AUTO_INCREMENT,
  user_id varchar(40) NOT NULL,
  user_name varchar(45) NOT NULL,
  PRIMARY KEY (user_pk)
);

CREATE TABLE decks (
  deck_pk int unsigned NOT NULL AUTO_INCREMENT,
  deck_id varchar(40) NOT NULL,
  user_fk int unsigned NOT NULL,
  deck_name varchar(45) NOT NULL,
  PRIMARY KEY (deck_pk),
  FOREIGN KEY (user_fk) REFERENCES users (user_pk) 
);

CREATE TABLE cardsInDecks (
  cardsInDeck_pk int unsigned NOT NULL AUTO_INCREMENT,
  card_fk int unsigned NOT NULL,
  deck_fk int unsigned NOT NULL,
  PRIMARY KEY (cardsInDeck_pk),
  FOREIGN KEY (card_fk) REFERENCES cards (card_pk) ON DELETE CASCADE,
  FOREIGN KEY (deck_fk) REFERENCES decks (deck_pk) ON DELETE CASCADE
);

CREATE TABLE userOwnedGames (
  userOwnedGames_pk int unsigned NOT NULL AUTO_INCREMENT,
  user_fk int unsigned NOT NULL,
  game_fk int unsigned NOT NULL,
  PRIMARY KEY (userOwnedGames_pk),
  FOREIGN KEY (user_fk) REFERENCES users (user_pk),
  FOREIGN KEY (game_fk) REFERENCES games (game_pk)
);

CREATE TABLE gamepieces (
  game_piece_pk int unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
  game_piece_name varchar(40) NOT NULL,
  game_piece_desc TEXT,
  game_fk int unsigned NOT NULL,
  FOREIGN KEY (game_fk) REFERENCES games(game_pk)
);
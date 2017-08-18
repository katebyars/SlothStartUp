SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS events (
  id int PRIMARY KEY auto_increment,
  name VARCHAR,
  description VARCHAR,
  speaker VARCHAR,
  room VARCHAR
);
CREATE TABLE IF NOT EXISTS attendees (
  id int PRIMARY KEY auto_increment,
  name VARCHAR,
  homecity VARCHAR,
  age INTEGER,
  foodpreference VARCHAR,
  eventId INTEGER
);
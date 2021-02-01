CREATE TABLE IF NOT EXISTS "knjiga" (
	"id"	INTEGER,
	"naslov"	TEXT,
	"autor"	TEXT,
	"isbn"	INTEGER,
	PRIMARY KEY("id")
);
INSERT INTO "knjiga" VALUES (1,'I bring nothing to the table','SheeraSeven','123-23');
INSERT INTO "knjiga" VALUES (2,'The 5 AM Club','Robin Sharma','34-43');
INSERT INTO "knjiga" VALUES (3,'Atomic Habits','James Clear','1-11');

db.createUser({
  user: 'root',
  pwd: 'docker',
  roles: [
    {
      role: 'readWrite',
      db: 'api',
    },
  ],
});

db = new Mongo().getDB('api');

db.key.insert([
  { item: "card", qty: 15 },
  { item: "album", qty: 30 },
  { item: "pen", qty: 45 }
]);

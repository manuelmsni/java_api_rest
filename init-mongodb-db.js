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

db.post.insertMany([
  {
    userId: 1,
    content: "Este es el primer post de prueba",
    imageUrls: ["http://example.com/image1.jpg"],
    comentarios: []
  },
  {
    userId: 2,
    content: "Este es el segundo post, con más contenido de ejemplo",
    imageUrls: ["http://example.com/image2.jpg", "http://example.com/image3.jpg"],
    comentarios: []
  }
]);

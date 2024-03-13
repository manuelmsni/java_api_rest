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
    _id: ObjectId('65f08e2842829023931618f1'),
    userId: 1,
    content: "Este es el primer post de prueba",
    imageUrls: ["https://img.freepik.com/fotos-premium/imagen-fotografica-foto-stock-fondo-pantalla-hd-8k-hermosa-naturaleza-gratuita_915071-82095.jpg"],
    comentarios: []
  },
  {
    _id: ObjectId('65f08e2842829023931618f2'),
    userId: 2,
    responseTo: ObjectId('65f08e2842829023931618f1'),
    content: "Este es el segundo post, con más contenido de ejemplo",
    imageUrls: ["https://png.pngtree.com/background/20230610/original/pngtree-landscapes-wallpaper-images-picture-image_3021437.jpg",
                "https://png.pngtree.com/background/20230501/original/pngtree-beautiful-nature-background-tree-in-a-garden-mountains-forest-ai-picture-image_2499901.jpg"],
    comentarios: []
  }
]);

db.userprofile.insertMany([
  {
    userId: 1,
    description: "Descripción del usuario 1",
    profileImage: "https://png.pngtree.com/background/20230612/original/pngtree-wolf-animals-images-wallpaper-for-pc-384x480-picture-image_3180467.jpg"
  },
  {
    userId: 2,
    description: "Descripción del usuario 2",
    profileImage: "https://png.pngtree.com/background/20230524/original/pngtree-sad-pictures-for-desktop-hd-backgrounds-picture-image_2705986.jpg"
  },
  {
    userId: 3,
    description: "Descripción del usuario 3",
    profileImage: "https://static.vecteezy.com/system/resources/previews/013/078/569/non_2x/illustration-of-cute-colored-cat-cartoon-cat-image-in-format-suitable-for-children-s-book-design-elements-introduction-of-cats-to-children-books-or-posters-about-animal-free-png.png"
  }
]);

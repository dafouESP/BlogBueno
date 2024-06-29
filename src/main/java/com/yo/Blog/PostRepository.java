package com.yo.Blog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*El <Post, Long> indica que la interfaz del repositorio está diseñada para trabajar con objetos de la clase Post, donde cada objeto Post está asociado
 a un identificador único (ID) de tipo Long. La clase Post no puede ser cualquier clase sino una marcada como @Entity, es decir una clase de entidad. Cada
  instancia de Post que se almacene en la base de datos tendrá un valor único para su campo id, que actúa como clave primaria en la tabla correspondiente 
  en la base de datos. Este id es crucial para identificar de manera única cada registro en la tabla, permitiendo así realizar operaciones de lectura, 
  actualización y eliminación precisas.

 El Long, que será el id, aunque parezca que va fuera del objeto, se mete dentro en su campo id. Por tanto es obligatorio que la clase Post tenga ese campo id.
 */

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    
}

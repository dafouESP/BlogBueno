package com.yo.Blog;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import org.springframework.http.HttpStatus;
import java.util.Optional;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;



public class PostControllerTes {

@Mock
private PostService postService;

@InjectMocks
private PostController postController;

@BeforeEach
public void setUp(){
    MockitoAnnotations.openMocks(this);
}


@Test
public void testGetAllPosts() {
    Post mockPost = new Post(1L, "T", "C", "A");
    when(postService.findAll()).thenReturn(List.of(mockPost));
    List<Post> posts = postController.getAllPosts();
    assertThat(posts.size()).isEqualTo(1);
    verify(postService, times(1)).findAll();

}

@Test
public void testGetPostById(){
    Long id = 1L;
    Post mockPost = new Post(id, "Title", "Content", "Author");
    when(postService.findById(id)).thenReturn(Optional.of(mockPost));
    
/*Lo que hace la linea siguiente es invocar el método getPostById(id) del controlador postController, que debería estar diseñado para enviar una solicitud 
al servidor (o a algún servicio interno) y luego devolver la respuesta obtenida. La respuesta se encapsula en un objeto ResponseEntity<Post>, que es una clase 
de Spring que representa completamente la respuesta HTTP */

    ResponseEntity<Post> response = postController.getPostById(id);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull();
    verify(postService, times(1)).findById(id);
}

@Test
public void testCreatePost(){
    Post mockPost = new Post(null, "T", "C", "A");
    when(postService.save(mockPost)).thenReturn(mockPost);
    Post createdPost = postController.createPost(mockPost);
    assertThat(createdPost.getId()).isNotNull();
    verify(postService, times(1)).save(mockPost);
}

@Test 
public void testUpdatePost(){
    Long id = 1L;
    Post mockPost = new Post(id, "e", "r", "t");
    when(postService.findById(id)).thenReturn(Optional.of(mockPost));
    when(postService.save(mockPost)).thenReturn(mockPost);
    ResponseEntity<Post> response = postController.updatePost(id, mockPost);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody().getTitle()).isEqualTo("e");
    verify(postService, times(1)).save(mockPost);

}

@Test
public void testDeletePost(){
    Long id = 1L;
    postController.deletePost(id);
    verify(postService, times(1)).deleteById(id);
}


    
}

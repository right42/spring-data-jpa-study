package me.right42.springdatajpa.web;

import me.right42.springdatajpa.domain.Post;
import me.right42.springdatajpa.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PostRestControllerTest {

    @Autowired
    PostRepository postRepository;

    @Autowired
    MockMvc mockMvc;


    @Test
    void getPost() throws Exception {
        Post post = new Post();
        post.setTitle("title");
        postRepository.save(post);

        mockMvc.perform(get("/posts/" + post.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("title"))

        ;
    }

    @Test
    void getPosts() throws Exception {
        createPost(100);

        mockMvc.perform(
                    get("/posts")
                        .param("page", "0")
                        .param("size", "10")
                        .param("sort", "createdDate,desc")
                        .param("sort", "title")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.page.size").value("10"))

        ;
    }

    private void createPost(int postsCount) {
        while(postsCount > 0) {
            Post post = new Post();
            post.setTitle("title");
            postRepository.save(post);
            postsCount--;
        }
    }


    @Test
    void test() throws Exception {
        mockMvc.perform(
                get("/posts/test")
                    .param("title", "test")
            )
            .andExpect(status().isOk());

    }
}
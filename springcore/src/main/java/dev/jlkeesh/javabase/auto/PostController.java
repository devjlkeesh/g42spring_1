package dev.jlkeesh.javabase.auto;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class PostController {
    private final PostService postService;

    /*public PostController(PostService grpcPostService) {
        this.grpcPostService = grpcPostService;
    }*/

    public PostController(@Qualifier("restPostService") PostService postService) {
        this.postService = postService;
    }
}

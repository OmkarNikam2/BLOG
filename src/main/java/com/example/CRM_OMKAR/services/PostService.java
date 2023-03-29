package com.example.CRM_OMKAR.services;

import com.example.CRM_OMKAR.payloads.PostDto;
import com.example.CRM_OMKAR.payloads.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto, Integer userId,Integer categoryId);
    PostDto updatePost(PostDto postDto,Integer postId);
    PostDto deletePost(Integer postId);
    PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);
    PostDto getPostById(Integer postId);
    List<PostDto> getPostsByCategory(Integer categoryId);
    List<PostDto> getPostsByUser(Integer userId);

    //search posts
    List<PostDto> searchPosts(String keyword);


}

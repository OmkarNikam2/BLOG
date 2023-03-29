package com.example.CRM_OMKAR.services.impl;

import com.example.CRM_OMKAR.entities.Category;
import com.example.CRM_OMKAR.entities.Post;
import com.example.CRM_OMKAR.entities.User;
import com.example.CRM_OMKAR.exceptions.ResourceNotFoundException;
import com.example.CRM_OMKAR.payloads.PostDto;
import com.example.CRM_OMKAR.payloads.PostResponse;
import com.example.CRM_OMKAR.repositories.CategoryRepo;
import com.example.CRM_OMKAR.repositories.PostRepo;
import com.example.CRM_OMKAR.repositories.UserRepo;
import com.example.CRM_OMKAR.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Override
    public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {

        User user=this.userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","User id",userId));

        Category category=this.categoryRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category","category id",categoryId));


        Post post=this.modelMapper.map(postDto,Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post newPost=this.postRepo.save(post);
        return this.modelMapper.map(newPost,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post=this.postRepo.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post","post Id",postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());

        Post updatepost=this.postRepo.save(post);
        PostDto updateDto=this.modelMapper.map(updatepost, PostDto.class);
        return updateDto;
    }

    @Override
    public PostDto deletePost(Integer postId) {
        Post post=this.postRepo.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post","post Id",postId));
        this.postRepo.delete(post);

        return null;
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {
        // Adding Pagination & sorting
        Sort sort=(sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        Pageable p= PageRequest.of(pageSize,pageNumber,sort);
        Page<Post> pagePost=this.postRepo.findAll(p);
        List<Post> allPosts=pagePost.getContent();

        //Pageable object -> Page object -> getContent() -> List<Post> ->stream.map.collectors

        List<PostDto> postDtos=allPosts.stream().map((post) ->this.modelMapper.map(post,PostDto.class))
                .collect(Collectors.toList());

        PostResponse postResponse=new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","post id",postId));
        PostDto postdto=this.modelMapper.map(post,PostDto.class);
        return postdto;
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        Category cat=this.categoryRepo.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category","category id",categoryId));
        List<Post> posts=this.postRepo.findByCategory(cat);

        List<PostDto> postDtos=posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
        User user=this.userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","user id",userId));
        List<Post> posts=this.postRepo.findByUser(user);

        List<PostDto> postDtos=posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        return null;
    }
}

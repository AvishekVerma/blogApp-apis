package com.blog.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.Comment;
import com.blog.entities.Post;
import com.blog.excep.ResourceNotFoundException;
import com.blog.payloads.CommentDto;
import com.blog.repo.CommentRepo;
import com.blog.repo.PostRepo;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		
		Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "postId", postId));;
		Comment comment = modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		Comment savedComment = commentRepo.save(comment);
		CommentDto savedCommentDto = modelMapper.map(savedComment, CommentDto.class);
		
		return savedCommentDto;
	}

	@Override
	public void deletComment(Integer commentId) {
		Comment comment = commentRepo.findById(commentId).orElseThrow((()-> new ResourceNotFoundException("Comment", "commentId", commentId)));
		commentRepo.delete(comment);
	}

}

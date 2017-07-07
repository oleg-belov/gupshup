package com.stackroute.gupshup.recommendationservice.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.gupshup.recommendationservice.entity.CircleRecommendation;
import com.stackroute.gupshup.recommendationservice.entity.UserRecommendation;
import com.stackroute.gupshup.recommendationservice.exception.RecommendationException;
import com.stackroute.gupshup.recommendationservice.service.CircleRecommendationService;
import com.stackroute.gupshup.recommendationservice.service.UserRecommendationService;

@CrossOrigin()
@RestController
@RequestMapping("recommendation")
public class RecommendationController {
	
	@Autowired
	UserRecommendationService userRecommendationService;
	
	@Autowired
	CircleRecommendationService circleRecommendationService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity getRouteError(){
		
		return new ResponseEntity<>("Incorrect URL",HttpStatus.FORBIDDEN);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.POST)
	public ResponseEntity postRouteError(){
		return new ResponseEntity<>("Incorrect URL",HttpStatus.FORBIDDEN);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity putRouteError(){
		return new ResponseEntity<>("Incorrect URL",HttpStatus.FORBIDDEN);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity deleteRouteError(){
		return new ResponseEntity<>("Incorrect URL",HttpStatus.FORBIDDEN);
	}
	
	
	@RequestMapping(value="/user/{id}", method=RequestMethod.GET)
	public ResponseEntity followFriendOfFriend(@PathVariable String id)
	{
		Iterable<List<String>> list;
		try {
			list = userRecommendationService.followRecommendation(id);
		} catch (RecommendationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>("Username incorrect",HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<Iterable<List<String>>>(list, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/circle/{id}", method=RequestMethod.GET)
	public ResponseEntity subscribeRecommendation(@PathVariable String id){
		Iterable<List<String>> list;
		try {
			list=circleRecommendationService.subscribeRecommendation(id);
		} catch (RecommendationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>("Username incorrect",HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/createuser", method=RequestMethod.POST)
	public ResponseEntity createUser(@Valid @RequestBody UserRecommendation userRecommendation, BindingResult bindingResult){
		if(bindingResult.hasErrors())
		{
			return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
		}
		try {
				userRecommendationService.createUser(userRecommendation);
		} catch (RecommendationException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>("Username already exists",HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<>(userRecommendation,HttpStatus.OK);
	}
	
	@RequestMapping(value="/updateuser", method=RequestMethod.PUT)
	public ResponseEntity updateUser(@Valid @RequestBody UserRecommendation userRecommendation, BindingResult bindingResult){
		if(bindingResult.hasErrors())
		{
			return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
		}
		try {
			userRecommendationService.updateUser(userRecommendation);
		} catch (RecommendationException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>("Username incorrect",HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<>(userRecommendation,HttpStatus.OK);
	}
	
	@RequestMapping(value="/deleteuser/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteUser(@PathVariable String id)
	{
		try {
			userRecommendationService.deleteUser(id);
		} catch (RecommendationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>("Username incorrect",HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<>("User Deleted", HttpStatus.OK);
	}
	
	@RequestMapping(value="/createcircle", method=RequestMethod.POST)
	public ResponseEntity createCircle(@Valid @RequestBody CircleRecommendation circleRecommendation, BindingResult bindingResult){
		if(bindingResult.hasErrors())
		{
			return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
		}
		
		try {
			circleRecommendationService.createCircle(circleRecommendation);
		} catch (RecommendationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>("Circle ID already exists", HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<>(circleRecommendation, HttpStatus.OK);
	}
	
	@RequestMapping(value="/deletecircle/{id}", method=RequestMethod.DELETE)
	public ResponseEntity deleteCircle(@PathVariable String id)
	{
		try {
			circleRecommendationService.deleteCircle(id);
		} catch (RecommendationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>("Circle ID incorrect",HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<>("Circle deleted",HttpStatus.OK);
	}
	
	@RequestMapping(value="/updatecircle", method=RequestMethod.PUT)
	public ResponseEntity updateCircle(@Valid @RequestBody CircleRecommendation circleRecommendation, BindingResult bindingResult){
		if(bindingResult.hasErrors())
		{
			return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
		}
		
		try {
			circleRecommendationService.updateCircle(circleRecommendation);
		} catch (RecommendationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>("Circle ID incorrect",HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(circleRecommendation,HttpStatus.OK);
	}
	
	@RequestMapping(value="/follows/{id1}/{id2}", method=RequestMethod.GET)
	public ResponseEntity follows(@PathVariable String id1, @PathVariable String id2){
		try {
			userRecommendationService.follows(id1,id2);
		} catch (RecommendationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>("Username incorrect", HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<>(id1+" follows "+id2,HttpStatus.OK);
	}
	
	@RequestMapping(value="/subscribed/{id1}/{id2}", method=RequestMethod.GET)
	public ResponseEntity subscribed(@PathVariable String id1, @PathVariable String id2){
		try {
			circleRecommendationService.subscribed(id1,id2);
		} catch (RecommendationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>("Circle ID or user ID incorrect",HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<>(id1+" subscribed to "+id2,HttpStatus.OK);
	}
	
	@RequestMapping(value="/leavecircle/{id1}/{id2}", method=RequestMethod.GET)
	public ResponseEntity leaveCircle(@PathVariable String id1, @PathVariable String id2){
		try {
			circleRecommendationService.leaveCircle(id1, id2);
		} catch (RecommendationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>("Circle ID or user ID incorrect",HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(id1+" unsubcribed from "+id2,HttpStatus.OK);
	}

}

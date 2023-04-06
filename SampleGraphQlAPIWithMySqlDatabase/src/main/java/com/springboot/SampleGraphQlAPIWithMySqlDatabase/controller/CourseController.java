package com.springboot.SampleGraphQlAPIWithMySqlDatabase.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.springboot.SampleGraphQlAPIWithMySqlDatabase.entity.CourseEntity;
import com.springboot.SampleGraphQlAPIWithMySqlDatabase.service.CourseService;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.DataFetcher;
import jakarta.annotation.PostConstruct;


import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class CourseController {
		
	@Autowired
    private CourseService courseService;
	
	@Value("classpath:graphql/schema.graphqls")
	private Resource schemaResource;
	
	private GraphQL graphQL;
	
	
	
	@PostConstruct
	public void loadSchema() throws IOException {
		File schemaFile = schemaResource.getFile();
		TypeDefinitionRegistry registry = new SchemaParser().parse(schemaFile);
		RuntimeWiring wiring = buildWiring();
		GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(registry, wiring);
		graphQL = GraphQL.newGraphQL(schema).build();
	}
	
	
	private RuntimeWiring buildWiring() {
		DataFetcher<List<CourseEntity>> fetcher1 = data -> {
			return (List<CourseEntity>) courseService.getAllCourses();
		};
		DataFetcher<CourseEntity> fetcher2 = data -> {
			return courseService.getCourseById(data.getArgument("id"));
		};	
		
		return RuntimeWiring.newRuntimeWiring().type("Query",
				typeWriting -> typeWriting.dataFetcher("allCourses", fetcher1).dataFetcher("getCourseById", fetcher2))
				.build();
		
	}
		
	
		
	
    @PostMapping("/post")
    public CourseEntity addCourse(@RequestBody CourseEntity course) {
        return courseService.saveCourse(course);
    }

    @PostMapping("/posts")
    public List<CourseEntity> addCourses(@RequestBody List<CourseEntity> courses) {
        return courseService.saveAllCourses(courses);
    }

    @PostMapping("/getAll")
    public ResponseEntity<Object> findAllCourses(@RequestBody String query) {
    	ExecutionResult result = graphQL.execute(query);
    	return new ResponseEntity<Object>(result, HttpStatus.OK);
    }

    @QueryMapping
    public CourseEntity findCourseById(@Argument String id) {
        return courseService.getCourseById(id);
    }

    @GetMapping("/get/course/{courseName}")
    public CourseEntity findCourseByName(@PathVariable String courseName) {
        return courseService.getCourseByName(courseName);
    }

    @PutMapping("/put/{id}")
    public CourseEntity updateCourse(@PathVariable String id,@RequestBody CourseEntity course) {
        return courseService.updateCourseById(id,course);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCourse(@PathVariable String id) {
        return courseService.deleteCourseById(id);
    }
}

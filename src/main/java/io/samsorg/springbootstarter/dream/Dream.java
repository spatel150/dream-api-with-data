package io.samsorg.springbootstarter.dream;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;

@Entity
// Mark the class as an JPA entity, which will map to a table in the database
// JPA can manage the persistence of the entities
public class Dream {
    // 'Dream' class is accessible by other classes
    @Id
    // ID is considered a primary key of the entity
    // Identifies each record in a table
    @UuidGenerator
    // The ID field is populated using a UUID value when a new record is created
    @Column(columnDefinition = "UUID")
    // The Column should be treated as an UUID in the database
    private String id;
    // We declare the ID field as a String that holds the UUID value of the Dream object. 

    @Column(name = "title")
    // Establish a connection between the model and schema. This tells JPA which data to store in the database
     // and how to retrieve the data. 
    private String title;
     // Maps to the "title" column in the database 
     
    @Column(name = "description", columnDefinition="text")
    private String description;
    // Maps to the "description" column in the database, with the column having a text type

    @Column(name = "explanation", columnDefinition="text")
    private String explanation;
    // Maps to the "explanation" column in the database, with the column having a text type

    @Column(name = "category")
    private String category;
    // Maps to the "category" column in the database

    @Version
    private Long version = 0L;
    // The 'Version' field is mainly used in optimistic locking considerations. 
    // Initialize the version field for concurrency control

    // Summary -- WWe are creating an entity called Dream as a class. The Dream entity maps to a table in a database. We have 5 attributes including ID as a UUID, which is unique.  We 
    // have a Column annotation that maps each attribute to a column in the database. Map the title column, description, explanation and category columns to the database. 
    // The Version annotation is using for optimistic locking considerations which will be explained later. 

    public Dream() {
    }
    // We have a default constructor that initializes an object with default values, which include null and false values

    public Dream(String id, String title, String description, String explanation, String category) {
        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.explanation = explanation;
        this.category = category;
    }

    // The parameterized constructor allows to take in one or more arguments. This creates an object with customized initial values

    // Summary -- We have two constructors including a default constructor that doesn't take in any arguments and a parametrized constructor that takes in customized values as
    // arguments based on the attributes so 'String title' for example. "A good book" 

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Getters and setters for the ID field

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Getters and setters for the title field

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Getters and setters for the description field

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    // Getters and setters for the explanation field

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // Getters and setters for the category field

     public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    // Getters and setters for the version field 

    // Summary -- Getters will return a value of a variable, where as setters will update the value, for example setting the value for any variable that is used in the Dream class. 

}

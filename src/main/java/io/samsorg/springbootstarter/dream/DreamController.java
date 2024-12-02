package io.samsorg.springbootstarter.dream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for handling HTTP requests related to Dream entities.
 * Provides endpoints to create, retrieve, update, and delete Dream entities.
 */

@RestController
// Indicates that this class is a REST controller and its methods handle HTTP requests
@CrossOrigin    
// Allows cross-origin requests from different domains/hosts (useful for front-end applications on different domains/hosts)

// Summary --

public class DreamController {
    
    @Autowired // Injects the beans [DreamService] and dependencies into the class. Creates an instance of the DreamService when the DreamController is created.
    private DreamService dreamService;
    // Injects the DreamService to handle business logic. Interacts with the DreamRepository to perform CRUD operations

    // Summary -- 
    // The Autowired annotation injects the DreamService bean into the controller. Spring creates and injects an
    // instance of the DreamService into the dreamService field when the DreamController has been instantiated/created 
    // The DreamService dreamService represents the service layer that the controller uses to handle any business logic. 
    // Interacts with the DreamRepository to handle CRUD operations. 

     /**
     * Handles HTTP GET requests to retrieve all Dream entities.
     * 
     * @return List<Dream> - A list of all Dream entities.
     */
    @GetMapping("/dreams")
    public List<Dream> getAllDreams() {
        // Calls the getAllDreams() method to retrieve all Dream entities
        return dreamService.getAllDreams();
    }

    // Summary -- 
    // We are making an HTTP GET request to the /dreams URL.
    // We are retrieving a list of all the 'Dream' objects from the database by calling the 
    // getAllDreams() method from the DreamService class


    /**
     * Handles HTTP GET requests to retrieve a single Dream entity by ID.
     * 
     * @param id - The ID of the Dream entity to retrieve.
     * @return Dream - The Dream entity with the specified ID.
     */
    @GetMapping("/dreams/{id}")
    public Dream getDream(@PathVariable String id) {
         // Calls the getDreams() to retrieve the Dream entity by ID
        return dreamService.getDream(id);
    }

    // Summary -- 
    // We are handling GET requests to the /dreams/{id} URL. We retrieve a single Dream object from the database based on the ID. We call the 
    // getDreams(id) method from the DreamService class. We then return a single Dream object based on the ID. 

     /**
     * Handles HTTP POST requests to create a new Dream entity.
     * 
     * @param dream - The Dream entity to create.
     */
    @PostMapping(value="/dreams")
    // Accepts a Dream object from the request body and passing it to the createDream() method
    public void createDream(@RequestBody Dream dream) {
        // Calls the createDream() method to create a new Dream entity
        dreamService.createDream(dream);
    }

    // Summary -- 
    // We are handling POST requests to the /dreams URL. We create a new Dream entity by passing in a Dream object inside the request body. 
    // We then pass in the dream object to the createDream() method and call the createDream(dream) method from the DreamService class.

     
    /**
     * Handles HTTP PUT requests to update an existing Dream entity.
     * 
     * @param dream - The Dream entity to update with new data.
     */
    @PutMapping(value="/dreams/{id}")
    public void updateDream(@RequestBody Dream dream) {
         // Calls the dream service method to update the Dream entity
        dreamService.updateDream(dream);
    }

    // Summary --
    // We handle PUT requests to the /dreams/{id} URl. We are updating an existing Dream entity by passing in the Dream object inside the request body 
    // similar to the POST request. We then pass the dream object in the updateDream method and call the updateDream(dream) method from the DreamService class

    /**
     * Handles HTTP DELETE to delete a Dream entity by ID.
     * 
     * @param id - The ID of the Dream entity to delete.
     */
    @DeleteMapping(value="/dreams/{id}")
    public void deleteDream(@PathVariable String id) {
        // Calls the dream service method to delete the Dream entity by ID
        dreamService.deleteDream(id);
    }

    // Summary --
    // We handle DELETE requests to the /dreams/{id} URL. We are deleting a Dream entity based on the ID passed in. We pass in the ID inside 
    // the deleteDream method and call the deleteDream method from the DreamService class. 

      /**
     * Handles HTTP DELETE requests to delete all Dream entities.
     */
     public void deleteAllDreams() {
        // Calls the service method to update the Dream entity
        dreamService.deleteAllDreams();
    }

    // Summary -- 
    // We have a method to delete all dreams although this method is not implemented as part of the frontend. Similar method to the DELETE request
    // but not passing in the ID. Use the DreamService class to call the deleteAllDreams() method.  

}

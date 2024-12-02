package io.samsorg.springbootstarter.dream;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import jakarta.persistence.OptimisticLockException;
import jakarta.transaction.Transactional;

@Service
public class DreamService {

    @Autowired
    private DreamRepository dreamRepository;
    

    // Summary --
    // We are injecting the DreamRepository inside the DreamService class using the @Autowired annotation. Allows the repository to perform data persistence operations. 

    public List<Dream> getAllDreams() {
        List<Dream> dreams = new ArrayList<>();
        dreamRepository.findAll()
            .forEach(dreams::add);
            return dreams;
    }

    // Summary --
    // We are retrieving all the dream entities from the database using the method getAllDreams. We use the findAll() method given by the repository and adds each dream to a list.
    // Provides a complete list of dreams stored in the database. 

    public Dream getDream(String id) {
        return dreamRepository.findById(id).orElse(null);
    }

    // Summary -- 
    // We are retrieving a single dream entity based on the ID from the database. We are using the findById() method given by the repository 
    // which returns a single dream entity if it exists otherwise return null based on the ID passed in.  

    @Transactional 
    // -- So all operations happen in a single transaction, which means the transaction must succeed or fail. If any part of the transaction fails, then an exception will be thrown
    // and the transaction will be rolled back so no changes are made to the database, if an error happens. Data integrity is maintained due to partial changes being rolled back.
    @Retryable(value = OptimisticLockException.class, maxAttempts = 3, backoff = @Backoff(delay = 1000))
    // -- We retry the method a few times once a specific exception is thrown. If there is an OptimisticLocking error, the method will try to execute 3 times. This is very useful if multiple users
    // are trying to create or update the same data at the same time or concurrently. 

    // Optimistic Locking -- a mechanism to prevent conflicts when multiple users or processes try to change the same data at the same time. For example, two people made changes 
    // to a data entity with ID of 1. The database will not be locked and assumes conflicts are rare and don't happen often. Updates are tracked based on a version or timestamp.
    // Does the version of the data being updated == version in the database? Yes == update the data. No == OptimisticLockingException is thrown.   

    public void createDream(Dream dream) {
        try {
            dreamRepository.save(dream);
        } catch (ObjectOptimisticLockingFailureException e) {
             throw new RuntimeException("Create conflict! Please try again", e);
        }
    }

    // Summary --
    // We have a try block that tries to save the dream entity to the database. We use the save() method provided by the repository and pass in the dream object. If the dream entity can't be saved
    // then the catch block will throw an exception  
    // OptimisticLockingFailureException: concurrency issue, where two users are updating data at the same time. 

    @Transactional
    @Retryable(value = OptimisticLockException.class, maxAttempts = 3, backoff = @Backoff(delay = 1000))
    public void updateDream(Dream dream) {
        // Step 1 -- Find an existing dream entity based on the ID using the findById() method. If not found, throw an exception. 
        // Store the found dream entity in the existingDream variable
        Dream existingDream = dreamRepository.findById(dream.getId())
                .orElseThrow(() -> new RuntimeException("Dream not found"));

        //  Step 2 -- Once the dream entity is found, we update the fields of the dream entity using the existingDream variable 
        // including title [getTitle], description [getDescription], explanation [getExplanation] and category [getCategory]
        existingDream.setTitle(dream.getTitle());
        existingDream.setDescription(dream.getDescription());
        existingDream.setExplanation(dream.getExplanation());
        existingDream.setCategory(dream.getCategory());

        // Step 3 -- After the fields have been updated from Step 2, we save the dream entity by passing in the newly updated existingDream variable 
        dreamRepository.save(existingDream);
        
    }

    // Summary -- 
    // We first get the dream entity based on the ID using the findById() method provided by the repository. If it's not found, throw an exception. Once we find a dream, 
    // we update the fields and then save the dream entity by passing in the updated existingDream variable. 

    @Transactional
    public void deleteDream(String id) {
        if (dreamRepository.existsById(id)) {
            dreamRepository.deleteById(id);
        } else {
            throw new RuntimeException("Dream not found");
        }
    }

    // Summary -- We are deleting a dream entity based on the ID. We first check to see if a single dream entity exists, and if it does, we execute the deleteById() method
    // provided by the dreamRepository. If the dream entity does not exist, we throw an exception that a dream was not found. 

    public void deleteAllDreams() {
        dreamRepository.deleteAll();
    }

    // Note: This method is not implemented on the frontend side. However, similar to the deleteDream method, we call the deleteAll() method provided by the repository.
    // Connected to a button that will delete all dream entries made on a database with one click. 
}

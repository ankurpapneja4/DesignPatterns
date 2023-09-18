package dev.java.designpatterns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * Prototype Pattern : Do Not Recreate A Heavy/Costly Object from Scratch. Instead, Clone It
 *
 *
 */
public class PrototypePattern {

    private static final Logger log = LoggerFactory.getLogger(PrototypePattern.class);
    public static class HeavyObject implements Cloneable{

        private String userName;
        private List<String> accessRoles;
        private List<String> userUploadedFileDetails;

        public HeavyObject(String userName) {
            this.userName = userName;
        }

        public void loadFromS3(){
            sleep(100);
            userUploadedFileDetails = List.of("Planning Sheet", "UML", "Module DTD", "Class Diagrams");
            log.info("Data Loaded From S3");
        }

        public void loadFromDatabase() {
            sleep(50);
            accessRoles = List.of("Project Owner", "Scrum Master");
            log.info("Data Loaded From DB");
        }

        /**
         *
         * Shallow Clone :
         *
         *  - Creates a new object that is a copy of the original object at the top level.
         *
         * - If the original object contains references to other objects (e.g., arrays, objects),
         *   shallow cloning does not create copies of those nested objects. Instead, it copies
         *   references to them.
         *
         */
        public HeavyObject shallowClone() {
            try
            {
                return (HeavyObject) super.clone();
            }
            catch ( CloneNotSupportedException exp )
            {
                throw new RuntimeException(exp);
            }
        }

        /**
         *
         * Deep Clone :
         *
         * - Deep cloning creates a new object, including copy of all nested objects
         *   and their properties.
         *
         * - It recursively traverses the original object and all its nested structures
         *   to create new instances of all objects and values.
         */
        public HeavyObject deepClone() {
            HeavyObject newObject             = new HeavyObject( this.userName + "" );
            newObject.accessRoles             = new ArrayList<>( this.accessRoles            );
            newObject.userUploadedFileDetails = new ArrayList<>(this.userUploadedFileDetails );

            return newObject;
        }


        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public List<String> getAccessRoles() {
            return accessRoles;
        }


        public List<String> getUserUploadedFileDetails() {
            return userUploadedFileDetails;
        }

        private static void sleep(long millis){

            try { Thread.sleep(millis); } catch( InterruptedException exp ) { log.error("InterrupedException When Thread Was Sleeping {}",exp); }
        }

    }

}

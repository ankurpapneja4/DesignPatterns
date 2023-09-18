package dev.java.designpatterns;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static dev.java.designpatterns.PrototypePattern.HeavyObject;
public class PrototypePatternTest {

    @Test
    public void shallowCloneTest(){

        HeavyObject object = new HeavyObject("Ava");
                    object.loadFromS3();
                    object.loadFromDatabase();

        HeavyObject newObject = object.shallowClone();


        Assertions.assertTrue( newObject.getUserName().equals(   object.getUserName()  ),
                "UserName should be equal");

        Assertions.assertSame( newObject.getUserName(), object.getUserName(),
                "Field userName  should refer to same String" );

        Assertions.assertSame( newObject.getAccessRoles(), object.getAccessRoles(),
                "Field accessRoles, should refer to same list");

        Assertions.assertSame( newObject.getUserUploadedFileDetails(), object.getUserUploadedFileDetails(),
                "Field userUploadedFileDetails should refer to same list");

    }

    @Test
    public void deepCloneTest(){

        HeavyObject object = new HeavyObject("Ava");
        object.loadFromS3();
        object.loadFromDatabase();

        HeavyObject newObject = object.deepClone();


        Assertions.assertTrue( newObject.getUserName().equals(   object.getUserName()  ),
                "UserName should be equal");

        Assertions.assertNotSame( newObject.getUserName(), object.getUserName(),
                "Field userName  should refer to different String objects" );

        Assertions.assertNotSame( newObject.getAccessRoles(), object.getAccessRoles(),
                "Field accessRoles should refer to different list objects");

        Assertions.assertNotSame( newObject.getUserUploadedFileDetails(), object.getUserUploadedFileDetails(),
                "Field userUploadedFileDetails should refer to different list objects");

    }
}

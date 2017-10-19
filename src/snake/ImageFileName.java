package snake;

import snakeGUI.Settings;
import java.lang.annotation.*;

@Documented
@Target(value=ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ImageFileName {
    String fileName() default Settings.defaultImageFileName;
}

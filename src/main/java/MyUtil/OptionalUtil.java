package MyUtil;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * Created by takeyuki on 17/05/27.
 */
public class OptionalUtil {
    public static <T> void doIfPresent(Optional<T> optional, Consumer<T> consumer) {
        optional.ifPresent(consumer);
    }
}

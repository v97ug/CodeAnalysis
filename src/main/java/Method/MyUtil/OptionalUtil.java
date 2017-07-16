package Method.MyUtil;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * Created by miyagi on 17/05/27.
 * @author miyagi
 */
public class OptionalUtil {
    public static <T> void doIfPresent(Optional<T> optional, Consumer<T> consumer) {
        optional.ifPresent(consumer);
    }
}

package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// BEGIN
class Validator {
    public static List<String> validate(Object o) {
        List<String> failed = new ArrayList<>();

        Field[] fields = o.getClass().getDeclaredFields();
        for (Field field : fields) {
            NotNull annotation = field.getAnnotation(NotNull.class);
            field.setAccessible(true);
            try {
                if (annotation != null && field.get(o) == null) {
                    failed.add(field.getName());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return failed;
    }

    public static Map<String, List<String>> advancedValidate(Object o) {
        Map<String, List<String>> failed = new HashMap<>();

        Field[] fields = o.getClass().getDeclaredFields();
        for (Field field : fields) {
            NotNull notNullAnnotation = field.getAnnotation(NotNull.class);
            MinLength minLengthAnnotation = field.getAnnotation(MinLength.class);
            field.setAccessible(true);
            try {
                if (notNullAnnotation != null && field.get(o) == null) {
                    if (!failed.containsKey(field.getName())) {
                        failed.put(field.getName(), new ArrayList<>());
                    }
                    failed.get(field.getName()).add("can not be null");
                }

                if (minLengthAnnotation != null) {
                    Object fieldValue = field.get(o);

                    if (fieldValue instanceof CharSequence) {
                        if (((CharSequence) fieldValue).length() < minLengthAnnotation.minLength()) {
                            if (!failed.containsKey(field.getName())) {
                                failed.put(field.getName(), new ArrayList<>());
                            }
                            failed.get(field.getName()).add("length less than " + minLengthAnnotation.minLength());
                        }
                    } else if (fieldValue != null){
                        if (!failed.containsKey(field.getName())) {
                            failed.put(field.getName(), new ArrayList<>());
                        }
                        failed.get(field.getName()).add("field doesn't have length attribute");
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return failed;
    }
}
// END

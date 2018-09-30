package io.vandalvnl.stringilizy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Optional;

public abstract class SequelizeStrings {

    public static String createSqlBind(String table, Object created) throws InvocationTargetException, IllegalAccessException {
        return createSql(table, created, true);
    }

    public static String createSql(String table, Object created) throws InvocationTargetException,
            IllegalAccessException {
        return createSql(table, created, false);
    }

    private static String createSql(String table, Object created, Boolean bind) throws InvocationTargetException,
            IllegalAccessException {
        StringBuilder builder = new StringBuilder().append("INSERT INTO ").append(table).append(" ");
        for (Method method : created.getClass().getDeclaredMethods()) {
            if (isGetter(method)) {
                Optional<Object> value = Optional.of(method.invoke(created));
                builder.append(toDatabase(method.getName()))
                        .append(" = ")
                        .append(bind ? "?" : sqlValue(value))
                        .append(", ");
            }
        }
        return removeFinalCommas(builder);
    }

    private static String toDatabase(String name) {
        return Stringilizy.toSneakCase(Stringilizy.getter2prop(name));
    }

    private static String removeFinalCommas(StringBuilder builder) {
        return builder.deleteCharAt(builder.length() - 1)
                .deleteCharAt(builder.length() - 1)
                .toString();
    }

    private static String sqlValue(Optional<Object> value) {
        if (value.isPresent()) {
            String className = value.get().getClass().getGenericSuperclass().getTypeName();
            if (className.endsWith("Number")) {
                return String.valueOf(value.get());
            }
            return "'" + value.get() + "'";
        }
        return "";
    }

    private static Boolean isGetter(Method method) {
        return Modifier.isPublic(method.getModifiers())
                && method.getParameterTypes().length == 0
                && method.getReturnType() != void.class
                && (method.getName().startsWith("get")
                || method.getName().startsWith("is"));
    }
}

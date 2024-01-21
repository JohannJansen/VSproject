package com.mygdx.kotc.kotcrpc;

import java.lang.reflect.Method;

public class MethodUtils {

    public static void invokeMethod(Object targetObject, String methodName, Object[] parameters) {
        Class<?> targetClass = targetObject.getClass();

        try {
            // Get the method with the specified name and parameter types
            Method method = findMethod(targetClass, methodName, parameters);

            // Invoke the method on the target object
            if (method != null) {
                method.invoke(targetObject, parameters);
            } else {
                System.out.println("Method not found");
            }
        } catch (Exception e) {
            System.out.println("Error invoking method");
            e.printStackTrace();
        }
    }

    private static Method findMethod(Class<?> targetClass, String methodName, Object[] parameters) {
        // Get all methods in the class
        Method[] methods = targetClass.getDeclaredMethods();

        // Iterate through methods to find a match
        for (Method method : methods) {
            if (method.getName().equals(methodName) && parametersMatch(method.getParameterTypes(), parameters)) {
                return method;
            }
        }

        return null; // Method not found
    }

    private static boolean parametersMatch(Class<?>[] parameterTypes, Object[] parameters) {
        // Check if the number of parameters match
        if (parameterTypes.length != parameters.length) {
            return false;
        }

        // Check if each parameter type matches
        for (int i = 0; i < parameterTypes.length; i++) {
            if (!isAssignable(parameterTypes[i], parameters[i])) {
                return false;
            }
        }

        return true;
    }

    private static boolean isAssignable(Class<?> targetType, Object value) {
        // Handle primitive types
        if (targetType.isPrimitive()) {
            if (targetType == int.class && value instanceof Integer) {
                return true;
            }
            // Add similar checks for other primitive types as needed
        }

        // Handle array types
        if (targetType.isArray() && value.getClass().isArray()) {
            Class<?> componentType = targetType.getComponentType();
            return isAssignable(componentType, ((Object[]) value)[0]);
        }

        // Check assignability for non-primitive and non-array types
        return targetType.isInstance(value);
    }
}

package com.resume.app;

import com.resume.app.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume resume = new Resume();
        Field uuid = resume.getClass().getDeclaredField("uuid");
        uuid.setAccessible(true);
        System.out.println(uuid.getName());
        System.out.println(uuid.get(resume));
        uuid.set(resume, "new_uuid");

        System.out.println("\n================ toString() method ===================");
        Method toString = resume.getClass().getMethod("toString");
        System.out.println(toString.invoke(resume));


    }
}

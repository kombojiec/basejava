//package com.resume.app.storage;
//
//import com.resume.app.model.Resume;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//public abstract class AbstractMapStorage<T> extends AbstractStorage<String> {
//    protected Map<String, Resume> storage = new HashMap<>();
//
//    @Override
//    public int getSize() {
//        return storage.size();
//    }
//
//    @Override
//    public void clear() {
//        storage.clear();
//    }
//
//    @Override
//    protected Stream<Resume> getAllResumeStream() {
//        return storage.entrySet().stream()
//                .map(el -> el.getValue())
//                .collect(Collectors.toList())
//                .stream();
//    }
//}

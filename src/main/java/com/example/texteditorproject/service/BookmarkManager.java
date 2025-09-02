package com.example.texteditorproject.service;

import com.example.texteditorproject.model.Bookmark;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BookmarkManager {
    private final List<Bookmark> bookmarks = new ArrayList<>();
    private final File bookmarkFile = new File("bookmarks.json");
    private final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();


    public BookmarkManager() {
        loadBookmarks();
    }

    public void addBookmark(Bookmark bookmark) {
        bookmarks.add(bookmark);
        saveBookmarks();
    }

    public List<Bookmark> getAllBookmarks() {
        return new ArrayList<>(bookmarks);
    }

    public Bookmark getBookmarkByFileAndPosition(String filePath, int position) {
        return bookmarks.stream()
                .filter(b -> b.getFilePath().equals(filePath) && b.getPosition() == position)
                .findFirst()
                .orElse(null);
    }

    private void saveBookmarks() {
        try (Writer writer = new FileWriter(bookmarkFile)) {
            gson.toJson(bookmarks, writer);
        } catch (IOException e) {
            System.err.println("Failed to save bookmarks: " + e.getMessage());
        }
    }

    private void loadBookmarks() {
        if (!bookmarkFile.exists()) {
            return;
        }

        try (Reader reader = new FileReader(bookmarkFile)) {
            Type listType = new TypeToken<List<Bookmark>>() {}.getType();
            List<Bookmark> loadedBookmarks = gson.fromJson(reader, listType);
            if (loadedBookmarks != null) {
                bookmarks.addAll(loadedBookmarks);
            }
        } catch (IOException e) {
            System.err.println("Failed to load bookmarks: " + e.getMessage());
        }
    }
}

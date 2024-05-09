package com.dayz.database.Dotawiki.service;

import com.dayz.database.Dotawiki.entity.bookmarks.Bookmark;
import com.dayz.database.Dotawiki.repository.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;

    @Autowired
    public BookmarkService(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }

    public void saveBookmark(Bookmark bookmark) {
        bookmarkRepository.save(bookmark);
    }

    public void deleteBookmarkByItemIdAndType(Long itemId,String type) {
        bookmarkRepository.deleteBookmarkByItemIdAndItemType(itemId, type);
    }

    public List<Bookmark> getAllBookmarkByUserId(Long userId) {
        return bookmarkRepository.getBookmarksByUserId(userId);
    }

}

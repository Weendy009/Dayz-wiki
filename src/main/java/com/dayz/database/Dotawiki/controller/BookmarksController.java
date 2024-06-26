package com.dayz.database.Dotawiki.controller;

import com.dayz.database.Dotawiki.entity.bookmarks.Bookmark;
import com.dayz.database.Dotawiki.entity.dto.BookmarkDTO;
import com.dayz.database.Dotawiki.entity.items.Item;
import com.dayz.database.Dotawiki.service.BookmarkService;
import com.dayz.database.Dotawiki.service.items.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/bookmarks")
public class BookmarksController {
    private final BookmarkService bookmarkService;
    private final ItemService itemService;

    @Autowired
    public BookmarksController(BookmarkService bookmarkService, ItemService itemService) {
        this.bookmarkService = bookmarkService;
        this.itemService = itemService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addBookmark(BookmarkDTO bookmarkDTO, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        Bookmark bookmark = new Bookmark();

        bookmark.setItemType(bookmarkDTO.getType());
        bookmark.setItemId(bookmarkDTO.getId());
        bookmark.setUserId(userId);

        bookmarkService.saveBookmark(bookmark);
        return ResponseEntity.ok("{\"status\": \"success\"}");
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Item>> getAllBookmarksByUser(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            Long userId = (Long) session.getAttribute("userId");
            List<Bookmark> bookmarks = bookmarkService.getAllBookmarkByUserId(userId);
            List<Item> bookmarkDTOS = new ArrayList<>();
            bookmarkDTOS.addAll(itemService.getItemsByIdAndType(bookmarks));
            bookmarkDTOS.removeIf(Objects::isNull);
            return ResponseEntity.ok(bookmarkDTOS);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete/{id}-{itemType}")
    public ResponseEntity<String> deleteBookmark(@PathVariable Long id,@PathVariable String itemType) {
        try {
            bookmarkService.deleteBookmarkByItemIdAndType(id, itemType);
            return ResponseEntity.ok("{\"status\": \"success\"}");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

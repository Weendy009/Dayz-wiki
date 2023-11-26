package com.dota.database.Dotawiki.controller;

import com.dota.database.Dotawiki.entity.bookmarks.Bookmark;
import com.dota.database.Dotawiki.entity.controllerRequests.BookmarkDTO;
import com.dota.database.Dotawiki.entity.items.Item;
import com.dota.database.Dotawiki.service.BookmarkService;
import com.dota.database.Dotawiki.service.WeaponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/bookmarks")
public class BookmarksController {

    private final WeaponService weaponService;

    private final BookmarkService bookmarkService;

    @Autowired
    public BookmarksController(BookmarkService bookmarkService, WeaponService weaponService) {
        this.bookmarkService = bookmarkService;
        this.weaponService = weaponService;
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

            for (Bookmark bookmark : bookmarks) {
                bookmarkDTOS.add(weaponService.getWeaponByIdAndType(Long.valueOf(bookmark.getItemId()), bookmark.getItemType()));
            }
            return ResponseEntity.ok(bookmarkDTOS);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBookmark(@PathVariable String id) {
        try {
            bookmarkService.deleteBookmarkByItemId(id);
            return ResponseEntity.ok("{\"status\": \"success\"}");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

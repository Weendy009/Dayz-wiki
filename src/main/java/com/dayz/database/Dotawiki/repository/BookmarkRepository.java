package com.dayz.database.Dotawiki.repository;

import com.dayz.database.Dotawiki.entity.bookmarks.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    public List<Bookmark> getBookmarksByUserId(Long userId);

    @Transactional
    @Modifying
    public void deleteBookmarkByItemIdAndItemType(Long itemId, String itemType);

}

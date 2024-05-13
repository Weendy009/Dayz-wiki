const bookmarksButton = document.getElementById("bookmarksButton");
const bookmarksModal = document.getElementById("bookmarksModal");
const row = document.querySelector('.row');

bookmarksButton.addEventListener("click", function () {
    fetch('/bookmarks/getAll')
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            addToBookmarksTableByType(data);
            bookmarksModal.style.display = "block";
        })
        .catch(error => {
            console.error('Ошибка при получении данных с сервера:', error);
        });
});
function addToBookmarksTableByType(items) {
    const tableBody = document.querySelector("#bookmarksTable tbody");

    tableBody.innerHTML = "";

    const rowsByType = {};

    items.forEach(item => {
        if (!rowsByType[item.type]) {
            rowsByType[item.type] = [];
        }
        rowsByType[item.type].push(item);
    });

    for (const type in rowsByType) {
        if (rowsByType.hasOwnProperty(type)) {
            const typeRow = tableBody.insertRow();
            const typeCell = typeRow.insertCell();
            typeCell.colSpan = Object.keys(items[0]).length + 1;
            typeCell.textContent = "---------------------------------------------------------------------------------- Type: " + type + " ----------------------------------------------------------------------------------";
            typeCell.classList.add("type-header");

            rowsByType[type].forEach(item => {
                const newRow = tableBody.insertRow();
                for (const field in item) {
                    if (item.hasOwnProperty(field)) {
                        const cell = newRow.insertCell();
                        cell.textContent = item[field];
                    }
                }
                const deleteCell = newRow.insertCell();
                const deleteButton = document.createElement("button");
                deleteButton.textContent = "Delete";
                deleteButton.classList.add("btn", "btn-danger");
                deleteButton.addEventListener("click", function () {
                    deleteBookmark(item.id, item.type);
                });
                deleteCell.appendChild(deleteButton);
            });
        }
    }
}
function deleteBookmark(bookmarkId, bookmarkType) {
    fetch(`/bookmarks/delete/${bookmarkId}-${bookmarkType}`, {
        method: 'DELETE',
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            if (data.status === 'success') {
                refreshBookmarksTable();
            }
        })
        .catch(error => {
            console.error('Ошибка при удалении закладки:', error);
        });
}
function refreshBookmarksTable() {
    const tableBody = document.querySelector("#bookmarksTable tbody");
    tableBody.innerHTML = "";

    fetch('/bookmarks/getAll')
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            addToBookmarksTableByType(data);
        })
        .catch(error => {
            console.error('Ошибка при обновлении данных и таблицы:', error);
        });
}
window.addEventListener("click", function (event) {
    if (event.target === bookmarksModal) {
        bookmarksModal.style.display = "none";
    }
});
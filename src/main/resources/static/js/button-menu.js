const toggleButton = document.getElementById("toggleButton");
const modal = document.getElementById("myModal");
const icon = document.getElementById("icon");
let isOpen = false;

function updateModalSize() {
    const modalContent = document.querySelector(".modal-content-two");
    const newWidth = window.innerWidth * 0.23;
    const newHeight = window.innerHeight * 0.22;
    const leftOffset = 70;
    modalContent.style.left = leftOffset + "px";
    modalContent.style.maxWidth = newWidth + "px";
    modalContent.style.height = newHeight + "px";
}

window.addEventListener("load", updateModalSize);
window.addEventListener("resize", updateModalSize);

toggleButton.addEventListener("click", () => {
    if (isOpen) {
        modal.classList.remove("active");
    } else {
        modal.classList.add("active");
    }
    isOpen = !isOpen;
    if (isOpen) {
        icon.style.transform = "rotate(90deg) scale(1.2)";
    } else {
        icon.style.transform = "none";
    }
});

modal.addEventListener("click", function(event) {
    if (event.target === modal && isOpen) {
        modal.classList.remove("active");
        isOpen = false;
        icon.style.transform = "none";
    }
});
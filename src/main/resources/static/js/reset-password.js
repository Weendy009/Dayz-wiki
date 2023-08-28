document.addEventListener("DOMContentLoaded", function () {
    const changePasswordBtn = document.getElementById("change-password-btn");
    const errorMessage = document.getElementById("error-message");
    const email = document.getElementById("email").value;

    changePasswordBtn.addEventListener("click", function () {
        const password = document.getElementById("password").value;
        const confirmPassword = document.getElementById("confirmPassword").value;

        if (password.length < 6) {
            errorMessage.textContent = "Пароль должен содержать не менее 6 символов.";
        } else if (password !== confirmPassword) {
            errorMessage.textContent = "Пароли не совпадают.";
        } else {
            errorMessage.textContent = "";
            const formData = new FormData();
            formData.append("password", password);
            formData.append("confirmPassword", confirmPassword);
            formData.append("email", email);

            fetch("/change", {
                method: "POST",
                body: formData
            })
                .then(response => {
                    return response.text();
                })
                .then(data => {
                    console.log(data);
                })
                .catch(error => {
                    console.error("Произошла ошибка:", error);
                });

        }
    });
});
function closeModal(modalId) {
    $(modalId).modal('hide');
}
document.querySelector(".register-button-containerTwo button").addEventListener("click", function () {
    closeModal('#registerModal');
    $('#loginModal').modal('show');
});


function showNotification(message, type, duration) {
    const iconClass = type === 'success' ? 'fa-check-circle' : 'fa-times-circle';
    const notification = $(`
        <div class="alert alert-${type} d-flex align-items-center">
            <i class="fas ${iconClass} mr-2"></i>
            <span>${message}</span>
            <div class="progress-bar"></div>
        </div>`
    );

    $('.notifications').append(notification);

    const progressBar = notification.find('.progress-bar');
    progressBar.animate({width: '100%'}, duration, function () {
        notification.fadeOut(300, function () {
            $(this).remove();
        });
    });
}

document.getElementById("loginForm").addEventListener("submit", function (event) {
    event.preventDefault();
    event.stopPropagation();

    const formData = {
        name: document.querySelector('input[name=name]').value,
        password: document.querySelector('input[name=password]').value
    };
    console.log(formData.name)
    console.log(formData.password)
    $.ajax({
        url: '/authenticate',
        type: 'POST',
        data: JSON.stringify(formData),
        contentType: 'application/json',
        success: function (response) {
            if (response === "success") {
                showNotification("You are successfully logged!", "success", 3000);
                setTimeout(function () {
                    $('#loginModal').modal('hide');
                }, 500);
            } else {
                showNotification("Invalid username or password", "danger", 3000);
                setTimeout(function () {
                    $('#loginModal').modal('hide');
                }, 500);
            }
        },

        error: function (error) {
            console.error('Request error:', error);
        }
    });

});
document.querySelector(".register-button-container button").addEventListener("click", function () {
    $('#loginModal').modal('hide');
    $('#registerModal').modal('show');
});
document.getElementById("registerForm").addEventListener("submit", function (event) {
    event.preventDefault();
    event.stopPropagation();

    const nameField = document.querySelector('input[name=nameRegister]');
    const emailField = document.querySelector('input[name=email]');
    const passwordField = document.querySelector('input[name=passwordRegister]');
    const confirmPasswordField = document.querySelector('input[name=confirmPassword]');

    clearFieldErrors([nameField, emailField, passwordField, confirmPasswordField]);

    const formData = {
        name: nameField.value,
        email: emailField.value,
        password: passwordField.value,
        confirmPassword: confirmPasswordField.value
    };

    $.ajax({
        url: '/register',
        type: 'POST',
        data: JSON.stringify(formData),
        contentType: 'application/json',
        success: function (response) {
            console.log(response);
            if (response === "success") {
                showNotification("Registration successful!", "success", 3000);
                setTimeout(function () {
                    $('#registerModal').modal('hide');
                }, 500);
            } else if (response === "This email is already in use on another account") {
                showError(emailField, response);
            } else if (response === "This username is already taken") {
                showError(nameField, response);
            } else if (response === "Password mismatch") {
                showError(passwordField, response);
                showError(confirmPasswordField, response);
            } else if (response === "Password at least 6 characters") {
                showError(passwordField, response);
                showError(confirmPasswordField, response);
            }
        },
        error: function (error) {
            console.error('Request error:', error);
        }
    });
});

function showError(field, message) {
    const errorDiv = document.createElement('div');
    errorDiv.className = 'error-message';
    errorDiv.innerText = message;
    field.parentNode.appendChild(errorDiv);
    field.classList.add('error-input');
}

function clearFieldErrors(fields) {
    fields.forEach(function (field) {
        const errorDiv = field.parentNode.querySelector('.error-message');
        if (errorDiv) {
            errorDiv.remove();
        }
        field.classList.remove('error-input');
    });
}
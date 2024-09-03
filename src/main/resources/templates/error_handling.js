document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('registration-form');
    const errorMessageDiv = document.getElementById('error-message');
    console.info()

    form.addEventListener('submit', async (event) => {
        event.preventDefault();

        try {
            const response = await fetch('/registration', {
                method: 'POST'
            });

            if (response.ok) {
                // Регистрация успешна
                // ...
            } else {
                const errorData = await response.json();
                errorMessageDiv.textContent = errorData.error;
            }
        } catch (error) {
            console.error('Error during registration:', error);
            errorMessageDiv.textContent = 'Произошла ошибка при регистрации.';
        }
    });
});
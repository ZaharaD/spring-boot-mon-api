function toggleMenu() {
    var menu = document.getElementById("menu");
    menu.classList.toggle("show");
}

// Boutons de changement de mode
const sign_in_btn = document.querySelector("#sign-in-btn");
const sign_up_btn = document.querySelector("#sign-up-btn");
const sign_in_btn2 = document.querySelector("#sign-in-btn2");
const sign_up_btn2 = document.querySelector("#sign-up-btn2");
const container = document.querySelector(".container");

// Changement d'état pour affichage formulaire
sign_up_btn?.addEventListener("click", () => {
    container?.classList.add("sign-up-mode");
});
sign_in_btn?.addEventListener("click", () => {
    container?.classList.remove("sign-up-mode");
});
sign_up_btn2?.addEventListener("click", () => {
    container?.classList.add("sign-up-mode2");
});
sign_in_btn2?.addEventListener("click", () => {
    container?.classList.remove("sign-up-mode2");
});

// Création du compte admin par défaut si non existant
if (!localStorage.getItem('admin')) {
    const adminUser = {
        id: 'admin',
        username: 'admin@ordexa.com',
        password: 'admin123', // À changer en production !
        prenom: 'Admin',
        nom: 'System',
        role: 'admin'
    };
    localStorage.setItem('admin', JSON.stringify(adminUser));
}

// Connexion (formulaire .sign-in-form)
document.querySelector('.sign-in-form')?.addEventListener('submit', function (e) {
    e.preventDefault();

    const emailInput = document.querySelector('.sign-in-form input[placeholder="Username"]');
    const passwordInput = document.querySelector('.sign-in-form input[placeholder="Password"]');

    const email = emailInput.value.trim();
    const password = passwordInput.value.trim();

    if (!email.includes('@') || !email.includes('.')) {
        alert("Email invalide !");
        return;
    }

    if (password === '') {
        alert("Mot de passe requis !");
        return;
    }

    // Vérification des identifiants admin
    const adminUser = JSON.parse(localStorage.getItem('admin'));
    if (email === adminUser.username && password === adminUser.password) {
        localStorage.setItem('user', JSON.stringify(adminUser));
        window.location.href = "page2.html";
        return;
    }

    // Vérification des identifiants client
    const clients = JSON.parse(localStorage.getItem('clients') || '[]');
    const client = clients.find(c => c.email === email);
    
    if (client && client.password === password) {
        const userData = {
            ...client,
            role: 'client'
        };
        localStorage.setItem('user', JSON.stringify(userData));
        window.location.href = "client.html";
        return;
    }

    alert("Identifiants incorrects !");
});

// Inscription (formulaire .sign-up-form)
document.querySelector('.sign-up-form')?.addEventListener('submit', function (e) {
    e.preventDefault();

    const username = document.querySelector('.sign-up-form input[placeholder="Username"]').value.trim();
    const email = document.querySelector('.sign-up-form input[placeholder="Email"]').value.trim();
    const password = document.querySelector('.sign-up-form input[placeholder="Password"]').value.trim();
    const confirmPassword = document.querySelector('.sign-up-form input[placeholder="Confirmer le mot de passe"]').value.trim();

    if (!email.includes('@') || !email.includes('.')) {
        alert("Email invalide !");
        return;
    }

    if (password === '') {
        alert("Mot de passe requis !");
        return;
    }

    if (password !== confirmPassword) {
        alert("Les mots de passe ne correspondent pas !");
        return;
    }

    // Vérifier si l'email n'est pas déjà utilisé
    const clients = JSON.parse(localStorage.getItem('clients') || '[]');
    if (clients.some(c => c.email === email)) {
        alert("Cet email est déjà utilisé !");
        return;
    }

    // Créer le nouveau client
    const newClient = {
        id: clients.length + 1,
        username,
        email,
        password,
        prenom: username.split(' ')[0] || '',
        nom: username.split(' ')[1] || '',
        tel: '',
        inscription: new Date().toISOString().split('T')[0],
        role: 'client'
    };

    // Ajouter le client à la liste
    clients.push(newClient);
    localStorage.setItem('clients', JSON.stringify(clients));

    // Connecter automatiquement
    localStorage.setItem('user', JSON.stringify(newClient));

    alert("Inscription réussie !");
    window.location.href = "client.html";
});

// Bouton personnalisé : Connexion directe
document.getElementById('btnConnexion')?.addEventListener('click', function () {
    const connexionReussie = true; // Remplace par ta condition réelle

    if (connexionReussie) {
        window.location.href = "page2.html";
    } else {
        alert('Erreur de connexion');
    }
});

// Auth pages switching
document.querySelectorAll('a[href="#"]')?.forEach(link => {
    link.addEventListener('click', function (e) {
        e.preventDefault();
        const loginPage = document.getElementById('login-page');
        const registerPage = document.getElementById('register-page');

        if (loginPage.classList.contains('hidden')) {
            loginPage.classList.remove('hidden');
            registerPage.classList.add('hidden');
        } else {
            loginPage.classList.add('hidden');
            registerPage.classList.remove('hidden');
        }
    });
});

// Validation du formulaire de connexion (#login-form)
document.getElementById('login-form')?.addEventListener('submit', function (e) {
    e.preventDefault();
    const email = document.getElementById('email')?.value;
    const password = document.getElementById('password')?.value;
    const emailError = document.getElementById('email-error');
    const passwordError = document.getElementById('password-error');
    let isValid = true;

    if (!email.includes('@') || !email.includes('.')) {
        emailError.classList.remove('hidden');
        isValid = false;
    } else {
        emailError.classList.add('hidden');
    }

    if (password === '') {
        passwordError.classList.remove('hidden');
        isValid = false;
    } else {
        passwordError.classList.add('hidden');
    }

    if (isValid) {
        // Connexion réussie
        alert("Connexion réussie !");
        window.location.href = "page2.html";
    }
});

// Validation du formulaire d'inscription (#register-form)
document.getElementById('register-form')?.addEventListener('submit', function (e) {
    e.preventDefault();
    const password = document.getElementById('reg-password')?.value;
    const confirmPassword = document.getElementById('confirm-password')?.value;
    const passwordMatchError = document.getElementById('password-match-error');

    if (password !== confirmPassword) {
        passwordMatchError.classList.remove('hidden');
    } else {
        passwordMatchError.classList.add('hidden');
        alert("Inscription réussie !");
        window.location.href = "page2.html";
    }
});

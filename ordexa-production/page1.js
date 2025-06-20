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
        password: 'ordexa55', // À changer en production !
        prenom: 'Admin',
        nom: 'System',
        role: 'admin'
    };
    localStorage.setItem('admin', JSON.stringify(adminUser));
}

// L'URL de base de l'API déployée
const API_BASE_URL = "https://ordexa-z.onrender.com";

// --- NOUVELLE GESTION DE LA CONNEXION ---
document.querySelector('.sign-in-form')?.addEventListener('submit', async function (e) {
    e.preventDefault();

    const emailInput = document.querySelector('.sign-in-form input[type="text"]');
    const passwordInput = document.querySelector('.sign-in-form input[type="password"]');
    const email = emailInput.value.trim();
    const password = passwordInput.value.trim();

    if (!email || !password) {
        alert("Tous les champs sont requis.");
        return;
    }

    // Authentification simple pour la production
    if (email === 'admin@ordexa.ml' && password === 'admin123') {
        // Créer un utilisateur admin
        const user = {
            id: 1,
            nom: "Koné",
            prenom: "Mohamed",
            email: email,
            role: "ADMIN"
        };
        
        // Simuler un token JWT
        const token = btoa(JSON.stringify({
            userId: user.id,
            email: user.email,
            role: user.role,
            exp: Date.now() + 3600000 // 1 heure
        }));
        
        localStorage.setItem('jwtToken', token);
        localStorage.setItem('user', JSON.stringify(user));
        
        alert('Connexion réussie ! Bienvenue ' + user.prenom + ' ' + user.nom);
        window.location.href = "page2.html";
    } else {
        alert('Identifiants incorrects. Veuillez vérifier votre email et mot de passe.');
    }
});

// --- NOUVELLE GESTION DE L'INSCRIPTION ---
document.querySelector('.sign-up-form')?.addEventListener('submit', async function (e) {
    e.preventDefault();

    const nomInput = document.querySelector('.sign-up-form input[placeholder="Nom"]');
    const prenomInput = document.querySelector('.sign-up-form input[placeholder="Prénom"]');
    const emailInput = document.querySelector('.sign-up-form input[placeholder="Email"]');
    const passwordInput = document.querySelector('.sign-up-form input[placeholder="Mot de passe"]');
    
    const nom = nomInput.value.trim();
    const prenom = prenomInput.value.trim();
    const email = emailInput.value.trim();
    const password = passwordInput.value.trim();
    
    if (!nom || !prenom || !email || !password) {
        alert("Tous les champs sont requis.");
        return;
    }

    // Simuler l'inscription
    alert('Inscription réussie ! Vous pouvez maintenant vous connecter.');
    
    // Retour au formulaire de connexion
    container?.classList.remove("sign-up-mode");
    
    // Vider les champs
    nomInput.value = '';
    prenomInput.value = '';
    emailInput.value = '';
    passwordInput.value = '';
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

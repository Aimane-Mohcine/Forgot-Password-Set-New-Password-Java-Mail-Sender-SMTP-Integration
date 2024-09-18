import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  constructor(private router: Router) { }

  logout() {
    // Supprimer les informations d'authentification de l'utilisateur (par exemple, le token)
    localStorage.removeItem('authToken');

    // Rediriger l'utilisateur vers la page de login
    this.router.navigate(['/']);
  }
}

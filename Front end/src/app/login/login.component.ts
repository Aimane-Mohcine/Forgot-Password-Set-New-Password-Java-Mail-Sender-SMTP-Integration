import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  email: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private http: HttpClient, private router: Router) { }

  onSubmit() {
    const apiUrl = 'http://localhost:8080/api/login';  // Remplacez par l'URL de votre backend

    this.http.post<any>(apiUrl, { email: this.email, password: this.password }).subscribe({
      next: (response) => {
        if (response.success) {
          this.router.navigate(['/home']);
        } else {
          this.errorMessage = 'Invalid credentials';
        }
      },
      error: (err) => {
        this.errorMessage = 'Invalid credentials';
      }
    });
  }
}

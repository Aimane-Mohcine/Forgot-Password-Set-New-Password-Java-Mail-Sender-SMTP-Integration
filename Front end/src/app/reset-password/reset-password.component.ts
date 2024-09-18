import { Component } from '@angular/core';  // Add the component decorator import
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css'] // Optional, if you have a CSS file
})
export class ResetPasswordComponent {
  newPassword: string = ''; // Initialize with an empty string
  email: string | null = localStorage.getItem('email'); // Use string | null in case email is not found in localStorage
  errorMessage: string = ''; // Initialize with an empty string

  constructor(private http: HttpClient, private router: Router) {}

  onSubmit() {
    if (!this.email) {  // Ensure email is not null before sending the request
      this.errorMessage = 'No email found. Please try the password reset process again.';
      return;
    }

    this.http.post('http://localhost:8080/api/reset-password', { email: this.email, newPassword: this.newPassword }).subscribe(
      (response: any) => {
        alert('Password successfully reset.');
        this.router.navigate(['/login']);
      },
      (error) => {
        this.errorMessage = 'Error resetting password. Please try again.';
      }
    );
  }
}

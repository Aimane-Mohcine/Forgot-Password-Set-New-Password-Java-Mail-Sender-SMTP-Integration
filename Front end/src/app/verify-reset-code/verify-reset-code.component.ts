import { Component } from '@angular/core';  // Import Component
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-verify-reset-code',
  templateUrl: './verify-reset-code.component.html',
  styleUrls: ['./verify-reset-code.component.css'] // Optional, if you have a CSS file
})
export class VerifyResetCodeComponent {
  code: string = ''; // Initialize with an empty string
  email: string | null = localStorage.getItem('email'); // Use string | null in case email is not found in localStorage
  errorMessage: string | null = null; // Allow null

  constructor(private http: HttpClient, private router: Router) {}

  onSubmit() {
    if (!this.email) {  // Ensure email is not null before sending the request
      this.errorMessage = 'No email found. Please start the password reset process again.';
      return;
    }

    this.http.post('http://localhost:8080/api/verify-reset-code', { email: this.email, code: this.code }).subscribe(
      (response: any) => {
        this.errorMessage = null;
        this.router.navigate(['/reset-password']);
      },
      (error) => {
        this.errorMessage = 'Invalid code. Please try again.';
      }
    );
  }
}

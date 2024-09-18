import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent {
  email: string = ''; // Initialize with an empty string
  errorMessage: string = ''; // Initialize with an empty string
  successMessage: string = ''; // Initialize with an empty string

  constructor(private http: HttpClient, private router: Router) {}

  onSubmit() {
    if (!this.email) {
      this.errorMessage = 'Please enter a valid email address.';
      return;
    }

    this.http.post('http://localhost:8080/api/forgot-password', { email: this.email }).subscribe(
      (response: any) => {
      console.log(this.email);
        this.successMessage = 'Reset code sent to your email.';
        this.errorMessage = ''; // Clear the error message
        localStorage.setItem('email', this.email); // Store email for later use
        this.router.navigate(['/verify-reset-code']);
      },
      (error) => {
        console.log(this.email);
        this.errorMessage = 'User not found or error occurred.';
        this.successMessage = ''; // Clear the success message
      }
    );
  }
}

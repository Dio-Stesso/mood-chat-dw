import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

interface UserProfile {
  username: string;
  email: string;
  photoUrl: string;
}

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  profile: UserProfile = { username: '', email: '', photoUrl: '' };
  newPassword = '';
  currentPassword = '';

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.http.get<UserProfile>('/api/')
      .subscribe(data => this.profile = data);
  }

  onFileSelected(event: Event) {
    const input = event.target as HTMLInputElement;
    if (!input.files?.length) return;
    const file = input.files[0];
    const fd = new FormData();
    fd.append('photo', file);
    this.http.post<{ photoUrl: string }>('/api/', fd)
      .subscribe(res => this.profile.photoUrl = res.photoUrl);
  }

  saveProfile() {
    this.http.put<UserProfile>('/api/', {
      username: this.profile.username,
      email: this.profile.email
    }).subscribe(res => this.profile = res);
  }

  changePassword() {
    this.http.post('/api/', {
      currentPassword: this.currentPassword,
      newPassword: this.newPassword
    }).subscribe(() => {
      alert('Password updated');
      this.currentPassword = this.newPassword = '';
    });
  }
}

import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-connexion',
  templateUrl: './connexion.component.html',
  styleUrls: ['./connexion.component.css']
})
export class ConnexionComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
    const form = document.querySelector('form');
    // @ts-ignore
    form.addEventListener('submit', (event) => {
      event.preventDefault(); // empêche le formulaire de soumettre les données

      // @ts-ignore
      const username = document.querySelector('#User').value;

      if (username === 'admin') {
        window.location.href = 'http://localhost:4200/admin-delete';
      }
    });
  }

}



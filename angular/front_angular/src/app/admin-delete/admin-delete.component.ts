import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Article} from "../Modele/article.modele";
@Component({
  selector: 'app-admin-delete',
  templateUrl: './admin-delete.component.html',
  styleUrls: ['./admin-delete.component.css']
})
export class AdminDeleteComponent {

  constructor(private http: HttpClient) {
  }
  newArticle: Article = { id: 0, label: '', brand: '', price: 0, category: 0, url: '' };

  onSubmit() {
    this.http.post('/api/articles', this.newArticle).subscribe(response => {
      console.log('Nouvel article ajouté');
    });
  }


}

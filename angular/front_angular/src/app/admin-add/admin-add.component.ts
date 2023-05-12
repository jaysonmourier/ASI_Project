import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Article} from "../Modele/article.modele";
@Component({
  selector: 'app-admin-add',
  templateUrl: './admin-add.component.html',
  styleUrls: ['./admin-add.component.css']
})
export class AdminAddComponent {
  constructor(private http: HttpClient) {
  }
  newArticle: Article = { id: 0, label: '', brand: '', price: 0, category: 0, url: ''};
  categories: any;
  subCategories: any = [];
  successMessage: string = '';
  ngOnInit() {
    this.http.get<any>('http://localhost:8080/ASI_Project_war/api/category').subscribe(data => {
      this.categories = data;
      for(let i=0; i<this.categories.length; i++){
        for(let j=0; j<this.categories[i].sousCategories.length; j++){
          this.subCategories.push(this.categories[i].sousCategories[j]);
        }
      }
      console.log(this.subCategories);
    });
  }
  onSubmit() {
    console.log(this.newArticle);
    this.http.post('http://localhost:8080/ASI_Project_war/api/articles', this.newArticle).subscribe(response => {
      console.log('Nouvel article ajouté');
      this.successMessage = 'L\'article a été ajouté avec succès.';
    });

  }
}

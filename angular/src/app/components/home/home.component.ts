import { Component, OnInit } from '@angular/core';
import {Kweet} from "../../models/Kweet";
import {User} from "../../models/User";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public kweets: Kweet[]

  constructor() {
    this.kweets = Array.of(
      new Kweet(1, new User("Goos", "Goos van den Bekerom", new Date()), "Kweet tekst met #hashtags en mentions naar @Marvin enzo. Je kent het wel.", 2, new Date()),
      new Kweet(1, new User("Marvin", "Marvin Zwolsman", new Date()), "Vette #hashtags in deze #kweet man bruur!", 3, new Date())
    )
  }

  ngOnInit() {}
}
